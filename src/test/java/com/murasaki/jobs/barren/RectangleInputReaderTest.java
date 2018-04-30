package com.murasaki.jobs.barren;

import com.murasaki.jobs.geometry.Point;
import com.murasaki.jobs.geometry.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("rectangle input reader")
class RectangleInputReaderTest {

    @DisplayName("from string")
    static class FromString {

        @Test
        @DisplayName("valid input")
        void validInput() {
            String string = "\"0 292 399 307\"";
            Rectangle expected = new Rectangle(new Point(0, 292), new Point(399, 307));
            assertEquals(expected, RectangleInputReader.fromString(string));
        }

        @Test
        @DisplayName("invalid input")
        void invalidInput() {
            String string = "\"0 292 399 307 12\"";
            assertThrows(IllegalArgumentException.class, () -> RectangleInputReader.fromString(string));
        }
    }

    @DisplayName("all from string")
    static class AllFromString {

        @Test
        @DisplayName("valid input")
        void validInput() {
            String string = "{\"48 192 351 207\",\"48 392 351 407\",\"120 52 135 547\",\"260 52 275 547\"}";
            Collection<Rectangle> rectangles = RectangleInputReader.allFromString(string);
            assertAll(
                    () -> assertEquals(4, rectangles.size()),
                    () -> assertTrue(rectangles.contains(new Rectangle(new Point(48, 192), new Point(351, 207)))),
                    () -> assertTrue(rectangles.contains(new Rectangle(new Point(48, 392), new Point(351, 407)))),
                    () -> assertTrue(rectangles.contains(new Rectangle(new Point(120, 52), new Point(135, 547)))),
                    () -> assertTrue(rectangles.contains(new Rectangle(new Point(260, 52), new Point(275, 547))))
            );
        }

        @Test
        @DisplayName("invalid input")
        void invalidInput() {
            // has an extra number in second rectangle
            String string = "{\"48 192 351 207\",\"48 392 351 407 512\",\"120 52 135 547\",\"260 52 275 547\"}";
            assertThrows(IllegalArgumentException.class, () -> RectangleInputReader.allFromString(string));
        }

    }

}