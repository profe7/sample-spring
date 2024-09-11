package com.testing.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.testing.demo.model.Processor;
import com.testing.demo.dto.ArkRequest;
import com.testing.demo.dto.ArkResponse;

@Service
public interface ArkService {
    List<ArkResponse> retrieveAll();

    Optional<ArkResponse> retrieveById(Integer id);

    Optional<ArkResponse> retrieveByManufacturer(String manufacturer);

    Optional<ArkResponse> retrieveByFamily(String family);

    Processor addEntry(ArkRequest request);
}
