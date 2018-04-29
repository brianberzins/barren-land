package com.murasaki.jobs.geometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("rectangle")
class RectangleTest {

    @DisplayName("constructor")
    static class Constructor {

        @Test
        @DisplayName("valid points do not throw an exceptions")
        void valid() {
            Point lowerLeft = new Point(0, 0);
            Point upperRight = new Point(5, 5);
            new Rectangle(lowerLeft, upperRight);
        }

        @Test
        @DisplayName("horizontally invalid points throw an invalid argument exceptions")
        void horizontallyInvalid() {
            Point lowerLeft = new Point(2, 0);
            Point upperRight = new Point(0, 5); // to the left of the lower left point
            assertThrows(IllegalArgumentException.class, () -> new Rectangle(lowerLeft, upperRight));
        }

        @Test
        @DisplayName("vertically invalid points throw an invalid argument exceptions")
        void verticallyInvalid() {
            Point lowerLeft = new Point(0, 2);
            Point upperRight = new Point(0, 1); // below the lower left point
            assertThrows(IllegalArgumentException.class, () -> new Rectangle(lowerLeft, upperRight));
        }

        @Test
        @DisplayName("correctly assigns all corners")
        void corners() {
            Point lowerLeft = new Point(1, 2);
            Point upperRight = new Point(3, 4);
            Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
            assertAll(
                    () -> assertEquals(new Point(1, 2), rectangle.getLowerLeft()),
                    () -> assertEquals(new Point(3, 2), rectangle.getLowerRight()),
                    () -> assertEquals(new Point(1, 4), rectangle.getUpperLeft()),
                    () -> assertEquals(new Point(3, 4), rectangle.getUpperRight())
            );
        }

    }

    @DisplayName("area")
    static class Area {

        @Test
        @DisplayName("area is calculated accurately")
        void area() {
            Point lowerLeft = new Point(0, 0);
            Point upperRight = new Point(5, 5);
            Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
            assertEquals(36, rectangle.area());
        }

    }

    @DisplayName("intersect")
    static class Intersect {

        @Test
        @DisplayName("entirely overlapping")
        void totalOverlap() {
            Rectangle outer = new Rectangle(new Point(0, 0), new Point(5, 5));
            Rectangle inner = new Rectangle(new Point(2, 2), new Point(3, 3));
            assertAll(
                    () -> assertEquals(inner, outer.intersection(inner)),
                    () -> assertEquals(inner, inner.intersection(outer))
            );
        }

        @Test
        @DisplayName("no intersection")
        void noIntersection() {
            Rectangle rect1 = new Rectangle(new Point(0, 0), new Point(2, 2));
            Rectangle rect2 = new Rectangle(new Point(3, 3), new Point(5, 5));
            assertAll(
                    () -> assertEquals(null, rect1.intersection(rect2)),
                    () -> assertEquals(null, rect2.intersection(rect1))
            );
        }

        @Test
        @DisplayName("corner intersection")
        void cornerIntersection() {
            Rectangle rect1 = new Rectangle(new Point(0, 0), new Point(2, 2));
            Rectangle rect2 = new Rectangle(new Point(1, 1), new Point(4, 4));
            Rectangle expected = new Rectangle(new Point(1, 1), new Point(2, 2));
            assertAll(
                    () -> assertEquals(expected, rect1.intersection(rect2)),
                    () -> assertEquals(expected, rect2.intersection(rect1))
            );
        }

        @Test
        @DisplayName("side covering")
        void sideCover() {
            Rectangle rect1 = new Rectangle(new Point(1, 1), new Point(3, 3));
            Rectangle rect2 = new Rectangle(new Point(0, 0), new Point(2, 5));
            Rectangle expected = new Rectangle(new Point(1, 1), new Point(2, 3));
            assertAll(
                    () -> assertEquals(expected, rect1.intersection(rect2)),
                    () -> assertEquals(expected, rect2.intersection(rect1))
            );
        }

        @Test
        @DisplayName("split middle")
        void splitMiddle() {
            Rectangle rect1 = new Rectangle(new Point(1, 1), new Point(4, 4));
            Rectangle rect2 = new Rectangle(new Point(0, 2), new Point(5, 3));
            Rectangle expected = new Rectangle(new Point(1, 2), new Point(4, 3));
            assertAll(
                    () -> assertEquals(expected, rect1.intersection(rect2)),
                    () -> assertEquals(expected, rect2.intersection(rect1))
            );
        }
    }

}