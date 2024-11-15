package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Podium;
import com.uptc.frw.casomongodb.service.PodiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("podium")
public class PodiumController {

    @Autowired
    private PodiumService podiumService;

    @GetMapping
    public List<Podium> getAllPodium() {
        return podiumService.findAllPodium();
    }

    @GetMapping ("{id}")
    public Podium getPodiumById(@PathVariable long id) {
        return podiumService.findPodiumById(id);
    }

    @PostMapping
    public Podium savePodium(@RequestBody Podium podium) {
        return podiumService.savePodium(podium);
    }

    @PutMapping
    public Podium updatePodium(@RequestBody Podium podium) {
        return podiumService.updatePodium(podium);
    }

    @DeleteMapping
    public void deletePodium(@RequestParam long id) {
        podiumService.deletePodium(id);
    }
}
