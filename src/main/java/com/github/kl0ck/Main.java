package com.github.kl0ck;

import javax.swing.SwingUtilities;

import com.github.kl0ck.game.GameWindow;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow w = new GameWindow();
                w.setVisible(true);
            }
        });
    }

}
