package com.murasaki.jobs.barren;

import com.murasaki.jobs.geometry.RectangleField;
import com.murasaki.jobs.geometry.Point;
import com.murasaki.jobs.geometry.Rectangle;

import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {
        String input = RectangleInputReader.readStdin();
        String output = process(input);
        System.out.print(output);
    }

    static String process(String input) {
        // starting green
        Rectangle startingGreen = new Rectangle(new Point(0,0), new Point(399, 599));
        RectangleField barrens = new RectangleField(startingGreen);

        // barren rectangles
        Iterable<Rectangle> barrenRectangles = RectangleInputReader.allFromString(input);
        barrens.removeAll(barrenRectangles);
        return barrens.sortedAreasAsStrings();
    }
}
