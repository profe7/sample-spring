package com.testing.demo.service.Ark;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.testing.demo.model.entity.Processor;
import com.testing.demo.model.dto.ArkRequest;
import com.testing.demo.model.dto.ArkResponse;

@Service
public interface ArkService {
    List<ArkResponse> retrieveAll();

    Optional<ArkResponse> retrieveById(Integer id);

    Optional<ArkResponse> retrieveByManufacturer(String manufacturer);

    Optional<ArkResponse> retrieveByFamily(String family);

    Processor addEntry(ArkRequest request);

    Processor editEntry(ArkRequest request, int Id);

    Processor deleteEntry(int Id);
}
