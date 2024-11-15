package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Podium;
import com.uptc.frw.casomongodb.jpa.models.Runner;
import com.uptc.frw.casomongodb.jpa.models.Stage;
import com.uptc.frw.casomongodb.jpa.repository.PodiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PodiumService {
    @Autowired
    private PodiumRepository podiumRepository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private StageService stageService;

    @Autowired
    private RunnerService runnerService;

    public List<Podium> findAllPodium() {
        return podiumRepository.findAll();
    }

    public Podium findPodiumById(long id) {
        return podiumRepository.findById(id).orElse(null);
    }

    public Podium savePodium (Podium podium) {
        Podium podiumOld = findPodiumById(podium.getIdPodium());
        Stage stage = stageService.findStageById(podium.getIdStage());
        podium.setStage(stage);
        Runner runner = runnerService.findRunnerById(podium.getIdRunner());
        podium.setRunner(runner);

        Map<String, Object> data = new HashMap<>();

        data.put("Podium", podium.getIdPodium());
        data.put("Stage", podium.getIdStage());
        data.put("Runner", podium.getIdRunner());
        data.put("PodiumPlace", podium.getPodiumPlace());
        data.put("Time", podium.getTime());

        if (podiumOld == null) {
            auditService.registrarAuditoria("Podium", "INSERT", null, data, String.valueOf(podium.getIdPodium()));
        }
        return podiumRepository.save(podium);
    }

    public Podium updatePodium(Podium podium) {
        Podium podiumOld = findPodiumById(podium.getIdPodium());
        Map<String, Object> oldData = new HashMap<>();
        oldData.put("Podium", podiumOld.getIdPodium());
        oldData.put("Stage", podiumOld.getIdStage());
        oldData.put("Runner", podiumOld.getIdRunner());
        oldData.put("PodiumPlace", podiumOld.getPodiumPlace());
        oldData.put("Time", podiumOld.getTime());

        Map<String, Object> newData = new HashMap<>();
        newData.put("Podium", podium.getIdPodium());
        newData.put("Stage", podium.getIdStage());
        newData.put("Runner", podium.getIdRunner());
        newData.put("PodiumPlace", podium.getPodiumPlace());
        newData.put("Time", podium.getTime());

        // Llamar al método de auditoría
        if (podium.getIdPodium() == podiumOld.getIdPodium()) {
            auditService.registrarAuditoria("Podium", "UPDATE", oldData, newData, String.valueOf(podium.getIdPodium()));
        }
        //Actualiza el Podium con los nuevos valores
        Stage stage = stageService.findStageById(podium.getIdStage());
        podiumOld.setStage(stage);
        Runner runner = runnerService.findRunnerById(podium.getIdRunner());
        podiumOld.setRunner(runner);
        podiumOld.setPodiumPlace(podium.getPodiumPlace());
        podiumOld.setTime(podium.getTime());
        return podiumRepository.save(podiumOld);
    }

    public void deletePodium(long id) {
        Podium podium = findPodiumById(id);
        Map<String, Object> data = new HashMap<>();

        data.put("Podium", podium.getIdPodium());
        data.put("Stage", podium.getIdStage());
        data.put("Runner", podium.getIdRunner());
        data.put("PodiumPlace", podium.getPodiumPlace());
        data.put("Time", podium.getTime());
        auditService.registrarAuditoria("Podium", "DELETE", data, null, String.valueOf(podium.getIdPodium()));
        podiumRepository.deleteById(id);
    }

}
