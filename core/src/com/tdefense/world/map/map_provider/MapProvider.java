package com.tdefense.world.map.map_provider;

import com.tdefense.system.Constant;

/**
 * {@link com.tdefense.world.map.Map} supplementing class, which offers maps that have been read from file, using
 * {@code MapLoader} class.<p>
 * When adding new maps to datamaps file, they should have their origin points set to the last y index of any x index.
 * Final point must be set to first y index of any x index. See {@link Constant#ORIGIN_CODE} and
 * {@link Constant#FINAL_CODE}
 * <table>
 *     <tr><td>0</td> <td>grass code (grass tile)</td></tr>
 *     <tr><td>1</td> <td>waypoint code (path tile)</td></tr>
 *     <tr><td>9</td> <td>origin point code (starting point, path tile</td></tr>
 *     <tr><td>8</td> <td>final point code (path tile)</td></tr>
 * </table>
 * @author mx01
 * @date 31.10.2018
 */
public final class MapProvider {
    private static final int[][] MAP_01 = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 1, 0, 0 },
            { 8, 1, 1, 1, 1, 0, 1, 1, 9 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public static int[][] getMap() {
        return MAP_01;
    }

    //region now irrelevant, planned features
    public static void loadMaps() {
        // load maps from file (make original format?), into maps static arraylist (init in initialize() method) (later)
        // new MapLoader class?
        // Libgdx's FileHandle has a helper method to easily read a text file into a String
    }

    public static void createMap() {
        // ability to create maps at runtime: (muuuch later)
            // random map generator - using seed
            // map creator
    }
    //endregion
}
