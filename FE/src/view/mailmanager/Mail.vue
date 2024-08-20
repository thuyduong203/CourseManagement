<script setup lang="ts">
import { ref, watch } from "vue";
import mailApi from "../../api/mailApi";
import ModalForm from "@/components/ModalForm.vue";
import { toast } from "vue3-toastify";
import { closeModal } from "@/util/bootstraputil";

const quillEditorRef = ref(null);
interface MailSender {
  id: string;
  code: string;
  name: string;
  subject: string;
  content: string;
}
interface ValueMail {
  label: string;
  value: string;
}

const arrMail = {
  ["DANG_KY_KHOA_HOC"]: [
    {
      label: "Họ và tên",
      value: "[NAME]",
    },
    {
      label: "Khóa học",
      value: "[COURSE]",
    },
  ],
  ["THAM_GIA_LOP"]: [
    {
      label: "Họ và tên",
      value: "[NAME]",
    },
    {
      label: "Tên khóa",
      value: "[COURSE]",
    },
    {
      label: "Tên lớp",
      value: "[CLASS]",
    },
    {
      label: "Thời gian",
      value: "[TIME]",
    },
    {
      label: "Tài khoản",
      value: "[USERNAME]",
    },
    {
      label: "Mật Khẩu",
      value: "[PASSWORD]",
    },
  ],
};

const arrValue = ref<ValueMail[]>([]);
const mails = ref<MailSender[]>([]);
const mail = ref<MailSender>({
  id: "",
  code: "",
  name: "",
  subject: "",
  content: "",
});

mailApi.getAllMail().then((res) => {
  mails.value = res.data;
});

function insertText(text: string) {
  if (quillEditorRef.value) {
    const quill = quillEditorRef.value.getQuill();
    const range = quill.getSelection();
    quill.insertText(range.index, text, "bold", true);
    console.log(range.index);
  }
  // const lastIndex = mail.value.content.lastIndexOf("</p>");
  // const newText = mail.value.content.slice(0, lastIndex) + text;
  // mail.value.content = newText;
}
function updateMail(mail?: MailSender) {
  mailApi.updateMail(mail).then(() => {
    closeModal("mailModalUpdate");
    toast.success("Cập nhập mail thành công");
  });
}

function getQuill(e: any) {
  console.log(e);
}
</script>

<template>
  <div class="container p-4">
    <h3><b>Tinh chỉnh mail</b></h3>
    <p class="breadcrum">Home / Tinh chỉnh mail</p>
    <div class="card mt-3">
      <div class="p-3 title_table">
        <i class="fa-solid fa-rectangle-list"></i>
        <span>Danh sách mail</span>
      </div>
      <hr />
      <div class="container py-3 px-4">
        <div class="row">
          <div
            @click="mail = ma"
            data-bs-toggle="modal"
            data-bs-target="#mailModalUpdate"
            class="col-4 p-2"
            v-for="ma in mails"
            :key="ma.id"
            style="cursor: pointer">
            <div class="card" @click="arrValue = arrMail[ma.code]">
              <div class="card-body text-center">
                <img
                  width="100px"
                  src="https://seeklogo.com/images/G/gmail-new-2020-logo-32DBE11BB4-seeklogo.com.png"
                  alt="..." />
                <h5 class="card-title my-3">{{ ma.name }}</h5>
                <h6 class="card-subtitle mb-2 text-body-secondary">
                  {{ ma.code }}
                </h6>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <ModalForm
    sizeModal="modal-lg"
    title="Cập nhập mail gửi"
    id-modal="mailModalUpdate">
    <div class="row container">
      <b class="col-2">Tiêu đề: </b>
      <div class="card col-10">
        <input v-model="mail.subject" class="subject" type="email" />
      </div>
    </div>
    <div class="row container mt-4">
      <b class="col-2">Nội dung: </b>
      <div class="card col-10 p-0">
        <QuillEditor
          ref="quillEditorRef"
          toolbar="#my-toolbar"
          contentType="html"
          theme="snow"
          style="border: none; min-height: 200px"
          v-model:content="mail.content">
          <template #toolbar>
            <div
              id="my-toolbar"
              style="border-top: none; border-left: none; border-right: none">
              <button class="ql-bold"></button>
              <button class="ql-italic"></button>
              <button class="ql-underline"></button>
              <button class="ql-strike"></button>
              <button class="ql-link"></button>
              <button class="ql-image"></button>
              <button class="ql-list" value="ordered"></button>
              <button class="ql-list" value="bullet"></button>
              <select class="ql-align">
                <option selected></option>
                <option value="center"></option>
                <option value="right"></option>
                <option value="justify"></option>
              </select>
              <button class="ql-video"></button>
              <select class="ql-color"></select>
              <select class="ql-background"></select>
              <select class="ql-size"></select>
              <button class="ql-formula"></button>
              <button class="ql-code-block"></button>
              <button class="ql-blockquote"></button>
              <button class="ql-direction" value="rtl"></button>
              <button class="ql-indent" value="-1"></button>
              <button class="ql-indent" value="+1"></button>
              <button class="ql-script" value="sub"></button>
              <button class="ql-script" value="super"></button>
              <button class="ql-clean"></button>
              <div class="dropdown">
                <button
                  style="
                    line-height: 10px;
                    margin-left: 20px;
                    color: #4b5563;
                    width: 120px;
                  "
                  class="dropdown-toggle"
                  data-bs-toggle="dropdown">
                  Chèn dữ liệu
                </button>
                <ul class="dropdown-menu">
                  <li
                    v-for="e in arrValue"
                    :key="e.value"
                    class="px-2 menu-select"
                    @click="insertText(e.value)">
                    {{ e.label }}
                  </li>
                </ul>
              </div>
            </div>
          </template>
        </QuillEditor>
      </div>
    </div>
    <template v-slot:submit>
      <button type="button" class="btn btn-info" @click="updateMail(mail)">
        Lưu
      </button>
    </template>
  </ModalForm>
</template>
<style scoped>
.menu-select:hover {
  cursor: pointer;
  background-color: rgba(212, 212, 212, 0.274);
}
.subject {
  border: none;
  outline: none;
}
</style>
