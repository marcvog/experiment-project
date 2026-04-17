package com.marcelo.vogel.experiment_tracker.dto;

import jakarta.validation.constraints.NotBlank;

public class ExperimentCreateDTO {

    @NotBlank
    private String name;

    private String parameters;

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}