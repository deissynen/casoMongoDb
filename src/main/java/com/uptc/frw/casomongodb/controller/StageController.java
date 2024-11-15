package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Stage;
import com.uptc.frw.casomongodb.jpa.repository.StageRepository;
import com.uptc.frw.casomongodb.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageService.findAllStages();
    }

    @GetMapping ("{id}")
    public Stage getStageById(@PathVariable long id) {
        return stageService.findStageById(id);
    }

    @PostMapping
    public Stage saveStage(@RequestBody Stage stage) {
        return stageService.saveStage(stage);
    }

    @PutMapping
    public Stage updateStage(@RequestBody Stage stage) {
        return stageService.updateStage(stage);
    }

    @DeleteMapping
    public void deleteStage(@RequestParam long id) {
        stageService.deleteStage(id);
    }

}
