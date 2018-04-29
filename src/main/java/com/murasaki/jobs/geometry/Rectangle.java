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
     * @throws IllegalArgumentException if the points are directionally not correct from each other, or if either
     * specified point is {@code null}
     */
    Rectangle(Point lowerLeft, Point upperRight) {
        if (lowerLeft == null || upperRight == null) {
            throw new IllegalArgumentException("rectangle corners cannot be null");
        }
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
        // be very careful with off-by-one errors, all rectangles has a witch of 1
        int width = upperRight.getX() - lowerLeft.getX() + 1;
        int height = upperRight.getY() - lowerLeft.getY() + 1;
        return width * height;
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
     * @throws IllegalArgumentException if the specified rectangle is null
     */
    boolean alignsWith(Rectangle rectangle) {
        if (rectangle == null) {
            throw new IllegalArgumentException("rectangle cannot be null");
        }
        boolean adjacent =
                lowerLeft.isAdjacent(rectangle.lowerRight)  ||
                lowerLeft.isAdjacent(rectangle.upperLeft)   ||
                upperLeft.isAdjacent(rectangle.upperRight)  ||
                upperLeft.isAdjacent(rectangle.lowerLeft)   ||
                lowerRight.isAdjacent(rectangle.lowerLeft)  ||
                lowerRight.isAdjacent(rectangle.upperRight) ||
                upperRight.isAdjacent(rectangle.upperLeft)  ||
                upperRight.isAdjacent(rectangle.lowerRight);
        boolean disjoint = this.intersection(rectangle) == null;
        return adjacent && disjoint;
    }

    /**
     * Gets the rectangular intersection of this rectangle and specified rectangle.
     *
     * @param rectangle rectangle to take the intersection with
     * @return rectangle representing the area that is within both this rectangle and the specified rectangle.
     */
    Rectangle intersection(Rectangle rectangle) {
        if (rectangle == null) {
            return null;
        }
        int x1 = Math.max(this.lowerLeft.getX(), rectangle.lowerLeft.getX());
        int y1 = Math.max(this.lowerLeft.getY(), rectangle.lowerLeft.getY());
        int x2 = Math.min(this.upperRight.getX(), rectangle.lowerRight.getX());
        int y2 = Math.min(this.upperRight.getY(), rectangle.upperRight.getY());
        Point newLowerLeft = new Point(x1, y1);
        Point newUpperRight = new Point(x2, y2);
        try {
            return new Rectangle(newLowerLeft, newUpperRight);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null; // the intersection isn't a valid rectangle, so there is no intersection
        }
    }


}
