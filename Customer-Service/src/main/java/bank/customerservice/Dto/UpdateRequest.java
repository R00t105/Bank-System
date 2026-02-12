package bank.customerservice.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateRequest(
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @Email(message = "Invalid email format")
        String email,

        @Pattern(
                regexp = "^01[0-2,5][0-9]{8}$",
                message = "Invalid Egyptian phone number")
        String phone,

        @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
        String address
) {
}
