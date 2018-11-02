package com.tdefense.world.map.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion texture;

    public Tile(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTextureRegion() {
        return texture;
    }
}
