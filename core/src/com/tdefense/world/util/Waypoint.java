package com.tdefense.world.util;

import com.badlogic.gdx.math.Vector2;

public class Waypoint {
    private Vector2 container;

    Waypoint(float x, float y) {
        container = new Vector2(x, y);
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

    void setX(float x) {
        container.x = x;
    }

    void setY(float y) {
        container.y = y;
    }
}
