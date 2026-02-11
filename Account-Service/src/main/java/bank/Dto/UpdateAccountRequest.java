package bank.Dto;

import bank.Constants.AccountStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateAccountRequest(

        @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code")
        String currency,

        AccountStatus status,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description) {
}