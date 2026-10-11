package ru.miet.testing;

public class CalculatorPresenterImpl implements CalculatorPresenter {

    private final Calculator calculator;
    private final CalculatorView view;

    public CalculatorPresenterImpl(Calculator calculator, CalculatorView view) {
        this.calculator = calculator;
        this.view = view;
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '+'
     */
    @Override
    public void onPlusClicked() {
        try {
            double a = getFirstArgument();
            double b = getSecondArgument();

            double result = calculator.sum(a, b);
            view.printResult(result);

        } catch (NumberFormatException e) {
            view.displayError("Введите корректные числа");
        } catch (ArithmeticException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '-'
     */
    @Override
    public void onMinusClicked() {
        try {
            double a = getFirstArgument();
            double b = getSecondArgument();

            double result = calculator.subtract(a, b);
            view.printResult(result);

        } catch (NumberFormatException e) {
            view.displayError("Введите корректные числа");
        } catch (ArithmeticException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '/'
     */
    @Override
    public void onDivideClicked() {
        try {
            double a = getFirstArgument();
            double b = getSecondArgument();

            double result = calculator.divide(a, b);
            view.printResult(result);

        } catch (NumberFormatException e) {
            view.displayError("Введите корректные числа");
        } catch (ArithmeticException e) {
            view.displayError(e.getMessage());
        }
    }

    /**
     * Вызывается формой в тот момент, когда пользователь нажал на кнопку '*'
     */
    @Override
    public void onMultiplyClicked() {
        try {
            double a = getFirstArgument();
            double b = getSecondArgument();

            double result = calculator.multiply(a, b);
            view.printResult(result);

        } catch (NumberFormatException e) {
            view.displayError("Введите корректные числа");
        } catch (ArithmeticException e) {
            view.displayError(e.getMessage());
        }
    }

    private double getFirstArgument() {
        return Double.parseDouble(view.getFirstArgumentAsString());
    }

    private double getSecondArgument() {
        return Double.parseDouble(view.getSecondArgumentAsString());
    }
}
