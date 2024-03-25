package payment.modes.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import payment.modes.model.PaymentModeDTO;
import payment.modes.service.PaymentModeService;

import java.util.List;

@Path("/payment-modes")
public class PaymentModeController {
    @Inject
    private PaymentModeService paymentModeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaymentModeDTO> getPaymentModes() {
        return paymentModeService.getPaymentModes();
    }
}
