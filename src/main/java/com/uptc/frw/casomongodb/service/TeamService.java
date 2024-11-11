package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Team;
import com.uptc.frw.casomongodb.jpa.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team findTeamById(long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    public Team updateTeam(Team team) {
        Team teamOld = findTeamById(team.getIdTeam());
        teamOld.setNameTeam(team.getNameTeam());
        teamOld.setCreationDateTeam(team.getCreationDateTeam());
        return teamRepository.save(teamOld);
    }
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }

    /*Se visualiza la lista de los  equipos*/
    public List<Team> findAllTeamSponsors(long idSponsor) {
        return teamRepository.findByTeamSponsorsIdTeam(idSponsor);
    }

}
