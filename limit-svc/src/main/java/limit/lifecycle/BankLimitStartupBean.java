package limit.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import limit.helper.BankLimitLoader;
import limit.repository.BankLimitRepository;
import org.jboss.logging.Logger;

@ApplicationScoped
public class BankLimitStartupBean {
    private final Logger logger = Logger.getLogger(BankLimitStartupBean.class);
    private final BankLimitRepository bankLimitRepository;
    @Inject
    public BankLimitStartupBean(BankLimitRepository bankLimitRepository) {
        this.bankLimitRepository = bankLimitRepository;
    }

    public void onStart(@Observes StartupEvent startupEvent) {
        logger.info("Starting bank limit service");
        var bankLimitSet = BankLimitLoader.getInstance().loadFrom(this.getClass().getClassLoader().getResourceAsStream("bank-default-limit.yaml"));
        bankLimitRepository.fillBankLimits(bankLimitSet.getBankLimits());
    }

    public void onStop(@Observes ShutdownEvent shutdownEvent) {
        logger.info("Shutting down bank limit service");
    }
}
