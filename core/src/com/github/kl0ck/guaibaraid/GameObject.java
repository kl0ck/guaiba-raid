package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Dimension;
import java.util.Objects;

public class GameObject {

    private final String name;
    private final Texture img;
    private final Rectangle rect;

    public GameObject(String name, Texture img) {
        this.name = Objects.requireNonNull(name);
        this.img = Objects.requireNonNull(img);
        this.rect = new Rectangle(0, 0, img.getWidth(), img.getHeight());
    }

    public String name() {
        return name;
    }

    public Texture img() {
        return img;
    }

    public Dimension size() {
        return new Dimension(img.getWidth(), img.getHeight());
    }

    public int x() {
        return Math.round(rect.x);
    }

    public int y() {
        return Math.round(rect.y);
    }

    public int w() {
        return img.getWidth();
    }

    public int h() {
        return img.getHeight();
    }

    public Rectangle rect() {
        return rect;
    }

    /** Delta X: deslocamento no eixo X. */
    public void dx(double dx) {
        rect.x += dx;
    }

    /** Delta Y: deslocamento no eixo Y. */
    public void dy(double dy) {
        rect.y += dy;
    }

    public int centerDX() {
        return (int) Math.round(w() / 2d);
    }

    public int centerDY() {
        return (int) Math.round(h() / 2d);
    }

    @Override
    public String toString() {
        return name + "[x=" + rect.x + ", y=" + rect.y + ", w=" + img.getWidth() + ", h=" + img.getHeight() + "]";
    }

    public void dispose() {
        img.dispose();
    }

}
