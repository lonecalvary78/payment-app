package payment.modes.repository;

import jakarta.enterprise.context.ApplicationScoped;
import payment.modes.model.PaymentModeDTO;

import java.util.List;

@ApplicationScoped
public class PaymentModeRepository {
    private List<PaymentModeDTO> paymentModes;

    public List<PaymentModeDTO> getPaymentModes() {
        return this.paymentModes;
    }

    public void fillPaymentModes(List<PaymentModeDTO> paymentModes) {
        this.paymentModes = paymentModes;
    }
}
