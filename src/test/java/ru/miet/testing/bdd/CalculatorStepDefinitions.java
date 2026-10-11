package ru.miet.testing.bdd;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.miet.testing.CalculatorApp;
import ru.miet.testing.CalculatorFrame;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorStepDefinitions {

    private static final String RESULT_PREFIX = "Результат: ";

    private CalculatorFrame frame;
    private String lastError;
    private String resultBeforeAction = RESULT_PREFIX;

    @Given("открыто окно калькулятора")
    public void openCalculator() throws Exception {
        lastError = null;
        SwingUtilities.invokeAndWait(() -> {
            frame = new CalculatorFrame() {
                @Override
                public void displayError(String message) {
                    lastError = message;
                }
            };
            CalculatorApp.wire(frame);
        });
    }

    @After
    public void closeCalculator() throws Exception {
        if (frame != null) {
            SwingUtilities.invokeAndWait(() -> frame.dispose());
        }
    }

    @Given("я ввел {string} в поле первого аргумента")
    public void enterFirst(String value) throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getFirstArgumentField().setText(value));
    }

    @Given("я ввел {string} в поле второго аргумента")
    public void enterSecond(String value) throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getSecondArgumentField().setText(value));
    }

    @When("я нажимаю кнопку {string}")
    public void pressButton(String action) throws Exception {
        JButton button = switch (action) {
            case "плюс" -> frame.getPlusButton();
            case "минус" -> frame.getMinusButton();
            case "умножить" -> frame.getMultiplyButton();
            case "делить" -> frame.getDivideButton();
            default -> throw new IllegalArgumentException("Неизвестное действие: " + action);
        };
        resultBeforeAction = resultText();
        lastError = null;
        SwingUtilities.invokeAndWait(button::doClick);
    }

    @Then("на экране отображается {string}")
    public void screenShows(String expectedText) {
        assertEquals(expectedText, resultText());
    }

    @Then("результат на экране равен {word}")
    public void resultEquals(String expected) {
        String text = resultText();
        assertTrue(text.startsWith(RESULT_PREFIX), "Неожиданный текст результата: " + text);
        double actual = Double.parseDouble(text.substring(RESULT_PREFIX.length()).trim());
        assertEquals(Double.parseDouble(expected), actual, 1e-9);
    }

    @Then("результат на экране не изменился")
    public void resultNotChanged() {
        assertEquals(resultBeforeAction, resultText());
    }

    @Then("показана ошибка {string}")
    public void errorShown(String expectedMessage) {
        assertEquals(expectedMessage, lastError);
    }

    @Then("ошибка не показана")
    public void noErrorShown() {
        assertNull(lastError, "Не ожидалась ошибка, но показано: " + lastError);
    }

    private String resultText() {
        return frame.getResultLabel().getText();
    }
}
