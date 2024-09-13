package com.testing.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArkRequest {
    private String manufacturer;

    private String family;

    private String model;

    private String socket;

    private String tdp;
}
