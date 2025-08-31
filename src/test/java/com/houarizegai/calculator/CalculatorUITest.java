package com.houarizegai.calculator;

import com.houarizegai.calculator.ui.CalculatorUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorUITest {

    private CalculatorUI calculatorUI;

    @BeforeEach
    void setUp() {
        calculatorUI = new CalculatorUI();
    }

    @ParameterizedTest
    @CsvSource({"3,5,+,8", "2,8,-,-6", "44.5,10,*,445", "320,5,/,64", "3,5,%,3", "5,3,^,125"})
    void testCalculation(double firstNumber, double secondNumber, char operator, double expectedResult) {
        assertEquals(expectedResult, calculatorUI.calculate(firstNumber, secondNumber, operator));
    }

    @Test
    void testFactorialZero() {
        assertEquals(1, calculatorUI.factorial(0));
    }

    @Test
    void testFactorialOne() {
        assertEquals(1, calculatorUI.factorial(1));
    }

    @Test
    void testFactorialSmallNumbers() {
        assertEquals(2, calculatorUI.factorial(2));
        assertEquals(6, calculatorUI.factorial(3));
        assertEquals(120, calculatorUI.factorial(5));
    }

    @Test
    void testFactorialLargeNumbers() {
        assertEquals(3628800, calculatorUI.factorial(10));
        assertEquals(2432902008176640000L, calculatorUI.factorial(20));
    }

    @Test
    void testFactorialNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> calculatorUI.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> calculatorUI.factorial(-100));
    }

    @Test
    void testFactorialEdgeCases() {
        // 21! overflows long, but let's see what happens
        long result = calculatorUI.factorial(21);
        assertTrue(result < 0, "Factorial of 21 should overflow and be negative");
    }

    @Test
    void testFactorialMaxInt() {
        // Should overflow, but should not throw unless negative
        long result = calculatorUI.factorial(Integer.MAX_VALUE);
        assertTrue(result < 0, "Factorial of Integer.MAX_VALUE should overflow and be negative");
    }
}
