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
import com.tdefense.map.Coordinate;
import com.tdefense.map.Map;
import com.tdefense.logging.Logging;
import com.tdefense.world.WorldRenderer;
import com.tdefense.world.world_controller.WorldController;

public class TDefense extends Game {
    private static final String TAG = TDefense.class.getSimpleName();
	// lower level resources
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private AssetManager manager;
	private Logging logging;

	// game related resources
	private Map map;
	private Entity player;
	private Entity enemy;

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	@Override
	public void create () {
		manager = new AssetManager();
        loadAssets(true);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        player = new Player(manager.get("player.png", Texture.class));
        map = new Map(manager.get("tile.atlas", TextureAtlas.class));
        enemy = new Enemy(manager.get("enemy.png", Texture.class));

		camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

        Coordinate coordinate = map.getPathStartCoordinate();
        enemy.setPositon(coordinate.x, coordinate.y);
	}

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        updateScene();
        drawScene();
    }

    private void updateScene() {
		// player controls logic
		if (Gdx.input.isKeyPressed(Input.Keys.W)) player.setPositonY(player.getPosY() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.S)) player.setPositonY(player.getPosY() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.A)) player.setPositonX(player.getPosX() - Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.D)) player.setPositonX(player.getPosX() + Constants.PLAYER_MOV_SPEED * Gdx.graphics.getDeltaTime());

		enemy.setPositonY(enemy.getPosY() - (Constants.ENEMY_MOV_SPEED * Gdx.graphics.getDeltaTime()));
	}

	// TODO stworzyć klasę dla klas dziedziczących od Entity. Wzorzec projektowy / entity component system?
    private void drawScene() {
	batch.begin();
		map.draw(batch);
	    enemy.draw(batch);
        player.draw(batch);
	batch.end();
    }

    /**
     *{@link AssetManager} can stop all code execution to load assets synchronously (on the same thread). This ensures
     * you that all assets have been loaded.
     * @param synchronously {@code true}, for synchronous asset load
     */
    private void loadAssets(boolean synchronously) {
        manager.load("tile.atlas", TextureAtlas.class);
        manager.load("player.png", Texture.class);
        manager.load("enemy.png", Texture.class);

        if (synchronously) { manager.finishLoading(); }
    }

	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
