package com.tdefense.entity_system.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity_system.HealthBar;

public abstract class Entity {
    protected Texture texture;
    protected Sprite sprite;
    protected Vector2 positon;

    public Entity(Texture texture) {
        this.texture = texture;
    }

    public abstract void draw(Batch batch);

    public abstract void create();

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getPosX() {
        return positon.x;
    }

    public float getPosY() {
        return positon.y;
    }

    public void setPositonX(float x) {
        positon.x = x;
    }

    public void setPositonY(float y) {
        positon.y = y;
    }

    public void setPositon(float x, float y) {
        positon.x = x;
        positon.y = y;
    }
}
