package bank.transactionservice.Service;

import bank.transactionservice.DTOs.TransactionRequest;
import bank.transactionservice.Entity.Transaction;
import bank.transactionservice.Exception.TransactionNotFoundException;
import bank.transactionservice.Mapper.TransactionMapper;
import bank.transactionservice.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Transactional
    public void saveTransaction(TransactionRequest transactionRequest) {
        transactionRepository.save(transactionMapper.toEntity(transactionRequest));
    }


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    public Transaction getTransactionById(Long id) throws TransactionNotFoundException {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

}