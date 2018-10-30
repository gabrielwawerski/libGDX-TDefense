package com.tdefense;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.world.entities.enemy.TestEnemy;
import com.tdefense.entity_system.entity.EnemyEntity;
import com.tdefense.world.entities.player.Player;
import com.tdefense.entity_system.entity.PlayerEntity;
import com.tdefense.world.map.Map;
import com.tdefense.tools.logging.Logger;
import com.tdefense.tools.Constants;

public class TDefense extends Game {
    private static final String TAG = TDefense.class.getSimpleName();
	// lower level resources
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private AssetManager manager;
	private Logger logger;

	// game related resources
	private Map map;
	private PlayerEntity player;
	private EnemyEntity testEnemy;

	@Override
	public void create () {
	    initializeGraphics();
		initializeWorld();
		camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.6f, Gdx.graphics.getHeight() / 1.6f);
		camera.update();
	}

	private void initializeGraphics() {
        manager = new AssetManager();
        loadAssets(true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
    }

	private void initializeWorld() {
        player = new Player(manager.get("player.png", Texture.class));
        map = new Map(manager.get("tile.atlas", TextureAtlas.class));
        testEnemy = new TestEnemy(manager.get("testEnemy.png", Texture.class));
        player.create();
        testEnemy.create();
        testEnemy.setPositon(map.getPathStart().getX(), map.getPathStart().getY()); // test code
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        updateScene();
        drawScene();
    }

    private void updateScene() {
	    float deltaTime = Gdx.graphics.getDeltaTime();
	    float playerMovSpeed = Constants.PLAYER_MOV_SPEED * deltaTime;
		// player controls logic
		if (Gdx.input.isKeyPressed(Input.Keys.W)) player.addPositionY(playerMovSpeed);
		if (Gdx.input.isKeyPressed(Input.Keys.S)) player.subPositionY(playerMovSpeed);
		if (Gdx.input.isKeyPressed(Input.Keys.A)) player.subPositionX(playerMovSpeed);
		if (Gdx.input.isKeyPressed(Input.Keys.D)) player.addPositionX(playerMovSpeed);

		// test code
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))     testEnemy.setPositon(testEnemy.getPosX(), testEnemy.getPosY() + 32f);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) testEnemy.setPositon(testEnemy.getPosX(), testEnemy.getPosY() - 32f);
        if (Gdx.input.isKeyJustPressed((Input.Keys.R)))   testEnemy.setPositon(map.getPathStart().getX(), map.getPathStart().getY());
        if (Gdx.input.isKeyJustPressed(Input.Keys.F))     testEnemy.setPositon(map.getPathEnd().getX(), map.getPathEnd().getY());
	}

    private void drawScene() {
	batch.begin();
		map.draw(batch);
	    testEnemy.draw(batch);
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
        manager.load("testEnemy.png", Texture.class);

        if (synchronously) { manager.finishLoading(); }
    }

    @Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
