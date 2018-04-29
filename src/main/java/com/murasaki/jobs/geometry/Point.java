package com.murasaki.jobs.geometry;

import lombok.Value;

@Value
class Point {

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
        return Math.abs(x - point.x) == 1 ^ Math.abs(y - point.y) == 1;
    }

}
