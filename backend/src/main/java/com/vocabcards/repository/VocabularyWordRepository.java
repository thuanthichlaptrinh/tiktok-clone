package com.vocabcards.repository;

import com.vocabcards.model.VocabularyWord;
import com.vocabcards.model.CEFRLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyWordRepository extends JpaRepository<VocabularyWord, Long> {
    
    Optional<VocabularyWord> findByWord(String word);
    
    List<VocabularyWord> findByLevel(CEFRLevel level);
    
    List<VocabularyWord> findByTopic(String topic);
    
    List<VocabularyWord> findByLevelAndTopic(CEFRLevel level, String topic);
    
    @Query("SELECT DISTINCT v.topic FROM VocabularyWord v ORDER BY v.topic")
    List<String> findAllTopics();
    
    @Query("SELECT v FROM VocabularyWord v WHERE v.level = :level ORDER BY RANDOM() LIMIT :limit")
    List<VocabularyWord> findRandomByLevel(@Param("level") CEFRLevel level, @Param("limit") int limit);
    
    @Query("SELECT v FROM VocabularyWord v WHERE v.topic = :topic ORDER BY RANDOM() LIMIT :limit")
    List<VocabularyWord> findRandomByTopic(@Param("topic") String topic, @Param("limit") int limit);
    
    @Query("SELECT v FROM VocabularyWord v ORDER BY RANDOM() LIMIT :limit")
    List<VocabularyWord> findRandomWords(@Param("limit") int limit);
}