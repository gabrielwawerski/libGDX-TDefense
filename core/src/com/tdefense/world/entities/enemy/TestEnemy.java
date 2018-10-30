package com.tdefense.world.entities.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity_system.entity.EnemyEntity;
import com.tdefense.tools.Constants;
import com.tdefense.world.map.path.Path;
import com.tdefense.world.map.path.Step;
import com.tdefense.world.map.path.StepEnum;

public class TestEnemy extends EnemyEntity {
    public TestEnemy(Texture texture) {
        super(texture);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, positon.x, positon.y);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        positon = new Vector2();
    }

    public boolean isStepCompletedd(Step step) {
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
}
