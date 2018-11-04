package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.system.asset_manager.AssetHandler;
import com.tdefense.system.logging.Logger;

public class WorldRenderer implements Disposable {
    private static final String TAG = WorldRenderer.class.getSimpleName();
    // framework resources, etc.
    OrthographicCamera camera;
    SpriteBatch batch;
    WorldController worldController;
    
    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        this.worldController.initialize();
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
        worldController.update(Gdx.graphics.getDeltaTime());
    }

    private void drawScene() {
        batch.begin();
        worldController.map.draw(batch);
        worldController.enemy.draw(batch);
        worldController.player.draw(batch);
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        AssetHandler.getInstance().dispose();
    }
}
