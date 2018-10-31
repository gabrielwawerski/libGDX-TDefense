package com.tdefense.world.map.waypoint;

import com.tdefense.world.map.map.Cell;

import com.tdefense.world.map.map.CellType;

public class WaypointSetBuilder {
    private Cell[][] cells;
    private WaypointSet waypointSet;

    public WaypointSet getWaypointSet(Cell[][] cells) {
        this.cells = cells;
        waypointSet = new WaypointSet();
        Waypoint originWaypoint = new Waypoint(map.getStartCell().getDataX(), map.getStartCell().getDataY());
//        waypointSet.add(originWaypoint);
//        waypointSet.setOriginWaypoint(originWaypoint);
        fillWaypointSet();
        return waypointSet;
    }

    private void fillWaypointSet() {
        Cell currentCell = cells.getStartCell();
        pos pos = new pos(map.getStartCell().getDataX(), map.getStartCell().getDataY());
        while (map.getFinalCell().getCellType() != currentCell.getCellType()
                && map.getFinalCell().getDataY() != pos.getY() - 1) {

            if (map.cellTypeAt(pos.getX(), pos.getY() - 1) == CellType.PATH && !map.getCellAt(pos.getX(), pos.getY() - 1).wasVisited()) {
                currentCell = map.getCellAt(pos.getX(), pos.getY() - 1);
                currentCell.setVisited(true);
                waypointSet.add(new Waypoint(currentCell.getDataX(), currentCell.getDataY()));
                pos.setY(pos.getY() - 1);
            }

            else if (map.cellTypeAt(pos.getX() - 1, pos.getY())
                    == CellType.PATH && !map.getCellAt(pos.getX() - 1, pos.getY()).wasVisited()) {
                currentCell = map.getCellAt(pos.getX() - 1, pos.getY());
                waypointSet.add(new Waypoint(currentCell.getDataX(), currentCell.getDataY()));
                currentCell.setVisited(true);
                pos.setX(currentCell.getDataX() - 1);
            }

            else if (map.cellTypeAt(pos.getX() + 1, pos.getY())
                    == CellType.PATH && !map.getCellAt(pos.getX() + 1, pos.getY()).wasVisited()) {
                currentCell = map.getCellAt(pos.getX() + 1, pos.getY());
                currentCell.setVisited(true);

                waypointSet.add(new Waypoint(currentCell.getDataX(), currentCell.getDataY()));
                pos.setX(currentCell.getDataX() + 1);
            }

            else if (map.cellTypeAt(pos.getX(), pos.getY() + 1)
                    == CellType.PATH && !map.getCellAt(pos.getX(), pos.getY() + 1).wasVisited()) {

                currentCell = map.getCellAt(pos.getX(), pos.getY() + 1);
                waypointSet.add(new Waypoint(currentCell.getDataX(), currentCell.getDataY()));
                currentCell.setVisited(true);
                pos.setY(currentCell.getDataY() + 1);
            }

            if (currentCell.getDataX() == map.getFinalCell().getDataX()
                    || currentCell.getDataY() - 2 >= map.getFinalCell().getDataY()) { // TODO TU WYWALA

                waypointSet.add(new Waypoint(map.getFinalCell().getDataX(), map.getFinalCell().getDataY()));
                waypointSet.setFinalWaypoint(new Waypoint(map.getFinalCell().getDataX(), map.getFinalCell().getDataY()));
                break;
            }
            pos.setX(currentCell.getDataX());
            pos.setY(currentCell.getDataY());
        }
    }

    class pos {
        int x;
        int y;

        public pos(int x, int y) {
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
