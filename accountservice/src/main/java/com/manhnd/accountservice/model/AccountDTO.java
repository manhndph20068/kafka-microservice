package com.manhnd.accountservice.model;


import com.manhnd.accountservice.data.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
    private String id;
    private String email;
    private String currency;
    private Double balance;
    private Double reserved;
    private String status;

    public static Account dtoToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .email(accountDTO.getEmail())
                .currency(accountDTO.getCurrency())
                .balance(accountDTO.getBalance())
                .reserved(accountDTO.getReserved())
                .status(accountDTO.getStatus())
                .build();
    }

    public static AccountDTO entityToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .reserved(account.getReserved())
                .status(account.getStatus())
                .build();
    }
}
