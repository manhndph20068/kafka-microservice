package com.manhnd.commonservice.kafka.events.account;

import com.manhnd.commonservice.kafka.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreatedEvent extends BaseEvent {
    private Long idProfile;
    private String email;

    private String status;

    public AccountCreatedEvent() {
        setEventType("ACCOUNT_CREATED");
    }

    @Override
    public String toString() {
        return "AccountCreatedEvent{" +
                "idProfile=" + idProfile +
                ", email='" + email + '\'' +
                '}';
    }

}
