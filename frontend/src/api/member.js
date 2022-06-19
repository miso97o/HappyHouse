import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, user).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/${userid}`).then(success).catch(fail);
}

function register(user, success, fail) {
  api.post(`/user/signup`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

function update(user, success, fail) {
  api.put(`/user/update`, JSON.stringify(user)).then(success).catch(fail);
}

function interestList(userid, success, fail) {
  api.get(`/user/interest/${userid}`).then(success).catch(fail);
}

function addInterest(params, success, fail) {
  api.post(`/user/interest`, params).then(success).catch(fail);
}

function removeInterest(params, success, fail) {
  api.post(`/user/interest/remove`, params).then(success).catch(fail);
}

// function logout(success, fail)

export {
  login,
  findById,
  register,
  deleteUser,
  update,
  interestList,
  addInterest,
  removeInterest,
};
