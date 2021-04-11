package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.kl0ck.guaibaraid.utils.Sync;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

public class GameCanvas extends ApplicationAdapter {

    public static final Logger LOGGER = Logger.getLogger(GameCanvas.class.getName());
    
    private static final Color COR_RIO = Color.decode("#66B2FF");

    private GameObject player;
    private GameObject tiros[];
    private int nextTiro = 0;
    private GameObject[] helis;
    private Texture cenario;
    private SpriteBatch batch;
    private Sync sync;

    @Override
    public void create() {
        player = new GameObject("Aviao", new Texture(Gdx.files.internal("aviao.png")));
        player.setX(800/2f - player.centerDX());

        tiros = new GameObject[]{
                new GameObject("Tiro1", new Texture(Gdx.files.internal("tiro.png")), false),
                new GameObject("Tiro2", new Texture(Gdx.files.internal("tiro.png")), false),
        };

        helis = new GameObject[]{
                new GameObject("Heli1", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli2", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli3", new Texture(Gdx.files.internal("heli.png"))),
        };

        batch = new SpriteBatch();

        sync = new Sync();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 1.0f, 1.0f);

        sync.sync(60);

        batch.begin();
        int dx = 0;
        for (GameObject h : helis) {
            batch.draw(h.img(), 100 + h.x() + dx, 500);
            dx += 200;
        }
        for (GameObject t : tiros) {
            if (t.isVisible()) {
                batch.draw(t.img(), t.x(), t.y());
            }
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

        moverTiros();
    }

    void atirar() {
        GameObject tiro = tiros[nextTiro];

        if (tiro.isVisible()) {
            return;
        }

        tiro.setVisible(true);

        tiro.setX(player.x() + player.centerDX());
        tiro.setY(player.y() + player.h());

        nextTiro++;

        if (nextTiro == tiros.length) {
            nextTiro = 0;
        }
    }

    void moverTiros() {
        for (GameObject tiro : tiros) {
            if (!tiro.isVisible()) {
                continue;
            }
            if (tiro.y() > 1000) {
                tiro.setVisible(false);
                continue;
            }
            tiro.dy(1000 * Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        player.dispose();
        batch.dispose();
    }

}
