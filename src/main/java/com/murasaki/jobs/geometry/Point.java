package com.murasaki.jobs.geometry;

import lombok.Value;

@Value
public class Point {

    int x;
    int y;

    /**
     * Checks whether this point is directionally adjacent to the specified point
     *
     * @param point point to check adjacency of
     * @return {@code true} if the specified point in adjacent to this point
     */
    boolean isAdjacent(Point point) {
        // check if the x coordinates are exactly one away
        // check if the y coordinates are exactly one away
        // take the xor of these conditions: only adjacent if exactly one in away in a single direction
        int xDiff = Math.abs(x - point.x);
        int yDiff = Math.abs(y - point.y);
        return (xDiff == 0 && yDiff == 1) || (xDiff == 1 && yDiff == 0);
    }

    /**
     * Gets the point that is offset from this point by the given x,y coordinates
     *
     * @param x offset in x direction
     * @param y offset in y direction
     * @return the point that is offset from this point by the specified coordinates
     */
    public Point offset(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }
}
