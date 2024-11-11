package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.*;
import com.uptc.frw.casomongodb.jpa.repository.TeamSponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSponsorService {
    @Autowired
    private TeamSponsorRepository teamSponsorRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private EditionService editionService;

    public List<TeamSponsor> findAllTeamSponsors() {
        return teamSponsorRepository.findAll();
    }

    public TeamSponsor findTeamSponsorById(Long id) {
        return teamSponsorRepository.findById(id).orElse(null);
    }

    public TeamSponsor saveTeamSponsor(TeamSponsor teamSponsor) {
        Team team = teamService.findTeamById(teamSponsor.getIdTeam());
        teamSponsor.setTeam(team);
        Sponsor sponsor = sponsorService.findSponsorById(teamSponsor.getIdSponsor());
        teamSponsor.setSponsor(sponsor);
        Edition edition = editionService.findEditionById(teamSponsor.getIdEdition());
        teamSponsor.setEdition(edition);
        return teamSponsorRepository.save(teamSponsor);
    }
    public TeamSponsor updateTeamSponsor(TeamSponsor teamSponsor) {
        TeamSponsor teamSponsorOld = findTeamSponsorById(teamSponsor.getIdTeamSponsor());
        Team team = teamService.findTeamById(teamSponsor.getIdTeam());
        teamSponsorOld.setTeam(team);
        Sponsor sponsor = sponsorService.findSponsorById(teamSponsor.getIdSponsor());
        teamSponsorOld.setSponsor(sponsor);
        Edition edition = editionService.findEditionById(teamSponsor.getIdEdition());
        teamSponsorOld.setEdition(edition);
        return teamSponsorRepository.save(teamSponsorOld);
    }

    public void deleteTeamSponsorById(Long id) {
        teamSponsorRepository.deleteById(id);
    }
}
