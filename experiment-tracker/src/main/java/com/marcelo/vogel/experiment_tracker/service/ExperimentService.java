package com.marcelo.vogel.experiment_tracker.service;

import com.marcelo.vogel.experiment_tracker.model.Experiment;
import com.marcelo.vogel.experiment_tracker.repository.ExperimentRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ExperimentService {

    private final ExperimentRepository repository;

    public ExperimentService(ExperimentRepository repository) {
        this.repository = repository;
    }

    @Async("experimentExecutor")
    public void runExperiment(UUID id) {
        System.out.println("Running in thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);

            Experiment experiment = repository.findById(id).orElseThrow();
            experiment.setStatus("COMPLETED");
            experiment.setFinishedAt(Instant.now());

            repository.save(experiment);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}