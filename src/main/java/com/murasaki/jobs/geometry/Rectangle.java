package com.murasaki.jobs.geometry;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Value
public class Rectangle {

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
    public Rectangle(Point lowerLeft, Point upperRight) {
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
     * Creates a new rectangle from the points specifying the lower left and upper right corners. This method will
     * return {@code null} instead of throwing an exception in the case that parameters are invalid.
     * @param lowerLeft     lower left corner of the rectangle
     * @param upperRight    upper right corner of the rectangle
     * @return a new rectangle with the specified points, or {@code null}
     * @see #Rectangle(Point, Point)
     */
    private static Rectangle rectangleOrNull(Point lowerLeft, Point upperRight) {
        try {
            return new Rectangle(lowerLeft, upperRight);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
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
     * Calculates the total area of the specified rectangles. Note that this method assumes the rectangles are non
     * overlapping.
     *
     * @param rectangles rectangles to sum the area of
     * @return the total area the rectangles
     */
    static int calculateArea(Iterable<Rectangle> rectangles) {
        int result = 0;
        for (Rectangle rectangle : rectangles) {
            result = result + rectangle.area();
        }
        return result;
    }

    /**
     * Removes the specified rectangle from this rectangle and returns what remains as a set of disjoint rectangles.
     *
     * @param rectangle rectangle to be removed
     * @return a set of disjoint rectangles that together represent the area remaining after the removal
     */
    Set<Rectangle> remove(Rectangle rectangle) {
        Set<Rectangle> result = new HashSet<>();
        Rectangle intersection = this.intersection(rectangle);
        if (intersection == null) {
            result.add(this);
            return result;
        }

        int top = this.upperRight.getY();
        int bottom = this.lowerLeft.getY();
        int left = this.lowerLeft.getX();
        int right = this.upperRight.getX();

        int intTop = intersection.upperRight.getY();
        int intBottom = intersection.lowerLeft.getY();
        int intLeft = intersection.lowerLeft.getX();
        int intRight = intersection.upperRight.getX();

        // Break this rectangle into 8 rectangles defined by the corners of the intersection of the rectangle to be
        // removed, then add each to the result set. In many cases, these will not be valid which means that they were
        // completely removed. For intuition, draw the case where you are removing something completely enclosed, draw
        // lines outward from each corner of the rectangle to be removed, then consider the other 8 rectangles
        result.add(rectangleOrNull(new Point(left, intTop + 1), new Point(intLeft - 1, top)));         // upper left
        result.add(rectangleOrNull(new Point(intLeft, intTop + 1), new Point(intRight, top)));         // upper
        result.add(rectangleOrNull(new Point(intRight + 1, intTop + 1), new Point(right, top)));       // upper right
        result.add(rectangleOrNull(new Point(intRight + 1, intBottom), new Point(right, intTop)));     // right
        result.add(rectangleOrNull(new Point(intRight + 1, bottom), new Point(right, intBottom - 1))); // lower right
        result.add(rectangleOrNull(new Point(intLeft, bottom), new Point(intRight, intBottom - 1)));   // lower
        result.add(rectangleOrNull(new Point(left, bottom), new Point(intLeft - 1, intBottom - 1)));   // lower left
        result.add(rectangleOrNull(new Point(left, intBottom), new Point(intLeft - 1, intTop)));       // left

        result.remove(null); // remove nulls
        return result;
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
        if (rectangle == null) {
            return false;
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
     * Checks whether this rectangle is aligned with any specified rectangles.
     *
     * @param rectangles rectangles to compare alignment with
     * @return {@code true} if any rectangle specified aligns with this rectangle
     * @see #alignsWith(Rectangle)
     */
    boolean alignsWith(Iterable<Rectangle> rectangles) {
        for (Rectangle rectangle : rectangles) {
            if (alignsWith(rectangle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the rectangular intersection of this rectangle and specified rectangle.
     *
     * @param rectangle rectangle to take the intersection with
     * @return rectangle representing the area that is within both this rectangle and the specified rectangle.
     */
    public Rectangle intersection(Rectangle rectangle) {
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
