import axiosClient from "./axios";

const teacherApi = {
  getAllTeacher: async () => {
    const response = await axiosClient.get(`/teacher`);
    return response;
  },
  getTeacherClass: async (request: Object) => {
    const response = await axiosClient.get(`/teacher/class`, {
      params: request,
    });
    return response;
  },
  searchTeacher: async (page: number, size: number, searchText: string) => {
    const response = await axiosClient.get(
      `/teacher/page?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  getTeacherId: async (id: number) => {
    const response = await axiosClient.get(`/teacher/${id}`);
    return response;
  },
  addTeacher: async (teacher: any) => {
    const response = await axiosClient.post(`/teacher/add`, teacher);
    return response;
  },
  updateTeacher: async (teacher: any) => {
    const response = await axiosClient.post(`/teacher/update`, teacher);
    return response;
  },
  removeTeacher: async (id: number) => {
    const response = await axiosClient.delete(`/teacher/remove/${id}`);
    return response;
  },
};

export default teacherApi;
