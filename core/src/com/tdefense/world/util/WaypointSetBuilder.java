package com.tdefense.world.util;

import com.tdefense.system.util.logging.Logger;
import com.tdefense.world.map.CellMap;

import static com.tdefense.world.util.MapUtils.toMap;

// TODO static
public class WaypointSetBuilder {
    private CellMap cellMap;
    private WaypointSet waypointSet;

    private static final String TAG = WaypointSetBuilder.class.getSimpleName();

    public WaypointSet createOrderedWaypointSet(CellMap cellMap) {
        this.cellMap = cellMap;
        waypointSet = new WaypointSet();
        buildOrderedWaypointSet();
        return waypointSet;
    }

    /**
     * Returns complete WaypointSet, with it's index set to 1.
     */
    private void buildOrderedWaypointSet() {
        int[][] mapData = cellMap.getMapData();
        int currentPosX = 0;
        int currentPosY = 0;
        int nextStepId = 0;
        int endPosX;
        int endPosY;
        boolean waypointSetBuilt = false;

        //region find start position, end position and total steps in mapData
        int endStep = 0;
        int endStepX = 0;
        int endStepY = 0;
        boolean startCellFound = false;

        for (int x = 0; x < mapData.length; x++) {
            for (int y = 0; y < mapData[x].length; y++) {
                // find end position
                if (mapData[x][y] > endStep) {
                    endStep = mapData[x][y];
                    endStepX = x;
                    endStepY = y;
                }

                // find start position
                if (mapData[x][y] == 1) {
                    currentPosX = x; // set pos x for waypointSet creating loop
                    currentPosY = y; // set pos y for waypointSet creating loop
                    nextStepId = 2;
                    cellMap.setStartCell(x, y);

                    // check direction
                    if (!(currentPosY - 1 < 0)
                            && mapData[currentPosX][currentPosY - 1] == nextStepId) {
                        waypointSet.add(toMap(x), toMap(y), Step.DOWN);

                    } else if (!(currentPosX - 1 < 0)
                            && mapData[currentPosX - 1][currentPosY] == nextStepId) {
                        waypointSet.add(toMap(x), toMap(y), Step.LEFT);

                    } else if (!(currentPosX + 1 >= mapData.length)
                            && mapData[currentPosX + 1][currentPosY] == nextStepId) {
                        waypointSet.add(toMap(x), toMap(y), Step.RIGHT);

                    } else if (!(currentPosY + 1 >= mapData[currentPosX].length)
                            && mapData[currentPosX][currentPosY + 1] == nextStepId) {
                        waypointSet.add(toMap(x), toMap(y), Step.UP);
                    }
                    startCellFound = true;

                    Logger.debug(TAG, "start cell found: (" + cellMap.getStartCell().getDataX()
                            + ", " + cellMap.getStartCell().getDataY() + ")");
                }
            }
        }

        if (!startCellFound) {
            String errorMessage = "start cell not found.";
            Logger.error(TAG, errorMessage);
            throw new IllegalArgumentException(errorMessage);
        } else {
            cellMap.setEndCell(endStepX, endStepY);
            waypointSet.setLastWaypoint(new Waypoint(
                    toMap(endStepX),
                    toMap(endStepY), Step.LAST));
            endPosX = cellMap.getEndCell().getDataX();
            endPosY = cellMap.getEndCell().getDataY();

            Logger.debug(TAG, "end cell found: (" + cellMap.getEndCell().getDataX()
                    + ", " + cellMap.getEndCell().getDataY() + ")");
        }
        //endregion

        Logger.debug(TAG, "building waypoint set. starting data: ("
                + cellMap.getStartCell().getDataX() + ", " + cellMap.getStartCell().getDataY() + ")");
        while (!waypointSetBuilt) {
            // DOWN CHECK
            if (!(currentPosY - 1 < 0)
                    && mapData[currentPosX][currentPosY - 1] == nextStepId) {
                currentPosY--;
                nextStepId++;
                waypointSet.add(toMap(currentPosX), toMap(currentPosY), Step.DOWN);
            }

            // LEFT CHECK
            else if (!(currentPosX - 1 < 0)
                    && mapData[currentPosX - 1][currentPosY] == nextStepId) {
                currentPosX--;
                nextStepId++;
                waypointSet.add(toMap(currentPosX), toMap(currentPosY), Step.LEFT);
            }

            // RIGHT CHECK
            else if (!(currentPosX + 1 >= mapData.length)
                    && mapData[currentPosX + 1][currentPosY] == nextStepId) {
                currentPosX++;
                nextStepId++;
                waypointSet.add(toMap(currentPosX), toMap(currentPosY), Step.RIGHT);
            }

            // UP CHECK
            else if (!(currentPosY + 1 >= mapData[currentPosX].length)
                    && mapData[currentPosX][currentPosY + 1] == nextStepId) {
                currentPosY++;
                nextStepId++;
                waypointSet.add(toMap(currentPosX), toMap(currentPosY), Step.UP);
            }

            // if last position, "finish" waypointset
            if (currentPosX == endPosX && currentPosY == endPosY) {
//                waypointSet.finish(MapUtils.toMap(currentPosX), MapUtils.toMap(currentPosY));
                waypointSetBuilt = true;
            }
        }

        //region debug messages
        Logger.debug(TAG, "waypointSet arraylist size: " + waypointSet.waypoints.size());
        Logger.debug(TAG, "first waypoint: " + waypointSet.waypoints.get(0).getVector());
        Logger.debug(TAG, "last waypoint: " + waypointSet.waypoints.get(waypointSet.waypoints.size() - 1).getVector());
        Logger.debug(TAG, "waypoints:");
        for (int i = 0; i < waypointSet.waypoints.size(); i++)
            Logger.debug(TAG, (i + 1) + " " + waypointSet.waypoints.get(i).getVector().toString());
        //endregion
    }
}
