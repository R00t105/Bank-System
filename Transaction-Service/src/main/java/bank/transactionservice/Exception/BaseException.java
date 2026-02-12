package bank.transactionservice.Exception;

import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private int statusCode;


    public BaseException(String message) {
        super(message);
    }


    public BaseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
