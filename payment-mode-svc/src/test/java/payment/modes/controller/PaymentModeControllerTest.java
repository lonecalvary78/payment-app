package payment.modes.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.modes.model.PaymentModeDTO;
import payment.modes.service.PaymentModeService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentModeControllerTest {
    @InjectMocks
    private PaymentModeController paymentModeController;

    @Mock
    private PaymentModeService paymentModeService;

    @Test
    void getPaymentModes() {
        when(paymentModeService.getPaymentModes()).thenReturn(Arrays.asList(new PaymentModeDTO()));
        Assertions.assertAll(()->Assertions.assertDoesNotThrow(
                ()->paymentModeController.getPaymentModes()),
                ()->Assertions.assertNotNull(paymentModeController.getPaymentModes()));
        verify(paymentModeService, times(2)).getPaymentModes();
    }
}
