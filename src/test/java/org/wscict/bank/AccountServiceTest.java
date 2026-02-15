package org.wscict.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wscict.bank.dto.AccountResponse;
import org.wscict.bank.exception.ResourceNotFoundException;
import org.wscict.bank.model.Account;
import org.wscict.bank.repository.AccountRepository;
import org.wscict.bank.service.impl.AccountServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void shouldReturnAccountCount() {

        // Arrange
        when(accountRepository.count()).thenReturn(3L);

        // Act
        long result = accountService.countAccounts();

        // Assert
        assertThat(result).isEqualTo(3L);
        verify(accountRepository).count();
    }
    @Test
    void shouldReturnAccountWhenFound() {

        // ======================
        // Arrange
        // ======================

        Long id = 1L;

        Account account = new Account();
        account.setOwnerName("Ali");
        account.setBalance(500.0);

        when(accountRepository.findById(id))
                .thenReturn(java.util.Optional.of(account));

        // ======================
        // Act
        // ======================

        AccountResponse result = accountService.getAccountById(id);

        // ======================
        // Assert
        // ======================

        assertThat(result).isNotNull();
        assertThat(result.getOwnerName()).isEqualTo("Ali");

        verify(accountRepository).findById(id);
    }
    @Test
    void shouldThrowExceptionWhenAccountNotFound() {

        // ======================
        // Arrange
        // ======================

        Long id = 99L;

        when(accountRepository.findById(id))
                .thenReturn(java.util.Optional.empty());

        // ======================
        // Act + Assert
        // ======================

        assertThatThrownBy(() ->
                accountService.getAccountById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Account not found with id: " + id);

        verify(accountRepository).findById(id);
    }

}

