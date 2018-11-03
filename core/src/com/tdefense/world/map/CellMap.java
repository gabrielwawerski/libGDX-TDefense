package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.system.Constant;
import com.tdefense.system.logging.Logger;
import com.tdefense.world.util.*;

public class CellMap {
    private TextureAtlas tileAtlas;
    private Tile grassTile;
    private Tile pathTile;
    private Cell[][] cells;
    private int[][] mapData;
    private static Cell startCell;
    private static Cell endCell;

    private WaypointSetBuilder wBuilder;
    private WaypointSet waypointSet;

    private static final String TAG = CellMap.class.getName();

    public CellMap(TextureAtlas textureAtlas) {
        this.tileAtlas = textureAtlas;
        grassTile = new Tile(tileAtlas.findRegion("grass"));
        pathTile = new Tile(tileAtlas.findRegion("path"));
    }

    public void create() {
        cells = new Cell[Constant.MAP_LENGTH_X][Constant.MAP_LENGTH_Y];
        mapData = MapUtil.getOrderedMapData();
        MapUtil.orderedMapDataToCells((mapData, cells);
        wBuilder = new WaypointSetBuilder(this);
        waypointSet = wBuilder.createSimpleWaypointSet();
    }

    public void draw(Batch batch) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {
                if (mapData[x][y] == Constant.FINAL_CODE)
                    batch.draw(grassTile.getTextureRegion(), MapUtil.toMap(x), MapUtil.toMap(y));

                if (mapData[x][y] == Constant.START_CODE || mapData[x][y] == Constant.PATH_CODE)
                    batch.draw(pathTile.getTextureRegion(), MapUtil.toMap(x), MapUtil.toMap(y));
            }
        }
    }

    public Cell getCellAt(int x, int y) {
        if ((x < 0 || x >= cells.length && y < 0 || y >= cells[0].length)) { // TODO check whether > length or >= length
            String errorMessage = "Invalid arguments. Found: x: " + x + ", y: " + y;
            Logger.error(TAG, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        } else
            return cells[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int[][] getMapData() {
        return mapData;
    }

    public void setCellVisited(Cell cell) {
        cells[cell.getDataX()][cell.getDataY()].setVisited(true);
    }

    public CellType cellTypeAt(Cell cell) {
        return cells[cell.getDataX()][cell.getDataY()].getCellType();
    }

    public Cell getCellAt(Cell cell) {
        return cells[cell.getDataX()][cell.getDataY()];
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public static Cell sGetStartCell() {
        return startCell;
    }

    public static void sSetStartCell(Cell cell) {
        startCell = cell;
    }

    public static Cell sGetEndCell() {
        return endCell;
    }

    public static void sSetEndCell(Cell cell) {
        startCell = cell;
    }
}
