package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Dimension;
import java.util.Objects;

public class GameObject {

    private final String name;
    private final Texture img;
    private final Rectangle rect;
    private boolean visible;

    public GameObject(String name, Texture img) {
        this.name = Objects.requireNonNull(name);
        this.img = Objects.requireNonNull(img);
        rect = new Rectangle(0, 0, img.getWidth(), img.getHeight());
        visible = true;
    }

    public GameObject(String name, Texture img, boolean visible) {
        this(name, img);
        setVisible(visible);
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

    public float x() {
        return rect.x;
    }

    public float y() {
        return rect.y;
    }

    public void setX(float x) {
        rect.x = x;
    }

    public void setY(float y) {
        rect.y = y;
    }

    public float w() {
        return rect.width;
    }

    public float h() {
        return rect.height;
    }

    public Rectangle rect() {
        return rect;
    }

    /** Delta X: deslocamento no eixo X. */
    public void dx(float dx) {
        rect.x += dx;
    }

    /** Delta Y: deslocamento no eixo Y. */
    public void dy(float dy) {
        rect.y += dy;
    }

    public float centerDX() {
        return w() / 2f;
    }

    public float centerDY() {
        return h() / 2f;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean b) {
        visible = b;
    }

    @Override
    public String toString() {
        return name + "[x=" + rect.x + ", y=" + rect.y + ", w=" + img.getWidth() + ", h=" + img.getHeight() + "]";
    }

    public void dispose() {
        img.dispose();
    }

}
