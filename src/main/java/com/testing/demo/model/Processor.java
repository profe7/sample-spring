package com.testing.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String manufacturer;

    private String family;

    private String model;

    private String socket;

    private String tdp;
}
