import axios from "../utils/axios";

//查询所有学生
export function lookAllStudent() {
    return axios.get("/student");
}

//查看一个学生
export function lookOneStudent(studentId) {
    return axios.get(`/student/${studentId}`);
}

//根据班级查看学生信息
export function lookOneStudentByClass(classId) {
    return axios.get(`/student/class/${classId}`);
}

//添加一名学生
export function insertOneStudent(obj) {
    return axios({
        url: "/student",
        method: "POST",
        data: obj,
    });
}

//修改一名学生
export function updateOneStudent(obj) {
    return axios({
        url: "/student",
        method: "PUT",
        data: obj,
    });
}

//删除学生
export function deleteOneStudent(ids) {
    return axios({
        url: "/student",
        method: "DELETE",
        data: ids,
    });
}
