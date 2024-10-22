package com.manhnd.commonservice.kafka.events.profile;

import com.manhnd.commonservice.kafka.events.BaseEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileCreatedEvent extends BaseEvent {

    private Long id;
    private String email;
    private String name;
    private String role;
    private String status;
    private Double initialBalance;

    public ProfileCreatedEvent() {
        setEventType("PROFILE_CREATED");
    }

    @Override
    public String toString() {
        return "ProfileCreatedEvent{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", initialBalance=" + initialBalance +

                '}';
    }
}
