import { useState } from 'react';
import CardMatching from '~/components/VocabularyGame/CardMatching';
import QuickQuiz from '~/components/VocabularyGame/QuickQuiz';
import classNames from 'classnames/bind';
import styles from './Games.module.scss';

const cx = classNames.bind(styles);

function Games() {
    const [activeGame, setActiveGame] = useState(null);

    const gameOptions = [
        {
            id: 'image-word',
            title: '🖼️ Image to Word Matching',
            description: 'Match images with their corresponding words',
            component: <CardMatching gameType="image-word" />
        },
        {
            id: 'word-meaning',
            title: '📝 Word to Meaning Matching',
            description: 'Match words with their meanings',
            component: <CardMatching gameType="word-meaning" />
        },
        {
            id: 'quick-quiz',
            title: '⚡ Quick Reflex Quiz',
            description: 'Test your vocabulary with rapid-fire questions',
            component: <QuickQuiz />
        }
    ];

    if (activeGame) {
        const game = gameOptions.find(g => g.id === activeGame);
        return (
            <div className={cx('games-container')}>
                <div className={cx('game-header')}>
                    <button 
                        onClick={() => setActiveGame(null)} 
                        className={cx('back-btn')}
                    >
                        ← Back to Games
                    </button>
                    <h2>{game.title}</h2>
                </div>
                {game.component}
            </div>
        );
    }

    return (
        <div className={cx('games-container')}>
            <div className={cx('games-header')}>
                <h1>Vocabulary Games</h1>
                <p>Practice with fun card matching games and quizzes</p>
            </div>

            <div className={cx('games-grid')}>
                {gameOptions.map(game => (
                    <div 
                        key={game.id} 
                        className={cx('game-card')}
                        onClick={() => setActiveGame(game.id)}
                    >
                        <h3>{game.title}</h3>
                        <p>{game.description}</p>
                        <button className={cx('play-btn')}>
                            Play Now
                        </button>
                    </div>
                ))}
            </div>

            <div className={cx('game-features')}>
                <h3>Game Features</h3>
                <div className={cx('features-list')}>
                    <div className={cx('feature')}>
                        <span className={cx('feature-icon')}>🎯</span>
                        <span>CEFR Level-based difficulty</span>
                    </div>
                    <div className={cx('feature')}>
                        <span className={cx('feature-icon')}>📊</span>
                        <span>Progress tracking</span>
                    </div>
                    <div className={cx('feature')}>
                        <span className={cx('feature-icon')}>🏆</span>
                        <span>Achievement system</span>
                    </div>
                    <div className={cx('feature')}>
                        <span className={cx('feature-icon')}>⚡</span>
                        <span>Quick reflex training</span>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Games;