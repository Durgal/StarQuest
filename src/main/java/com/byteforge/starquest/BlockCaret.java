/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.byteforge.starquest;

import javax.swing.text.*;
import java.awt.*;

public class BlockCaret extends DefaultCaret {

    public BlockCaret() {
        setBlinkRate(500); // standard blink rate
    }

    @Override
    protected synchronized void damage(Rectangle r) {
        if (r == null) return;
        x = r.x;
        y = r.y;
        height = r.height;
        width = Math.max(r.width, getDefaultWidth());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        JTextComponent comp = getComponent();
        if (comp == null) return;

        // Let DefaultCaret handle visibility toggling (blink timer)
        if (!isVisible() || !comp.hasFocus()) return;

        try {
            Rectangle r = comp.modelToView(getDot());
            if (r == null) return;

            int width = Math.max(r.width, getDefaultWidth());
            Color caretColor = comp.getCaretColor();
            if (caretColor == null) caretColor = Color.BLACK;

            g.setColor(caretColor);
            g.fillRect(r.x, r.y, width, r.height);

            // Update damaged area for blinking
            if (x != r.x || y != r.y || height != r.height) {
                damage(r);
            }

        } catch (BadLocationException e) {
            // ignore
        }
    }

    private int getDefaultWidth() {
        JTextComponent comp = getComponent();
        if (comp == null) return 8;
        FontMetrics fm = comp.getFontMetrics(comp.getFont());
        return fm.charWidth('M');
    }
}