package com.tdefense.world.map.map;

public class Cell {
    private CellType cellType;
    private boolean visited;
    private float posX;
    private float posY;
    private int dataX;
    private int dataY;

    public Cell(CellType cellType, float posX, float posY, int dataX, int dataY) {
        this.cellType = cellType;
        this.posX = posX;
        this.posY = posY;
        visited = false;
        this.dataX = dataX;
        this.dataY = dataY;
    }

    public CellType getCellType() {
        return cellType;
    }

    public boolean wasVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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
}
