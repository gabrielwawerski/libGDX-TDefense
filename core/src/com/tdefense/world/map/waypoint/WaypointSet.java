package com.tdefense.world.map.waypoint;

import java.util.ArrayList;

public class WaypointSet {
    private ArrayList<Waypoint> waypoints;
    private Waypoint originWaypoint;

    private static final String TAG = WaypointSet.class.getSimpleName();

    WaypointSet() {
        waypoints = new ArrayList<Waypoint>();
    }

    public void add(Waypoint waypoint) {
        waypoints.add(waypoint);
    }

    public Waypoint getOriginWaypoint() {
        return  waypoints.get(0);
    }
}
