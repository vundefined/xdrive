- node version 20.8.0

```
<img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

https://admin.spicyboy.cn/#/dashboard/dataVisualize

import.meta.env.VITE_BASE_URL

server: {
    proxy: {
        '^/admin': {
            target: 'http://localhost:8086/',
            changeOrigin: true
        },
        rewrite: function (path) {
          let _path = path.replace(/^\/wvp/, '/api');
          console.log('proxy-wvp-gb28181--', _path);
          return _path;
        }
    },
}
```
