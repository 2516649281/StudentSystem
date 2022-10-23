import axios from "axios";

//设置公共后台url地址
const server = axios.create({
    baseURL: "http://47.92.215.94:8000/api",
});

server.interceptors.request.use(
    (config) => {
        var token = sessionStorage.getItem("token");
        console.log(`获取token:${token}`);
        if (token != null) {
            console.log("已向请求添加请求头");
            config.headers["token"] = token;
        }
        return config;
    },
    (err) => {
        return Promise.err(err);
    }
);
export default server;
