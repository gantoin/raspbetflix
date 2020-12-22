import axios from "axios";

export default axios.create({
    baseURL: window.location.hostname + ":8080",
    headers: {
        "Content-type": "application/json"
    }
});