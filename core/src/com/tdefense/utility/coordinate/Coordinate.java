package com.tdefense.utility.coordinate;

import com.badlogic.gdx.math.Vector2;
import com.tdefense.tools.Constant;

/**
 * Use this class if you want to specify desired coordinate on the map_provider. Given {@code x}, {@code y} values
 * will be calculated to point to the relative (0, 0) origin of the tile.
 */
public class Coordinate {
    private Vector2 container;

    public Coordinate() {
        container = new Vector2();
    }

    public Coordinate(float x, float y) {
        container = new Vector2();
        container.x = x * Constant.TILE_SCALE;
        container.y = y * Constant.TILE_SCALE;
    }

    public Coordinate(Vector2 coordinate) {
        container = coordinate;
    }

    public Vector2 getVector() {
        return container;
    }

    public void set(float x, float y) {
        container.x = x * Constant.TILE_SCALE;
        container.y = y * Constant.TILE_SCALE;
    }

    public void set(Vector2 position) {
        container = position;
    }

    public float getX() {
        return container.x;
    }

    public float getY() {
        return container.y;
    }
}
