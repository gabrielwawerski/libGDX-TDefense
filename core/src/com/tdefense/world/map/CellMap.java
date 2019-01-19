package com.tdefense.world.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.system.util.Config;
import com.tdefense.system.util.logging.Logger;
import com.tdefense.world.util.*;

public class CellMap {
    private TextureAtlas tileAtlas;
    private Cell[][] cells;
    private int[][] mapData;
    private Cell startCell;
    private Cell endCell;

    private WaypointSetBuilder wBuilder;
    public WaypointSet waypointSet;

    private Tile grassTileRegion;
    private Tile pathTileRegion;
    private Tile archerTower;


    private static final String TAG = CellMap.class.getSimpleName();

    public CellMap(TextureAtlas tileAtlas) {
        this.tileAtlas = tileAtlas;
        grassTileRegion = new TileRegion(this.tileAtlas.findRegion("grass"));
        pathTileRegion = new TileRegion(this.tileAtlas.findRegion("path"));
        archerTower = new TileRegion(this.tileAtlas.findRegion("archer_tower"));

    }

    public void create() {
        cells = new Cell[Config.MAP_LENGTH_X][Config.MAP_LENGTH_Y];
        wBuilder = new WaypointSetBuilder();

        mapData = MapUtils.getTempMapData();
        MapUtils.orderedMapDataToCells(this);
        waypointSet = wBuilder.createOrderedWaypointSet(this);
    }

    public void draw(Batch batch) {
        for (int x = 0; x < Config.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Config.MAP_LENGTH_Y; y++) {
                if (cells[x][y].getTile() instanceof TileRegion) {
                    batch.draw(cells[x][y].getTile().getTextureRegion(),
                            cells[x][y].getMapX(), cells[x][y].getMapY());
                }
            }
        }
    }

    public TextureAtlas getTileAtlas() {
        return tileAtlas;
    }

    public Tile getGrassTileRegion() {
        return grassTileRegion;
    }

    public Tile getPathTileRegion() {
        return pathTileRegion;
    }

    public Tile getArcherTower() {
        return archerTower;
    }

    public WaypointSet getWaypointSet() {
        return waypointSet;
    }

    public Cell getCellAt(int x, int y) {
        if (((x < 0 || x >= cells.length) && (y < 0 || y >= cells[0].length))) { // TODO check whether > length or >= length
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
