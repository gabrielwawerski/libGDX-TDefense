package com.tdefense.world.map.path;

/**
 * Implement in classes that need to have the ability to traverse it's entity/entities along supplied path.
 */
public interface PathTraversable {
    /** Should supply VALID path for entities that can move along it. */
    void getPath(Path path);
}
