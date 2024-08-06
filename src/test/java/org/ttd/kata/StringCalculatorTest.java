package org.ttd.kata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

    @Test
    void testEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void testAddOneNumber() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    void testAddTwoNumbers() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    void testAddMultipleNumbers() {
        assertEquals(25, stringCalculator.add("1,2,3,4,15"));
        assertEquals(467, stringCalculator.add("1.1,2,3,4.456"));
    }


    @Test
    void testAddNewLine() {
        assertEquals(10, stringCalculator.add("1\n2,3\n4"));
    }

    @Test
    void testAddWithDelimiters() {
        assertEquals(3, stringCalculator.add("//;\\n1;2"));
        assertEquals(8, stringCalculator.add("//;\\n1;2[1]4"));
    }

    @Test
    void  testWithNegativeNumbers() {
        try {
            stringCalculator.add("1,-2,3,-4,-8");
        } catch (IllegalArgumentException e) {
            assertEquals("Negative numbers not allowed: -2, -4, -8", e.getMessage());
        }
    }

    @Test
    void testWithCustomedDelimiter() {
        assertEquals(6, stringCalculator.add("//[***]\\n1***2***3"));
    }

    @Test
    void testWithCustomedDelimiterWithCharacters() {
        assertEquals(18, stringCalculator.add("//mar\\n17dec1"));
    }

    @Test
    void testIgnoreNumbersGreaterThanOrEqualsTo1000() {
        assertEquals(2, stringCalculator.add("2,1701"));
        assertEquals(2, stringCalculator.add("1000,2"));
        assertEquals(222, stringCalculator.add("1111,222"));
    }
}
