package com.tdefense.utility.coordinate;

import com.badlogic.gdx.math.Vector2;
import com.tdefense.tools.Constants;

/**
 * Use this class if you want to specify desired coordinate on the map. Given {@code x}, {@code y} values
 * will be calculated to point to the relative (0, 0) origin of the tile.
 */
public class Coordinate {
    private Vector2 container;

    public Coordinate() {
        container = new Vector2();
        container.x = 0f;
        container.y = 0f;
    }

    public Coordinate(float x, float y) {
        container = new Vector2();
        container.x = x * Constants.TILE_SCALE;
        container.y = y * Constants.TILE_SCALE;
    }

    public Coordinate(Vector2 coordinate) {
        container = coordinate;
    }

    public void set(float x, float y) {
        container.x = x * Constants.TILE_SCALE;
        container.y = y * Constants.TILE_SCALE;
    }

    public float getX() {
        return container.x;
    }

    public float getY() {
        return container.y;
    }
}
