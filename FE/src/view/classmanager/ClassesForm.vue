<script lang="ts" setup>
import teacherApi from "@/api/teacherApi";
import { dayOfWeek } from "@/util/contanst";
import { dayOfWeekConvert, studyConvert } from "@/util/enumconvert";
import { ref } from "vue";

const props = defineProps({
  classes: {
    type: Object,
    required: true,
  },
  teachers: {
    type: Array<Teacher>,
    required: true,
  },
  courses: Array<Course>,
  searchTeacher: Function,
  error: {
    type: Object,
    required: true,
  },
});
const clearError = (field: string) => {
  props.error[field] = "";
};
</script>

<template>
  <form class="row">
    <div class="form-group pb-2 col-6">
      <label for="course" class="form-label m-0"
        >Khóa học<span class="required">*</span></label
      >
      <select
        v-model="classes.courseId"
        @change="clearError('courseId')"
        id="course"
        class="form-select mt-1"
        :class="{ 'is-invalid': error.courseId }"
        required>
        <option value="" selected disabled>Chọn khóa học</option>
        <option v-for="course in courses" :value="course.id" :key="course.id">
          {{ `${course.code} - ${course.name}` }}
        </option>
      </select>
      <div class="invalid-feedback">{{ error.courseId }}</div>
    </div>

    <div class="form-group pb-2 col-6">
      <label for="timeStart"
        >Thời gian bắt đầu<span class="required">*</span></label
      >
      <input
        @input="clearError('timeStart')"
        v-model="classes.timeStart"
        type="date"
        class="form-control mt-1"
        :class="{ 'is-invalid': error.timeStart }"
        id="timeStart"
        required />
      <div class="invalid-feedback">{{ error.timeStart }}</div>
    </div>

    <div class="form-group pb-2 col-6">
      <label for="maxStudent"
        >Số lượng sinh viên<span class="required">*</span></label
      >
      <input
        @input="clearError('maxStudent')"
        v-model="classes.maxStudent"
        type="number"
        class="form-control mt-1"
        :class="{ 'is-invalid': error.maxStudent }"
        id="maxStudent"
        placeholder="Số lượng sinh viên tối đa"
        required />
      <div class="invalid-feedback">{{ error.maxStudent }}</div>
    </div>

    <div class="form-group pb-2 col-6">
      <label for="time">Chọn ca học<span class="required">*</span></label>
      <select
        @change="clearError('study')"
        v-model="classes.study"
        id="time"
        class="form-select mt-1"
        :class="{ 'is-invalid': error.study }"
        required>
        <option value="" selected disabled hidden>Chọn ca học</option>
        <option v-for="study in 6" :value="study" :key="study">
          Ca {{ study }} ({{ studyConvert(study) }})
        </option>
      </select>
      <div class="invalid-feedback">{{ error.study }}</div>
    </div>

    <div class="form-group pb-2 col-6">
      <label>Chọn lịch học<span class="required">*</span></label>
      <div class="d-flex justify-content-between select-day mt-1">
        <div v-for="day in dayOfWeek" :key="day">
          <input
            type="checkbox"
            :value="day"
            :id="day"
            v-model="classes.dayOfWeek" />
          <label
            @click="clearError('dayOfWeek')"
            class="badge text-dark"
            :for="day"
            :class="{ 'text-bg-info': classes.dayOfWeek.includes(day) }"
            >{{
              (day != "SUNDAY" ? "Thứ " : "") + dayOfWeekConvert(day)
            }}</label
          >
        </div>
      </div>
      <div
        class="error"
        style="
          width: 100%;
          margin-top: 0.25rem;
          font-size: 0.875em;
          color: #dc3545;
        "
        v-show="error.dayOfWeek">
        Lịch học không được để trống
      </div>
    </div>

    <div class="form-group pb-2 col-6">
      <label for="quantity"
        >Giảng viên hướng dẫn<span class="required">*</span></label
      >
      <select
        @change="clearError('teacherId')"
        :disabled="!classes.study || !classes.dayOfWeek.length"
        @click="searchTeacher"
        v-model="classes.teacherId"
        id="teacher"
        class="form-select mt-1"
        :class="{
          'is-invalid':
            error.teacherId && classes.study && classes.dayOfWeek.length,
        }"
        required>
        <option value="" selected disabled hidden>
          Chọn giảng viên hướng dẫn
        </option>
        <option
          v-for="teacher in teachers"
          :key="teacher.id"
          :value="teacher.id">
          {{ `${teacher.code} - ${teacher.name}` }}
        </option>
      </select>
      <div
        class="invalid-feedback"
        v-show="classes.study && classes.dayOfWeek.length">
        {{ error.teacherId }}
      </div>
    </div>
  </form>
</template>
<style scoped>
.badge {
  border: 1px solid #dee2e6;
  cursor: pointer;
  line-height: 28px;
}
.select-day input {
  display: none;
}
</style>
