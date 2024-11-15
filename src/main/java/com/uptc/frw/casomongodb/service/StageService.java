package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.*;
import com.uptc.frw.casomongodb.jpa.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StageService {
    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private EditionService editionService;

    public List<Stage> findAllStages() {
        return stageRepository.findAll();
    }

    public Stage findStageById(Long id) {
        return stageRepository.findById(id).orElse(null);
    }


    public Stage saveStage(Stage stage) {
        Stage stageOld = findStageById(stage.getIdStage());
        Map<String, Object> data = new HashMap<>();

        Edition edition = editionService.findEditionById(stage.getIdEdition());
        stage.setEditionS(edition);

        data.put("Edition", stage.getIdEdition());
        data.put("Consecutive", stage.getConsecutive());
        data.put("Origin", stage.getOrigin());
        data.put("Destination", stage.getDestination());
        data.put("Length", stage.getLongitude());
        data.put("Type", stage.getLongitude());

        if (stageOld == null) {
            auditService.registrarAuditoria("Stage", "INSERT", null, data, String.valueOf(stage.getIdStage()));
        }
        return stageRepository.save(stage);
    }

    public Stage updateStage(Stage stage) {
        // Buscar el Stage antiguo desde la base de datos
        Stage stageOld = findStageById(stage.getIdStage());

        // Crear mapas para los datos antiguos y nuevos
        Map<String, Object> oldData = new HashMap<>();
        oldData.put("Edition", stageOld.getIdEdition());
        oldData.put("Consecutive", stageOld.getConsecutive());
        oldData.put("Origin", stageOld.getOrigin());
        oldData.put("Destination", stageOld.getDestination());
        oldData.put("Length", stageOld.getLongitude());
        oldData.put("Type", stageOld.getType());

        Map<String, Object> newData = new HashMap<>();
        newData.put("Edition", stage.getIdEdition());
        newData.put("Consecutive", stage.getConsecutive());
        newData.put("Origin", stage.getOrigin());
        newData.put("Destination", stage.getDestination());
        newData.put("Length", stage.getLongitude());
        newData.put("Type", stage.getType());

        // Llamar al método de auditoría
        if (stage.getIdStage() == stageOld.getIdStage()) {
            auditService.registrarAuditoria("Stage", "UPDATE", oldData, newData, String.valueOf(stage.getIdStage()));
        }

        // Actualizar el Stage con los nuevos valores
        Edition edition = editionService.findEditionById(stageOld.getIdEdition());
        stageOld.setEditionS(edition);
        stageOld.setIdEdition(stage.getIdEdition());
        stageOld.setConsecutive(stage.getConsecutive());
        stageOld.setOrigin(stage.getOrigin());
        stageOld.setDestination(stage.getDestination());
        stageOld.setLongitude(stage.getLongitude());
        stageOld.setType(stage.getType());
        // Guardar el Stage actualizado
        return saveStage(stageOld);
    }

    public void deleteStage(long id) {
        Stage stage = findStageById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("Edition", stage.getIdEdition());
        data.put("Consecutive", stage.getConsecutive());
        data.put("Origin", stage.getOrigin());
        data.put("Destination", stage.getDestination());
        data.put("Length", stage.getLongitude());
        data.put("Type", stage.getLongitude());

        auditService.registrarAuditoria("Stage", "DELETE", data, null, String.valueOf(stage.getIdStage()));
        stageRepository.deleteById(id);

    }

}
