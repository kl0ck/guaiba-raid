package com.github.kl0ck.game;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private GameCanvas canvas;

    public GameWindow(GraphicsConfiguration gc) {
        super(gc);
        setTitle("Guaíba Raid");
        setIgnoreRepaint(true);
        setSize(new Dimension(800, 600));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new GameCanvas(this);
        add(canvas);
    }

    public GameCanvas canvas() {
        return canvas;
    }
    
}
