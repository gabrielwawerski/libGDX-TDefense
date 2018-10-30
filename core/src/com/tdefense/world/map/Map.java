package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.tools.Constants;
import com.tdefense.utility.coordinate.Coordinate;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.path.Step;
import com.tdefense.world.map.tile.Tile;

public class Map {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile pathTile;

    private int mapSizeX;
    private int mapSizeY;
    private int map[][];

    private Path path;
    private Coordinate pathStart;
    private Coordinate pathEnd;

    /**
     * <table>
     *     <tr><td>0</td> <td>grass tile</td></tr>
     *     <tr><td>1</td> <td>path tile - going straight</td></tr>
     *     <tr><td>2</td> <td>path tile - going left</td></tr>
     *     <tr><td>9</td> <td>path tile - going right</td></tr>
     * </table>
     * @param tileAtlas
     */
    public Map(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTile = new Tile(this.tileAtlas.findRegion("grass"));
        pathTile = new Tile(this.tileAtlas.findRegion("dirt"));
        map = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 9, 1, 3, 0, 3, 1, 1, 1, 8 },
                { 0, 0, 2, 0, 3, 0, 0, 0, 0 },
                { 0, 0, 2, 0, 3, 0, 0, 0, 0 },
                { 0, 0, 2, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        mapSizeX = map.length;
        mapSizeY = map[0].length;
        pathStart = new Coordinate();
        pathEnd = new Coordinate();
        createPath();

        assignPathStartCoordinate();
        assignPathEndCoordinate();
    }

    public void draw(Batch batch) {
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {
                if(map[x][y] == 0) {
                    batch.draw(grassTile.getTexture(), translateCoordinate(x), translateCoordinate(y));
                }

                if(map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 3
                        || map[x][y] == 8 || map[x][y] == 9) {
                    batch.draw(pathTile.getTexture(), translateCoordinate(x), translateCoordinate(y));
                }
            }
        }
    }

    public Path getPath() {
        return path;
    }

    private void createPath() {
        path = new Path();
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {
                if (map[x][y] == 1) {
                    path.addStep(Step.STEP_DOWN);
                } else if (map[x][y] == 2) {
                    path.addStep(Step.STEP_LEFT);
                } else if (map[x][y] == 3) {
                    path.addStep(Step.STEP_RIGHT);
                } else if (map[x][y] == 8) {
                    path.addStep(Step.STEP_DOWN);
                    path.setStartPosition(translateCoordinate(x), translateCoordinate(y));
                } else if (map[x][y] == 9) {
                    path.addStep(Step.STEP_DOWN);
                }
            }
        }
    }

    private void assignPathStartCoordinate() {
        for (int x = 0; x < mapSizeX; x++) {
                if(map[x][mapSizeY - 1] == 8) {
                    pathStart.set(x, mapSizeY - 1);
                    break;  // stop further search because path nextMove coordinate has been found
                }
        }
    }
    
    private void assignPathEndCoordinate() {
        for (int x = mapSizeX - 1; x > 0; x--) {
                if(map[x][0] == 9) {
                    pathEnd.set(x, 0f);
                    break; // stop further search because path nextMove coordinate has been found
                }
        }
    }

    public Coordinate getTileAt(float positionX, float positionY) {
        return new Coordinate(positionX, positionY);
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

    private static float calculateTilePosition(float value) {
        return value * Constants.TILE_SCALE;
    }
}
