package org.duplicateFinder.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccuracyTest {

    @Test
    public void testExactAccuracy() {
        assertEquals("Exact", Accuracy.EXACT.value);
    }

    @Test
    public void testVeryHighAccuracy() {
        assertEquals("Very High", Accuracy.VERY_HIGH.value);
    }

    @Test
    public void testHighAccuracy() {
        assertEquals("High", Accuracy.HIGH.value);
    }

    @Test
    public void testLowAccuracy() {
        assertEquals("Low", Accuracy.LOW.value);
    }

    @Test
    public void testZeroAccuracy() {
        assertEquals("Zero", Accuracy.ZERO.value);
    }
}