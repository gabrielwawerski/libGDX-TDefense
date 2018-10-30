package com.tdefense.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.world.world_controller.WorldController;

public class WorldRenderer implements Disposable {
    private static final String TAG = WorldRenderer.class.getSimpleName();

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    
    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
    }
    
    private void initialize() {
    }
    
    public void render() {
    }
    
    public void resize(int width, int height) {
    }
    
    @Override
    public void dispose() {
    }
}
