package com.tdefense.world.map.path;

import com.badlogic.gdx.math.Vector2;
import com.tdefense.tools.Constants;

public class Step {
    private Vector2 step;

    public static final Vector2 STEP_UP    = new Vector2(0f, Constants.TILE_SCALE);
    public static final Vector2 STEP_DOWN  = new Vector2(0f, -Constants.TILE_SCALE);
    public static final Vector2 STEP_LEFT  = new Vector2(-Constants.TILE_SCALE, 0f);
    public static final Vector2 STEP_RIGHT = new Vector2(Constants.TILE_SCALE,  0f);

    public Step(Vector2 step) {
        this.step = step;
    }

    public Vector2 get() {
        return step;
    }
}
