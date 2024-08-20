import axiosClient from "./axios";

const classesApi = {
  getAllClasses: async () => {
    const response = await axiosClient.get(`/classes`);
    return response;
  },
  searchClasses: async (page: number, size: number, searchText: string) => {
    const response = await axiosClient.get(
      `/classes?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  getClassesId: async (id: number) => {
    const response = await axiosClient.get(`/classes/${id}`);
    return response;
  },
  getClassStudentCode: async (searchText: string, code: string) => {
    const response = await axiosClient.get(
      `/classes/student/${code}?searchText=${searchText}`
    );
    return response;
  },
  getPointClassStudentCode: async (searchText: string, code: string) => {
    const response = await axiosClient.get(
      `/classes/point/student/${code}?searchText=${searchText}`
    );
    return response;
  },
  getLessonCodeClass: async (code: string) => {
    const response = await axiosClient.get(`/classes/lesson/${code}`);
    return response;
  },
  changeStatusLesson: async (less: Lesson) => {
    const response = await axiosClient.post(
      `/classes/lesson/change-status`,
      less
    );
    return response;
  },
  getCheduleCodeClass: async (page: number, size: number, code: string) => {
    const response = await axiosClient.get(
      `/classes/schedule/${code}?page=${page}&size=${size}`
    );
    return response;
  },
  addClasses: async (classes: any) => {
    const response = await axiosClient.post(`/classes/add`, classes);
    return response;
  },
  addPointClass: async (addPointRequest: AddPoint) => {
    const response = await axiosClient.post(
      `/classes/add-point`,
      addPointRequest
    );
    return response;
  },
  addStudentClass: async (idClass: string, listIdStudent: Array<number>) => {
    const response = await axiosClient.put(
      `/classes/add-student-class/${idClass}`,
      listIdStudent
    );
    return response;
  },
  updateClasses: async (classes: any) => {
    const response = await axiosClient.post(`/classes/update`, classes);
    return response;
  },
  removeClasses: async (id: number) => {
    const response = await axiosClient.delete(`/classes/remove/${id}`);
    return response;
  },
};

export default classesApi;
