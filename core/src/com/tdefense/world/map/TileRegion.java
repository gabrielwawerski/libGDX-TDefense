package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileRegion extends Tile {
    private TextureRegion texture;

    public TileRegion(TextureRegion texture) {
        this.texture = texture;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return texture;
    }
}
