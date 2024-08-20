<script setup lang="ts">
import { ref, watch } from "vue";
import Table from "../../components/Table.vue";
import studentApi from "../../api/studentApi";
import ModalForm from "@/components/ModalForm.vue";
import StudentForm from "./StudentForm.vue";
import "./student.css";
import { closeModal } from "@/util/bootstraputil";
import { genderType } from "@/util/enumconvert";
import EyeEdit from "@/assets/icon/EyeEdit.vue";
import Pageing from "@/components/Pageing.vue";
import { getUser } from "@/util/userUtil";
import { setLoad } from "@/util/load";

const students = ref<Student[]>([]);
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
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
});
const searchText = ref<string>("");

const headers = ["Mã", "Tên", "Số điện thoại", "Giới tính"];

function searchStudent() {
  setLoad(true);
  studentApi
    .searchStudent(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
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

searchStudent();

let timer: any = null;
watch(searchText, (newValue) => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchStudent();
  }, 500);
});

function addStudent() {
  studentApi.addStudent(student.value).then(() => {
    searchStudent();
    closeModal("studentModalAdd");
    resetForm();
  });
}

function updateStudent(student: Student) {
  studentApi.updateStudent(student).then(() => {
    searchStudent();
    closeModal("studentModalUpdate");
    resetForm();
  });
}

function viewStudent(id: number) {
  studentApi.getStudentId(id).then((response) => {
    student.value = { ...response.data };
  });
}

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
</script>

<template>
  <div>
    <h3><b>Quản lý học viên</b></h3>
    <p class="breadcrum">Home / Quản lý học viên</p>
    <div class="card mt-3">
      <div class="p-3 title_table">
        <i class="fa-solid fa-rectangle-list"></i>
        <span>Danh sách học viên</span>
      </div>
      <hr />
      <div class="row mx-2 mt-4 mb-3">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm học viên" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>

        <!-- <div class="col-6">
          <button
            v-if="getUser()?.role == 'ADMIN'"
            @click="resetForm()"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#studentModalAdd">
            <i class="fa-solid fa-plus"></i> Thêm mới
          </button>
        </div> -->
      </div>

      <Table keyName="student" :headers="headers">
        <tr v-for="(student, index) in students" :key="student.id">
          <td>{{ index + 1 }}</td>
          <td>{{ student.code }}</td>
          <td>{{ student.name }}</td>
          <td>{{ student.phone }}</td>
          <td>
            {{
              student.gender != null
                ? genderType(student.gender)
                : "Không xác định"
            }}
          </td>
          <td v-if="getUser()?.role == 'ADMIN'">
            <button
              v-if="getUser()?.role == 'ADMIN'"
              @click="viewStudent(student.id)"
              data-bs-toggle="modal"
              data-bs-target="#studentModalUpdate"
              class="btn btn-info btn-sm pt-0">
              <EyeEdit class="p-0" style="color: white; font-size: 20px" />
            </button>
            <button
              @click="updateStudent({ ...student, status: 'KHONG_HOAT_DONG' })"
              v-if="student.status === 'HOAT_DONG'"
              class="btn btn-danger btn-sm py-0 m-1">
              <i
                class="fa-regular fa-trash-can"
                style="font-size: 13px; padding: 5px 3px"></i>
            </button>
            <button
              @click="updateStudent({ ...student, status: 'HOAT_DONG' })"
              v-else
              class="btn btn-warning btn-sm py-0 m-1">
              <i
                class="fa-solid fa-retweet"
                style="font-size: 15px; padding: 5px 1px"></i>
            </button>
          </td>
        </tr>
      </Table>
      <Pageing :refertion="searchStudent" :pageable="pageable" />
    </div>
    <ModalForm
      sizeModal="modal-lg"
      title="Thêm mới học viên"
      id-modal="studentModalAdd">
      <StudentForm :student="student" />
      <template v-slot:submit>
        <button type="button" class="btn btn-info" @click="addStudent">
          Lưu
        </button>
      </template>
    </ModalForm>
    <ModalForm
      sizeModal="modal-lg"
      title="Cập nhập học viên"
      id-modal="studentModalUpdate">
      <StudentForm :student="student" :show-courses="true" />
      <template v-slot:submit>
        <button
          type="button"
          class="btn btn-info"
          @click="updateStudent(student)">
          Lưu
        </button>
      </template>
    </ModalForm>
  </div>
</template>
