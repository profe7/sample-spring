package com.testing.demo.repository;

import com.testing.demo.model.entity.Processor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ArkRepositoryTest {

    @Mock
    private ArkRepository arkRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Processor processor1 = new Processor();
        Processor processor2 = new Processor();

        processor1.setId(1); processor1.setManufacturer("Intel"); processor1.setFamily("Core i7"); processor1.setModel("10700K");
        processor2.setId(2); processor2.setManufacturer("AMD"); processor2.setFamily("Ryzen 5"); processor2.setModel("5600X");

        when(arkRepository.findAll()).thenReturn(Arrays.asList(processor1, processor2));
        when(arkRepository.findById(1)).thenReturn(Optional.of(processor1));
        when(arkRepository.findById(2)).thenReturn(Optional.of(processor2));
        when(arkRepository.findByManufacturer("Intel")).thenReturn(Optional.of(processor1));
        when(arkRepository.findByManufacturer("AMD")).thenReturn(Optional.of(processor2));
        when(arkRepository.findByFamily("Core i7")).thenReturn(Optional.of(processor1));
        when(arkRepository.findByFamily("Ryzen 5")).thenReturn(Optional.of(processor2));
    }

    @AfterEach
    public void tearDown() {
        arkRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        List<Processor> processors = arkRepository.findAll();
        assertEquals(2, processors.size());
        verify(arkRepository, times(1)).findAll();
        assertEquals("Intel", processors.get(0).getManufacturer());
        assertEquals("AMD", processors.get(1).getManufacturer());
        assertEquals("Core i7", processors.get(0).getFamily());
        assertEquals("Ryzen 5", processors.get(1).getFamily());
        assertEquals("10700K", processors.get(0).getModel());
        assertEquals("5600X", processors.get(1).getModel());
    }

    @Test
    void testFindById() {
        Optional<Processor> processor = arkRepository.findById(1);
        assertEquals("Intel", processor.get().getManufacturer());
        assertEquals("Core i7", processor.get().getFamily());
        assertEquals("10700K", processor.get().getModel());
        verify(arkRepository, times(1)).findById(1);
    }

    @Test
    void testFindByManufacturer() {
        Optional<Processor> processor = arkRepository.findByManufacturer("Intel");
        assertEquals("Intel", processor.get().getManufacturer());
        assertEquals("Core i7", processor.get().getFamily());
        assertEquals("10700K", processor.get().getModel());
        verify(arkRepository, times(1)).findByManufacturer("Intel");
    }

    @Test
    void testFindByFamily() {
        Optional<Processor> processor = arkRepository.findByFamily("Ryzen 5");
        assertEquals("AMD", processor.get().getManufacturer());
        assertEquals("Ryzen 5", processor.get().getFamily());
        assertEquals("5600X", processor.get().getModel());
        verify(arkRepository, times(1)).findByFamily("Ryzen 5");
    }
}
