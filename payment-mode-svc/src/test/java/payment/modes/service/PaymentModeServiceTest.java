package payment.modes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.modes.model.PaymentModeDTO;
import payment.modes.repository.PaymentModeRepository;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentModeServiceTest {
    @InjectMocks
    private PaymentModeService paymentModeService;

    @Mock
    private PaymentModeRepository paymentModeRepository;

    @Test
    void getPaymentModes() {
        when(paymentModeRepository.getPaymentModes()).thenReturn(Arrays.asList(new PaymentModeDTO()));
        Assertions.assertAll(
                ()->Assertions.assertDoesNotThrow(()->paymentModeService.getPaymentModes()),
                ()->Assertions.assertNotNull(paymentModeService.getPaymentModes()));
        verify(paymentModeRepository, times(2)).getPaymentModes();
    }
}
