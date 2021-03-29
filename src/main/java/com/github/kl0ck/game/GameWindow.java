package com.github.kl0ck.game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    GameCanvas canvas;

    public GameWindow() {
        setTitle("Guaíba Raid");
        setSize(new Dimension(800, 600));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        canvas = new GameCanvas();
        add(canvas);
    }
    
}
