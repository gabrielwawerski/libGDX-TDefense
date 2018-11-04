package com.tdefense.world.util;

import java.util.ArrayList;

public class WaypointSet {
    public ArrayList<Waypoint> waypoints;
    private Waypoint lastWaypoint;
    private int index;

    private static final String TAG = WaypointSet.class.getSimpleName();

    public Waypoint getNextWaypoint() {
        if (waypoints.get(index).getX() == lastWaypoint.getX()
                && waypoints.get(index).getY() == lastWaypoint.getY()) {
            index = 0;
            return lastWaypoint;
        }
        return waypoints.get(index++);
    }

    public Waypoint getLastWaypoint() {
        return lastWaypoint;
    }

    //region WaypointSetBuilder helper methods
    WaypointSet() {
        waypoints = new ArrayList<Waypoint>();
        index = 0;
    }

    void add(float x, float y) {
        waypoints.add(new Waypoint(x, y));
    }

    void setLastWaypoint(Waypoint lastWaypoint) {
        this.lastWaypoint = lastWaypoint;
    }

    /**
     * Call to set last waypoint coordinates.
     */
    void build(float x, float y) {
        waypoints.get(waypoints.size() - 1).setX(x);
        waypoints.get(waypoints.size() - 1).setY(y);
    }
    //endregion
}
