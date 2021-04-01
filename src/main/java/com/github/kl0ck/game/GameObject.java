package com.github.kl0ck.game;

import java.awt.*;
import java.awt.image.*;

public abstract class GameObject {

    final BufferedImage img;
    int x=0, y=0;

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

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void dx(int dx) {
        x += dx;
    }

    public void dy(int dy) {
        y += dy;
    }

}
