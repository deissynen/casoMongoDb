package com.uptc.frw.casomongodb.service;

import com.uptc.frw.casomongodb.jpa.models.Podium;
import com.uptc.frw.casomongodb.jpa.models.Runner;
import com.uptc.frw.casomongodb.jpa.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerService {
    @Autowired
    private RunnerRepository runnerRepository;

    public List<Runner> findAllRunners() {
        return runnerRepository.findAll();
    }

    public Runner findRunnerById(long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    public Runner saveRunner(Runner runner) {
        return runnerRepository.save(runner);
    }

    public Runner updateRunner(Runner runner) {
        Runner runnerOld = findRunnerById(runner.getIdRunner());
        runnerOld.setNameRunner(runner.getNameRunner());
        runnerOld.setCountryRunner(runner.getCountryRunner());
        runnerOld.setBirthdayRunner(runner.getBirthdayRunner());
        return runnerRepository.save(runnerOld);
    }
    public void deleteRunner(long id) {
        runnerRepository.deleteById(id);
    }


    /*Lista que consulta los podiums de un corredor*/
    public List<Podium> findPodiumByRunner(long idRunner) {
        Runner runner = runnerRepository.findById(idRunner).orElseThrow(() -> new RuntimeException("Corredor no encontrado"));
        return runner.getPodiums();
    }

}
