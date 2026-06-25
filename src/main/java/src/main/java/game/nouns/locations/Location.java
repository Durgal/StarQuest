package src.main.java.game.nouns.locations;

import src.main.java.game.data.Data;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.locations.biomes.Biome;
import src.main.java.generators.MarkovChain;

import java.util.*;

import src.main.java.utilities.Enum;


public class Location extends Place implements Comparable<Location> {

    // function of Location
    public enum Function {
        TOWN,
        DUNGEON,
        WILDERNESS
    }

    Continent parent;

    Biome biome;

    List<Room> rooms = new ArrayList<>();

    Room entrance;

    Room exit;

    Lifeform inhabitants;

    public Function function;

    Boolean shelter;

    public Location() { }
    
    public Location(Continent continent) {
        parent = continent;
        inhabitants = parent.parent.getRandomLifeform(parent.parent.lifeforms);
        biome = new Biome(parent.parent);
        name = generateRandomName(parent.parent.type);
        function = Enum.random(Function.class);
    }

    public String getName() {
        String result;
        if (name.length() <= 4){
            result = biome.toString()+" OF "+name;
        }
        else {
            result = name+" "+biome.toString();
        }
        return result;
    }

    private String generateRandomName(Planet.Category type) {
        MarkovChain chain = Data.location_generator.get(type);
        if (chain == null) throw new IllegalArgumentException("Unknown location type: " + type);
        return chain.generateName(8).toUpperCase();
    }

    public Continent getContinent() {
        return parent;
    }

    public static String printLocationDetails(Lifeform lifeform) {
        SolarSystem system = lifeform.getLocation(SolarSystem.class);
        Location location = lifeform.getLocation(Location.class);
        String details = "";
        details += system.getName() + "\n" + location.getName()+"\nTYPE    > "+location.getFunction()+"\nBIOME    > "+location.parent.getPlanet().type;
        return details;
    }
    
//    public static void printAsciiMap(Lifeform lifeform) {
//        Location location = lifeform.getLocation(Location.class);
//        List<Room> rooms = location.getRooms();
//        if (rooms.isEmpty()) {
//            System.out.println("(no rooms)");
//            return;
//        }
//
//        // BFS to assign coordinates
//        Map<Room, int[]> coords = new HashMap<>();
//        Queue<Room> queue = new LinkedList<>();
//        Set<Room> visited = new HashSet<>();
//
//        Room start = rooms.get(0);
//        coords.put(start, new int[]{0, 0});
//        queue.add(start);
//        visited.add(start);
//
//        int minX = 0, minY = 0, maxX = 0, maxY = 0;
//
//        while (!queue.isEmpty()) {
//            Room r = queue.poll();
//            int[] pos = coords.get(r);
//            int x = pos[0], y = pos[1];
//
//            if (r.getNorth() != null && !visited.contains(r.getNorth())) {
//                coords.put(r.getNorth(), new int[]{x, y - 1});
//                queue.add(r.getNorth());
//                visited.add(r.getNorth());
//                minY = Math.min(minY, y - 1);
//            }
//            if (r.getSouth() != null && !visited.contains(r.getSouth())) {
//                coords.put(r.getSouth(), new int[]{x, y + 1});
//                queue.add(r.getSouth());
//                visited.add(r.getSouth());
//                maxY = Math.max(maxY, y + 1);
//            }
//            if (r.getEast() != null && !visited.contains(r.getEast())) {
//                coords.put(r.getEast(), new int[]{x + 1, y});
//                queue.add(r.getEast());
//                visited.add(r.getEast());
//                maxX = Math.max(maxX, x + 1);
//            }
//            if (r.getWest() != null && !visited.contains(r.getWest())) {
//                coords.put(r.getWest(), new int[]{x - 1, y});
//                queue.add(r.getWest());
//                visited.add(r.getWest());
//                minX = Math.min(minX, x - 1);
//            }
//        }
//
//        int width = maxX - minX + 1;
//        int height = maxY - minY + 1;
//
//        int cellWidth = 4; // each room cell
//        int totalCols = Math.max(width * cellWidth, 6 * cellWidth); // enforce minimum width
//        int totalRows = height;
//
//        // Create grid with braille blanks
//        String[][] grid = new String[totalRows][totalCols];
//        for (String[] row : grid) Arrays.fill(row, "⠀"); // braille blank
//
//        // Draw rooms and connections
//        for (Map.Entry<Room, int[]> e : coords.entrySet()) {
//            Room room = e.getKey();
//            int[] pos = e.getValue();
//            int gx = (pos[0] - minX) * cellWidth + cellWidth / 2;
//            int gy = pos[1] - minY;
//
//            boolean isCurrent = lifeform.getLocation() == room;
//            boolean isExplored = room.isExplored();
//            boolean shouldDrawConnections = isCurrent || isExplored;
//
//            // Draw room
//            if (isCurrent) grid[gy][gx] = "■";
//            else if (isExplored) grid[gy][gx] = "▣";
//            else grid[gy][gx] = "□";
//
//            // Draw connections if this room is explored or current
//            if (isCurrent || isExplored) {
//                // East
//                if (room.getEast() != null) {
//                    if (gx + 2 < totalCols) grid[gy][gx + 2] = "•";
//                }
//                // West
//                if (room.getWest() != null) {
//                    if (gx - 2 >= 0) grid[gy][gx - 2] = "•";
//                }
//                // North (vertical, above room)
//                if (room.getNorth() != null) {
//                    int ny = gy - 1;
//                    if (ny >= 0) grid[ny][gx] = "•";
//                }
//                // South (vertical, below room)
//                if (room.getSouth() != null) {
//                    int sy = gy + 1;
//                    if (sy < totalRows) grid[sy][gx] = "•";
//                }
//            }
//        }
//
//        // Build final string
//        StringBuilder sb = new StringBuilder();
//        for (String[] row : grid) {
//            for (String cell : row) sb.append(cell);
//            sb.append("\n");
//        }
//
//        System.out.print(sb.toString());
//    }







    
    public static void printAsciiMap(Lifeform lifeform) {
        Location location = lifeform.getLocation(Location.class);
        List<Room> rooms = location.getRooms();
        if (rooms.isEmpty()) {
            System.out.println("(no rooms)");
            return;
        }

        // Assign coordinates by traversing the graph
        Map<Room, int[]> coords = new HashMap<>();
        Queue<Room> queue = new LinkedList<>();
        Set<Room> visited = new HashSet<>();

        Room start = rooms.get(0);
        coords.put(start, new int[]{0, 0});
        queue.add(start);
        visited.add(start);

        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        while (!queue.isEmpty()) {
            Room r = queue.poll();
            int[] pos = coords.get(r);
            int x = pos[0];
            int y = pos[1];

            if (r.getNorth() != null && !visited.contains(r.getNorth())) {
                coords.put(r.getNorth(), new int[]{x, y - 1});
                queue.add(r.getNorth());
                visited.add(r.getNorth());
                minY = Math.min(minY, y - 1);
            }
            if (r.getSouth() != null && !visited.contains(r.getSouth())) {
                coords.put(r.getSouth(), new int[]{x, y + 1});
                queue.add(r.getSouth());
                visited.add(r.getSouth());
                maxY = Math.max(maxY, y + 1);
            }
            if (r.getEast() != null && !visited.contains(r.getEast())) {
                coords.put(r.getEast(), new int[]{x + 1, y});
                queue.add(r.getEast());
                visited.add(r.getEast());
                maxX = Math.max(maxX, x + 1);
            }
            if (r.getWest() != null && !visited.contains(r.getWest())) {
                coords.put(r.getWest(), new int[]{x - 1, y});
                queue.add(r.getWest());
                visited.add(r.getWest());
                minX = Math.min(minX, x - 1);
            }
        }

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;

        // Create ASCII grid (Unicode blank U+2800)
        String[][] grid = new String[height * 2 + 1][width * 4 + 1];
        for (String[] row : grid) {
            Arrays.fill(row, "⠀"); // Braille blank
        }

        // Draw rooms and connections
        for (Map.Entry<Room, int[]> e : coords.entrySet()) {
            Room room = e.getKey();
            int[] pos = e.getValue();

            int gx = (pos[0] - minX) * 4 + 2;
            int gy = (pos[1] - minY) * 2 + 1;

            boolean isCurrent = lifeform.getLocation() == room;
            boolean isExplored = room.isExplored(); // track in Room class

            // Room markers
            if (isCurrent) {
                grid[gy][gx] = "■";
            } else if (isExplored) {
                grid[gy][gx] = "▣";
            } else {
                grid[gy][gx] = "□";
            }

            // Connection markers (only for explored rooms)
            if (room.getNorth() != null && (room.isExplored() || room.getNorth().isExplored())) grid[gy - 1][gx] = "•";
            if (room.getSouth() != null && (room.isExplored() || room.getSouth().isExplored())) grid[gy + 1][gx] = "•";
            if (room.getWest()  != null && (room.isExplored() || room.getWest().isExplored()))  grid[gy][gx - 2] = "•";
            if (room.getEast()  != null && (room.isExplored() || room.getEast().isExplored()))  grid[gy][gx + 2] = "•";
        }

        // Build final string — trim trailing blanks per line
        StringBuilder sb = new StringBuilder();
        for (String[] row : grid) {
            StringBuilder line = new StringBuilder();
            for (String cell : row) line.append(cell);

            // Trim trailing blank cells (Braille or space)
            String trimmed = line.toString(); //.replaceAll("[⠀\\s]+$", "");
            
            // Remove first character if it exists
            if (trimmed.length() > 0) {
                trimmed = trimmed.substring(1);
            }

            sb.append(trimmed).append("\n");
        }

        System.out.print(sb.toString());
    }


    public boolean isTown() {
        return function == Function.TOWN;
    }

    public boolean isDungeon() {
        return function == Function.DUNGEON;
    }

    public boolean isWilderness() {
        return function == Function.WILDERNESS;
    }

    public Function getFunction() { return function; }

    public List<Room> makeRooms(int n) {
        for (int i = 0; i < n; i++) {
            rooms.add(new Room(this));
        }
        return rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getEntrance() { return entrance; }

    public void setEntrance(Room room) { entrance = room; }

    public Room getExit() { return exit; }

    public void setExit(Room room) { exit = room; }

    public Biome getBiome() {
        return biome;
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

}
