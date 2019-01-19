package com.tdefense.system.asset_handler;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Singleton wrapper for {@link AssetManager}
 */
public final class AssetHandler extends AssetManager {
    private static AssetHandler instance;
    private AssetHandler() {}

    public static AssetHandler getInstance() {
        if (instance == null)
            instance = new AssetHandler();
        return instance;
    }

    /**
     * Stops all code execution to load assets synchronously (on the same thread). This ensures that all assets have been
     * loaded when method finishes.
     */
    public void loadAssets() {
        instance.load("tile.atlas", TextureAtlas.class);
        instance.load("player.png", Texture.class);
        instance.load("enemy.png", Texture.class);
        instance.finishLoading();
    }

    /**
     * {@code AssetHandler} can stop all code execution to load assets synchronously (on the same thread). This ensures
     * you that all assets have been loaded when method finishes.
     * @param synchronously {@code true}, for synchronous asset load
     */
    public void loadAssets(boolean synchronously) {
        instance.load("tile.atlas", TextureAtlas.class);
        instance.load("player.png", Texture.class);
        instance.load("enemy.png", Texture.class);

        if (synchronously) {
            instance.finishLoading();
        }
    }
}
