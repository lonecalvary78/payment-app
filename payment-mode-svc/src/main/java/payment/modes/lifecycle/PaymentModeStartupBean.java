package payment.modes.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import payment.modes.helper.PaymentModeLoader;
import payment.modes.model.PaymentModeSet;
import payment.modes.repository.PaymentModeRepository;

@ApplicationScoped
public class PaymentModeStartupBean {
    private final Logger logger = Logger.getLogger(PaymentModeStartupBean.class);

    @Inject
    private PaymentModeRepository paymentModeRepository;

    public void onStart(@Observes StartupEvent startupEvent) {
        logger.info("starting the payment mode service");
        var paymentModeSet = (PaymentModeSet) PaymentModeLoader.getInstance().loadDataFrom(this.getClass().getClassLoader().getResourceAsStream("payment-modes.yaml"));
        paymentModeRepository.fillPaymentModes(paymentModeSet.getPaymentModes());
    }

    public void onStop(@Observes ShutdownEvent shutdown) {
        logger.info("shutting the payment mode service");
    }
}
