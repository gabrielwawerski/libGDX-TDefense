package com.tdefense.world.map.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.system.Constant;
import com.tdefense.system.logging.Logger;
import com.tdefense.world.map.map_utils.*;
import com.tdefense.world.map.tile.Tile;

import static com.tdefense.world.map.map_utils.MapUtils.translateCellMap;

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

    private static final String TAG = CellMap.class.getName();

    public CellMap(TextureAtlas textureAtlas) {
        this.tileAtlas = textureAtlas;
    }

    public void create() {
        cells = new Cell[Constant.MAP_LENGTH_X][Constant.MAP_LENGTH_Y];
        grassTile = new Tile(tileAtlas.findRegion("grass"));
        pathTile = new Tile(tileAtlas.findRegion("path"));
        mapData = MapUtils.getMap();
        translateCellMap(cells, mapData);
        wBuilder = new WaypointSetBuilder(this);
    }

    public void draw(Batch batch) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {
                if (mapData[x][y] == Constant.FINAL_CODE) {
                    batch.draw(grassTile.getTextureRegion(), MapUtils.scale(x), MapUtils.scale(y));
                }

                if (mapData[x][y] == Constant.ORIGIN_CODE || mapData[x][y] == Constant.PATH_CODE) {
                    batch.draw(pathTile.getTextureRegion(), MapUtils.scale(x), MapUtils.scale(y));
                }
            }
        }
    }

    public Cell getCellAt(int x, int y) {
        if ((x < 0 || x > cells.length && y < 0 || y > cells[0].length)) {
            Logger.error(TAG, "Invalid arguments. Found: x: " + x + ", y: " + y);
            throw new IllegalArgumentException("Invalid arguments. Found: x: " + x + ", y: " + y);
        } else {
            return cells[x][y];
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCellVisited(Cell cell, boolean visited) {
        cells[cell.getDataX()][cell.getDataY()].setVisited(visited);
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

    public Cell getFinalCell() {
        return finalCell;
    }

    public static Cell sgetStartCell() {
        return startCell;
    }

    public static Cell sgetFinalCell() {
        return finalCell;
    }

    public static void setFinalCell(Cell cell) {
        finalCell = cell;
    }

    public static void setStartCell(Cell cell) {
        startCell = cell;
    }
}
