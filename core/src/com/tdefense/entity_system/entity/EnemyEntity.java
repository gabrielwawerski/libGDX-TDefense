package com.tdefense.entity_system.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.tools.Constants;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.path.PathTraversable;
import com.tdefense.world.map.path.Step;
import com.tdefense.world.map.path.StepEnum;

import java.util.ArrayList;

public abstract class EnemyEntity extends Entity implements Enemy, PathTraversable {
    protected boolean moveCompleted;
    protected boolean isMoving;
    protected boolean isStepCompleted;

    public EnemyEntity(Texture texture) {
        super(texture);
        moveCompleted = true;
        isMoving = false;
        isStepCompleted = false;

    }

    public boolean isStepCompleted() {
        return isStepCompleted;
    }

    public abstract void create();

    public void nextMove(Step step, float deltaTime) {

    }

    public void prepareForWave(Vector2 startPosition) {
        setPositon(startPosition.x, startPosition.y);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setCurrentPosition() {
        setPosition(getPositon());
    }
}
