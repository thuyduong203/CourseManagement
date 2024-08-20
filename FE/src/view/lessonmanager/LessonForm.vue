<script lang="ts" setup>
import { watch } from "vue";

const props = defineProps({
  lesson: {
    type: Object,
    required: true,
  },
  error: {
    type: Object,
    required: true,
  },
});
const clearError = (field: string) => {
  props.error[field] = "";
};
watch(
  () => props.lesson.id,
  () => {
    props.error["name"] = "";
    props.error["description"] = "";
    props.error["price"] = "";
    props.error["imageUrl"] = "";
  }
);
</script>

<template>
  <form>
    <div class="form-group pb-2">
      <label for="name">Tên môn học<span class="required">*</span></label>
      <input
        :class="{ ' is-invalid': error.name }"
        @input="clearError('name')"
        v-model="lesson.name"
        type="text"
        class="form-control"
        id="name"
        placeholder="Nhập tên môn học" />
      <div class="invalid-feedback">
        {{ error.name }}
      </div>
    </div>
    <div class="form-group pb-2">
      <label for="name">Số buổi học<span class="required">*</span></label>
      <input
        @input="clearError('quantity')"
        :class="{ ' is-invalid': error.quantity }"
        v-model="lesson.quantity"
        type="text"
        class="form-control"
        id="name"
        placeholder="Nhập tên môn học" />
      <div class="invalid-feedback">
        {{ error.quantity }}
      </div>
    </div>
    <div class="form-group">
      <label for="description">Mô tả<span class="required">*</span></label>
      <textarea
        :class="{ ' is-invalid': error.description }"
        @input="clearError('description')"
        v-model="lesson.description"
        type="text"
        class="form-control"
        id="description"
        placeholder="Nhập mô tả"
        rows="5"></textarea>
      <div class="invalid-feedback">
        {{ error.description }}
      </div>
    </div>
  </form>
</template>
