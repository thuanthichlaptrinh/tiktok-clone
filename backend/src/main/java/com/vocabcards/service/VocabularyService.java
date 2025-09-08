package com.vocabcards.service;

import com.vocabcards.model.VocabularyWord;
import com.vocabcards.model.CEFRLevel;
import com.vocabcards.repository.VocabularyWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyService {
    
    @Autowired
    private VocabularyWordRepository vocabularyWordRepository;
    
    public List<VocabularyWord> getAllWords() {
        return vocabularyWordRepository.findAll();
    }
    
    public Optional<VocabularyWord> getWordById(Long id) {
        return vocabularyWordRepository.findById(id);
    }
    
    public Optional<VocabularyWord> getWordByWord(String word) {
        return vocabularyWordRepository.findByWord(word);
    }
    
    public List<VocabularyWord> getWordsByLevel(CEFRLevel level) {
        return vocabularyWordRepository.findByLevel(level);
    }
    
    public List<VocabularyWord> getWordsByTopic(String topic) {
        return vocabularyWordRepository.findByTopic(topic);
    }
    
    public List<VocabularyWord> getWordsByLevelAndTopic(CEFRLevel level, String topic) {
        return vocabularyWordRepository.findByLevelAndTopic(level, topic);
    }
    
    public List<String> getAllTopics() {
        return vocabularyWordRepository.findAllTopics();
    }
    
    public List<VocabularyWord> getRandomWords(int limit) {
        return vocabularyWordRepository.findRandomWords(limit);
    }
    
    public List<VocabularyWord> getRandomWordsByLevel(CEFRLevel level, int limit) {
        return vocabularyWordRepository.findRandomByLevel(level, limit);
    }
    
    public List<VocabularyWord> getRandomWordsByTopic(String topic, int limit) {
        return vocabularyWordRepository.findRandomByTopic(topic, limit);
    }
    
    public VocabularyWord saveWord(VocabularyWord word) {
        return vocabularyWordRepository.save(word);
    }
    
    public void deleteWord(Long id) {
        vocabularyWordRepository.deleteById(id);
    }
}