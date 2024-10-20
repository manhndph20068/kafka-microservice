package com.manhnd.accountservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private String email;

    private String name;

    private String status;

    private String role;

    private double initalBalance;
}
