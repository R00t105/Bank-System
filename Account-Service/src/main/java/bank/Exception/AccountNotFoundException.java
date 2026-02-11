package bank.Exception;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException(Long id) {
        super("Account not found with ID: " + id, 404);
    }

    public AccountNotFoundException(String accountNumber) {
        super("Account not found with account number: " + accountNumber, 404);
    }
}