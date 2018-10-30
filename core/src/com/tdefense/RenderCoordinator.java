package com.tdefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class RenderCoordinator implements Disposable {
    private static final String TAG = RenderCoordinator.class.getSimpleName();

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private LogicHandler logicHandler;
    
    public RenderCoordinator(LogicHandler logicHandler) {
        this.logicHandler = logicHandler;
    }
    
    private void initialize() {
        // move stuff from create to here
    }
    
    public void render() {
        updateScene();
        drawScene();
    }

    private void updateScene() {
        logicHandler.update(Gdx.graphics.getDeltaTime());
    }

    private void drawScene() {
    }
    
    public void resize(int width, int height) {
    }
    
    @Override
    public void dispose() {
    }
}
