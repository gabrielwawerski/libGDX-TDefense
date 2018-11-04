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
    private Cell startCell;
    private Cell endCell;

    private WaypointSetBuilder wBuilder;
    public WaypointSet waypointSet;

    private static final String TAG = CellMap.class.getSimpleName();

    public CellMap(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTile = new Tile(this.tileAtlas.findRegion("grass"));
        pathTile = new Tile(this.tileAtlas.findRegion("path"));
    }

    public void create() {
        cells = new Cell[Constant.MAP_LENGTH_X][Constant.MAP_LENGTH_Y];
        wBuilder = new WaypointSetBuilder();

        mapData = MapUtils.getOrderedMapData();
        MapUtils.orderedMapDataToCells(this);
        waypointSet = wBuilder.createOrderedWaypointSet(this);
    }

    public void draw(Batch batch) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {
                if (cells[x][y].getCellType() == CellType.GRASS)
                    batch.draw(grassTile.getTextureRegion(), cells[x][y].getMapX(), cells[x][y].getMapY());

                if (cells[x][y].getCellType() == CellType.PATH)
                    batch.draw(pathTile.getTextureRegion(), cells[x][y].getMapX(), cells[x][y].getMapY());
            }
        }
    }

    public WaypointSet getWaypointSet() {
        return waypointSet;
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

    public CellType cellTypeAt(Cell cell) {
        return cells[cell.getDataX()][cell.getDataY()].getCellType();
    }

    public Cell getCellAt(Cell cell) {
        return cells[cell.getDataX()][cell.getDataY()];
    }

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(int x, int y) {
        startCell = cells[x][y];
    }

    public Cell getEndCell() {
        return endCell;
    }

    public void setEndCell(int x, int y) {
        endCell = cells[x][y];
    }
}
