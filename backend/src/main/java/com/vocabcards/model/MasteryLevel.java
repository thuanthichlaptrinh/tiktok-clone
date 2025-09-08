package com.vocabcards.model;

public enum MasteryLevel {
    NEW("New word - never studied"),
    FAMILIAR("Familiar - seen a few times"),
    LEARNING("Learning - practicing regularly"),
    MASTERED("Mastered - high accuracy rate");
    
    private final String description;
    
    MasteryLevel(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}