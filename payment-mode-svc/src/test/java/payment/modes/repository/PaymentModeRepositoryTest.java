package payment.modes.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.modes.model.PaymentModeDTO;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentModeRepositoryTest {
    private PaymentModeRepository paymentModeRepository;
    private List<PaymentModeDTO> paymentModes;

    @BeforeAll
    void setUp() {
        var paymentModeDTO = new PaymentModeDTO();
        paymentModeDTO.setCode("INTERNAL_TRANSER");
        paymentModeDTO.setDescription("Internal Transfer");
        paymentModes = Arrays.asList(paymentModeDTO);

        paymentModeRepository = new PaymentModeRepository();
        paymentModeRepository.fillPaymentModes(paymentModes);
    }

    @Test
    void getPaymentModes() {
        Assertions.assertNotNull(paymentModeRepository.getPaymentModes());
    }
}
