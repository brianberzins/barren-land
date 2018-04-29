package com.murasaki.jobs.geometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("point")
class PointTest {

    @DisplayName("adjacent")
    static class Alignment {

        @Test
        @DisplayName("is adjacent by x")
        void isAdjacentX() {
            Point point1 = new Point(1, 0);
            Point point2 = new Point(0, 0);
            assertAll(
                    () -> assertTrue(point1.isAdjacent(point2)),
                    () -> assertTrue(point2.isAdjacent(point1))
            );
        }

        @Test
        @DisplayName("is adjacent by y")
        void isAdjacentY() {
            Point point1 = new Point(0, 0);
            Point point2 = new Point(0, 1);
            assertAll(
                    () -> assertTrue(point1.isAdjacent(point2)),
                    () -> assertTrue(point2.isAdjacent(point1))
            );
        }

        @Test
        @DisplayName("not adjacent by corner")
        void notAdjacentByCorner() {
            Point point1 = new Point(0, 0);
            Point point2 = new Point(1, 1);
            assertAll(
                    () -> assertFalse(point1.isAdjacent(point2)),
                    () -> assertFalse(point2.isAdjacent(point1))
            );
        }

        @Test
        @DisplayName("not adjacent")
        void notAdjacent() {
            Point point1 = new Point(0, 0);
            Point point2 = new Point(0, 2);
            assertAll(
                    () -> assertFalse(point1.isAdjacent(point2)),
                    () -> assertFalse(point2.isAdjacent(point1))
            );
        }

    }


}