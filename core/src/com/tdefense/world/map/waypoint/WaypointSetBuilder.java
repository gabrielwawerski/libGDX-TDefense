package com.tdefense.world.map.waypoint;

import com.tdefense.system.Constant;
import com.tdefense.system.logging.Logger;

/**
 * {@link WaypointSet} building class. Supply your {@code int[][] map} to {@link #getWaypointSet(int[][])} method,
 * in order to receive newly created {@code WaypointSet} instance, containing an array of ordered coordinates (x, y),
 * corresponding to each map path tile's position - extracted from the supplied map array. Each coordinate is stored
 * in {@link Waypoint} object - with first being {@link #originWaypoint} and the last as {@link #finalWaypoint}.
 * @author mx01
 * @date 31.10.2018
 */
public class WaypointSetBuilder {
    private static int[][] workingMap;
    private static int lengthX;
    private static int lengthY;

    private static WaypointSet waypointSet;
    /** first coordinate(x, y) of supplied {@code int[][] map} 2d array. */
    private static Waypoint originWaypoint;
    /** last coordinate(x,y) of supplied {@code int[][] map} 2d array.*/
    private static Waypoint finalWaypoint;

    private WaypointSetBuilder() { }

    private static final String TAG = WaypointSetBuilder.class.getSimpleName();

    /**
     * Returns {@link WaypointSet} instance, containing correctly ordered waypoints, beginning with
     * {@link Constant#ORIGIN_CODE} and ending with {@link Constant#FINAL_CODE} in map data.
     * @param map map data from {@link com.tdefense.world.map.map_provider.MapProvider}
     * @return ordered {@code Waypoint} objects stored in {@code WaypointSet)
     */
    public static WaypointSet getWaypointSet(int[][] map) {
        workingMap = map;
        lengthX = workingMap.length;
        lengthY = workingMap[0].length;
        waypointSet = new WaypointSet();
        originWaypoint = getOriginWaypoint();
        finalWaypoint = getFinalWaypoint();
        waypointSet.add(originWaypoint);
        fillWaypointSet();
        waypointSet.add(getFinalWaypoint());
        return waypointSet;
    }

    private static Waypoint getOriginWaypoint() {
        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                if (workingMap[x][y] == Constant.ORIGIN_CODE)
                    return new Waypoint(x, y);
            }
        }

        String errorMessage = "Invalid workingMap array. Point of origin (9) not found.";
        Logger.error(TAG, errorMessage);
        throw new IndexOutOfBoundsException(errorMessage);
    }

    private static Waypoint getFinalWaypoint() {
        for (int x = 0; x < lengthX; x++) {
            if (workingMap[x][0] == Constant.FINAL_CODE)
                return new Waypoint(x, 0);
        }

        String errorMessage = "Invalid workingMap array. Last point (8) not found.";
        Logger.error(TAG, errorMessage);
        throw new IndexOutOfBoundsException(errorMessage);
    }

    // TODO TEST
    /**
     *          <- -y   +y ->
     *y: 0  1  2  3  4  5  6  7  8    x:
     *
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
        Waypoint tempWaypoint = new Waypoint();
        Waypoint lastWaypoint = new Waypoint();
        boolean firstIter = true;
        int[][] mapCopy = workingMap.clone();

        // safe cast - originWaypoint is not fractional
        Pos.x = originWaypoint.getArrayPosX(); // set x, y to originWaypoint - to start filling waypointSet
        Pos.y = originWaypoint.getArrayPosX();
        int iteracja = 0;
        int lastX = originWaypoint.getArrayPosX();
        int lastY = originWaypoint.getArrayPosY();
        // TODO check entire loop
        // add waypoints until == last position
        while (mapCopy[Pos.x - 1][Pos.y] != 8 && mapCopy[Pos.x][Pos.y + 1] != 8
                && mapCopy[Pos.x + 1][Pos.y] != 8 && mapCopy[Pos.x][Pos.y - 1] != 8) {
//            System.out.println(iteracja++);
            Logger.log("w petli while", "iteracja: " + iteracja++);
            //region 1st loop iteration - skips y + 1 check
            if (firstIter) {
                if (mapCopy[Pos.x - 1][Pos.y] == 1 && Pos.x - 1 != lastX && Pos.y != lastY) {
                    lastX = Pos.x - 1;
                    lastY = Pos.y;
                    lastWaypoint.set(Pos.x - 1, Pos.y);
                    waypointSet.add(new Waypoint(Pos.x - 1, Pos.y));
                    Pos.x -= 1;
                    firstIter = false;
                } else if (mapCopy[Pos.x + 1][Pos.y] == 1) {
                    lastX = Pos.x + 1;
                    lastY = Pos.y;
                    lastWaypoint.set(Pos.x + 1, Pos.y);
                    waypointSet.add(new Waypoint(Pos.x + 1, Pos.y));
                    Pos.x += 1;
                    firstIter = false;
                } else if (mapCopy[Pos.x][Pos.y - 1] == 1) {
                    lastX = Pos.x;
                    lastY = Pos.y - 1;
                    lastWaypoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(new Waypoint(Pos.x, Pos.y - 1));
                    Pos.y -= 1;
                    firstIter = false;
                }
            }
            //endregion

            else {
                if (mapCopy[Pos.x - 1][Pos.y] == 1) {
                    lastX = Pos.x - 1;
                    lastY = Pos.y;
                    lastWaypoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(new Waypoint(Pos.x - 1, Pos.y));
                    Pos.x -= 1;
                } else if (mapCopy[Pos.x][Pos.y + 1] == 1) {
                    lastX = Pos.x;
                    lastY = Pos.y + 1;
                    lastWaypoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(new Waypoint(Pos.x, Pos.y + 1));
                    Pos.y += 1;
                } else if (mapCopy[Pos.x + 1][Pos.y] == 1) {
                    lastX = Pos.x + 1;
                    lastY = Pos.y;
                    lastWaypoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(new Waypoint(Pos.x + 1, Pos.y));
                    Pos.x += 1;
                } else if (mapCopy[Pos.x][Pos.y - 1] == 1) {
                    lastX = Pos.x;
                    lastY = Pos.y - 1;
                    lastWaypoint.set(Pos.x, Pos.y - 1);
                    waypointSet.add(new Waypoint(Pos.x, Pos.y - 1));
                    Pos.y -= 1;
                }
            }
        }
    }

    //region helper static inner class
    private static class Pos {
        static int x;
        static int y;
    }
    //endregion
}
