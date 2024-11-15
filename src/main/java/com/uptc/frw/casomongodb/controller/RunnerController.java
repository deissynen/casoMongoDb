package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Runner;
import com.uptc.frw.casomongodb.service.RunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("runner")
public class RunnerController {
    @Autowired
    private RunnerService runnerService;

    @GetMapping
    public List<Runner> getAllRunners() {
        return runnerService.findAllRunners();
    }

    @GetMapping ("{id}")
    public Runner getRunnerById(@PathVariable long id) {
        return runnerService.findRunnerById(id);
    }

    @PostMapping
    public Runner saveRunner(@RequestBody Runner runner) {
        return runnerService.saveRunner(runner);
    }

    @PutMapping
    public Runner updateRunner(@RequestBody Runner runner) {
        return runnerService.updateRunner(runner);
    }

    @DeleteMapping
    public void deleteRunner(@RequestParam long id) {
        runnerService.deleteRunner(id);
    }





}
