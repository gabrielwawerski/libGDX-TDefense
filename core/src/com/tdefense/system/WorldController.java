package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.system.asset_manager.AssetHandler;

public class WorldController implements Disposable {
    private static final String TAG = WorldController.class.getSimpleName();
    // framework resources, etc.
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private LogicHandler logicHandler;
    
    public WorldController(LogicHandler logicHandler) {
        this.logicHandler = logicHandler;

        AssetHandler.getInstance().loadAssets(true);
        this.logicHandler.initialize();
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
        logicHandler.enemy.draw(batch);
        logicHandler.player.draw(batch);
        batch.end();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        AssetHandler.getInstance().dispose();
    }
}
