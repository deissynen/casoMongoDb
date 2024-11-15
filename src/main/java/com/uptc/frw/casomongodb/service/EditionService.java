package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Edition;
import com.uptc.frw.casomongodb.jpa.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {
    @Autowired
    private EditionRepository editionRepository;

    public List<Edition> findAllEditions() {
        return editionRepository.findAll();
    }

    public Edition findEditionById(Long id) {
        return editionRepository.findById(id).orElse(null);
    }

    public Edition saveEdition(Edition edition) {
         return editionRepository.save(edition);
    }

    public Edition updateEdition(Edition edition) {
        Edition editionOld = findEditionById(edition.getIdEdition());
        editionOld.setYearEdition(edition.getYearEdition());
        editionOld.setDateEndEdition(edition.getDateEndEdition());
        editionOld.setDateStartEdition(edition.getDateStartEdition());
        return editionRepository.save(editionOld);
    }

    public void deleteEdition(Long id) {
        editionRepository.deleteById(id);
    }
}
