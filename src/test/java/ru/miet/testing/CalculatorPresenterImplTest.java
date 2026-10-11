package ru.miet.testing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorPresenterImplTest {

    /**
     * Простая тестовая реализация View.
     */
    static class FakeView implements CalculatorView {

        private String firstArgument = "";
        private String secondArgument = "";

        private Double result;
        private String error;

        @Override
        public void printResult(double result) {
            this.result = result;
        }

        @Override
        public void displayError(String message) {
            this.error = message;
        }

        @Override
        public String getFirstArgumentAsString() {
            return firstArgument;
        }

        @Override
        public String getSecondArgumentAsString() {
            return secondArgument;
        }

        public void setArguments(String first, String second) {
            this.firstArgument = first;
            this.secondArgument = second;
        }

        public Double getResult() {
            return result;
        }

        public String getError() {
            return error;
        }
    }

    @Test
    void plusButtonShouldDisplayResult() {
        FakeView view = new FakeView();
        view.setArguments("10", "5");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.sum(10, 5)).thenReturn(15.0);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onPlusClicked();

        assertEquals(15.0, view.getResult(), 1e-9);
        assertNull(view.getError());
    }

    @Test
    void minusButtonShouldDisplayResult() {
        FakeView view = new FakeView();
        view.setArguments("10", "5");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.subtract(10, 5)).thenReturn(5.0);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onMinusClicked();

        assertEquals(5.0, view.getResult(), 1e-9);
        assertNull(view.getError());
    }

    @Test
    void multiplyButtonShouldDisplayResult() {
        FakeView view = new FakeView();
        view.setArguments("10", "5");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.multiply(10, 5)).thenReturn(50.0);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onMultiplyClicked();

        assertEquals(50.0, view.getResult(), 1e-9);
        assertNull(view.getError());
    }

    @Test
    void divideButtonShouldDisplayResult() {
        FakeView view = new FakeView();
        view.setArguments("10", "5");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.divide(10, 5)).thenReturn(2.0);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onDivideClicked();

        assertEquals(2.0, view.getResult(), 1e-9);
        assertNull(view.getError());
    }

    @Test
    void invalidFirstArgumentShouldDisplayError() {
        FakeView view = new FakeView();
        view.setArguments("abc", "5");

        Calculator calculator = Mockito.mock(Calculator.class);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onPlusClicked();

        assertNull(view.getResult());
        assertEquals("Введите корректные числа", view.getError());
    }

    @Test
    void invalidSecondArgumentShouldDisplayError() {
        FakeView view = new FakeView();
        view.setArguments("10", "abc");

        Calculator calculator = Mockito.mock(Calculator.class);

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onMinusClicked();

        assertNull(view.getResult());
        assertEquals("Введите корректные числа", view.getError());
    }

    @Test
    void divideByZeroShouldDisplayError() {
        FakeView view = new FakeView();
        view.setArguments("10", "0");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.divide(10, 0)).thenThrow(new ArithmeticException("Деление на ноль или слишком малый делитель"));

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onDivideClicked();

        assertNull(view.getResult());
        assertEquals(
                "Деление на ноль или слишком малый делитель",
                view.getError()
        );
    }

    @Test
    void divideBySmallNumberShouldDisplayError() {
        FakeView view = new FakeView();
        view.setArguments("10", "0.00000001");

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.divide(10, 0.00000001)).thenThrow(new ArithmeticException("Деление на ноль или слишком малый делитель"));

        CalculatorPresenter presenter =
                new CalculatorPresenterImpl(calculator, view);

        presenter.onDivideClicked();

        assertNull(view.getResult());
        assertEquals(
                "Деление на ноль или слишком малый делитель",
                view.getError()
        );
    }
}
