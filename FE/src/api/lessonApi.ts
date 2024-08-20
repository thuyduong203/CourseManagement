import axiosClient from "./axios";

const lessonApi = {
  getAllLesson: async () => {
    const response = await axiosClient.get(`/lesson`);
    return response;
  },
  getAllLessonCourse: async (idCourse: string) => {
    const response = await axiosClient.get(`/lesson/course/${idCourse}`);
    return response;
  },

  searchLesson: async (
    page: number,
    size: number,
    searchText: string,
    idCourse: number
  ) => {
    const response = await axiosClient.get(
      `/lesson/page/${idCourse}?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  searchLessonClass: async (
    page: number,
    size: number,
    searchText: string,
    codeClass: string
  ) => {
    const response = await axiosClient.get(
      `/lesson/class/page/${codeClass}?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  searchLessonManager: async (
    page: number,
    size: number,
    searchText: string
  ) => {
    const response = await axiosClient.get(
      `/lesson/page?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  getLessonId: async (id: number) => {
    const response = await axiosClient.get(`/lesson/${id}`);
    return response;
  },
  addLesson: async (lesson: any, idCourse: number) => {
    const response = await axiosClient.post(`/lesson/add`, lesson);
    return response;
  },
  updateLesson: async (lesson: any) => {
    const response = await axiosClient.post(`/lesson/update`, lesson);
    return response;
  },
};

export default lessonApi;
