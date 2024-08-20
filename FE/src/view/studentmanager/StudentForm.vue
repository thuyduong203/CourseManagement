<script lang="ts" setup>
import courseApi from "@/api/courseApi";

const props = defineProps({
  student: {
    type: Object,
    required: true,
  },
  courses: Array<Course>,
  showCourses: {
    type: Boolean,
    required: false,
  },
});
</script>

<template>
  <form class="row">
    <div class="form-group col-md-6">
      <label for="name">Họ và tên<span class="required">*</span></label>
      <input
        v-model="student.name"
        type="text"
        class="form-control"
        id="name"
        placeholder="Nhập họ và tên" />
    </div>
    <div class="form-group col-md-6">
      <label for="birthDate">Ngày sinh<span class="required">*</span></label>
      <input
        v-model="student.birthDate"
        type="date"
        class="form-control"
        id="birthDate"
        placeholder="Nhập ngày sinh" />
    </div>
    <div class="form-group col-md-6 my-3">
      <label for="email">Email học viên<span class="required">*</span></label>
      <input
        v-model="student.email"
        type="text"
        class="form-control"
        id="email"
        placeholder="Nhập email giảng viên" />
    </div>
    <div class="form-group col-md-6 my-3">
      <label for="phone">Số điện thoại<span class="required">*</span></label>
      <input
        v-model="student.phone"
        type="text"
        class="form-control"
        id="phone"
        placeholder="Nhập số điện thoại" />
    </div>
    <div class="form-group col-md-6" v-if="!showCourses">
      <label for="address">Khóa học<span class="required">*</span></label>
      <select v-model="student.courseId" id="course" class="form-select mt-1">
        <option :value="undefined" selected style="display: none">
          Chọn khóa học
        </option>
        <option v-for="course in courses" :value="course.id" :key="course.id">
          {{ `${course.code} - ${course.name}` }}
        </option>
      </select>
    </div>

    <div class="form-group col-md-6">
      <p>Giới tính<span class="required">*</span></p>
      <div class="form-check form-check-inline mt-2">
        <input
          :checked="student.gender == 'MALE'"
          v-model="student.gender"
          class="form-check-input"
          type="radio"
          name="gender"
          id="gender-man"
          value="MALE" />
        <label class="form-check-label mr-3" for="gender-man">Nam</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          :checked="student.gender == 'FEMALE'"
          v-model="student.gender"
          class="form-check-input"
          type="radio"
          name="gender"
          id="gender-women"
          value="FEMALE" />
        <label class="form-check-label mr-3" for="gender-women">Nữ</label>
      </div>
    </div>
  </form>
</template>
