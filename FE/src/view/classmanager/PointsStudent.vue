<script setup lang="ts">
import Table from "@/components/Table.vue";
import { ref } from "vue";
import { useRoute } from "vue-router";
import classesApi from "../../api/classesApi";

const headers = ["Môn học", "Điểm", "Sổ buổi", "Trạng thái"];

const studentClass = ref<StudentClass[]>([]);
const route = useRoute();
const lessonClass = ref<LessonClass[]>([]);
function getLessonByClass() {
  if (typeof route.params.codeClass == "string") {
    classesApi
      .getLessonCodeClass(route.params.codeClass)
      .then((res) => {
        lessonClass.value = res.data;
      })
      .finally(() => {
        classesApi
          .getPointClassStudentCode("", route.params.codeClass)
          .then((res) => {
            studentClass.value = res.data;
          });
      });
  }
}
getLessonByClass();
</script>

<template>
  <div style="border: 1px solid #dee2e6">
    <Table keyName="student" :headers="headers">
      <tr v-for="(l, index) in lessonClass" :key="l.id">
        <td>{{ index + 1 }}</td>
        <td>{{ l.name }}</td>
        <td>{{ studentClass[0].points[l.id] }}</td>
        <td>{{ l.quantity }}</td>
        <td>
          {{
            l.status
              ? l.status == 0
                ? "Chưa học"
                : l.status == 1
                ? "Đang học"
                : l.status == 2
                ? "Đã học"
                : "Hoàn thành"
              : "Chưa học"
          }}
        </td>
      </tr>
    </Table>
  </div>
</template>
<style scoped></style>
