package limit.helper;

import limit.model.collection.BankLimitSet;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class BankLimitLoader {
    @Getter
    private static final BankLimitLoader instance = new BankLimitLoader();

    private BankLimitLoader() {}

    public BankLimitSet loadFrom(InputStream dataSourceInputStream) {
        var mapper = new Yaml();
        return mapper.loadAs(dataSourceInputStream, BankLimitSet.class);
    }
}
