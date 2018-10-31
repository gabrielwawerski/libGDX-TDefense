package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.entity_system.entity.EnemyEntity;
import com.tdefense.entity_system.entity.PlayerEntity;
import com.tdefense.system.asset_manager.AssetHandler;
import com.tdefense.tools.Constant;
import com.tdefense.world.entities.enemy.TestEnemy;
import com.tdefense.world.entities.player.Player;
import com.tdefense.world.map.Map;

/**
 * Handles all logic related operations, such as updating entities positions and all other non-graphic related business.
 * {@link #update(float deltaTime)}
 */
class LogicHandler {
    private static final String TAG = LogicHandler.class.getSimpleName();
    // game related resources
    Map map;
    PlayerEntity player;
    EnemyEntity enemy;

    void update(float deltaTime) {
        float playerMovSpeed = Constant.PLAYER_MOV_SPEED * deltaTime;

        // player controls logic
        if (Gdx.input.isKeyPressed(Input.Keys.W)) player.addPositionY(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) player.subPositionY(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) player.subPositionX(playerMovSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) player.addPositionX(playerMovSpeed);
        // test code
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))     enemy.setPositon(enemy.getPositionX(), enemy.getPositionY() + 32f);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) enemy.setPositon(enemy.getPositionX(), enemy.getPositionY() - 32f);
//        if (Gdx.input.isKeyJustPressed((Input.Keys.R)))   enemy.setPositon(mapProvider.getPathStart().getX(), mapProvider.getPathStart().getY());
//        if (Gdx.input.isKeyJustPressed(Input.Keys.F))     enemy.setPositon(mapProvider.getPathEnd().getX(), mapProvider.getPathEnd().getY());
    }

    /**
     * {@link AssetHandler#loadAssets(boolean)} MUST be called before calling this method! It also is highly recommended
     * to pass {@code true} to it, so you are 100% sure all assets have been loaded.
     */
    void initialize() {
        map = new Map(AssetHandler.getInstance().get("tile.atlas", TextureAtlas.class));
        player = new Player(AssetHandler.getInstance().get("player.png", Texture.class));
        enemy = new TestEnemy(AssetHandler.getInstance().get("enemy.png", Texture.class));
        // move to create method?
        map.create();
        player.create();
        enemy.create();
    }
}
