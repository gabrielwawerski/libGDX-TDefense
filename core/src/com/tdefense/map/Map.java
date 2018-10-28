package com.tdefense.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.Constants;
import com.tdefense.Drawable;
import com.tdefense.map.tile.Tile;

public class Map implements Drawable {
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
        mapSizeX = 32 * Constants.MAX_TILE_X;
        mapSizeY = 32 * Constants.MAX_TILE_Y;

        pathStartX = 5;
        pathStartY = Constants.MAX_TILE_Y - 1;
        pathEndX = 5;
        pathEndY = 1;
    }

//    private void fillMapCoordinates() {
//        for (int x = 0; x < mapCoordinates.length; x++) {
//            for (int y = 0; x < mapCoordinates[0].length; y++) {
//                mapCoordinates[x] = x + 16;
//            }
//        }
//    }

    // TODO figure out setting mapSize x and y
    @Override
    public void draw(Batch batch) {
        for (int x = 0; x < Constants.MAX_TILE_X; x++) {
            for (int y = 0; y < Constants.MAX_TILE_Y; y++) {
                batch.draw(grassTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
                if (x == 5) {
                    batch.draw(dirtTile.getTexture(), x * Constants.TILE_SCALE, y * Constants.TILE_SCALE);
                }
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
