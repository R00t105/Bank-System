package bank.transactionservice.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public record TransactionRequest(

        @NotBlank(message = "Transaction Type is Required")
        String transactionType,
        @Range(min = 100, max = 1000000, message = "Amount must be between 100 and 1,000,000")
        BigDecimal amount,

        Long fromAccountId,
        Long toAccountId

) {
}
