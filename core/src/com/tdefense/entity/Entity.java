package com.tdefense.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Texture texture;
    protected Sprite sprite;
    protected Vector2 position;

    public Entity(Texture texture) {
        this.texture = texture;
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public abstract void draw(Batch batch);

    public abstract void create();

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getPositionX() {
        return position.x;
    }

    public void setPositonX(float x) {
        position.x = x;
    }

    public float getPositionY() {
        return position.y;
    }

    public void setPositonY(float y) {
        position.y = y;
    }

    public void setPositon(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setPosition(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void addPositionX(float x) {
        position.x += x;
    }

    public void subPositionX(float x) {
        position.x -= x;
    }

    public void addPositionY(float y) {
        position.y += y;
    }

    public void subPositionY(float y) {
        position.y -= y;
    }
}
