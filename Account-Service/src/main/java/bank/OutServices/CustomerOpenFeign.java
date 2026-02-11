package bank.OutServices;

import bank.Dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Customer-Service")
public interface CustomerOpenFeign {

    @GetMapping("/customers/exists/{customerId}")
    public Boolean existsCustomerById(@PathVariable("customerId") Long customerId);

}
