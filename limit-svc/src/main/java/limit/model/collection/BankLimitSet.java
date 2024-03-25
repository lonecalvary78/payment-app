package limit.model.collection;

import limit.model.BankLimitDTO;
import lombok.Data;

import java.util.List;

@Data
public class BankLimitSet {
    private List<BankLimitDTO> bankLimits;
}
