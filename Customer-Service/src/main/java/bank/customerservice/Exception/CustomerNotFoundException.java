package bank.customerservice.Exception;

public class CustomerNotFoundException extends BaseException{

    public CustomerNotFoundException(Long id) {
        super("Customer not found with ID: " + id, 404);
    }

}
