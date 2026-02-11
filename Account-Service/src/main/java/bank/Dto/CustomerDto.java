package bank.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerDto(
        String name,
        LocalDate dateOfBirth,
        String email,
        String phone,
        String address,
        LocalDateTime createdAt,
        String status
)  {


}
