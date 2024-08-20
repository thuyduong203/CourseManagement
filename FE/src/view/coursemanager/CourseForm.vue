<script lang="ts" setup>
import { watch } from "vue";

const props = defineProps({
  course: {
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
  () => props.course.id,
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
    <div class="form-group">
      <label for="name">Tên khóa học<span class="required">*</span></label>
      <input
        v-model="course.name"
        :class="{ ' is-invalid': error.name }"
        @input="clearError('name')"
        type="text"
        class="form-control"
        placeholder="Nhập tên khóa học"
        required />
      <div class="invalid-feedback">
        {{ error.name }}
      </div>
    </div>
    <div class="form-group">
      <label for="price">Giá khóa học<span class="required">*</span></label>
      <input
        @input="clearError('price')"
        v-model="course.price"
        :class="{ ' is-invalid': error.price }"
        type="number"
        class="form-control"
        placeholder="Nhập giá khóa học"
        required />
      <div class="invalid-feedback">
        {{ error.price }}
      </div>
    </div>
    <div class="form-group">
      <label for="imageUrl">Link ảnh<span class="required">*</span></label>
      <input
        @input="clearError('imageUrl')"
        v-model="course.imageUrl"
        :class="{ ' is-invalid': error.imageUrl }"
        type="text"
        class="form-control"
        placeholder="Nhập link ảnh"
        required />
      <div class="invalid-feedback">
        {{ error.imageUrl }}
      </div>
    </div>
    <div class="form-group">
      <label for="description">Mô tả khóa học</label>
      <textarea
        @input="clearError('description')"
        :class="{ ' is-invalid': error.description }"
        v-model="course.description"
        class="form-control"
        placeholder="Nhập mô tả khóa học"
        rows="4"></textarea>
      <div class="invalid-feedback">
        {{ error.description }}
      </div>
    </div>
  </form>
</template>
./LessonForm.vue
