package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Edition;
import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import com.uptc.frw.casomongodb.jpa.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditionService {
    @Autowired
    private EditionRepository editionRepository;

    @Autowired
    private AuditService auditService;

    public List<Edition> findAllEditions() {
        return editionRepository.findAll();
    }

    public Edition findEditionById(Long id) {
        return editionRepository.findById(id).orElse(null);
    }

    public Edition saveEdition(Edition edition) {
        Map<String, Object> data = new HashMap<>();
        data.put("Year", edition.getYearEdition());
        data.put("Start Date", edition.getDateStartEdition());
        data.put("End Date", edition.getDateEndEdition());
        auditService.registrarAuditoria("Edition", "INSERT", null, data, String.valueOf(edition.getIdEdition()));
        return editionRepository.save(edition);
    }

    public Edition updateEdition(Edition edition) {

        Edition editionOld = findEditionById(edition.getIdEdition());

        // Crear mapas para los datos antiguos y nuevos
        Map<String, Object> oldData = new HashMap<>();
        oldData.put("Year", editionOld.getYearEdition());
        oldData.put("Start Date", editionOld.getDateStartEdition());
        oldData.put("End Date", editionOld.getDateEndEdition());


        Map<String, Object> newData = new HashMap<>();
        newData.put("Year", edition.getYearEdition());
        newData.put("Start Date", edition.getDateStartEdition());
        newData.put("End Date", edition.getDateEndEdition());

        // Llamar al método de auditoría
        auditService.registrarAuditoria("Edition", "UPDATE", oldData, newData, String.valueOf(edition.getIdEdition()));

        editionOld.setYearEdition(edition.getYearEdition());
        editionOld.setDateEndEdition(edition.getDateEndEdition());
        editionOld.setDateStartEdition(edition.getDateStartEdition());

        return editionRepository.save(editionOld);
    }

    public void deleteEdition(Long id) {

        Edition editionOld = findEditionById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("Year", editionOld.getYearEdition());
        data.put("Start Date", editionOld.getDateStartEdition());
        data.put("End Date", editionOld.getDateEndEdition());
        auditService.registrarAuditoria("Edition", "DELETE", data, null, String.valueOf(editionOld.getIdEdition()));
        editionRepository.deleteById(id);
    }
}
