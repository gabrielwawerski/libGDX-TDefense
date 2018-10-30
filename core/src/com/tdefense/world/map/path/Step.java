package com.tdefense.world.map.path;

import com.badlogic.gdx.math.Vector2;
import com.tdefense.tools.Constants;

public final class Step {
    private Vector2 step;
    private StepEnum stepEnum;

    public static final Step STEP_UP    = new Step(new Vector2(0f, Constants.TILE_SCALE), StepEnum.STEP_UP);
    public static final Step STEP_DOWN  = new Step(new Vector2(0f, -Constants.TILE_SCALE), StepEnum.STEP_DOWN);
    public static final Step STEP_LEFT  = new Step(new Vector2(-Constants.TILE_SCALE, 0f), StepEnum.STEP_LEFT);
    public static final Step STEP_RIGHT = new Step(new Vector2(Constants.TILE_SCALE,  0f), StepEnum.STEP_RIGHT);

    public Step(Vector2 step, StepEnum stepEnum) {
        this.step = step;
        this.stepEnum = stepEnum;
    }

    public StepEnum getStepEnum() {
        return stepEnum;
    }
}
