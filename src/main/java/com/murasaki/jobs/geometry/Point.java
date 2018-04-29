package com.murasaki.jobs.geometry;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
class Point {

    int horizontal;
    int vertical;

    /**
     * Checks whether this point is directionally adjacent to the specified point
     *
     * @param point point to check adjacency of
     * @return {@code true} if the specified point in adjacent to this point
     */
    boolean isAdjacent(Point point) {
        return false;
    }

}
