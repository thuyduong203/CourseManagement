<script lang="ts" setup>
import lessonApi from "@/api/lessonApi";
import ModalForm from "@/components/ModalForm.vue";
import { getUser } from "@/util/userUtil";
import { onMounted, ref } from "vue";
import draggable from "vuedraggable";

const lessons = ref<Lesson[]>([]);

searchLesson();
</script>

<template></template>
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
