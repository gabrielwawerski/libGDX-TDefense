package com.tdefense.world.util;

import com.tdefense.system.Constant;
import com.tdefense.system.logging.Logger;
import com.tdefense.world.map.CellMap;

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
        int nextStep = 0;
        int finalStep;
        boolean loop = true;
        int x = 0;
        int y = 0;

        // TODO refactor - only one coord check - x
        // find starting position
        for (int i = 0; i < Constant.MAP_LENGTH_X; i++) {
            for (int j = 0; j < Constant.MAP_LENGTH_Y; j++) {

                // check and save map's final cell
                if (mapData[i][0] != 0) {
                    finalStep = j;
                }
                // we only need to check if y equals the path's beginning
                if (mapData[i][j] == 1) {
                    CellMap.sSetStartCell(new Cell(CellType.PATH_START, MapUtils.scale(x), MapUtils.scale(y), x, y));
                    x = i;
                    y = j;
                    nextStep = i;
                } else {
                    String errorMessage = "No starting point found in ordered map data.";
                    Logger.log(TAG, errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                }
            }
        }

        // main loop for filling waypointSet
        while (x < Constant.MAP_LENGTH_X && y < Constant.MAP_LENGTH_Y && loop) { // TODO temporary - figue it out
            // DOWN CHECK
            if (mapData[x][y - 1] == nextStep) {
                nextStep++;
                waypointSet.add(cells[x][y]);
                y++;
            }

            // LEFT CHECK
            else if (mapData[x - 1][y] == nextStep) {
                nextStep++;
                waypointSet.add(cells[x - 1][y]);
                x++;
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
