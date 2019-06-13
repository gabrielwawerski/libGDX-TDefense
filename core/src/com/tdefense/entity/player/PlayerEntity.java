package com.tdefense.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Entity;

public class PlayerEntity extends Entity {
    public PlayerEntity(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, position.x, position.y);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        position = new Vector2();
    }
}
