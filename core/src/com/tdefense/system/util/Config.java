package com.tdefense.system.util;

import com.tdefense.system.util.logging.Logger;

public final class Config {
    // window size
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;

    /** size of each atlas tile */
    public static final float TILE_SCALE = 32f;
    /** map array x length */
    public static final int MAP_LENGTH_X = 9;
    /** map array y length */
    public static final int MAP_LENGTH_Y = 9;

    public static final float PLAYER_MOV_SPEED = 65;
    public static final float ENEMY_MOV_SPEED = 120;

    /** {@link Logger#setLogLevel(int)}  */
    public static final int DEFAULT_LOG_LEVEL = Logger.LOG_ERROR;

    public static float ARCHER_TOWER_POSITION_X;
    public static float ARCHER_TOWER_POSITION_Y;
}