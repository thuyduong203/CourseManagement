import axiosClient from "./axios";

const mailApi = {
  getAllMail: async () => {
    const response = await axiosClient.get(`/mail`);
    return response;
  },
  getMailId: async (id: number) => {
    const response = await axiosClient.get(`/mail/${id}`);
    return response;
  },
  updateMail: async (mail: any) => {
    const response = await axiosClient.post(`/mail/update`, mail);
    return response;
  },
};

export default mailApi;
