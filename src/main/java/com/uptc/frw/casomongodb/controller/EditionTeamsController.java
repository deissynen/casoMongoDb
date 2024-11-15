package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.EditionTeam;
import com.uptc.frw.casomongodb.service.EditionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("editionTeams")
public class EditionTeamsController {
    @Autowired
    private EditionTeamService editionTeamService;

    @GetMapping
    public List<EditionTeam> getAllEditionTeams() {
        return editionTeamService.findAllEditionTeams();
    }

    @GetMapping ("{id}")
    public EditionTeam getEditionTeamsByID(@PathVariable long id) {
        return editionTeamService.findEditionTeamById(id);
    }

    @PostMapping
    public EditionTeam saveEditionTeam(@RequestBody EditionTeam editionTeam) {
        return editionTeamService.saveEditionTeam(editionTeam);
    }

    @PutMapping
    public EditionTeam updateEditionTeam(@RequestBody EditionTeam editionTeam) {
        return editionTeamService.updateEditionTeam(editionTeam);
    }

    @DeleteMapping
    public void deleteEditionTeam(@RequestParam long id) {
        editionTeamService.deleteEditionTeamById(id);
    }

}
