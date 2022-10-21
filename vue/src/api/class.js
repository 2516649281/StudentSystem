import axios from "../utils/axios";

//查看所有班级
export function lookAllClass() {
    return axios.get("/class");
}

//根据id值查看班级
export function lookClassById(classId) {
    return axios.get(`/class/${classId}`);
}

//添加一条班级记录
export function insertOneClass(obj) {
    return axios({
        url: "/class",
        method: "POST",
        data: obj,
    });
}

//修改一条班级信息
export function updateOneClass(obj) {
    return axios({
        url: "/class",
        method: "PUT",
        data: obj,
    });
}

//删除一条班级信息
export function deleteOneClass(classId) {
    return axios({
        url: "/class",
        method: "DELETE",
        data: classId,
    });
}
