package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.kl0ck.guaibaraid.utils.Sync;

import java.util.logging.Logger;

public class GameCanvas extends ApplicationAdapter {

    public static final Logger LOGGER = Logger.getLogger(GameCanvas.class.getName());

    private GameObject player;
    private GameObject tiro;
    private Sound tiroSound;
    private GameObject[] helis;
    //private Texture cenario;
    private SpriteBatch batch;
    private Sync sync;

    @Override
    public void create() {
        player = new GameObject("Aviao", new Texture(Gdx.files.internal("aviao.png")));
        player.setX(800/2f - player.centerDX());

        tiro = new GameObject("Tiro", new Texture(Gdx.files.internal("tiro.png")), false);
        tiroSound = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));

        helis = new GameObject[]{
                new GameObject("Heli1", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli2", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli3", new Texture(Gdx.files.internal("heli.png"))),
        };

        int dx = 0;
        for (GameObject h : helis) {
            h.setX(100 + dx);
            h.setY(500);
            dx += 200;
        }

        batch = new SpriteBatch();

        sync = new Sync();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 1.0f, 1.0f);

        batch.begin();
        for (GameObject h : helis) {
            if (h.isVisible()) {
                batch.draw(h.img(), h.x(), h.y());
            }
        }
        if (tiro.isVisible()) {
            batch.draw(tiro.img(), tiro.x(), tiro.y());
        }
        batch.draw(player.img(), player.x(), player.y());
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.dx(-400 * Gdx.graphics.getDeltaTime());
            if (player.x() < 0) {
                player.setX(0);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.dx(400 * Gdx.graphics.getDeltaTime());
            if (player.x() > 800 - player.w()) {
                player.setX(800 - player.w());
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.dy(400 * Gdx.graphics.getDeltaTime());
            if (player.y() > 600 - player.h()) {
                player.setY(600 - player.h());
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.dy(-400 * Gdx.graphics.getDeltaTime());
            if (player.y() <= 0) {
                player.setY(0);
            }
        }

        // Tiros
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            atirar();
        }

        moverTiro();

        sync.sync(60);
    }

    void atirar() {
        if (tiro.isVisible()) {
            return;
        }

        tiro.setVisible(true);
        tiro.setX(player.x() + player.centerDX());
        tiro.setY(player.y() + player.h());
        tiroSound.play();
    }

    void moverTiro() {
        if (!tiro.isVisible()) {
            return;
        }

        if (tiro.y() > 1000) {
            tiro.setVisible(false);
            return;
        }

        tiro.dy(1000 * Gdx.graphics.getDeltaTime());

        for (GameObject h : helis) {
//                System.out.println(tiro.rect() + ", " + h.rect());
//                System.out.println("overlap 1: " + tiro.rect().overlaps(h.rect()));
//                System.out.println("overlap 2: " + h.rect().overlaps(tiro.rect()));
            if (h.isVisible() && tiro.rect().overlaps(h.rect())) {
                tiro.setVisible(false);
                h.setVisible(false);
            }
        }
    }

    @Override
    public void dispose() {
        player.dispose();
        tiro.dispose();
        tiroSound.dispose();
        for (GameObject h : helis) {
            h.dispose();
        }
        batch.dispose();
    }

}
