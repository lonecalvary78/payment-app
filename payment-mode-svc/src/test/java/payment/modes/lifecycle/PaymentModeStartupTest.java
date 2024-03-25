package payment.modes.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.modes.repository.PaymentModeRepository;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doCallRealMethod;

@ExtendWith(MockitoExtension.class)
public class PaymentModeStartupTest {
    @InjectMocks
    private PaymentModeStartupBean paymentModeStartupBean;

    @Mock
    private PaymentModeRepository paymentModeRepository;

    @Test
    void onStart() {
        var startupEvent = new StartupEvent();
        doCallRealMethod().when(paymentModeRepository).fillPaymentModes(any());
        Assertions.assertDoesNotThrow(()->paymentModeStartupBean.onStart(startupEvent));
        verify(paymentModeRepository, times(1)).fillPaymentModes(any());
    }

    @Test
    void onStop() {
        var shutdownEvent = new ShutdownEvent();
        paymentModeStartupBean.onStop(shutdownEvent);
    }
}
