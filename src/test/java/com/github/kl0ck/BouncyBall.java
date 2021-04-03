package com.github.kl0ck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** https://stackoverflow.com/questions/27681998/smooth-animation-in-java-for-fast-moving-objects */
public class BouncyBall {

    public static void main(String[] args) {
        new BouncyBall();
    }

    public BouncyBall() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ControlPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ControlPane extends JPanel {

        private JSlider speed;
        private JSlider quanity;

        private BallPitPane ballPitPane;

        public ControlPane() {
            setLayout(new BorderLayout());
            ballPitPane = new BallPitPane();
            add(ballPitPane);

            JPanel controls = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;

            speed = new JSlider(1, 100, 4);
            quanity = new JSlider(1, 100, 1);

            controls.add(new JLabel("Speed:"), gbc);
            gbc.gridy++;
            controls.add(new JLabel("Quanity:"), gbc);

            gbc.gridx++;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            controls.add(speed, gbc);
            gbc.gridy++;
            controls.add(quanity, gbc);
            add(controls, BorderLayout.SOUTH);

            speed.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ballPitPane.setSpeed(speed.getValue());
                }
            });

            quanity.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ballPitPane.setQuanity(quanity.getValue());
                }
            });
        }

    }

    public class BallPitPane extends JPanel {

        private List<Ball> balls;
        private int speed;

        public BallPitPane() {
            balls = new ArrayList<>(25);
            setSpeed(2);
            setQuanity(1);

            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Ball ball : balls) {
                        ball.update(getWidth(), speed);
                    }
                    repaint();
                }
            });
            timer.start();
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public void setQuanity(int quanity) {

            while (balls.size() > quanity) {
                balls.remove(0);
            }
            while (balls.size() < quanity) {
                int radius = 4 + (int) (Math.random() * 48);
                Ball ball = new Ball(
                        randomColor(),
                        (int) Math.abs(Math.random() * getWidth() - radius),
                        (int) Math.abs(Math.random() * getHeight() - radius),
                        radius
                );
                balls.add(ball);
            }

        }

        protected Color randomColor() {

            int red = (int) Math.abs(Math.random() * 255);
            int green = (int) Math.abs(Math.random() * 255);
            int blue = (int) Math.abs(Math.random() * 255);

            return new Color(red, green, blue);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            for (Ball ball : balls) {
                ball.paint(g2d);
            }
            g2d.dispose();
        }

        public class Ball {

            private Color color;
            private int x;
            private int y;
            private int radius;
            private int delta;

            public Ball(Color color, int x, int y, int radius) {
                this.color = color;
                this.x = x;
                this.y = y;
                this.radius = radius;
                delta = Math.random() > 0.5 ? 1 : -1;
            }

            public void update(int width, int speed) {
                x += speed * delta;
                if (x + radius >= width) {
                    x = width - radius;
                    delta *= -1;
                } else if (x < 0) {
                    x = 0;
                    delta *= -1;
                }
            }

            public void paint(Graphics g) {
                g.setColor(color);
                g.fillOval(x, y, radius, radius);
            }

        }

    }

}
