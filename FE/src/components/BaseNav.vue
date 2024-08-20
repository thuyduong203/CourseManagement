<script setup lang="ts">
import LoginApi from "@/api/loginApi";
import "../assets/css/admin.css";
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useCookies } from "vue3-cookies";
import { roleType } from "@/util/enumconvert";
import { getUser, setUser } from "@/util/userUtil";
import { getLoad } from "@/util/load";
import ModalForm from "./ModalForm.vue";
import StudentForm from "@/view/studentmanager/StudentForm.vue";
import courseApi from "@/api/courseApi";
import studentApi from "../api/studentApi";
import { closeModal } from "@/util/bootstraputil";

import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/css/index.css";
const route = useRoute();

const arrRouteCourse = ref<Array<string>>([
  "course",
  "class",
  "lesson",
  "classDetail",
  "classPeople",
  "classPoint",
]);

const courses = ref<Course[]>([]);
const student = ref<Student>({
  id: -1,
  code: "",
  name: "",
  birthDate: "",
  email: "",
  phone: "",
  gender: "MALE",
  address: "",
  status: "",
  courseId: -1,
});
const openCourse = ref<boolean>(true);
const { cookies } = useCookies();
const router = useRouter();

function checkLogin() {
  if (!cookies.get("token")) {
    router.push("/login");
  } else {
    LoginApi.getUserLogin()
      .then((res) => {
        setUser(res.data);
      })
      .catch(() => {
        logout();
      });
  }
}
function getCourse() {
  resetForm();
  courseApi.getAllCourse().then((response) => {
    courses.value = response.data;
  });
}

function logout() {
  cookies.remove("token");
  router.push("/login");
}

checkLogin();

function resetForm() {
  student.value = {
    id: -1,
    code: "",
    name: "",
    birthDate: "",
    email: "",
    phone: "",
    gender: "MALE",
    address: "",
    status: "",
  };
}

function addStudent() {
  studentApi.addStudent(student.value).then(() => {
    closeModal("RegisterModal");
    resetForm();
  });
}
</script>

<template>
  <loading :active="getLoad()" />
  <div class="menu">
    <router-link to="/">
      <div class="logo">
        <img alt="logo" src="../assets/image/logo-app.png" /></div
    ></router-link>
    <ul class="list-group">
      <li
        @click="openCourse = !openCourse"
        :class="{
             active_list_collapsed: arrRouteCourse.includes(route.name as string),
             open_course: openCourse
          }"
        class="list-group-item btn-menu"
        data-bs-target="#collapseCourse"
        data-bs-toggle="collapse"
        aria-expanded="true"
        aria-controls="collapseCourse">
        <i class="fa-solid fa-book"></i>
        <span>Quản lý khóa học</span>
        <i class="fa-solid fa-angle-up fa-sm"></i>
      </li>
      <div class="collapse show" id="collapseCourse">
        <router-link
          to="/course"
          :class="{ 'router-link-active': route.name == 'lesson' }"
          ><li class="list-group-item btn-menu-chillren">
            <span>Danh sách khóa học</span>
            <div class="active_list"></div>
          </li>
        </router-link>
        <router-link
          to="/class"
          :class="{
            'router-link-active':
              route.name == 'classDetail' ||
              route.name == 'classPeople' ||
              route.name == 'classPoint',
          }"
          ><li class="list-group-item btn-menu-chillren">
            <span>Danh sách lớp học</span>
            <div class="active_list"></div>
          </li>
        </router-link>
        <router-link to="/lesson" v-if="getUser()?.role == 'ADMIN'"
          ><li class="list-group-item btn-menu-chillren">
            <span>Danh sách môn học</span>
            <div class="active_list"></div>
          </li>
        </router-link>
      </div>

      <router-link to="/student" v-if="getUser()?.role == 'ADMIN'">
        <li class="list-group-item btn-menu">
          <i class="fa-solid fa-user-graduate"></i><span>Quản lý học viên</span>
          <div class="active_list"></div>
        </li>
      </router-link>

      <router-link to="/teacher">
        <li class="list-group-item btn-menu" v-if="getUser()?.role == 'ADMIN'">
          <i class="fa-solid fa-user"></i>
          <span>Quản lý giảng viên</span>
          <div class="active_list"></div>
        </li>
      </router-link>

      <router-link to="/mail">
        <li class="list-group-item btn-menu" v-if="getUser()?.role == 'ADMIN'">
          <i class="fa-solid fa-gears"></i>
          <span>Tinh chỉnh mail</span>
          <div class="active_list"></div>
        </li>
      </router-link>
      <li
        @click="getCourse"
        class="list-group-item btn-menu"
        v-if="getUser()?.role == 'ADMIN'"
        data-bs-toggle="modal"
        data-bs-target="#RegisterModal">
        <i class="fa-regular fa-id-card"></i>
        <span>Đăng ký học</span>
        <div class="active_list"></div>
      </li>
    </ul>
  </div>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid content_navbar">
      <div class="small__mail"></div>
      <div class="tool_navbar">
        <div class="btn_navbar">
          <button type="button" class="btn btn-bell position-relative">
            <i class="fa-regular fa-bell icon_bell"></i>
            <span
              class="position-absolute top-0 start-100 translate-middle badge badge-bell rounded-pill bg-primary">
              99+
            </span>
          </button>
        </div>
        <div class="ngan_cach"></div>
        <div class="dropdown">
          <div class="btn_navbar dropdown-toggle" data-bs-toggle="dropdown">
            <img
              class="image_user"
              alt="pic"
              src="../assets/image/avatar.jpg" />
            <div class="user_context">
              {{ getUser()?.name }}
              <p>{{ roleType(getUser()?.role) }}</p>
            </div>
          </div>
          <ul class="dropdown-menu">
            <li @click="logout" class="dropdown-item" style="cursor: pointer">
              Đăng xuất
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <div class="content pb-5" style="background-color: #f5f5f9">
    <router-view class="p-4"></router-view>
  </div>
  <ModalForm size-modal="modal-lg" title="Đăng ký học" id-modal="RegisterModal">
    <StudentForm :student="student" :courses="courses" />
    <template v-slot:submit>
      <button @click="addStudent" type="button" class="btn btn-info">
        Đăng ký
      </button>
    </template>
  </ModalForm>
</template>
<style>
.navbar {
  z-index: 100;
  background: #fff !important;
  display: flex !important;
  margin-left: 18%;
  box-shadow: 0 0 10px #2c2c2c2b !important;
  height: 65px;
  position: fixed;
  width: 82%;
}
.menu {
  z-index: 100;
  text-align: center;
  position: fixed;
  background: #ffffff;
  width: 18%;
  border-right: 1px #e2e8f0 solid;
  height: 100%;
  font-size: 14px !important;
}
.image_user {
  width: 42px;
  height: 42px;
  border-radius: 50%;
}
.btn_navbar {
  display: inline-block;
  margin: 0px 13px;
  color: white;
  cursor: pointer;
}
.tool_navbar {
  justify-content: flex-end !important;
  align-items: center !important;
  display: flex !important;
}
.icon_bell {
  color: rgb(75, 75, 75);
  font-size: 23px;
}
.btn-bell {
  padding: 0;
  width: 38px;
  height: 38px;
  border-radius: 10px;
  background-color: #f3fbff;
}
.btn-bell:hover {
  background-color: #f3fbff;
}
.badge-bell {
  padding: 2px 3px;
  font-size: 10px;
}
.ngan_cach {
  height: 30px;
  width: 1px;
  background-color: #bfe9ff;
  margin: 0px 10px;
}
.user_context {
  min-width: 100px;
  color: black;
  float: right;
  margin-right: 10px;
}
.user_context > p {
  color: gray;
  font-size: 13px;
  margin: 0px;
}
.logo {
  padding: 20px;
}
.logo > img {
  height: 40px;
  margin-top: -10px;
  margin-right: 10px;
}
.btn-menu {
  border: none;
  text-align: left;
  padding: 15px;
  cursor: pointer;
  font-size: 15px;
  color: #566a7f;
  padding: 10px;
  margin: 3px 10px;
  text-decoration: none !important;
}
.btn-menu i {
  margin-right: 10px;
}
.btn-menu:hover {
  background-color: #f3fbff;
  border-radius: 10px;
}
.router-link-active .btn-menu {
  color: #696cff;
  background-color: #e7e7ff;
  border-radius: 10px;
  transition: all 0.5s ease-in;
}

.router-link-active .btn-menu-chillren {
  color: #696cff;
  background-color: #e7e7ff;
  border-radius: 10px;
  transition: all 0.5s ease-in;
}
.btn-menu-chillren {
  color: #566a7f;
  transition: all 0.5s ease-in;
  margin: 3px 10px 3px 35px;
  text-align: left;
  border: none !important;
}

.router-link-active .active_list {
  height: 70%;
  width: 4px;
  background-color: #696cff;
  position: absolute;
  right: -10px;
  top: 15%;
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  transition: all 0.5s ease-in;
}

.active_list_collapsed {
  color: #696cff;
}

.active_list_collapsed .active_list {
  height: 70%;
  width: 4px;
  background-color: #696cff;
  position: absolute;
  right: -10px;
  top: 15%;
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  transition: all 0.5s ease-in;
}
.content {
  margin-left: 18%;
  padding-top: 65px;
  max-width: 82%;
  min-height: 100vh;
}

.fa-angle-up {
  margin-left: 10px;
  transition: all 0.3s ease-in;
  transform: rotate(90deg);
}

.open_course .fa-angle-up {
  transform: rotate(180deg);
}
</style>
