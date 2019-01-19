package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.system.asset_handler.AssetHandler;
import com.tdefense.system.util.Config;

public class GameRenderer implements Disposable {
    // framework resources, etc.
    OrthographicCamera camera;
    OrthographicCamera cameraGUI;
    SpriteBatch batch;
    GameController gameController;
    private TextureRegion archerTowerGui;

    private static final String TAG = GameRenderer.class.getSimpleName();

    GameRenderer() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 1.6f, Gdx.graphics.getHeight() / 1.6f);
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.6f, Gdx.graphics.getHeight() / 1.6f);
        camera.update();

        cameraGUI = new OrthographicCamera(Gdx.graphics.getWidth() / 1.6f, Gdx.graphics.getHeight() / 1.6f);
        cameraGUI.position.set(0, 0, 0);
        cameraGUI.setToOrtho(true);
        cameraGUI.update();

        gameController = new GameController(camera, cameraGUI);
        gameController.initialize();
        batch = new SpriteBatch();
        archerTowerGui = new TextureRegion(gameController.map.getTileAtlas().findRegion("archer_tower"));
        Config.ARCHER_TOWER_POSITION_X = cameraGUI.position.x + 150;
        Config.ARCHER_TOWER_POSITION_Y = 70;
    }

    public void render() {
        updateScene();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        drawScene();
        drawGui(batch);
    }

    private void drawGui(SpriteBatch batch) {
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();

        drawGuiMenu(batch);

        batch.end();
    }

    private void drawGuiMenu(SpriteBatch batch) {
        batch.draw(archerTowerGui, Config.ARCHER_TOWER_POSITION_X, Config.ARCHER_TOWER_POSITION_Y);
    }

    public Vector3 unproject(Vector3 vector) {
        return camera.unproject(vector);
    }

    private void updateScene() {
        gameController.update(Gdx.graphics.getDeltaTime());
    }

    private void drawScene() {
        batch.begin();
        gameController.map.draw(batch);
        gameController.enemy.draw(batch);
        gameController.player.draw(batch);
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        AssetHandler.getInstance().dispose();
    }
}
