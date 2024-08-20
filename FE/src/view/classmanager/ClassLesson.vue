<script lang="ts" setup>
import classesApi from "@/api/classesApi";
import lessonApi from "@/api/lessonApi";
import { getUser } from "@/util/userUtil";
import { ref } from "vue";
import { useRoute } from "vue-router";
import draggable from "vuedraggable";
import Swal from "sweetalert2";
import { toast } from "vue3-toastify";
import Pageing from "@/components/Pageing.vue";
import type { Lesson } from "@/typings/lesson";
import { setLoad } from "@/util/load";

const route = useRoute();
const lessonsClass = ref<Lesson[]>([]);
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
const searchText = ref<string>("");

function searchLessonClass() {
  setLoad(true);
  if (typeof route.params.codeClass === "string") {
    lessonApi
      .searchLessonClass(
        pageable.value.pageNumber,
        pageable.value.size,
        searchText.value,
        route.params.codeClass
      )
      .then((res) => {
        setLoad(false);
        lessonsClass.value = res.data.content;

        pageable.value = {
          pageNumber: res.data.pageable.pageNumber,
          totalPages: res.data.totalPages,
          totalElements: res.data.totalElements,
          size: res.data.size,
        };
      });
  }
}

searchLessonClass();

function chageStatus(less: Lesson, status: string) {
  Swal.fire({
    title: "Xác nhận đổi trạng thái môn học",
    showDenyButton: true,
    confirmButtonText: "Xác nhận",
    denyButtonText: `Hủy`,
  }).then((result) => {
    if (result.isConfirmed) {
      classesApi.changeStatusLesson({ ...less, status: status }).then(() => {
        toast("Update thành công");
        searchLessonClass();
      });
    }
  });
}
</script>

<template>
  <div>
    <div class="row mx-2">
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
        v-model="lessonsClass"
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
                :data-bs-target="'#collapseLesson' + less.idLesson"
                aria-expanded="false"
                :aria-controls="'collapseLesson' + less.idLesson">
                <i class="fa-solid fa-angle-up fa-sm"></i> &nbsp;
                <i class="fa-solid fa-book-skull"></i> &nbsp;Môn học
                {{ index + 1 }}:
                {{ less.name }}
              </div>
              <div class="p-3 d-flex flex-row">
                <span class="badge text-bg-info"
                  ><i class="fa-solid fa-clock"></i>&nbsp;
                  {{ less.quantity }} buổi</span
                >
                &nbsp;&nbsp;
                <select
                  :disabled="getUser()?.role != 'ADMIN'"
                  @change="
                    (e) => {
                      chageStatus(less, e.target.value);
                    }
                  "
                  class="form-select form-select-sm"
                  aria-label="Default select example">
                  <option
                    :value="0"
                    :selected="less.status == 0 || less.status == null">
                    Chưa học
                  </option>
                  <option :value="1" :selected="less.status == 1">
                    Đang học
                  </option>
                  <option :value="2" :selected="less.status == 2">
                    Đã học
                  </option>
                  <option :value="3" :selected="less.status == 3">
                    Hoàn thành
                  </option>
                </select>
              </div>
            </div>
            <div class="collapse" :id="'collapseLesson' + less.idLesson">
              <div class="card card-body" style="white-space: pre-line">
                {{ less.description }}
              </div>
            </div>
          </div>
        </template>
      </draggable>
    </div>
    <Pageing :refertion="searchLessonClass" :pageable="pageable" />
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
