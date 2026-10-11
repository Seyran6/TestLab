package ru.miet.testing;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorFrame frame = CalculatorApp.wire(new CalculatorFrame());
            frame.setVisible(true);
        });
    }
}
