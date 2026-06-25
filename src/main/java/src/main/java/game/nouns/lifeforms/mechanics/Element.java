package src.main.java.game.nouns.lifeforms.mechanics;

public class Element {

    public enum Category {   // TODO: effects "theme" of lifeform: coloring, weaknesses, and attacks ?
        // certain elemental lifeforms should only exist on specific planet Categories
        NONE,   // All
        FIRE,   // Lava
        WATER,  // Oceanic / Jungle
        WIND,   // Desert / Desolate
        EARTH,  // Lava / Terran
        ICE,    // Arctic
        PLANT,  // Jungle / Terran
        DARK,   // Oceanic / Arctic / Desolate
        SOLAR   // Desert
    }

    Double[][] typeMap = new Double[][]{
        // ATTACK -----------------------------------------------   --- DEFENSE
        // NONE  FIRE  WATER WIND  EARTH ICE   PLANT DARK  SOLAR              |
         { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0 },   // NONE---|
         { 1.0,  1.0,  2.0,  1.0,  2.0,  0.5,  0.5,  1.0,  1.0 },   // FIRE---|
         { 1.0,  0.5,  1.0,  0.5,  1.0,  2.0,  2.0,  1.0,  1.0 },   // WATER--|
         { 1.0,  1.0,  1.0,  2.0,  2.0,  0.5,  0.5,  1.0,  1.0 },   // WIND---|
         { 1.0,  0.5,  2.0,  0.5,  1.0,  2.0,  1.0,  1.0,  1.0 },   // EARTH--|
         { 1.0,  2.0,  0.5,  1.0,  0.5,  1.0,  2.0,  1.0,  1.0 },   // ICE----|
         { 1.0,  2.0,  0.5,  2.0,  0.5,  1.0,  1.0,  1.0,  1.0 },   // PLANT--|
         { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5,  2.0 },   // DARK---|
         { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  2.0,  0.5 },   // SOLAR--|
    };

    // chart:
    // 1.0 = normal
    // 2.0 = super effective
    // 0.5 = not very effective

    // return the effectiveness of an attack of type element versus the def of another element
    public Double effectiveness(Category attack, Category defense) {
        return typeMap[attack.ordinal()][defense.ordinal()];
    }

}
