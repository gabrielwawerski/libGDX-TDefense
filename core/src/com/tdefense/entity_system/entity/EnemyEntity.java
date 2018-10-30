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
    private Vector2 currentPosition;

    public EnemyEntity(Texture texture) {
        super(texture);
    }

    public boolean isStepCompleted(Step step) {
        if (step.getStepEnum() == StepEnum.STEP_UP) {
            return currentPosition.y >= currentPosition.y + Constants.TILE_SCALE;
        } else if (step.getStepEnum() == StepEnum.STEP_DOWN) {
            return currentPosition.y <= currentPosition.y - Constants.TILE_SCALE;
        } else if (step.getStepEnum() == StepEnum.STEP_LEFT) {
            return currentPosition.x <= currentPosition.x - Constants.TILE_SCALE;
        } else if (step.getStepEnum() == StepEnum.STEP_RIGHT) {
            return currentPosition.x >= currentPosition.x + Constants.TILE_SCALE;
        }
        return false;
    }

    public void stepUp(float deltaTime) {
        positon.y += Constants.TILE_SCALE * deltaTime;
    }

    public void stepDown(float deltaTime) {
        positon.y -= Constants.TILE_SCALE * deltaTime;
    }

    public void stepLeft(float deltaTime) {
        positon.x -= Constants.TILE_SCALE * deltaTime;
    }

    public void stepRight(float deltaTime) {
        positon.x += Constants.TILE_SCALE * deltaTime;
    }

    public void prepareForWave(Vector2 startPosition) {
        setPositon(startPosition.x, startPosition.y);
    }

    public void setCurrentPosition() {
        currentPosition = positon;
    }
}
