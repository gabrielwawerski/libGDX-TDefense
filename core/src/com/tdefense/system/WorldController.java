package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tdefense.entity.enemy.EnemyEntity;
import com.tdefense.entity.player.PlayerEntity;
import com.tdefense.system.asset_manager.AssetHandler;
import com.tdefense.world.map.CellMap;
import com.tdefense.world.map.Tile;
import com.tdefense.world.util.CellType;
import com.tdefense.world.util.MapUtils;

/**
 * Handles all logic related operations, such as updating entity_concrete positions and all other non-graphic related business.
 * {@link #update(float deltaTime)}
 */
class WorldController extends InputAdapter {
    // resources shared with WorldRenderer
    CellMap map;
    PlayerEntity player;
    EnemyEntity enemy;
    private OrthographicCamera camera;
    private boolean startEnemyMovement = false;
    private Tile archerTower;
    private Vector3 touchPos;

    private static final String TAG = WorldController.class.getSimpleName();

    WorldController(OrthographicCamera camera) {
        Gdx.input.setInputProcessor(this);
        this.camera = camera;
        touchPos = new Vector3();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.x = screenX;
        touchPos.y = screenY;
        camera.unproject(touchPos);
        touchPos.x /= Constant.TILE_SCALE;
        touchPos.y /= Constant.TILE_SCALE;

        if (button == Input.Buttons.LEFT) {
            if (map.getCellAt((int)touchPos.x, (int)touchPos.y).getCellType() != CellType.PATH) {
                map.getCellAt((int) touchPos.x, (int) touchPos.y).setTile(archerTower);
                return true;
            }
        }
        return false;
    }

    void update(float deltaTime) {
        float playerMovSpeedDelta = Constant.PLAYER_MOV_SPEED * deltaTime;

        // enemy debug stuff
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

        // enemy debug stuff
        if (Gdx.input.isKeyJustPressed(Input.Keys.E))
            enemy.setPositon(map.waypointSet.waypoints.get(0).getX(), map.waypointSet.waypoints.get(0).getY());

        if (Gdx.input.isKeyJustPressed(Input.Keys.F))
            enemy.setPositon(
                    map.waypointSet.waypoints.get(map.waypointSet.waypoints.size() - 1).getX(),
                    map.waypointSet.waypoints.get(map.waypointSet.waypoints.size() - 1).getY());

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            enemy.addPositionY(5 * deltaTime);
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
        archerTower = new Tile(AssetHandler.getInstance().get("tile.atlas", TextureAtlas.class).findRegion("grass2"));
    }
}
