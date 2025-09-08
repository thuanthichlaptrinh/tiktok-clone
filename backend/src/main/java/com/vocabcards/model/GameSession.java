package com.vocabcards.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    
    private int score;
    private int totalQuestions;
    private int correctAnswers;
    private double accuracyRate;
    private long durationSeconds;
    
    @Column(name = "started_at")
    private LocalDateTime startedAt;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    // Constructors
    public GameSession() {}
    
    public GameSession(User user, GameType gameType) {
        this.user = user;
        this.gameType = gameType;
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.accuracyRate = 0.0;
        this.startedAt = LocalDateTime.now();
    }
    
    public void completeSession() {
        this.completedAt = LocalDateTime.now();
        if (startedAt != null) {
            this.durationSeconds = java.time.Duration.between(startedAt, completedAt).getSeconds();
        }
        if (totalQuestions > 0) {
            this.accuracyRate = (double) correctAnswers / totalQuestions;
        }
    }
    
    public void addQuestionResult(boolean isCorrect, int points) {
        this.totalQuestions++;
        if (isCorrect) {
            this.correctAnswers++;
            this.score += points;
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public GameType getGameType() { return gameType; }
    public void setGameType(GameType gameType) { this.gameType = gameType; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    
    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    
    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }
    
    public double getAccuracyRate() { return accuracyRate; }
    public void setAccuracyRate(double accuracyRate) { this.accuracyRate = accuracyRate; }
    
    public long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(long durationSeconds) { this.durationSeconds = durationSeconds; }
    
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}