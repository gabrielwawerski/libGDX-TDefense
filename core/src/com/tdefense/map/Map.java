package com.tdefense.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.Constants;
import com.tdefense.Drawable;
import com.tdefense.map.tile.tiles.Tile;

public class Map implements Drawable {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile dirtTile;

    public Map(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTile = new Tile(this.tileAtlas.findRegion("grass"));
        dirtTile = new Tile(this.tileAtlas.findRegion("dirt"));
    }

    @Override
    public void draw(Batch batch) {
        for (int x = 0; x < Constants.MAP_SIZE_X; x++) {
            for (int y = 0; y < Constants.MAP_SIZE_Y; y++) {
                batch.draw(grassTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
                // draw dirt path that ai will walk on
                if (x == 5) {
                    batch.draw(dirtTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
                    // TODO set enemy starting point here
                }
            }
        }
    }
}
