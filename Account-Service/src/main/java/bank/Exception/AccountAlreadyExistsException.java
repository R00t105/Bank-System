package bank.Exception;

public class AccountAlreadyExistsException extends BaseException {

    public AccountAlreadyExistsException(String accountNumber) {
        super("Account already exists with account number: " + accountNumber, 409);
    }
}