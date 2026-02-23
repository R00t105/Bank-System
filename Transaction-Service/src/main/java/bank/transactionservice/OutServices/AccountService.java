package bank.transactionservice.OutServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "Account-Service")
public interface AccountService {

    @PostMapping("accounts/deposit")
    public BigDecimal deposit(@RequestParam("amount") BigDecimal amount);

}
