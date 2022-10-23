const {defineConfig} = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: "8000", // 设置端口号
        proxy: {
            "/api": {
                target: "http://47.92.215.94:8080", //API服务器的地址
                // target: "http://localhost:8080", //API服务器的地址
                ws: true, //代理websockets
                changeOrigin: true, // 是否跨域，虚拟的站点需要更管origin
                pathRewrite: {
                    "^/api": "",
                },
            },
        },
    },
    assetsDir: "static",
    parallel: false,
    publicPath: "./",
});
