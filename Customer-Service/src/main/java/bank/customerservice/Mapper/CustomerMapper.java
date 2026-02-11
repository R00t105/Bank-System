package bank.customerservice.Mapper;

import bank.customerservice.Dto.AddCustomerRequest;
import bank.customerservice.Dto.CustomerAddedResponse;
import bank.customerservice.Dto.UpdateRequest;
import bank.customerservice.Entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    Customer toCustomer(AddCustomerRequest addCustomerRequest);
    CustomerAddedResponse toCustomerAddedResponse(Customer customer);
    Customer updateEntityFromDto(UpdateRequest updateRequest, @MappingTarget Customer customer);

}