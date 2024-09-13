package com.testing.demo.controller;

import com.testing.demo.model.dto.ArkRequest;
import com.testing.demo.model.dto.ArkResponse;
import com.testing.demo.model.entity.Processor;
import com.testing.demo.service.Ark.ArkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ark/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArkController {

    private final ArkService arkService;

    @GetMapping("allProcessors/")
    public ResponseEntity<List<ArkResponse>> retrieveAll() {
        return ResponseEntity.ok(arkService.retrieveAll());
    }

    @GetMapping("processorById/{id}")
    public ResponseEntity<ArkResponse> retrieveById(@PathVariable Integer id) {
        return ResponseEntity.ok(arkService.retrieveById(id).orElse(null));
    }

    @GetMapping("processorByManufacturer/{manuf}")
    public ResponseEntity<ArkResponse> retrieveByManufacturer(@PathVariable String manuf) {
        return ResponseEntity.ok(arkService.retrieveByManufacturer(manuf).orElse(null));
    }

    @GetMapping("processorByFamily/{family}")
    public ResponseEntity<ArkResponse> retrieveByFamily(@PathVariable String family) {
        return ResponseEntity.ok(arkService.retrieveByFamily(family).orElse(null));
    }

    @PostMapping("addProcessor/")
    public ResponseEntity<Processor> addEntry(@RequestBody ArkRequest request) {
        return ResponseEntity.ok(arkService.addEntry(request));
    }

    @PutMapping("editProcessor/{id}")
    public ResponseEntity<Processor> editEntry(@RequestBody ArkRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(arkService.editEntry(request, id));
    }

    @DeleteMapping("deleteProcessor/{id}")
    public ResponseEntity<Processor> deleteEntry(@PathVariable Integer id) {
        return ResponseEntity.ok(arkService.deleteEntry(id));
    }
}
