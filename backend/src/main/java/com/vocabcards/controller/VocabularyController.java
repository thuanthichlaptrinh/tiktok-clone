package com.vocabcards.controller;

import com.vocabcards.model.VocabularyWord;
import com.vocabcards.model.CEFRLevel;
import com.vocabcards.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocabulary")
@CrossOrigin(origins = "http://localhost:3000")
public class VocabularyController {
    
    @Autowired
    private VocabularyService vocabularyService;
    
    @GetMapping
    public ResponseEntity<List<VocabularyWord>> getAllWords() {
        return ResponseEntity.ok(vocabularyService.getAllWords());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VocabularyWord> getWordById(@PathVariable Long id) {
        return vocabularyService.getWordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/level/{level}")
    public ResponseEntity<List<VocabularyWord>> getWordsByLevel(@PathVariable CEFRLevel level) {
        return ResponseEntity.ok(vocabularyService.getWordsByLevel(level));
    }
    
    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<VocabularyWord>> getWordsByTopic(@PathVariable String topic) {
        return ResponseEntity.ok(vocabularyService.getWordsByTopic(topic));
    }
    
    @GetMapping("/topics")
    public ResponseEntity<List<String>> getAllTopics() {
        return ResponseEntity.ok(vocabularyService.getAllTopics());
    }
    
    @GetMapping("/random")
    public ResponseEntity<List<VocabularyWord>> getRandomWords(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) CEFRLevel level,
            @RequestParam(required = false) String topic) {
        
        if (level != null) {
            return ResponseEntity.ok(vocabularyService.getRandomWordsByLevel(level, limit));
        } else if (topic != null) {
            return ResponseEntity.ok(vocabularyService.getRandomWordsByTopic(topic, limit));
        } else {
            return ResponseEntity.ok(vocabularyService.getRandomWords(limit));
        }
    }
    
    @PostMapping
    public ResponseEntity<VocabularyWord> createWord(@RequestBody VocabularyWord word) {
        return ResponseEntity.ok(vocabularyService.saveWord(word));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VocabularyWord> updateWord(
            @PathVariable Long id, 
            @RequestBody VocabularyWord word) {
        word.setId(id);
        return ResponseEntity.ok(vocabularyService.saveWord(word));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWord(@PathVariable Long id) {
        vocabularyService.deleteWord(id);
        return ResponseEntity.ok().build();
    }
}