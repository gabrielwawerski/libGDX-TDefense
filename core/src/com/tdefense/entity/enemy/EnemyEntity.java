package com.tdefense.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Enemy;
import com.tdefense.entity.Entity;
import com.tdefense.world.util.WaypointSet;

public class EnemyEntity extends Entity implements Enemy {
    private WaypointSet waypointSet;

    public EnemyEntity(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, positon.x, positon.y);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        positon = new Vector2();
    }

    public void setWaypointSet(WaypointSet waypointSet) {
        this.waypointSet = waypointSet;
    }
}
