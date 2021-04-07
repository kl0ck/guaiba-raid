package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class GameCanvas extends ApplicationAdapter {

    public static final Logger LOGGER = Logger.getLogger(GameCanvas.class.getName());
    
    private static final Color COR_RIO = Color.decode("#66B2FF");

    private Texture aviao;
    private Rectangle aviaoRect = new Rectangle(0,0,66,64);
    private Texture cenario;
    private SpriteBatch batch;
    private boolean left, down, right, up;
    private double dx = 3d, dy = 3d;
    private boolean repaintInProgress = false;

    @Override
    public void create() {
        aviao = new Texture(Gdx.files.internal("plane.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 1.0f, 1.0f);

        batch.begin();
        batch.draw(aviao, aviaoRect.x, aviaoRect.y);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            aviaoRect.x -= 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            aviaoRect.x += 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            aviaoRect.y += 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            aviaoRect.y -= 400 * Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void dispose() {
        aviao.dispose();
        batch.dispose();
    }

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
                //g.translate(window.getWidth() / 2d, window.getHeight() / 2d);
                //desenharCenario(g);
                //desenharAviao(g);

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

//    private void desenharCenario(Graphics2D g) {
//        Rectangle rect = window.getBounds();
//        int x = -(rect.width / 2) -10;
//        int y = -(rect.height / 2) -10;
//        int w = rect.width + 10;
//        int h = rect.height + 10;
//
//        if (cenario == null) {
//            BufferedImage img = (BufferedImage) window.createImage(w, h);
//            Graphics2D bg = (Graphics2D) img.getGraphics();
//            bg.setColor(COR_RIO);
//            bg.fillRect(0, 0, w, h);
//            //bg.clearRect(-(rect.width / 2) -10, -(rect.height / 2) -10, rect.width + 10, rect.height + 10);
//            cenario = new GameObject("Cenário", img);
//        }
//
//        g.drawImage(cenario.img(), null, x, y);
//    }
//
//    private void desenharAviao(Graphics2D g) {
//        g.drawImage(aviao.img(), null, aviao.x() - aviao.centerDX(), aviao.y() - aviao.centerDY());
//    }
//
//    public void atualizar() {
//        // Atualiza posições dos objetos na tela a cada X milissegundos.
//        atualizarPosicaoAviao();
//
//        repaint();
//    }

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

//    public void keyTyped(KeyEvent e) {
//    }
//
//    private BufferedImage loadImage(String imgPath) {
//        try {
//            return ImageIO.read(getClass().getResourceAsStream(imgPath));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void atualizarPosicaoAviao() {
//        if (left) {
//            aviao.dx(-dx);
//        }
//
//        if (right) {
//            aviao.dx(+dx);
//        }
//
//        if (up) {
//            aviao.dy(-dy);
//        }
//
//        if (down) {
//            aviao.dy(+dy);
//        }
//    }
    
}
