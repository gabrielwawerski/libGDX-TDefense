package com.tdefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Sprite sprite;
    private Vector2 positon;

    public Player(Texture texture, Sprite sprite, Vector2 positon) {
        this.texture = texture;
        this.sprite = sprite;
        this.positon = positon;
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
