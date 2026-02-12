package bank.transactionservice.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm a", timezone = "Africa/Cairo")
    private LocalDateTime time = LocalDateTime.now();

    private Map<String, String> errors = new HashMap<>();


    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}