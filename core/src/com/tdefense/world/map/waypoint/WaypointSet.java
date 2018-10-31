package com.tdefense.world.map.waypoint;

import java.util.ArrayList;

public class WaypointSet {
    private ArrayList<Waypoint> waypoints;
    private int index;

    private Waypoint originWaypoint;
    private Waypoint finalWaypoint;

    private static final String TAG = WaypointSet.class.getSimpleName();

    public Waypoint getNextWaypoint() {
        if (index > waypoints.size() - 1)
            index = 0;
        return waypoints.get(index++);
    }

    public Waypoint getOriginWaypoint() {
        return originWaypoint;
    }

    //region WaypointBuilder methods
    WaypointSet() {
        waypoints = new ArrayList<Waypoint>();
        index = 0;
    }

    void setOriginWaypoint(Waypoint waypoint) {
        originWaypoint = waypoint;
    }

    void setFinalWaypoint(Waypoint finalWaypoint) {
        this.finalWaypoint = finalWaypoint;
    }

    void add(Waypoint waypoint) {
        waypoints.add(waypoint);
    }
    //endregion
}
