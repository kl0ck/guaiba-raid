package com.github.kl0ck.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameCanvas extends JPanel implements KeyListener {

    private static final Logger LOGGER = Logger.getLogger(GameCanvas.class.getName());
    
    private static final Color COR_RIO = Color.decode("#66B2FF");

    private final Window window;
    private GameObject aviao;
    private GameObject cenario;
    private boolean left, down, right, up;
    private double dx = 3d, dy = 3d;
    private boolean repaintInProgress = false;

    public GameCanvas(Window window) {
        this.window = Objects.requireNonNull(window);

        addKeyListener(this);

        // Precisa receber foco senão as teclas não funcionam.
        setFocusable(true);

        aviao = new GameObject("Avião", loadImage("/plane.png"));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (repaintInProgress) {
            System.out.println("skip repaint");
            return;
        }
        repaintInProgress = true;

        long paintStart = System.currentTimeMillis();

        // // https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
        // BufferStrategy strategy = getBufferStrategy();

        // // Render single frame
        // do {
        //     // The following loop ensures that the contents of the drawing buffer
        //     // are consistent in case the underlying surface was recreated
        //     do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                //Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
                Graphics2D g = (Graphics2D) graphics;
                //g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                //g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

                // Origem (0,0) no centro da tela.
                g.translate(window.getWidth() / 2d, window.getHeight() / 2d);
                desenharCenario(g);
                desenharAviao(g);

                // Dispose the graphics
                g.dispose();

                Toolkit.getDefaultToolkit().sync();

        //         // Repeat the rendering if the drawing buffer contents
        //         // were restored
        //     } while (strategy.contentsRestored());

        //     // Display the buffer
        //     strategy.show();

        //     // Repeat the rendering if the drawing buffer was lost
        // } while (strategy.contentsLost());

        //System.out.println("paint ms = " + (System.currentTimeMillis() - paintStart));

        repaintInProgress = false;
    }

    // @Override
    // protected void paintComponent(Graphics graphics) {
    //     super.paintComponent(graphics);

    //     Graphics2D g = (Graphics2D) graphics;

    //     g.translate(PONTO_PARTIDA.x, PONTO_PARTIDA.y);

    //     desenhar(g);

    //     //Toolkit.getDefaultToolkit().sync();

    //     System.out.println("paint ms = " + (System.currentTimeMillis() - lastPaintMs));
    //     lastPaintMs = System.currentTimeMillis();
    // }

    private void desenharCenario(Graphics2D g) {
        Rectangle rect = window.getBounds();
        int x = -(rect.width / 2) -10;
        int y = -(rect.height / 2) -10;
        int w = rect.width + 10;
        int h = rect.height + 10;

        if (cenario == null) {
            BufferedImage img = (BufferedImage) window.createImage(w, h);
            Graphics2D bg = (Graphics2D) img.getGraphics();
            bg.setColor(COR_RIO);
            bg.fillRect(0, 0, w, h);
            //bg.clearRect(-(rect.width / 2) -10, -(rect.height / 2) -10, rect.width + 10, rect.height + 10);
            cenario = new GameObject("Cenário", img);
        }

        g.drawImage(cenario.img(), null, x, y);
    }

    private void desenharAviao(Graphics2D g) {
        g.drawImage(aviao.img(), null, aviao.x() - aviao.centerDX(), aviao.y() - aviao.centerDY());
    }

    public void atualizar() {
        // Atualiza posições dos objetos na tela a cada X milissegundos.
        atualizarPosicaoAviao();

        repaint();
    }

    private BufferStrategy getBufferStrategy() {
        // https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
        BufferStrategy bufferStrategy = window.getBufferStrategy();
        if (bufferStrategy == null) {
            window.createBufferStrategy(2);
            bufferStrategy = window.getBufferStrategy();
            //System.out.println("BufferStrategy page flipping? " + bufferStrategy.getCapabilities().isPageFlipping());
        }
        return bufferStrategy;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            // esquerda
            left = true;

        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            // direita
            right = true;

        } else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            // cima
            up = true;

        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            // baixo
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            // esquerda
            left = false;

        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            // direita
            right = false;

        } else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            // cima
            up = false;

        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            // baixo
            down = false;

        } else if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private BufferedImage loadImage(String imgPath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void atualizarPosicaoAviao() {
        if (left) {
            aviao.dx(-dx);
        }

        if (right) {
            aviao.dx(+dx);
        }

        if (up) {
            aviao.dy(-dy);
        }

        if (down) {
            aviao.dy(+dy);
        }
    }
    
}
