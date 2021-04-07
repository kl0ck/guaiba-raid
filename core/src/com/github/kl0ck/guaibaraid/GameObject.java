package com.github.kl0ck.guaibaraid;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameObject {

    private final String name;
    private final BufferedImage img;
    private double x=0, y=0;

    public GameObject(String name, BufferedImage img) {
        this.name = name;
        this.img = img;
    }

    public String name() {
        return name;
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
        return (int) Math.round(x);
    }

    public int y() {
        return (int) Math.round(y);
    }

    public int w() {
        return img.getWidth();
    }

    public int h() {
        return img.getHeight();
    }

    /** Delta X: deslocamento no eixo X. */
    public void dx(double dx) {
        x += dx;
    }

    /** Delta Y: deslocamento no eixo Y. */
    public void dy(double dy) {
        y += dy;
    }

    public int centerDX() {
        return (int) Math.round(w() / 2d);
    }

    public int centerDY() {
        return (int) Math.round(h() / 2d);
    }

    @Override
    public String toString() {
        return name + "[x=" + x + ", y=" + y + ", w=" + img.getWidth() + ", h=" + img.getHeight() + "]";
    }

}
