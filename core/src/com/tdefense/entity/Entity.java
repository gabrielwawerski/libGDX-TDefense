package com.tdefense.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Texture texture;
    protected Sprite sprite;
    protected Vector2 positon;

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

    public Vector2 getPositon() {
        return positon;
    }

    public float getPositionX() {
        return positon.x;
    }

    public void setPositonX(float x) {
        positon.x = x;
    }

    public float getPositionY() {
        return positon.y;
    }

    public void setPositonY(float y) {
        positon.y = y;
    }

    public void setPositon(float x, float y) {
        positon.x = x;
        positon.y = y;
    }

    public void setPosition(Vector2 position) {
        this.positon.x = position.x;
        this.positon.y = position.y;
    }

    public void addPositionX(float x) {
        positon.x += x;
    }

    public void subPositionX(float x) {
        positon.x -= x;
    }

    public void addPositionY(float y) {
        positon.y += y;
    }

    public void subPositionY(float y) {
        positon.y -= y;
    }
}
