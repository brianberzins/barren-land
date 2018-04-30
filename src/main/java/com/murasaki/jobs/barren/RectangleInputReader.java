package com.murasaki.jobs.barren;

import com.murasaki.jobs.geometry.Point;
import com.murasaki.jobs.geometry.Rectangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RectangleInputReader {

    private static final String INPUT_REGEX = "\\{(\"([0-9]+ ?){4}\",?)+}";
    private static final String RECTANGLE_REGEX = "\"([0-9]+ ?){4}\"";

    /**
     * Creates a rectangle from formatted string. Format is quote mark, followed by 4 space separated numbers,
     * followed another quote mark. The numbers indicate lower left X, lower left Y, upper right X, upper right Y
     * in that order.
     *
     * @param string formatted string
     * @return rectangle constructed from numbers in the string
     * @see #INPUT_REGEX
     */
    static Rectangle fromString(String string) {
        if (!string.matches(RECTANGLE_REGEX)) {
            throw new IllegalArgumentException("expected rectangle to match regex: " + RECTANGLE_REGEX);
        }
        string = string.substring(1, string.length() - 1); // remove leading and trailing quote marks
        List<Integer> parts = Arrays.stream(string.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Point lowerLeft = new Point(parts.get(0), parts.get(1));
        Point upperRight = new Point(parts.get(2), parts.get(3));
        return new Rectangle(lowerLeft, upperRight);
    }


    /**
     * Creates a collection of rectangles from the formatted string. Format is open-brance, followed by
     * any number of comma separated rectangle formats, followed by a closing-brace.
     *
     * @param string formatted string
     * @return rectangles constructed from the formatted string
     * @see #fromString(String)
     * @see #INPUT_REGEX
     */
    static Collection<Rectangle> allFromString(String string) {
        if (!string.matches(INPUT_REGEX)) {
            throw new IllegalArgumentException("expected input to match regex: " + INPUT_REGEX);
        }
        string = string.substring(1, string.length() - 1); // remove leading and trailing braces
        return Arrays.stream(string.split(","))
                .map(RectangleInputReader::fromString)
                .collect(Collectors.toList());
    }

    /**
     * Reads a single line from standard in and returns it as a string.
     * @return single line from standard in
     * @throws IOException
     */
    static String readStdin() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

}
