package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.entity_system.entity.EnemyEntity;
import com.tdefense.entity_system.entity.PlayerEntity;
import com.tdefense.system.asset_manager.AssetHandler;
import com.tdefense.tools.Constants;
import com.tdefense.world.entities.enemy.TestEnemy;
import com.tdefense.world.entities.player.Player;
import com.tdefense.world.map.Map;
import com.tdefense.world.map.path.Step;

class LogicHandler {
    private static final String TAG = LogicHandler.class.getSimpleName();

    // game related resources
    Map map;
    PlayerEntity player;
    TestEnemy testEnemy;

    private boolean waveStarted = false;

    void update(float deltaTime) {
        float playerMovSpeed = Constants.PLAYER_MOV_SPEED * deltaTime;

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            startWave(deltaTime);
            waveStarted = true;
        }

        if (waveStarted) {
                if (testEnemy.isStepCompletedd(map.getPath().getNextStepForBoolean())) {
                    if (map.getPath().getNextStepForBoolean() == Step.STEP_UP) {
                        testEnemy.stepUp(deltaTime);
                        map.getPath().stepAcquired();
                    } else if (map.getPath().getNextStepForBoolean() == Step.STEP_DOWN) {
                        testEnemy.stepDown(deltaTime);
                        map.getPath().stepAcquired();
                    } else if (map.getPath().getNextStepForBoolean() == Step.STEP_LEFT) {
                        testEnemy.stepLeft(deltaTime);
                        map.getPath().stepAcquired();
                    } else if (map.getPath().getNextStepForBoolean() == Step.STEP_RIGHT) {
                        testEnemy.stepRight(deltaTime);
                        map.getPath().stepAcquired();
                    }
                    testEnemy.setCurrentPosition();
                    testEnemy.nextMove(map.getPath().getNextStep(), deltaTime);
            }
        }

        // player controls logic
        if (Gdx.input.isKeyPressed(Input.Keys.W)) player.addPositionY(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) player.subPositionY(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) player.subPositionX(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) player.addPositionX(playerMovSpeed);

        // test code
//        if (Gdx.input.isKeyJustPressed(Input.Keys.E))     testEnemy.setPositon(testEnemy.getPosX(), testEnemy.getPosY() + 32f);
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) testEnemy.setPositon(testEnemy.getPosX(), testEnemy.getPosY() - 32f);
//        if (Gdx.input.isKeyJustPressed((Input.Keys.R)))   testEnemy.setPositon(map.getPathStart().getX(), map.getPathStart().getY());
//        if (Gdx.input.isKeyJustPressed(Input.Keys.F))     testEnemy.setPositon(map.getPathEnd().getX(), map.getPathEnd().getY());
    }

    void initialize() {
        player = new Player(AssetHandler.getInstance().get("player.png", Texture.class));
        map = new Map(AssetHandler.getInstance().get("tile.atlas", TextureAtlas.class));
        testEnemy = new TestEnemy(AssetHandler.getInstance().get("testEnemy.png", Texture.class));
        player.create();
        testEnemy.create();
    }

    void startWave(float deltaTime) {
        testEnemy.prepareForWave(map.getPathStart().getVector());
        testEnemy.setCurrentPosition();
        testEnemy.nextMove(map.getPath().getNextStep(), deltaTime);
    }
}
