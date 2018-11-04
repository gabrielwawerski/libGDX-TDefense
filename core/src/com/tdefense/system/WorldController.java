package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.tdefense.entity.enemy.EnemyEntity;
import com.tdefense.entity.player.PlayerEntity;
import com.tdefense.system.asset_manager.AssetHandler;
import com.tdefense.world.map.CellMap;

/**
 * Handles all logic related operations, such as updating entity_concrete positions and all other non-graphic related business.
 * {@link #update(float deltaTime)}
 */
class WorldController {
    // resources shared with WorldRenderer
    CellMap map;
    PlayerEntity player;
    EnemyEntity enemy;
    private boolean startEnemyMovement = false;

    private static final String TAG = WorldController.class.getSimpleName();

    void update(float deltaTime) {
        float playerMovSpeedDelta = Constant.PLAYER_MOV_SPEED * deltaTime;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            enemy.setPosition(enemy.waypointSet.getFirstWaypoint().getVector());
            startEnemyMovement = true;
        }

        if (startEnemyMovement && !enemy.wasPathTraversed()) {
            enemy.move(deltaTime);
        }

        // player controls logic
        if (Gdx.input.isKeyPressed(Input.Keys.W)) player.addPositionY(playerMovSpeedDelta);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) player.subPositionY(playerMovSpeedDelta);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) player.subPositionX(playerMovSpeedDelta);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) player.addPositionX(playerMovSpeedDelta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
            enemy.setPositon(map.waypointSet.waypoints.get(0).getX(), map.waypointSet.waypoints.get(0).getY());

        if (Gdx.input.isKeyJustPressed(Input.Keys.F))
            enemy.setPositon(
                    map.waypointSet.waypoints.get(map.waypointSet.waypoints.size() - 1).getX(),
                    map.waypointSet.waypoints.get(map.waypointSet.waypoints.size() - 1).getY());

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            enemy.addPositionY(5);
    }

    void initialize() {
        AssetHandler.getInstance().loadAssets(true);
        map = new CellMap(AssetHandler.getInstance().get("tile.atlas", TextureAtlas.class));
        map.create();
        player = new PlayerEntity(AssetHandler.getInstance().get("player.png", Texture.class));
        player.create();
        enemy = new EnemyEntity(AssetHandler.getInstance().get("enemy.png", Texture.class));
        enemy.create();
        enemy.addWaypointSet(map.getWaypointSet());
    }
}
