package bank.Exception;

import bank.Constants.AccountStatus;

public class InvalidAccountStatusException extends BaseException {

    public InvalidAccountStatusException(String accountNumber, AccountStatus currentStatus, String requiredStatus) {
        super(String.format("Account %s has status %s. Required status: %s",
                accountNumber, currentStatus, requiredStatus), 400);
    }
}