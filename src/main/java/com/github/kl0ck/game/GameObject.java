package com.github.kl0ck.game;

import java.awt.*;
import java.awt.image.*;

public abstract class GameObject {

    final BufferedImage img;

    public GameObject(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage img() {
        return img;
    }

    public Graphics2D g() {
        return (Graphics2D) img.getGraphics();
    }

    public Dimension size() {
        return new Dimension(img.getWidth(), img.getHeight());
    }
    
}
