package com.vocabcards.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String username;
    
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    
    @NotBlank
    @Size(min = 6)
    private String password;
    
    private String firstName;
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    private CEFRLevel currentLevel;
    
    private int totalScore;
    private int wordsLearned;
    private int gamesPlayed;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserProgress> progressSet;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<GameSession> gameSessionSet;
    
    // Constructors
    public User() {}
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.currentLevel = CEFRLevel.A1;
        this.totalScore = 0;
        this.wordsLearned = 0;
        this.gamesPlayed = 0;
        this.createdAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (currentLevel == null) {
            currentLevel = CEFRLevel.A1;
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public CEFRLevel getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(CEFRLevel currentLevel) { this.currentLevel = currentLevel; }
    
    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    
    public int getWordsLearned() { return wordsLearned; }
    public void setWordsLearned(int wordsLearned) { this.wordsLearned = wordsLearned; }
    
    public int getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    
    public Set<UserProgress> getProgressSet() { return progressSet; }
    public void setProgressSet(Set<UserProgress> progressSet) { this.progressSet = progressSet; }
    
    public Set<GameSession> getGameSessionSet() { return gameSessionSet; }
    public void setGameSessionSet(Set<GameSession> gameSessionSet) { this.gameSessionSet = gameSessionSet; }
}