package ru.miet.testing;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new CalculatorImpl();
            CalculatorFrame frame = new CalculatorFrame();
            CalculatorPresenter presenter = new CalculatorPresenterImpl(calculator, frame);

            frame.setPresenter(presenter);
            frame.setVisible(true);
        });
    }
}
