<script lang="ts" setup>
const props = defineProps({
  isCard: {
    type: Boolean,
    required: false,
  },
  pageable: {
    type: Object,
    required: true,
  },
  refertion: {
    type: Function,
    required: true,
  },
});
</script>

<template>
  <div
    class="d-flex justify-content-between align-items-center flex-wrap mx-4 my-0 mt-1">
    <ul class="pagination">
      <li class="page-item">
        <button
          class="page-link"
          @click="
            pageable.pageNumber = pageable.pageNumber - 1;
            console.log(pageable);
            refertion();
          ">
          <i class="fa-solid fa-caret-right fa-rotate-180"></i>
        </button>
      </li>
      <li
        class="page-item"
        v-for="page in pageable.totalPages"
        :key="'coursePage' + page">
        <button
          @click="
            pageable.pageNumber = page - 1;
            refertion();
          "
          class="page-link"
          :class="{ active: pageable.pageNumber + 1 === page }">
          {{ page }}
        </button>
      </li>
      <li class="page-item">
        <button
          class="page-link"
          @click="
            pageable.pageNumber = pageable.pageNumber + 1;
            refertion();
          ">
          <i class="fa-solid fa-caret-right"></i>
        </button>
      </li>
    </ul>
    <div class="d-flex align-items-center pb-2">
      <select
        v-if="isCard"
        class="select_page"
        v-model="pageable.size"
        @change="refertion()">
        <option class="btn" :value="6">6</option>
        <option class="btn" :value="9">9</option>
        <option class="btn" :value="12">12</option>
        <option class="btn" :value="15">15</option>
        <option class="btn" :value="18">18</option>
        <option class="btn" :value="21">21</option>
      </select>
      <select
        v-else
        class="select_page"
        v-model="pageable.size"
        @change="refertion()">
        <option class="btn" :value="5">5</option>
        <option class="btn" :value="10">10</option>
        <option class="btn" :value="20">20</option>
        <option class="btn" :value="30">30</option>
        <option class="btn" :value="40">40</option>
        <option class="btn" :value="50">50</option>
      </select>
      <span
        >&nbsp;Hiển thị từ
        {{ (pageable.pageNumber + 1) * pageable.size - pageable.size + 1 }}
        đến&nbsp;
        {{
          (pageable.pageNumber + 1) * pageable.size > pageable.totalElements
            ? pageable.totalElements
            : (pageable.pageNumber + 1) * pageable.size
        }}
        của&nbsp;{{ pageable.totalElements }}</span
      >
    </div>
  </div>
</template>
