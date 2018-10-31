package com.tdefense.world.map.waypoint;

import com.tdefense.tools.logging.Logger;

/**
 * {@link WaypointSet} building class. Supply your {@code int[][] map} to {@link #getWaypointSet(int[][])} method,
 * in order to receive newly created {@code WaypointSet} instance, containing an array of ordered coordinates (x, y),
 * corresponding to each map path tile's position - extracted from the supplied map array. Each coordinate is stored
 * in {@link Waypoint} object - with first being {@link #originWaypoint} and the last as {@link #lastWaypoint}.
 * @author mx01
 * @date 31.10.2018
 */
public class WaypointBuilder {
    private static final String TAG = WaypointBuilder.class.getSimpleName();

    private static int[][] workingMap;
    private static int lengthX;
    private static int lengthY;

    private static WaypointSet waypointSet;
    /** first coordinate(x, y) of supplied {@code int[][] map} 2d array. */
    private static Waypoint originWaypoint;
    /** last coordinate(x,y) of supplied {@code int[][] map} 2d array.*/
    private static Waypoint lastWaypoint;

    private WaypointBuilder() {
    }

    /**
     * Returns {@link WaypointSet} instance, containing correctly ordered waypoints
     * @param map
     * @return
     */
    public static WaypointSet getWaypointSet(int[][] map) {
        workingMap = map;
        lengthX = workingMap.length;
        lengthY = workingMap[0].length;
        waypointSet = new WaypointSet();

        originWaypoint = getOriginWaypoint();
        lastWaypoint = getLastWaypoint();
        fillWaypointSet();

        return waypointSet;
    }

    private static Waypoint getOriginWaypoint() {
        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                if (workingMap[x][y] == 9) {
                    return new Waypoint(x, y);
                }
            }
        }
        String errorMessage = "Invalid workingMap array. Point of origin (9) not found.";
        Logger.error(TAG, errorMessage);
        throw new IndexOutOfBoundsException(errorMessage);
    }

    private static Waypoint getLastWaypoint() {
        for (int x = 0; x < lengthX; x++) {
            if (workingMap[x][0] == 8) {
                return new Waypoint(x, 0);
            }
        }
        String errorMessage = "Invalid workingMap array. Last point (8) not found.";
        Logger.error(TAG, errorMessage);
        throw new IndexOutOfBoundsException(errorMessage);
    }

    // TODO TEST
    /**
     *          <- -y   +y ->
     * y:0  1  2  3  4  5  6  7  8    x:
     * { 0, 0, 0, 0, 0, 0, 0, 0, 0 },  0
     * { 0, 0, 0, 1, 1, 1, 1, 0, 0 },  1
     * { 0, 0, 0, 1, 0, 0, 1, 0, 0 },  2  /\
     * { 0, 0, 0, 1, 1, 0, 1, 0, 0 },  3  -x
     * { 8, 1, 1, 0, 1, 0, 1, 1, 9 },  4
     * { 0, 0, 1, 0, 1, 0, 0, 0, 0 },  5  +x
     * { 0, 0, 1, 0, 1, 0, 0, 0, 0 },  6  \/
     * { 0, 0, 1, 1, 1, 0, 0, 0, 0 },  7
     * { 0, 0, 0, 0, 0, 0, 0, 0, 0 }   8
     */
    private static void fillWaypointSet() {
        Waypoint tempWpoint = new Waypoint();
        boolean firstIter = true;
        // add origin (first) waypoint to set because we've found it
        waypointSet.add(originWaypoint);
        // safe - originWaypoint is not fractional
        Pos.x = (int)originWaypoint.getX(); // set x, y to originWaypoint - to start filling waypointSet
        Pos.y = (int)originWaypoint.getY();

        // add waypoints until == last position
        while (Pos.x != lastWaypoint.getX() && Pos.y != lastWaypoint.getY()) {
            //region 1st loop iteration - skip y + 1 check
            if (firstIter) {
                if (workingMap[Pos.x - 1][Pos.y] == 1) {
                    tempWpoint.set(Pos.x - 1, Pos.y);
                    waypointSet.add(tempWpoint);
                    Pos.x -= 1;

                    firstIter = false;
                    continue;
                }
                else if (workingMap[Pos.x + 1][Pos.y] == 1) {
                    tempWpoint.set(Pos.x + 1, Pos.y);
                    waypointSet.add(tempWpoint);
                    Pos.x += 1;

                    firstIter = false;
                    continue;
                }
                else if (workingMap[Pos.x][Pos.y - 1] == 1) {
                    tempWpoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(tempWpoint);
                    Pos.y -= 1;

                    firstIter = false;
                    continue;
                }
            }
            //endregion

            else {
                // check upper cell
                if (workingMap[Pos.x - 1][Pos.y] == 1) {
                    tempWpoint.set(Pos.x - 1, Pos.y);
                    waypointSet.add(tempWpoint);
                    continue;
                }
                // check right cell
                else if (workingMap[Pos.x][Pos.y + 1] == 1) {
                    tempWpoint.set(Pos.x, Pos.y + 1);
                    waypointSet.add(tempWpoint);
                }
                // check lower cell
                else if (workingMap[Pos.x + 1][Pos.y] == 1) {
                    tempWpoint.set(Pos.x + 1, Pos.y);
                    waypointSet.add(tempWpoint);
                }
                // check left cell
                else if (workingMap[Pos.x][Pos.y - 1] == 1) {
                    tempWpoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(tempWpoint);
                }
            }
        }
        waypointSet.add(lastWaypoint);
    }

    //region class only helper class
    private static class Pos {
        static int x;
        static int y;

        protected static void set() {
        }


    }
    //endregion
}
