package com.manhnd.accountservice.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    private String id;

    private String email;

    private String currency;

    private double balance;

    private double reserved;

    @Version
    private Long version;

    public Account(String id, String email, String currency, double balance, double reserved) {
        this.id = id;
        this.email = email;
        this.currency = currency;
        this.balance = balance;
        this.reserved = reserved;
    }
}
