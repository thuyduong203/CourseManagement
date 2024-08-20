<script setup lang="ts">
import lessonApi from "@/api/lessonApi";
import Pageing from "@/components/Pageing.vue";
import { closeModal } from "@/util/bootstraputil";
import { ref, watch, watchEffect } from "vue";
import { useRoute } from "vue-router";
import draggable from "vuedraggable";
import courseApi from "@/api/courseApi";
import { getUser } from "@/util/userUtil";
import ModalForm from "@/components/ModalForm.vue";

import { setLoad } from "@/util/load";

const route = useRoute();
const lessonsCourse = ref<Lesson[]>([]);
const lessons = ref<Lesson[]>([]);
let courseLessonIds = [];

const lessonSelect = ref<string[]>([]);

const course = ref<Course>({
  id: -1,
  name: "",
  description: "",
});
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
const searchText = ref<string>("");

function searchLessonCourse() {
  setLoad(true);
  if (typeof route.params.idCourse === "string") {
    lessonApi
      .searchLesson(
        pageable.value.pageNumber,
        pageable.value.size,
        searchText.value,
        parseInt(route.params.idCourse)
      )
      .then((response) => {
        lessonsCourse.value = response.data.content;
        lessonSelect.value = lessonsCourse.value.map((e) => {
          return e.idLesson + "";
        });

        courseLessonIds = lessonsCourse.value.map((e) => {
          return e.id;
        });

        pageable.value = {
          pageNumber: response.data.pageable.pageNumber,
          totalPages: response.data.totalPages,
          totalElements: response.data.totalElements,
          size: response.data.size,
        };
        setLoad(false);
      });
  }
}
const pageableLesson = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
const searchTextLesson = ref<string>("");
function searchLesson() {
  setLoad(true);
  lessonApi
    .searchLessonManager(
      pageableLesson.value.pageNumber,
      pageableLesson.value.size,
      searchTextLesson.value
    )
    .then((response) => {
      lessons.value = response.data.content;
      pageableLesson.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
      setLoad(false);
    });
}
searchLessonCourse();

let timer: any = null;
watch(searchText, () => {
  clearTimeout(timer);
  timer = setTimeout(() => {
    searchLessonCourse();
  }, 500);
});

function sortOrdinal() {
  lessonsCourse.value = lessonsCourse.value.map((e, index) => {
    return { ...e, ordinal: index + 1 };
  });
}

function onAddLesson(lessonSelect: string[]) {
  if (typeof route.params.idCourse === "string") {
    const request = {
      idCourse: route.params.idCourse,
      lessonsId: lessonSelect,
      courseLessonIds: courseLessonIds,
    };
    courseApi.addCourseLesson(request).then(() => {
      searchLessonCourse();
      closeModal("lessonModalAdd");
    });
  }
}

watchEffect(() => {
  if (
    typeof route.params.idCourse === "string" &&
    parseInt(route.params.idCourse) > 0
  ) {
    courseApi.getCourseId(parseInt(route.params.idCourse)).then((res) => {
      course.value = res.data;
      document.title = course.value.name + "";
    });
  }
});
</script>
<template>
  <div>
    <h3><b>Quản lý môn học</b></h3>
    <p class="breadcrum">Home / Quản lý khóa học / {{ course.name }}</p>
    <div class="card mt-3">
      <div class="row p-3">
        <div class="title_table col-6" style="line-height: 42px">
          <i class="fa-solid fa-rectangle-list"></i>
          <span>Danh sách môn học - {{ course.code }}</span>
        </div>
        <div class="col-6">
          <button
            @click="searchLesson"
            v-if="getUser()?.role == 'ADMIN'"
            id="btn-open-lessonAdd"
            class="btn-them"
            data-bs-toggle="modal"
            data-bs-target="#lessonModalAdd">
            <i class="fa-solid fa-plus"></i> Chọn môn học
          </button>

          <ModalForm
            size-modal="modal-lg"
            title="Chọn môn học"
            id-modal="lessonModalAdd">
            <div>
              <div class="row mt-4">
                <div class="input_search_table col-6">
                  <div>
                    <input
                      v-model="searchTextLesson"
                      placeholder="Tìm kiếm môn học" />
                    <i class="fa-solid fa-magnifying-glass"></i>
                  </div>
                </div>
              </div>
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
                        select: lessonSelect.includes(less.id),
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
                          ><i class="fa-solid fa-clock"></i>&nbsp;Thời lượng:
                          {{ less.quantity }} buổi</span
                        >
                      </div>
                      <div class="p-3">
                        <span class="badge text-bg-primary p-0">
                          <input
                            v-model="lessonSelect"
                            style="display: none"
                            class="form-check-input m-0"
                            type="checkbox"
                            :value="less.id"
                            :id="less.id" />
                          &nbsp;
                          <label
                            style="cursor: pointer"
                            class="form-check-label p-2"
                            :for="less.id">
                            Chọn
                          </label></span
                        >
                      </div>
                      <div class="pt-3"></div>
                    </div>
                    <div class="collapse" :id="'collapseLesson' + less.id">
                      <div class="card card-body" style="white-space: pre-line">
                        {{ less.description }}
                      </div>
                    </div>
                  </div>
                </template>
              </draggable>
              <Pageing :refertion="searchLesson" :pageable="pageableLesson" />
            </div>
            <template v-slot:submit>
              <button
                @click="onAddLesson(lessonSelect)"
                type="button"
                class="btn btn-info">
                Xác nhận
              </button>
            </template>
          </ModalForm>
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
          :disabled="getUser()?.role !== 'ADMIN'"
          v-model="lessonsCourse"
          tag="div"
          class="card card-lesson"
          item-key="id">
          <template #item="{ element: less, index }">
            <div>
              <div
                class="d-inline-flex gap-1 justify-content-between select"
                :class="{ firtP: index === 0 }">
                <div
                  :title="less.name"
                  class="collapsed btn-collap"
                  data-bs-toggle="collapse"
                  :data-bs-target="'#collapseLesson' + less.id"
                  aria-expanded="false"
                  :aria-controls="'collapseLesson' + less.id">
                  <i class="fa-solid fa-angle-up fa-sm"></i> &nbsp;
                  <i class="fa-solid fa-book-skull"></i> &nbsp;Môn học
                  {{ index + 1 }}:
                  {{ less.name }}
                </div>
                <div class="p-3">
                  <span class="badge text-bg-info"
                    ><i class="fa-solid fa-clock"></i>&nbsp;Thời lượng:
                    {{ less.quantity }} buổi</span
                  >
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
      <Pageing :refertion="searchLessonCourse" :pageable="pageable" />
    </div>
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
