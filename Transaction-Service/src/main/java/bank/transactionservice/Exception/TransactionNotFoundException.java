package bank.transactionservice.Exception;

import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends BaseException{


    public TransactionNotFoundException(Long id) {

        super("Transaction with ID " + id + " not found", HttpStatus.NOT_FOUND.value());
    }

}
