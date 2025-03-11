import { forwardRef, useState } from 'react';
import images from '~/assets/images';
import styles from './Image.module.scss';
import classNames from 'classnames';

// Đổi tên props fallback thành customFallback => nếu không có ảnh bên ngoài truyền vào
// thì nó sẽ lấy noImage còn có thì nó sẽ lấy ảnh được truyền
function Image({ src, alt, className, fallback: customFallback = images.noImage, ...props }, ref) {
    const [fallback, setFallback] = useState('');

    // Khi ảnh của src lỗi thì sẽ lấy ảnh noImage
    const handleError = () => {
        setFallback(customFallback);
    };

    return (
        <img
            // styles.wrapper: css những phần chung khi ảnh bị lỗi
            className={classNames(styles.wrapper, className)}
            ref={ref}
            src={fallback || src}
            alt={alt}
            {...props}
            onError={handleError}
        />
    );
}

export default forwardRef(Image);
