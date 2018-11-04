package com.tdefense.world.util;

import com.tdefense.system.Constant;
import com.tdefense.world.map.CellMap;

public class MapUtils {
    public static int[][] getOrderedMapData() {
        return new int[][] {
           // y:  0   1   2   3   4   5   6   7   8     x:
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 0
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 1
                { 0,  0,  0,  0,  7,  6,  5,  4,  0 }, // 2
                { 0,  0, 10,  9,  8,  0,  0,  3,  0 }, // 3
                { 0,  0, 11,  0,  0,  0,  0,  2,  1 }, // 4
                { 0,  0, 12,  0,  0,  0,  0,  0,  0 }, // 5
                {15, 14, 13,  0,  0,  0,  0,  0,  0 }, // 6
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 7
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }  // 8
        };
    }

    public static void orderedMapDataToCells(CellMap cellMap) {
        Cell[][] cells = cellMap.getCells();
        int[][] mapData = cellMap.getMapData();

        for (int x = 0; x < mapData.length; x++) {
            for (int y = 0; y < mapData[x].length; y++) {
                if (mapData[x][y] == 0) {
                    cells[x][y] = new Cell(cellMap.getGrassTile(), CellType.GRASS, toMap(x), toMap(y), x, y);
                } else if (mapData[x][y] != 0) {
                    cells[x][y] = new Cell(cellMap.getPathTile(), CellType.PATH, toMap(x), toMap(y), x, y);
                }
            }
        }
    }

    /**
     * Scales provided value, by {@link Constant#TILE_SCALE}, so that after transformation - the value returned to caller
     * represents tile position in relation to other tiles, as if it was a part of map's tile "grid". (which is not
     * a real thing in this project as of now)
     * @param value the value to be scaled
     * @return scaled value
     * @author mx
     */
    public static float toMap(float value) {
        return value * Constant.TILE_SCALE;
    }
}
