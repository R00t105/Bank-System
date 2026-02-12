package bank.Controller;

import bank.Dto.AccountResponse;
import bank.Dto.CreateAccountRequest;
import bank.Dto.TransactionRequest;
import bank.Dto.UpdateAccountRequest;
import bank.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Management", description = "APIs for managing bank accounts (CRUD and Transactions)")
public class AccountController {

    private final AccountService accountService;


    @Operation(summary = "Get all accounts", description = "Returns a list of all bank accounts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts list")
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }


    @Operation(summary = "Get account by ID", description = "Returns a single account by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(
            @Parameter(description = "Account ID") @PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }


    @Operation(summary = "Get account by account number", description = "Returns a single account by its account number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByNumber(
            @Parameter(description = "Account Number") @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }


    @Operation(summary = "Get accounts by customer ID", description = "Returns all accounts belonging to a specific customer")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved accounts")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomerId(
            @Parameter(description = "Customer ID") @PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }


    @Operation(summary = "Create a new account", description = "Creates a new bank account for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Account number already exists")
    })
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(request));
    }


    @Operation(summary = "Update account", description = "Updates account details (currency, status, etc.)")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Account updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @Parameter(description = "Account ID") @PathVariable Long id,
            @Valid @RequestBody UpdateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountService.updateAccount(id, request));
    }


    @Operation(summary = "Delete an account", description = "Deletes an account (only if status is CLOSED and balance is zero)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "400", description = "Account cannot be deleted (not closed or has balance)")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @Parameter(description = "Account ID") @PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Deposit money", description = "Deposits money into an account")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Deposit successful"),
            @ApiResponse(responseCode = "400", description = "Invalid amount or account status"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @Parameter(description = "Account ID") @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountService.deposit(id, request));
    }


    @Operation(summary = "Withdraw money", description = "Withdraws money from an account")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Withdrawal successful"),
            @ApiResponse(responseCode = "400", description = "Invalid amount, insufficient balance, or account not active"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @Parameter(description = "Account ID") @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountService.withdraw(id, request));
    }


    @Operation(summary = "Close account", description = "Closes an account (only if balance is zero)")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Account closed successfully"),
            @ApiResponse(responseCode = "400", description = "Account has non-zero balance"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("/{id}/close")
    public ResponseEntity<AccountResponse> closeAccount(
            @Parameter(description = "Account ID") @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(accountService.closeAccount(id));
    }
}