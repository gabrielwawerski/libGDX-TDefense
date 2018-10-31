package com.tdefense.world.map.map_utils;

import com.tdefense.world.map.map.CellMap;
import com.tdefense.world.map.map.CellType;

public class WaypointSetBuilder {
    private CellMap cellMap;
    private WaypointSet waypointSet;

    public  WaypointSetBuilder(CellMap cellMap) {
        this.cellMap = cellMap;
    }

    public WaypointSet createWaypointSet() {
        waypointSet = new WaypointSet();
//        fillWaypointSet();
        return waypointSet;
    }

    private void fillWaypointSet() {
        // current position inside cellMap's tile 2d array.
        Cell currentPos = CellMap.sgetStartCell();
        Cell nextPos = CellMap.sgetStartCell();
        Cell finalPos = CellMap.sgetFinalCell();

        // cells for checking next index in corresponding direction
        Cell downCheck = new Cell(currentPos.getDataX(), currentPos.getDataY() - 1);
        Cell leftCheck = new Cell(currentPos.getDataX() - 1, currentPos.getDataY());
        Cell rightCheck = new Cell(currentPos.getDataX() + 1, currentPos.getDataY());
        Cell upCheck = new Cell(currentPos.getDataX(), currentPos.getDataY() + 1);
        CellType temp;
        // TODO:
        // - add cell to waypointSet
        // - set cell visited to true
        //
        while (currentPos != finalPos) {
            if (cellMap.getCellAt(currentPos).getDataY() > cellMap.getCells()[0].length) {
                waypointSet.add(currentPos);
                cellMap.getCellAt(currentPos).
            }

            // DOWN CHECK
            if ((cellMap.cellTypeAt(downCheck) == CellType.PATH
                    || cellMap.cellTypeAt(downCheck) == CellType.PATH_FINAL)
                    && !(downCheck.getDataX() > cellMap.getCells().length)) {

                if (cellMap.cellTypeAt(downCheck) == CellType.PATH) {
                    cellMap.setCellVisited(downCheck, true);
                    waypointSet.add(downCheck);
                    currentPos = downCheck;
                }
                else if (cellMap.cellTypeAt(downCheck) == CellType.PATH_FINAL) {
                    waypointSet.add(downCheck);
                    break;
                }
            }

            // LEFT CHECK
            if ((cellMap.getCellAt(leftCheck).getCellType() == CellType.PATH
                    || cellMap.cellTypeAt(leftCheck) == CellType.PATH_FINAL)
                    && !(leftCheck.getDataX() < 0)) {

                if (cellMap.cellTypeAt(leftCheck) == CellType.PATH) {
                    cellMap.setCellVisited(leftCheck, true);
                    waypointSet.add(leftCheck);
                    currentPos = leftCheck;
                }
                else if (cellMap.cellTypeAt(leftCheck) == CellType.PATH_FINAL) {
                    waypointSet.add(leftCheck);
                    break;
                }
            }

            // RIGHT CHECK
            if ((cellMap.cellTypeAt(rightCheck) == CellType.PATH
                    || cellMap.cellTypeAt(rightCheck) == CellType.PATH_FINAL)
                    && !(rightCheck.getDataX() > cellMap.getCells().length)) {
                if (cellMap.cellTypeAt(rightCheck) == CellType.PATH) {
                    cellMap.setCellVisited(rightCheck, true);
                    waypointSet.add(rightCheck);
                    currentPos = rightCheck;
                }
                else if (cellMap.cellTypeAt(rightCheck) == CellType.PATH_FINAL) {
                    waypointSet.add(rightCheck);
                    break;
                }
            }

            // UP CHECK
            if ((cellMap.cellTypeAt(upCheck) == CellType.PATH
                    || cellMap.cellTypeAt(upCheck) == CellType.PATH_FINAL)
                    && !(upCheck.getDataY() > cellMap.getCells()[0].length)) {

                if (cellMap.cellTypeAt(upCheck) == CellType.PATH) {
                    cellMap.setCellVisited(upCheck, true);
                    waypointSet.add(upCheck);
                }
                else if (cellMap.cellTypeAt(upCheck) == CellType.PATH_FINAL) {
                    waypointSet.add(upCheck);
                    break;
                }
            }

        }
    }

    private class Coord {
        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
