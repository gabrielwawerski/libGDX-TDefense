package com.tdefense.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tdefense.entity.Entity;
import com.tdefense.system.util.Config;
import com.tdefense.system.util.logging.Logger;
import com.tdefense.world.util.Step;
import com.tdefense.world.util.Waypoint;
import com.tdefense.world.util.WaypointSet;

public class EnemyEntity extends Entity implements Enemy {
    public WaypointSet waypointSet;
    private boolean pathTraversed;
    private Waypoint currentWaypoint;
    private int thisId;

    private static int enemyId = 0;
    private static final String TAG = EnemyEntity.class.getSimpleName();
    /** CAUTION: if object moves very fast, this should be increased */
    private static final int SAFE_DISTANCE = 2; // TODO move to constants

    public EnemyEntity(Texture texture) {
        super(texture);
        thisId = enemyId++;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sprite, positon.x, positon.y);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        positon = new Vector2();
        pathTraversed = false;
    }

    public void move(float deltaTime) {
        // if distance to last waypoint <= 5, set enemy pos to it and set pathTraversed to true; exit method
        if (positon.dst(waypointSet.getLastWaypoint().getVector()) <= SAFE_DISTANCE) {
            positon.set(waypointSet.getLastWaypoint().getVector());
            setPathTraversed(true);
            Logger.debug("Enemy " + thisId, "path succesfully traversed!");
            return;
        }

        if (positon.dst(currentWaypoint.getVector()) <= SAFE_DISTANCE) {
            positon.x = currentWaypoint.getX();
            positon.y = currentWaypoint.getY();
            currentWaypoint = waypointSet.getNextWaypoint();
            Logger.debug("Enemy " + thisId,
                    "moving to waypoint (" + currentWaypoint.getX() / 32 + "," + currentWaypoint.getY() / 32 + ")");
        } else {
            Step currentStep = currentWaypoint.getStep();

            if (currentStep == Step.DOWN) {
                subPositionY(Config.ENEMY_MOV_SPEED * deltaTime);
            } else if (currentStep == Step.LEFT) {
                subPositionX(Config.ENEMY_MOV_SPEED * deltaTime);
            } else if (currentStep == Step.RIGHT) {
                addPositionX(Config.ENEMY_MOV_SPEED * deltaTime);
            } else if (currentStep == Step.UP) {
                subPositionY(Config.ENEMY_MOV_SPEED * deltaTime);
            }
        }
    }

    // TODO shouldn't be public
    public WaypointSet getWaypointSet() {
        return waypointSet;
    }

    public boolean wasPathTraversed() {
        return pathTraversed;
    }

    private void setPathTraversed(boolean pathTraversed) {
        this.pathTraversed = pathTraversed;
    }

    public void addWaypointSet(WaypointSet waypointSet) {
        this.waypointSet = waypointSet;
        currentWaypoint = waypointSet.getCurrentWaypoint();
    }
}