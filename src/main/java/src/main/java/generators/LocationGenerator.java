package src.main.java.generators;

import src.main.java.utilities.Math;
import src.main.java.game.nouns.locations.*;

import java.util.*;


public class LocationGenerator {

    static int MIN_WORLDS = 3;
    static int MAX_WORLDS = 5;

    static int MIN_CONTINENTS = 3;
    static int MAX_CONTINENTS = 7;

    static int MIN_LOCATIONS = 10;
    static int MAX_LOCATIONS = 20;

    // dungeons
    static int MIN_ROOMS = 9;
    static int MAX_ROOMS = 36;

    // wilderness
    static int MIN_SPACES = 24;
    static int MAX_SPACES = 24;

    // towns
    static int MIN_BUILDINGS = 5;
    static int MAX_BUILDINGS = 10;


    // given a SolarSystem
    //      > generate the Planets
    //          > Continents per world
    //              > Locations per continent
    //                  > Rooms per location
    public static SolarSystem generate(SolarSystem system) {

        List<Planet> planets = system.makePlanets(Math.randomRange(MIN_WORLDS, MAX_WORLDS));

        // create all Places in the current SolarSystem
        for (Planet planet : planets) {
            int x = Math.randomRange(MIN_CONTINENTS, MAX_CONTINENTS);
            List<Continent> continents = planet.makeContinents(x);

            for (Continent continent : continents) {
                List<Location> locations = continent.makeLocations(Math.randomRange(MIN_LOCATIONS, MAX_LOCATIONS));

                for (Location location : locations) {

                    if (location.isTown()) {
                        List<Room> rooms = location.makeRooms(Math.randomRange(MIN_BUILDINGS, MAX_BUILDINGS));
                        generateTown(location, rooms);
                    }

                    if (location.isDungeon()) {
                        List<Room> rooms = location.makeRooms(Math.randomRange(MIN_ROOMS, MAX_ROOMS));
                        generateDungeon(location, rooms);
                    }

                    if (location.isWilderness()) {
                        List<Room> rooms = location.makeRooms(Math.randomRange(MIN_SPACES, MAX_SPACES));
                        generateWilderness(location, rooms);
                    }

                    assignEntranceExit(location);
                }
            }
        }

        // make connections between each Place
        connectAll(planets);
//        system.setGraph(generateLocationGraph(systems)); // TODO: when we have multiple SolarSystems
        for (Planet planet : planets) {
            List<Continent> continents = planet.getContinents();
            connectRandom(continents);

            for (Continent continent : continents) {
                List<Location> locations = continent.getLocations();
                connectRandom(locations);
            }
        }

        return system;
    }

    private static <T extends Place> void connectAll(List<? extends Place> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                nodes.get(i).connect(nodes.get(j));
                nodes.get(j).connect(nodes.get(i));
            }
        }
    }

    private static <T extends Place> void connectRandom(List<T> nodes) {
        if (nodes.isEmpty()) return;

        Random rng = Math.getRandom();

        // Ensure at least a spanning tree (everything is connected)
        for (int i = 1; i < nodes.size(); i++) {
            int j = rng.nextInt(i);
            nodes.get(i).connect(nodes.get(j));
            nodes.get(j).connect(nodes.get(i));
        }

        // Add some extra random edges
        int extraEdges = rng.nextInt(nodes.size());
        for (int k = 0; k < extraEdges; k++) {
            int a = rng.nextInt(nodes.size());
            int b = rng.nextInt(nodes.size());
            if (a != b) {
                nodes.get(a).connect(nodes.get(b));
                nodes.get(b).connect(nodes.get(a));
            }
        }
    }

    private static void generateTown(Location location, List<Room> rooms) {
        for (Room room : rooms) {
            location.connect(room);
            room.connect(location);
        }
    }

    private static Room[][] generateDungeon(Location location, List<Room> rooms) {
        if (rooms.isEmpty()) return new Room[0][0];

        Random rng = Math.getRandom();
        int gridSize = (int) java.lang.Math.ceil(java.lang.Math.sqrt(rooms.size()));
        Room[][] grid = new Room[gridSize][gridSize];

        // Place rooms into grid
        int index = 0;
        for (int y = 0; y < gridSize && index < rooms.size(); y++) {
            for (int x = 0; x < gridSize && index < rooms.size(); x++) {
                grid[x][y] = rooms.get(index++);
            }
        }

        // ---- Step 1: Maze-like full connectivity using DFS ----
        boolean[][] visited = new boolean[gridSize][gridSize];

        class Cell {
            int x, y;
            Cell(int x, int y) { this.x = x; this.y = y; }
        }

        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(0, 0)); // start at top-left
        visited[0][0] = true;

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Room currentRoom = grid[current.x][current.y];

            // Collect unvisited neighbors
            List<Cell> neighbors = new ArrayList<>();
            if (current.x > 0 && !visited[current.x - 1][current.y] && grid[current.x - 1][current.y] != null)
                neighbors.add(new Cell(current.x - 1, current.y));
            if (current.x < gridSize - 1 && !visited[current.x + 1][current.y] && grid[current.x + 1][current.y] != null)
                neighbors.add(new Cell(current.x + 1, current.y));
            if (current.y > 0 && !visited[current.x][current.y - 1] && grid[current.x][current.y - 1] != null)
                neighbors.add(new Cell(current.x, current.y - 1));
            if (current.y < gridSize - 1 && !visited[current.x][current.y + 1] && grid[current.x][current.y + 1] != null)
                neighbors.add(new Cell(current.x, current.y + 1));

            if (!neighbors.isEmpty()) {
                // Pick a random neighbor to connect
                Cell next = neighbors.get(rng.nextInt(neighbors.size()));
                Room nextRoom = grid[next.x][next.y];

                // Connect current room <-> next room
                if (next.x == current.x - 1) {
                    currentRoom.connect(nextRoom, Room.Cardinal.WEST);
                    nextRoom.connect(currentRoom, Room.Cardinal.EAST);
                } else if (next.x == current.x + 1) {
                    currentRoom.connect(nextRoom, Room.Cardinal.EAST);
                    nextRoom.connect(currentRoom, Room.Cardinal.WEST);
                } else if (next.y == current.y - 1) {
                    currentRoom.connect(nextRoom, Room.Cardinal.NORTH);
                    nextRoom.connect(currentRoom, Room.Cardinal.SOUTH);
                } else if (next.y == current.y + 1) {
                    currentRoom.connect(nextRoom, Room.Cardinal.SOUTH);
                    nextRoom.connect(currentRoom, Room.Cardinal.NORTH);
                }

                visited[next.x][next.y] = true;
                stack.push(next);
            } else {
                stack.pop(); // backtrack
            }
        }

        // ---- Step 2: Add random extra connections for loops ----
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                Room room = grid[x][y];
                if (room == null) continue;

                if (x > 0 && grid[x - 1][y] != null && rng.nextDouble() < 0.2) {
                    room.connect(grid[x - 1][y], Room.Cardinal.WEST);
                    grid[x - 1][y].connect(room, Room.Cardinal.EAST);
                }
                if (y > 0 && grid[x][y - 1] != null && rng.nextDouble() < 0.2) {
                    room.connect(grid[x][y - 1], Room.Cardinal.NORTH);
                    grid[x][y - 1].connect(room, Room.Cardinal.SOUTH);
                }
            }
        }

        return grid;
    }

    private static void generateWilderness(Location location, List<Room> rooms) {
        if (rooms.isEmpty()) return;

        Random rng = Math.getRandom();

        // Map to hold room positions
        Map<Integer, Map<Integer, Room>> grid = new HashMap<>();
        Set<Room> unplaced = new HashSet<>(rooms);
        List<int[]> placedCoords = new ArrayList<>();

        // Step 1: Place the first room at (0,0)
        Room start = rooms.get(0);
        unplaced.remove(start);
        grid.computeIfAbsent(0, k -> new HashMap<>()).put(0, start);
        placedCoords.add(new int[]{0, 0});

        // Track current min/max X for width limitation
        int minX = 0, maxX = 0;

        // Frontier for expansion
        List<int[]> frontier = new ArrayList<>();
        frontier.add(new int[]{0, 0});

        // Step 2: Expand rooms organically
        while (!unplaced.isEmpty() && !frontier.isEmpty()) {
            int[] pos = frontier.get(rng.nextInt(frontier.size()));
            int x = pos[0], y = pos[1];

            // Determine unplaced neighbors
            List<int[]> emptyDirs = new ArrayList<>();
            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];

                // Skip if that neighbor already exists
                if (grid.containsKey(nx) && grid.get(nx).containsKey(ny)) continue;

                // Enforce max width of 6 rooms
                int projectedMinX = java.lang.Math.min(minX, nx);
                int projectedMaxX = java.lang.Math.max(maxX, nx);
                if (projectedMaxX - projectedMinX + 1 > 6) continue;

                emptyDirs.add(new int[]{nx, ny, dir[0], dir[1]});
            }

            if (emptyDirs.isEmpty()) {
                frontier.remove(pos);
                continue;
            }

            // Place a random unplaced room
            int[] dir = emptyDirs.get(rng.nextInt(emptyDirs.size()));
            int nx = dir[0], ny = dir[1];
            Room room = unplaced.iterator().next();
            unplaced.remove(room);
            grid.computeIfAbsent(nx, k -> new HashMap<>()).put(ny, room);
            placedCoords.add(new int[]{nx, ny});
            frontier.add(new int[]{nx, ny});

            // Update min/max X
            minX = java.lang.Math.min(minX, nx);
            maxX = java.lang.Math.max(maxX, nx);

            // Connect new room to the current room
            Room current = grid.get(x).get(y);
            if (dir[2] == 1) { // EAST
                current.connect(room, Room.Cardinal.EAST);
                room.connect(current, Room.Cardinal.WEST);
            } else if (dir[2] == -1) { // WEST
                current.connect(room, Room.Cardinal.WEST);
                room.connect(current, Room.Cardinal.EAST);
            } else if (dir[3] == 1) { // SOUTH
                current.connect(room, Room.Cardinal.SOUTH);
                room.connect(current, Room.Cardinal.NORTH);
            } else if (dir[3] == -1) { // NORTH
                current.connect(room, Room.Cardinal.NORTH);
                room.connect(current, Room.Cardinal.SOUTH);
            }
        }

        // Step 3: Add extra random connections (loops)
        for (int[] coord : placedCoords) {
            int x = coord[0], y = coord[1];
            Room room = grid.get(x).get(y);
            if (room == null) continue;

            for (int[] dir : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
                int nx = x + dir[0], ny = y + dir[1];
                Room neighbor = grid.getOrDefault(nx, Collections.emptyMap()).get(ny);
                if (neighbor == null) continue;

                if (rng.nextDouble() < 0.2) { // 20% chance to add a loop
                    if (dir[0] == 1) { room.connect(neighbor, Room.Cardinal.EAST); neighbor.connect(room, Room.Cardinal.WEST); }
                    else if (dir[0] == -1) { room.connect(neighbor, Room.Cardinal.WEST); neighbor.connect(room, Room.Cardinal.EAST); }
                    else if (dir[1] == 1) { room.connect(neighbor, Room.Cardinal.SOUTH); neighbor.connect(room, Room.Cardinal.NORTH); }
                    else if (dir[1] == -1) { room.connect(neighbor, Room.Cardinal.NORTH); neighbor.connect(room, Room.Cardinal.SOUTH); }
                }
            }
        }
    }


    public static void assignEntranceExit(Location location) {
        List<Room> rooms = location.getRooms();
        if (rooms.isEmpty()) return;

        if (location.isWilderness()) {
            Room entrance = rooms.get(0);
            Room exit = findFarthestRoom(entrance);
            location.setEntrance(entrance);
            location.setExit(exit);
        }
        else if (location.isDungeon()) {
            Room entrance = rooms.get(0);
            location.setEntrance(entrance);
        }
        else if (location.isTown()) {
            Room entrance = rooms.get(0);
            location.setEntrance(entrance);
        }
    }

    private static Room findFarthestRoom(Room start) {
        Map<Room, Integer> distance = new HashMap<>();
        Queue<Room> queue = new LinkedList<>();
        queue.add(start);
        distance.put(start, 0);

        Room farthest = start;
        int maxDist = 0;

        while (!queue.isEmpty()) {
            Room current = queue.poll();
            int d = distance.get(current);

            if (d > maxDist) {
                maxDist = d;
                farthest = current;
            }

            for (Room neighbor : current.getNeighbors()) { // returns N/S/E/W neighbors
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, d + 1);
                    queue.add(neighbor);
                }
            }
        }

        return farthest;
    }

}
