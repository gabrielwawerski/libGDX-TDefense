package com.tdefense.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
    private static final String TAG = WorldRenderer.class.getSimpleName();

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    
    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
    }
    
    private void initialize() {
        // move stuff from create to here
    }
    
    public void render() {
    }

    private void updateScene() {
        worldController.update(Gdx.graphics.getDeltaTime());
    }

    private void drawScene() {
    }
    
    public void resize(int width, int height) {
    }
    
    @Override
    public void dispose() {
    }
}
