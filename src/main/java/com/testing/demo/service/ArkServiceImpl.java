package com.testing.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.testing.demo.repository.ArkRepository;
import com.testing.demo.model.Processor;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.testing.demo.dto.ArkRequest;
import com.testing.demo.dto.ArkResponse;

@Service
@RequiredArgsConstructor
public class ArkServiceImpl  implements ArkService {

    private final ArkRepository arkRepository;

    @Override
    public List<ArkResponse> retrieveAll() {
        List<Processor> allProcessors = arkRepository.findAll();
        List<ArkResponse> response = new ArrayList<>();
        for (Processor processor : allProcessors) {
            response.add(ArkResponse.builder()
                .manufacturer(processor.getManufacturer())
                .family(processor.getFamily())
                .model(processor.getModel())
                .socket(processor.getSocket())
                .tdp(processor.getTdp())
                .build());
        }
        return response;

    }

    @Override
    public Optional<ArkResponse> retrieveById(Integer id) {
        Optional<Processor> processor = arkRepository.findById(id);
        return processor.map(value -> ArkResponse.builder()
                .manufacturer(value.getManufacturer())
                .family(value.getFamily())
                .model(value.getModel())
                .socket(value.getSocket())
                .tdp(value.getTdp())
                .build());
    }

    @Override
    public Optional<ArkResponse> retrieveByManufacturer(String manufacturer) {
        Optional<Processor> processor = arkRepository.findByManufacturer(manufacturer);
        return processor.map(value -> ArkResponse.builder()
                .manufacturer(value.getManufacturer())
                .family(value.getFamily())
                .model(value.getModel())
                .socket(value.getSocket())
                .tdp(value.getTdp())
                .build());
    }

    @Override
    public Optional<ArkResponse> retrieveByFamily(String family) {
        Optional<Processor> processor = arkRepository.findByFamily(family);
        return processor.map(value -> ArkResponse.builder()
                .manufacturer(value.getManufacturer())
                .family(value.getFamily())
                .model(value.getModel())
                .socket(value.getSocket())
                .tdp(value.getTdp())
                .build());
    }

    @Override
    public Processor addEntry(ArkRequest request) {
        Processor processor = Processor.builder()
                .manufacturer(request.getManufacturer())
                .family(request.getFamily())
                .model(request.getModel())
                .socket(request.getSocket())
                .tdp(request.getTdp())
                .build();
        return arkRepository.save(processor);
    }
}
