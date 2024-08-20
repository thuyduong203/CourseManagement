<script lang="ts" setup>
import { onMounted } from "vue";

const props = defineProps<{
  title: string;
  idModal: string;
  sizeModal?: string;
}>();

onMounted(() => {
  if (
    props.idModal === "lessonModalAdd" ||
    props.idModal === "lessonModalUpdate"
  ) {
    const modalElement = document.getElementById(props.idModal);
    if (modalElement) {
      modalElement.addEventListener("hide.bs.modal", () => {
        const btnAddCourse = document.getElementById("btn-open-courseAdd");
        if (btnAddCourse) {
          btnAddCourse.click();
        }
      });
    }
  }
});
</script>

<template>
  <div
    class="modal fade"
    :id="idModal"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true">
    <div class="modal-dialog" :class="sizeModal">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title fs-5">{{ title }}</h5>
          <button
            type="button"
            class="btn-close"
            :id="idModal + 'Close'"
            data-bs-dismiss="modal"
            aria-label="Close"></button>
        </div>
        <div class="modal-body"><slot></slot></div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal">
            Đóng
          </button>
          <slot name="submit"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.modal-title {
  font-size: 20px;
  font-weight: 900;
}
</style>
