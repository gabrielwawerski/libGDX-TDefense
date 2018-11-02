package com.tdefense.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Enemy;
import com.tdefense.entity.Entity;

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
