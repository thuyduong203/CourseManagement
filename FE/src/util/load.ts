import { ref } from "vue";

const load = ref<boolean>(false);

function setLoad(value: boolean) {
  load.value = value;
}

function getLoad(): boolean {
  return load.value;
}

export { setLoad, getLoad };
