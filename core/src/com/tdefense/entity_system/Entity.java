package com.tdefense.entity_system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.Drawable;

public abstract class Entity implements Drawable {
    private Texture texture;
    private Sprite sprite;
    private Vector2 positon;

    public Entity(Texture texture) {
        this.texture = texture;
        sprite = new Sprite(texture);
        positon = new Vector2();
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, positon.x, positon.y);
    }

    public void draw(Batch batch, float x, float y) {
        batch.draw(sprite, x, y);
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

    public void addPosition(float x, float y) {
        positon.x += x;
        positon.y += y;
    }

    public void subPosition(float x, float y) {
        positon.x -= x;
        positon.y -= y;
    }
}
