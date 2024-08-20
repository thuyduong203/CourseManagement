import { createRouter, createWebHistory } from "vue-router";
import User from "@/view/User.vue";
import BaseNav from "@/components/BaseNav.vue";
import Student from "@/view/studentmanager/Student.vue";
import Login from "@/view/Login.vue";
import Teacher from "@/view/teachermanager/Teacher.vue";
import Course from "@/view/coursemanager/Course.vue";
import Classes from "@/view/classmanager/Classes.vue";
import Lesson from "@/view/lessonmanager/Lesson.vue";
import ClassesDetail from "@/view/classmanager/ClassesDetail.vue";
import News from "@/view/classmanager/News.vue";
import Peoples from "@/view/classmanager/Peoples.vue";
import Points from "@/view/classmanager/Points.vue";
import PointsStudent from "@/view/classmanager/PointsStudent.vue";
import Forbidden from "@/view/Forbidden.vue";
import LessonManager from "@/view/lessonmanager/LessonManager.vue";
import ClassLesson from "@/view/classmanager/ClassLesson.vue";
import Home from "@/view/website/Home.vue";
import CourseDetail from "@/view/website/CourseDetail.vue";
import Mail from "@/view/mailmanager/Mail.vue";

const router = createRouter({
  history: createWebHistory(),
  scrollBehavior() {
    return { top: 0 };
  },
  routes: [
    {
      path: "/",
      component: BaseNav,
      redirect: "/trang-chu",
      children: [
        {
          path: "/course",
          component: Course,
          name: "course",
          meta: { title: "Quản lý khóa học" },
        },
        {
          path: "/forbidden",
          component: Forbidden,
          name: "forbidden",
          meta: { title: "Không có quyền" },
        },
        {
          path: "/course/:idCourse(\\d+)",
          name: "lesson",
          component: Lesson,
          meta: { title: "Quản lý khóa học" },
        },
        {
          path: "/class",
          component: Classes,
          name: "class",
          meta: { title: "Quản lý lớp học" },
        },
        {
          path: "/lesson",
          component: LessonManager,
          name: "lessonManger",
          meta: { title: "Quản lý môn học" },
        },
        {
          path: "/class/:codeClass",
          component: ClassesDetail,
          children: [
            {
              path: "",
              component: News,
              name: "classDetail",
            },
            {
              path: "people",
              component: Peoples,
              name: "classPeople",
            },
            {
              path: "point",
              component: Points,
              name: "classPoint",
            },
            {
              path: "point/student",
              component: PointsStudent,
              name: "classPointStudent",
            },
            // {
            //   path: "schedule",
            //   component: Schedule,
            //   name: "classScheduleStudent",
            // },
            {
              path: "lesson",
              component: ClassLesson,
              name: "classLessonStudent",
            },
          ],
        },
        {
          path: "/student",
          component: Student,
          meta: { title: "Quản lý học viên" },
        },
        {
          path: "/teacher",
          component: Teacher,
          meta: { title: "Quản lý giảng viên" },
        },
        {
          path: "/user",
          component: User,
          meta: { title: "Thông tin người dùng" },
        },
        {
          path: "/mail",
          component: Mail,
          meta: { title: "Tinh chỉnh mail" },
        },
      ],
    },
    {
      path: "/login",
      component: Login,
    },
    {
      path: "/trang-chu",
      component: Home,
    },
    {
      path: "/khoa-hoc/:id",
      component: CourseDetail,
    },
  ],
});

router.beforeEach((to, from) => {
  if (typeof to.meta?.title === "string") {
    document.title = to.meta.title;
  } else {
    document.title = "NWS";
  }
});

export default router;
