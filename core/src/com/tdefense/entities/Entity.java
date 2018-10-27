package com.tdefense.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.tdefense.Constants;
import com.tdefense.Drawable;

public abstract class Entity implements Drawable {
    private Texture texture;
    private Sprite sprite;
    private Vector2 positon;

    public Entity(Texture texture) {
        this.texture = texture;
        sprite = new Sprite(texture);
        positon = new Vector2(Constants.MAP_SIZE_X / 2.f, Constants.MAP_SIZE_Y / 2.f);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, positon.x, positon.y);
    }

    public float getPosX() {
        return positon.x;
    }

    public float getPosY() {
        return positon.y;
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
