package limit.repository;

import limit.base.BaseTest;
import limit.exception.BankLimitNotFoundException;
import limit.helper.BankLimitLoader;
import limit.model.BankLimitDTO;
import limit.model.collection.BankLimitSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankLimitRepositoryTest extends BaseTest {
    private BankLimitRepository bankLimitRepository;

    private BankLimitSet bankLimitSet;

    @BeforeEach
    void setUp() {
        bankLimitRepository = new BankLimitRepository();
        var bankLimit = new BankLimitDTO();
                bankLimit.setProductCode("INTERNAL_TRANSFER");
                bankLimit.setCurrencyCode("IDR");
                bankLimit.setMaximumLimit(BigDecimal.valueOf(5000000));
        var bankLimits = Arrays.asList(bankLimit);
        bankLimitSet = new BankLimitSet();
        bankLimitSet.setBankLimits(bankLimits);
    }

    @Test
    void getBankLimits() {
        bankLimitRepository.fillBankLimits(bankLimitSet.getBankLimits());
        Assertions.assertNotNull(bankLimitRepository.getBankLimits());
    }

    @ParameterizedTest
    @MethodSource("testParametersForGetBankLimitForSpecificProduct")
    void getBankLimitForSpecificProduct(Map<String, Object> testParams) {
        var productCode = String.valueOf(testParams.get("productCode"));
        var isNegativeCase = (boolean)testParams.get("isNegativeCase");
        var bankLimitSet = BankLimitLoader.getInstance().loadFrom(this.getClass().getClassLoader().getResourceAsStream("bank-default-limit.yaml"));
        bankLimitRepository.fillBankLimits(bankLimitSet.getBankLimits());
        if (!isNegativeCase) {
            Assertions.assertAll(
                    () -> Assertions.assertDoesNotThrow(() -> bankLimitRepository.getBankLimitByProductCode(productCode)),
                    () -> Assertions.assertNotNull(bankLimitRepository.getBankLimitByProductCode(productCode)));
        } else {
            Assertions.assertThrows(BankLimitNotFoundException.class, () -> bankLimitRepository.getBankLimitByProductCode(productCode));
        }
    }

    static Stream<Map<String,Object>> testParametersForGetBankLimitForSpecificProduct() {
       return constructNewTestParameters(new String[] {"productCode","isNegativeCase"}, new Object[][] {{"INTERNAL_TRANSFER",false}, {"UNKNOWN", true}});
    }
}
