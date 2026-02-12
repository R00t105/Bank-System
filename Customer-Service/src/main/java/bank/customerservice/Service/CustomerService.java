package bank.customerservice.Service;

import bank.customerservice.Dto.AddCustomerRequest;
import bank.customerservice.Dto.CustomerAddedResponse;
import bank.customerservice.Dto.UpdateRequest;
import bank.customerservice.Entity.Customer;
import bank.customerservice.Exception.CustomerAlreadyExists;
import bank.customerservice.Exception.CustomerNotFoundException;
import bank.customerservice.Mapper.CustomerMapper;
import bank.customerservice.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerAddedResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toCustomerAddedResponse).toList();
    }

    public CustomerAddedResponse getCustomerById(Long id) {
        return customerMapper.toCustomerAddedResponse(customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)));
    }

    public CustomerAddedResponse addCustomer(AddCustomerRequest customerRequest) {
        Customer checkCustomerExist = customerRepository.findByNationalId(customerRequest.getNationalId());

        if (checkCustomerExist != null) {
            throw new CustomerAlreadyExists(checkCustomerExist.getNationalId());
        }

        Customer customer = new Customer();
        customer = customerMapper.toCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerAddedResponse(savedCustomer);
    }

    @Transactional
    public CustomerAddedResponse editCustomer(Long id, UpdateRequest updates) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        Customer updatedCustomer = customerMapper.updateEntityFromDto(updates, customer);
        return customerMapper.toCustomerAddedResponse(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.delete(customer);
    }

    public Boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

}
