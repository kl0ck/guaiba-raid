package com.github.kl0ck;

import javax.swing.SwingUtilities;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

import com.github.kl0ck.game.GameWindow;

public class Main {

    public static final double FPS = 60;

    public static void main(String[] args) {
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

                Timer timer = new Timer(true);
                TimerTask task = new TimerTask(){
                    @Override
                    public void run() {
                        w.canvas().atualizar();
                    }
                };

                timer.schedule(task, 0, Math.round(1000 / FPS));
            }
        });
    }

}
