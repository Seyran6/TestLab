package ru.miet.testing;

public class CalculatorImpl implements Calculator {

    /**
     * Вычисляет сумму двух чисел
     */
    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    /**
     * Вычисляет разность двух чисел a - b
     */
    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Вычисляет произведение двух чисел
     */
    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Вычисляет отношение числа a к числу b.
     * Должен выбросить ArithmeticException если |b| < 10e-8
     */
    @Override
    public double divide(double a, double b) {
        if (Math.abs(b) < 10e-8) {
            throw new ArithmeticException("Деление на ноль или слишком малый делитель");
        }

        return a / b;
    }
}
