package com.manhnd.accountservice.service;

import com.manhnd.accountservice.model.AccountDTO;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;

public interface IServiceAccountService {

    AccountDTO createAccount(ProfileCreatedEvent profileCreatedEvent);
}
