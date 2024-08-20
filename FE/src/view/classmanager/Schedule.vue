<script lang="ts" setup>
import Table from "@/components/Table.vue";
import { ref } from "vue";
import { useRoute } from "vue-router";
import classesApi from "../../api/classesApi";
import moment, { Moment } from "moment";
import { getUser } from "@/util/userUtil";
import { dayOfWeekConvert, studyConvert } from "@/util/enumconvert";

const headers = ["STT", "Ngày học", "Thời gian", "Giờ học", "Trạng thái"];
const route = useRoute();
interface Schedule {
  dayOfWeek: string;
  id?: number;
  study?: number;
  time: string;
  status?: string;
}
interface ClassTime {
  id: string;
  dayOfWeek: string[];
  lessLearn: number;
  study?: number;
  quantity: number;
}

const schedules = ref<Schedule[]>([]);
const classTime = ref<ClassTime>();
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 5,
});
function searchSchedule() {
  if (typeof route.params.codeClass == "string") {
    classesApi
      .getCheduleCodeClass(
        pageable.value.pageNumber,
        pageable.value.size,
        route.params.codeClass
      )
      .then((response) => {
        classTime.value = response.data.timeStudy;
        // pageable.value = {
        //   pageNumber: 1,
        //   totalPages: response.data.schedule.totalPages,
        //   totalElements: 5,
        //   size: response.data.schedule.size,
        // };
      })
      .finally(() => {
        genSchedule();
      });
  }
}

searchSchedule();

function genSchedule() {
  const quantity = classTime.value.quantity;
  const lessLearn = classTime.value.lessLearn;
  let totalLesson = quantity - lessLearn;
  let timeStart = moment();
  while (totalLesson > 0) {
    for (const day of classTime.value?.dayOfWeek) {
      const schedule = {
        dayOfWeek: day,
        time: timeStart.day(day).startOf("day").toString(),
        study: classTime.value?.study,
      };
      const today = moment().startOf("day");
      if (moment(timeStart).day(day).startOf("day").isSameOrAfter(today)) {
        schedules.value.push(schedule);
        totalLesson--;
        if (totalLesson <= 0) {
          break;
        }
      }
    }
    timeStart = timeStart.add(1, "weeks");
  }
}

const fromDate = ref<Moment>(moment());
const toDate = ref<Moment>(moment().add(1, "weeks"));
</script>

<template>
  <div>
    <div class="mx-4 mb-5">
      <p>Thời gian</p>
      <div class="input-group">
        <input
          @change="
            (e) => {
              fromDate = moment(e.target.value);
            }
          "
          :value="fromDate.format('YYYY-MM-DD')"
          type="date"
          class="form-control"
          placeholder="Ngày bắt đầu"
          aria-label="From Date" />
        <span class="input-group-text"
          ><i class="fa-solid fa-right-long"></i
        ></span>
        <input
          @change="
            (e) => {
              toDate = moment(e.target.value);
            }
          "
          :value="toDate.format('YYYY-MM-DD')"
          type="date"
          class="form-control"
          placeholder="Ngày kết thúc"
          aria-label="to Date" />
      </div>
      <em style="font-size: 13px; color: gray; margin-left: 5px"
        >Lựa chọn thời gian để hiện thị chi tiết lịch học</em
      >
    </div>
    <p class="mx-4 mb-2">
      <i class="fa-regular fa-calendar-days"></i>&nbsp;Lịch học dự kiến
    </p>
    <Table :action="true" keyName="schedule" :headers="headers">
      <tr
        v-for="(schedule, index) in schedules.filter(
          (e) => moment(e.time) >= fromDate && moment(e.time) < toDate
        )"
        :key="schedule.id">
        <td>{{ index + 1 }}</td>
        <td>
          {{
            schedule.dayOfWeek != "SUNDAY"
              ? "Thứ " + dayOfWeekConvert(schedule.dayOfWeek)
              : "Chủ nhật"
          }}
        </td>
        <td>{{ moment(schedule.time).format("DD/MM/YYYY") }}</td>
        <td>{{ studyConvert(schedule.study) }}</td>
        <td v-if="getUser()?.role == 'ADMIN'">
          <span class="badge text-bg-warning"> Lịch Dự kiến</span>
        </td>
      </tr>
    </Table>
  </div>
</template>

<style scoped></style>
