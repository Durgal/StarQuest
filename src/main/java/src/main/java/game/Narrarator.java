package src.main.java.game;

import src.main.java.game.data.Data;
import src.main.java.game.nouns.lifeforms.Player;
import src.main.java.game.nouns.locations.*;
import src.main.java.text_based_mechanics.parts_of_speech.Adjective;
import src.main.java.game.nouns.locations.biomes.Biome.*;
import src.main.java.utilities.Math;
import src.main.java.utilities.Enum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.main.java.game.nouns.lifeforms.Lifeform;
import src.main.java.game.nouns.things.Thing;
import src.main.java.text_based_mechanics.parts_of_speech.Noun;


public class Narrarator {

    public static void introduction(Universe universe) {
        List<Planet> planets = universe.system.getPlanets();
        System.out.println("Solar System Summary");
        System.out.println("SEED = "+Data.SEED);
        System.out.println("YOUR SCANNERS INDICATE "+planets.size()+" PLANETS IN THIS SOLAR SYSTEM.");

        for (Planet planet : planets) {
            System.out.println("PLANET - - - - - - " + planet.getName());
            System.out.println(" BIOME   . . . . . " + planet.getCategory().toString().toUpperCase());
            System.out.println(" CLIMATE . . . . . " + planet.getClimate());
            System.out.println(" LIFEFORM  . . . . " + planet.getNativeSpecies());
            System.out.println(" CONTINENTS  . . . " + planet.getContinentNames());
        }
    }

    public static void story(Player player) {

        String story = "";
        Place place = player.getLocation();

        if (SolarSystem.class.isInstance(place)) {
            SolarSystem system = (SolarSystem) place;
            story = "YOU FIND YOURSELF IN THE "+system.getName()+" SOLAR SYSTEM. "+system.getPlanets().size()+" PLANETS ORBIT THE SUN.";
        }

        if (Planet.class.isInstance(place)) {
            Planet planet = (Planet) place;
            story = "YOU FIND YOURSELF ORBITING A "+planet.getCategory()+" PLANET: "+planet.getName()+". THERE ARE "+planet.getContinents().size()+" CONTINENTS TO LAND ON.";
        }

        if (Continent.class.isInstance(place)) {
            Continent continent = (Continent) place;
            Planet planet = continent.getPlanet();
            List<String> responses = Arrays.asList(
                    "YOU PLUMMET TOWARD THE VAST CONTINENT OF "+continent+" ON "+planet+", THE WINDS SCREAMING AROUND YOUR SHIP AS THE GROUND RUSHES TO MEET YOU.",
                    "THE SHIP TREMBLES AS YOU PLUNGE TOWARD THE IMMENSE LANDMASS OF "+continent+" OF "+planet+", CLOUDS WHIPPING PAST LIKE GHOSTS.",
                    "YOU PLUNGE THROUGH THE ATMOSPHERE OF "+planet+", HEADING STRAIGHT FOR THE REGION OF "+continent+", FLAMES LICKING THE HULL.",
                    "THE SKY OPENS UP AS YOU FALL TOWARD THE REGION OF "+continent+" ON "+planet+", THE LAND BELOW RISING TO MEET YOU.",
                    "YOU DIVE THROUGH TURBULENT SKIES ABOVE THE VAST EXPANSE OF "+continent+" OF "+planet+", THE WORLD BELOW SPRAWLING AND UNKNOWN.",
                    "WITH A ROAR, YOU PLUNGE TOWARD "+continent+" OF "+planet+", THE WIND HOWLING LIKE A HUNGRY BEAST.",
                    "THE SHIP SHUDDERS AS YOU PLUMMET THROUGH THE ATMOSPHERE TOWARD THE VAST LAND OF "+continent+" ON "+planet+", HEAT AND WIND PRESSING AGAINST YOU.",
                    "YOU SLAM THROUGH CLOUDS, DESCENDING RAPIDLY TOWARD THE TERRITORY OF "+continent+" OF "+planet+", THE SURFACE GROWING LARGER WITH EVERY SECOND.",
                    "THE STARS BLUR BEHIND YOU AS YOU FALL TOWARD "+continent+" ON "+planet+", THE LAND LOOMING LIKE AN UNCHARTED DUNGEON.",
                    "YOU PLUNGE THROUGH THE ATMOSPHERE OF "+planet+", HEADED FOR THE SPRAWLING LANDMASS OF "+continent+", THE PLANET BENEATH YOU SEEMING TO BREATHE AND SHIFT.");
            story = Math.random(responses);
        }

        if (Location.class.isInstance(place)) {
            Location location = (Location) place;
            List<String> responses = new ArrayList<>();

            String name = location.getName();
            Adjective adjective = Math.random(location.getBiome().getAdjectives());
            String biome = String.valueOf(location.getContinent().getPlanet().getCategory());

            Temperature temperature = location.getBiome().getTemperature();
            Lux lux = location.getBiome().getLux();
            Weather weather = location.getBiome().getWeather();
            Humidity humidity = location.getBiome().getHumidity();
            Flora flora = location.getBiome().getFlora();
            Fauna fauna = location.getBiome().getFauna();

            java.lang.Enum<?> descriptive = Enum.choose(temperature, lux, weather, humidity, flora, fauna);

            switch(location.getFunction()) {
                case TOWN:
                    responses = Arrays.asList(
                            "YOU ARRIVE AT THE "+name+", A "+adjective+" TOWN IN THE "+biome+" LANDSCAPE.",
                            "THE "+name+", AN "+adjective+" "+biome+" TOWN, LIES BEFORE YOU.",
                            "A "+adjective+" TOWN IN THE "+biome+" WILDERNESS, KNOWN AS THE "+name+", STANDS HERE.",
                            "THE "+adjective+" TOWN OF THE "+biome+" WILDERNESS, THE "+name+", SPREADS BEFORE YOU.",
                            "YOU SEE THE "+name+", A "+adjective+" "+biome+" TOWN.",
                            "BEFORE YOU IS THE "+name+", A "+adjective+" TOWN IN THE "+biome+" WILDERNESS.",
                            "THE "+adjective+" TOWN OF THE "+biome+", THE "+name+", RISES BEFORE YOU."
                    );
                    break;

                case DUNGEON:
                    responses = Arrays.asList(
                            "YOU STAND BEFORE THE "+name+", A "+adjective+" DUNGEON RISING UP FROM THE "+biome+" LANDSCAPE.",
                            "BEFORE YOU LIES THE "+name+", A "+adjective+" DUNGEON IN THE "+biome+" EXPANSE.",
                            "THE "+adjective+" DUNGEON OF THE "+biome+", KNOWN AS THE "+name+", AWAITS.",
                            "YOU LOOK AT THE "+name+", A "+adjective+" DUNGEON IN THE "+biome+" EXPANSE.",
                            "THE "+adjective+" "+biome+" DUNGEON, THE "+name+", TOWERS BEFORE YOU.",
                            "A "+adjective+" DUNGEON IN THE "+biome+" WILDERNESS, THE "+name+", LOOMS BEFORE YOU.",
                            "YOU SEE A "+adjective+" DUNGEON OF THE "+biome+" WILDS, NESTLED IN THE THE "+name+".",
                            "THE "+name+", A "+adjective+" DUNGEON BURIED DEEP BENEATH THE "+biome+" WILDERNESS."
                    );
                    break;

                case WILDERNESS:
                    responses = Arrays.asList(
                            "YOU STEP INTO THE "+name+", A "+adjective+" WILDERNESS IN THE "+biome+" LANDSCAPE.",
                            "THE "+adjective+" "+biome+" WILDERNESS, THE "+name+", SPREADS BEFORE YOU.",
                            "A "+adjective+" "+biome+" WILDERNESS, KNOWN AS THE "+name+", LIES AHEAD.",
                            "BEFORE YOU IS THE "+name+", A "+adjective+" "+biome+" WILDERNESS.",
                            "THE "+adjective+" "+biome+" WILDERNESS, THE "+name+", STRETCHES OUT.",
                            "YOU STAND AT THE "+name+", A "+adjective+" "+biome+" WILDERNESS.",
                            "THE "+name+", A "+adjective+" "+biome+" WILDERNESS, UNFOLDS BEFORE YOU.",
                            "THE "+adjective+" "+biome+" WILDERNESS, KNOWN AS THE "+name+", STRETCHES BEFORE YOU."
                    );
                    break;
            }

            story = Math.random(responses)+" "+describeLocation(descriptive);
        }

        if (Room.class.isInstance(place)) {
            story  = describe ((Room)place,player) + "\n";
            story += (((Room)place).getNorth() != null) ? "NORTH > ?\n" : "";
            story += (((Room)place).getSouth() != null) ? "SOUTH > ?\n" : "";
            story += (((Room)place).getEast() != null) ? "EAST > ?\n" : "";
            story += (((Room)place).getWest() != null) ? "WEST > ?\n" : "";
            
            //story += "YOU SEE A "+Math.random(((Room)place).getContent());
        }

        System.out.println(story);

//                    // TODO: the WEATHER shines/rains/snows etc
//                    // TODO: you hear/see/observe some ACTION! or some INTERESTING feature! or some INTERESTING <functional> room (shop, ruins, castle, etc)!

    }

    private static String describeLocation(java.lang.Enum<?> type) {
        List<String> responses = new ArrayList<>();

        if (type instanceof Temperature) {
            switch ((Temperature) type) {
                case SUBZERO:
                    responses = Arrays.asList(
                            "THE SUBZERO TEMPERATURES MAKE YOU SHIVER.",
                            "ICE CRYSTALS FORM ON EVERYTHING AROUND YOU.",
                            "THE AIR BURNS YOUR LUNGS WITH FREEZING COLD.",
                            "YOUR BREATH FREEZES IN THE AIR AS YOU MOVE.",
                            "THE FROST BITES THROUGH YOUR CLOTHING."
                    );
                    break;
                case ZERO:
                    responses = Arrays.asList(
                            "THE AIR IS BITTERLY COLD, CUTTING THROUGH YOUR CLOTHING.",
                            "A CHILL RUNS DOWN YOUR SPINE AS YOU MOVE.",
                            "FROST LINGERS ON THE SURFACE OF EVERYTHING AROUND YOU.",
                            "EVERY STEP LEAVES A CRUNCH IN THE FROST.",
                            "THE COLD MAKES YOUR MOVEMENTS SLIGHTLY HESITANT."
                    );
                    break;
                case LOW:
                    responses = Arrays.asList(
                            "THE COOL AIR MAKES YOU BREATHLESS WITH EACH STEP.",
                            "A MILD CHILL HANGS OVER THE LANDSCAPE.",
                            "YOU SHIVER AS THE WIND PASSES THROUGH THE AREA.",
                            "A SLIGHT BREEZE SENDS SHIVERS DOWN YOUR SPINE.",
                            "THE TEMPERATURE IS COOL BUT BEARABLE."
                    );
                    break;
                case NORMAL:
                    responses = Arrays.asList(
                            "THE TEMPERATURE FEELS COMFORTABLE, NEITHER TOO HOT NOR TOO COLD.",
                            "A BALANCED AIR TEMPERATURE MAKES TRAVEL PLEASANT.",
                            "THE AIR AROUND YOU FEELS MODERATE AND TEMPERATE.",
                            "THE ENVIRONMENT FEELS IDEAL FOR EXPLORATION.",
                            "THE TEMPERATURE IS PLEASANT, ALMOST PERFECT."
                    );
                    break;
                case HIGH:
                    responses = Arrays.asList(
                            "THE HEAT MAKES YOU SWEAT AS YOU MOVE.",
                            "THE WARM AIR PRESSURES AGAINST YOUR SKIN.",
                            "YOU FEEL THE SUN BEATING DOWN, WARMING THE LANDSCAPE.",
                            "EVERY BREATH FEELS WARM AND THICK.",
                            "THE HEAT MAKES THE LANDSCAPE SHIMMER."
                    );
                    break;
                case VERYHIGH:
                    responses = Arrays.asList(
                            "THE AIR SCORCHES YOUR SKIN AND MAKES BREATHING DIFFICULT.",
                            "EXTREME HEAT WAVES RISE FROM THE GROUND.",
                            "THE TEMPERATURE IS OPPRESSIVE, MAKING EVERY STEP HARDER.",
                            "EVERY SURFACE SEEMS TO RADIATE INTENSE HEAT.",
                            "THE SUN BEATS RELENTLESSLY DOWN ON YOU."
                    );
                    break;
                case SCORCHING:
                    responses = Arrays.asList(
                            "THE SUN BLAZES RELENTLESSLY, BURNING EVERYTHING IN SIGHT.",
                            "EVERY BREATH OF AIR FEELS LIKE FIRE IN YOUR LUNGS.",
                            "THE LANDSCAPE SIZZLES UNDER SCORCHING HEAT.",
                            "THE AIR WAVES WITH INTENSE HEAT, MAKING DISTANCES WARP.",
                            "THE HEAT IS ALMOST UNBEARABLE, EACH STEP PAINFUL."
                    );
                    break;
            }
        } else if (type instanceof Lux) {
            switch ((Lux) type) {
                case DARK:
                    responses = Arrays.asList(
                            "THE DARKNESS MAKES SHADOWS SEEM TO MOVE ON THEIR OWN.",
                            "EVERY STEP IS CLOUDED IN SHADOW AND UNCERTAINTY.",
                            "THE NIGHT SURROUNDS YOU, OBSCURING THE LANDSCAPE.",
                            "DIM SHAPES LOOM IN THE PERIPHERAL VISION.",
                            "THE WORLD FEELS MYSTERIOUS AND HIDDEN IN SHADOWS."
                    );
                    break;
                case TWILIGHT:
                    responses = Arrays.asList(
                            "THE TWILIGHT CASTS LONG SHADOWS OVER THE LANDSCAPE.",
                            "DIM LIGHT CREATES AN AIR OF MYSTERY AROUND YOU.",
                            "THE SUN SINKS, GIVING THE WORLD AN ORANGE HUE.",
                            "EVERYTHING IS TINTED WITH A SOFT, MELANCHOLIC GLOW.",
                            "THE LIGHT LEVEL MAKES SHADOWS ELONGATE AND SHIFT."
                    );
                    break;
                case NORMAL:
                    responses = Arrays.asList(
                            "THE LIGHT LEVEL IS NORMAL, MAKING EVERYTHING CLEARLY VISIBLE.",
                            "SUNLIGHT ILLUMINATES THE AREA COMFORTABLY.",
                            "THE LANDSCAPE IS EASY TO NAVIGATE IN THIS LIGHT.",
                            "EVERY DETAIL IS CLEAR AND DISTINCT.",
                            "THE AREA IS WELL-LIT AND EASY TO EXAMINE."
                    );
                    break;
                case BRIGHT:
                    responses = Arrays.asList(
                            "THE BRIGHT LIGHT MAKES THE COLORS OF THE LAND VIVID AND SHARP.",
                            "EVERY DETAIL IS ACCENTUATED UNDER THE INTENSE LIGHT.",
                            "SUNLIGHT BLINDS YOU MOMENTARILY AS IT REFLECTS OFF THE SURFACE.",
                            "THE LANDSCAPE SHINES WITH BRILLIANT INTENSITY.",
                            "THE BRIGHT LIGHT MAKES EVERYTHING SPARKLE."
                    );
                    break;
            }
        } else if (type instanceof Weather) {
            switch ((Weather) type) {
                case NONE:
                    responses = Arrays.asList(
                            "THE SKIES ARE CLEAR AND UNCHALLENGING.",
                            "NOTHING DISTURBS THE CALM OF THE ENVIRONMENT.",
                            "THE AIR FEELS STILL AND QUIET.",
                            "THE LANDSCAPE IS PEACEFUL WITHOUT WEATHER INTERFERENCE.",
                            "THE ENVIRONMENT FEELS TRANQUIL AND UNCHALLENGED."
                    );
                    break;
                case CALM:
                    responses = Arrays.asList(
                            "A GENTLE BREEZE RUSTLES THROUGH THE LANDSCAPE.",
                            "THE WIND IS LIGHT, HARDLY DISTURBING YOUR PATH.",
                            "A CALM AIR HOVERS OVER EVERYTHING.",
                            "A SOFT WIND TOUCHES YOUR FACE.",
                            "THE AIR MOVES GENTLY, CREATING A QUIET ATMOSPHERE."
                    );
                    break;
                case SUN:
                    responses = Arrays.asList(
                            "SUNLIGHT WARMS THE LANDSCAPE BEAUTIFULLY.",
                            "THE SKY IS CLEAR, THE SUN BEATING DOWN GENTLY.",
                            "EVERYTHING IS BATHED IN WARM SUNLIGHT.",
                            "SUN RAYS LIGHT UP THE ENVIRONMENT, GIVING IT LIFE.",
                            "THE LANDSCAPE GLOWS UNDER THE SUNLIGHT."
                    );
                    break;
                case OVERCAST:
                    responses = Arrays.asList(
                            "GRAY CLOUDS HANG LOW, DIMMING THE LIGHT.",
                            "THE SKIES ARE OVERCAST, CASTING A SOMBER MOOD.",
                            "A HEAVY LAYER OF CLOUDS COVERS THE AREA.",
                            "THE CLOUDS MAKE EVERYTHING FEEL MUTED AND QUIET.",
                            "THE SKY IS GRAY, CASTING A DULL SHADOW OVER THE LAND."
                    );
                    break;
                case WIND:
                    responses = Arrays.asList(
                            "STRONG WINDS WHIP THROUGH THE LANDSCAPE.",
                            "GUSTS OF WIND PUSH AGAINST YOU.",
                            "THE AIR MOVES VIOLENTLY, WHISTLING AROUND YOU.",
                            "THE WIND HOWLS THROUGH THE TREES AND ROCKS.",
                            "EVERY STEP IS CHALLENGED BY FORCEFUL WIND."
                    );
                    break;
                case RAIN:
                    responses = Arrays.asList(
                            "RAIN PATTERS STEADILY AROUND YOU.",
                            "THE SOUND OF WATER HITTING THE GROUND FILLS THE AIR.",
                            "DROPLETS DRIP FROM THE TREES AND ROCKS AS THE SKY WEIGHS HEAVY.",
                            "THE RAIN CREATES A CONSTANT PATTERN OF SOUND.",
                            "EACH STEP SPLASHES IN PUDDLES FORMED BY THE RAIN."
                    );
                    break;
                case FOG:
                    responses = Arrays.asList(
                            "THICK FOG MAKES IT HARD TO SEE BEYOND A FEW METERS.",
                            "MIST HANGS LOW, SHROUDING THE LANDSCAPE.",
                            "A WHITE HAZE BLURS THE EDGES OF EVERYTHING AROUND YOU.",
                            "VISIBILITY IS LIMITED BY THE DENSE FOG.",
                            "THE FOG MAKES THE LANDSCAPE FEEL MYSTERIOUS AND HIDDEN."
                    );
                    break;
                case SMOG:
                    responses = Arrays.asList(
                            "THICK SMOG HANGS HEAVY IN THE AIR, MAKING BREATHING HARD.",
                            "THE LANDSCAPE IS OBSCURED BY A YELLOW-BROWN HAZE.",
                            "AIR POLLUTION CREATES A HEAVY, OPPRESSIVE ATMOSPHERE.",
                            "THE SMOG MAKES EVERYTHING LOOK GRIMY AND DISTANT.",
                            "BREATHING IS DIFFICULT AS THE SMOG ENVELOPES THE AREA."
                    );
                    break;
                case SNOW:
                    responses = Arrays.asList(
                            "SNOW FLUTTERS DOWN, COVERING EVERYTHING IN WHITE.",
                            "THE WORLD BENEATH YOU IS SILENT, BLANKETED IN FRESH SNOW.",
                            "COLD FLURRIES DANCE IN THE AIR, MAKING EACH BREATH VISIBLE.",
                            "THE SNOW CREATES A SOFT, QUIET LANDSCAPE.",
                            "EVERY SURFACE IS DRESSED IN WHITE AS SNOW FALLS."
                    );
                    break;
                case SANDSTORM:
                    responses = Arrays.asList(
                            "A RAGING SANDSTORM SWALLOWS THE LANDSCAPE, SCOURING YOUR SKIN AND BLINDING YOUR PATH.",
                            "WHIRLING DUNES OF SAND TEAR THROUGH THE AIR, MAKING EACH BREATH A STRUGGLE AS A SANDSTORM RAGES.",
                            "A SANDSTORM BURIES EVERYTHING IN SHIFTING SAND, LEAVING YOU DISORIENTED AND LOST.",
                            "GUSTS CARRY SHARP GRAINS THAT STING YOUR EYES AND REND YOUR FLESH RAW AS A SANDSTORM SURROUNDS YOU.",
                            "A SANDSTORM SCREAMS ACROSS THE DESERT, TURNING EVERY STEP INTO A FIGHT FOR SURVIVAL."
                    );
                    break;

                case FIRESTORM:
                    responses = Arrays.asList(
                            "A RAGING FIRESTORM DEVOURS THE LANDSCAPE, FLAMES HUNGRY FOR EVERYTHING IN SIGHT.",
                            "ASH AND CINDERS RAIN FROM ABOVE AS SMOKE CHOKES YOUR LUNGS AND A FIRESTORM RAGES.",
                            "INFERNOS FROM A FIRESTORM SURROUND YOU, HEAT WAVES MAKING THE AIR ITSELF FEEL LIKE FIRE.",
                            "THE ROAR OF FLAMES DROWNS OUT ALL ELSE, EVERY STEP A DANCE WITH DEATH AS A FIRESTORM SURROUNDS YOU.",
                            "THE FIRESTORM ADVANCES MERCILESSLY, CONSUMING ANYTHING AND EVERYTHING IN ITS PATH."
                    );
                    break;

                case HURRICANE:
                    responses = Arrays.asList(
                            "A HURRICANE’S VIOLENT WINDS RIP TREES FROM THEIR ROOTS AND HURL DEBRIS LIKE MISSILES.",
                            "TORRENTIAL RAINS BLIND YOUR VISION AS WIND THREATENS TO THROW YOU OFF YOUR FEET AS A HURRICANE RAGES.",
                            "THE HURRICANE BATTERS THE LANDSCAPE RELENTLESSLY, TURNING SAFE PATHS INTO DEATH TRAPS.",
                            "ROARING WIND AND WATER COMBINE IN A HURRICANE TO TEAR EVERYTHING ASUNDER.",
                            "EACH STEP FEELS FUTILE AS THE HURRICANE RAGES, PROMISING TO SWEEP YOU INTO OBLIVION."
                    );
                    break;

                case BLIZZARD:
                    responses = Arrays.asList(
                            "A FEROCIOUS BLIZZARD BLANKETS THE WORLD IN WHITE, MAKING IT IMPOSSIBLE TO SEE OR BREATHE.",
                            "SNOW AND ICE HAMMER YOU WITH MERCILESS FORCE, FREEZING YOUR LIMBS SOLID.",
                            "THE WINDS OF A BLIZZARD HOWL LIKE A BEAST, TEARING AWAY ALL WARMTH AND HOPE OF SHELTER.",
                            "VISIBILITY IS ERASED ENTIRELY, THE WORLD REDUCED TO THE CHAOTIC WHITENESS OF THE BLIZZARD.",
                            "THE BLIZZARD RAGES, PROMISING DEATH TO ANY WHO LINGER WITHOUT SHELTER."
                    );
                    break;
            }
        } else if (type instanceof Humidity) {
            switch ((Humidity) type) {
                case ZERO:
                    responses = Arrays.asList(
                            "THE AIR IS DRY AND CRISP.",
                            "THE ENVIRONMENT IS EXTREMELY ARID."
                    );
                    break;
                case LOW:
                    responses = Arrays.asList(
                            "THE HUMIDITY IS LOW, LEAVING THE AIR COOL AND COMFORTABLE.",
                            "A SLIGHT MOISTURE HANGS IN THE AIR, HARDLY NOTICEABLE.",
                            "THE AIR FEELS DRY BUT PLEASANT."
                    );
                    break;
                case MEDIUM:
                    responses = Arrays.asList(
                            "A MODERATE HUMIDITY MAKES THE AIR FEEL FULL AND RICH.",
                            "THE ENVIRONMENT HAS NOTICEABLE MOISTURE, CLINGING SLIGHTLY TO SKIN.",
                            "THE AIR FEELS NEITHER DRY NOR HEAVY, JUST BALANCED."
                    );
                    break;
                case HIGH:
                    responses = Arrays.asList(
                            "THE AIR IS HEAVY AND HUMID, CLINGING TO YOUR SKIN.",
                            "EVERY BREATH FEELS MOIST AND THICK.",
                            "HIGH HUMIDITY MAKES MOVEMENT MORE LABORED AND THE AIR HEAVY."
                    );
                    break;
            }
        } else if (type instanceof Flora) {
            switch ((Flora) type) {
                case NONE:
                    responses = Arrays.asList(
                            "THE LANDSCAPE IS BARE, WITH NO SIGNS OF PLANT LIFE.",
                            "NOTHING BUT ROCKS AND DUST SURROUND YOU.",
                            "THE ENVIRONMENT FEELS BARREN AND EMPTY."
                    );
                    break;
                case LOW:
                    responses = Arrays.asList(
                            "SPARSE PLANT LIFE DOTS THE TERRAIN.",
                            "A FEW SCRUBS AND BRUSHES SURVIVE HERE.",
                            "THE LAND IS SCATTERED WITH SMALL PLANTS."
                    );
                    break;
                case MEDIUM:
                    responses = Arrays.asList(
                            "VEGETATION IS NOTICEABLE, WITH TREES AND BUSHES SCATTERED THROUGHOUT.",
                            "THE LAND IS RICH WITH PLANT LIFE, BUT NOT DENSE.",
                            "GRASSES, SHRUBS, AND TREES MAKE UP A MODERATE FLORA PRESENCE."
                    );
                    break;
                case HIGH:
                    responses = Arrays.asList(
                            "THE AREA IS LUSH WITH PLANT LIFE, TREES AND BUSHES ABOUND.",
                            "VEGETATION COVERS MUCH OF THE LANDSCAPE.",
                            "EVERYWHERE YOU LOOK, PLANTS FLOURISH AND GROW ABUNDANTLY."
                    );
                    break;
            }
        } else if (type instanceof Fauna) {
            switch ((Fauna) type) {
                case NONE:
                    responses = Arrays.asList(
                            "THERE ARE NO SIGNS OF ANIMAL LIFE HERE.",
                            "THE ENVIRONMENT IS SILENT, DEVOID OF CREATURES.",
                            "YOU SEE NOTHING BUT EMPTY LANDSCAPE."
                    );
                    break;
                case LOW:
                    responses = Arrays.asList(
                            "A FEW CREATURES MOVE CAUTIOUSLY THROUGH THE AREA.",
                            "YOU HEAR OCCASIONAL SOUNDS OF ANIMAL LIFE.",
                            "WILDLIFE IS SPARSE, BUT PRESENT."
                    );
                    break;
                case MEDIUM:
                    responses = Arrays.asList(
                            "ANIMALS ARE NOTICEABLE THROUGHOUT THE AREA.",
                            "THE LAND IS ALIVE WITH MOVEMENT OF CREATURES HERE AND THERE.",
                            "FAUNA IS COMMON, WITH CREATURES OF VARIOUS KINDS PRESENT."
                    );
                    break;
                case HIGH:
                    responses = Arrays.asList(
                            "THE AREA IS TEEMING WITH ANIMAL LIFE.",
                            "CREATURES OF ALL KINDS ROAM AND FLY THROUGH THE LANDSCAPE.",
                            "THE LANDSCAPE FEELS WILD AND ALIVE WITH FAUNA."
                    );
                    break;
            }
        }

        // Return a random sentence
        return responses.get(Math.getRandom().nextInt(responses.size()));
    }
    
    // --- Public entry ---
    public static String describe(Room room, Player player) {
        StringBuilder sb = new StringBuilder();

        // 1. Room
        sb.append(describeRoom(room)).append(" ");

        // 2. Objects
        sb.append(describeObjects(room.getThings())).append(" ");

        // 3. Lifeforms
        sb.append(describeLifeforms(room.getLifeforms())).append(" ");

        // 4. Player
//        sb.append(describePlayer(player)); // TODO

        return sb.toString().replaceAll("\\s+", " ").trim();
    }

    // --- Room ---
    private static String describeRoom(Room room) {
        String template = pick(roomTemplates);
        Lux light = room.getLocation().getBiome().getLux();
        return String.format(template, room.getSize(), describeRoomLight(light));
    }

    // --- Objects ---
    private static String describeObjects(List<Thing> objects) {
        if (objects.isEmpty()) return "";
        List<String> lines = new ArrayList<>();
        for (Thing obj : objects) {
            String phrase = buildObjectPhrase(obj);
            String template = pick(objectTemplates);
            lines.add(String.format(template, phrase, "", obj.getState() != null ? describeObject(obj.getState(), obj.toString()) : ""));
        }
        return String.join(" ", lines);
    }
    
    // Light
    private static String describeRoomLight(Lux light) {
        List<String> options = switch (light) {
            case DARK -> List.of(
                "PLUNGED INTO DARKNESS, WHERE ONLY VAGUE SHAPES CAN BE MADE OUT",
                "SWALLOWED BY SHADOW, BARELY LIT BY FAINT GLIMMERS",
                "SHROUDED IN DARKNESS, THE AIR THICK AND UNCERTAIN",
                "PITCH BLACK EXCEPT FOR A DIM FLICKER SOMEWHERE AHEAD"
            );
            case TWILIGHT -> List.of(
                "BATHED IN A DIM, UNCERTAIN GLOW",
                "WRAPPED IN TWILIGHT HUES, WHERE SHADOWS DANCE ON THE WALLS",
                "LIT BY FAINT LIGHT, NEITHER DARK NOR BRIGHT",
                "GLOOMY, WHERE YOUR EYES STRAIN TO MAKE SENSE OF THINGS"
            );
            case NORMAL -> List.of(
                "LIT EVENLY, THE VISIBILITY COMFORTABLE AND CLEAR",
                "FILLED WITH STEADY LIGHT, NEITHER HARSH NOR DIM",
                "MODERATELY LIT, GIVING THE PLACE A NATURAL FEEL",
                "BALANCED IN BRIGHTNESS - YOU CAN SEE WELL ENOUGH"
            );
            case BRIGHT -> List.of(
                "AWASH WITH LIGHT, EVERY SURFACE GLARINGLY VISIBLE",
                "BRILLIANTLY ILLUMINATED, LEAVING NOWHERE TO HIDE",
                "BRIGHT ENOUGH TO MAKE YOU SQUINT",
                "RADIANT AND OPEN, SHADOWS BANISHED COMPLETELY"
            );
        };

        return pick(options);
    }
    
    public static String describeObject(Thing.State state, String itemName) {
        itemName = itemName.toUpperCase();

        List<String> options = switch (state) {
            // --- DEFAULT ---
            case DEFAULT -> List.of(
                "NOTHING SEEMS UNUSUAL ABOUT THE " + itemName,
                "THE " + itemName + " APPEARS ORDINARY"
            );

            // --- STRUCTURAL INTEGRITY ---
            case CRACKED -> List.of(
                "THE SURFACE OF THE " + itemName + " IS CRACKED",
                "HAIRLINE FRACTURES RUN ALONG THE " + itemName
            );
            case DENTED -> List.of(
                "THE " + itemName + " IS DENTED FROM HEAVY BLOWS",
                "DEEP IMPRESSIONS MAR THE SURFACE OF THE " + itemName
            );
            case BROKEN -> List.of(
                "THE " + itemName + " IS BROKEN AND USELESS",
                "THE " + itemName + " LIES IN PIECES"
            );
            case SHATTERED -> List.of(
                "THE " + itemName + " IS SHATTERED BEYOND REPAIR",
                "SHARDS OF THE " + itemName + " ARE SCATTERED ABOUT"
            );
            case RUSTED -> List.of(
                "RUST EATS AWAY AT THE " + itemName,
                "THE " + itemName + " IS COATED IN ORANGE RUST"
            );
            case SPLINTERED -> List.of(
                "THE " + itemName + " IS SPLINTERED AND ROUGH TO THE TOUCH",
                "WOODEN FRAGMENTS JUT FROM THE " + itemName
            );

            // --- POSITION / ORIENTATION ---
            case UPRIGHT -> List.of(
                "THE " + itemName + " STANDS UPRIGHT",
                "THE " + itemName + " RESTS NEATLY IN PLACE"
            );
            case ON_ITS_SIDE -> List.of(
                "THE " + itemName + " LIES ON ITS SIDE",
                "THE " + itemName + " HAS BEEN KNOCKED OVER"
            );
            case UPSIDE_DOWN -> List.of(
                "THE " + itemName + " IS UPSIDE DOWN",
                "THE " + itemName + " RESTS INVERTED ON THE GROUND"
            );
            case TILTED -> List.of(
                "THE " + itemName + " LEANS AT AN ODD ANGLE",
                "THE " + itemName + " IS SLANTED, UNSTABLE"
            );
            case LEANING -> List.of(
                "THE " + itemName + " LEANS AGAINST THE WALL",
                "THE " + itemName + " IS PROPPED UP CARELESSLY"
            );
            case HALF_BURIED -> List.of(
                "THE " + itemName + " IS HALF-BURIED IN DIRT",
                "ONLY PART OF THE " + itemName + " IS VISIBLE ABOVE THE GROUND"
            );

            // --- SURFACE / CONDITION ---
            case CLEAN -> List.of(
                "THE " + itemName + " IS UNUSUALLY CLEAN",
                "NOT A SPECK OF DUST MARKS THE " + itemName
            );
            case DIRTY -> List.of(
                "THE " + itemName + " IS COVERED IN GRIME",
                "FILTH COATS THE " + itemName
            );
            case DUSTY -> List.of(
                "A THIN LAYER OF DUST COVERS THE " + itemName,
                "THE " + itemName + " IS DUSTY AND LONG-NEGLECTED"
            );
            case BLOODIED -> List.of(
                "THE " + itemName + " IS STAINED WITH DRIED BLOOD",
                "BLOOD SMEARS THE SURFACE OF THE " + itemName
            );
            case STAINED -> List.of(
                "THE " + itemName + " IS STAINED AND DISCOLORED",
                "DARK SPOTS MAR THE " + itemName
            );
            case MUDDY -> List.of(
                "THE " + itemName + " IS SPLATTERED WITH MUD",
                "DRIED EARTH CLINGS TO THE " + itemName
            );
            case COVERED_IN_WEBS -> List.of(
                "THIN COBWEBS DRAPE THE " + itemName,
                "SPIDERS HAVE CLAIMED THE " + itemName + " AS THEIR OWN"
            );
            case COVERED_IN_MOSS -> List.of(
                "THE " + itemName + " IS CARPETED WITH MOSS",
                "GREEN MOSS CREEPS ALONG THE SURFACE OF THE " + itemName
            );

            // --- ACTIVITY / FUNCTIONALITY ---
            case ACTIVE -> List.of(
                "THE " + itemName + " HUMS WITH ENERGY",
                "THE " + itemName + " IS ACTIVE AND VIBRANT"
            );
            case INACTIVE -> List.of(
                "THE " + itemName + " IS INACTIVE AND STILL",
                "NO ENERGY FLOWS THROUGH THE " + itemName
            );
            case FLICKERING -> List.of(
                "THE " + itemName + " FLICKERS WEAKLY",
                "LIGHT FROM THE " + itemName + " FALTERS IN AND OUT"
            );
            case SMOLDERING -> List.of(
                "SMOKE RISES FROM THE " + itemName,
                "THE " + itemName + " SMOLDERS QUIETLY, HEAT STILL LINGERING"
            );
            case GLOWING -> List.of(
                "THE " + itemName + " EMITS A FAINT GLOW",
                "A SOFT LIGHT RADIATES FROM THE " + itemName
            );

            // --- MAGICAL / SPECIAL ---
            case ENCHANTED -> List.of(
                "THE " + itemName + " SHIMMERS WITH MYSTICAL LIGHT",
                "AN AURA OF POWER SURROUNDS THE " + itemName
            );
            case CURSED -> List.of(
                "THE " + itemName + " EXUDES A MALEVOLENT ENERGY",
                "A DARK PRESENCE CLINGS TO THE " + itemName
            );
            case CORRUPTED -> List.of(
                "THE " + itemName + " SEEMS TWISTED BY SOME FOUL FORCE",
                "THE ESSENCE OF THE " + itemName + " FEELS WRONG"
            );

            default -> List.of("NOTHING SEEMS UNUSUAL ABOUT THE " + itemName);
        };

        return pick(options);
    }

    private static String buildObjectPhrase(Noun obj) {
        String phrase = "";
        //if (obj.getAdjective() != null) phrase += obj.getAdjective() + " "; // (only use the material adjective when looking directly at an object)
        if (obj.getMaterial() != null) phrase += obj.getMaterial().toString() + " ";
        phrase += obj.toString();
        return withArticle(phrase.trim());
    }

    // --- Lifeforms ---
    private static String describeLifeforms(List<Lifeform> lifeforms) {
        if (lifeforms.isEmpty()) return "";
        List<String> lines = new ArrayList<>();
        for (Lifeform lf : lifeforms) {
            String creature = lf.getAdjective() + " " + lf.getName();
            String template = pick(lifeformTemplates);
            lines.add(String.format(template, withArticle(creature), lf.getAction().toString(), lf.getEmotion().toString()));
        }
        return String.join(" ", lines);
    }

    // --- Player ---
    private static String describePlayer(Player player) {
        if (player.getAction() == null || player.getAction().toString().equals("NOTHING")) {
            String template = pick(playerTemplatesIdle);
            return String.format(template, player.getEmotion().toString());
        } else {
            String template = pick(playerTemplatesActive);
            return String.format(template, player.getAction().toString(), player.getEmotion().toString(), player.getCondition());
        }
    }

    // --- Helpers ---
    private static String pick(List<String> options) {
        return options.get(Math.getRandom().nextInt(options.size()));
    }

    private static String withArticle(String phrase) {
        if (phrase == null || phrase.isEmpty()) return "";
        char first = Character.toLowerCase(phrase.charAt(0));
        String article = "AEIOU".indexOf(first) >= 0 ? "AN" : "A";
        return article + " " + phrase;
    }
    
    // --- Templates ---
    private static final List<String> roomTemplates = Arrays.asList(
        "YOU ARE IN A %s CHAMBER, %s.",
        "THE %s ROOM IS %s.",
        "AROUND YOU STRETCHES A %s SPACE, %s."
    );

    private static final List<String> objectTemplates = Arrays.asList(
        "YOU SEE %s %s %s.",
        "YOU NOTICE %s %s %s.",
        "THERE IS %s %s %s."
    );

    private static final List<String> lifeformTemplates = Arrays.asList(
        "%s IS %s WITH A %s EXPRESSION.",
        "YOU SPOT %s, %s, ITS DEMEANEOR IS %s.",
        "LURKING NEARBY, %s IS %s %s."
    );

    private static final List<String> playerTemplatesIdle = Arrays.asList(
        "YOU STAND %s, DOING NOTHING IN PARTICULAR.",
        "AT THE CENTER, YOU REMAIN STILL, YOUR STANCE %s.",
        "YOU ARE %s, IDLE FOR THE MOMENT."
    );

    private static final List<String> playerTemplatesActive = Arrays.asList(
        "YOU ARE %s, YOUR STANCE IS %s AND FEEL %s.",
        "AT THE CENTER, YOU ARE %s WHILE STANDING %s, FEELING %s.",
        "YOU REMAIN %s, %s, FEELING %s."
    );
}
