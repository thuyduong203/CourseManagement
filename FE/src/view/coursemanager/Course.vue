<script setup lang="ts">
import { ref, watch } from "vue";
import courseApi from "../../api/courseApi";
import "./course.css";
import Pageing from "@/components/Pageing.vue";
import { RouterLink } from "vue-router";
import { closeModal } from "@/util/bootstraputil";
import ModalForm from "@/components/ModalForm.vue";
import CourseForm from "./CourseForm.vue";
import { getUser } from "@/util/userUtil";
import type { Course, CourseError } from "@/typings/course";
import type { Pageable } from "@/typings/pageable";
import { comfirm } from "@/util/sweetalert";
import { toast } from "vue3-toastify";
import { setLoad } from "@/util/load";
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

let timer: any = null;
watch(searchText, () => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchCourse();
  }, 500);
});

const course = ref<Course>({
  id: -1,
  name: "",
  description: "",
  imageUrl: "",
  price: "",
});
const error = ref<CourseError>({
  name: "",
  description: "",
  price: "",
  imageUrl: "",
});

function validateCourse() {
  let flag = true;
  if (
    course.value.name == "" ||
    course.value.name == null ||
    course.value.name == undefined
  ) {
    error.value.name = "Tên không được để trống";
    flag = false;
  }
  if (
    course.value.description == "" ||
    course.value.description == null ||
    course.value.description == undefined
  ) {
    error.value.description = "Mô tả không được để trống";
    flag = false;
  }
  if (
    course.value.price == "" ||
    course.value.price == null ||
    course.value.price == undefined
  ) {
    error.value.price = "Giá không được để trống";
    flag = false;
  }
  if (
    course.value.imageUrl == "" ||
    course.value.imageUrl == null ||
    course.value.imageUrl == undefined
  ) {
    error.value.imageUrl = "Hình ảnh không được để trống";
    flag = false;
  }
  return flag;
}

async function onAdd() {
  if (validateCourse()) {
    if (await comfirm("Xác nhận thêm khóa học")) {
      courseApi.addCourse(course.value).then(() => {
        toast.success("Thêm khóa học thành công");
        searchCourse();
        resetFormCourse();
        closeModal("CourseModalAdd");
      });
    }
  }
}

async function onUpdate() {
  if (validateCourse()) {
    if (await comfirm("Xác nhận cập nhập khóa học")) {
      courseApi.updateCourse(course.value).then(() => {
        searchCourse();
        resetFormCourse();
        closeModal("CourseModalUpdate");
      });
    }
  }
}

function resetFormCourse() {
  course.value = {
    id: -1,
    name: "",
    description: "",
    imageUrl: "",
    price: "",
  };
}
function getCourse(idCourse: number) {
  courseApi.getCourseId(idCourse).then((res) => {
    course.value = res.data;
  });
}
</script>

<template>
  <div>
    <h3><b>Quản lý khóa học</b></h3>
    <p class="breadcrum">Home / Quản lý khóa học</p>
    <div class="card mt-3">
      <div class="row p-3">
        <div class="title_table col-6" style="line-height: 42px">
          <i class="fa-solid fa-rectangle-list"></i>
          <span>Danh sách khóa học</span>
        </div>
        <div class="col-6">
          <button
            v-if="getUser()?.role == 'ADMIN'"
            @click="resetFormCourse"
            id="btn-open-courseAdd"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#CourseModalAdd">
            <i class="fa-solid fa-plus"></i> Thêm mới
          </button>
        </div>
      </div>
      <hr />
      <div class="row mx-2 mt-4">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm khóa học" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>
      </div>

      <div class="row container mx-1">
        <div
          class="col-4 p-2"
          style="cursor: pointer"
          v-for="course in courses"
          :key="course.id">
          <div class="card p-0 card-course" :title="course.name">
            <button
              v-if="getUser()?.role == 'ADMIN'"
              @click="getCourse(course.id)"
              class="btn-edit-course"
              id="btn-open-courseAdd"
              data-bs-toggle="modal"
              data-bs-target="#CourseModalUpdate">
              <i class="fa-solid fa-pencil" style="color: #1f4c7d"></i>
            </button>
            <RouterLink :to="'/course/' + course.id">
              <img
                height="200px"
                :src="course.imageUrl"
                class="card-img-top"
                alt="..." />
              <div class="card-body pt-2 px-3">
                <h5 class="card-title m-0">
                  <b>{{ course.name }}</b>
                </h5>
                <div class="my-2">
                  <em class="card-subtitle text-danger small">
                    {{
                      course.price?.toLocaleString("vi", {
                        style: "currency",
                        currency: "VND",
                      })
                    }}
                  </em>
                  <span class="badge text-bg-warning mx-2">{{
                    course.code
                  }}</span>
                  <!-- <span class="badge text-bg-info">{{ course.timeEnd }}</span> -->
                </div>
                <p class="card-text" style="white-space: pre-line">
                  {{ course.description }}
                </p>
              </div>
              <div class="my-2 d-flex justify-content-between px-3">
                <p v-if="course.lessonQuantity">
                  <i class="fa-solid fa-book-bookmark"></i>
                  &nbsp;<b>{{ course.lessonQuantity }}</b> bài học
                </p>
                <p v-else style="color: red">
                  <i class="fa-solid fa-book-bookmark"></i>
                  &nbsp;<b>{{ course.lessonQuantity }}</b> bài học
                </p>
                <p v-if="getUser()?.role != 'STUDENT'">
                  <i class="fa-solid fa-user-graduate"></i>
                  &nbsp;<b>{{ course.studentQuantity }}</b> học viên
                </p>
              </div>
            </RouterLink>
          </div>
        </div>
      </div>
      <Pageing :is-card="true" :refertion="searchCourse" :pageable="pageable" />
    </div>
    <ModalForm title="Thêm mới khóa học" id-modal="CourseModalAdd">
      <CourseForm :course="course" :error="error" />
      <template v-slot:submit>
        <button @click="onAdd" type="button" class="btn btn-info">Thêm</button>
      </template>
    </ModalForm>
    <ModalForm title="Cập nhập khóa học" id-modal="CourseModalUpdate">
      <CourseForm :course="course" :error="error" />
      <template v-slot:submit>
        <button @click="onUpdate" type="button" class="btn btn-info">
          Cập nhập
        </button>
      </template>
    </ModalForm>
  </div>
</template>
./CourseForm.vue
