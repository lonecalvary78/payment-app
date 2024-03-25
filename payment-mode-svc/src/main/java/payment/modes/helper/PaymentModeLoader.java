package payment.modes.helper;

import org.yaml.snakeyaml.Yaml;
import payment.modes.model.PaymentModeSet;

import java.io.InputStream;

public class PaymentModeLoader {
    private static PaymentModeLoader instance = new PaymentModeLoader();
    public static PaymentModeLoader getInstance() { return instance; }

    private PaymentModeLoader() {}

    public PaymentModeSet loadDataFrom(InputStream dataSourceInputStream) {
       var converter = new Yaml();
       return converter.loadAs(dataSourceInputStream, PaymentModeSet.class);
    }

}
