<script setup lang="ts">
import { getUser } from "@/util/userUtil";
import { useRoute } from "vue-router";

const route = useRoute();

if (typeof route.params.codeClass == "string") {
  document.title = route.params.codeClass;
}
</script>

<template>
  <div>
    <h3><b>Chi tiết lớp học</b></h3>
    <p class="breadcrum">
      Home / Quản lý lớp học / {{ route.params.codeClass }}
    </p>
    <div class="card mt-3">
      <ul class="nav nav-underline" style="border-bottom: 1px solid #dee2e6">
        <!-- <li class="nav-item" style="margin-left: 20px">
          <RouterLink
            :to="`/class/${route.params.codeClass}`"
            class="nav-link"
            aria-current="page"
            >Bảng tin</RouterLink
          >
        </li> -->
        <li class="nav-item">
          <RouterLink
            :to="`/class/${route.params.codeClass}/people`"
            class="nav-link"
            aria-current="page"
            >Mọi người</RouterLink
          >
        </li>
        <li class="nav-item" v-if="getUser()?.role != 'STUDENT'">
          <RouterLink
            :to="`/class/${route.params.codeClass}/point`"
            class="nav-link"
            aria-current="page"
            >Điểm</RouterLink
          >
        </li>
        <li class="nav-item" v-else>
          <RouterLink
            :to="`/class/${route.params.codeClass}/point/student`"
            class="nav-link"
            aria-current="page"
            >Điểm</RouterLink
          >
        </li>
        <!-- <li class="nav-item">
          <RouterLink
            :to="`/class/${route.params.codeClass}/schedule`"
            class="nav-link"
            aria-current="page"
            >Lịch học</RouterLink
          >
        </li> -->
        <li class="nav-item">
          <RouterLink
            :to="`/class/${route.params.codeClass}/lesson`"
            class="nav-link"
            aria-current="page"
            >Môn học</RouterLink
          >
        </li>
      </ul>
      <div class="card-body">
        <router-view style="min-height: 60vh"></router-view>
      </div>
    </div>
  </div>
</template>
<style scoped>
.nav-item a {
  color: gray !important;
  border: none;
  padding: 15px;
}
.nav-item a:hover {
  background-color: rgba(170, 170, 170, 0.1);
}
.nav-item .router-link-exact-active {
  color: blue !important;
  border-bottom: 3px solid blue;
}
.nav-item {
  text-align: center;
}
</style>

./ClassesForm.vue ./ClassesForm.vue../../api/classesApi ./ClassesForm.vue
