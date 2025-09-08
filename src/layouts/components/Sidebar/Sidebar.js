import classNames from 'classnames/bind';
import styles from './Sidebar.module.scss';
import config from '~/config';
import Menu, { MenuItem } from './Menu';
import {
    HomeActiveIcon,
    HomeIcon,
    LiveActiveIcon,
    LiveIcon,
    UserGroupActiveIcon,
    UserGroupIcon,
} from '~/components/Icons';

const cx = classNames.bind(styles);

function Sidebar() {
    return (
        <aside className={cx('wrapper')}>
            <Menu>
                <MenuItem
                    title="Home"
                    to={config.routes.home}
                    icon={<HomeIcon />}
                    activeIcon={<HomeActiveIcon />}
                />
                <MenuItem
                    title="Lessons"
                    to={config.routes.lessons}
                    icon={<UserGroupIcon />}
                    activeIcon={<UserGroupActiveIcon />}
                />
                <MenuItem 
                    title="Games" 
                    to={config.routes.games} 
                    icon={<LiveIcon />} 
                    activeIcon={<LiveActiveIcon />} 
                />
                <MenuItem 
                    title="Progress" 
                    to={config.routes.progress} 
                    icon={<UserGroupIcon />} 
                    activeIcon={<UserGroupActiveIcon />} 
                />
            </Menu>
        </aside>
    );
}

export default Sidebar;
