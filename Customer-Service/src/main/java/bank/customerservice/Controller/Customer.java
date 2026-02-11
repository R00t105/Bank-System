package bank.customerservice.Controller;

import bank.customerservice.Dto.AddCustomerRequest;
import bank.customerservice.Dto.CustomerAddedResponse;
import bank.customerservice.Dto.UpdateRequest;
import bank.customerservice.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers (CRUD Operations)")
public class Customer {

    private final CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Returns a list of all customers")
    @GetMapping
    public ResponseEntity<List<CustomerAddedResponse>> getCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @Operation(summary = "Get customer by ID", description = "Returns a customer by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddedResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }

    @Operation(summary = "Add a new customer", description = "Adds a new customer to the system")
    @PostMapping
    public ResponseEntity<CustomerAddedResponse> addCustomer(@RequestBody @Valid AddCustomerRequest customerRequest) {
        return ResponseEntity.status(201).body(customerService.addCustomer(customerRequest));
    }

    @Operation(summary = "Update customer details", description = "Updates the details of an existing customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddedResponse> editCustomer(@PathVariable Long id, @RequestBody @Valid UpdateRequest updates){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.editCustomer(id, updates));
    }

    @Operation(summary = "Delete a customer", description = "Deletes a customer by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer deleted successfully");
    }

    @Operation(summary = "Check if customer exists by ID", description = "Checks if a customer exists by their ID")
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> customerExists(@PathVariable Long id) {
        return ResponseEntity.status(200).body(customerService.existsById(id));
    }
}
