package com.tdefense.world.entity_concrete.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.player.PlayerEntity;

public class Player extends PlayerEntity {
    public Player(Texture texture) {
        super(texture);
    }

    @Override
    public void create() {
        this.sprite = new Sprite(texture);
        this.positon = new Vector2();
    }
}