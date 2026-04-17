package com.marcelo.vogel.experiment_tracker.repository;

import com.marcelo.vogel.experiment_tracker.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExperimentRepository extends JpaRepository<Experiment, UUID> {
}