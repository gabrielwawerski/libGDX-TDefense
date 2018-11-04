package com.tdefense.system;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.tdefense.system.logging.Logger;

public class TDefense extends Game {
    private static final String TAG = TDefense.class.getSimpleName();

    private WorldRenderer worldRenderer;

	@Override
	public void create () {
        Logger.setLogLevel(Logger.LOG_DEBUG);
	    worldRenderer = new WorldRenderer();
	}

    @Override
    public void render () {
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }

    @Override
	public void dispose () {
	    worldRenderer.dispose();
	}
}
