package com.murasaki.jobs.barren;

import com.murasaki.jobs.geometry.RectangleField;
import com.murasaki.jobs.geometry.Point;
import com.murasaki.jobs.geometry.Rectangle;

import java.io.IOException;

public class Main {

    /**
     * Reads from standard in, processes the input, prints to standard out.
     * @throws IOException in the event that reading from standard in fails
     */
    public static void main(String... args) throws IOException {
        String input = RectangleInputReader.readStdin();
        String output = process(input);
        System.out.print(output);
    }

    /**
     * Interprets input as a formatted list of rectangles to remove from a standard field.
     * @param input formatted input of a list of rectangles to remove
     * @return sorted list of connected areas left in the field
     */
    static String process(String input) {
        // starting green
        Rectangle startingGreen = new Rectangle(new Point(0, 0), new Point(399, 599));
        RectangleField barrens = new RectangleField(startingGreen);

        // barren rectangles
        Iterable<Rectangle> barrenRectangles = RectangleInputReader.allFromString(input);
        barrens.removeAll(barrenRectangles);
        return barrens.sortedAreasAsStrings();
    }
}
