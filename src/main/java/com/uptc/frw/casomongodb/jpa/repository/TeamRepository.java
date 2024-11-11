package com.uptc.frw.casomongodb.jpa.repository;

import com.uptc.frw.casomongodb.jpa.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByTeamSponsorsIdTeam(long idSponsor);
}
