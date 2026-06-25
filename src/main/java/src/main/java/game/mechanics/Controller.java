/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.game.mechanics;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;

public class Controller {
    private static final Set<Integer> heldKeys = Collections.synchronizedSet(new HashSet<>());
    private static final Set<Integer> pressedKeys = Collections.synchronizedSet(new HashSet<>());

    // Private constructor prevents instantiation
    private Controller() {}

    // --- Attach listener to a component ---
    public static void attachTo(JComponent component) {
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (!heldKeys.contains(key)) pressedKeys.add(key);
                heldKeys.add(key);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                heldKeys.remove(key);
                pressedKeys.remove(key);
            }
        });
    }

    // --- Query functions ---
    public static boolean anyKeyHeld() {
        return !heldKeys.isEmpty();
    }

    public static boolean anyKeyPressed() {
        return !pressedKeys.isEmpty();
    }

    public static boolean keyHeld(int keyCode) {
        return heldKeys.contains(keyCode);
    }

    public static boolean keyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }

    public static void clearPressed() {
        pressedKeys.clear();
    }
}