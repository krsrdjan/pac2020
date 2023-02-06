import http from "../http-common";

const getAll = () => {
  console.log("#############################################")
  console.log(process.env.REACT_APP_CONFERENCE_APP_API_URL);
  return http.get("/overviews");
};

const get = id => {
  return http.get(`/overviews/${id}`);
};

const create = data => {
  return http.post("/overviews", data);
};

const update = (id, data) => {
  return http.put(`/overviews/${id}`, data);
};

const remove = id => {
  return http.delete(`/overviews/${id}`);
};

const removeAll = () => {
  return http.delete(`/overviews`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  removeAll
};
