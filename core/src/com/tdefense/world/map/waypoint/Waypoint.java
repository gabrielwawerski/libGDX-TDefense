package com.tdefense.world.map.waypoint;

import com.badlogic.gdx.math.Vector2;
import com.tdefense.world.map.Map;

public class Waypoint {
    private Vector2 container;
    private int arrayPositionX;
    private int arrayPositionY;

    public float getX() {
        return container.x;
    }

    public float getY() {
        return container.y;
    }

    public int getArrayPosX() {
        return arrayPositionX;
    }

    public int getArrayPosY() {
        return arrayPositionY;
    }

    public Vector2 getVector() {
        return container;
    }

    //region WaypointSetBuilder helper methods
    Waypoint() {
        container = new Vector2();
    }

    Waypoint(int x, int y) {
        arrayPositionX = x;
        arrayPositionY = y;
        container = new Vector2(Map.scale(x), Map.scale(y));
    }

//    void set(float x, float y) {
//        container.x = Map.scale(x);
//        container.y = Map.scale(y);
//    }

    void set(int x, int y) {
        arrayPositionX = x;
        arrayPositionY = y;
        container.x = Map.scale(x);
        container.y = Map.scale(y);
    }
    //endregion
}
