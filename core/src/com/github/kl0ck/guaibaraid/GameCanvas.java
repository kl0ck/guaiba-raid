package com.github.kl0ck.guaibaraid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.logging.Logger;

public class GameCanvas extends ApplicationAdapter {

    public static final Logger LOGGER = Logger.getLogger(GameCanvas.class.getName());
    
    private static final Color COR_RIO = Color.decode("#66B2FF");

    private GameObject player;
    private GameObject tiros[];
    private GameObject[] helis;
    private Texture cenario;
    private SpriteBatch batch;

    private boolean left, down, right, up;
    private double dx = 3d, dy = 3d;
    private boolean repaintInProgress = false;

    @Override
    public void create() {
        player = new GameObject("Aviao", new Texture(Gdx.files.internal("aviao.png")));
        tiros = new GameObject[]{
                new GameObject("Tiro1", new Texture(Gdx.files.internal("tiro.png"))),
                new GameObject("Tiro2", new Texture(Gdx.files.internal("tiro.png"))),
                new GameObject("Tiro3", new Texture(Gdx.files.internal("tiro.png"))),
        };
        helis = new GameObject[]{
                new GameObject("Heli1", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli2", new Texture(Gdx.files.internal("heli.png"))),
                new GameObject("Heli3", new Texture(Gdx.files.internal("heli.png"))),
        };
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 1.0f, 1.0f);

        batch.begin();
        batch.draw(player.img(), player.x(), player.y());
        int dx = 0;
        for (GameObject h : helis) {
            batch.draw(h.img(), h.x() + dx, h.y() + 400);
            dx += 100;
        }
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.rect().x -= 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.rect().x += 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.rect().y += 400 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.rect().y -= 400 * Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void dispose() {
        player.dispose();
        batch.dispose();
    }

}
