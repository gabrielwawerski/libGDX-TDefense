package com.tdefense.world.util;

public class Cell {
    private CellType cellType;
    private float mapX;
    private float mapY;
    private int dataX;
    private int dataY;
    private boolean visited;

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
        visited = false;
        this.dataX = dataX;
        this.dataY = dataY;
    }

    Cell(int dataX, int dataY) {
        this.dataX = dataX;
        this.dataY = dataY;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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

    protected void setX(float posX) {
        this.mapX = posX;
    }

    protected void setY(float posY) {
        this.mapY = posY;
    }

    protected void setDataX(int dataX) {
        this.dataX = dataX;
    }

    protected void setDataY(int dataY) {
        this.dataY = dataY;
    }
}
