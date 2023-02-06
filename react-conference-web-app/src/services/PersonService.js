import http from "../http-common";

const getAll = () => {
  console.log("#############################################")
  console.log(process.env.REACT_APP_CONFERENCE_APP_API_URL);
  return http.get("/persons");
};

const get = id => {
  return http.get(`/persons/${id}`);
};

const create = data => {
  return http.post("/persons", data);
};

const update = (id, data) => {
  return http.put(`/persons/${id}`, data);
};

const remove = id => {
  return http.delete(`/persons/${id}`);
};

const removeAll = () => {
  return http.delete(`/persons`);
};

const findByFirstName = firstName => {
  return http.get(`/persons?name=${firstName}`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  removeAll,
  findByFirstName: findByFirstName
};
