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
        @DisplayName("null rectangle")
        void nullRectangle() {
            Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(5, 5));
            assertNull(rectangle.intersection(null));
        }

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
            Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(2, 2));
            Rectangle rectangle2 = new Rectangle(new Point(3, 3), new Point(5, 5));
            assertAll(
                    () -> assertEquals(null, rectangle1.intersection(rectangle2)),
                    () -> assertEquals(null, rectangle2.intersection(rectangle1))
            );
        }

        @Test
        @DisplayName("corner intersection")
        void cornerIntersection() {
            Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(2, 2));
            Rectangle rectangle2 = new Rectangle(new Point(1, 1), new Point(4, 4));
            Rectangle expected = new Rectangle(new Point(1, 1), new Point(2, 2));
            assertAll(
                    () -> assertEquals(expected, rectangle1.intersection(rectangle2)),
                    () -> assertEquals(expected, rectangle2.intersection(rectangle1))
            );
        }

        @Test
        @DisplayName("side covering")
        void sideCover() {
            Rectangle rectangle1 = new Rectangle(new Point(1, 1), new Point(3, 3));
            Rectangle rectangle2 = new Rectangle(new Point(0, 0), new Point(2, 5));
            Rectangle expected = new Rectangle(new Point(1, 1), new Point(2, 3));
            assertAll(
                    () -> assertEquals(expected, rectangle1.intersection(rectangle2)),
                    () -> assertEquals(expected, rectangle2.intersection(rectangle1))
            );
        }

        @Test
        @DisplayName("split middle")
        void splitMiddle() {
            Rectangle rectangle1 = new Rectangle(new Point(1, 1), new Point(4, 4));
            Rectangle rectangle2 = new Rectangle(new Point(0, 2), new Point(5, 3));
            Rectangle expected = new Rectangle(new Point(1, 2), new Point(4, 3));
            assertAll(
                    () -> assertEquals(expected, rectangle1.intersection(rectangle2)),
                    () -> assertEquals(expected, rectangle2.intersection(rectangle1))
            );
        }
    }

    @DisplayName("aligns with")
    static class Alignment {

        Rectangle rectangle1 = new Rectangle(new Point(2, 2), new Point(4, 4));

        @Test
        @DisplayName("null rectangle")
        void nullRectangle() {
            assertThrows(IllegalArgumentException.class, () -> rectangle1.alignsWith(null));
        }

        @Test
        @DisplayName("upper left/right")
        void upperLeftRight() {
            Rectangle rectangle2 = new Rectangle(new Point(0, 2), new Point(1, 4));
            assertAll(
                    () -> assertTrue(rectangle1.alignsWith(rectangle2)),
                    () -> assertTrue(rectangle2.alignsWith(rectangle1))
            );
        }

        @Test
        @DisplayName("lower left/right")
        void lowerLeftRight() {
            Rectangle rectangle2 = new Rectangle(new Point(0, 1), new Point(1, 4));
            assertAll(
                    () -> assertTrue(rectangle1.alignsWith(rectangle2)),
                    () -> assertTrue(rectangle2.alignsWith(rectangle1))
            );
        }

        @Test
        @DisplayName("left up/down")
        void leftUpDown() {
            Rectangle rectangle2 = new Rectangle(new Point(2, 5), new Point(3, 7));
            assertAll(
                    () -> assertTrue(rectangle1.alignsWith(rectangle2)),
                    () -> assertTrue(rectangle2.alignsWith(rectangle1))
            );
        }

        @Test
        @DisplayName("right up/down")
        void rightUpDown() {
            Rectangle rectangle2 = new Rectangle(new Point(3, 5), new Point(4, 6));
            assertAll(
                    () -> assertTrue(rectangle1.alignsWith(rectangle2)),
                    () -> assertTrue(rectangle2.alignsWith(rectangle1))
            );
        }

        @Test
        @DisplayName("overlaps")
        void overlaps() {
            Rectangle rectangle2 = new Rectangle(new Point(3, 3), new Point(5, 5));
            assertAll(
                    () -> assertFalse(rectangle1.alignsWith(rectangle2)),
                    () -> assertFalse(rectangle2.alignsWith(rectangle1))
            );
        }
    }
}