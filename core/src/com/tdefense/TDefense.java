package com.tdefense;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.entity_system.Entity;
import com.tdefense.entity_system.enemy.Enemy;
import com.tdefense.entity_system.player.Player;
import com.tdefense.map.Map;
import com.tdefense.logging.Logging;

public class TDefense extends Game {
	// lower level com.tdefense.resource
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private AssetManager manager;
	private Logging logging;

	private Map map;
	private Entity player;
	private Entity enemy;

	private float timeSeconds = 1000;
    private float period = 1f;
    private boolean isActionFinished;

	@Override
	public void create () {
		manager = new AssetManager();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();

        loadAssets(true);
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
		timeSeconds = 0f;
		period = 1f;
		isActionFinished = true;

        player = new Player(manager.get("player.png", Texture.class));
        map = new Map(manager.get("tile.atlas", TextureAtlas.class));
        enemy = new Enemy(manager.get("enemy.png", Texture.class));

        enemy.setPositonX(enemy.getPosX() + Constants.ENEMY_MOV_SPEED);
        enemy.setPositonY(enemy.getPosY() + Constants.ENEMY_MOV_SPEED);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	/**
	 *{@link AssetManager} can stop all code execution to load assets synchronously. This ensures you that all assets
     * have been loaded.
	 * @param synchronously {@code true}, for synchronous asset load
	 */
	private void loadAssets(boolean synchronously) {
	    manager.load("tile.atlas", TextureAtlas.class);
		manager.load("player.png", Texture.class);
		manager.load("enemy.png", Texture.class);

        if (synchronously) {
            manager.finishLoading();
        }
    }

    private void updateScene() {
		// TODO
		// player controls logic
		if (Gdx.input.isKeyPressed(Input.Keys.W)) player.setPositonY(player.getPosY() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.S)) player.setPositonY(player.getPosY() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.A)) player.setPositonX(player.getPosX() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.D)) player.setPositonX(player.getPosX() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());

		if (isActionFinished){
            enemy.setPositonX(enemy.getPosX() + Constants.ENEMY_MOV_SPEED);
		}
	}

	// TODO stworzyć klasę dla klas dziedziczących od Entity
	// wzorzec projektowy?
    private void drawScene() {
		batch.begin();
		map.draw(batch);
	    player.draw(batch);
        if (isActionFinished) {
            enemy.draw(batch);
        }
		batch.end();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

        timeSeconds += Gdx.graphics.getRawDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            isActionFinished = true;

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    try { this.wait(1000); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            });
        } else {
            isActionFinished = false;
        }

		updateScene();
		drawScene();
	}

	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
//		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			player.setPositonY(player.getPosY() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//			player.setPositonY(player.getPosY() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//			player.setPositonX(player.getPosX() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//			player.setPositonX(player.getPosX() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
//		}
	}
}
