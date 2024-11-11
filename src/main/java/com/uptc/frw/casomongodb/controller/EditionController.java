package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Edition;
import com.uptc.frw.casomongodb.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edition")
public class EditionController {
    @Autowired
    private EditionService editionService;

    @GetMapping
    public List<Edition> getAllEditions() {
        return editionService.findAllEditions();
    }

    @GetMapping("{id}")
    public Edition getEditionById(@PathVariable long id) {
        return editionService.findEditionById(id);
    }

    @PostMapping
    public Edition saveEdition(@RequestBody Edition edition) {
        return editionService.saveEdition(edition);
    }

    @PutMapping
    public Edition updateEdition(@RequestBody Edition edition) {
        return editionService.updateEdition(edition);
    }

    @DeleteMapping
    public void deleteEdition(@RequestParam long id) {
        editionService.deleteEdition(id);
    }

}
