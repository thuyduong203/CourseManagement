import axiosClient from "./axios";

const courseApi = {
  getAllCourse: async () => {
    const response = await axiosClient.get(`/course`);
    return response;
  },
  searchCourse: async (page: number, size: number, searchText: string) => {
    const response = await axiosClient.get(
      `/course/page?page=${page}&size=${size}&searchText=${searchText}`
    );
    return response;
  },
  getCourseId: async (id: number) => {
    const response = await axiosClient.get(`/course/${id}`);
    return response;
  },
  addCourse: async (course: any) => {
    const response = await axiosClient.post(`/course/add`, course);
    return response;
  },
  addCourseLesson: async (request: any) => {
    const response = await axiosClient.post(`/course/add-lesson`, request);
    return response;
  },
  updateCourse: async (course: any) => {
    const response = await axiosClient.post(`/course/update`, course);
    return response;
  },
  updateStatusCourse: async (course: any) => {
    const response = await axiosClient.post(`/course/update`, course);
    return response;
  },
};

export default courseApi;
