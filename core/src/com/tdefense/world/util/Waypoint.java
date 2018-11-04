package com.tdefense.world.util;

import com.badlogic.gdx.math.Vector2;

public class Waypoint {
    private Vector2 container;
    private Step step;

    Waypoint(float x, float y, Step step) {
        container = new Vector2(x, y);
        this.step = step;
    }

    public Vector2 getVector() {
        return container;
    }

    public float getX() {
        return container.x;
    }

    public float getY() {
        return container.y;
    }

    public Step getStep() {
        return step;
    }

    void setX(float x) {
        container.x = x;
    }

    void setY(float y) {
        container.y = y;
    }

    void setStep(Step step) {
        this.step = step;
    }
}
