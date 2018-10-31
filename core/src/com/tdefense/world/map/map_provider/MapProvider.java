package com.tdefense.world.map.map_provider;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * {@link com.tdefense.world.map.Map} supplementing class, which offers maps that have been read from file, using
 * {@code MapLoader} class.
 * @author mx01
 * @date 31.10.2018
 */
public class MapProvider {
    /**
     * Map must have it's origin point set to the last y index of any x index. Final point must be set to first y index
     * of any x index.
     * <table>
     *     <tr><td>0</td> <td>grass code (grass tile)</td></tr>
     *     <tr><td>1</td> <td>waypoint code (path tile)</td></tr>
     *     <tr><td>9</td> <td>origin point code (starting point, path tile</td></tr>
     *     <tr><td>8</td> <td>final point code (path tile)</td></tr>
     * </table>
     * @return map data, presented in two dimensional int array
     */
    public static int[][] getMap() {
        return new int[][] {
      // y index: 0  1  2  3  4  5  6  7  8       x index:
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 0
                { 0, 0, 0, 1, 1, 1, 1, 0, 0 }, // 1
                { 0, 0, 0, 1, 0, 0, 1, 0, 0 }, // 2
                { 0, 0, 0, 1, 1, 0, 1, 0, 0 }, // 3
                { 8, 1, 1, 0, 1, 0, 1, 1, 9 }, // 4
                { 0, 0, 1, 0, 1, 0, 0, 0, 0 }, // 5
                { 0, 0, 1, 0, 1, 0, 0, 0, 0 }, // 6
                { 0, 0, 1, 1, 1, 0, 0, 0, 0 }, // 7
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }  // 8
        };
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
