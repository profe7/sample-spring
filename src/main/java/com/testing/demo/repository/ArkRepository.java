package com.testing.demo.repository;

import com.testing.demo.model.entity.Processor;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArkRepository extends JpaRepository<Processor, Integer> {

    @NonNull
    List<Processor> findAll();

    Optional<Processor> findById(Integer id);

    Optional<Processor> findByManufacturer(String manufacturer);

    Optional<Processor> findByFamily(String family);
}
