<script setup lang="ts">
import classesApi from "@/api/classesApi";
import studentApi from "@/api/studentApi";
import ModalForm from "@/components/ModalForm.vue";
import Pageing from "@/components/Pageing.vue";
import Table from "@/components/Table.vue";
import { closeModal } from "@/util/bootstraputil";
import { getUser } from "@/util/userUtil";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

import { setLoad } from "@/util/load";

const route = useRoute();

const students = ref<Student[]>([]);
const studentClass = ref<StudentClass[]>([]);
const studentClassSelect = ref<StudentClass[]>([]);
const teacherName = ref<string>();

const searchText = ref<string>("");

const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
function searchStudent() {
  setLoad(true);
  studentApi
    .searchStudentCourse(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value,
      route.params.codeClass
    )
    .then((response) => {
      students.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };

      setLoad(false);
    });
}

function getStudentByClass() {
  if (typeof route.params.codeClass == "string") {
    classesApi
      .getClassStudentCode(searchText.value, route.params.codeClass)
      .then((res) => {
        studentClassSelect.value = res.data.students;
        studentClass.value = res.data.students;
        teacherName.value = res.data.teacher;
      });
  }
}

function addStudent() {
  if (typeof route.params.codeClass == "string") {
    const idClass: string = route.params.codeClass;
    const listIdStudent: Array<number> = studentClassSelect.value.map(
      (e) => e.idStudent
    );
    classesApi.addStudentClass(idClass, listIdStudent).then(() => {
      getStudentByClass();
      closeModal("studentModalAdd");
    });
  }
}

function isSelected(id: number) {
  return studentClassSelect.value.some((student) => student.idStudent === id);
}
function toggleSelection(student: Student) {
  const index = studentClassSelect.value.findIndex(
    (s) => s.idStudent === student.id
  );
  if (index !== -1) {
    studentClassSelect.value.splice(index, 1);
  } else {
    studentClassSelect.value.push({
      idStudent: student.id,
      nameStudent: student.name,
      codeStudent: student.code,
    });
  }
}
function toggleSelectAll() {
  if (studentClassSelect.value.length === students.value.length) {
    studentClassSelect.value = [];
  } else {
    studentClassSelect.value = students.value.map((std) => ({
      idStudent: std.id,
      nameStudent: std.name,
      codeStudent: std.code,
    }));
  }
}

onMounted(() => {
  getStudentByClass();
});
if (typeof route.params.codeClass == "string") {
  document.title = route.params.codeClass;
}
</script>

<template>
  <div class="content-people">
    <div class="title">
      <h2>Giáo viên</h2>
    </div>
    <div class="user">
      <img aria-hidden="true" src="../../assets/image/avatar.jpg" />&nbsp;&nbsp;
      {{ teacherName }}
    </div>
    <div class="title mt-5">
      <div class="d-flex justify-content-between">
        <h2>Học viên</h2>
        <p class="add-student">
          {{ studentClass.length }} sinh viên &nbsp;<i
            style="cursor: pointer"
            v-if="getUser()?.role !== 'STUDENT'"
            @click="searchStudent"
            class="fa-solid fa-user-plus student-add"
            data-bs-toggle="modal"
            data-bs-target="#studentModalAdd"></i>
        </p>
      </div>
    </div>
    <div class="user">
      <div
        class="d-flex justify-content-between mt-3"
        v-for="student in studentClass"
        :key="'student' + student.idStudent">
        <div>
          <img src="../../assets/image/avatar.jpg" />&nbsp;&nbsp;
          {{ student.nameStudent }}
        </div>
        <i
          v-if="getUser()?.role !== 'STUDENT'"
          @click="searchStudent()"
          class="fa-solid fa-eraser"
          style="line-height: 40px"></i>
      </div>
    </div>
    <ModalForm
      sizeModal="modal-lg"
      title="Thêm học viên"
      id-modal="studentModalAdd">
      <div class="px-4 text-center">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm học viên" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr>
              <th style="width: 10px">
                <input
                  :checked="
                    studentClassSelect.length === students.length &&
                    students.length > 0
                  "
                  @click="toggleSelectAll"
                  class="form-check-input"
                  type="checkbox"
                  id="flexCheckDefault" />
              </th>
              <th>Mã</th>
              <th>Tên</th>
              <th>Số điện thoại</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="student in students" :key="student.id">
              <td style="width: 10px">
                <input
                  class="form-check-input"
                  type="checkbox"
                  :checked="isSelected(student.id)"
                  @click="toggleSelection(student)" />
              </td>
              <td>{{ student.code }}</td>
              <td>{{ student.name }}</td>
              <td>{{ student.phone }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <Pageing :refertion="searchStudent" :pageable="pageable" />
      <template v-slot:submit>
        <button type="button" class="btn btn-info" @click="addStudent">
          Thêm
        </button>
      </template>
    </ModalForm>
  </div>
</template>
<style scoped>
.content-people {
  margin-left: 150px;
  margin-right: 150px;
}
.fa-eraser {
  color: red;
}
.title {
  border-bottom: 1px solid #2a76fa;
  color: #2a76fa;
  margin-bottom: 15px;
  padding-left: 15px;
  padding-right: 15px;
}
.title > div i,
h2 {
  line-height: 40px;
  margin-bottom: 10px;
}
.user {
  padding-left: 15px;
  padding-right: 15px;
}
.user img {
  border-radius: 50%;
  max-height: 40px;
  max-width: 40px;
}
.student-add {
  text-align: center;
  height: 40px;
  width: 40px;
  border-radius: 50%;
}
.student-add:hover {
  background-color: rgba(192, 192, 192, 0.3);
}
</style>
