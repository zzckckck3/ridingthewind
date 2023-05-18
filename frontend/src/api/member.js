import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
    await api.post(`/member/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/member/info/${userid}`).then(success).catch(fail);
}

export { login, findById};