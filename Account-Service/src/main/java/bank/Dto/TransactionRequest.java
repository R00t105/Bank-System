package bank.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotBlank(message = "Transaction Type is Required")
    String transactionType;
    @Range(min = 100, max = 1000000, message = "Amount must be between 100 and 1,000,000")
    BigDecimal amount;

    Long fromAccountId;
    Long toAccountId;
}