import PropTypes from 'prop-types';
import classNames from 'classnames/bind';
import Tippy from '@tippyjs/react/headless';
import { Wrapper as PopperWrapper } from '~/components/Popper';
import styles from './Menu.module.scss';
import MenuItem from './MenuItem';
import HeaderMenu from './HeaderMenu';
import { useState } from 'react';

const cx = classNames.bind(styles);

// Tạo 1 function trống dùng để gán cho onChange, Khi onChange ko được truyền vào thì sẽ không bị lỗi
const defautlFn = () => {};

function Menu({ children, items = [], hideOnClick = false, onChange = defautlFn, ...passProps }) {
    const [history, setHistory] = useState([{ data: items }]);
    const current = history[history.length - 1];

    const renderItems = () => {
        return current.data.map((item, index) => {
            const isParent = !!item.children;

            return (
                <MenuItem
                    key={index}
                    data={item}
                    onClick={() => {
                        if (isParent) {
                            setHistory((prev) => [...prev, item.children]);
                        } else {
                            onChange(item);
                        }
                    }}
                />
            );
        });
    };

    const hanleBack = () => {
        // Xóa phần tử cuối mảng -> là menu vừa nhấn vào
        setHistory((prev) => prev.slice(0, prev.length - 1));
    };

    const renderResult = (attrs) => (
        <div className={cx('menu-list')} tabIndex="-1" {...attrs}>
            <PopperWrapper className={cx('menu-popper')}>
                {history.length > 1 && (
                    <HeaderMenu
                        title={current.title}
                        onBack={hanleBack} // Xóa phần tử cuối mảng -> là menu vừa nhấn vào
                    />
                )}
                <div className={cx('menu-body')}>{renderItems()}</div>
            </PopperWrapper>
        </div>
    );

    // Reset to first page
    const handleResetMenu = () => {
        setHistory((prev) => prev.slice(0, 1));
    };

    return (
        <Tippy
            {...passProps}
            interactive
            delay={[0, 700]} // hiện ngay và 700ms mới ẩn
            offset={[12, 8]} // chỉnh sửa tippy sang phải
            hideOnClick={hideOnClick} // Khi hover vào tippy và nhấn ra bên ngoài sẽ ko tắt
            placement="bottom-end"
            render={renderResult}
            onHide={handleResetMenu} // đi về page đầu tiên
        >
            {children}
        </Tippy>
    );
}

Menu.propTypes = {
    children: PropTypes.isRequired,
    items: PropTypes.array,
    hideOnClick: PropTypes.bool,
    onChange: PropTypes.func,
};

export default Menu;
