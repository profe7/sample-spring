package com.testing.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import com.testing.demo.model.dto.ArkRequest;
import com.testing.demo.model.dto.ArkResponse;
import com.testing.demo.model.dto.ArkResponse;
import com.testing.demo.model.entity.Processor;
import com.testing.demo.repository.ArkRepository;

import com.testing.demo.service.Ark.ArkService;
import com.testing.demo.service.Ark.ArkServiceImpl;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;

@ExtendWith(MockitoExtension.class)
class ArkServiceTest {

    @Mock
    private ArkRepository arkRepository;

    @InjectMocks
    private ArkServiceImpl arkService;

    @AfterEach
    public void tearDown() {
        arkRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        Processor processor1 = new Processor();
        Processor processor2 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");
        processor2.setId(2); processor2.setManufacturer("AMD"); processor2.setFamily("Ryzen 5"); processor2.setModel("5600X");

        when(arkRepository.findAll()).thenReturn(Arrays.asList(processor1, processor2));
        List<ArkResponse> processors = arkService.retrieveAll();
        assertEquals(2, processors.size());
        assertEquals("Intel", processors.get(0).getManufacturer());
        assertEquals("AMD", processors.get(1).getManufacturer());
        assertEquals("Core i7", processors.get(0).getFamily());
        assertEquals("Ryzen 5", processors.get(1).getFamily());
        assertEquals("10700K", processors.get(0).getModel());
        assertEquals("5600X", processors.get(1).getModel());
    }

    @Test
    void testFindById() {
        Processor processor1 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");

        when(arkRepository.findById(1)).thenReturn(Optional.of(processor1));
        Optional<ArkResponse> processor = arkService.retrieveById(1);
        assertTrue(processor.isPresent());
        assertEquals("Intel", processor.get().getManufacturer());
        assertEquals("Core i7", processor.get().getFamily());
        assertEquals("10700K", processor.get().getModel());
    }

    @Test
    void testFindByManufacturer() {
        Processor processor1 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");

        when(arkRepository.findByManufacturer("Intel")).thenReturn(Optional.of(processor1));
        Optional<ArkResponse> processor = arkService.retrieveByManufacturer("Intel");
        assertTrue(processor.isPresent());
        assertEquals("Intel", processor.get().getManufacturer());
        assertEquals("Core i7", processor.get().getFamily());
        assertEquals("10700K", processor.get().getModel());
    }

    @Test
    void testFindByFamily() {
        Processor processor1 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");

        when(arkRepository.findByFamily("Core i7")).thenReturn(Optional.of(processor1));
        Optional<ArkResponse> processor = arkService.retrieveByFamily("Core i7");
        assertTrue(processor.isPresent());
        assertEquals("Intel", processor.get().getManufacturer());
        assertEquals("Core i7", processor.get().getFamily());
        assertEquals("10700K", processor.get().getModel());
    }

    @Test
    void testAddEntry() {
        Processor processor1 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");

        when(arkRepository.save(any(Processor.class))).thenReturn(processor1);
        Processor processor = arkService.addEntry(new ArkRequest().builder()
                .manufacturer("Intel")
                .family("Core i7")
                .model("10700K")
                .socket("LGA1200")
                .tdp("65W")
                .build());
        assertEquals("Intel", processor.getManufacturer());
        assertEquals("Core i7", processor.getFamily());
        assertEquals("10700K", processor.getModel());
    }

    @Test
    void testDeleteEntry() {
        Processor processor = new Processor();
        processor.setId(1);
        processor.setManufacturer("Intel");
        processor.setFamily("Core i7");
        processor.setModel("10700K");

        when(arkRepository.findById(1)).thenReturn(Optional.of(processor));
        Processor deletedProcessor = arkService.deleteEntry(1);
        verify(arkRepository, times(1)).deleteById(1);
        assertNotNull(deletedProcessor);
        assertEquals(processor, deletedProcessor);
    }
}
