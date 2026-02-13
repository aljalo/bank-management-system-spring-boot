package org.wscict.bank.mapper;

import org.mapstruct.Mapper;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.dto.CreateAccountRequest;
import org.wscict.bank.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(CreateAccountRequest request);

    AccountResponse toResponse(Account account);
}
