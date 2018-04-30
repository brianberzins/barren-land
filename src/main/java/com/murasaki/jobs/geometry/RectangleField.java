package com.murasaki.jobs.geometry;

import java.util.*;
import java.util.stream.Collectors;

public class RectangleField {

    Set<Rectangle> rectangles;

    /**
     * Creates a new rectangular field using the specified rectangles are starting points.
     * @param greenRectangles starting rectangles in the field.
     */
    public RectangleField(Rectangle... greenRectangles) {
        this.rectangles = new HashSet<>();
        this.rectangles.addAll(Arrays.asList(greenRectangles));
    }

    /**
     * Remove all rectangles in the specified collection from the field
     * @param rectangles rectangles to be removed
     * @see #remove(Rectangle)
     */
    public void removeAll(Iterable<Rectangle> rectangles) {
        for (Rectangle barrenRectangle : rectangles) {
            remove(barrenRectangle);
        }
    }

    /**
     * Removes the specified rectangle from the field.
     * @param rectangle rectangle to remove from the field
     */
    void remove(Rectangle rectangle) {
        Set<Rectangle> intersectingRectangles = intersectingRectangles(rectangle);
        for (Rectangle intersectingRectangle : intersectingRectangles) {
            // remove all intersecting rectangles, but add back what remains after the removal
            rectangles.remove(intersectingRectangle);
            rectangles.addAll(intersectingRectangle.remove(rectangle));
        }
    }

    /**
     * Gets the set of all rectangles in this field that intersect with the specified rectangle.
     *
     * @param otherRectangle rectangle to find intersections with
     * @return the set of all rectangles in this field that intersect with the specified rectangle
     */
    Set<Rectangle> intersectingRectangles(Rectangle otherRectangle) {
        Set<Rectangle> result = new HashSet<>();
        for (Rectangle rectangle : rectangles) {
            if (rectangle.intersection(otherRectangle) != null) {
                result.add(rectangle);
            }
        }
        return result;
    }

    /**
     * Returns the set of sets of rectangles that are all aligned with each other. This method works transitively
     * to ensure that each set contains rectangles that are can be connected together by alignment
     *
     * @return the set of sets of rectangles that are all aligned with each other
     * @see Rectangle#alignsWith(Rectangle)
     * @see Rectangle#alignsWith(Collection)
     */
    Set<Set<Rectangle>> adjacentSets() {
        Set<Set<Rectangle>> result = new HashSet<>();
        for (Rectangle rectangle : rectangles) {
            Set<Set<Rectangle>> matchedSets = new HashSet<>();
            // find all sets that this rectangle is aligned with
            for (Set<Rectangle> set : result) {
                if (rectangle.alignsWith(set)) {
                    matchedSets.add(set);
                }
            }
            // create a new set
            Set<Rectangle> newSet = new HashSet<>();
            newSet.add(rectangle);
            if (!matchedSets.isEmpty()) {
                // if there were adjacent sets, merge them together
                result.removeAll(matchedSets);
                for (Set<Rectangle> matchedSet : matchedSets) {
                    newSet.addAll(matchedSet);
                }
            }
            // add the new set to the result
            result.add(newSet);
        }
        return result;
    }

    /**
     * Calculates the area of all disjoint sets of rectangles and returns them as a space separated sorted list.
     * @return the area of all disjoint sets of rectangles and returns them as a space separated sorted list
     */
    public String sortedAreasAsStrings() {
        return adjacentSets().stream()
                .map(Rectangle::calculateArea)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

}
