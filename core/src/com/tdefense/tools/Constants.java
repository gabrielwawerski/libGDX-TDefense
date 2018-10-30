package com.tdefense.tools;

import com.tdefense.tools.logging.Logger;

public class Constants {
    // window size
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;

    // world size
    public static final int WORLD_WIDTH = 100;
    public static final int WORLD_HEIGHT = 100;
    // map related
    public static final int MAX_TILE_X = 11;
    public static final int MAX_TILE_Y = 11;
    public static final float TILE_SCALE = 32f;  // the size of every tile from tile atlas

    public static final float PLAYER_MOV_SPEED = 65;
    public static final float ENEMY_MOV_SPEED = 15;

    /** {@link Logger#setLogLevel(int)}  */
    public static final int DEFAULT_LOG_LEVEL = Logger.LOG_ERROR;
}