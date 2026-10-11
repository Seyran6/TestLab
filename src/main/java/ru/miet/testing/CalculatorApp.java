package ru.miet.testing;

public final class CalculatorApp {

    private CalculatorApp() {
    }

    /**
     * Подключает к форме презентер с реальной реализацией калькулятора.
     *
     * @return тот же экземпляр формы (для удобства цепочек вызовов)
     */
    public static CalculatorFrame wire(CalculatorFrame frame) {
        Calculator calculator = new CalculatorImpl();
        CalculatorPresenter presenter = new CalculatorPresenterImpl(calculator, frame);
        frame.setPresenter(presenter);
        return frame;
    }
}
