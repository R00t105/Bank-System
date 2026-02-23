package bank.OutServices;

import bank.Dto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Transaction-Service")
public interface TransactionOpenFeign {

    @PostMapping("transactions/save")
    public TransactionRequest saveTransaction(@RequestBody TransactionRequest transactionRequest);

}
