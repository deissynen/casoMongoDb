package com.uptc.frw.casomongodb.controller;

import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import com.uptc.frw.casomongodb.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sponsor")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;

    @GetMapping
    public List<Sponsor> getAllSponsors() {
        return sponsorService.findAllSponsors();
    }

    @GetMapping ("{id}")
    public Sponsor getSponsorById(@PathVariable long id) {
        return sponsorService.findSponsorById(id);
    }
    @PostMapping
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.saveSponsor(sponsor);
    }

    @PutMapping
    public Sponsor updateSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.updateSponsor(sponsor);
    }

    @DeleteMapping
    public void deleteSponsor(@RequestParam long id) {
        sponsorService.deleteSponsor(id);
    }

}
