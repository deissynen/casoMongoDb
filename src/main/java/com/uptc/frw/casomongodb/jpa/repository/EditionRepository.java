package com.uptc.frw.casomongodb.jpa.repository;

import com.uptc.frw.casomongodb.jpa.models.Edition;
import com.uptc.frw.casomongodb.jpa.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditionRepository extends JpaRepository<Edition, Long> {
    //List<Edition> findByTeamIdTeam(long idTeam);
}
