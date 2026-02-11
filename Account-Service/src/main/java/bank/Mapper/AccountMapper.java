package bank.Mapper;

import bank.Dto.AccountResponse;
import bank.Dto.CreateAccountRequest;
import bank.Dto.UpdateAccountRequest;
import bank.Entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {


    @Mapping(source = "initialBalance", target = "balance")
    Account toAccount(CreateAccountRequest createAccountRequest);

    AccountResponse toAccountResponse(Account account);

    Account updateEntityFromDto(UpdateAccountRequest updateRequest, @MappingTarget Account account);
}