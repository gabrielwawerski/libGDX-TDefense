package com.tdefense.world.entities.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity_system.entity.EnemyEntity;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.path.Step;

public class TestEnemy extends EnemyEntity {
    private Vector2 nextPosition;
    private Path path;

    public TestEnemy(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, positon.x, positon.y);
    }

    public void create() {
        sprite = new Sprite(texture);
        positon = new Vector2();
        nextPosition = new Vector2();
    }

    public void getPath(Path path) {
        this.path = path;
    }

    public void move() {
    }

    public void currentPosition() {
    }

    public void nextPosition(Step step) {
        nextPosition.set(step.get());
    }
}
