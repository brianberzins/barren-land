package com.murasaki.jobs.barren;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("examples")
class MainTest {

    @Test
    @DisplayName("example 1")
    void example1() {
        String input = "{\"0 292 399 307\"}";
        String output = Main.process(input);
        assertEquals("116800 116800", output);
    }

    @Test
    @DisplayName("example 2")
    void example2() {
        String input = "{\"48 192 351 207\",\"48 392 351 407\",\"120 52 135 547\",\"260 52 275 547\"}";
        String output = Main.process(input);
        assertEquals("22816 192608", output);
    }

}