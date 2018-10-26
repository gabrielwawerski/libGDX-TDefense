package com.tdefense.entities.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entities.Entity;

public class Player extends Entity {
    public Player(Texture texture, Sprite sprite, Vector2 positon) {
        super(texture, sprite, positon);
    }
}
