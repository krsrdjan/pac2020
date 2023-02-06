import http from "../http-common";

const getAll = () => {
  console.log("#############################################")
  console.log(process.env.REACT_APP_CONFERENCE_APP_API_URL);
  return http.get("/talks");
};

const get = id => {
  return http.get(`/talks/${id}`);
};

const create = data => {
  return http.post("/talks", data);
};

const update = (id, data) => {
  return http.put(`/talks/${id}`, data);
};

const remove = id => {
  return http.delete(`/talks/${id}`);
};

const removeAll = () => {
  return http.delete(`/talks`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  removeAll,
};
