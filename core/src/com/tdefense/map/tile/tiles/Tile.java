package com.tdefense.map.tile.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion texture;

    private int posX;
    private int posY;

    public Tile(TextureRegion texture) {
        this.texture = texture;
    }

    public Tile(TextureRegion texture, int posX, int posY) {
        this.texture = texture;
        this.posX = posX;
        this.posY = posY;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
