# Thư viện `customize-cra` tùy chỉnh cấu hình webpack

    - Link: https://github.com/arackaf/customize-cra
    - Lệnh: `npm i customize-cra react-app-rewired --dev -D`

# Thư viện babel-plugin-module-resolver

    - Dùng để import cú pháp ngắn
    - Ví dụ:
        // Use this:
        import MyUtilFn from 'utils/MyUtilFn';
        // Instead of that:
        import MyUtilFn from '../../../../utils/MyUtilFn';

    - Link: https://github.com/tleunen/babel-plugin-module-resolver
    - Lệnh: `npm install --save-dev babel-plugin-module-resolver`

# Cài đặt và cấu hình Prettier trên VS Code

# Cấu hình sử dụng SASS

    - Tạo folder GlobalStyles
    - Cài đặt thư viện sass: `npm i -D sass`
    - Cài Reset CSS: `npm install --save normalize.css`
        @import 'normalize.css';
        @import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap');

    - Cài thư viện classNames: `npm i classnames`
    - Set default CSS: font-family, font-size, line-height ...

# Cài đặt và cấu hình Router

    - Lệnh: `npm i react-router-dom`
    - Tạo thư mục routes

# Thêm thư viện FontAwesome

    -   Lệnh:
        npm i --save @fortawesome/fontawesome-svg-core
        npm i --save @fortawesome/free-solid-svg-icons
        npm i --save @fortawesome/free-regular-svg-icons
        npm i --save @fortawesome/react-fontawesome

# Cài đặt thư viện tippy

    -   Dùng để làm tooltip, popper
    -   Link: https://github.com/atomiks/tippyjs-react
