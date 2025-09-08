package com.vocabcards.config;

import com.vocabcards.model.VocabularyWord;
import com.vocabcards.model.CEFRLevel;
import com.vocabcards.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private VocabularyService vocabularyService;
    
    @Override
    public void run(String... args) throws Exception {
        initializeVocabulary();
    }
    
    private void initializeVocabulary() {
        // Check if data already exists
        if (vocabularyService.getAllWords().isEmpty()) {
            // A1 Level Words
            createWord("Apple", "A round fruit with red or green skin", "🍎", CEFRLevel.A1, "Food", "/ˈæpəl/", "I eat an apple every day.");
            createWord("Cat", "A small domesticated carnivorous mammal", "🐱", CEFRLevel.A1, "Animals", "/kæt/", "The cat is sleeping on the sofa.");
            createWord("House", "A building where people live", "🏠", CEFRLevel.A1, "Places", "/haʊs/", "My house is near the school.");
            createWord("Book", "A set of printed pages bound together", "📚", CEFRLevel.A1, "Education", "/bʊk/", "I am reading a good book.");
            createWord("Car", "A road vehicle with four wheels", "🚗", CEFRLevel.A1, "Transport", "/kɑːr/", "My car is red.");
            createWord("Tree", "A woody perennial plant", "🌳", CEFRLevel.A1, "Nature", "/triː/", "The tree is very tall.");
            createWord("Water", "A colorless, transparent liquid", "💧", CEFRLevel.A1, "Nature", "/ˈwɔːtər/", "I drink water every day.");
            createWord("Sun", "The star around which Earth orbits", "☀️", CEFRLevel.A1, "Nature", "/sʌn/", "The sun is shining brightly.");
            
            // A2 Level Words
            createWord("Computer", "An electronic device for processing data", "💻", CEFRLevel.A2, "Technology", "/kəmˈpjuːtər/", "I use my computer for work.");
            createWord("Family", "A group of related people", "👨‍👩‍👧‍👦", CEFRLevel.A2, "People", "/ˈfæməli/", "My family is very important to me.");
            createWord("School", "An institution for education", "🏫", CEFRLevel.A2, "Education", "/skuːl/", "Children go to school to learn.");
            createWord("Friend", "A person you know and like", "👫", CEFRLevel.A2, "People", "/frend/", "She is my best friend.");
            createWord("Money", "A medium of exchange", "💰", CEFRLevel.A2, "Finance", "/ˈmʌni/", "I need money to buy groceries.");
            createWord("Time", "The indefinite continued progress of existence", "⏰", CEFRLevel.A2, "Abstract", "/taɪm/", "What time is it?");
            
            // B1 Level Words
            createWord("Environment", "The natural world around us", "🌍", CEFRLevel.B1, "Nature", "/ɪnˈvaɪrənmənt/", "We must protect the environment.");
            createWord("Technology", "The application of scientific knowledge", "⚙️", CEFRLevel.B1, "Technology", "/tekˈnɒlədʒi/", "Technology changes rapidly.");
            createWord("Communication", "The means of sending information", "📞", CEFRLevel.B1, "Communication", "/kəˌmjuːnɪˈkeɪʃən/", "Good communication is essential.");
            createWord("Education", "The process of learning", "🎓", CEFRLevel.B1, "Education", "/ˌedʒuˈkeɪʃən/", "Education opens many doors.");
            createWord("Culture", "The customs and traditions of a society", "🎭", CEFRLevel.B1, "Culture", "/ˈkʌltʃər/", "Every country has its own culture.");
            
            // B2 Level Words
            createWord("Innovation", "A new method or idea", "💡", CEFRLevel.B2, "Business", "/ˌɪnəˈveɪʃən/", "Innovation drives progress.");
            createWord("Sustainability", "The ability to maintain something", "♻️", CEFRLevel.B2, "Environment", "/səˌsteɪnəˈbɪləti/", "Sustainability is crucial for our future.");
            createWord("Democracy", "A system of government by the people", "🗳️", CEFRLevel.B2, "Politics", "/dɪˈmɒkrəsi/", "Democracy requires active participation.");
            createWord("Globalization", "The process of international integration", "🌐", CEFRLevel.B2, "Economics", "/ˌɡloʊbəlaɪˈzeɪʃən/", "Globalization affects all countries.");
            
            // C1 Level Words
            createWord("Paradigm", "A typical example or pattern", "🔄", CEFRLevel.C1, "Philosophy", "/ˈpærədaɪm/", "This represents a paradigm shift in thinking.");
            createWord("Eloquent", "Fluent and persuasive in speaking", "🎤", CEFRLevel.C1, "Communication", "/ˈeləkwənt/", "She gave an eloquent speech.");
            createWord("Resilience", "The ability to recover quickly", "💪", CEFRLevel.C1, "Psychology", "/rɪˈzɪliəns/", "Resilience helps us overcome challenges.");
            
            // C2 Level Words
            createWord("Quintessential", "Representing the most perfect example", "⭐", CEFRLevel.C2, "Abstract", "/ˌkwɪntɪˈsenʃəl/", "This is the quintessential example of modern art.");
            createWord("Ubiquitous", "Present everywhere", "🌎", CEFRLevel.C2, "Abstract", "/juːˈbɪkwɪtəs/", "Smartphones have become ubiquitous in modern society.");
            
            System.out.println("Sample vocabulary data initialized successfully!");
        }
    }
    
    private void createWord(String word, String meaning, String emoji, CEFRLevel level, 
                           String topic, String pronunciation, String example) {
        VocabularyWord vocabWord = new VocabularyWord(word, meaning, emoji, level, topic);
        vocabWord.setPronunciation(pronunciation);
        vocabWord.setExampleSentence(example);
        vocabularyService.saveWord(vocabWord);
    }
}