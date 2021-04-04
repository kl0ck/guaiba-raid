package com.github.kl0ck;

import javax.swing.SwingUtilities;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

import com.github.kl0ck.game.GameWindow;

public class Main {

    public static final double FPS = 200;
    public static final long FRAME_TIME = Math.round(1000 / FPS);

    public static long lastMs = System.currentTimeMillis();

    public static void main(String[] args) {
        System.out.println("FPS = " + FPS + ", frame time = " + FRAME_TIME + " ms");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice defaultScreen = graphics.getDefaultScreenDevice();

                GameWindow w = new GameWindow(defaultScreen.getDefaultConfiguration());
                // if (defaultScreen.isFullScreenSupported()) {
                //     w.setUndecorated(true);
                //     defaultScreen.setFullScreenWindow(w);
                // }
                w.setVisible(true);

                Runnable loop = new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            System.out.println("timer ms = " + (System.currentTimeMillis() - lastMs));
                            w.canvas().atualizar();
                            lastMs = System.currentTimeMillis();
                            try {
                                Thread.sleep(FRAME_TIME);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };
                Thread thread = new Thread(loop);
                thread.setDaemon(true);
                thread.start();
            }
        });
    }

}
