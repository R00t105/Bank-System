package bank.customerservice.Repository;

import bank.customerservice.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNationalId(String nationalId);
    Boolean existsCustomerById(Long id);
}
