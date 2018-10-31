package com.tdefense.world.map.waypoint;

import com.badlogic.gdx.math.Vector2;

public class Waypoint {
    private Vector2 container;

    public Waypoint(float x, float y) {
        container = new Vector2(x, y);
    }

    public Waypoint(int x, int y) {
        container = new Vector2(x, y);
    }

    public Waypoint(Vector2 vector) {
        container = vector;
    }

    public Vector2 getVector() {
        return container;
    }
}
