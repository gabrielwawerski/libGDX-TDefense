package com.tdefense.world.map.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.system.Constant;
import com.tdefense.world.map.tile.Tile;
import com.tdefense.world.map.waypoint.WaypointSet;
import com.tdefense.world.map.waypoint.WaypointSetBuilder;

import static com.tdefense.world.map.map.MapUtils.translateCellMap;

public class CellMap {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile pathTile;
    private Cell[][] cells;
    private int[][] mapData;
    private static Cell startCell;
    private static Cell finalCell;

    private WaypointSetBuilder wBuilder;
    private WaypointSet waypointSet;

    public CellMap(TextureAtlas textureAtlas) {
        this.tileAtlas = textureAtlas;
    }

    public void create() {
        cells = new Cell[Constant.MAP_LENGTH_X][Constant.MAP_LENGTH_Y];
        grassTile = new Tile(tileAtlas.findRegion("grass"));
        pathTile = new Tile(tileAtlas.findRegion("path"));
        mapData = MapUtils.getMap();
        translateCellMap(cells, mapData);
        wBuilder = new WaypointSetBuilder(cells);
    }

    public void draw(Batch batch) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {
                if (cells[x][y].getCellType() == CellType.GRASS) {
                    batch.draw(grassTile.getTextureRegion(), MapUtils.scale(x), MapUtils.scale(y));
                }
                if (cells[x][y].getCellType() == CellType.PATH
                        || cells[x][y].getCellType() == CellType.PATH_ORIGIN
                        || cells[x][y].getCellType() == CellType.PATH_FINAL) {
                    batch.draw(pathTile.getTextureRegion(), MapUtils.scale(x), MapUtils.scale(y));
                }
            }
        }
    }

//    if (mapData[x][y] == Constant.PATH_CODE
//                        || mapData[x][y] == Constant.ORIGIN_CODE
//                        || mapData[x][y] == Constant.FINAL_CODE) {

    public int[][] getMapData() {
        return mapData;
    }

    public Cell getCellAt(int x, int y) {
        return cells[x][y];
    }

    public CellType cellTypeAt(int x, int y) {
        return cells[x][y].getCellType();
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getFinalCell() {
        return finalCell;
    }

    public static void setFinalCell(Cell cell) {
        finalCell = cell;
    }

    public static void setStartCell(Cell cell) {
        startCell = cell;
    }
}
