package limit.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@JsonbPropertyOrder({"productCode","currencyCode","maximumLimit"})
public class BankLimitDTO {
    private String productCode;
    private String currencyCode;
    private BigDecimal maximumLimit;
}
