package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.TeamSponsor;
import com.uptc.frw.casomongodb.service.TeamSponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teamSponsor")
public class TeamSponsorController {
    @Autowired
    private TeamSponsorService teamSponsorService;

    @GetMapping
    public List<TeamSponsor> getAllTeamSponsors() {
        return teamSponsorService.findAllTeamSponsors();
    }

    @GetMapping ("{id}")
    public TeamSponsor getTeamSponsorById(@PathVariable long id) {
        return teamSponsorService.findTeamSponsorById(id);
    }

    @PostMapping
    public TeamSponsor saveTeamSponsor(@RequestBody TeamSponsor teamSponsor) {
        return teamSponsorService.saveTeamSponsor(teamSponsor);
    }

    @PutMapping
    public TeamSponsor updateTeamSponsor(@RequestBody TeamSponsor teamSponsor) {
        return teamSponsorService.updateTeamSponsor(teamSponsor);
    }

    @DeleteMapping
    public void deleteTeamSponsor(@RequestParam long id) {
        teamSponsorService.deleteTeamSponsorById(id);
    }

}


