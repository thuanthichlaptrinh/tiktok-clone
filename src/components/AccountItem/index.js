import classNames from 'classnames/bind';
import styles from './AccountItem.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';

const cx = classNames.bind(styles);

function AccountItem() {
    return (
        <div className={cx('wrapper')}>
            <img
                className={cx('avatar')}
                src="https://scontent.fsgn2-8.fna.fbcdn.net/v/t39.30808-1/480644834_1562916157701547_4234488127986584803_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=102&ccb=1-7&_nc_sid=e99d92&_nc_eui2=AeHDW8z5s6ZP_NIVUXt0waKc1pXxM9iOg5zWlfEz2I6DnLQFgOdHSeBiVtnPX-KpwrIW0yXPXr6OSRZpQ7xVyGMH&_nc_ohc=wp489qksAfYQ7kNvgFlbUvA&_nc_oc=AdhAfkjIU9ZNXaTEndyBmYGLSAMmdLqaFF1n0SCZjNbkJ_LByKpNPv1vZsG4-YDFuWunxgt6alcCmMsn_IdvxY80&_nc_zt=24&_nc_ht=scontent.fsgn2-8.fna&_nc_gid=AcmduXqaVFQl5qy3Zjw6-1Y&oh=00_AYAzB8JQTVCcYDdMcQRgHbBUstEcUyjGl_N__egAK9uFdQ&oe=67C0D944"
                alt="Hoaa"
            />
            <div className={cx('info')}>
                <h4 className={cx('name')}>
                    <span>Nguyen Van A</span>
                    <FontAwesomeIcon className={cx('check-icon')} icon={faCheckCircle} />
                </h4>
                <span className={cx('username')}>nguyenvana</span>
            </div>
        </div>
    );
}

export default AccountItem;
