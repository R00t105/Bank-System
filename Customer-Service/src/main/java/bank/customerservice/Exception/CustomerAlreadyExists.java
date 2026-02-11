package bank.customerservice.Exception;

public class CustomerAlreadyExists extends BaseException {

    public CustomerAlreadyExists(String nationalId) {
        super("This National ID already exists: " + nationalId, 409);
    }

}
