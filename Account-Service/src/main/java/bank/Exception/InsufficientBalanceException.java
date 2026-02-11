package bank.Exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends BaseException {

    public InsufficientBalanceException(String accountNumber, BigDecimal requestedAmount, BigDecimal availableBalance) {
        super(String.format("Insufficient balance in account %s. Requested: %s, Available: %s",
                accountNumber, requestedAmount, availableBalance), 400);
    }
}