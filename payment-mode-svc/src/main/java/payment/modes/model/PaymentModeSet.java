package payment.modes.model;

import java.util.List;

public class PaymentModeSet {
    private List<PaymentModeDTO> paymentModes;

    public List<PaymentModeDTO> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<PaymentModeDTO> paymentModes) {
        this.paymentModes = paymentModes;
    }
}

