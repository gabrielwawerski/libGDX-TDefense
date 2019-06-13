package com.tdefense.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.tdefense.entity.enemy.EnemyEntity;
import com.tdefense.entity.player.PlayerEntity;
import com.tdefense.system.asset_handler.AssetHandler;
import com.tdefense.system.util.Config;
import com.tdefense.system.util.logging.Logger;
import com.tdefense.world.map.CellMap;
import com.tdefense.world.util.CellType;

import static com.tdefense.system.util.Config.*;

/**
 * Handles all logic related operations, such as updating entity_concrete positions and all other non-graphic related business.
 * {@link #update(float deltaTime)}
 */
class GameController extends InputAdapter {
    CellMap map;
    PlayerEntity player;
    EnemyEntity enemy;

    private OrthographicCamera camera;
    private OrthographicCamera cameraGUI;

    private boolean buyMode;

    private boolean startEnemyMovement;
    private Vector3 touchPos;

    private GameRenderer renderer;

    private static final String TAG = GameController.class.getSimpleName();

    GameController(GameRenderer renderer) {
        this.renderer = renderer;
        Gdx.input.setInputProcessor(this);
        camera = renderer.camera;
        cameraGUI = renderer.cameraGUI;
        touchPos = new Vector3();
        buyMode = false;
        startEnemyMovement = false;
    }

    void initialize() {
        AssetHandler handler = AssetHandler.getInstance();
        handler.loadAssets();
        map = new CellMap(handler.get("tile.atlas", TextureAtlas.class));
        map.create();
        player = new PlayerEntity(handler.get("player.png", Texture.class));
        player.create();
        enemy = new EnemyEntity(handler.get("enemy.png", Texture.class));
        enemy.create();
        enemy.addWaypointSet(map.getWaypointSet());
    }

    void update(float deltaTime) {
        float playerMovSpeedDelta = Config.PLAYER_MOV_SPEED * deltaTime;

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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.x = screenX;
        touchPos.y = screenY;
        camera.unproject(touchPos);

        // if archer tower (on buy menu) touched, set buyMode
        if (onBuyMenu(touchPos.x, touchPos.y)) {
            buyMode = true;
            Logger.debug(TAG, "inside archer icon");
            Logger.debug(TAG, Float.toString(touchPos.x));
            Logger.debug(TAG, Float.toString(touchPos.y));
        }

        // TODO move to different class?
        if (buyMode) {
            // if selected item from menu, continue else return
            // TODO add method/class that handles placing items in menus and another for checking if touchpos is on any item
            if (button == Input.Buttons.LEFT && onArcherTower(touchPos.x, touchPos.y)) {
                touchPos.x /= TILE_SCALE;
                touchPos.y /= TILE_SCALE;
                // wstawianie wiezy na mape
                if (map.getCellAt((int)touchPos.x, (int)touchPos.y).getCellType() != CellType.PATH) {
                    map.getCellAt((int) touchPos.x, (int) touchPos.y).setTile(map.getArcherTower());
                    return true;
                }
            } else {
                buyMode = false;
                return true;
            }
        }

        if (button == Input.Buttons.RIGHT) {
            if (buyMode) {
                buyMode = false;
            }
        }
        return false;
    }

    private boolean onArcherTower(float x, float y) {
        ARCHER_TOWER_POSITION_X = renderer.archerTowerGui.getRegionX();
        ARCHER_TOWER_POSITION_Y = renderer.archerTowerGui.getRegionY();

        if ((x >= ARCHER_TOWER_POSITION_X && x <= ARCHER_TOWER_POSITION_X + renderer.archerTowerGui.getRegionWidth())
                && (y >= ARCHER_TOWER_POSITION_Y && y <= ARCHER_TOWER_POSITION_Y + renderer.archerTowerGui.getRegionHeight())) {
            System.out.println("found archer tower");
            return true;
        } else
            return false;
    }

    private boolean onBuyMenu(float x, float y) {
        int mapBoundsX = (int)(MAP_LENGTH_X * TILE_SCALE);
        int mapBoundsY = (int)(MAP_LENGTH_Y * TILE_SCALE);
        int menuBoundsX = mapBoundsX + 200;
        int menuBoundsY = mapBoundsY + 600;

        return (x >= mapBoundsX && x <= menuBoundsX) && (y >= mapBoundsY && x <= menuBoundsY);
    }

    private boolean onMap(float x, float y) {
        return x <= (MAP_LENGTH_X * TILE_SCALE) && y <= (MAP_LENGTH_Y * TILE_SCALE);
    }
}
