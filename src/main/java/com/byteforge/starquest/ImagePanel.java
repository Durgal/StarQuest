package com.byteforge.starquest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private Image portrait;

    public ImagePanel() {
        try {
            portrait = new ImageIcon("src/main/resources/img/igeos.png").getImage();
            if (portrait != null) {
                // Set the panel size to match the image
                setPreferredSize(new Dimension(portrait.getWidth(this), portrait.getHeight(this)));
                setOpaque(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (portrait != null) {
            g.drawImage(portrait, 0, 0, this);
        } else {
            g.drawString("No image loaded", 10, 20);
        }
    }
}
