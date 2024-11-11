package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Edition;
import com.uptc.frw.casomongodb.jpa.models.EditionTeam;
import com.uptc.frw.casomongodb.jpa.models.Runner;
import com.uptc.frw.casomongodb.jpa.models.Team;
import com.uptc.frw.casomongodb.jpa.repository.EditionTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionTeamService {
    @Autowired
    private EditionTeamRepository editionTeamRepository;

    @Autowired
    private EditionService editionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private RunnerService runnerService;


    public List<EditionTeam> findAllEditionTeams() {
        return editionTeamRepository.findAll();
    }

    public EditionTeam findEditionTeamById(Long id) {
        return editionTeamRepository.findById(id).orElse(null);
    }
    public EditionTeam saveEditionTeam(EditionTeam editionTeam) {
        Edition edition = editionService.findEditionById(editionTeam.getIdEdition());
        editionTeam.setEdition(edition);
        Team team = teamService.findTeamById(editionTeam.getIdTeam());
        editionTeam.setTeam(team);
        Runner runner = runnerService.findRunnerById(editionTeam.getIdRunner());
        editionTeam.setRunner(runner);
        return editionTeamRepository.save(editionTeam);
    }

    public EditionTeam updateEditionTeam(EditionTeam editionTeam) {
        EditionTeam editionTeamOld = findEditionTeamById(editionTeam.getIdEditionTeam());
        Edition edition = editionService.findEditionById(editionTeam.getIdEdition());
        editionTeamOld.setEdition(edition);
        Team team = teamService.findTeamById(editionTeam.getIdTeam());
        editionTeamOld.setTeam(team);
        Runner runner = runnerService.findRunnerById(editionTeam.getIdRunner());
        editionTeamOld.setRunner(runner);
        return editionTeamRepository.save(editionTeamOld);
    }

    public void deleteEditionTeamById(Long id) {
        editionTeamRepository.deleteById(id);
    }

}
