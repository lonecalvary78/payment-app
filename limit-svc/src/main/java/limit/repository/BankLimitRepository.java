package limit.repository;

import jakarta.enterprise.context.ApplicationScoped;
import limit.exception.BankLimitNotFoundException;
import limit.model.BankLimitDTO;

import java.util.List;

@ApplicationScoped
public class BankLimitRepository {
    private List<BankLimitDTO> bankLimits;

    public List<BankLimitDTO> getBankLimits() {
        return bankLimits;
    }
    public BankLimitDTO getBankLimitByProductCode(String productCode) throws BankLimitNotFoundException {
        return bankLimits.stream().filter(bankLimitDTO -> bankLimitDTO.getProductCode().equals(productCode)).findFirst().orElseThrow(()-> new BankLimitNotFoundException(productCode));
    }

    public void fillBankLimits(List<BankLimitDTO> bankLimits) {
        this.bankLimits = bankLimits;
    }
}
