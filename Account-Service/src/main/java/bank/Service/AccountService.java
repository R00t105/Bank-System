package bank.Service;

import bank.Constants.AccountStatus;
import bank.Dto.AccountResponse;
import bank.Dto.CreateAccountRequest;
import bank.Dto.CustomerDto;
import bank.Dto.TransactionRequest;
import bank.Dto.UpdateAccountRequest;
import bank.Entity.Account;
import bank.Exception.AccountAlreadyExistsException;
import bank.Exception.AccountNotFoundException;
import bank.Exception.InsufficientBalanceException;
import bank.Exception.InvalidAccountStatusException;
import bank.Mapper.AccountMapper;
import bank.OutServices.CustomerOpenFeign;
import bank.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerOpenFeign customerOpenFeign;


    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }


    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return accountMapper.toAccountResponse(account);
    }


    public AccountResponse getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
        return accountMapper.toAccountResponse(account);
    }


    public List<AccountResponse> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }


    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {

        Boolean isCustomerExists = customerOpenFeign.existsCustomerById(request.getCustomerId());
        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new AccountAlreadyExistsException(request.getAccountNumber());
        }

        if (!isCustomerExists) {
            throw new IllegalArgumentException("Customer not found");
        }
        Account account = accountMapper.toAccount(request);
        account.setStatus(AccountStatus.PENDING);

        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(savedAccount);
    }


    @Transactional
    public AccountResponse updateAccount(Long id, UpdateAccountRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        Account updatedAccount = accountMapper.updateEntityFromDto(request, account);
        return accountMapper.toAccountResponse(updatedAccount);
    }


    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if (account.getStatus() != AccountStatus.CLOSED) {
            throw new InvalidAccountStatusException(
                    account.getAccountNumber(),
                    account.getStatus(),
                    "CLOSED"
            );
        }

        // Business rule: Only accounts with zero balance can be deleted
        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("Cannot delete account with non-zero balance");
        }

        accountRepository.delete(account);
    }


    @Transactional
    public AccountResponse deposit(Long id, TransactionRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if (account.getStatus() == AccountStatus.CLOSED || account.getStatus() == AccountStatus.FROZEN) {
            throw new InvalidAccountStatusException(
                    account.getAccountNumber(),
                    account.getStatus(),
                    "ACTIVE or PENDING"
            );
        }

        account.setBalance(account.getBalance().add(request.getAmount()));

        if (account.getStatus() == AccountStatus.PENDING) {
            account.setStatus(AccountStatus.ACTIVE);
        }

        return accountMapper.toAccountResponse(account);
    }


    @Transactional
    public AccountResponse withdraw(Long id, TransactionRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new InvalidAccountStatusException(
                    account.getAccountNumber(),
                    account.getStatus(),
                    "ACTIVE"
            );
        }

        if (!account.hasSufficientBalance(request.getAmount())) {
            throw new InsufficientBalanceException(
                    account.getAccountNumber(),
                    request.getAmount(),
                    account.getBalance()
            );
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));

        return accountMapper.toAccountResponse(account);
    }


    @Transactional
    public AccountResponse closeAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("Cannot close account with non-zero balance. Please withdraw all funds first.");
        }

        account.setStatus(AccountStatus.CLOSED);
        return accountMapper.toAccountResponse(account);
    }
}