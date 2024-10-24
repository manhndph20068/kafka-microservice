package com.manhnd.accountservice.service;

import com.manhnd.accountservice.data.Account;
import com.manhnd.accountservice.event.EventProducer;
import com.manhnd.accountservice.model.AccountDTO;
import com.manhnd.accountservice.repository.IAccountRepository;
import com.manhnd.commonservice.common.CommonException;
import com.manhnd.commonservice.kafka.events.profile.ProfileCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAccountImpl implements IServiceAccountService{

    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public AccountDTO createAccount(ProfileCreatedEvent profileCreatedEvent) {
        try {
            Optional<Account> accountOp = iAccountRepository.findByEmail(profileCreatedEvent.getEmail());
            if (accountOp.isPresent()) {
                throw new CommonException("Email đã tồn tại");
            }
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setEmail(profileCreatedEvent.getEmail());
            accountDTO.setStatus("SUCCESS");
            accountDTO.setCurrency("VND");
            accountDTO.setBalance(profileCreatedEvent.getInitialBalance());
            accountDTO.setReserved(0.0);
            Account account = AccountDTO.dtoToEntity(accountDTO);
            Account accountSave = iAccountRepository.save(account);

            eventProducer.sendAccountCreatedEvent(accountDTO, profileCreatedEvent.getId(), "SUCCESS");
            return AccountDTO.entityToDTO(accountSave);
        } catch (Exception e) {
            eventProducer.sendAccountCreatedEvent(null, profileCreatedEvent.getId(), "FAIL");
            return null;
        }
    }

}
