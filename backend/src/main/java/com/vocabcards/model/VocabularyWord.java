package com.vocabcards.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vocabulary_words")
public class VocabularyWord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(unique = true)
    private String word;
    
    @NotBlank
    private String meaning;
    
    private String pronunciation;
    
    private String imageUrl;
    
    private String emoji;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private CEFRLevel level;
    
    @NotBlank
    private String topic;
    
    private String exampleSentence;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "vocabularyWord", cascade = CascadeType.ALL)
    private Set<UserProgress> userProgressSet;
    
    // Constructors
    public VocabularyWord() {}
    
    public VocabularyWord(String word, String meaning, String emoji, CEFRLevel level, String topic) {
        this.word = word;
        this.meaning = meaning;
        this.emoji = emoji;
        this.level = level;
        this.topic = topic;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    
    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }
    
    public String getPronunciation() { return pronunciation; }
    public void setPronunciation(String pronunciation) { this.pronunciation = pronunciation; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    
    public CEFRLevel getLevel() { return level; }
    public void setLevel(CEFRLevel level) { this.level = level; }
    
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    
    public String getExampleSentence() { return exampleSentence; }
    public void setExampleSentence(String exampleSentence) { this.exampleSentence = exampleSentence; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Set<UserProgress> getUserProgressSet() { return userProgressSet; }
    public void setUserProgressSet(Set<UserProgress> userProgressSet) { this.userProgressSet = userProgressSet; }
}