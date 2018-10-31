package com.tdefense.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Entity;
import com.tdefense.world.map.waypoint.WaypointSet;

public class EnemyEntity extends Entity implements Enemy {
    private WaypointSet waypointSet;
    public EnemyEntity(Texture texture) {
        super(texture);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        positon = new Vector2();
    }
}
