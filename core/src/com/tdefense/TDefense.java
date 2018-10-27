package com.tdefense;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entities.Entity;
import com.tdefense.entities.player.Player;
import com.tdefense.map.Map;

import java.util.HashMap;

public class TDefense extends Game {
	// lower level resources
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private AssetManager manager;

	private Map map;
	private Entity player;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        manager = new AssetManager();
        loadAssets(true);

        player = new Player(manager.get("player.png", Texture.class));
        map = new Map(manager.get("tile.atlas", TextureAtlas.class));

        camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
        camera.update();
	}

	private void loadAssets(boolean loadSynchronously) {
	    manager.load("player.png", Texture.class);
	    manager.load("tile.atlas", TextureAtlas.class);

        if (loadSynchronously) {
            manager.finishLoading();
        }
    }

    private void updateScene() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.addPosition(0, Constants.PLAYER_MOV_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.subPosition(0, Constants.PLAYER_MOV_SPEED);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.addPosition(Constants.PLAYER_MOV_SPEED, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.subPosition(Constants.PLAYER_MOV_SPEED, 0);
		}
    }

    private void drawScene() {
	    batch.setProjectionMatrix(camera.combined);

		batch.begin();
		map.draw(batch);
	    player.draw(batch);
		batch.end();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		updateScene();
		drawScene();
	}



	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
