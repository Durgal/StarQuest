package src.main.java.generators;


import src.main.java.game.data.Data;
import src.main.java.game.nouns.lifeforms.Monster;
import src.main.java.game.nouns.lifeforms.Species;
import src.main.java.game.nouns.locations.Planet;

import java.util.*;
import src.main.java.game.nouns.lifeforms.Lifeform;

import src.main.java.utilities.Math;

public class NameGenerator {

    // Location name generator
    public static void location(Class clazz) {
        int order = 2;
        switch (clazz.getSimpleName().toUpperCase()) {

            case "ROOM":
                break;

            case "LOCATION":
                Data.location_generator.put(Planet.Category.TERRAN, new MarkovChain(List.of(
                        "VERDANTH","GREENHAVEN","GRASSWOLD","FAIRMEAD","PALDOR","ELMRIDGE","RIVERSHIRE",
                        "HILLFORD","MEADOWRUN","BRIARGLEN","WILDFOLK","ARBOREA","GARDENIA","CEDARHOLT","VALEWOOD"
                ), order));

                Data.location_generator.put(Planet.Category.VOLCANIC, new MarkovChain(List.of(
                        "ASHENFALL","LAVARIDGE","PYRAVAL","EMBERHALT","MAGMAHOLD","CRAGGOR","FIRESPINE",
                        "CINDERTON","SMOLDERAX","FLAMEVEIL","OBSIDIUM","BRIMSTONE","FURNOX","TORVUL","SCORCHAX"
                ), order));

                Data.location_generator.put(Planet.Category.ARCTIC, new MarkovChain(List.of(
                        "FROSTHAVEN","GLACIERON","ICEFJORD","SNOWMERE","TUNDRAHALT","PERMALEI","BLIZZARDEN",
                        "ICEWOLD","NORTHSHEER","GLIMEAR"
                ), order));

                Data.location_generator.put(Planet.Category.DESERT, new MarkovChain(List.of(
                        "SANDREACH","DUNEVOR","ARIDIA","KALDRAN","SEREVA","DUNEMIRE","SCORCHWIND",
                        "SILTHOLD","SABLEVA","GOLDRIFT","SUNSCOUR","MIRAGEA","BAHARTA"
                ), order));

                Data.location_generator.put(Planet.Category.OCEANIC, new MarkovChain(List.of(
                        "SEABRIGHT","COASTMAR","TIDELANE","CORALHAVEN","AQUARIS","PELAGOR","DELPHINE",
                        "NAUTILUS","REEFTON","VASTSEA","WATERFORD","BRINEVALE","SALTARA"
                ), order));

                Data.location_generator.put(Planet.Category.JUNGLE, new MarkovChain(List.of(
                        "RAINTHORN","CANOPYA","MURKWOOD","LUSHVALE","VINEFALL","SILVATRON","TROPARIA",
                        "BROMELIS","FERNGLEN","JUNGARON","MALAYON","DRIFTVINE","SAMOORA"
                ), order));

                Data.location_generator.put(Planet.Category.DESOLATE, new MarkovChain(List.of(
                        "NOXPLAINS","RUINVALE","DUSTHOLD","BLEAKRIDGE","OBLIVION","ASHENBASIN","BARRENGAZE",
                        "SEREWASTE","DESOLIS","WASTERVA","HOLLOWEND","FALLOWDEN"
                ), order));
                break;

            case "CONTINENT":
                Data.continent_generator.put(Planet.Category.TERRAN, new MarkovChain(
                        Arrays.asList("ALVARON", "VERIDIA", "SOLMARIS", "LUMARON", "CALVESTRA"), order));

                Data.continent_generator.put(Planet.Category.JUNGLE, new MarkovChain(
                        Arrays.asList("ZAKURA", "MORLANA", "VINTRAS", "KALORIM", "JUNVARA"), order));

                Data.continent_generator.put(Planet.Category.VOLCANIC, new MarkovChain(
                        Arrays.asList("IGNARON", "MOLTRAS", "PYRADOR", "ASHURAN", "VULKARIA"), order));

                Data.continent_generator.put(Planet.Category.DESERT, new MarkovChain(
                        Arrays.asList("SAHRON", "DUNMARA", "KAZURIM", "ARIDORN", "ZARETH"), order));

                Data.continent_generator.put(Planet.Category.OCEANIC, new MarkovChain(
                        Arrays.asList("AQUILON", "PELAGROS", "MARINOR", "LYRANIS", "CORALITH"), order));

                Data.continent_generator.put(Planet.Category.DESOLATE, new MarkovChain(
                        Arrays.asList("NOXVARA", "UMBRAZON", "TENEBRIA", "VORLATH", "GRIMORIA"), order));

                Data.continent_generator.put(Planet.Category.ARCTIC, new MarkovChain(
                        Arrays.asList("FROSTHOLM", "GLACIARA", "CRYOVAR", "NORDHEL", "STELVORN"), order));
                break;

            case "PLANET":
                Data.planet_generator.put(Planet.Category.TERRAN, new MarkovChain(
                        Arrays.asList("EARTH", "GAIA", "SOLARA", "VERDAN", "ARCADIA"), order));

                Data.planet_generator.put(Planet.Category.JUNGLE, new MarkovChain(
                        Arrays.asList("ZANORA", "MURAKA", "VYNORA", "LOKARI", "KALORA"), order));

                Data.planet_generator.put(Planet.Category.VOLCANIC, new MarkovChain(
                        Arrays.asList("IGNARA", "MOLTARA", "PYRONIS", "ASHMAR", "VULKRON", "MARS"), order));

                Data.planet_generator.put(Planet.Category.DESERT, new MarkovChain(
                        Arrays.asList("SAHARA", "DUNARA", "MIRAGEA", "KAZIRA", "ZARQAN"), order));

                Data.planet_generator.put(Planet.Category.OCEANIC, new MarkovChain(
                        Arrays.asList("AQUARIA", "MARINA", "PELORIA", "NEREUS", "LYRONA"), order));

                Data.planet_generator.put(Planet.Category.DESOLATE, new MarkovChain(
                        Arrays.asList("NOXARA", "UMBRAE", "VORLIS", "GRIMOR", "TENEBRA"), order));

                Data.planet_generator.put(Planet.Category.ARCTIC, new MarkovChain(
                        Arrays.asList("FROSTAN", "GLACIRA", "CRYONIS", "NORDRA", "STELORA"), order));
                break;

            case "SOLARSYSTEM":
                Data.solar_system_generator.put(Data.SEED,
                        new MarkovChain(Arrays.asList(
                                "KEPLARON",
                                "ZYPHORA",
                                "ALTORIS",
                                "ORPHEON",
                                "CALYPSOR",
                                "TRIONIS",
                                "VORLAXIS",
                                "NYTHERON",
                                "AEQUORIS",
                                "XILPHOR",
                                "PROXIMA",
                                "DRAVONIS",
                                "HELIOR",
                                "QUANTARA",
                                "OBLIVOR",
                                "TENEBRIS",
                                "MYRRHON",
                                "STYGIS",
                                "LUNARIS",
                                "GAIATHOR",
                                "PYLONIS",
                                "CRONAX",
                                "UMBRION",
                                "AETHERON",
                                "ZARNAXIS",
                                "VORTARA",
                                "KYTHERA",
                                "ECLIPTOR",
                                "NEBULON",
                                "ARCYON",
                                "XANTRIS",
                                "OMNIRA",
                                "THALORIS",
                                "SPHYNOR",
                                "VULCRON",
                                "LYCORA",
                                "TERAXIS",
                                "QUORAN",
                                "SEPHYRIS",
                                "OBERONIS",
                                "NOXMAR",
                                "ILLYRIA",
                                "CHRONOR",
                                "VALKYRIS",
                                "EXOLOR",
                                "ASTARON",
                                "MAELTHOR",
                                "TYCHONIS",
                                "ZEPHYRION"),
                                order));
                break;
            case "GALAXY":
                break;
            default:
                break;
        }
    }

    // Monster species name generator
    public static void monster() {

        int order = 2;

        // Characteristics
        Map<Monster.Characteristics, List<String>> characteristicSyllables = new EnumMap<>(Monster.Characteristics.class);
        characteristicSyllables.put(Monster.Characteristics.HORNS, List.of("TOR","KOR","RHAM","BRAK","VORN"));
        characteristicSyllables.put(Monster.Characteristics.TUSKS, List.of("TUS","SKAR","REND","MOK","VRA"));
        characteristicSyllables.put(Monster.Characteristics.SPORES, List.of("SPOR","FUNG","MYC","PHTA","GRON"));
        characteristicSyllables.put(Monster.Characteristics.VENOM, List.of("VEN","NOC","ZYR","HISS","TOX"));
        characteristicSyllables.put(Monster.Characteristics.CLAWS, List.of("CLA","RAK","FANG","SHAR","TAL"));
        characteristicSyllables.put(Monster.Characteristics.MANDIBLES, List.of("MAN","GNAW","JAW","CRUN","BITE"));
        characteristicSyllables.put(Monster.Characteristics.FINS, List.of("FIN","FLOP","GLID","SWIM","WAV"));
        characteristicSyllables.put(Monster.Characteristics.FRILLS, List.of("FRIL","RAIM","VOR","SHAR","ZIL"));
        characteristicSyllables.put(Monster.Characteristics.ANTENNAE, List.of("ANT","SENS","SPI","LUM","TIK"));
        characteristicSyllables.put(Monster.Characteristics.SHELL, List.of("SHEL","ARM","CARP","CRUST","PLAT"));
        characteristicSyllables.put(Monster.Characteristics.BEAK, List.of("BEK","KLIK","CRAK","PEK","TOK"));
        characteristicSyllables.put(Monster.Characteristics.FANGS, List.of("FANG","TETH","SNAP","GNAW","MAW"));
        characteristicSyllables.put(Monster.Characteristics.WHISKERS, List.of("WHIS","VIBR","SENS","FEEL","TIK"));
        characteristicSyllables.put(Monster.Characteristics.TONGUE, List.of("TONG","LASH","FLEK","STIK","SLUR"));
        characteristicSyllables.put(Monster.Characteristics.BLUBBER, List.of("BLUB","FAT","LARD","GRIS","PLON"));
        characteristicSyllables.put(Monster.Characteristics.TENTACLES, List.of("TENT","SQU","WRIT","KRAK","LASH"));
        characteristicSyllables.put(Monster.Characteristics.MUCOUS, List.of("MUC","SLIM","GLOP","OOZ","VIS"));
        characteristicSyllables.put(Monster.Characteristics.TRANSLUCENT, List.of("GLOS","PHA","SHEE","LUM","EID"));
        characteristicSyllables.put(Monster.Characteristics.BIOLUMINESCENT, List.of("BIO","LUM","PHOS","GLEA","RAY"));
        characteristicSyllables.put(Monster.Characteristics.ELECTROGENESIS, List.of("ZAP","VOL","ELEC","SPAR","ARC"));
        characteristicSyllables.put(Monster.Characteristics.PYROGENESIS, List.of("PYR","IGNI","FLAR","BLAZ","CIND"));
        characteristicSyllables.put(Monster.Characteristics.HYDROGENESIS, List.of("HYD","AQUA","TORR","SURG","WAV"));
        characteristicSyllables.put(Monster.Characteristics.MONOCULAR, List.of("MONO","OCU","EYE","GLIM","GAZE"));

        // Instincts
        Map<Monster.Instinct, List<String>> instinctSyllables = new EnumMap<>(Monster.Instinct.class);
        instinctSyllables.put(Monster.Instinct.CAUTIOUS, List.of("SKIT","HUSH","WHIS","SLINK","VIG"));
        instinctSyllables.put(Monster.Instinct.PROTECTIVE, List.of("GUAR","WARD","SHEL","MOTH","DEF"));
        instinctSyllables.put(Monster.Instinct.HOSTILE, List.of("SNAR","GROWL","FANG","HISS","RAGE"));
        instinctSyllables.put(Monster.Instinct.SNEAKY, List.of("SHAD","NIB","PEEK","LURK","CREE"));
        instinctSyllables.put(Monster.Instinct.LAZY, List.of("NAP","DROO","SNOO","DOZ","LOAF"));
        instinctSyllables.put(Monster.Instinct.SCAVENGER, List.of("SCRA","PICK","BONE","RUM","DIG"));
        instinctSyllables.put(Monster.Instinct.PACK_HUNTER, List.of("HUNT","CHAS","SWAR","GRUP","PRED"));
        instinctSyllables.put(Monster.Instinct.HERD, List.of("HERD","FLOK","GATH","MOB","CLUS"));
        instinctSyllables.put(Monster.Instinct.HIVE_MIND, List.of("HIV","SWAR","CONN","MIND","UNIS"));
        instinctSyllables.put(Monster.Instinct.HIBERNATOR, List.of("HIB","SLEE","DEN","BURR","DORM"));
        instinctSyllables.put(Monster.Instinct.NOCTURNAL, List.of("NIGHT","ECL","MOON","DUSK","SHAD"));
        instinctSyllables.put(Monster.Instinct.GIBBERING, List.of("GIB","RAV","BAB","CHA","MAD"));

        // Species Types
        Map<Lifeform.Classification, List<String>> speciesSyllables = new EnumMap<>(Lifeform.Classification.class);
        speciesSyllables.put(Lifeform.Classification.ABYSSAL, List.of("XIL","AZR","OBL","THO","QUR"));
        speciesSyllables.put(Lifeform.Classification.BEAST, List.of("GRU","RAK","THOR","BUL","FANG"));
        speciesSyllables.put(Lifeform.Classification.ARTHROPOD, List.of("SCRA","CHIT","EXO","VEX","PIN"));
        speciesSyllables.put(Lifeform.Classification.CONSTRUCT, List.of("STEEL","CIR","GEAR","BLOCK","TRON"));
        speciesSyllables.put(Lifeform.Classification.ELEMENTAL, List.of("")); // could be any element
        speciesSyllables.put(Lifeform.Classification.HUMANOID, List.of("AL","MER","DRA","TAL","VAN"));
        speciesSyllables.put(Lifeform.Classification.PLANT, List.of("SPRO","LEAF","VINE","BRAN","FLOR"));
        speciesSyllables.put(Lifeform.Classification.UNKNOWN, List.of("ZOR","XEN","QIL","NYX","VOR"));


        // Markov Training Data
        for (Monster.Characteristics c : characteristicSyllables.keySet()) {
            for (Monster.Instinct i : instinctSyllables.keySet()) {
                for (Lifeform.Classification t : speciesSyllables.keySet()) {
                    List<String> trainingData = new ArrayList<>();

                    List<String> charSylls = characteristicSyllables.getOrDefault(c, List.of());
                    List<String> instSylls = instinctSyllables.getOrDefault(i, List.of());
                    List<String> typeSylls = speciesSyllables.getOrDefault(t, List.of());

                    // Generate multiple entries with different combinations
                    for (int n = 0; n < 12; n++) { // more repetitions → richer chain
                        StringBuilder sb = new StringBuilder();

                        int pattern = Math.getRandom().nextInt(6); // 0,1,2,3,4,5
                        List<List<String>> chosenLists = switch (pattern) {
                            case 0 -> Arrays.asList(typeSylls);              // 1
                            case 1 -> Arrays.asList(typeSylls);              // 1
                            case 2 -> Arrays.asList(charSylls);              // 2
                            case 3 -> Arrays.asList(typeSylls, charSylls);   // 1 + 2
                            case 4 -> Arrays.asList(typeSylls, instSylls);   // 1 + 3
                            default -> Arrays.asList(charSylls, instSylls);  // 2 + 3
                        };

                        // Randomize the order of chosenLists
                        if (Math.getRandom().nextBoolean()) Collections.reverse(chosenLists);

                        // Append 2–4 syllables per chosen list
                        for (List<String> lst : chosenLists) {
                            int numSylls = 1 + Math.getRandom().nextInt(3); // 1-3 syllables
                            for (int s = 0; s < numSylls; s++) {
                                sb.append(lst.get(Math.getRandom().nextInt(lst.size())));
                            }
                        }

                        trainingData.add(sb.toString());
                    }

                    Data.monster_generator.put(
                            Arrays.asList(t, c, i),
                            new MarkovChain(trainingData, order)
                    );
                }
            }
        }
    }

    public static void npc() {
        int order = 2;

        // HUMAN
        Data.npc_male_generator.put(Data.species.get(Species.Type.HUMAN), new MarkovChain(List.of(
                "RICHARD", "OLIVER", "ERIK", "ALEXANDER", "JULIUS", "LEONIDAS", "MARCUS", "OCTAVIAN",
                "ARTHUR", "ROLAND", "EDMUND", "GODFREY", "HENRIC"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.HUMAN), new MarkovChain(List.of(
                "CATHERINE", "ISABELLA", "ELEANOR", "VICTORIA", "JULIA",
                "HELENA", "MARGARET", "ANNE", "LUCIA", "ROSE"
        ), order));

        // GUARDIAN
        Data.npc_male_generator.put(Data.species.get(Species.Type.GUARDIAN), new MarkovChain(List.of(
                "AURIC", "AEGION", "PROTHAR", "SENTOR", "CUSTAR", "VIGMAR",
                "KYPHOR", "ARCION", "FORTAN", "WARDIC", "DEFYON"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.GUARDIAN), new MarkovChain(List.of(
                "AEGIRA", "SENTHA", "ARCYNE", "KYPRIA", "VIGORA",
                "CUSTRA", "PROTHIA", "GUARDIS", "FORTARA", "AURIONA"
        ), order));

        // IGNEOS
        Data.npc_male_generator.put(Data.species.get(Species.Type.IGNEOS), new MarkovChain(List.of(
                "DURGAL", "IGNARON", "PYRAX", "MAGLOR", "VOLCAR", "SMORAN",
                "CINDROS", "FLARIK", "OBSIDAR", "LAVORN", "TORVAL"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.IGNEOS), new MarkovChain(List.of(
                "IGNARA", "PYRIA", "VOLCINA", "ASHARA", "CINDRA",
                "MAGRIA", "FLARINA", "SMORA", "BLAZINA", "LAVARA"
        ), order));

        // PROTOZOAN
        Data.npc_male_generator.put(Data.species.get(Species.Type.PROTOZOAN), new MarkovChain(List.of(
                "XILTHOR", "ZORANIS", "NEXOR", "MYXAL", "CHORAX",
                "ZOATHE", "VORZAN", "KORZUL", "ETHMAR", "SPHINX"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.PROTOZOAN), new MarkovChain(List.of(
                "ZOANIS", "MYXORA", "CHORAYA", "XELMIRA", "NEXORA",
                "VORINA", "ETHZIA", "SPORINA", "ZORAYA", "CHOZMA"
        ), order));

        // ARKON
        Data.npc_male_generator.put(Data.species.get(Species.Type.ARKON), new MarkovChain(List.of(
                "ZANTHOR", "ARKYL", "RAKANOR", "XILMAR", "VORLAN",
                "DRAKOR", "ZULTHAR", "GORNAX", "THORAK", "KYTHON"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.ARKON), new MarkovChain(List.of(
                "ARKYRA", "ZANARA", "VORINA", "DRAXIA", "THORIA",
                "XILANA", "KORINA", "ZULMARIS", "RAKINA", "BRAXA"
        ), order));

        // NUMERIAN
        Data.npc_male_generator.put(Data.species.get(Species.Type.NUMERIAN), new MarkovChain(List.of(
                "OPOQ", "ALGARON", "SIGMAR", "LOGAR", "NUMOR", "QUADROS",
                "RHOZAN", "CALCIR", "FORMAR", "INTEGRYX", "PROXOR"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.NUMERIAN), new MarkovChain(List.of(
                "ALGIA", "SIGRIA", "LOGINA", "NUMINA", "QUADRIA",
                "RHOZA", "FORMINA", "CALCINA", "SECTORA", "DIVINA"
        ), order));

        // OKAMI
        Data.npc_male_generator.put(Data.species.get(Species.Type.OKAMI), new MarkovChain(List.of(
                "RAIKURO", "YORAMIR", "INAZOR", "SHINRAI", "HOKARI",
                "KAZURO", "TORANIS", "YOMIKU", "KAMORO", "HIKARI"
        ), order));
        Data.npc_female_generator.put(Data.species.get(Species.Type.OKAMI), new MarkovChain(List.of(
                "RAINA", "YORIKO", "AMATERA", "INAZUMI", "SHIORI",
                "KAZUMI", "HOKINA", "MIRAI", "TORAMI", "OKINA"
        ), order));
    }

    public static void civilization() {
        // Civilization syllables per Species
        int order = 2;

        Data.civilization_generator.put(Data.species.get(Species.Type.HUMAN), new MarkovChain(List.of("BYZANTOR","ROMAX","SAXNOR","MAYEGY","ANGLOTH","NORVAN","ATHENOR","CARTHOS","IMPERIX","TROJANOR","GOTHMAR","ETRURIX","HELLION","PERSYON","DANORIS","CELTAX","LATINOR","BRYTANIS","NORMAR","HUNNAX"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.GUARDIAN), new MarkovChain(List.of("AEGYS","PROTARI","VIGILOR","ARCUST","CUSTORIS","SENTHAR","SHIELDOR","GUARDON","DEFYON","AEGRON","WATCHAR","PALYON","ARCIVOR","BULWOR","PROXIS","SENTHARON","VIGYRON","AEGYMAR","KYPHORIS","FORTAR"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.IGNEOS), new MarkovChain(List.of("IGNAR","PYRONIS","VOLCAR","CINDAR","SMORAX","FLARYON","SCORVEX","LAVARON","ASHMOR","PYRAXIS","VOLCANIS","CRATERON","TORVUL","OBSIDOR","VULKARIS","MAGMARON","IGNIXOR","BLAZARON","FUMARIX"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.PROTOZOAN), new MarkovChain(List.of("XILTHOR","ZORANIS","AERXEN","NEXORIS","MYXOR","SPORAZ","PROTOXIL","VORZAN","CHOZMAR","ZOATHE","KORZUL","ETHZOAN","AMOZOR","XENMAR","ZOTHIR","ULMORIS","XELVOX","PROZARON","CHORAXIS","ZENOTH"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.ARKON), new MarkovChain(List.of("ZANTHOR","ARKYL","RAKANOR","XILMAR","VORLAN","DRAKONIS","KORVAX","RYZONAR","ZULTHAR","GORNAX","ZARNORIS","VULTOR","XANMAR","THORAK","KRAVON","ARKYNOR","ZARKUL","VORLARIS","KYTHON","BRAXOR"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.NUMERIAN), new MarkovChain(List.of("ALGARON","SIGMAR","LOGARA","NUMQUAD","CALCORA","SUMARON","RHOZAN","SIGMIS","DELTANOR","INTEGRYX","FORMARIS","EQUYON","QUADROS","LOGYMAR","RHOMAX","MATRION","AXIONOR","SECTORIS","DIVARON","PROXIMAR"),order));
        Data.civilization_generator.put(Data.species.get(Species.Type.OKAMI), new MarkovChain(List.of("RAIKAMI","YOROKA","INAMIR","SHINRAI","KAMIORA","HOKZAN","INUYOR","MIKORI","YORAMAR","OKATSU","RAIYON","HIKAMI","SHIRONA","AMATERA","KAZIOR","YORASHI","RAIYORI","INAZOR","TORAKAMI","YOMIRAN"),order));
    }

    public static String choose(String ... names) {
        List<String> list = new ArrayList<>(Arrays.asList(names));
        Math.shuffle(list);
        return list.subList(0, list.size()).get(0);
    }
}
