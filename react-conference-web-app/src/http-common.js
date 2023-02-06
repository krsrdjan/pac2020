import axios from "axios";

export default axios.create({
    // config based on .env file
    baseURL: process.env.REACT_APP_CONFERENCE_APP_API_URL,
    headers: {
        "Content-type": "application/json"
    }

    //local config below
    // baseURL: 'http://localhost:8080',
    // headers: {
    //   "Content-type": "application/json"
    // }
});
