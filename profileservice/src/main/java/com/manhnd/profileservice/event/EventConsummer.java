package com.manhnd.profileservice.event;

import com.manhnd.profileservice.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsummer {

    @Autowired
    private IProfileService iProfileService;
}
