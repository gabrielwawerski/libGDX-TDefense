package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.system.asset_manager.AssetHandler;

public class WorldCoordinator implements Disposable {
    private static final String TAG = WorldCoordinator.class.getSimpleName();

    // framework resources
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private LogicHandler logicHandler;
    
    public WorldCoordinator(LogicHandler logicHandler) {
        this.logicHandler = logicHandler;
        initialize();
    }
    
    private void initialize() {
        AssetHandler.getInstance().loadAssets(true);
        logicHandler.initialize();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 1.6f, Gdx.graphics.getHeight() / 1.6f);
        camera.update();
    }
    
    public void render() {
        updateScene();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        drawScene();
    }

    private void updateScene() {
        logicHandler.update(Gdx.graphics.getDeltaTime());
    }

    private void drawScene() {
        batch.begin();
        logicHandler.map.draw(batch);
        logicHandler.testEnemy.draw(batch);
        logicHandler.player.draw(batch);
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        AssetHandler.getInstance().dispose();
    }
}
