# VocabCards Backend

Spring Boot backend API for the VocabCards vocabulary learning application.

## Features

- **RESTful API** for vocabulary management
- **PostgreSQL database** integration with JPA/Hibernate
- **CEFR level categorization** (A1-C2)
- **Topic-based organization** of vocabulary
- **User progress tracking** with spaced repetition
- **Game session management** for different game types
- **JWT authentication** (ready to implement)
- **CORS support** for frontend integration

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **JWT** for authentication

## Database Schema

### Core Entities

1. **VocabularyWord** - Stores vocabulary entries with CEFR levels and topics
2. **User** - User accounts with progress tracking
3. **UserProgress** - Individual progress tracking per word per user
4. **GameSession** - Records of game sessions and scores

### CEFR Levels
- A1 (Beginner)
- A2 (Elementary) 
- B1 (Intermediate)
- B2 (Upper-Intermediate)
- C1 (Advanced)
- C2 (Proficiency)

### Game Types
- Image to Word Matching
- Word to Meaning Matching
- Quick Reflex Quiz
- Multiple Choice Quiz
- Spelling Challenge

## Setup Instructions

### Prerequisites
- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

### Database Setup
1. Install PostgreSQL
2. Create database:
```sql
CREATE DATABASE vocabcards;
CREATE USER vocabcards_user WITH PASSWORD 'vocabcards_password';
GRANT ALL PRIVILEGES ON DATABASE vocabcards TO vocabcards_user;
```

### Application Setup
1. Navigate to the backend directory:
```bash
cd backend
```

2. Update database configuration in `application.properties` if needed

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

## API Endpoints

### Vocabulary Endpoints
- `GET /api/vocabulary` - Get all vocabulary words
- `GET /api/vocabulary/{id}` - Get word by ID
- `GET /api/vocabulary/level/{level}` - Get words by CEFR level
- `GET /api/vocabulary/topic/{topic}` - Get words by topic
- `GET /api/vocabulary/topics` - Get all available topics
- `GET /api/vocabulary/random` - Get random words for games
- `POST /api/vocabulary` - Create new vocabulary word
- `PUT /api/vocabulary/{id}` - Update vocabulary word
- `DELETE /api/vocabulary/{id}` - Delete vocabulary word

### Query Parameters for Random Words
- `limit` - Number of words to return (default: 10)
- `level` - Filter by CEFR level
- `topic` - Filter by topic

### Example API Calls

```bash
# Get all A1 level words
curl http://localhost:8080/api/vocabulary/level/A1

# Get random words for Food topic
curl http://localhost:8080/api/vocabulary/random?topic=Food&limit=5

# Get all available topics
curl http://localhost:8080/api/vocabulary/topics
```

## Sample Data

The application includes a data initializer that populates the database with sample vocabulary covering:

- **A1 Level**: Basic words (Apple, Cat, House, Book, Car, Tree, Water, Sun)
- **A2 Level**: Everyday concepts (Computer, Family, School, Friend, Money, Time)
- **B1 Level**: More complex topics (Environment, Technology, Communication, Education, Culture)
- **B2 Level**: Advanced concepts (Innovation, Sustainability, Democracy, Globalization)
- **C1 Level**: Sophisticated vocabulary (Paradigm, Eloquent, Resilience)
- **C2 Level**: Expert-level words (Quintessential, Ubiquitous)

## Topics Covered

- Food
- Animals
- Places
- Education
- Transport
- Nature
- Technology
- People
- Finance
- Abstract concepts
- Business
- Environment
- Politics
- Economics
- Philosophy
- Communication
- Psychology
- Culture

## Future Enhancements

- [ ] User authentication with JWT
- [ ] Progress tracking API endpoints
- [ ] Game session recording
- [ ] Spaced repetition algorithm
- [ ] Image upload for vocabulary
- [ ] Audio pronunciation files
- [ ] Achievement system
- [ ] Social features (sharing progress)
- [ ] Admin dashboard
- [ ] Import/Export vocabulary sets

## Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
```

### Docker Support (Future)
```dockerfile
# Dockerfile example for future containerization
FROM openjdk:17-jre-slim
COPY target/vocab-backend-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```