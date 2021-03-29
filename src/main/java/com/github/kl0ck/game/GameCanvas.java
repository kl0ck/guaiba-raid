package com.github.kl0ck.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameCanvas extends JPanel {

    GameObject testObject;

    public GameCanvas() {
        testObject = new GameObject(loadImage("/image.png")){};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;
        g.drawImage(testObject.img(), null, 0, 0);
    }

    BufferedImage loadImage(String imgPath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
