package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import com.uptc.frw.casomongodb.jpa.models.TeamSponsor;
import com.uptc.frw.casomongodb.jpa.repository.SponsorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SponsorService {
    @Autowired
    private SponsorsRepository sponsorsRepository;

    @Autowired
    private AuditService auditService;
    public List<Sponsor> findAllSponsors() {
        return sponsorsRepository.findAll();
    }

    public Sponsor findSponsorById(long id) {
        return sponsorsRepository.findById(id).orElse(null);
    }

    public Sponsor saveSponsor(Sponsor sponsor) {
        Map<String, Object> data = new HashMap<>();
        data.put("nameSponsor", sponsor.getNameSponsor());
        data.put("nitSponsor", sponsor.getNitSponsor());
        auditService.registrarAuditoria("Sponsor", "INSERT", null, data, String.valueOf(sponsor.getIdSponsor()));
        return sponsorsRepository.save(sponsor);

    }

    public Sponsor updateSponsor(Sponsor sponsor) {
        // Buscar el Sponsor antiguo desde la base de datos
        Sponsor sponsorOld = findSponsorById(sponsor.getIdSponsor());

        // Crear mapas para los datos antiguos y nuevos
        Map<String, Object> oldData = new HashMap<>();
        oldData.put("nameSponsor", sponsorOld.getNameSponsor());
        oldData.put("nitSponsor", sponsorOld.getNitSponsor());

        Map<String, Object> newData = new HashMap<>();
        newData.put("nameSponsor", sponsor.getNameSponsor());
        newData.put("nitSponsor", sponsor.getNitSponsor());

        // Llamar al método de auditoría
        auditService.registrarAuditoria("Sponsor", "UPDATE", oldData, newData, String.valueOf(sponsor.getIdSponsor()));

        // Actualizar el Sponsor con los nuevos valores
        sponsorOld.setNameSponsor(sponsor.getNameSponsor());
        sponsorOld.setNitSponsor(sponsor.getNitSponsor());

        // Guardar el Sponsor actualizado
        return saveSponsor(sponsorOld);
    }
    public void deleteSponsor(long id) {
        Sponsor sponsorOld = findSponsorById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("nameSponsor", sponsorOld.getNameSponsor());
        data.put("nitSponsor", sponsorOld.getNitSponsor());
        auditService.registrarAuditoria("Sponsor", "DELETE", data, null, String.valueOf(sponsorOld.getIdSponsor()));
        sponsorsRepository.deleteById(id);

    }

    /*Se visualiza la lista de los patrocinadores*/
    public List<TeamSponsor> findAllTeamSponsors(long idSponsor) {
        Sponsor sponsor = sponsorsRepository.findById(idSponsor).orElse(null);
        return sponsor.getTeamSponsors();
    }

}
