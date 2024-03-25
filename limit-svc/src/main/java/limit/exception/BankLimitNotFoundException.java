package limit.exception;

public class BankLimitNotFoundException extends Exception {
    public BankLimitNotFoundException(String productCode) {
        super("There is no bank limit for this product[ProductCode: %s]".formatted(productCode));
    }
}
