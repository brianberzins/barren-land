package com.murasaki.jobs.geometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("rectangle field")
class RectangleFieldTest {


    @DisplayName("adjacent sets")
    static class AdjacentSets {

        @Test
        @DisplayName("all adjacent")
        void allAdjacent() {
            Rectangle rectangle1 = new Rectangle(new Point(2, 2), new Point(5,5));
            Rectangle rectangle2 = new Rectangle(new Point(6, 2), new Point(9,4));
            Rectangle rectangle3 = new Rectangle(new Point(6, 0), new Point(7,1));
            RectangleField rectangleField = new RectangleField(rectangle1, rectangle2, rectangle3);
            Set<Set<Rectangle>> adjacentSets = rectangleField.adjacentSets();
            assertEquals(1, adjacentSets.size());
            Set<Rectangle> adjacentRectangles = adjacentSets.iterator().next();
            assertAll(
                    () -> assertTrue(adjacentRectangles.contains(rectangle1)),
                    () -> assertTrue(adjacentRectangles.contains(rectangle2)),
                    () -> assertTrue(adjacentRectangles.contains(rectangle3))
            );
        }

        @Test
        @DisplayName("not adjacent")
        void nonAdjacent() {
            Rectangle rectangle1 = new Rectangle(new Point(2, 2), new Point(5,5));
            Rectangle rectangle2 = new Rectangle(new Point(6, 0), new Point(7,1));
            RectangleField rectangleField = new RectangleField(rectangle1, rectangle2);
            Set<Set<Rectangle>> adjacentSets = rectangleField.adjacentSets();
            assertEquals(2, adjacentSets.size());
        }

    }

    @DisplayName("remove")
    static class Remove {

        @Test
        @DisplayName("remove rectangle")
        void removeRectangle() {
            Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(5,5));
            RectangleField rectangleField = new RectangleField(rectangle);
            Rectangle remove1 = new Rectangle(new Point(0, 3), new Point(4, 4));
            Rectangle remove2 = new Rectangle(new Point(3, 0), new Point(4, 4));
            rectangleField.removeAll(Arrays.asList(remove1, remove2));
            Rectangle expected1 = new Rectangle(new Point(0, 0), new Point(2, 2));
            Rectangle expected2 = new Rectangle(new Point(0, 5), new Point(4, 5));
            Rectangle expected3 = new Rectangle(new Point(5, 5), new Point(5, 5));
            Rectangle expected4 = new Rectangle(new Point(5, 3), new Point(5, 4));
            Rectangle expected5 = new Rectangle(new Point(5, 0), new Point(5, 2));
            assertAll(
                    () -> assertEquals(5, rectangleField.rectangles.size()),
                    () -> assertTrue(rectangleField.rectangles.contains(expected1)),
                    () -> assertTrue(rectangleField.rectangles.contains(expected2)),
                    () -> assertTrue(rectangleField.rectangles.contains(expected3)),
                    () -> assertTrue(rectangleField.rectangles.contains(expected4)),
                    () -> assertTrue(rectangleField.rectangles.contains(expected5))
            );
        }

    }

    @DisplayName("intersecting rectangles")
    static class IntersectingRectangles {

        @Test
        @DisplayName("multiple intersecting")
        void singleIntersecting() {
            Rectangle rectangle1 = new Rectangle(new Point(2, 2), new Point(5,5));
            Rectangle rectangle2 = new Rectangle(new Point(6, 2), new Point(10,5));
            RectangleField rectangleField = new RectangleField(rectangle1, rectangle2);
            Rectangle toFind = new Rectangle(new Point(1, 4), new Point(11, 5));
            Set<Rectangle> intersecting = rectangleField.intersectingRectangles(toFind);
            assertAll(
                    () -> assertEquals(2, intersecting.size()),
                    () -> assertTrue(intersecting.contains(rectangle1)),
                    () -> assertTrue(intersecting.contains(rectangle2))
            );

        }
    }

}