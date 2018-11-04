package com.tdefense.world.util;

public class Cell {
    private CellType cellType;
    private float mapX;
    private float mapY;
    private int dataX;
    private int dataY;

    /**
     *
     * @param cellType
     * @param mapX cell's position on game map array, scaled with cell coordinates
     * @param mapY cell's position on game map array, scaled with cell coordinates
     * @param dataX x cell position in coordinate array
     * @param dataY y cell position in coordinate array
     */
    Cell(CellType cellType, float mapX, float mapY, int dataX, int dataY) {
        this.cellType = cellType;
        this.mapX = mapX;
        this.mapY = mapY;
        this.dataX = dataX;
        this.dataY = dataY;
    }

    public CellType getCellType() {
        return cellType;
    }

    public float getMapX() {
        return mapX;
    }

    public float getMapY() {
        return mapY;
    }

    public int getDataX() {
        return dataX;
    }

    public int getDataY() {
        return dataY;
    }
}
