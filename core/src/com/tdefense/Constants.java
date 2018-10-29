package com.tdefense;

import com.tdefense.logging.Logging;

public class Constants {
    // window size
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    // map related
    public static final int MAX_TILE_X = 11;
    public static final int MAX_TILE_Y = 10;
    public static final int TILE_SCALE = 32;  // size of every tile from tile atlas

    public static final float PLAYER_MOV_SPEED = 100;
    public static final float ENEMY_MOV_SPEED = 15;

    /** {@link com.tdefense.logging.Logging#setLogLevel(int)}  */
    public static final int DEFAULT_LOG_LEVEL = Logging.LOG_ERROR;
}