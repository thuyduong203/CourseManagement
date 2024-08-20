<script lang="ts" setup>
import { getUser } from "@/util/userUtil";
import { defineProps } from "vue";

const props = defineProps<{
  headers: Array<string>;
  keyName: string;
  action?: boolean;
}>();
</script>

<template>
  <div class="px-4 text-center">
    <table class="table table-hover">
      <thead>
        <tr>
          <th v-if="!action" class="text-center">STT</th>
          <th v-for="header in headers" :key="props.keyName + header">
            {{ header }}
          </th>
          <th v-if="!action && getUser()?.role == 'ADMIN'" class="text-center">
            Hành động
          </th>
        </tr>
      </thead>
      <tbody>
        <slot></slot>
      </tbody>
    </table>
  </div>
</template>

<style>
thead tr th {
  background-color: #f8fafc !important;
}
td {
  font-weight: normal;
}
</style>
