package com.tdefense.entity_system.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class PlayerEntity extends Entity {
    public PlayerEntity(Texture texture) {
        super(texture);
    }

    public abstract void create();
}
