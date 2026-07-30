package com.yaligaming.hebrewfix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HebrewTextProcessorTest {

    @Test
    void isHebrewDetectsHebrewLetters() {
        assertTrue(HebrewTextProcessor.isHebrew('א'));
        assertTrue(HebrewTextProcessor.isHebrew('ת'));
        assertTrue(HebrewTextProcessor.isHebrew('ש'));
        assertTrue(HebrewTextProcessor.isHebrew('ם'));
    }

    @Test
    void isHebrewRejectsNonHebrew() {
        assertFalse(HebrewTextProcessor.isHebrew('a'));
        assertFalse(HebrewTextProcessor.isHebrew('Z'));
        assertFalse(HebrewTextProcessor.isHebrew('1'));
        assertFalse(HebrewTextProcessor.isHebrew(' '));
        assertFalse(HebrewTextProcessor.isHebrew('.'));
    }

    @Test
    void containsHebrewDetectsHebrew() {
        assertTrue(HebrewTextProcessor.containsHebrew("שלום"));
        assertTrue(HebrewTextProcessor.containsHebrew("Hello שלום"));
        assertTrue(HebrewTextProcessor.containsHebrew("מה קורה"));
    }

    @Test
    void containsHebrewRejectsNonHebrew() {
        assertFalse(HebrewTextProcessor.containsHebrew("Hello World"));
        assertFalse(HebrewTextProcessor.containsHebrew("12345"));
    }

    @Test
    void containsHebrewHandlesNullAndEmpty() {
        assertFalse(HebrewTextProcessor.containsHebrew(null));
        assertFalse(HebrewTextProcessor.containsHebrew(""));
    }
}
