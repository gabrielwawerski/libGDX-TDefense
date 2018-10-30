package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.tools.Constants;
import com.tdefense.utility.coordinate.Coordinate;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.tile.Tile;

public class Map {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile dirtTile;

    private int mapSizeX;
    private int mapSizeY;
    private int map[][];

    private Path path;
    private Coordinate pathStart;
    private Coordinate pathEnd;

    public Map(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTile = new Tile(this.tileAtlas.findRegion("grass"));
        dirtTile = new Tile(this.tileAtlas.findRegion("dirt"));
        map = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        mapSizeX = map.length;
        mapSizeY = map[0].length;
        pathStart = new Coordinate();
        pathEnd = new Coordinate();

        setPathStartCoordinate();
        setPathEndCoordinate();
    }

    public void draw(Batch batch) {
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {
                if(map[x][y] == 0) {
                    batch.draw(grassTile.getTexture(), translateCoordinate(x), translateCoordinate(y));
                }
                
                if(map[x][y] == 1) {
                    batch.draw(dirtTile.getTexture(), (float)x * Constants.TILE_SCALE, (float)y * Constants.TILE_SCALE);
                }
            }
        }
    }

    public Path createPath() {

        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {
                if(map[x][y] == 1) {
                }
            }
        }
        return new Path();
    }

    private void setPathStartCoordinate() {
        for (int x = 0; x < mapSizeX; x++) {
                if(map[x][mapSizeY - 1] == 1) {
                    pathStart.set((float)x, (float)mapSizeY - 1);
                    break;  // stop further search because path start coordinate has been found
                }
        }
    }
    
    private void setPathEndCoordinate() {
        for (int x = mapSizeX - 1; x > 0; x--) {
                if(map[x][0] == 1) {
                    pathEnd.set((float)x, 0f);
                }
        }
    }

    public float getMapSizeX() {
        return mapSizeX;
    }

    public float getMapSizeY() {
        return mapSizeY;
    }

    public Coordinate getPathStart() {
        return pathStart;
    }

    public Coordinate getPathEnd() {
        return pathEnd;
    }

    private static float translateCoordinate(float value) {
        return value * Constants.TILE_SCALE;
    }
}
