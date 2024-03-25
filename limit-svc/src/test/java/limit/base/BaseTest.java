package limit.base;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class BaseTest {
    protected static Stream<Map<String, Object>> constructNewTestParameters(String[] parameterNames, Object[][] parameterValues) {
        var testParams = new ArrayList<Map<String, Object>>();
        if(ObjectUtils.isNotEmpty(parameterValues)) {
            for (int rowCounter=0;rowCounter<parameterValues.length;rowCounter++) {
                var parameterValue = parameterValues[rowCounter];
                if (parameterValue.length == parameterNames.length) {
                    var parameterEntry = new HashMap<String, Object>();
                    for (int parameterNameCounter=0;parameterNameCounter<parameterValue.length;parameterNameCounter++) {
                        parameterEntry.put(parameterNames[parameterNameCounter],parameterValue[parameterNameCounter]);
                    }
                    if (!parameterEntry.isEmpty())
                        testParams.add(parameterEntry);
                }
            }

        }
        return testParams.stream();
    }
}
