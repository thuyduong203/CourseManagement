<script lang="ts" setup>
const props = defineProps({
  teacher: {
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
</script>

<template>
  <form class="row">
    <div class="form-group col-md-6">
      <label for="name">Họ và tên<span class="required">*</span></label>
      <input
        v-model="teacher.name"
        :class="{ ' is-invalid': error.name }"
        @input="clearError('name')"
        type="text"
        class="form-control"
        id="name"
        placeholder="Nhập họ và tên" />
      <div class="invalid-feedback">
        {{ error.name }}
      </div>
    </div>
    <div class="form-group col-md-6">
      <label for="birthDate">Ngày sinh<span class="required">*</span></label>
      <input
        v-model="teacher.birthDate"
        :class="{ ' is-invalid': error.birthDate }"
        @input="clearError('birthDate')"
        type="date"
        class="form-control"
        id="birthDate"
        placeholder="Nhập ngày sinh" />
      <div class="invalid-feedback">
        {{ error.birthDate }}
      </div>
    </div>
    <div class="form-group col-md-6 my-3">
      <label for="email">Email giảng viên<span class="required">*</span></label>
      <input
        v-model="teacher.email"
        :class="{ ' is-invalid': error.email }"
        @input="clearError('email')"
        type="text"
        class="form-control"
        id="email"
        placeholder="Nhập email giảng viên" />
      <div class="invalid-feedback">
        {{ error.email }}
      </div>
    </div>
    <div class="form-group col-md-6 my-3">
      <label for="phone">Số điện thoại<span class="required">*</span></label>
      <input
        v-model="teacher.phone"
        :class="{ ' is-invalid': error.phone }"
        @input="clearError('phone')"
        type="text"
        class="form-control"
        id="phone"
        placeholder="Nhập số điện thoại" />
      <div class="invalid-feedback">
        {{ error.phone }}
      </div>
    </div>
    <div class="form-group col-md-6">
      <label for="address">Địa chỉ<span class="required">*</span></label>
      <input
        v-model="teacher.address"
        :class="{ ' is-invalid': error.address }"
        @input="clearError('address')"
        type="text"
        class="form-control"
        id="address"
        placeholder="Nhập địa chỉ" />
      <div class="invalid-feedback">
        {{ error.address }}
      </div>
    </div>

    <div class="form-group col-md-6">
      <p>Giới tính<span class="required">*</span></p>
      <div class="form-check form-check-inline mt-2">
        <input
          :class="{ ' is-invalid': error.gender }"
          @input="clearError('gender')"
          :checked="teacher.gender == 'MALE'"
          v-model="teacher.gender"
          class="form-check-input"
          type="radio"
          gender="gender"
          id="gender-man"
          value="MALE" />
        <label class="form-check-label mr-3" for="gender-man">Nam</label>
      </div>
      <div class="form-check form-check-inline">
        <input
          :class="{ ' is-invalid': error.gender }"
          @input="clearError('gender')"
          :checked="teacher.gender == 'FEMALE'"
          v-model="teacher.gender"
          class="form-check-input"
          type="radio"
          gender="gender"
          id="gender-women"
          value="FEMALE" />
        <label class="form-check-label mr-3" for="gender-women">Nữ</label>
        <div class="invalid-feedback">
          {{ error.gender }}
        </div>
      </div>
    </div>
  </form>
</template>
