package ru.miet.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {

    private final Calculator calculator = new CalculatorImpl();

    @Test
    void sumShouldReturnCorrectResult()
    {
        assertEquals(7.0, calculator.sum(3.0, 4.0), 1e-9);
    }

    @Test
    void sumShouldWorkWithNegativeNumbers()
    {
        assertEquals(-1.0, calculator.sum(-3.0, 2.0), 1e-9);
    }

    @Test
    void subtractShouldReturnCorrectResult()
    {
        assertEquals(2.0, calculator.subtract(5.0, 3.0), 1e-9);
    }

    @Test
    void subtractShouldWorkWithNegativeNumbers()
    {
        assertEquals(-8.0, calculator.subtract(-5.0, 3.0), 1e-9);
    }

    @Test
    void multiplyShouldReturnCorrectResult()
    {
        assertEquals(12.0, calculator.multiply(3.0, 4.0), 1e-9);
    }

    @Test
    void multiplyByZeroShouldReturnZero()
    {
        assertEquals(0.0, calculator.multiply(100.0, 0.0), 1e-9);
    }

    @Test
    void divideShouldReturnCorrectResult()
    {
        assertEquals(2.0, calculator.divide(10.0, 5.0), 1e-9);
    }

    @Test
    void divideShouldWorkWithNegativeNumbers() {
        assertEquals(-2.0, calculator.divide(-10.0, 5.0), 1e-9);
    }

    @Test
    void divideByZeroShouldThrowException() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10.0, 0.0)
        );
    }

    @Test
    void divideByTooSmallPositiveNumberShouldThrowException() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10.0, 0.5e-7)
        );
    }

    @Test
    void divideByTooSmallNegativeNumberShouldThrowException() {
        assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10.0, -0.5e-7)
        );
    }

    @Test
    void divideByBoundaryValueShouldBeAllowed() {
        assertEquals(
                10.0 / 1e-7,
                calculator.divide(10.0, 1e-7),
                1e-9
        );
    }
}
