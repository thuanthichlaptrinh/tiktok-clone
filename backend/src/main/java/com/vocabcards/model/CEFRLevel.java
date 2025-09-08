package com.vocabcards.model;

public enum CEFRLevel {
    A1("Beginner"),
    A2("Elementary"),
    B1("Intermediate"),
    B2("Upper-Intermediate"),
    C1("Advanced"),
    C2("Proficiency");
    
    private final String description;
    
    CEFRLevel(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}