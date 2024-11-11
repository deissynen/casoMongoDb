package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import com.uptc.frw.casomongodb.jpa.models.TeamSponsor;
import com.uptc.frw.casomongodb.jpa.repository.SponsorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorService {
    @Autowired
    private SponsorsRepository sponsorsRepository;

    public List<Sponsor> findAllSponsors() {
        return sponsorsRepository.findAll();
    }

    public Sponsor findSponsorById(long id) {
        return sponsorsRepository.findById(id).orElse(null);
    }

    public Sponsor saveSponsor(Sponsor sponsor) {
        return sponsorsRepository.save(sponsor);
    }

    public Sponsor updateSponsor(Sponsor sponsor) {
        Sponsor sponsorOld = findSponsorById(sponsor.getIdSponsor());
        sponsorOld.setNameSponsor(sponsor.getNameSponsor());
        sponsorOld.setNitSponsor(sponsor.getNitSponsor());
        return saveSponsor(sponsorOld);
    }
    public void deleteSponsor(long id) {
        sponsorsRepository.deleteById(id);
    }

    /*Se visualiza la lista de los patrocinadores*/
    public List<TeamSponsor> findAllTeamSponsors(long idSponsor) {
        Sponsor sponsor = sponsorsRepository.findById(idSponsor).orElse(null);
        return sponsor.getTeamSponsors();
    }

}
