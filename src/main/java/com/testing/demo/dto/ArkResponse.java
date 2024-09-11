package com.testing.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArkResponse {
    private String manufacturer;

    private String family;

    private String model;

    private String socket;

    private String tdp;
}