<script setup lang="ts">
import { ref, watch } from "vue";
import Table from "../../components/Table.vue";
import teacherApi from "../../api/teacherApi";
import ModalForm from "@/components/ModalForm.vue";
import TeacherForm from "./TeacherForm.vue";
import "./teacher.css";
import { closeModal } from "@/util/bootstraputil";
import { genderType } from "@/util/enumconvert";
import EyeEdit from "@/assets/icon/EyeEdit.vue";
import Pageing from "@/components/Pageing.vue";
import { getUser } from "@/util/userUtil";
import { setLoad } from "@/util/load";
import type { Teacher, TeacherEror } from "@/typings/teacher";
import type { Pageable } from "@/typings/pageable";
import { comfirm } from "@/util/sweetalert";

const teachers = ref<Teacher[]>([]);

const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
const teacher = ref<Teacher>({
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
const error = ref<TeacherEror>({
  name: "",
  birthDate: "",
  email: "",
  phone: "",
  gender: "",
  address: "",
});
const searchText = ref<string>("");

const headers = ["Mã", "Tên", "Số điện thoại", "Giới tính", "Số lượng lớp"];

function searchTeacher() {
  setLoad(true);
  teacherApi
    .searchTeacher(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
    )
    .then((response) => {
      teachers.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
      setLoad(false);
    });
}

searchTeacher();

let timer: any = null;
watch(searchText, (newValue, oldValue) => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchTeacher();
  }, 500);
});

function validateTeacher() {
  let flag = true;
  if (
    teacher.value.name == "" ||
    teacher.value.name == null ||
    teacher.value.name == undefined
  ) {
    error.value.name = "Tên không được để trống";
    flag = false;
  }
  if (
    teacher.value.birthDate == "" ||
    teacher.value.birthDate == null ||
    teacher.value.birthDate == undefined
  ) {
    error.value.birthDate = "Ngày sinh không được để trống";
    flag = false;
  }
  if (
    teacher.value.email == "" ||
    teacher.value.email == null ||
    teacher.value.email == undefined
  ) {
    error.value.email = "Email không được để trống";
    flag = false;
  }
  if (
    teacher.value.phone == "" ||
    teacher.value.phone == null ||
    teacher.value.phone == undefined
  ) {
    error.value.phone = "Số điện thoại không được để trống";
    flag = false;
  }
  if (
    teacher.value.gender == "" ||
    teacher.value.gender == null ||
    teacher.value.gender == undefined
  ) {
    error.value.gender = "Giới tính không được để trống";
    flag = false;
  }
  if (
    teacher.value.address == "" ||
    teacher.value.address == null ||
    teacher.value.address == undefined
  ) {
    error.value.address = "Địa chỉ không được để trống";
    flag = false;
  }
  return flag;
}

async function addTeacher() {
  if (validateTeacher()) {
    if (await comfirm("Xác nhận thêm giảng viên")) {
      teacherApi.addTeacher(teacher.value).then(() => {
        searchTeacher();
        closeModal("teacherModalAdd");
        resetForm();
      });
    }
  }
}

async function updateTeacher(teacher: Teacher) {
  if (validateTeacher()) {
    if (await comfirm("Xác nhận cập nhập giảng viên")) {
      teacherApi.updateTeacher(teacher).then(() => {
        searchTeacher();
        closeModal("teacherModalUpdate");
        resetForm();
      });
    }
  }
}

function viewTeacher(id: number) {
  teacherApi.getTeacherId(id).then((response) => {
    teacher.value = { ...response.data };
  });
}

function resetForm() {
  teacher.value = {
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
    <h3><b>Quản lý giảng viên</b></h3>
    <p class="breadcrum">Home / Quản lý giảng viên</p>
    <div class="card mt-3">
      <div class="p-3 title_table">
        <i class="fa-solid fa-rectangle-list"></i>
        <span>Danh sách giảng viên</span>
      </div>
      <hr />
      <div class="row mx-2 mt-4 mb-3">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm giảng viên" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>

        <div class="col-6">
          <button
            v-if="getUser()?.role == 'ADMIN'"
            @click="resetForm()"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#teacherModalAdd">
            <i class="fa-solid fa-plus"></i> Thêm mới
          </button>
        </div>
      </div>

      <Table keyName="teacher" :headers="headers">
        <tr v-for="(teacher, index) in teachers" :key="teacher.id">
          <td>{{ index + 1 }}</td>
          <td>{{ teacher.code }}</td>
          <td>{{ teacher.name }}</td>
          <td>{{ teacher.phone }}</td>
          <td>{{ genderType(teacher.gender) }}</td>
          <td>{{ teacher.totalClass }}</td>
          <td v-if="getUser()?.role == 'ADMIN'">
            <button
              @click="viewTeacher(teacher.id)"
              data-bs-toggle="modal"
              data-bs-target="#teacherModalUpdate"
              class="btn btn-info btn-sm pt-0">
              <EyeEdit class="p-0" style="color: white; font-size: 20px" />
            </button>
            <button
              @click="updateTeacher({ ...teacher, status: 'KHONG_HOAT_DONG' })"
              v-if="teacher.status === 'HOAT_DONG'"
              class="btn btn-danger btn-sm py-0 m-1">
              <i
                class="fa-regular fa-trash-can"
                style="font-size: 15px; padding: 5px 3px"></i>
            </button>
            <button
              @click="updateTeacher({ ...teacher, status: 'HOAT_DONG' })"
              v-else
              class="btn btn-warning btn-sm py-0 m-1">
              <i
                class="fa-solid fa-retweet"
                style="font-size: 15px; padding: 4px 0px"></i>
            </button>
          </td>
        </tr>
      </Table>
      <Pageing :refertion="searchTeacher" :pageable="pageable" />
    </div>
    <ModalForm
      sizeModal="modal-lg"
      title="Thêm mới giảng viên"
      id-modal="teacherModalAdd">
      <TeacherForm :error="error" :teacher="teacher" />
      <template v-slot:submit>
        <button type="button" class="btn btn-info" @click="addTeacher">
          Lưu
        </button>
      </template>
    </ModalForm>
    <ModalForm
      sizeModal="modal-lg"
      title="Cập nhập giảng viên"
      id-modal="teacherModalUpdate">
      <TeacherForm :error="error" :teacher="teacher" />
      <template v-slot:submit>
        <button
          type="button"
          class="btn btn-info"
          @click="updateTeacher(teacher)">
          Lưu
        </button>
      </template>
    </ModalForm>
  </div>
</template>
./TeacherForm.vue
