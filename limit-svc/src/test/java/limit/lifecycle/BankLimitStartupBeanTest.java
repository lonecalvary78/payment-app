package limit.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import limit.model.BankLimitDTO;
import limit.repository.BankLimitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankLimitStartupBeanTest {
    @InjectMocks
    private BankLimitStartupBean bankLimitStartupBean;

    @Mock
    private BankLimitRepository bankLimitRepository;

    @Test
    void onStart() {
        doCallRealMethod().when(bankLimitRepository).fillBankLimits(any());
        var startupEvent = new StartupEvent();
        bankLimitStartupBean.onStart(startupEvent);
        verify(bankLimitRepository, times(1)).fillBankLimits(any());
    }

    @Test
    void onStop() {
        var shutdownEvent = new ShutdownEvent();
        Assertions.assertDoesNotThrow(()->bankLimitStartupBean.onStop(shutdownEvent));
    }
}
