package com.tdefense.world.map.waypoint;

import java.util.ArrayList;

public class WaypointSet {
    private ArrayList<Waypoint> waypoints;
    private int index;

    private static final String TAG = WaypointSet.class.getSimpleName();

    public Waypoint getWaypoint(int index) {
        return waypoints.get(index);
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints;
    }

    //region WaypointBuilder methods
    WaypointSet() {
        waypoints = new ArrayList<Waypoint>();
        index = 0;
    }

    void add(Waypoint waypoint) {
        waypoints.add(waypoint);
    }
    //endregion
}
