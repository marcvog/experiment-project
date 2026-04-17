package com.marcelo.vogel.experiment_tracker.controller;
import com.marcelo.vogel.experiment_tracker.dto.ExperimentCreateDTO;
import com.marcelo.vogel.experiment_tracker.dto.ExperimentResponseDTO;
import com.marcelo.vogel.experiment_tracker.service.ExperimentService;
import com.marcelo.vogel.experiment_tracker.model.Experiment;
import com.marcelo.vogel.experiment_tracker.repository.ExperimentRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/experiments")
public class ExperimentController {

    private final ExperimentRepository repository;
    private final ExperimentService service;

    public ExperimentController(ExperimentRepository repository, ExperimentService service) {
        this.repository = repository;
        this.service = service;
    }

    // Create experiment
    @PostMapping
    public ExperimentResponseDTO create(@RequestBody @Valid ExperimentCreateDTO dto) {

        Experiment experiment = new Experiment();
        experiment.setName(dto.getName());
        experiment.setParameters(dto.getParameters());
        experiment.setStatus("CREATED");
        experiment.setCreatedAt(Instant.now());

        Experiment saved = repository.save(experiment);

        return toDTO(saved);
    }

    private ExperimentResponseDTO toDTO(Experiment experiment) {
        ExperimentResponseDTO dto = new ExperimentResponseDTO();

        dto.setId(experiment.getId());
        dto.setName(experiment.getName());
        dto.setStatus(experiment.getStatus());
        dto.setCreatedAt(experiment.getCreatedAt());
        dto.setStartedAt(experiment.getStartedAt());
        dto.setFinishedAt(experiment.getFinishedAt());

        return dto;
    }

    @PostMapping("/{id}/start")
    public Experiment startExperiment(@PathVariable UUID id) {
        Experiment experiment = repository.findById(id)
                .orElseThrow();

        experiment.setStatus("RUNNING");
        experiment.setStartedAt(Instant.now());
        repository.save(experiment);

        // async execution
        service.runExperiment(id);

        return experiment;
    }

    // Get all experiments
    @GetMapping
    public List<ExperimentResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ExperimentResponseDTO getById(@PathVariable UUID id) {
        Experiment experiment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return toDTO(experiment);
    }
}