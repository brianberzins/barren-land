package com.murasaki.jobs.geometry;

import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
class Rectangle {

    Point upperLeft;
    Point lowerLeft;
    Point upperRight;
    Point lowerRight;

    /**
     * Creates a new rectangle from the points specifying the lower left and upper right corners.
     *
     * @param lowerLeft     lower left corner of the rectangle
     * @param upperRight    upper right corner of the rectangle
     * @throws IllegalArgumentException if the points are directionally not correct from each other
     */
    Rectangle(Point lowerLeft, Point upperRight) {
        if (upperRight.getX() < lowerLeft.getX()) {
            throw new IllegalArgumentException("upper right corner is to the left of the lower left corner");
        } else if (upperRight.getY() < lowerLeft.getY()) {
            throw new IllegalArgumentException("upper right corner is below the lower left corner");
        }
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.upperLeft = new Point(lowerLeft.getX(), upperRight.getY());
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
    }

    /**
     * Gets the area of this rectangle
     *
     * @return the area of this rectangle
     */
    int area() {
        return 0;
    }

    /**
     * Removes the specified rectangle from this rectangle and returns what remains and set of disjoint rectangles.
     *
     * @param rectangle     rectangle to be removed
     * @return a set of disjoint rectangles that together represent the area remaining after the removal
     */
    Set<Rectangle> remove(Rectangle rectangle) {
        return Collections.emptySet();
    }

    /**
     * Checks whether this rectangle and the specified rectangle are non-overlapping and have adjacent corners such
     * that the corners both share a cardinal-direction edge (example: the adjacent corners are both on the left edge of
     * their respective rectangles)
     *
     * @param rectangle rectangle to compare aligned with
     * @return {@code true} if this rectangle aligns with the specified rectangle
     */
    boolean alignsWith(Rectangle rectangle) {
        return false;
    }


    /**
     * Gets the rectangular intersection of this rectangle and specified rectangle.
     *
     * @param rectangle rectangle to take the intersection with
     * @return rectangle representing the area that is within both this rectangle and the specified rectangle.
     */
    Rectangle intersection(Rectangle rectangle) {
        return null;
    }


}
