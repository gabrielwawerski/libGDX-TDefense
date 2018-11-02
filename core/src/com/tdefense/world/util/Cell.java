package com.tdefense.world.util;

public class Cell {
    private CellType cellType;
    private float posX;
    private float posY;
    private int dataX;
    private int dataY;
    private boolean visited;

    Cell(CellType cellType, float posX, float posY, int dataX, int dataY) {
        this.cellType = cellType;
        this.posX = posX;
        this.posY = posY;
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

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public int getDataX() {
        return dataX;
    }

    public int getDataY() {
        return dataY;
    }

    protected void setX(float posX) {
        this.posX = posX;
    }

    protected void setY(float posY) {
        this.posY = posY;
    }

    protected void setDataX(int dataX) {
        this.dataX = dataX;
    }

    protected void setDataY(int dataY) {
        this.dataY = dataY;
    }
}
