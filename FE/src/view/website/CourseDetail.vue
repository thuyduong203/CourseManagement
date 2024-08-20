<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import courseApi from "../../api/courseApi";
import lessonApi from "../../api/lessonApi";
import { ref } from "vue";
import draggable from "vuedraggable";
import studentApi from "@/api/studentApi";
import Swal from "sweetalert2";

import { setLoad } from "@/util/load";

const router = useRouter();
const route = useRoute();

const indexSelect = ref<number>(1);

const course = ref<Course>({
  id: -1,
  name: "",
  description: "",
});
const lessons = ref<Lesson[]>([]);

function getCourse(idCourse: number) {
  courseApi.getCourseId(idCourse).then((res) => {
    course.value = res.data;
  });
}

function getLesson(idCourse: string) {
  lessonApi.getAllLessonCourse(idCourse).then((res) => {
    lessons.value = res.data;
  });
}

if (typeof route.params.id == "string") {
  getCourse(parseInt(route.params.id));
  getLesson(route.params.id);
}
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
  if (typeof route.params.id == "string") {
    student.value.courseId = parseInt(route.params.id);
    studentApi.addStudent(student.value).then(() => {
      Swal.fire({
        title: "Thành công!",
        text: "Đăng ký khóa học thành công!",
        icon: "success",
      }).then(() => {
        resetForm();
      });
    });
  }
}

const courses = ref<Course[]>([]);
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 6,
});

const searchText = ref<string>("");
function searchCourse() {
  setLoad(true);
  courseApi
    .searchCourse(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
    )
    .then((response) => {
      courses.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
      setLoad(false);
    });
}

searchCourse();

function nextPage(params: number) {
  pageable.value.pageNumber = pageable.value.pageNumber + params;
  searchCourse();
}
</script>
<template>
  <div class="content-home">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container">
        <div class="navbar-brand" @click="router.push('/')">
          <img src="../../assets/image/logo-web.png" />
        </div>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavMenu"
          aria-controls="navbarNavMenu"
          aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavMenu">
          <div class="navbar-nav ms-auto">
            <router-link class="nav-link" to="/">TRANG CHỦ</router-link>
            <a class="nav-link mx-3" href="/trang-chu#course-list">KHÓA HỌC</a>
            <a class="nav-link" href="/trang-chu#about">VỀ CHÚNG TÔI</a>
          </div>
        </div>
      </div>
    </nav>
    <div class="container content-container" style="height: 600px">
      <div class="row">
        <div class="col-lg-7">
          <div>
            <h1 class="my-4 mb-2" style="font-weight: 900; font-size: 50px">
              {{ course.name }}
            </h1>
          </div>
          <div>
            <b style="color: red; font-size: 2em">
              {{
                course.price?.toLocaleString("vi", {
                  style: "currency",
                  currency: "VND",
                })
              }}
            </b>
          </div>
          <div class="mt-4 mb-2">
            <button
              @click="indexSelect = 1"
              class="btn btn-light"
              :class="{ 'btn-info': indexSelect == 1 }">
              <b>Tổng quan</b>
            </button>
            <button
              :class="{ 'btn-info': indexSelect == 2 }"
              @click="indexSelect = 2"
              class="btn btn-light mx-3">
              <b>Môn học</b>
            </button>
          </div>
          <div
            class="container"
            style="
              overflow: auto;
              height: 400px;
              padding: 25px;
              border-radius: 10px;
              background-color: white;
              box-shadow: 0px 2px 1px -1px rgba(0, 0, 0, 0.2),
                0px 1px 1px 0px rgba(0, 0, 0, 0.14),
                0px 1px 3px 0px rgba(0, 0, 0, 0.12);
            ">
            <div v-show="indexSelect == 1">
              <b style="font-size: 20px">Tổng quan khóa học</b>
              <hr class="my-2" />
              <div>
                <div class="my-3">
                  <span class="card-text" style="white-space: pre-line">
                    {{ course.description }}
                  </span>
                </div>
              </div>
            </div>
            <div v-show="indexSelect == 2">
              <b style="font-size: 20px">Danh sách môn học</b>
              <hr class="my-2" />
              <div>
                <div class="my-3">
                  <draggable
                    :disabled="true"
                    v-model="lessons"
                    tag="div"
                    class="card card-lesson"
                    item-key="id">
                    <template #item="{ element: less, index }">
                      <div>
                        <div
                          class="d-inline-flex gap-1 justify-content-between"
                          :class="{
                            firtP: index === 0,
                          }">
                          <div
                            :title="less.name"
                            class="collapsed btn-collap"
                            data-bs-toggle="collapse"
                            :data-bs-target="'#collapseLesson' + less.id"
                            aria-expanded="false"
                            :aria-controls="'collapseLesson' + less.id">
                            <i class="fa-solid fa-angle-up fa-sm"></i> &nbsp;
                            <i class="fa-solid fa-book-skull"></i> &nbsp;
                            {{ less.name }}
                          </div>
                          <div class="p-3 px-0">
                            <span class="badge text-bg-info"
                              ><i class="fa-solid fa-clock"></i>&nbsp;Thời
                              lượng: {{ less.quantity }} buổi</span
                            >
                          </div>
                          <div class="pt-3"></div>
                        </div>
                        <div class="collapse" :id="'collapseLesson' + less.id">
                          <div
                            class="card card-body"
                            style="white-space: pre-line">
                            {{ less.description }}
                          </div>
                        </div>
                      </div>
                    </template>
                  </draggable>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-5 mt-4">
          <div
            class="container"
            style="
              padding: 25px;
              border-radius: 10px;
              background-color: white;
              box-shadow: 0px 2px 1px -1px rgba(0, 0, 0, 0.2),
                0px 1px 1px 0px rgba(0, 0, 0, 0.14),
                0px 1px 3px 0px rgba(0, 0, 0, 0.12);
            ">
            <b style="font-size: 20px">Đăng ký khóa học</b
            ><img
              style="border-radius: 15px"
              height="200px"
              :src="course.imageUrl"
              class="card-img-top my-2"
              alt="..." />
            <form class="mt-4">
              <div class="row gy-3 overflow-hidden">
                <div class="col-12">
                  <div class="form-floating">
                    <input
                      v-model="student.name"
                      type="text"
                      class="form-control"
                      name="name"
                      id="name"
                      placeholder="Họ tên"
                      required />
                    <label for="name" class="form-label"
                      >Họ tên <span class="required">*</span></label
                    >
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-floating">
                    <input
                      v-model="student.email"
                      type="text"
                      class="form-control"
                      name="email"
                      id="email"
                      placeholder="Họ tên"
                      required />
                    <label for="email" class="form-label"
                      >Email <span class="required">*</span></label
                    >
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-floating">
                    <input
                      v-model="student.phone"
                      type="text"
                      class="form-control"
                      name="phone"
                      id="phone"
                      placeholder="Họ tên"
                      required />
                    <label for="phone" class="form-label"
                      >Số điện thoại <span class="required">*</span></label
                    >
                  </div>
                </div>
                <div class="col-12">
                  <div class="d-grid">
                    <button
                      @click.prevent="addStudent"
                      class="btn btn-dark btn-lg"
                      type="submit">
                      Đăng ký ngay
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div>
        <h1
          class="text-center my-5"
          style="color: #001c66; font-weight: 600; width: 100%">
          Khóa Học Tại Newwave Solutions
        </h1>
        <div class="row container mx-1">
          <div
            class="col-lg-4 col-md-6 p-2"
            style="cursor: pointer"
            v-for="course in courses"
            :key="course.id">
            <div
              class="card p-0 card-course"
              :title="course.name"
              style="
                border: none;
                box-shadow: 0px 2px 1px -1px rgba(0, 0, 0, 0.2),
                  0px 1px 1px 0px rgba(0, 0, 0, 0.14),
                  0px 1px 3px 0px rgba(0, 0, 0, 0.12);
              ">
              <RouterLink :to="'/khoa-hoc/' + course.id">
                <img
                  height="200px"
                  :src="course.imageUrl"
                  class="card-img-top"
                  alt="..." />
                <div class="card-body pt-2 px-3">
                  <h5 class="card-title m-0">
                    <b>{{ course.name }}</b>
                  </h5>

                  <div class="my-3">
                    <p>
                      <i
                        class="fa-solid fa-book-bookmark"
                        style="color: cadetblue"></i>
                      &nbsp;<span style="color: gray; font-weight: 500">
                        Số lượng môn:
                      </span>
                      <b style="color: gray">{{ course.lessonQuantity }}</b>
                    </p>
                    <p class="my-2">
                      <i
                        class="fa-solid fa-landmark"
                        style="color: cadetblue"></i>
                      &nbsp;<span style="color: gray; font-weight: 500">
                        Số lượng lớp:
                      </span>
                      <b style="color: gray">{{ course.classQuantity }}</b>
                    </p>
                    <p>
                      <i
                        class="fa-solid fa-user-graduate"
                        style="color: cadetblue"></i>
                      &nbsp;<span style="color: gray; font-weight: 500">
                        Số lượng học viên:
                      </span>
                      <b style="color: gray">{{ course.studentQuantity }}</b>
                    </p>
                  </div>
                  <div>
                    <b style="color: #001c66; font-size: 1.3em">
                      {{
                        course.price?.toLocaleString("vi", {
                          style: "currency",
                          currency: "VND",
                        })
                      }}
                    </b>
                  </div>
                  <!-- <p class="card-text" style="white-space: pre-line">
                      {{ course.description }}
                    </p> -->
                </div>
              </RouterLink>
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-center mt-4">
          <button
            @click="nextPage(-1)"
            :disabled="pageable.pageNumber == 0"
            type="button"
            class="btn btn-info">
            <i class="fa-solid fa-left-long"></i> Trang trước
          </button>
          &nbsp;
          <button
            :disabled="pageable.totalPages - 1 == pageable.pageNumber"
            type="button"
            class="btn btn-info"
            @click="nextPage(1)">
            Trang tiếp <i class="fa-solid fa-right-long"></i>
          </button>
        </div>
      </div>
      <footer class="footer pb-5 mt-5">
        <div class="container row">
          <div class="col-9">
            <h4 style="color: #001c66; font-weight: 600; width: 100%">
              Địa chỉ
            </h4>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-location-dot"></i>&nbsp;Tầng 1, 4, 10, toà
              nhà Mitec, đường Dương Đình Nghệ, quận Cầu Giấy, thành phố Hà Nội,
              Việt Nam
            </p>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-location-dot"></i>&nbsp;1-11-8 Yushima, Quận
              Bunkyo, Thành phố Tokyo 113-0034, Nhật Bản
            </p>
          </div>
          <div class="col-3">
            <h4 style="color: #001c66; font-weight: 600; width: 100%">
              Liên hệ
            </h4>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-phone"></i>&nbsp;0987657412
            </p>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-envelope"></i>&nbsp;trieutuong2003@gmail.com
            </p>
          </div>
        </div>
      </footer>
    </div>
  </div>
</template>
<style scoped>
.content-home {
  background-image: url("https://cdn-main.28tech.com.vn/media/core/background/bg-image-10.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-color: white;
}
.btn-info:disabled {
  display: none;
}
.navbar {
  background-color: #0b033c !important;
  margin: 0;
  width: 100%;
  box-shadow: none !important;
  height: 70px;
}
.navbar-brand img {
  height: 40px;
}
.navbar-nav .nav-link {
  color: white !important;
}
.content-container {
  padding-top: 80px;
  height: 800px;
}
.hero-content {
  margin-top: 100px;
}
.hero-title {
  font-weight: 600;
  font-size: 3.5vw !important;
  text-align: center;
  color: #ffffff;
}
.hero-text {
  text-align: center;
  color: #ffffff;
  padding: 50px 5vw;
}
.hero-button .btn {
  transition: all 0.5s;
  width: 200px;
  font-weight: 900;
  height: 60px;
}
.hero-button .btn:hover {
  transform: translate3d(0, -2px, 0);
}
.btn-all-course:hover {
  transform: translate3d(0, -2px, 0);
}
.card-lesson * {
  border-radius: 0;
}
.card-lesson .badge {
  border-radius: 10px;
  font-size: 13px;
  padding: 8px;
}
.card-lesson .card-body {
  padding-top: 10px;
  padding-bottom: 10px;
}

.card-lesson .collapsed .fa-angle-up {
  transform: rotate(180deg);
}
.card-lesson .btn-collap {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  width: 100%;
  padding: 15px 0px;
  line-height: 30px;
}
.card-lesson .collapsed .fa-angle-up {
  transform: rotate(180deg);
}

.card-lesson .d-inline-flex {
  border-top: 1px solid rgba(0, 0, 0, 0.175);
  width: 100%;
  cursor: pointer;
}
.card-lesson .select {
  background-color: #e3f3fd;
}
.card-lesson .firtP {
  border-top: none;
}
.card-lesson .collapsing .card {
  border: none;
  border-top: 1px solid rgba(0, 0, 0, 0.175);
}
.card-lesson .collapse .card {
  border: none;
  border-top: 1px solid rgba(0, 0, 0, 0.175);
}
</style>
