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
                {14, 13, 12,  0,  0,  0,  0,  0,  0 }, // 5
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 6
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 7
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }  // 8
        };
    }

    public static int[][] getSimpleMapData() {
        return new int[][] {
           // y:  0  1  2  3  4  5  6  7  8      x:
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 0
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 1
                { 0 , 0,  0,  0,  0,  0,  0,  0,  0 }, // 2
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 3
                { 1,  1,  1,  1,  1,  1,  1,  1,  1 }, // 4
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 5
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 6
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }, // 7
                { 0,  0,  0,  0,  0,  0,  0,  0,  0 }  // 8
        };
    }

    public static void translateOrderedMapData(Cell[][] cells, int[][] mapData) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {
                if (mapData[x][y] == CellType.PATH) {

                }

                else if
            }
        }
    }

    public static void translateSimpleMapData(int[][] mapData, Cell[][] cells) {
        for (int x = 0; x < Constant.MAP_LENGTH_X; x++) {
            for (int y = 0; y < Constant.MAP_LENGTH_Y; y++) {

                if (mapData[x][y] == Constant.GRASS_CODE) {
                    cells[x][y] = new Cell(CellType.GRASS, scale(x), scale(x), x, y);
                }
                else if (mapData[x][y] == Constant.PATH_CODE) {
                    cells[x][y] = new Cell(CellType.PATH, scale(x), scale(x), x, y);
                }
                else if (mapData[x][y] == Constant.START_CODE) {
                    Cell cell = new Cell(CellType.PATH_START, scale(x), scale(x), x, y);
                    cells[x][y] = cell;
                    CellMap.sSetStartCell(cell);
                }
                else if (mapData[x][y] == Constant.FINAL_CODE) {
                    Cell cell = new Cell(CellType.PATH_END, scale(x), scale(x), x, y);
                    cells[x][y] = cell;
                    CellMap.sSetEndCell(cell);
                }
            }
        }
    }

//    public CellMap getMapSlice(Cell[][] map, Cell startingPoint) {
//        int[][] temp = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
//        int[][] subArray = new int[temp.length][];
//        for (int i = 0; i < subArray.length; i++) {
//            subArray[i] = new int[2];
//            subArray[i][0] = temp[i][1];
//            subArray[i][1] = temp[i][2];
//        }
//    }

    /**
     * Scales provided value, by {@link Constant#TILE_SCALE}, so that after transformation - the value returned to caller
     * represents tile position in relation to other tiles, as if it was a part of map's tile "grid". (which is not
     * a real thing in this project as of now)
     * @param value the value to be scaled
     * @return scaled value
     * @author mx
     */
    public static float scale(float value) {
        return value * Constant.TILE_SCALE;
    }
}
