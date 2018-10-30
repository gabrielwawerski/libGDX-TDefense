package com.tdefense.entity_system.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.path.PathTraversable;

public abstract class EnemyEntity extends Entity implements PathTraversable {
    private Path path;

    public EnemyEntity(Texture texture) {
        super(texture);
    }

    public abstract void create();
}
