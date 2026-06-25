package src.main.java.game.nouns.locations.biomes;

import src.main.java.game.ColorPalette;
import src.main.java.game.nouns.locations.Planet;
import src.main.java.text_based_mechanics.parts_of_speech.Adjective;
import src.main.java.text_based_mechanics.parts_of_speech.adjective.*;
import src.main.java.utilities.Enum;
import src.main.java.utilities.Math;

import java.util.*;

public class Biome {

    String name;

    Planet parent;

    Category category;

    ColorPalette palette;

    Set<Adjective> adjectives = new HashSet<>();

    Weather weather;
    Set<Geographical> features = new HashSet<>();
    Lux lux;
    Humidity humidity;
    Temperature temperature;
    Fauna fauna;
    Flora flora;
    Boolean shelter = false;

    public Biome() {
        // default constructor
    }
    
    public Biome(Planet planet) {
        this.parent = planet;
        switch(parent.getCategory()) {
            case TERRAN -> {
                category = Enum.choose(Category.GRASSLANDS, Category.FOREST, Category.SWAMP, Category.HIGHLANDS);
            }
            case DESERT -> {
                category = Enum.choose(Category.DUNES, Category.CANYON, Category.PLAINS, Category.CAVE);
            }
            case OCEANIC -> {
                category = Enum.choose(Category.ISLAND, Category.COASTLINE, Category.SEA, Category.ABYSS);
            }
            case JUNGLE -> {
                category = Enum.choose(Category.RAINFOREST, Category.VALLEY, Category.CAVE, Category.CANYON);
            }
            case VOLCANIC -> {
                category = Enum.choose(Category.VOLCANO, Category.ASHLANDS, Category.CAVE, Category.BASIN);
            }
            case ARCTIC -> {
                category = Enum.choose(Category.MOUNTAINS, Category.WASTELANDS, Category.CAVE, Category.VALLEY);
            }
            case DESOLATE -> {
                category = Enum.choose(Category.WASTELANDS, Category.BASIN, Category.CAVE, Category.CANYON);
            }
        }
        setTemperature();
        setHumidity();
        setWeather();
        setLight();
        setVegetation();
        setFauna();
        setFeatures();
        setAdjectives();

        name = category.toString();
    }

    public String toString() {
        return name.toUpperCase();
    }

    private void setAdjectives() {
        // add adjectives for each planet
        switch(parent.getCategory()) {
            case TERRAN -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.DAPPLED, Descriptive.BLEAK)));
            }
            case DESERT -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.RADIANT, Descriptive.BRILLIANT)));
                adjectives.add(new Adjective(Enum.choose(Color.AMBER, Color.BROWN)));
            }
            case OCEANIC -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.DARK, Descriptive.DEEP)));
                adjectives.add(new Adjective(Enum.choose(Color.OPALESCENT, Color.BLUE)));
            }
            case JUNGLE -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.VIBRANT, Descriptive.VIVID, Descriptive.VERDANT)));
                adjectives.add(new Adjective(Enum.choose(Color.GREEN)));
            }
            case VOLCANIC -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.FIERY, Descriptive.FLAMING)));
                adjectives.add(new Adjective(Enum.choose(Color.AMBER, Color.RED)));
            }
            case ARCTIC -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.BLEAK, Descriptive.COLD)));
                adjectives.add(new Adjective(Enum.choose(Color.ALABASTER, Color.WHITE, Color.COLORLESS)));
            }
            case DESOLATE -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.FORGOTTEN, Descriptive.SOMBER)));
                adjectives.add(new Adjective(Enum.choose(Color.GRAY, Color.BLACK, Color.COLORLESS)));
            }
        }

        // reset adjectives if in a shelter biome
        if (isShelter()) {
            adjectives.clear();
        }

        // add adjectives for each biome
        switch (category) {
            case ABYSS -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.DARK, Descriptive.DEEP, Descriptive.ABNORMAL)));
                adjectives.add(new Adjective(Enum.choose(Color.OPALESCENT, Color.BLUE)));
            }
            case ASHLANDS -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.FIERY, Descriptive.BARREN, Descriptive.ASHEN)));
                adjectives.add(new Adjective(Enum.choose(Color.RED, Color.COLORLESS)));
            }
            case BASIN -> {
                // take whatever adjectives used for the planet
            }
            case CAVE -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.QUIET, Descriptive.DARK)));
                switch(parent.getCategory()) {
                    case DESERT -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.SANDY, Descriptive.HOT)));
                        adjectives.add(new Adjective(Enum.choose(Color.AMBER, Color.BROWN)));
                    }
                    case JUNGLE -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.LUSH, Descriptive.PSYCHEDELIC, Descriptive.VERDANT)));
                        adjectives.add(new Adjective(Enum.choose(Color.GREEN, Color.EMERALD)));
                    }
                    case VOLCANIC -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.FIERY, Descriptive.HAZY)));
                        adjectives.add(new Adjective(Enum.choose(Color.AMBER, Color.RED)));
                    }
                    case ARCTIC -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.PRISMATIC, Descriptive.COLD)));
                        adjectives.add(new Adjective(Enum.choose(Color.ALABASTER, Color.WHITE, Color.PURPLE)));
                    }
                    case DESOLATE -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.FORGOTTEN, Descriptive.DUSTY)));
                        adjectives.add(new Adjective(Enum.choose(Color.GRAY, Color.BLACK, Color.COLORLESS)));
                    }
                }
            }
            case CANYON -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.QUIET, Descriptive.DEEP)));
                switch(parent.getCategory()) {
                    case DESERT -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.SANDY, Descriptive.COLD)));
                        adjectives.add(new Adjective(Enum.choose(Color.AMBER, Color.BROWN)));
                    }
                    case JUNGLE -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.LUSH, Descriptive.PSYCHEDELIC, Descriptive.VERDANT)));
                        adjectives.add(new Adjective(Enum.choose(Color.EMERALD, Color.GREEN)));
                    }
                    case DESOLATE -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.FORGOTTEN, Descriptive.DUSTY)));
                        adjectives.add(new Adjective(Enum.choose(Color.GRAY, Color.BLACK, Color.COLORLESS)));
                    }
                }
            }
            case COASTLINE -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.GLITTERING, Descriptive.SANDY)));
                adjectives.add(new Adjective(Enum.choose(Color.AZURE, Color.COLORFUL)));
            }
            case DUNES -> {
                adjectives.add(new Adjective(Descriptive.SANDY));
                adjectives.add(new Adjective(Enum.choose(Descriptive.QUIET, Descriptive.BRIGHT)));
                adjectives.add(new Adjective(Enum.choose(Color.GOLDEN, Color.BROWN)));
            }
            case FOREST -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.QUIET, Descriptive.DARK, Descriptive.VERDANT)));
                adjectives.add(new Adjective(Enum.choose(Color.BLACK, Color.GREEN, Color.EMERALD)));
            }
            case GRASSLANDS -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.QUIET, Descriptive.BRIGHT)));
                adjectives.add(new Adjective(Enum.choose(Color.EMERALD, Color.GREEN)));
            }
            case HIGHLANDS -> {
                // use planet descriptions
            }
            case ISLAND -> {
                // use planet descriptions
            }
            case MOUNTAINS -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.SNOWY, Descriptive.COLD)));
                adjectives.add(new Adjective(Enum.choose(Color.WHITE, Color.ALABASTER)));
            }
            case PLAINS -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.GRASSY, Descriptive.HOT)));
                adjectives.add(new Adjective(Enum.choose(Color.GOLDEN, Color.BROWN)));
            }
            case SEA -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.WET, Descriptive.UNDULATING)));
                adjectives.add(new Adjective(Enum.choose(Color.BLUE, Color.OPALESCENT)));
            }
            case SWAMP -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.SOMBER, Descriptive.QUIET)));
                adjectives.add(new Adjective(Enum.choose(Color.BLACK, Color.COLORLESS)));
            }
            case RAINFOREST -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.LUSH, Descriptive.VIBRANT, Descriptive.VERDANT)));
                adjectives.add(new Adjective(Enum.choose(Color.COLORFUL, Color.MAGENTA, Color.GREEN, Color.EMERALD)));
            }
            case VALLEY -> {
                // inherit from planet
            }
            case VOLCANO -> {
                adjectives.add(new Adjective(Enum.choose(Descriptive.HAZY, Descriptive.HOT)));
                adjectives.add(new Adjective(Enum.choose(Color.RED, Color.CRIMSON)));
            }
            case WASTELANDS -> {
                switch(parent.getCategory()) {
                    case ARCTIC -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.GLITTERING, Descriptive.COLD)));
                        adjectives.add(new Adjective(Enum.choose(Color.ALABASTER, Color.WHITE, Color.COLORLESS)));
                    }
                    case DESOLATE -> {
                        adjectives.add(new Adjective(Enum.choose(Descriptive.FORGOTTEN, Descriptive.DUSTY)));
                        adjectives.add(new Adjective(Enum.choose(Color.GRAY, Color.BLACK, Color.COLORLESS)));
                    }
                }
            }
        }

        // add weather adjectives
        switch (weather) {
            case NONE -> {
                // TODO ?
            }
            case CALM -> {
                adjectives.add(new Adjective(Descriptive.QUIET));
            }
            case SUN -> {
                adjectives.add(new Adjective(Descriptive.BRIGHT));
            }
            case WIND -> {
                adjectives.add(new Adjective(Descriptive.WINDY));
            }
            case OVERCAST -> {
                adjectives.add(new Adjective(Descriptive.DARK));
            }
            case FOG -> {
                //adjectives.add(new Adjective(Descriptive.BLEAK)); none
            }
            case RAIN -> {
                adjectives.add(new Adjective(Descriptive.WET));
            }
            case SMOG -> {
                adjectives.add(new Adjective(Descriptive.HAZY));
                adjectives.add(new Adjective(Descriptive.HOT));
            }
            case SNOW -> {
                adjectives.add(new Adjective(Descriptive.SNOWY));
                adjectives.add(new Adjective(Descriptive.COLD));
            }
            case BLIZZARD -> {
                adjectives.clear();
                adjectives.add(new Adjective(Descriptive.BLINDING));
                adjectives.add(new Adjective(Descriptive.COLD));
                adjectives.add(new Adjective(Enum.choose(Descriptive.WINDY, Descriptive.SNOWY)));
                adjectives.add(new Adjective(Enum.choose(Color.WHITE, Color.ALABASTER)));
            }
            case FIRESTORM -> {
                adjectives.clear();
                adjectives.add(new Adjective(Descriptive.BLINDING));
                adjectives.add(new Adjective(Descriptive.HOT));
                adjectives.add(new Adjective(Enum.choose(Descriptive.FIERY, Descriptive.FLAMING)));
                adjectives.add(new Adjective(Enum.choose(Color.RED, Color.CRIMSON)));
            }
            case SANDSTORM -> {
                adjectives.clear();
                adjectives.add(new Adjective(Descriptive.BLINDING));
                adjectives.add(new Adjective(Enum.choose(Descriptive.WINDY, Descriptive.SANDY)));
                adjectives.add(new Adjective(Enum.choose(Color.AMBER)));
            }
            case HURRICANE -> {
                adjectives.clear();
                adjectives.add(new Adjective(Descriptive.BLINDING));
                adjectives.add(new Adjective(Enum.choose(Descriptive.WET, Descriptive.DARK)));
                adjectives.add(new Adjective(Enum.choose(Color.GRAY)));
            }
        }

        // TODO: remove all but the last color as the TRUE color for a location

    }



//    public String getAdjective() {
////        return Adjective.random(adjectives);
//        return Adjective.get(adjectives,Color.class);
//    }

    public Set<Adjective> getAdjectives() { // TODO
        return Adjective.get(adjectives);
    }

    public String getDescription() {
        String location_intro = "YOU FIND YOURSELF STANDING IN";
        String determiner = " A "; // TODO: get correct determiner for biome (potentially make biome Category another class that holds determiner, OR get from Adjective?) [ie AN, A, THE]
        String[][] biomeToWeatherMap;

        String[] abyss_desc = { "THE ABYSS UNSETTLES YOU. BEST NOT TO STARE FOR TOO LONG.",
                                "GAZING INTO THE ABYSS, YOU CAN'T SHAKE THE FEELING OF UNEASE.",
                                "THE ABYSS BECKONS... SOMETHING FROM THE DEEP REACHES OUT TO YOU, PROBING YOUR MIND.",
                                "THE UNFATHOMABLE DEPTHS OF THE ABYSS CALL OUT TO YOU."};

        String[] caves_desc = { "THE ABYSS UNSETTLES YOU. BEST NOT TO STARE FOR TOO LONG.",
                                "GAZING INTO THE ABYSS, YOU CAN'T SHAKE THE FEELING OF UNEASE.",
                                "THE ABYSS BECKONS... SOMETHING FROM THE DEEP REACHES OUT TO YOU, PROBING YOUR MIND.",
                                "THE UNFATHOMABLE DEPTHS OF THE ABYSS CALL OUT TO YOU."};

        biomeToWeatherMap = new String[][] {
              // GRASSLAND                                      FOREST                                                              SWAMP                                                                                             HIGHLANDS                                                     ASHLAND                                                                                                     VOLCANO                                                                                                     CAVE                      MOUNTAIN                                                                                                          DUNES                                                                                                              CANYON                                                                                                                    PLAINS                                                                                                         ISLAND                                     COASTAL                                        SEA                                               ABYSS                                                       RAINFOREST                                                                                              VALLEY                                                                                                            BASIN                                                                                                       WASTELAND
 /*     NONE*/  {"",                                            "",                                                                 "",                                                                                               "",                                                           "",                                                                                                         "",                                                                                                         "SILENCE FILLS THE AIR.", "",                                                                                                               "",                                                                                                                "",                                                                                                                       "",                                                                                                            "",                                        "",                                            "",                                               abyss_desc[Math.getRandom().nextInt(abyss_desc.length)],    "",                                                                                                     "",                                                                                                               "",                                                                                                         ""},                                                         // NONE,
 /*     CALM*/  {"SILENCE FILLS THE SURROUNDING FIELDS.",       "SILENCE PERMEATES THROUGH THE FOREST.",                            "AN UNSETTLING SILENCE FILLS THE SWAMP, AS IF THE GNARLED TREES ARE HOLDING THEIR BREATH.",       "SILENCE FILLS THE SURROUNDING ALPINE MEADOWS.",              "AN EERIE CALM SETTLES OVER THE SCORCHED LAND.",                                                            "SILENCE FILLS THE AIR AS THE VOLCANO LOOMS ABOVE YOU.",                                                    "",                       "A TRANQUIL SILENCE FILLS THE TREACHEROUS MOUNTAIN PATH.",                                                        "SILENCE SETTLES OVER THE DUNES.",                                                                                 "SILENCE FILLS THE IMMENSE CANYON.",                                                                                      "A SILENCE DESCENDS UPON THE GOLDEN PLAINS.",                                                                  "A SILENCE FALLS UPON THE ISLAND.",        "A SILENCE FALLS UPON THE COAST.",             "A SILENCE FALLS UPON THE OCEAN.",                "",                                                         "A PEACEFUL SILENCE FILLS THE RAINFOREST. OR ARE YOU BEING HUNTED?",                                    "SILENCE FILLS THE VALLEY.",                                                                                      "A TRANQUIL SILENCE PERMEATES THE IMMENSE BASIN.",                                                          "AN EERIE SILENCE SETTLES OVER THIS FORGOTTEN LANDSCAPE."},  // CALM,
 /*    SUNNY*/  {"SUNLIGHT ILLUMINATES MILES OF GREEN FIELDS.", "THE SUN DANCES OVERHEAD THROUGH THE THICK FOREST CANOPY ABOVE.",   "SMALL PATCHES OF SUN PEEK THROUGH TWISTED TREES AND PRISMATIC PATTERNS DANCE IN THE BOG WATER.", "SUNLIGHT ILLUMINATES THE GREAT EXPANSE OF HIGHLAND FIELDS.", "SUNLIGHT BLESSES THIS DEAD LAND THROUGH THE DUST AND ASHES.",                                              "RAYS OF SUNLIGHT PEAK OVER THE TOP OF THE VOLCANO.",                                                       "",                       "THE SUN DANCES OVER THE MOUNTAIN PEAKS.",                                                                        "THE SUNS RELENTLESS HEAT BEATS DOWN FROM ABOVE.",                                                                 "THE SUN SHINES BRIGHTLY OVERHEAD.",                                                                                      "THE SUN BEATS DOWN FROM ABOVE.",                                                                              "THE SUN BEATS DOWN FROM ABOVE.",          "THE SUN DANCES THROUGH THE CLOUDLESS SKY.",   "THE SUN DANCES THROUGH THE CLOUDLESS SKY.",      "",                                                         "SUNLIGHT DRIPS THROUGH THE THICK JUNGLE CANOPY.",                                                      "SUNLIGHT PEAKS THROUGH THE VALLEY.",                                                                             "SUNLIGHT DANCES ABOVE YOU.",                                                                               "RAYS OF SUNLIGHT GRACE THE DUSTY AIR."},                    // SUN,
 /* OVERCAST*/  {"DARK CLOUDS LOOM OVERHEAD.",                  "A DARKNESS SETTLES ABOVE YOU, BARELY SEEN THROUGH THE TREE TOPS.", "A DARKNESS SETTLES ABOVE YOU.",                                                                  "DARK CLOUDS LOOM OVERHEAD.",                                 "DARK CLOUDS LOOM OVERHEAD.",                                                                               "DARK CLOUDS LOOM OVERHEAD.",                                                                               "",                       "DARK CLOUDS LOOM OVERHEAD.",                                                                                     "A DARK CLOUD SETTLES JUST OVERHEAD.",                                                                             "DARK CLOUDS FORM ABOVE YOU.",                                                                                            "A DARK CLOUD FORMS OVERHEAD.",                                                                                "DARK CLOUDS LOOM OVERHEAD.",              "DARK CLOUDS LOOM OVERHEAD.",                  "DARK CLOUDS LOOM OVERHEAD.",                     "",                                                         "DARK CLOUDS FORM ABOVE YOU.",                                                                          "DARK CLOUDS FORM OVER THE VALLEY.",                                                                              "DARK CLOUDS LOOM OVERHEAD.",                                                                               "DARK CLOUDS LOOM OVERHEAD."},                               // OVERCAST,
 /*     WIND*/  {"GUSTS OF WIND BLOW THROUGH THE FIELDS.",      "WIND BEGINS TO BLOW THROUGH THE TREES.",                           "WIND BLOWS THROUGH THE WRETCHED DYING TREES.",                                                   "GUSTS OF WIND BLOW OVER THE HIGHLANDS FIELDS.",              "WIND BEGINS TO BLOW, KICKING UP CLOUDS OF ASH AND DUST.",                                                  "THE WIND BLOWS, KICKING UP CLOUDS OF DUST AND ASHES.",                                                     "",                       "A STRONG WIND BEGINS TO BLOW THROUGH THE MOUNTAIN PASS.",                                                        "THE WIND PICKS UP, KICKING UP CLOUDS OF SAND.",                                                                   "THE WIND BEGINS TO BLOW THROUGH THE CANYON.",                                                                            "THE WIND BLOWS THROUGH THE PLAINS, LIKE AN AMBER OCEAN OF GRASS.",                                            "THE WIND BEGINS TO BLOW.",                "THE WIND BEGINS TO BLOW.",                    "THE WIND BLOWS, CAUSING THE OCEAN TO UNDULATE.", "",                                                         "WIND BLOWS THROUGH THE RAINFOREST.",                                                                   "WIND BLOWS THROUGH THE VALLEY.",                                                                                 "GUSTS OF WIND BEGIN TO BLOW.",                                                                             "GRAY DUST SWIRLS AROUND YOU, AS THE WIND BEGINS TO BLOW."}, // WIND,
 /*     RAIN*/  {"DROPS OF RAIN FALL FROM THE SKY.",            "A LIGHT RAIN TRICKLES DOWN FROM ABOVE.",                           "RAIN BEGINS TO FALL.",                                                                           "SMALL DROPS OF RAIN BEGIN TO FALL.",                         "SMALL DROPS OF RAIN BEGIN TO FALL.",                                                                       "SMALL DROPS OF RAIN BEGIN TO FALL. A WELCOME SIGHT.",                                                      "",                       "FREEZING RAIN FALLS FROM THE SKY. PRISMS OF LIGHT PASSING THROUGH THE SKY.",                                     "A FEW DROPS OF RAIN BEGIN TO FALL. EVAPORATING AS THEY HIT THE HOT SANDS.",                                       "SMALL DROPS OF RAIN FALL FROM THE SKY.",                                                                                 "SMALL DROPS OF RAIN FALL FROM THE SKY.",                                                                      "DROPS OF RAIN FALL FROM THE SKY.",        "DROPS OF RAIN FALL FROM THE SKY.",            "DROPS OF RAIN FALL FROM THE DARKENED SKY.",      "",                                                         "WARM TROPICAL RAIN BEGINS TO FALL.",                                                                   "DROPS OF RAIN FALL FROM THE SKY.",                                                                               "DROPS OF RAIN FALL FROM THE SKY.",                                                                         "DROPS OF RAIN FALL FROM THE SKY."},                         // RAIN,
 /*      FOG*/  {"A THICK WHITE FOG SWIRLS AROUND YOU.",        "A THICK FOG SETTLES, SWIRLING AROUND THE FOREST",                  "A THICK FOG SWIRLS AROUND THE TWISTED TREES.",                                                   "A THICK WHITE MIST SWIRLS AROUND YOU.",                      "A THICK WHITE MIST SWIRLS THROUGH THE BURNED HILLS.",                                                      "A THICK FOG BEGINS TO SETTLE AROUND YOU.",                                                                 "",                       "A THICK WHITE FOG SETTLES IN THE MOUNTAIN PASS.",                                                                "FOG SETTLES OVER THE DESERT SANDS.",                                                                              "A WHITE MIST FILLS THE CANYON.",                                                                                         "A WHITE MIST SETTLES AROUND YOU.",                                                                            "A WHITE MIST SETTLES AROUND YOU.",        "A THICK MIST SWIRLS AROUND YOU.",             "A THICK MIST SWIRLS AROUND YOU.",                "",                                                         "A THICK FOG SWIRLS THROUGH THE JUNGLE.",                                                               "A THICK FOG SWIRLS THROUGH THE VALLEY.",                                                                         "A THICK WHITE MIST SWIRLS AROUND YOU.",                                                                    "A WHITE MIST SETTLES ON THE DEAD LANDSCAPE."},              // FOG,
 /*     SMOG*/  {"A THICK BLACK HAZE SWIRLS AROUND YOU.",       "A BLACK SMOG SETTLES, SWIRLING AROUND THE FOREST",                 "A THICK SMOG SETTLES AROUND THE TWISTED TREES.",                                                 "A THICK BLACK HAZE SWIRLS AROUND YOU.",                      "A THICK BLACK SMOG SWIRLS AROUND YOU.",                                                                    "THICK BLACK SMOG SWIRLS AROUND YOU.",                                                                      "",                       "A THICK BLACK HAZE SETTLES IN THE MOUNTAIN PASS.",                                                               "THICK BLACK HAZE SETTLES OVER THE DESERT SANDS.",                                                                 "A THICK BLACK HAZE FILLS THE CANYON.",                                                                                   "A THICK BLACK HAZE SURROUNDS YOU.",                                                                           "A THICK BLACK HAZE SURROUNDS YOU.",       "A THICK BLACK HAZE SETTLES ALONG THE COAST.", "A THICK BLACK HAZE SETTLES OVER THE OCEAN.",     "",                                                         "A THICK BLACK HAZE FILLS THE JUNGLE.",                                                                 "A THICK BLACK HAZE SETTLES IN THE VALLEY.",                                                                      "A THICK BLACK HAZE SETTLES IN THE BASIN.",                                                                 "A BLACK HAZE SETTLES OVER THE DEAD LANDSCAPE."},            // SMOG,
 /*     SNOW*/  {"SMALL FLECKS OF SNOW FALL FROM THE SKY",      "SMALL FLECKS OF SNOW FALL FROM THE SKY",                           "A SUDDEN CHILL MAKES YOU SHIVER. SNOW BEGINS TO FALL.",                                          "DARK CLOUDS LOOM OVERHEAD AS THE SNOW BEGINS TO FALL.",      "",                                                                                                         "",                                                                                                         "",                       "SHEETS OF WHITE SNOW FALL FROM ABOVE.",                                                                          "",                                                                                                                "SNOW FALLS IN THE CANYON.",                                                                                              "SMALL FLECKS OF SNOW FALL FROM THE SKY.",                                                                     "SMALL FLECKS OF SNOW FALL FROM THE SKY.", "SMALL FLECKS OF SNOW FALL FROM THE SKY.",     "SMALL FLECKS OF SNOW FALL FROM THE SKY.",        "",                                                         "",                                                                                                     "SNOW BEGINS TO FALL IN THE VALLEY.",                                                                             "SNOW BEGINS TO FALL.",                                                                                     "DUSTY GRAY SNOW BEGINS TO FALL."},                          // SNOW,
 /*SANDSTORM*/  {"G",                                           "F",                                                                "S",                                                                                              "H",                                                          "A",                                                                                                        "V",                                                                                                        "C",                      "M",                                                                                                              "CLOUDS OF SAND FILL THE AIR AS A SANDSTORM BEGINS! THE SAND STINGS LIKE A THOUSAND WASPS, AND BLINDS YOUR EYES.", "CLOUDS OF SAND FILL THE AIR AS A SANDSTORM BEGINS! THE SAND STINGS LIKE A THOUSAND GNATS, AND BLINDS YOUR EYES.",        "BEFORE YOU KNOW IT A SANDSTORM BEGINS! THE SAND STINGS LIKE A THOUSAND NEEDLES, AND BLINDS YOUR EYES.",       "I",                                       "C",                                           "S",                                              "A",                                                        "R",                                                                                                    "V",                                                                                                              "B",                                                                                                        "W"},  // SANDSTORM,
 /*FIRESTORM*/  {"G",                                           "F",                                                                "S",                                                                                              "H",                                                          "INTENSE HEAT FILLS THE AIR AS A FIRESTORM BEGINS! THE CRIMSON WIND BURNS YOUR SKIN AND BLINDS YOUR EYES.", "INTENSE HEAT FILLS THE AIR AS A FIRESTORM BEGINS! THE CRIMSON WIND BURNS YOUR SKIN AND BLINDS YOUR EYES.", "C",                      "M",                                                                                                              "D",                                                                                                               "C",                                                                                                                      "P",                                                                                                           "I",                                       "C",                                           "S",                                              "A",                                                        "R",                                                                                                    "V",                                                                                                              "INTENSE HEAT FILLS THE AIR AS A FIRESTORM BEGINS! THE CRIMSON WIND BURNS YOUR SKIN AND BLINDS YOUR EYES.", "W"},  // FIRESTORM,
 /*HURRICANE*/  {"G",                                           "F",                                                                "S",                                                                                              "H",                                                          "A",                                                                                                        "V",                                                                                                        "C",                      "M",                                                                                                              "D",                                                                                                               "A HURRICANE SWIRLS AND STORMS AROUND YOU!",                                                                              "P",                                                                                                           "I",                                       "C",                                           "S",                                              "A",                                                        "A HURRICANE SWIRLS AND STORMS AROUND YOU! YOU ARE COMPLETELY DRENCHED AS THE DARK TORRENT CLOSES IN.", "A HURRICANE SWIRLS AND STORMS AROUND YOU! YOU ARE COMPLETELY DRENCHED AS THE DARK TORRENT CLOSES IN.",           "B",                                                                                                        "W"},  // HURRICANE,
 /* BLIZZARD*/  {"G",                                           "F",                                                                "S",                                                                                              "H",                                                          "A",                                                                                                        "V",                                                                                                        "C",                      "INTENSE COLD FILLS THE AIR AS A BLIZZARD BEGINS! THE FREEZING BLIZZARD PUMMELS YOUR BODY AND BLINDS YOUR EYES.", "D",                                                                                                               "C",                                                                                                                      "P",                                                                                                           "I",                                       "C",                                           "S",                                              "A",                                                        "R",                                                                                                    "INTENSE COLD FILLS THE AIR AS A BLIZZARD BEGINS! THE FREEZING BLIZZARD PUMMELS YOUR BODY AND BLINDS YOUR EYES.", "B",                                                                                                        "INTENSE COLD FILLS THE AIR AS A BLIZZARD BEGINS! THE FREEZING BLIZZARD PUMMELS YOUR BODY AND BLINDS YOUR EYES."},  // BLIZZARD
        };
        String weather = biomeToWeatherMap[this.weather.ordinal()][this.category.ordinal()]; // TODO: REPLACE HARD-CODED ADJECTIVES ABOVE WITH LOCATIONAL ADJECTIVES!
                                                                                             // TODO: REPLACE SOUNDS-BASED ADJECTIVES WITH NEW AUDIBLE ADJECTIVES?
                                                                                             // TODO: MOVE TO NARRARATOR CLASS? OR... MAKE SUBCLASS OF BIOME FOR EACH
                                                                                             // TODO: IF EACH BIOME IS A CHILD CLASS WE CAN HAVE 100s OF DESCRIPTIONS, RANDOMLY SELECTED, A COLOR PALETTE, A SET OF ADJECTIVES ETC

        String features = this.features.toString();
        String string = location_intro + determiner + getAdjectives() + " " + category + ". " + weather;
        return string;
    }

    private void setTemperature() {
        switch(parent.getCategory()) {
            case TERRAN -> {
                temperature = Enum.choose(Temperature.LOW, Temperature.NORMAL, Temperature.NORMAL, Temperature.NORMAL, Temperature.HIGH);
            }
            case DESERT -> {
                temperature = Enum.choose(Temperature.NORMAL, Temperature.HIGH, Temperature.HIGH, Temperature.VERYHIGH, Temperature.VERYHIGH);
            }
            case OCEANIC -> {
                temperature = Enum.choose(Temperature.LOW, Temperature.NORMAL, Temperature.NORMAL, Temperature.NORMAL);
            }
            case JUNGLE -> {
                temperature = Enum.choose(Temperature.NORMAL, Temperature.HIGH, Temperature.HIGH, Temperature.HIGH, Temperature.VERYHIGH);
            }
            case VOLCANIC -> {
                temperature = Enum.choose(Temperature.HIGH, Temperature.VERYHIGH, Temperature.VERYHIGH, Temperature.VERYHIGH, Temperature.SCORCHING, Temperature.SCORCHING);
                temperature = category == Category.VOLCANO ? Temperature.SCORCHING : temperature;
            }
            case ARCTIC -> {
                temperature = Enum.choose(Temperature.LOW, Temperature.ZERO, Temperature.ZERO, Temperature.ZERO, Temperature.SUBZERO, Temperature.SUBZERO);
            }
            case DESOLATE -> {
                temperature = Enum.choose(Temperature.NORMAL, Temperature.LOW, Temperature.ZERO, Temperature.ZERO, Temperature.ZERO, Temperature.SUBZERO, Temperature.SUBZERO);
            }
        }
    }

    private void setHumidity() {
        switch(parent.getCategory()) {
            case TERRAN -> {
                humidity = Enum.choose(Humidity.LOW, Humidity.MEDIUM, Humidity.MEDIUM, Humidity.MEDIUM, Humidity.HIGH);
            }
            case DESERT -> {
                humidity = Enum.choose(Humidity.ZERO, Humidity.ZERO, Humidity.LOW);
            }
            case OCEANIC -> {
                humidity = Enum.choose(Humidity.MEDIUM, Humidity.HIGH);
            }
            case JUNGLE -> {
                humidity = Humidity.HIGH;
            }
            case VOLCANIC -> {
                humidity = Enum.choose(Humidity.ZERO, Humidity.LOW, Humidity.MEDIUM);
            }
            case ARCTIC -> {
                humidity = Enum.choose(Humidity.MEDIUM, Humidity.MEDIUM, Humidity.MEDIUM, Humidity.HIGH, Humidity.HIGH);
            }
            case DESOLATE -> {
                humidity = Humidity.ZERO;
            }
        }
    }

    private void setWeather() {

        setShelter(); // determine if current biome is sheltered from normal weather effects

        // standard weather options for humidity and temperature
        switch (temperature) {
            case NORMAL -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.OVERCAST,Weather.RAIN);
                }
                else {
                    weather = Enum.choose(Weather.SUN,Weather.CALM);
                }
            }
            case LOW -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.FOG,Weather.OVERCAST,Weather.RAIN);
                }
                else {
                    weather = Enum.choose(Weather.CALM,Weather.WIND);
                }
            }
            case HIGH -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.FOG,Weather.OVERCAST,Weather.RAIN);
                }
                else {
                    weather = (parent.getCategory() != Planet.Category.DESERT) ? Enum.choose(Weather.SUN,Weather.CALM,Weather.WIND) : Enum.choose(Weather.SUN,Weather.CALM,Weather.SANDSTORM);
                }
            }
            case ZERO -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.FOG,Weather.OVERCAST,Weather.SNOW,Weather.SNOW,Weather.SNOW,Weather.BLIZZARD);
                }
                else {
                    weather = Enum.choose(Weather.SUN,Weather.CALM,Weather.WIND);
                }
            }
            case SUBZERO -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.FOG,Weather.OVERCAST,Weather.SNOW,Weather.SNOW,Weather.BLIZZARD,Weather.BLIZZARD);
                }
                else {
                    weather = Enum.choose(Weather.SUN,Weather.CALM,Weather.WIND);
                }
            }
            case VERYHIGH -> {
                if (humidity.ordinal() > Humidity.MEDIUM.ordinal()) {
                    weather = Enum.choose(Weather.FOG,Weather.OVERCAST,Weather.RAIN,Weather.HURRICANE,Weather.HURRICANE);
                }
                else {
                    switch (parent.getCategory()) {
                        case VOLCANIC -> {
                            weather = Enum.choose(Weather.SUN,Weather.WIND,Weather.SMOG,Weather.FIRESTORM);
                        }
                        case DESERT -> {
                            weather = Enum.choose(Weather.SUN,Weather.WIND,Weather.SANDSTORM,Weather.SANDSTORM);
                        }
                        default -> {
                            weather = Enum.choose(Weather.SUN,Weather.WIND);
                        }
                    }
                }
            }
            case SCORCHING -> {
                weather = Enum.choose(Weather.CALM,Weather.WIND,Weather.SMOG,Weather.FIRESTORM);
            }
        }

        // special weather for specific biomes
        if (category == Category.SWAMP) {
            weather = Enum.choose(Weather.FOG, Weather.RAIN);
        }

        if (isShelter()) {
            weather = Weather.NONE;
        }
    }

    private void setFeatures() {
        switch(category) {
            case GRASSLANDS -> {
                features.add(Enum.choose(Geographical.HILLS,Geographical.PLAINS,Geographical.MEADOWS,Geographical.LAKES));
            }
            case FOREST -> {
                features.add(Enum.choose(Geographical.FOREST));
                features.add(Enum.choose(Geographical.SPRING,Geographical.WATERFALL,Geographical.RIVER));
            }
            case CAVE -> {
                features.add(Enum.choose(Geographical.CAVE));
                switch (parent.getCategory()) {
                    case VOLCANIC -> {
                        features.add(Enum.choose(Geographical.MAGMA,Geographical.MAGMA,Geographical.ROCKY,Geographical.HOTSPRING));
                    }
                    case DESERT -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.DUNES));
                    }
                    case JUNGLE -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.JUNGLE));
                    }
                    default -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.ROCKY,Geographical.SPRING));
                    }
                }
            }
            case ISLAND -> {
                features.add(Enum.choose(Geographical.MEADOWS));
            }
            case CANYON -> {
                features.add(Enum.choose(Geographical.CANYON));
                switch (parent.getCategory()) {
                    case VOLCANIC -> {
                        features.add(Enum.choose(Geographical.MAGMA,Geographical.MAGMA,Geographical.ROCKY));
                    }
                    case DESERT -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.DUNES,Geographical.DUNES));
                    }
                    case JUNGLE -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.JUNGLE));
                    }
                    default -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.ROCKY));
                    }
                }
            }
            case BASIN -> {
                switch (parent.getCategory()) {
                    case VOLCANIC -> {
                        features.add(Enum.choose(Geographical.MAGMA, Geographical.MAGMA, Geographical.LAVA_GEYSERS, Geographical.ROCKY));
                    }
                    case DESERT -> {
                        features.add(Enum.choose(Geographical.EROSION, Geographical.DUNES, Geographical.DUNES));
                    }
                    case JUNGLE -> {
                        features.add(Enum.choose(Geographical.EROSION, Geographical.WATERFALL, Geographical.JUNGLE, Geographical.RIVER));
                    }
                    case DESOLATE -> {
                        features.add(Enum.choose(Geographical.CRATERED, Geographical.EROSION, Geographical.ROCKY));
                    }
                    default -> {
                        features.add(Enum.choose(Geographical.WETLAND,Geographical.CRATERED,Geographical.RIVER,Geographical.RIVERBED,Geographical.GEYSERS));
                    }
                }

            }
            case VALLEY -> {
                features.add(Enum.choose(Geographical.VALLEY));
                switch (parent.getCategory()) {
                    case VOLCANIC -> {
                        features.add(Enum.choose(Geographical.MAGMA,Geographical.MAGMA,Geographical.ROCKY,Geographical.CHASM));
                    }
                    case DESERT -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.DUNES,Geographical.DUNES,Geographical.CHASM));
                    }
                    case JUNGLE -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.JUNGLE,Geographical.CHASM));
                    }
                    case DESOLATE -> {
                        features.add(Enum.choose(Geographical.CRATERED, Geographical.EROSION, Geographical.ROCKY,Geographical.CHASM));
                    }
                    default -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.ROCKY,Geographical.GEYSERS));
                    }
                }
            }
            case SEA -> {
                features.add(Enum.choose(Geographical.OCEAN));
            }
            case WASTELANDS -> {
                switch (parent.getCategory()) {
                    case VOLCANIC -> {
                        features.add(Enum.choose(Geographical.MAGMA,Geographical.MAGMA,Geographical.LAVA_GEYSERS,Geographical.ROCKY));
                    }
                    case DESERT -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.DUNES,Geographical.DUNES,Geographical.PLATEAU));
                    }
                    case JUNGLE -> {
                        features.add(Enum.choose(Geographical.EROSION,Geographical.WATERFALL,Geographical.JUNGLE));
                    }
                    default -> {
                        features.add(Enum.choose(Geographical.CRATERED,Geographical.EROSION,Geographical.ROCKY));
                    }
                }
            }
            case ABYSS -> {
                features.add(Enum.choose(Geographical.OCEAN));
                features.add(Enum.choose(Geographical.CHASM,Geographical.ROCKY));
            }
            case DUNES -> {
                features.add(Enum.choose(Geographical.DUNES));
                features.add(Enum.choose(Geographical.RIVERBED,Geographical.EROSION,Geographical.PLATEAU,Geographical.ROCKY));
            }
            case ASHLANDS -> {
                features.add(Enum.choose(Geographical.CRATERED,Geographical.ROCKY));
                features.add(Enum.choose(Geographical.MAGMA,Geographical.LAVA_GEYSERS));
            }
            case SWAMP -> {
                features.add(Enum.choose(Geographical.WETLAND));
            }
            case PLAINS -> {
                features.add(Enum.choose(Geographical.PLAINS));
            }
            case COASTLINE -> {
                features.add(Enum.choose(Geographical.SHORELINE));
                features.add(Enum.choose(Geographical.EROSION,Geographical.ROCKY));
            }
            case VOLCANO -> {
                features.add(Enum.choose(Geographical.CALDERA));
                features.add(Enum.choose(Geographical.MAGMA,Geographical.MAGMA,Geographical.ROCKY,Geographical.EROSION,Geographical.CHASM));
            }
            case MOUNTAINS -> {
                features.add(Enum.choose(Geographical.MOUNTAIN));
                features.add(Enum.choose(Geographical.ROCKY,Geographical.EROSION,Geographical.FOOTHILLS,Geographical.CLIFF,Geographical.CHASM));
            }
            case HIGHLANDS -> {
                features.add(Enum.choose(Geographical.FOOTHILLS));
                features.add(Enum.choose(Geographical.HILLS,Geographical.MEADOWS,Geographical.PLAINS,Geographical.PLATEAU));
            }
            case RAINFOREST -> {
                features.add(Enum.choose(Geographical.JUNGLE));
            }
        }
    }

    private void setLight() {

//                DARK,     // impossible to see without light source
//                TWILIGHT, // difficult to see without light
//                NORMAL,   // normal light levels
//                BRIGHT    // blinding light, increased fatigue

        switch(parent.getCategory()) {
            case TERRAN, ARCTIC, OCEANIC -> {
                lux = Lux.NORMAL;
            }
            case DESERT -> {
                lux = Lux.BRIGHT;
            }
            case JUNGLE, VOLCANIC, DESOLATE -> {
                lux = Lux.TWILIGHT;
            }
        }

        // override base planetary light with weather
        switch (weather) {
            case SUN, FIRESTORM, BLIZZARD -> {
                lux = Lux.BRIGHT;
            }
            case OVERCAST, RAIN, SANDSTORM, HURRICANE -> {
                lux = Lux.TWILIGHT;
            }
            case SMOG -> {
                lux = Lux.DARK;
            }
        }

        // make certain locations always dark
        switch (category) {
            case CAVE, ABYSS -> {
                lux = Lux.DARK;
            }
            case SWAMP -> {
                lux = Lux.TWILIGHT;
            }
        }

    }

    private void setVegetation() {
        switch(parent.getCategory()) {
            case TERRAN -> {
                flora = Enum.choose(Flora.LOW, Flora.MEDIUM, Flora.MEDIUM, Flora.MEDIUM, Flora.HIGH);
            }
            case DESERT -> {
                flora = Enum.choose(Flora.NONE, Flora.LOW);
            }
            case OCEANIC -> {
                flora = Enum.choose(Flora.LOW, Flora.MEDIUM, Flora.HIGH);
            }
            case JUNGLE -> {
                flora = Flora.HIGH;
            }
            case VOLCANIC -> {
                flora = Enum.choose(Flora.NONE, Flora.NONE, Flora.NONE, Flora.LOW);
            }
            case ARCTIC -> {
                flora = Enum.choose(Flora.NONE, Flora.LOW, Flora.MEDIUM);
            }
            case DESOLATE -> {
                flora = Flora.NONE;
            }
        }
    }

    private void setFauna() {
        switch(parent.getCategory()) {
            case TERRAN -> {
                fauna = Enum.choose(Fauna.LOW, Fauna.MEDIUM, Fauna.MEDIUM, Fauna.MEDIUM, Fauna.HIGH);
            }
            case DESERT -> {
                fauna = Enum.choose(Fauna.NONE, Fauna.LOW);
            }
            case OCEANIC -> {
                fauna = Enum.choose(Fauna.LOW, Fauna.MEDIUM, Fauna.HIGH);
            }
            case JUNGLE -> {
                fauna = Enum.choose(Fauna.HIGH, Fauna.HIGH, Fauna.MEDIUM, Fauna.LOW);
            }
            case VOLCANIC -> {
                fauna = Enum.choose(Fauna.NONE, Fauna.NONE, Fauna.NONE, Fauna.LOW);
            }
            case ARCTIC -> {
                fauna = Enum.choose(Fauna.NONE, Fauna.LOW, Fauna.MEDIUM);
            }
            case DESOLATE -> {
                fauna = Fauna.NONE;
            }
        }
    }

    private void setShelter() {

        switch (category) {
            case CAVE, ABYSS -> {
                shelter = true;
            }
        }

        if (shelter) {
            weather = Weather.NONE;
        }
    }

    private boolean isShelter() {
        return shelter;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Lux getLux() {
        return lux;
    }

    public Weather getWeather() {
        return weather;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public Flora getFlora() {
        return flora;
    }

    public Fauna getFauna() {
        return fauna;
    }

    public enum Category { // biome categories // TODO: make into a separate class? (implement getDescription() method, which reads all geo features, etc and produces a desc)

        // TERRAN
        GRASSLANDS,
        FOREST,
        SWAMP,
        HIGHLANDS,

        // VOLCANIC
        ASHLANDS,
        VOLCANO,
        CAVE,

        // ARCTIC
        MOUNTAINS,

        // DESERT
        DUNES,
        CANYON,
        PLAINS,

        // OCEANIC
        ISLAND,
        COASTLINE,
        SEA,
        ABYSS,

        // JUNGLE
        RAINFOREST,
        VALLEY,

        // DESOLATE
        BASIN,
        WASTELANDS
    }

    public enum Geographical { // geographical features that may be present in a biome
        RIVER,
        LAKES,
        SHORELINE,
        OCEAN,
        VALLEY,
        CLIFF,
        CANYON,
        PLATEAU,
        CHASM,
        GEYSERS,
        FOREST,
        JUNGLE,
        HILLS,
        RIVERBED,
        WATERFALL,
        SPRING,
        HOTSPRING,
        EROSION,
        CAVE,
        FOOTHILLS,
        MOUNTAIN,
        WETLAND,
        PLAINS,
        MEADOWS,
        MAGMA,
        LAVA_GEYSERS,
        DUNES,
        ROCKY,
        CALDERA,
        CRATERED
    }

    public enum Weather { // dependent on humidity / temperature / category
        NONE,
        CALM,
        SUN,
        OVERCAST,
        WIND,
        RAIN,
        FOG,
        SMOG,
        SNOW,
        SANDSTORM,
        FIRESTORM,
        HURRICANE,
        BLIZZARD
    }

    public enum Lux { // the amount of light
        DARK,
        TWILIGHT,
        NORMAL,
        BRIGHT
    }

    public enum Humidity {
        ZERO,
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Temperature {
        SUBZERO,
        ZERO,
        LOW,
        NORMAL,
        HIGH,
        VERYHIGH,
        SCORCHING
    }

    public enum Fauna {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Flora {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

//    static Map<Category,Set<? extends Adjective>> adjective_map = new HashMap<>() {{
//        //adjectives.add(new Adjective(Enum.choose(Descriptive.RADIANT, Descriptive.BRILLIANT)));
//        put(CAVES,new HashSet<>(List.of(new Adjective(Descriptive.DARK))));
//    }};

}
