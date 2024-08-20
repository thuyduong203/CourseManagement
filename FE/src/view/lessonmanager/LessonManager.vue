<script setup lang="ts">
import lessonApi from "@/api/lessonApi";
import ModalForm from "@/components/ModalForm.vue";
import Pageing from "@/components/Pageing.vue";
import { closeModal } from "@/util/bootstraputil";
import { ref, watch, watchEffect } from "vue";
import { useRoute } from "vue-router";
import draggable from "vuedraggable";
import LessonForm from "./LessonForm.vue";
import { getUser } from "@/util/userUtil";
import type { Lesson, LessonError } from "@/typings/lesson";
import { comfirm } from "@/util/sweetalert";
import { setLoad } from "@/util/load";

const route = useRoute();
const lessons = ref<Lesson[]>([]);

const lesson = ref<Lesson>({
  id: -1,
  name: "",
  description: "",
});

const error = ref<LessonError>({
  name: "",
  description: "",
  quantity: "",
});

function validateLesson() {
  let flag = true;
  if (
    lesson.value.name == "" ||
    lesson.value.name == null ||
    lesson.value.name == undefined
  ) {
    error.value.name = "Tên không được để trống";
    flag = false;
  }
  if (
    lesson.value.description == "" ||
    lesson.value.description == null ||
    lesson.value.description == undefined
  ) {
    error.value.description = "Mô tả không được để trống";
    flag = false;
  }
  if (lesson.value.quantity == null || lesson.value.quantity == undefined) {
    error.value.quantity = "Số lượng không được để trống";
    flag = false;
  }
  return flag;
}
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
const searchText = ref<string>("");

function searchLesson() {
  setLoad(true);
  lessonApi
    .searchLessonManager(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
    )
    .then((response) => {
      lessons.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
      setLoad(false);
    });
}

searchLesson();

let timer: any = null;
watch(searchText, () => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchLesson();
  }, 500);
});

function resetForm() {
  lesson.value = {
    id: -1,
    name: "",
    description: "",
  };
}

async function updateLesson() {
  if (validateLesson()) {
    if (await comfirm("Xác nhận thêm môn học")) {
      lessonApi.updateLesson(lesson.value).then(() => {
        closeModal("lessonModalUpdate");
        searchLesson();
        resetForm();
      });
    }
  }
}

async function addLesson() {
  if (validateLesson()) {
    if (await comfirm("Xác nhận cập nhập môn học")) {
      lessonApi
        .addLesson({ ...lesson.value, ordinal: lessons.value.length + 1 })
        .then(() => {
          searchLesson();
          closeModal("lessonModalAdd");
          resetForm();
        });
    }
  }
}
function getLesson(lessonId: string) {
  if (typeof lessonId === "string") {
    lessonApi.getLessonId(parseInt(lessonId)).then((res) => {
      lesson.value = res.data;
    });
  }
}
</script>
<template>
  <div>
    <h3><b>Quản lý môn học</b></h3>
    <p class="breadcrum">Home / Quản lý môn học</p>
    <div class="card mt-3">
      <div class="row p-3">
        <div class="title_table col-6" style="line-height: 42px">
          <i class="fa-solid fa-rectangle-list"></i>
          <span>Danh sách môn học</span>
        </div>
        <div class="col-6">
          <button
            v-if="getUser()?.role == 'ADMIN'"
            @click="resetForm"
            id="btn-open-lessonAdd"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#lessonModalAdd">
            <i class="fa-solid fa-plus"></i> Thêm mới
          </button>
        </div>
      </div>
      <hr />
      <div class="row mx-2 mt-4">
        <div class="input_search_table col-6">
          <div>
            <input v-model="searchText" placeholder="Tìm kiếm môn học" />
            <i class="fa-solid fa-magnifying-glass"></i>
          </div>
        </div>
      </div>
      <div class="mx-4 mt-1 mb-3">
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
                :class="{ firtP: index === 0 }">
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
                <div class="p-3">
                  <span class="badge text-bg-info"
                    ><i class="fa-solid fa-clock"></i>&nbsp;Thời lượng:
                    {{ less.quantity }} buổi</span
                  >
                </div>
                <div class="pt-3">
                  <button
                    v-if="getUser()?.role == 'ADMIN'"
                    @click="getLesson(less.id)"
                    class="btn-edit-lesson"
                    data-bs-toggle="modal"
                    data-bs-target="#lessonModalUpdate">
                    <i class="fa-solid fa-pencil" style="color: #1f4c7d"></i>
                  </button>
                </div>
                <div class="pt-3" style="margin-right: 15px">
                  <button
                    v-if="getUser()?.role == 'ADMIN'"
                    @click="getLesson(less.id)"
                    class="btn-edit-lesson"
                    data-bs-toggle="modal"
                    data-bs-target="#lessonModalUpdate">
                    <i class="fa-solid fa-eraser" style="color: red"></i>
                  </button>
                </div>
              </div>
              <div class="collapse" :id="'collapseLesson' + less.id">
                <div class="card card-body" style="white-space: pre-line">
                  {{ less.description }}
                </div>
              </div>
            </div>
          </template>
        </draggable>
      </div>
      <Pageing :refertion="searchLesson" :pageable="pageable" />
    </div>
    <ModalForm title="Thêm mới môn học" id-modal="lessonModalAdd">
      <LessonForm :lesson="lesson" :error="error" />
      <template v-slot:submit>
        <button @click="addLesson" type="button" class="btn btn-info">
          Thêm
        </button>
      </template>
    </ModalForm>
    <ModalForm title="Cập nhập môn học" id-modal="lessonModalUpdate">
      <LessonForm :lesson="lesson" :error="error" />
      <template v-slot:submit>
        <button @click="updateLesson" type="button" class="btn btn-info">
          Cập nhập
        </button>
      </template>
    </ModalForm>
  </div>
</template>
<style scoped>
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
  background-color: #e3f3fd;
  cursor: pointer;
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
.btn-edit-lesson {
  transition: transform 0.3s ease;
  height: 30px;
  width: 35px;
  background-color: white;
  border-radius: 5px;
  border: none !important;
}
.btn-edit-lesson:hover {
  font-size: 14px;
}
</style>
../coursemanager/LessonForm.vue../coursemanager/LessonForm.vue
