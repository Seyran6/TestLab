package ru.miet.testing;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame implements CalculatorView {

    private final JTextField firstArgumentField = new JTextField(10);
    private final JTextField secondArgumentField = new JTextField(10);
    private final JLabel resultLabel = new JLabel("Результат: ");

    private final JButton plusButton = new JButton("+");
    private final JButton minusButton = new JButton("-");
    private final JButton multiplyButton = new JButton("*");
    private final JButton divideButton = new JButton("/");

    private CalculatorPresenter presenter;

    public CalculatorFrame() {
        super("Калькулятор");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        inputPanel.add(new JLabel("Аргумент A:"));
        inputPanel.add(firstArgumentField);
        inputPanel.add(new JLabel("Аргумент B:"));
        inputPanel.add(secondArgumentField);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.add(plusButton);
        buttonsPanel.add(minusButton);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(divideButton);

        plusButton.addActionListener(e -> presenter.onPlusClicked());
        minusButton.addActionListener(e -> presenter.onMinusClicked());
        multiplyButton.addActionListener(e -> presenter.onMultiplyClicked());
        divideButton.addActionListener(e -> presenter.onDivideClicked());

        resultLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(inputPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        setSize(340, 190);
        setLocationRelativeTo(null);
    }

    public void setPresenter(CalculatorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void printResult(double result) {
        resultLabel.setText("Результат: " + result);
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE
        );
    }

    @Override
    public String getFirstArgumentAsString() {
        return firstArgumentField.getText();
    }

    @Override
    public String getSecondArgumentAsString() {
        return secondArgumentField.getText();
    }

    // Геттеры, нужные только для UI-тестов

    public JButton getPlusButton() {
        return plusButton;
    }

    public JButton getMinusButton() {
        return minusButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JTextField getFirstArgumentField() {
        return firstArgumentField;
    }

    public JTextField getSecondArgumentField() {
        return secondArgumentField;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }
}
