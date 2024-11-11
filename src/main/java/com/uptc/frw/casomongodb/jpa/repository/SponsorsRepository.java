package com.uptc.frw.casomongodb.jpa.repository;

import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorsRepository extends JpaRepository<Sponsor, Long> {
}
