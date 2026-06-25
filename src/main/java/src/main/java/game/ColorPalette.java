package src.main.java.game;

import src.main.java.text_based_mechanics.parts_of_speech.adjective.Color;

import java.util.Set;

public class ColorPalette {

    private Set<Color> colors;

    public ColorPalette(Set colors) {
        this.colors = colors;
    }

    public Set<Color> get() {
        return colors;
    }

}
