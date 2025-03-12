import { useEffect, useRef, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleXmark, faSpinner } from '@fortawesome/free-solid-svg-icons';
import HeadLessTippy from '@tippyjs/react/headless';
import AccountItem from '~/components/AccountItem';
import classNames from 'classnames/bind';
import styles from './Search.module.scss';

import * as searchServices from '~/apiServices/searchServices';
import { SearchIcon } from '~/components/Icons';
import { Wrapper as PopperWrapper } from '~/components/Popper';
import { useDebouce } from '~/hook';

const cx = classNames.bind(styles);

function Search() {
    const [searchValue, setSearchValue] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const [showResult, setShowResult] = useState(true);
    const [loading, setLoading] = useState(false);

    const debouced = useDebouce(searchValue, 500);

    const inputRef = useRef();

    useEffect(() => {
        if (!debouced.trim()) {
            setSearchResult([]);
            return;
        }

        const fetchApi = async () => {
            setLoading(true);

            const result = await searchServices.search(debouced, 'less');
            setSearchResult(result);

            setLoading(false);
        };

        fetchApi();
    }, [debouced]);

    const handleClear = () => {
        setSearchValue('');
        setSearchResult([]);
        inputRef.current.focus();
    };

    const handleHideResult = () => {
        setShowResult(false);
    };

    // Xử lý không cho người dùng nhập dấu cách khi bắt đầu
    const handleChange = (e) => {
        const search = e.target.value;

        // Nếu không bắt đầu bằng dấu cách
        if (!search.startsWith(' ')) {
            setSearchValue(search);
        }
    };

    return (
        <HeadLessTippy
            visible={showResult && searchResult.length > 0} // Có kết quả thì mới hiện tippy
            interactive // Nhấn được vào tippy
            render={(attrs) => (
                <div className={cx('search-result')} tabIndex={-1} {...attrs}>
                    <PopperWrapper>
                        <h4 className={cx('search-title')}>Accounts</h4>
                        {searchResult.map((result) => (
                            <AccountItem key={result.id} data={result} />
                        ))}
                    </PopperWrapper>
                </div>
            )}
            onClickOutside={handleHideResult}
        >
            <div className={cx('search')}>
                <input
                    ref={inputRef}
                    value={searchValue}
                    placeholder="Search accounts and videos"
                    spellCheck={false}
                    onChange={handleChange}
                    onFocus={() => setShowResult(true)} // khi focus thì bật tippy lên
                />

                {!!searchValue && !loading && (
                    <button className={cx('clear-btn')} onClick={handleClear}>
                        <FontAwesomeIcon icon={faCircleXmark} />
                    </button>
                )}

                {loading && <FontAwesomeIcon className={cx('loading-icon')} icon={faSpinner} />}

                <button className={cx('search-btn')} onMouseDown={(e) => e.preventDefault()}>
                    <SearchIcon />
                </button>
            </div>
        </HeadLessTippy>
    );
}

export default Search;

// onMouseDown={(e) => e.preventDefault()}: bỏ đi border khi nhấn vào btn search
