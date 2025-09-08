package com.vocabcards.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_progress")
public class UserProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "vocabulary_word_id", nullable = false)
    private VocabularyWord vocabularyWord;
    
    private int correctAnswers;
    private int totalAttempts;
    private double accuracyRate;
    
    @Enumerated(EnumType.STRING)
    private MasteryLevel masteryLevel;
    
    @Column(name = "first_learned_at")
    private LocalDateTime firstLearnedAt;
    
    @Column(name = "last_reviewed_at")
    private LocalDateTime lastReviewedAt;
    
    @Column(name = "next_review_at")
    private LocalDateTime nextReviewAt;
    
    // Constructors
    public UserProgress() {}
    
    public UserProgress(User user, VocabularyWord vocabularyWord) {
        this.user = user;
        this.vocabularyWord = vocabularyWord;
        this.correctAnswers = 0;
        this.totalAttempts = 0;
        this.accuracyRate = 0.0;
        this.masteryLevel = MasteryLevel.NEW;
        this.firstLearnedAt = LocalDateTime.now();
        this.lastReviewedAt = LocalDateTime.now();
        this.nextReviewAt = LocalDateTime.now().plusDays(1);
    }
    
    public void updateProgress(boolean isCorrect) {
        this.totalAttempts++;
        if (isCorrect) {
            this.correctAnswers++;
        }
        this.accuracyRate = (double) this.correctAnswers / this.totalAttempts;
        this.lastReviewedAt = LocalDateTime.now();
        
        // Update mastery level based on accuracy and attempts
        updateMasteryLevel();
        
        // Calculate next review date based on mastery level
        calculateNextReview();
    }
    
    private void updateMasteryLevel() {
        if (totalAttempts >= 10 && accuracyRate >= 0.9) {
            masteryLevel = MasteryLevel.MASTERED;
        } else if (totalAttempts >= 5 && accuracyRate >= 0.7) {
            masteryLevel = MasteryLevel.LEARNING;
        } else if (totalAttempts >= 2) {
            masteryLevel = MasteryLevel.FAMILIAR;
        }
    }
    
    private void calculateNextReview() {
        long daysToAdd = switch (masteryLevel) {
            case NEW -> 1;
            case FAMILIAR -> 3;
            case LEARNING -> 7;
            case MASTERED -> 30;
        };
        this.nextReviewAt = LocalDateTime.now().plusDays(daysToAdd);
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public VocabularyWord getVocabularyWord() { return vocabularyWord; }
    public void setVocabularyWord(VocabularyWord vocabularyWord) { this.vocabularyWord = vocabularyWord; }
    
    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }
    
    public int getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }
    
    public double getAccuracyRate() { return accuracyRate; }
    public void setAccuracyRate(double accuracyRate) { this.accuracyRate = accuracyRate; }
    
    public MasteryLevel getMasteryLevel() { return masteryLevel; }
    public void setMasteryLevel(MasteryLevel masteryLevel) { this.masteryLevel = masteryLevel; }
    
    public LocalDateTime getFirstLearnedAt() { return firstLearnedAt; }
    public void setFirstLearnedAt(LocalDateTime firstLearnedAt) { this.firstLearnedAt = firstLearnedAt; }
    
    public LocalDateTime getLastReviewedAt() { return lastReviewedAt; }
    public void setLastReviewedAt(LocalDateTime lastReviewedAt) { this.lastReviewedAt = lastReviewedAt; }
    
    public LocalDateTime getNextReviewAt() { return nextReviewAt; }
    public void setNextReviewAt(LocalDateTime nextReviewAt) { this.nextReviewAt = nextReviewAt; }
}