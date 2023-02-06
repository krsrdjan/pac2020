import http from "../http-common";

const getAll = () => {
  console.log("#############################################")
  console.log(process.env.REACT_APP_CONFERENCE_APP_API_URL);
  return http.get("/events");
};

const get = id => {
  return http.get(`/events/${id}`);
};

const getDayOverview = id => {
  return http.get(`/events/overview/${id}`);
};

const create = data => {
  return http.post("/events", data);
};

const update = (id, data) => {
  return http.put(`/events/${id}`, data);
};

const remove = id => {
  return http.delete(`/events/${id}`);
};

const removeAll = () => {
  return http.delete(`/events`);
};

export default {
  getAll,
  get,
  getDayOverview,
  create,
  update,
  remove,
  removeAll
};
