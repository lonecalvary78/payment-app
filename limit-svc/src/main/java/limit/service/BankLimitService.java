package limit.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import limit.exception.BankLimitNotFoundException;
import limit.model.BankLimitDTO;
import limit.repository.BankLimitRepository;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class BankLimitService {
    private Logger logger = Logger.getLogger(BankLimitService.class);
    private final BankLimitRepository bankLimitRepository;

    @Inject
    public BankLimitService(BankLimitRepository bankLimitRepository) {
        this.bankLimitRepository = bankLimitRepository;
    }

    public List<BankLimitDTO> getBankLimits() {
        logger.info("getBankLimits");
        return bankLimitRepository.getBankLimits();
    }

    public BankLimitDTO getBankLimitByProductCode(String productCode) throws BankLimitNotFoundException {
        return bankLimitRepository.getBankLimitByProductCode(productCode);
    }
}
