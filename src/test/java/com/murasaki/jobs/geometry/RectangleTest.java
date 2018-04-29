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

}