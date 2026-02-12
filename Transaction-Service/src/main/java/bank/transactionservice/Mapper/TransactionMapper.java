package bank.transactionservice.Mapper;

import bank.transactionservice.DTOs.TransactionRequest;
import bank.transactionservice.Entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface TransactionMapper {

    Transaction toEntity(TransactionRequest transactionRequest);

}
