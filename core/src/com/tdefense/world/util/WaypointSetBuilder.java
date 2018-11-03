package com.tdefense.world.util;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import com.tdefense.system.Constant;
import com.tdefense.system.logging.Logger;
import com.tdefense.world.map.CellMap;

import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.zip.DataFormatException;

public class WaypointSetBuilder {
    private CellMap cellMap;
    private WaypointSet waypointSet;

    private static final String TAG = WaypointSetBuilder.class.getName();

    public WaypointSet createOrderedWaypointSet(CellMap cellMap) {
        this.cellMap = cellMap;
        waypointSet = new WaypointSet();
        fillSimpleWaypointSet();
        return waypointSet;
    }

    public WaypointSet createSimpleWaypointSet(CellMap cellMap) {
        this.cellMap = cellMap;
        waypointSet = new WaypointSet();
        fillOrderedWaypointSet();
        return waypointSet;
    }

    private void fillOrderedWaypointSet() {
        Cell[][] cells = cellMap.getCells();
        int[][] mapData = cellMap.getMapData();
        boolean loop = true;
        int nextStepId = 0;
        int posX = 0;
        int posY = 0;
        boolean startCellFound = false;
        int lastStep = 0;

        // find beginning cell
        for (int i = 0; i < Constant.MAP_LENGTH_X; i++) {
            for (int j = 0; j < Constant.MAP_LENGTH_Y; j++) {
                if (mapData[i][j] >= 1) {
                    CellMap.sSetStartCell(new Cell(CellType.PATH_START,
                            MapUtil.toMap(i), MapUtil.toMap(j),
                            i, j));

                    startCellFound = true;
                    posX = i;
                    posY = j;
                    waypointSet.add(CellMap.sGetStartCell());
                    nextStepId = 2;
                    break;
                }
            }
        }

        if (!startCellFound) {
            String errorMessage = "Start cell hasn't been found.";
            Logger.error(TAG, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        } else {
            while (posX != cellMap.getEndCell().getDataX() && posY != cellMap.getEndCell().getDataY()) {
                for (int i = posX; i < Constant.MAP_LENGTH_X; i++) {
                    for (int j = posY; j < Constant.MAP_LENGTH_Y; j++) {
                        if (!(j - 1 < 0) && mapData[i][j - 1] == nextStepId) {
                            nextStepId++;
                            posY--;
                            waypointSet.add(cellMap.getCellAt(i, 0));
                        } else if (!(j + 1 < 0) && mapData[i][j + 1] == nextStepId) {
                            nextStepId++;
                            posY++;
                            waypointSet.add(cellMap.getCellAt(i, 0));
                        } else if (!(i - 1 < 0) && mapData[i - 1][j] == nextStepId) {
                            nextStepId++;
                            posX--;
                        }
                    }
                }
            }
        }

        // main loop for filling waypointSet
        while (posX < Constant.MAP_LENGTH_X && posY < Constant.MAP_LENGTH_Y && loop) { // TODO temporary - figue it out
            // DOWN CHECK
            if (mapData[posX][posY - 1] == nextStepId) {
                nextStepId++;
                waypointSet.add(cells[posX][posY]);
                posY++;
            }

            // LEFT CHECK
            else if (mapData[posX - 1][posY] == nextStepId) {
                nextStepId++;
                waypointSet.add(cells[posX - 1][posY]);
                posX++;
            }
        }
    }

    private void fillSimpleWaypointSet() {
        waypointSet.add(cellMap.getStartCell());

        // current position inside cellMap's cells 2d array.
        Cell currentPos = cellMap.getStartCell();
        Cell nextPos = cellMap.getStartCell();
        Cell endPos = cellMap.getEndCell();

        // cells for checking next index in corresponding direction
        Cell downCheck = new Cell(currentPos.getDataX(), currentPos.getDataY() - 1);
        Cell leftCheck = new Cell(currentPos.getDataX() - 1, currentPos.getDataY());
        Cell rightCheck = new Cell(currentPos.getDataX() + 1, currentPos.getDataY());
        Cell upCheck = new Cell(currentPos.getDataX(), currentPos.getDataY() + 1);

        // TODO:
        // - add cell to waypointSet    X
        // - set cell visited to true   X
        while (currentPos != endPos) {
            // DOWN CHECK
            if ((cellMap.cellTypeAt(downCheck) == CellType.PATH || cellMap.cellTypeAt(downCheck) == CellType.PATH_END)
                    && !(downCheck.getDataX() > cellMap.getCells().length)) {

                if (cellMap.cellTypeAt(downCheck) == CellType.PATH) {
                    cellMap.setCellVisited(downCheck);
                    waypointSet.add(downCheck);
                    currentPos = cellMap.getCellAt(downCheck);
                }
                else if (cellMap.cellTypeAt(downCheck) == CellType.PATH_END) {
                    waypointSet.add(downCheck);
                    break;
                }
            }

            // LEFT CHECK
            else if ((cellMap.getCellAt(leftCheck).getCellType() == CellType.PATH || cellMap.cellTypeAt(leftCheck) == CellType.PATH_END)
                    && !(leftCheck.getDataX() < 0)) {

                if (cellMap.cellTypeAt(leftCheck) == CellType.PATH) {
                    cellMap.setCellVisited(leftCheck);
                    waypointSet.add(leftCheck);
                    currentPos = leftCheck;
                }
                else if (cellMap.cellTypeAt(leftCheck) == CellType.PATH_END) {
                    waypointSet.add(leftCheck);
                    break;
                }
            }

            // RIGHT CHECK
            else if ((cellMap.cellTypeAt(rightCheck) == CellType.PATH || cellMap.cellTypeAt(rightCheck) == CellType.PATH_END)
                    && !(rightCheck.getDataX() > cellMap.getCells().length)) {

                if (cellMap.cellTypeAt(rightCheck) == CellType.PATH) {
                    cellMap.setCellVisited(rightCheck);
                    waypointSet.add(rightCheck);
                    currentPos = rightCheck;
                }
                else if (cellMap.cellTypeAt(rightCheck) == CellType.PATH_END) {
                    waypointSet.add(rightCheck);
                    break;
                }
            }

            // UP CHECK
            else if ((cellMap.cellTypeAt(upCheck) == CellType.PATH || cellMap.cellTypeAt(upCheck) == CellType.PATH_END)
                    && !(upCheck.getDataY() > cellMap.getCells()[0].length)) {

                if (cellMap.cellTypeAt(upCheck) == CellType.PATH) {
                    cellMap.setCellVisited(upCheck);
                    waypointSet.add(upCheck);
                }
                else if (cellMap.cellTypeAt(upCheck) == CellType.PATH_END) {
                    waypointSet.add(upCheck);
                    break;
                }
            }
        }
    }
}
