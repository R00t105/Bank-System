package bank.transactionservice.Controller;

import bank.transactionservice.DTOs.TransactionRequest;
import bank.transactionservice.Entity.Transaction;
import bank.transactionservice.Exception.TransactionNotFoundException;
import bank.transactionservice.Service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "transactions", description = "Transactions management")
public class TransactionController {


    private final TransactionService transactionService;


    @Operation(summary = "Get All Transactions", description = "Get All Transactions from DB")
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }


    @Operation(summary = "Save Transaction", description = "Save Transaction to DB")
    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        transactionService.saveTransaction(transactionRequest);
        return ResponseEntity.ok("Transaction saved successfully");
    }


    @Operation(summary = "Get Transaction by ID", description = "Get Transaction by ID from DB")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) throws TransactionNotFoundException {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }


}
