<script setup lang="ts">
import { onMounted, ref, watch } from "vue";
import classesApi from "../../api/classesApi";
import ModalForm from "@/components/ModalForm.vue";
import ClassesForm from "./ClassesForm.vue";
import "./classes.css";
import { closeModal } from "@/util/bootstraputil";
import courseApi from "@/api/courseApi";
import teacherApi from "@/api/teacherApi";
import Pageing from "@/components/Pageing.vue";
import { setLoad } from "@/util/load";
import {
  dayOfWeekConvert,
  daysPerWeekConvert,
  studyConvert,
} from "@/util/enumconvert";
import moment from "moment";
import { getUser } from "@/util/userUtil";
import { comfirm } from "@/util/sweetalert";

const classess = ref<Classes[]>([]);
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 6,
});
const classes = ref<Classes>({
  id: -1,
  code: "",
  status: "",
  timeStart: "",
  dayOfWeek: [],
  teacherId: "",
  courseId: "",
  study: "",
});
const error = ref<ClassesError>({
  courseId: "",
  timeStart: "",
  maxStudent: "",
  study: "",
  dayOfWeek: "",
  teacherId: "",
});

function validateClasses() {
  let flag = true;
  if (
    classes.value.courseId == "" ||
    classes.value.courseId == null ||
    classes.value.courseId == undefined
  ) {
    error.value.courseId = "Khóa không được để trống";
    flag = false;
  }
  if (
    classes.value.timeStart == "" ||
    classes.value.timeStart == null ||
    classes.value.timeStart == undefined
  ) {
    error.value.timeStart = "Thời gian bắt đầu không được để trống";
    flag = false;
  }
  if (
    classes.value.maxStudent == null ||
    classes.value.maxStudent == undefined
  ) {
    error.value.maxStudent = "Số lượng học viên không được để trống";
    flag = false;
  }
  if (
    classes.value.study == "" ||
    classes.value.study == null ||
    classes.value.study == undefined
  ) {
    error.value.study = "Ca không được để trống";
    flag = false;
  }
  if (classes.value.dayOfWeek.length == 0) {
    error.value.dayOfWeek = "Ngày học không được để trống";
    flag = false;
  }
  if (
    classes.value.teacherId == "" ||
    classes.value.teacherId == null ||
    classes.value.teacherId == undefined
  ) {
    error.value.teacherId = "Giảng viên không được để trống";
    flag = false;
  }
  return flag;
}
const searchText = ref<string>("");

function searchClasses() {
  setLoad(true);
  classesApi
    .searchClasses(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
    )
    .then((response) => {
      classess.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
    })
    .finally(() => {
      setLoad(false);
      classess.value.map((cls) => {
        if (cls.lessLearn != undefined && cls.totalLesson != undefined) {
          cls.process = ((cls.lessLearn / cls.totalLesson) * 100).toFixed(1);
        }
      });
    });
}

searchClasses();

let timer: any = null;
watch(searchText, () => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchClasses();
  }, 500);
});

async function addClasses() {
  if (validateClasses()) {
    if (await comfirm("Xác nhận thêm lớp học")) {
      classesApi.addClasses(classes.value).then(() => {
        searchClasses();
        closeModal("classesModalAdd");
        resetForm();
      });
    }
  }
}

async function updateClasses() {
  if (validateClasses()) {
    if (await comfirm("Xác nhận cập nhập lớp học")) {
      classesApi.updateClasses(classes.value).then(() => {
        searchClasses();
        closeModal("classesModalUpdate");
        resetForm();
      });
    }
  }
}

function viewClasses(id: number) {
  openModalData();
  classesApi
    .getClassesId(id)
    .then((response) => {
      classes.value = { ...response.data };
    })
    .finally(() => {
      searchTeacher();
    });
}

function resetForm() {
  classes.value = {
    id: -1,
    code: "",
    status: "",
    timeStart: "",
    dayOfWeek: [],
    teacherId: "",
    courseId: "",
    study: "",
  };
}
const courses = ref<Course[]>([]);

function openModalData() {
  resetForm();
  courseApi.getAllCourse().then((response) => {
    courses.value = response.data;
  });
}

const teachers = ref<Teacher[]>([]);
function searchTeacher() {
  teacherApi
    .getTeacherClass({
      id: classes.value.id,
      dayOfWeek: classes.value.dayOfWeek.join(","),
      study: classes.value.study,
    })
    .then((response) => {
      teachers.value = response.data;
    });
}
</script>

<template>
  <div>
    <h3><b>Quản lý lớp học</b></h3>
    <p class="breadcrum">Home / Quản lý lớp học</p>
    <div class="card mt-3">
      <div class="row p-3">
        <div class="title_table col-6" style="line-height: 42px">
          <i class="fa-solid fa-rectangle-list"></i>
          <span>Danh sách lớp học</span>
        </div>
        <div class="col-6">
          <button
            v-if="getUser()?.role == 'ADMIN'"
            @click="openModalData"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#classesModalAdd">
            <i class="fa-solid fa-plus"></i> Thêm mới
          </button>
        </div>
      </div>
      <hr />
      <div class="row mx-2 mt-4 mb-2">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm lớp học" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>
      </div>
      <div class="row mx-3">
        <div
          class="col-4 p-2 pt-0 pb-3"
          v-for="classes in classess"
          :key="classes.id">
          <RouterLink
            :to="`/class/${classes.code}/people`"
            class="card card-class"
            :title="classes.courseName">
            <button
              v-if="getUser()?.role == 'ADMIN'"
              class="btn-edit-class"
              data-bs-toggle="modal"
              data-bs-target="#classesModalUpdate"
              @click.prevent="viewClasses(classes.id)">
              <i class="fa-solid fa-gears" style="color: white"></i>
            </button>
            <div class="card-img-top" alt="bg-classes">
              <div class="d-flex justify-content-between">
                <h4 class="class-code">
                  {{ classes.code }}
                </h4>
              </div>
              <p
                class="course-name"
                style="
                  overflow: hidden;
                  text-overflow: ellipsis;
                  display: -webkit-box;
                  -webkit-line-clamp: 1;
                  -webkit-box-orient: vertical;
                ">
                {{ classes.courseName }}
              </p>
              <p :title="classes.teacherName" class="teacher">
                {{ classes.teacherName }}
              </p>
              <div class="bg-class"></div>
            </div>

            <div class="card-body">
              <div class="mb-3">
                <i class="fa-solid fa-users"></i>
                Sinh viên:
                <b>{{ `${classes.countStudent}/${classes.maxStudent}` }}</b>
              </div>
              <div class="mb-3">
                <i class="fa-regular fa-calendar-days"></i>
                Lịch học:
                <span
                  v-for="(day, index) in classes.dayOfWeek"
                  :key="'show' + day">
                  <b v-if="classes.dayOfWeek.length">{{
                    dayOfWeekConvert(day) +
                    (index < classes.dayOfWeek.length - 1 ? ", " : "")
                  }}</b>
                  <b v-else> Không có </b>
                </span>
              </div>
              <div class="mb-3">
                <i class="fa-solid fa-book"></i>
                Số môn học:
                <b>{{ classes.totalLesson }}</b>
              </div>
              <div class="mb-3">
                <i class="fa-regular fa-clock"></i>
                Giờ học:
                <b v-if="classes.study"
                  >Ca
                  {{
                    classes.study +
                    " (" +
                    (classes.study && studyConvert(classes.study)) +
                    ")"
                  }}</b
                >
                <b v-else> Không có </b>
              </div>
              <div class="mb-3">
                <i class="fa-regular fa-calendar-plus"></i>
                Ngày bắt đầu:
                <b>{{ moment(classes.timeStart).format("DD/MM/yyyy") }}</b>
              </div>
              <div class="mb-3">
                <i class="fa-regular fa-calendar-check"></i>
                Hoàn thành (dự kiến):
                <b>{{ moment(classes.timeEnd).format("DD/MM/yyyy") }}</b>
              </div>
              <div style="margin-left: 7px">
                <div class="progress" aria-valuenow="0" aria-valuemax="100">
                  <div
                    class="progress-bar progress-bar-animated progress-bar-striped"
                    :style="{
                      width: classes.process + '%',
                    }">
                    <span v-if="classes.process >= 50"
                      >{{
                        `${classes.lessLearn}/${classes.totalLesson} (${classes.process}%)`
                      }}
                    </span>
                  </div>
                  <div
                    v-if="classes.process < 50"
                    class="progress-bar"
                    style="background-color: #e9ecef; width: 100%">
                    <span
                      style="color: #0d6efd"
                      :style="{ 'margin-right': `${classes.process}%` }"
                      >{{
                        `${classes.lessLearn}/${classes.totalLesson} (${
                          classes.process || "0"
                        }%)`
                      }}</span
                    >
                  </div>
                </div>
              </div>
            </div>
          </RouterLink>
        </div>
      </div>

      <Pageing
        :is-card="true"
        :refertion="searchClasses"
        :pageable="pageable" />
    </div>
    <ModalForm
      size-modal="modal-lg"
      title="Thêm mới lớp học"
      id-modal="classesModalAdd">
      <ClassesForm
        :error="error"
        :courses="courses"
        :classes="classes"
        :teachers="teachers"
        :search-teacher="searchTeacher" />
      <template v-slot:submit>
        <button type="button" class="btn btn-info" @click="addClasses">
          Thêm
        </button>
      </template>
    </ModalForm>
    <ModalForm
      size-modal="modal-lg"
      title="Cập nhập lớp học"
      id-modal="classesModalUpdate">
      <ClassesForm
        :error="error"
        :courses="courses"
        :classes="classes"
        :teachers="teachers"
        :search-teacher="searchTeacher" />
      <template v-slot:submit>
        <button type="button" class="btn btn-info" @click="updateClasses">
          Cập nhập
        </button>
      </template>
    </ModalForm>
  </div>
</template>

./ClassesForm.vue ./ClassesForm.vue../../api/classesApi ./ClassesForm.vue
