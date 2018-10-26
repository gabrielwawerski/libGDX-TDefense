package com.tdefense;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entities.Entity;
import com.tdefense.entities.enemy.Enemy;
import com.tdefense.entities.player.Player;

import java.util.HashMap;

public class TDefense extends ApplicationAdapter implements InputProcessor {
	// lower level resources
    private OrthographicCamera camera;
	private SpriteBatch batch;

	// map resources
	private TextureAtlas tileAtlas;
	private TextureRegion dirt;
	private TextureRegion grass;

	// map starting x, y position
	private float mapStartingPointX;
	private float mapStartingPointY;

	// player resources
	private Player player;

	// input resources
	// hashmap for storing time between button presses
	private HashMap<Integer, Long> keyTime = new HashMap<Integer, Long>();


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
        camera.update();

        // map resources initialization
        tileAtlas = new TextureAtlas(Gdx.files.internal("tile.atlas"));
        dirt = tileAtlas.findRegion("dirt");
        grass = tileAtlas.findRegion("grass");

        // player resources initialization
        Texture playerTexture = new Texture(Gdx.files.internal("player.png"));
        player = new Player(playerTexture, new Sprite(playerTexture), new Vector2(Constants.MAP_SIZE_X / 2.f, Constants.MAP_SIZE_Y / 2.f));

	}

    private void updateScene() {
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			camera.zoom -= 0.02;
			camera.update();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
			camera.zoom += 0.02;
			camera.update();
		}

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

		//==============================================================================================================
		batch.begin();

	    drawMap();
	    batch.draw(player.getSprite(), player.getPosX(), player.getPosY());

		batch.end();
		//==============================================================================================================
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		updateScene();
		drawScene();
	}

	/**
	 * Draws the map using grass and dirt textures. Dirt is used as path that AI will traverse.
	 */
	private void drawMap() {
		for (int x = 0; x < Constants.MAP_SIZE_X; x++) {
			for (int y = 0; y < Constants.MAP_SIZE_Y; y++) {
				batch.draw(grass, x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
				// draw dirt path that ai will walk on
				if (x == 5) {
					batch.draw(dirt, x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
					if (y == 0) // TODO Fix setting map starting point
						setMapStartingPoint(x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
				}
			}
		}
	}

	/**
	 * Sets first tile coordinates of the dirt path (tile that enemies will spawn on).
	 */
	private void setMapStartingPoint(int x, int y) {
		mapStartingPointX = x;
		mapStartingPointY = y;
	}

	private float getMapStartingPointX() {
		return  mapStartingPointX;
	}

	private float getMapStartingPointy() {
		return  mapStartingPointY;
	}

	//region Region: InputProcessor interface specific methods
	@Override
	public boolean keyDown(int keycode) {
		keyTime.put(keycode, System.currentTimeMillis());
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	//endregion

	@Override
	public void dispose () {
		batch.dispose();
	}
}
