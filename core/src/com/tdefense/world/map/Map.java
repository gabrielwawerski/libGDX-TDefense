package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.system.Constant;
import com.tdefense.world.map.map_provider.MapProvider;
import com.tdefense.world.map.tile.Tile;
import com.tdefense.world.map.waypoint.WaypointSetBuilder;
import com.tdefense.world.map.waypoint.WaypointSet;

public class Map {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile pathTile;

    private int[][] map;
    private int mapSizeX;
    private int mapSizeY;

    private WaypointSet waypointSet;

    public Map(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
    }

    public WaypointSet getWaypointSet() {
        return waypointSet;
    }

    public void create() {
        grassTile = new Tile(tileAtlas.findRegion("grass"));
        pathTile = new Tile(tileAtlas.findRegion("path"));
        map = MapProvider.getMap();
        mapSizeX = map.length;
        mapSizeY = map[0].length;
        waypointSet = WaypointSetBuilder.getWaypointSet(map);
    }

    public void draw(Batch batch) {
        for (int x = 0; x < mapSizeX; x++) {
            for (int y = 0; y < mapSizeY; y++) {
                if (map[x][y] == 0) {
                    batch.draw(grassTile.getTextureRegion(), scale(x), scale(y)); }
                if (map[x][y] == 1 || map[x][y] == 9 || map[x][y] == 8) {
                    batch.draw(pathTile.getTextureRegion(), scale(x), scale(y));
                }
            }
        }
    }

    /**
     * Scales provided value, by {@link Constant#TILE_SCALE}, so that after transformation - the value returned to caller
     * represents tile position in relation to other tiles, as if it was a part of map's tile "grid". (which is not
     * a real thing in this project as of now)
     * @param value the value to be scaled
     * @return scaled value
     * @author mx
     */
    public static float scale(float value) {
        return value * Constant.TILE_SCALE;
    }
}
