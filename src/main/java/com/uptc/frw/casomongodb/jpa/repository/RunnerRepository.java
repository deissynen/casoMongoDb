package com.uptc.frw.casomongodb.jpa.repository;

import com.uptc.frw.casomongodb.jpa.models.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
