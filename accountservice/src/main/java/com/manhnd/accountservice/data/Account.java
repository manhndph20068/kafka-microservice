package com.manhnd.accountservice.data;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;

    private String currency;

    private double balance;

    private double reserved;

    private String status;

    @Version
    private Long version;
}
