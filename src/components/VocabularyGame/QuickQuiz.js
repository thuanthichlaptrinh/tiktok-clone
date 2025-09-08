import { useState, useEffect } from 'react';
import classNames from 'classnames/bind';
import styles from './QuickQuiz.module.scss';

const cx = classNames.bind(styles);

// Sample vocabulary data for quiz
const quizData = [
    {
        id: 1,
        word: 'Apple',
        options: ['A round fruit with red or green skin', 'A type of tree', 'A computer brand', 'A color'],
        correct: 0,
        image: '🍎'
    },
    {
        id: 2,
        word: 'House',
        options: ['A type of animal', 'A building where people live', 'A kind of food', 'A musical instrument'],
        correct: 1,
        image: '🏠'
    },
    {
        id: 3,
        word: 'Book',
        options: ['A type of food', 'A piece of clothing', 'A set of printed pages bound together', 'A musical note'],
        correct: 2,
        image: '📚'
    },
    {
        id: 4,
        word: 'Cat',
        options: ['A type of car', 'A kind of hat', 'A musical instrument', 'A small domesticated carnivorous mammal'],
        correct: 3,
        image: '🐱'
    },
    {
        id: 5,
        word: 'Car',
        options: ['A road vehicle with four wheels', 'A type of animal', 'A piece of furniture', 'A musical instrument'],
        correct: 0,
        image: '🚗'
    },
    {
        id: 6,
        word: 'Tree',
        options: ['A type of food', 'A building material', 'A woody perennial plant', 'A piece of clothing'],
        correct: 2,
        image: '🌳'
    }
];

function QuickQuiz() {
    const [currentQuestion, setCurrentQuestion] = useState(0);
    const [selectedAnswer, setSelectedAnswer] = useState(null);
    const [score, setScore] = useState(0);
    const [gameComplete, setGameComplete] = useState(false);
    const [showResult, setShowResult] = useState(false);
    const [timeLeft, setTimeLeft] = useState(10);
    const [gameStarted, setGameStarted] = useState(false);

    useEffect(() => {
        if (gameStarted && timeLeft > 0 && !showResult) {
            const timer = setTimeout(() => setTimeLeft(timeLeft - 1), 1000);
            return () => clearTimeout(timer);
        } else if (timeLeft === 0 && !showResult) {
            // Time's up, move to next question
            handleAnswerSubmit();
        }
    }, [timeLeft, showResult, gameStarted]);

    const startGame = () => {
        setGameStarted(true);
        setCurrentQuestion(0);
        setScore(0);
        setGameComplete(false);
        setSelectedAnswer(null);
        setShowResult(false);
        setTimeLeft(10);
    };

    const handleAnswerSelect = (answerIndex) => {
        if (!showResult) {
            setSelectedAnswer(answerIndex);
        }
    };

    const handleAnswerSubmit = () => {
        const isCorrect = selectedAnswer === quizData[currentQuestion].correct;
        
        if (isCorrect) {
            setScore(prev => prev + 10);
        }

        setShowResult(true);

        setTimeout(() => {
            if (currentQuestion + 1 < quizData.length) {
                setCurrentQuestion(prev => prev + 1);
                setSelectedAnswer(null);
                setShowResult(false);
                setTimeLeft(10);
            } else {
                setGameComplete(true);
            }
        }, 2000);
    };

    const resetGame = () => {
        setGameStarted(false);
        setCurrentQuestion(0);
        setSelectedAnswer(null);
        setScore(0);
        setGameComplete(false);
        setShowResult(false);
        setTimeLeft(10);
    };

    if (!gameStarted) {
        return (
            <div className={cx('quick-quiz')}>
                <div className={cx('start-screen')}>
                    <h2>⚡ Quick Reflex Quiz</h2>
                    <p>Test your vocabulary knowledge with rapid-fire questions!</p>
                    <div className={cx('rules')}>
                        <h4>Rules:</h4>
                        <ul>
                            <li>🕐 10 seconds per question</li>
                            <li>🎯 Choose the correct meaning</li>
                            <li>⚡ Quick reflexes earn more points</li>
                        </ul>
                    </div>
                    <button onClick={startGame} className={cx('start-btn')}>
                        Start Quiz
                    </button>
                </div>
            </div>
        );
    }

    if (gameComplete) {
        const totalQuestions = quizData.length;
        const percentage = (score / (totalQuestions * 10)) * 100;

        return (
            <div className={cx('quick-quiz')}>
                <div className={cx('results-screen')}>
                    <h2>🎉 Quiz Complete!</h2>
                    <div className={cx('score-display')}>
                        <div className={cx('score-circle')}>
                            <span className={cx('score-text')}>{score}</span>
                            <span className={cx('score-label')}>points</span>
                        </div>
                        <p>You got {Math.round(percentage)}% correct!</p>
                    </div>
                    <div className={cx('action-buttons')}>
                        <button onClick={startGame} className={cx('play-again-btn')}>
                            Play Again
                        </button>
                        <button onClick={resetGame} className={cx('back-btn')}>
                            Back to Menu
                        </button>
                    </div>
                </div>
            </div>
        );
    }

    const question = quizData[currentQuestion];

    return (
        <div className={cx('quick-quiz')}>
            <div className={cx('quiz-header')}>
                <div className={cx('progress')}>
                    Question {currentQuestion + 1} of {quizData.length}
                </div>
                <div className={cx('timer', { urgent: timeLeft <= 3 })}>
                    ⏱️ {timeLeft}s
                </div>
                <div className={cx('score')}>
                    Score: {score}
                </div>
            </div>

            <div className={cx('question-container')}>
                <div className={cx('question-image')}>
                    <span className={cx('emoji-display')}>{question.image}</span>
                </div>
                
                <div className={cx('question-content')}>
                    <h3>What does "{question.word}" mean?</h3>
                    
                    <div className={cx('options')}>
                        {question.options.map((option, index) => (
                            <button
                                key={index}
                                className={cx('option', {
                                    selected: selectedAnswer === index,
                                    correct: showResult && index === question.correct,
                                    incorrect: showResult && selectedAnswer === index && index !== question.correct
                                })}
                                onClick={() => handleAnswerSelect(index)}
                                disabled={showResult}
                            >
                                {option}
                            </button>
                        ))}
                    </div>

                    {selectedAnswer !== null && !showResult && (
                        <button onClick={handleAnswerSubmit} className={cx('submit-btn')}>
                            Submit Answer
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
}

export default QuickQuiz;