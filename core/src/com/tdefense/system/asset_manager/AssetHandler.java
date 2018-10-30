package com.tdefense.system.asset_manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class AssetHandler extends AssetManager {
    // TODO figure out if both instances are needed
    // maybe extend AssetManager?
    private static AssetHandler instance;

    private AssetHandler() {}

    public static AssetHandler getInstance() {
        if (instance == null)
            instance = new AssetHandler();
        return instance;
    }

    /**
     *{@link AssetManager} can stop all code execution to load assets synchronously (on the same thread). This ensures
     * you that all assets have been loaded.
     * @param synchronously {@code true}, for synchronous asset load
     */
    public void loadAssets(boolean synchronously) {
        instance.load("tile.atlas", TextureAtlas.class);
        instance.load("player.png", Texture.class);
        instance.load("testEnemy.png", Texture.class);

        if (synchronously) { instance.finishLoading(); }
    }
}
