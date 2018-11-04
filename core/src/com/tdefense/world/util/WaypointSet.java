package com.tdefense.world.util;

import java.util.ArrayList;

public class WaypointSet {
    public ArrayList<Waypoint> waypoints;
    private Waypoint lastWaypoint;
    private int index;

    private static final String TAG = WaypointSet.class.getSimpleName();

    public Waypoint getNextWaypoint() {
        return waypoints.get(index++);
    }

    public Waypoint getCurrentWaypoint() {
        return waypoints.get(index);
    }

    public void nextWaypoint() {
        index++;
    }

    public void reset() {
        index = 1;
    }

    public Waypoint getLastWaypoint() {
        return waypoints.get(waypoints.size() - 1);
    }

    public Waypoint getFirstWaypoint() {
        return waypoints.get(0);
    }

    //region WaypointSetBuilder helper methods
    WaypointSet() {
        waypoints = new ArrayList<Waypoint>();
        index = 1;
    }

    void add(float x, float y, Step step) {
        waypoints.add(new Waypoint(x, y, step));
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
        waypoints.get(waypoints.size() - 1).setStep(Step.LAST);
        index = 1;
    }
    //endregion
}
