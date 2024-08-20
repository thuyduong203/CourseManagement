import axiosClient from "./axios";

const LoginApi = {
  login: async (accountLogin: Object) => {
    const response = await axiosClient.post(`/login`, accountLogin);
    return response;
  },
  getUserLogin: async () => {
    const response = await axiosClient.get(`/get-user-login`);
    return response;
  },
};

export default LoginApi;
