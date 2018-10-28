package com.tdefense.map.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion texture;

    public Tile(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
