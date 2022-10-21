import {createRouter, createWebHashHistory} from "vue-router";
import login from "../views/login.vue";
import register from "../views/register.vue";
import page from "../views/page";
import student from "../views/student";
import user from "../views/user";
import aclass from "../views/class";

const routes = [
    //登录
    {
        path: "/",
        name: "login",
        component: login,
        meta: {
            requireAuth: true,
        },
    },
    //注册
    {
        path: "/register",
        name: "register",
        component: register,
        meta: {
            requireAuth: true,
        },
    },
    //公共
    {
        path: "/page",
        name: "page",
        component: page,
        //主页
        children: [
            //学生
            {
                path: "/student",
                name: "student",
                component: student,
            },
            //用户
            {
                path: "/user",
                name: "user",
                component: user,
            },
            //班级
            {
                path: "/class",
                name: "class",
                component: aclass,
            },
        ],
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});

//登录拦截
router.beforeEach((to, from, next) => {
    if (!to.meta.requireAuth) {
        var token = sessionStorage.getItem("token");
        if (token != null) {
            next();
        } else {
            next({
                name: "login",
            });
        }
    } else {
        next();
    }
});
export default router;
