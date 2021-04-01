package com.github.kl0ck.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameCanvas extends JPanel implements ActionListener, KeyListener {

    private GameObject aviao;
    private boolean left, down, right, up;
    private Timer timer;

    public GameCanvas() {
        addKeyListener(this);

        // Precisa receber foco senão as teclas não funcionam.
        setFocusable(true);

        setBackground(Color.decode("#66B2FF"));

        aviao = new GameObject(loadImage("/plane.png")){};

        // O timer executa o actionPerformed() a cada X milissegundos.
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        desenhar(g);
    }

    private void desenhar(Graphics2D g) {
        g.drawImage(aviao.img, null, aviao.x, aviao.y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza posições dos objetos na tela a cada X milissegundos.

        atualizarPosicaoAviao();

        // TODO

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            // esquerda
            left = true;

        } else if (code == KeyEvent.VK_S) {
            // baixo
            down = true;

        } else if (code == KeyEvent.VK_D) {
            // direita
            right = true;

        } else if (code == KeyEvent.VK_W) {
            // cima
            up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A) {
            // esquerda
            left = false;

        } else if (code == KeyEvent.VK_S) {
            // baixo
            down = false;

        } else if (code == KeyEvent.VK_D) {
            // direita
            right = false;

        } else if (code == KeyEvent.VK_W) {
            // cima
            up = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    BufferedImage loadImage(String imgPath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarPosicaoAviao() {
        if (left) {
            System.out.println("left");
            aviao.dx(-1);
        }

        if (down) {
            // FIXME: INVERTER O EIXO Y
            System.out.println("down");
            aviao.dy(-1);
        }

        if (right) {
            System.out.println("right");
            aviao.dx(+1);
        }

        if (up) {
            System.out.println("up");
            aviao.dy(+1);
        }
    }
    
}
