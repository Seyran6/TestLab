package ru.miet.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.mockito.Mockito.*;

class CalculatorFrameUiTest {

    private CalculatorFrame frame;
    private CalculatorPresenter presenterMock;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            frame = new CalculatorFrame();
            presenterMock = mock(CalculatorPresenter.class);
            frame.setPresenter(presenterMock);
        });
    }

    @AfterEach
    void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.dispose());
    }

    @Test
    void clickingPlusButtonShouldCallOnPlusClicked() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getPlusButton().doClick());

        verify(presenterMock, times(1)).onPlusClicked();
        verifyNoMoreInteractions(presenterMock);
    }

    @Test
    void clickingMinusButtonShouldCallOnMinusClicked() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getMinusButton().doClick());

        verify(presenterMock, times(1)).onMinusClicked();
        verifyNoMoreInteractions(presenterMock);
    }

    @Test
    void clickingMultiplyButtonShouldCallOnMultiplyClicked() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getMultiplyButton().doClick());

        verify(presenterMock, times(1)).onMultiplyClicked();
        verifyNoMoreInteractions(presenterMock);
    }

    @Test
    void clickingDivideButtonShouldCallOnDivideClicked() throws Exception {
        SwingUtilities.invokeAndWait(() -> frame.getDivideButton().doClick());

        verify(presenterMock, times(1)).onDivideClicked();
        verifyNoMoreInteractions(presenterMock);
    }
}
