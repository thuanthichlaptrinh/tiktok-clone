package com.vocabcards.model;

public enum GameType {
    IMAGE_WORD_MATCHING("Image to Word Matching"),
    WORD_MEANING_MATCHING("Word to Meaning Matching"),
    QUICK_QUIZ("Quick Reflex Quiz"),
    MULTIPLE_CHOICE("Multiple Choice Quiz"),
    SPELLING_CHALLENGE("Spelling Challenge");
    
    private final String displayName;
    
    GameType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}