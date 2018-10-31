package com.tdefense.world.map.waypoint;

import com.badlogic.gdx.math.Vector2;

public class Waypoint {
    private Vector2 container;

    public float getX() {
        return container.x;
    }

    public float getY() {
        return container.y;
    }

    public Vector2 getVector() {
        return container;
    }

    //region WaypointBuilder helper methods
    Waypoint() {
        container = new Vector2();
    }

    Waypoint(float x, float y) {
        container = new Vector2(x, y);
    }

    protected void set(float x, float y) {
        container.x = x;
        container.y = y;
    }
    //endregion
}
