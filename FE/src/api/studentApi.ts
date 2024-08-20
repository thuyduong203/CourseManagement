import axiosClient from "./axios";

const studentApi = {
  getAllStudent: async () => {
    const response = await axiosClient.get(`/student`);
    return response;
  },
  searchStudentCourse: async (
    page: number,
    size: number,
    searchText: string,
    id: string
  ) => {
    const response = await axiosClient.get(
      `/student/course/${id}?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  searchStudent: async (page: number, size: number, searchText: string) => {
    const response = await axiosClient.get(
      `/student?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  getStudentId: async (id: number) => {
    const response = await axiosClient.get(`/student/${id}`);
    return response;
  },
  addStudent: async (student: any) => {
    const response = await axiosClient.post(`/student/add`, student);
    return response;
  },
  updateStudent: async (student: any) => {
    const response = await axiosClient.post(`/student/update`, student);
    return response;
  },
  removeStudent: async (id: number) => {
    const response = await axiosClient.delete(`/student/remove/${id}`);
    return response;
  },
};

export default studentApi;
