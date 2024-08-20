import "./assets/css/base.css";
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/router";
import Vue3Toastify, { type ToastContainerOptions } from "vue3-toastify";
import "vue3-toastify/dist/index.css";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import { QuillEditor } from "@vueup/vue-quill";

createApp(App)
  .use(Vue3Toastify, {
    autoClose: 3000,
  } as ToastContainerOptions)
  .use(router)
  .component("QuillEditor", QuillEditor)
  .mount("#app");
