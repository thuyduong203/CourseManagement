import axios from "axios";
import { useRouter } from "vue-router";
import { useCookies } from "vue3-cookies";

const { cookies } = useCookies();
const router = useRouter();

const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "content-type": "application/json",
  },
});

axiosClient.interceptors.request.use(
  (config) => {
    const token = cookies.get("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  () => {}
);

axiosClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response && error.response.status === 403) {
      window.location.href = "/forbidden";
    }
    if (error.response && error.response.status === 400) {
      console.error(error.response.data.message);
    }
  }
);

export default axiosClient;
