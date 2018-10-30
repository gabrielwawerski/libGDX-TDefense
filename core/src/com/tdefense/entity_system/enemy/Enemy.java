package com.tdefense.entity_system.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity_system.entity.Entity;

public class Enemy extends Entity {
    public Enemy(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch) {

    }

    @Override
    public void create() {
        this.sprite = new Sprite(texture);
        this.positon = new Vector2();
    }
}
