package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Podium;
import com.uptc.frw.casomongodb.jpa.models.Runner;
import com.uptc.frw.casomongodb.jpa.models.Sponsor;
import com.uptc.frw.casomongodb.jpa.repository.PodiumRepository;
import com.uptc.frw.casomongodb.jpa.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RunnerService {
    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private AuditService auditService;

    public List<Runner> findAllRunners() {
        return runnerRepository.findAll();
    }

    public Runner findRunnerById(long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    public Runner saveRunner(Runner runner) {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", runner.getNameRunner());
        data.put("Country", runner.getCountryRunner());
        data.put("birthDay", runner.getBirthdayRunner());
        auditService.registrarAuditoria("Sponsor", "UPDATE", null, data, String.valueOf(runner.getIdRunner()));

        return runnerRepository.save(runner);
    }

    public Runner updateRunner(Runner runner) {
        Runner runnerOld = findRunnerById(runner.getIdRunner());
        Map<String, Object> oldData = new HashMap<>();
        oldData.put("Name", runnerOld.getNameRunner());
        oldData.put("Country", runnerOld.getCountryRunner());
        oldData.put("birthDay", runnerOld.getBirthdayRunner());

        Map<String, Object> newData = new HashMap<>();
        oldData.put("Name", runner.getNameRunner());
        oldData.put("Country", runner.getCountryRunner());
        oldData.put("birthDay", runner.getBirthdayRunner());

        // Llamar al método de auditoría
        auditService.registrarAuditoria("Sponsor", "UPDATE", oldData, newData, String.valueOf(runner.getIdRunner()));


        runnerOld.setNameRunner(runner.getNameRunner());
        runnerOld.setCountryRunner(runner.getCountryRunner());
        runnerOld.setBirthdayRunner(runner.getBirthdayRunner());
        return runnerRepository.save(runnerOld);
    }
    public void deleteRunner(long id) {
        Runner runnerOld = findRunnerById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("Name", runnerOld.getNameRunner());
        data.put("Country", runnerOld.getCountryRunner());
        data.put("birthDay", runnerOld.getBirthdayRunner());
        auditService.registrarAuditoria("Sponsor", "DELETE", data, null, String.valueOf(runnerOld.getIdRunner()));
        runnerRepository.deleteById(id);
    }


    /*Lista que consulta los podiums de un corredor*/
    public List<Podium> findPodiumByRunner(long idRunner) {
        Runner runner = runnerRepository.findById(idRunner).orElseThrow(() -> new RuntimeException("Corredor no encontrado"));
        return runner.getPodiums();
    }

}
