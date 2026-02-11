package bank.customerservice.Dto;

import bank.customerservice.Constants.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddedResponse {

    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm a",
            timezone = "Africa/Cairo"
    )
    private LocalDateTime createdAt;
    private CustomerStatus status = CustomerStatus.PENDING;

}
