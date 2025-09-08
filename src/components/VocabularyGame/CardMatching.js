import { useState, useEffect } from 'react';
import classNames from 'classnames/bind';
import styles from './CardMatching.module.scss';

const cx = classNames.bind(styles);

// Sample vocabulary data
const vocabularyData = [
    {
        id: 1,
        word: 'Apple',
        meaning: 'A round fruit with red or green skin',
        image: '🍎',
        level: 'A1',
        topic: 'Food'
    },
    {
        id: 2,
        word: 'House',
        meaning: 'A building where people live',
        image: '🏠',
        level: 'A1',
        topic: 'Places'
    },
    {
        id: 3,
        word: 'Book',
        meaning: 'A set of printed pages bound together',
        image: '📚',
        level: 'A1',
        topic: 'Education'
    },
    {
        id: 4,
        word: 'Cat',
        meaning: 'A small domesticated carnivorous mammal',
        image: '🐱',
        level: 'A1',
        topic: 'Animals'
    },
    {
        id: 5,
        word: 'Car',
        meaning: 'A road vehicle with four wheels',
        image: '🚗',
        level: 'A1',
        topic: 'Transport'
    },
    {
        id: 6,
        word: 'Tree',
        meaning: 'A woody perennial plant',
        image: '🌳',
        level: 'A1',
        topic: 'Nature'
    }
];

function CardMatching({ gameType = 'image-word' }) {
    const [cards, setCards] = useState([]);
    const [selectedCards, setSelectedCards] = useState([]);
    const [matchedCards, setMatchedCards] = useState([]);
    const [score, setScore] = useState(0);
    const [gameComplete, setGameComplete] = useState(false);

    useEffect(() => {
        initializeGame();
    }, [gameType]);

    const initializeGame = () => {
        let gameCards = [];
        
        if (gameType === 'image-word') {
            // Create pairs of image and word cards
            vocabularyData.forEach(item => {
                gameCards.push({
                    id: `img-${item.id}`,
                    content: item.image,
                    type: 'image',
                    matchId: item.id,
                    word: item.word
                });
                gameCards.push({
                    id: `word-${item.id}`,
                    content: item.word,
                    type: 'word',
                    matchId: item.id,
                    word: item.word
                });
            });
        } else if (gameType === 'word-meaning') {
            // Create pairs of word and meaning cards
            vocabularyData.forEach(item => {
                gameCards.push({
                    id: `word-${item.id}`,
                    content: item.word,
                    type: 'word',
                    matchId: item.id,
                    word: item.word
                });
                gameCards.push({
                    id: `meaning-${item.id}`,
                    content: item.meaning,
                    type: 'meaning',
                    matchId: item.id,
                    word: item.word
                });
            });
        }

        // Shuffle cards
        gameCards = gameCards.sort(() => Math.random() - 0.5);
        setCards(gameCards);
        setSelectedCards([]);
        setMatchedCards([]);
        setScore(0);
        setGameComplete(false);
    };

    const handleCardClick = (cardId) => {
        if (selectedCards.length === 2 || matchedCards.includes(cardId)) {
            return;
        }

        const newSelected = [...selectedCards, cardId];
        setSelectedCards(newSelected);

        if (newSelected.length === 2) {
            const [firstCardId, secondCardId] = newSelected;
            const firstCard = cards.find(card => card.id === firstCardId);
            const secondCard = cards.find(card => card.id === secondCardId);

            if (firstCard.matchId === secondCard.matchId) {
                // Match found
                setMatchedCards(prev => [...prev, firstCardId, secondCardId]);
                setScore(prev => prev + 10);
                setSelectedCards([]);

                // Check if game is complete
                if (matchedCards.length + 2 === cards.length) {
                    setGameComplete(true);
                }
            } else {
                // No match, clear selection after delay
                setTimeout(() => {
                    setSelectedCards([]);
                }, 1000);
            }
        }
    };

    const resetGame = () => {
        initializeGame();
    };

    return (
        <div className={cx('card-matching')}>
            <div className={cx('game-header')}>
                <h2>Card Matching Game</h2>
                <div className={cx('game-info')}>
                    <span>Score: {score}</span>
                    <button onClick={resetGame} className={cx('reset-btn')}>
                        Reset Game
                    </button>
                </div>
            </div>

            {gameComplete && (
                <div className={cx('game-complete')}>
                    <h3>🎉 Congratulations!</h3>
                    <p>You completed the game with a score of {score}!</p>
                    <button onClick={resetGame} className={cx('play-again-btn')}>
                        Play Again
                    </button>
                </div>
            )}

            <div className={cx('cards-grid')}>
                {cards.map(card => (
                    <div
                        key={card.id}
                        className={cx('card', {
                            selected: selectedCards.includes(card.id),
                            matched: matchedCards.includes(card.id),
                            image: card.type === 'image'
                        })}
                        onClick={() => handleCardClick(card.id)}
                    >
                        {card.type === 'image' ? (
                            <span className={cx('emoji-image')}>{card.content}</span>
                        ) : (
                            <span className={cx('card-text')}>{card.content}</span>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default CardMatching;