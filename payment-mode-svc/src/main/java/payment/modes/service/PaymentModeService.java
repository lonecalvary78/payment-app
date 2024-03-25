package payment.modes.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import payment.modes.model.PaymentModeDTO;
import payment.modes.repository.PaymentModeRepository;

import java.util.List;

@ApplicationScoped
public class PaymentModeService {
    private final Logger logger = Logger.getLogger(PaymentModeService.class);
    @Inject
    private PaymentModeRepository paymentModeRepository;

    public List<PaymentModeDTO> getPaymentModes() {
       return paymentModeRepository.getPaymentModes();
    }
}
