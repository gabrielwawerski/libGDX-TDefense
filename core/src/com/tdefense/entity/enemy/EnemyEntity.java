package com.tdefense.entity.enemy;

import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Entity;
import com.tdefense.world.map.waypoint.WaypointSet;

public class EnemyEntity extends Entity implements Enemy {

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
}
