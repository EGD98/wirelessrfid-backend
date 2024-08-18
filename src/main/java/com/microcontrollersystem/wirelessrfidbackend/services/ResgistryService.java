package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.RfidRequest;
import com.microcontrollersystem.wirelessrfidbackend.repositories.RfidRequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ResgistryService {
    private final RfidRequestRepository rfidRequestRepository;

    public List<RfidRequest> getRegistryList() {
        return rfidRequestRepository.findAllByStatusTrue();
    }
}
