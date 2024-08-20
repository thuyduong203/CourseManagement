<script setup lang="ts">
import classesApi from "@/api/classesApi";
import { daysPerWeekConvert } from "@/util/enumconvert";
import moment from "moment";
import { ref } from "vue";
import { useRoute } from "vue-router";

const studentClass = ref<StudentClass[]>([]);
const lessonClass = ref<LessonClass[]>([]);
const route = useRoute();
const searchText = ref<string>("");
const today: Date = new Date();
const arrEnable = ref<number[]>([]);

function getStudentByClass() {
  if (typeof route.params.codeClass == "string") {
    classesApi
      .getPointClassStudentCode(searchText.value, route.params.codeClass)
      .then((res) => {
        studentClass.value = res.data;
      })
      .finally(() => {
        if (typeof route.params.codeClass == "string") {
          classesApi
            .getLessonCodeClass(route.params.codeClass)
            .then((res) => {
              lessonClass.value = res.data;
            })
            .finally(() => {
              lessonClass.value.forEach((e) => {
                if (e.status > 0 && e.status < 3) {
                  arrEnable.value.push(e.id);
                }
              });
            });
        }
      });
  }
}

getStudentByClass();

function addPoint(studentId: number, lessonId: string, point: number) {
  if (typeof (route.params.codeClass == "string")) {
    let code: string = route.params.codeClass + "";
    let addPointRequest: AddPoint = {
      codeClass: code,
      studentId: studentId,
      lessonId: lessonId,
      point: point,
    };
    classesApi.addPointClass(addPointRequest).then(() => {
      getStudentByClass();
    });
  }
}
</script>

<template>
  <div style="border: 1px solid #dee2e6">
    <table class="table table-bordered m-0">
      <thead style="border: 1px solid #dee2e6">
        <tr
          style="
            position: -webkit-sticky;
            position: sticky;
            top: 0;
            z-index: 2;
          ">
          <th
            style="
              width: 215px;
              padding: 0;
              line-height: 99px;
              position: sticky;
              left: 0;
            ">
            <p
              style="
                width: 215px;
                border-bottom: 1px solid #dee2e6;
                border-right: 1px solid #dee2e6;
                border-radius: 0px;
              ">
              Sinh viên
            </p>
          </th>
          <th
            :title="lesson.name"
            class="lessName pb-0 px-0"
            style="height: 55px; width: 150px; height: 100px"
            scope="col"
            v-for="lesson in lessonClass"
            :key="lesson.id"
            :class="{ leesonDone: !arrEnable.includes(lesson.id) }">
            <div
              style="
                border-bottom: 1px solid #dee2e6;
                border-radius: 0px;
                height: 82px;
              ">
              <p class="p-1 pt-0" style="height: 60px; margin-top: 10px">
                {{ lesson.name }}
              </p>
            </div>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in studentClass" :key="student.idStudent + ''">
          <td
            style="
              width: 215px;
              position: -webkit-sticky;
              position: sticky;
              left: 0;
              height: 60px;
            "
            scope="row"
            class="student"
            :title="student.codeStudent + ' - ' + student.nameStudent">
            <p>
              <img src="../../assets/image/avatar.jpg" />&nbsp;&nbsp;
              {{ student.nameStudent }}
            </p>
          </td>
          <td
            scope="col"
            v-for="lesson in lessonClass"
            :key="lesson.id"
            style="height: 61px; min-width: 100px; padding: 0">
            <input
              v-if="arrEnable.includes(lesson.id)"
              @keyup.enter="
                if (lesson.id && student.points) {
                  addPoint(
                    student.idStudent,
                    lesson.id + '',
                    student.points[lesson.id]
                  );
                }
              "
              v-model="student.points[lesson.id]"
              class="input-point" />
            <input
              v-else
              disabled
              class="input-point leesonDone"
              v-model="student.points[lesson.id]" />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<style scoped>
.leesonDone {
  background-color: rgb(245, 245, 245) !important;
}
table {
  font-size: 14px;
  display: block;
  overflow: auto;
  text-align: center;
  height: 485px;
  border-radius: 10px;
}
*:not(input, th) {
  border-radius: 10px;
}

.input-point {
  border: none !important;
  outline: none !important;
  height: 61px;
  width: 100%;
  text-align: center;
  font-size: 16px;
}
.lessName p {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.lessName {
  vertical-align: middle;
}
table::-webkit-scrollbar {
  height: 10px;
}
table::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-clip: padding-box;
}
img {
  border-radius: 50%;
  max-height: 30px;
  max-width: 30px;
}
.student {
  width: 200px;
  line-height: 62px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
  padding: 0px;
  padding-left: 10px;
  border-radius: 0px;
}
.student p {
  border-right: 1px solid #dee2e6;
  border-radius: 0px;
}
</style>
