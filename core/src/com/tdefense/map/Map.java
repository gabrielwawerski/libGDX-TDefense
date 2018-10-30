package com.tdefense.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.Constants;
import com.tdefense.map.tile.Tile;

public class Map {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile dirtTile;

    private float mapSizeX;
    private float mapSizeY;
    private int mapCoordinates[][];

    private int pathStartX;
    private int pathStartY;
    private int pathEndX;
    private int pathEndY;

    public Map(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTile = new Tile(this.tileAtlas.findRegion("grass"));
        dirtTile = new Tile(this.tileAtlas.findRegion("dirt"));
        mapSizeX = Constants.TILE_SCALE * Constants.MAX_TILE_X;
        mapSizeY = Constants.TILE_SCALE * Constants.MAX_TILE_Y;

        //region TODO temporary, needs implemented path creation, for automated setting of path start and end fields.
        pathStartX = 5 * Constants.TILE_SCALE;
            pathStartY = (Constants.MAX_TILE_Y - 1) * Constants.TILE_SCALE;
            pathEndX = 5 * Constants.TILE_SCALE;
            pathEndY = 1 * Constants.TILE_SCALE;
        //endregion
    }

    // TODO figure out setting mapSize x and y
    public void draw(Batch batch) {
        for (int x = 0; x < Constants.MAX_TILE_X; x++) {
            for (int y = 0; y < Constants.MAX_TILE_Y; y++) {
                batch.draw(grassTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);  // grass drawing
                if (x == (Constants.MAX_TILE_X - 1) / 2) { batch.draw(dirtTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE); }  // path drawing
            }
        }
    }

    public Coordinate getPathStartCoordinate() {
        return new Coordinate(pathStartX, pathStartY);
    }

    public Coordinate getPathEndCoordinate() {
        return new Coordinate(pathEndX, pathEndY);
    }

    public float getMapSizeX() {
        return mapSizeX;
    }

    public float getMapSizeY() {
        return mapSizeY;
    }
}
