package limit.service;

import limit.exception.BankLimitNotFoundException;
import limit.model.BankLimitDTO;
import limit.repository.BankLimitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.test.CommonPaymentTest;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankLimitServiceTest extends CommonPaymentTest {
    @InjectMocks
    private BankLimitService bankLimitService;

    @Mock
    private BankLimitRepository bankLimitRepository;

    @Test
    void getBankLimits() {
        when(bankLimitRepository.getBankLimits()).thenReturn(Arrays.asList(new BankLimitDTO()));
        bankLimitService.getBankLimits();
        verify(bankLimitRepository, times(1)).getBankLimits();
    }

    @ParameterizedTest
    @MethodSource("testParametersForGetBankLimitForSpecificProduct")
    void getBankLimitForSpecificProduct(Map<String, Object> testParams) throws BankLimitNotFoundException {
        var productCode = String.valueOf(testParams.get("productCode"));
        var isNegativeCase = (boolean) testParams.get("isNegativeCase");
        if (!isNegativeCase) {
            when(bankLimitRepository.getBankLimitByProductCode(any())).thenReturn(new BankLimitDTO());
            Assertions.assertAll(
                    ()-> Assertions.assertDoesNotThrow(()->bankLimitService.getBankLimitByProductCode(productCode)),
                    ()-> Assertions.assertNotNull(bankLimitService.getBankLimitByProductCode(productCode)));
            verify(bankLimitRepository, times(2)).getBankLimitByProductCode(any());
        } else {
            doThrow(BankLimitNotFoundException.class).when(bankLimitRepository).getBankLimitByProductCode(any());
            Assertions.assertThrows(BankLimitNotFoundException.class,()->bankLimitService.getBankLimitByProductCode(productCode));
            verify(bankLimitRepository, times(1)).getBankLimitByProductCode(any());
        }
    }

    static Stream<Map<String,Object>> testParametersForGetBankLimitForSpecificProduct() {
        return constructNewTestParameters(new String[] {"productCode","isNegativeCase"}, new Object[][] {{"INTERNAL_TRANSFER",false}, {"UNKNOWN", true}});
    }

}
