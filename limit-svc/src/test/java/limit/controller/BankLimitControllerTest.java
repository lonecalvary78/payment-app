package limit.controller;

import limit.base.BaseTest;
import limit.exception.BankLimitNotFoundException;
import limit.model.BankLimitDTO;
import limit.service.BankLimitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankLimitControllerTest extends BaseTest {
    @InjectMocks
    private BankLimitController bankLimitController;

    @Mock
    private BankLimitService bankLimitService;

    @Test
    void getBankLimits() {
        when(bankLimitService.getBankLimits()).thenReturn(Arrays.asList(new BankLimitDTO()));
        Assertions.assertNotNull(bankLimitController.getBankLimits());
        verify(bankLimitService, times(1)).getBankLimits();
    }

    @ParameterizedTest
    @MethodSource("testParametersForGetBankLimitForSpecificProduct")
    void getBankLimitForSpecificProduct(Map<String, Object> testParams) throws BankLimitNotFoundException {
        var productCode = String.valueOf(testParams.get("productCode"));
        var isNegativeCase = (boolean) testParams.get("isNegativeCase");

        if (!isNegativeCase){
            when(bankLimitService.getBankLimitByProductCode(any())).thenReturn(new BankLimitDTO());
            Assertions.assertAll(
                    ()-> Assertions.assertDoesNotThrow((()->bankLimitController.getBankLimitByProductCode(productCode))),
                    ()-> Assertions.assertNotNull(bankLimitController.getBankLimitByProductCode(productCode))
            );
            verify(bankLimitService, times(2)).getBankLimitByProductCode(any());
        } else {
            doThrow(BankLimitNotFoundException.class).when(bankLimitService).getBankLimitByProductCode(any());
            Assertions.assertThrows(BankLimitNotFoundException.class, ()->bankLimitController.getBankLimitByProductCode(productCode));
            verify(bankLimitService, times(1)).getBankLimitByProductCode(any());
        }
    }

    static Stream<Map<String,Object>> testParametersForGetBankLimitForSpecificProduct() {
        return constructNewTestParameters(new String[] {"productCode","isNegativeCase"}, new Object[][] {{"INTERNAL_TRANSFER",false}, {"UNKNOWN", true}});
    }

}
