// Config
import config from '~/config';

// layouts
import { HeaderOnly } from '~/layouts';

// Pages
import Home from '~/pages/Home';
import Lessons from '~/pages/Lessons';
import Games from '~/pages/Games';
import Progress from '~/pages/Progress';
import Profile from '~/pages/Profile';
import Upload from '~/pages/Upload';
import Search from '~/pages/Search';
import Following from '~/pages/Following';
import Live from '~/pages/Live';

// Sử dụng cho những route không cần đăng nhập nhưng vẫn xem được
const publicRoutes = [
    { path: config.routes.home, component: Home },
    { path: config.routes.lessons, component: Lessons },
    { path: config.routes.games, component: Games },
    { path: config.routes.progress, component: Progress },
    { path: config.routes.profile, component: Profile },
    { path: config.routes.search, component: Search, layout: null },
    // Legacy routes for backward compatibility
    { path: config.routes.following, component: Following },
    { path: config.routes.live, component: Live },
    { path: config.routes.upload, component: Upload, layout: HeaderOnly },
];

// Sử dụng cho route bắt buộc đăng nhập mới xem được
const privateRoutes = [];

export { publicRoutes, privateRoutes };
