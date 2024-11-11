package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Team;
import com.uptc.frw.casomongodb.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.findAllTeams();
    }

    @GetMapping("{id}")
    public Team getTeamById(@PathVariable long id) {
        return teamService.findTeamById(id);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @PutMapping
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @DeleteMapping
    public void deleteTeam(@RequestParam long id) {
        teamService.deleteTeam(id);
    }


}
