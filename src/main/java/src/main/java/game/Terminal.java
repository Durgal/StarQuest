/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game;

import src.main.java.game.mechanics.Controller;
import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.*;
import src.main.java.game.nouns.lifeforms.Player;
import src.main.java.game.nouns.lifeforms.mechanics.Attributes.Attribute;
/**
 *
 * @author Christopher
 */
public class Terminal {
    
    private static final int DEFAULT_TYPE_SPEED = 25;
    private static final int FAST_TYPE_SPEED = 5;
    
    private static final int MAIN_TERMINAL_WIDTH = 50;
    private static final int AUX_TERMINAL_WIDTH = 18;
    private static final int VIS_TERMINAL_WIDTH = 17;
    
    private static volatile Thread currentOutputThread;

    public static void showOutput(String input, JTextArea story, JTextArea command) {
        final String text = formatTextForOutput(input,MAIN_TERMINAL_WIDTH);

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> setInputEnabled(false, command));

            try {
                char[] chars = text.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    final char c = chars[i];
                    SwingUtilities.invokeLater(() -> story.append(String.valueOf(c)));

                    // If any key is pressed, reduce delay to almost zero for instant typing
                    int delay;
                    if (Controller.anyKeyPressed()) {
                        delay = 1;  // fast typing
                        Controller.clearPressed(); // consume press
                    } else if (Controller.anyKeyHeld()) {
                        delay = FAST_TYPE_SPEED;  // speed up while holding
                    } else {
                        delay = DEFAULT_TYPE_SPEED; // normal typing speed
                    }

                    Thread.sleep(delay);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> setInputEnabled(true, command));
            }

        }).start();
    }

    public static void showOutput(String input, JTextArea panel) {
        final String text = formatTextForOutput(input, AUX_TERMINAL_WIDTH);

        // Interrupt previous output thread if still running
        if (currentOutputThread != null && currentOutputThread.isAlive()) {
            currentOutputThread.interrupt();
        }

        // Create and start a new one
        Thread thread = new Thread(() -> {
            try {
                for (char c : text.toCharArray()) {
                    if (Thread.interrupted()) return; // stop immediately if interrupted

                    SwingUtilities.invokeLater(() -> panel.append(String.valueOf(c)));

                    int delay = Controller.anyKeyHeld() ? FAST_TYPE_SPEED : DEFAULT_TYPE_SPEED;
                    Thread.sleep(delay);
                }
            } catch (InterruptedException e) {
                // Graceful exit when interrupted
                Thread.currentThread().interrupt();
            }
        }, "TerminalOutputThread");

        currentOutputThread = thread;
        thread.start();
    }
    
    public static void showVisualText(String input, JTextArea panel) {
        new Thread(() -> {
        try {
            for (char c : input.toCharArray()) {
                SwingUtilities.invokeLater(() -> panel.append(String.valueOf(c)));
                Thread.sleep(FAST_TYPE_SPEED);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }).start();
    }
        
    private static void setInputEnabled(boolean enabled, JTextArea component) {
        Color enabled_color = Color.BLACK;
        Color disabled_color = new Color(230, 230, 230);
        component.setEditable(enabled);
        component.setBackground(enabled ? enabled_color : disabled_color);
    }
        
    private static String formatTextForOutput(String text, int width) {
            String[] inputLines = text.split("\n", -1); // preserve empty lines
    StringBuilder result = new StringBuilder();

    for (String inputLine : inputLines) {
        String[] words = inputLine.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) continue; // skip extra spaces

            int extraLength = (currentLine.length() == 0 ? 0 : 1) + word.length();

            // Wrap line if word won't fit
            if (currentLine.length() + extraLength > width-1) {
                result.append(" ");               // skipped first char
                result.append(currentLine.toString().stripTrailing());
                result.append("\n");
                currentLine.setLength(0);        // reset line
            }

            if (currentLine.length() > 0) currentLine.append(" ");
            currentLine.append(word);
        }

        // Append any remaining text in the current line
        if (currentLine.length() > 0) {
            result.append(" ");                   // skipped first char
            result.append(currentLine.toString().stripTrailing());
        }

        result.append("\n"); // preserve original newline between input lines
    }

    return result.toString(); // final newline preserved
    }
    
    public static void setLineSpacing(JTextArea textArea, float extraSpace) {
        textArea.setUI(new BasicTextAreaUI() {
            @Override
            public View create(Element elem) {
                return new ParagraphView(elem) {
                    @Override
                    protected void layout(int width, int height) {
                        super.layout(width, height);
                        for (int i = 0; i < getViewCount(); i++) {
                            View v = getView(i);
                            v.setSize(width, v.getPreferredSpan(View.Y_AXIS) + extraSpace);
                        }
                    }

                    @Override
                    public float getPreferredSpan(int axis) {
                        float span = super.getPreferredSpan(axis);
                        if (axis == View.Y_AXIS) {
                            span += extraSpace * getViewCount();
                        }
                        return span;
                    }
                };
            }
        });
    }

    public static void setPlayerMenu(Player player, JTextArea jTextCharacterDetails) {
        
        String CHARACTER_TEMPLATE = """
                                                                        C H A R A C T E R
                                          . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
                                          
                                             NAME    : <NA> LIFE . . . . <LI>    FACTION : <FA>
                                             SPECIES : <SP> ENERGY . . . <EN>    ORIGIN  : <BA>
                                             CLASS   : <CL> DEFENSE  . . <DE>    CREED   : <MO>
                                             LEVEL   : <LV>                      GENDER  : <GE>
                                    
                                    
                                             ATTRIBUTES                  SKILLS
                                                                      
                                             POWER . . . . <P>           MELEE  . . . . [1]
                                                                         BLOCK  . . . . [2]
                                             AGILITY . . . <A>           RANGED . . . . [3]
                                                                         STEALTH  . . . [4]
                                             VITALITY  . . <V>           MEDICINE . . . [5]
                                                                         SURVIVAL . . . [6]
                                             LOGIC . . . . <L>           TECHNOLOGY . . [7]
                                                                         LORE . . . . . [8]
                                             SPIRIT  . . . <S>           WILLPOWER  . . [9]
                                                                         INFLUENCE  . . [10]
                                          """;
        
        // Character Details
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<NA>", format(player.getName(),17));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<SP>", format(player.getSpecies(),17));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<CL>", format(player.getOccupation(),17));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<LV>", format(player.getLevel(),17));

        // Character Derived Attributes
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<LI>", format(player.getLife(),4));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<EN>", format(player.getEnergy(),4));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<DE>", format(player.getDefense(),4));
        
        // Character Personal Attributes
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<FA>", format(player.getFaction(),19));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<BA>", format(player.getBackground(),19));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<GE>", format(player.getGender(),7));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<MO>", format(player.getAlignment(),7));
        
        // Character Attributes
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<P>", format(player.getAttribute(Attribute.POWER),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<A>", format(player.getAttribute(Attribute.AGILITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<V>", format(player.getAttribute(Attribute.VITALITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<L>", format(player.getAttribute(Attribute.LOGIC),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("<S>", format(player.getAttribute(Attribute.SPIRIT),3));
        
        // Character Skills
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[1]", format(player.getAttribute(Attribute.POWER),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[2]", format(player.getAttribute(Attribute.AGILITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[3]", format(player.getAttribute(Attribute.VITALITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[4]", format(player.getAttribute(Attribute.LOGIC),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[5]", format(player.getAttribute(Attribute.SPIRIT),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[6]", format(player.getAttribute(Attribute.POWER),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[7]", format(player.getAttribute(Attribute.AGILITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[8]", format(player.getAttribute(Attribute.VITALITY),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[9]", format(player.getAttribute(Attribute.LOGIC),3));
        CHARACTER_TEMPLATE = CHARACTER_TEMPLATE.replace("[10]", format(player.getAttribute(Attribute.SPIRIT),3));
        
        jTextCharacterDetails.setText(CHARACTER_TEMPLATE);
    }
    
    private static String format(String input, int padding) {
        if (input == null) {
            input = "";
        }

        // If string is already N or more characters, return first N characters
        if (input.length() >= padding) {
            return input.substring(0, padding);
        }

        // Otherwise, pad with spaces
        StringBuilder sb = new StringBuilder(input);
        while (sb.length() < padding) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
