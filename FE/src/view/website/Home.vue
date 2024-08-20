<script setup lang="ts">
import { getUser } from "@/util/userUtil";
import { onMounted, ref, watch } from "vue";
import courseApi from "../../api/courseApi";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();

const courses = ref<Course[]>([]);
const pageable = ref<Pageable>({
  pageNumber: 0,
  totalPages: 0,
  totalElements: 0,
  size: 6,
});

const searchText = ref<string>("");
function searchCourse() {
  courseApi
    .searchCourse(
      pageable.value.pageNumber,
      pageable.value.size,
      searchText.value
    )
    .then((response) => {
      courses.value = response.data.content;
      pageable.value = {
        pageNumber: response.data.pageable.pageNumber,
        totalPages: response.data.totalPages,
        totalElements: response.data.totalElements,
        size: response.data.size,
      };
    });
}

searchCourse();

function nextPage(params: number) {
  pageable.value.pageNumber = pageable.value.pageNumber + params;
  searchCourse();
}
</script>

<template>
  <div class="content-home">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container">
        <div class="navbar-brand" @click="router.push('/')">
          <img src="../../assets/image/logo-web.png" />
        </div>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavMenu"
          aria-controls="navbarNavMenu"
          aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavMenu">
          <div class="navbar-nav ms-auto">
            <router-link class="nav-link" to="/">TRANG CHỦ</router-link>
            <a class="nav-link mx-3" href="#course-list">KHÓA HỌC</a>
            <a class="nav-link" href="#about">VỀ CHÚNG TÔI</a>
          </div>
        </div>
      </div>
    </nav>
    <div class="container content-container">
      <div class="hero-content">
        <h1 class="hero-title mx-3">
          Newwave Solutions - Become A Better Developer
        </h1>
        <p class="hero-text mx-3">
          Newwave Solutions là một trong những công ty hàng đầu cung cấp các
          phần mềm dạng dịch vụ cho các khách hàng thị trường Nhật Bản, thị
          trường Global và trong nước với 12+ năm kinh nghiệm cùng quy mô 300+
          nhân viên.
        </p>
        <div class="d-flex justify-content-center hero-button">
          <button type="button" class="btn btn-info">Các Khóa Học</button>
          &nbsp; &nbsp; &nbsp; &nbsp;
          <button type="button" class="btn btn-light">Liên Hệ</button>
        </div>
      </div>
      <div
        class="p-3"
        style="
          background-color: white;
          width: 100%;
          margin-top: 70px;
          border-radius: 50px;
        ">
        <h1
          class="text-center"
          style="color: #001c66; font-weight: 600; width: 100%">
          Tại sao bạn nên học với Newwave Solutions
        </h1>
        <div class="row mt-5">
          <div class="col-lg-6" style="text-align: center">
            <img
              src="https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/lo_trinh_khoa_hoc/dai_dien2.png"
              alt="" />
          </div>
          <div class="col-lg-6">
            <div class="row mt-5">
              <div class="col-10 d-flex align-items-center" style="width: 100%">
                <div
                  class="col-2 p-3"
                  style="
                    background-color: #def5fd;
                    border-radius: 20px;
                    text-align: center;
                    width: 90px;
                  ">
                  <img
                    style="width: 100%"
                    src="https://cdn-main.28tech.com.vn/media/core/icons/chat-luong-cao.png"
                    alt="" />
                </div>
                <div style="margin-left: 10px">
                  <h3 style="font-size: 18px; font-weight: 700; color: #001c66">
                    Chất lượng cao
                  </h3>
                  <p style="font-size: 14px; color: #6b7385; font-weight: 500">
                    Nội dung của khóa học được đầu tư cả về chất và lượng, giáo
                    viên có kinh nghiệm và cực kỳ tâm huyết với công việc giảng
                    dạy.
                  </p>
                </div>
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-10 d-flex align-items-center" style="width: 100%">
                <div
                  class="col-2 p-3"
                  style="
                    background-color: #def5fd;
                    border-radius: 20px;
                    text-align: center;
                    width: 90px;
                  ">
                  <img
                    style="width: 100%"
                    src="https://cdn-main.28tech.com.vn/media/core/icons/ky-nang-lap-trinh.png"
                    alt="" />
                </div>
                <div style="margin-left: 10px">
                  <h3 style="font-size: 18px; font-weight: 700; color: #001c66">
                    Cung cấp nhiều kỹ năng quan trọng
                  </h3>
                  <p style="font-size: 14px; color: #6b7385; font-weight: 500">
                    Khóa học cung cấp kỹ thuật lập trình, tư duy logic, cách
                    giải quyết bài toán, thuật toán... Những kỹ năng sẽ theo bạn
                    mãi trong học tập và công việc sau này.
                  </p>
                </div>
              </div>
            </div>
            <div class="row mt-5">
              <div class="col-10 d-flex align-items-center" style="width: 100%">
                <div
                  class="col-2 p-3"
                  style="
                    background-color: #def5fd;
                    border-radius: 20px;
                    text-align: center;
                    width: 90px;
                  ">
                  <img
                    style="width: 100%"
                    src="https://cdn-main.28tech.com.vn/media/core/icons/lap-trinh-vien.png"
                    alt="" />
                </div>
                <div style="margin-left: 10px">
                  <h3 style="font-size: 18px; font-weight: 700; color: #001c66">
                    Bước chuẩn bị vững chắc của một lập trình viên
                  </h3>
                  <p style="font-size: 14px; color: #6b7385; font-weight: 500">
                    Kỹ thuật lập trình là kỹ năng đầu tiên cần phải học khi bạn
                    muốn trở thành một lập trình viên, việc học tốt kỹ thuật lập
                    trình sẽ là bước đệm vững chắc cho sự nghiệp của bạn
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <br id="course-list" />
        <div style="margin-top: 100px"></div>
        <div>
          <h1
            class="text-center mb-3"
            style="color: #001c66; font-weight: 600; width: 100%">
            Khóa Học Tại Newwave Solutions
          </h1>
          <div class="row container mx-1">
            <div
              class="col-lg-4 col-md-6 p-2"
              style="cursor: pointer"
              v-for="course in courses"
              :key="course.id">
              <div
                class="card p-0 card-course"
                :title="course.name"
                style="
                  border: none;
                  box-shadow: 0px 2px 1px -1px rgba(0, 0, 0, 0.2),
                    0px 1px 1px 0px rgba(0, 0, 0, 0.14),
                    0px 1px 3px 0px rgba(0, 0, 0, 0.12);
                ">
                <RouterLink :to="'/khoa-hoc/' + course.id">
                  <img
                    height="200px"
                    :src="course.imageUrl"
                    class="card-img-top"
                    alt="..." />
                  <div class="card-body pt-2 px-3">
                    <h5 class="card-title m-0">
                      <b>{{ course.name }}</b>
                    </h5>

                    <div class="my-3">
                      <p>
                        <i
                          class="fa-solid fa-book-bookmark"
                          style="color: cadetblue"></i>
                        &nbsp;<span style="color: gray; font-weight: 500">
                          Số lượng môn:
                        </span>
                        <b style="color: gray">{{ course.lessonQuantity }}</b>
                      </p>
                      <p class="my-2">
                        <i
                          class="fa-solid fa-landmark"
                          style="color: cadetblue"></i>
                        &nbsp;<span style="color: gray; font-weight: 500">
                          Số lượng lớp:
                        </span>
                        <b style="color: gray">{{ course.classQuantity }}</b>
                      </p>
                      <p>
                        <i
                          class="fa-solid fa-user-graduate"
                          style="color: cadetblue"></i>
                        &nbsp;<span style="color: gray; font-weight: 500">
                          Số lượng học viên:
                        </span>
                        <b style="color: gray">{{ course.studentQuantity }}</b>
                      </p>
                    </div>
                    <div>
                      <b style="color: #001c66; font-size: 1.3em">
                        {{
                          course.price?.toLocaleString("vi", {
                            style: "currency",
                            currency: "VND",
                          })
                        }}
                      </b>
                    </div>
                    <!-- <p class="card-text" style="white-space: pre-line">
                      {{ course.description }}
                    </p> -->
                  </div>
                </RouterLink>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-4">
            <button
              @click="nextPage(-1)"
              :disabled="pageable.pageNumber == 0"
              type="button"
              class="btn btn-info">
              <i class="fa-solid fa-left-long"></i> Trang trước
            </button>
            &nbsp;
            <button
              :disabled="pageable.totalPages - 1 == pageable.pageNumber"
              type="button"
              class="btn btn-info"
              @click="nextPage(1)">
              Trang tiếp <i class="fa-solid fa-right-long"></i>
            </button>
          </div>
        </div>

        <br id="about" />
        <div style="margin-top: 100px"></div>
        <div class="row mb-5">
          <div class="col-lg-6" style="text-align: center">
            <img
              src="https://cdn-main.28tech.com.vn/media/anh-khoa-hoc/lo_trinh_khoa_hoc/dai_dien1.png"
              alt="" />
          </div>
          <div class="col-lg-6" style="margin-top: 80px">
            <h1 style="color: #001c66; font-weight: 600; width: 100%">
              Về Newwave Solutions
            </h1>
            <p style="color: #6b7385">
              Newwave Solutions là một đội nhóm gồm các Lập trình viên hiện đang
              làm việc ở nhiều lĩnh vực khác nhau nhưng có chung niềm đam mê với
              giảng dạy và chia sẻ kiến thức.
            </p>
            <br />
            <p style="color: #6b7385">
              Newwave Solutions luôn cố gắng đổi mới, trau dồi kỹ năng, đón nhận
              đóng góp, khắc phúc những điểm chưa tốt để mang đến cho các bạn
              học viên những khóa học lập trình với chất lượng cao nhất.
            </p>
            <br />
            <p style="color: #6b7385">
              Với những bài giảng chất lượng và bài tập phong phú cùng hệ thống
              chấm bài tự động Hackerrank, 28Tech sẽ đem lại cho người học nhiều
              kỹ năng rất giá trị.
            </p>
            <div class="d-flex align-items-center mt-4" style="width: 100%">
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/BlueFlat_tick_icon.svg/512px-BlueFlat_tick_icon.svg.png"
                style="height: 25px; margin-bottom: 5px" />
              &nbsp;&nbsp;&nbsp;
              <h5><b>Giảng viên giàu kinh nghiệm</b></h5>
            </div>
            <div class="d-flex align-items-center mt-4" style="width: 100%">
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/BlueFlat_tick_icon.svg/512px-BlueFlat_tick_icon.svg.png"
                style="height: 25px; margin-bottom: 5px" />
              &nbsp;&nbsp;&nbsp;
              <h5><b>Bài giảng và bài tập chất lượng</b></h5>
            </div>
          </div>
        </div>
      </div>
      <footer class="footer pb-5">
        <div class="container row">
          <div class="col-md-9 mb-4">
            <h4 style="color: #001c66; font-weight: 600; width: 100%">
              Địa chỉ
            </h4>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-location-dot"></i>&nbsp;Tầng 1, 4, 10, toà
              nhà Mitec, đường Dương Đình Nghệ, quận Cầu Giấy, thành phố Hà Nội,
              Việt Nam
            </p>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-location-dot"></i>&nbsp;1-11-8 Yushima, Quận
              Bunkyo, Thành phố Tokyo 113-0034, Nhật Bản
            </p>
          </div>
          <div class="col-md-3">
            <h4 style="color: #001c66; font-weight: 600; width: 100%">
              Liên hệ
            </h4>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-phone"></i>&nbsp;0987657412
            </p>
            <p style="color: #6b7385" class="mt-4">
              <i class="fa-solid fa-envelope"></i>&nbsp;trieutuong2003@gmail.com
            </p>
          </div>
        </div>
      </footer>
    </div>
  </div>
</template>
<style scoped>
.content-home {
  background-image: url("https://cdn-main.28tech.com.vn/media/core/background/hero-bg.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-color: white;
}
.navbar {
  background-color: #0b033c !important;
  margin: 0;
  width: 100%;
  box-shadow: none !important;
  height: 70px;
}
.navbar-brand img {
  height: 40px;
}
.navbar-nav .nav-link {
  color: white !important;
}
.content-container {
  padding-top: 80px;
  height: 800px;
}
.btn-info:disabled {
  display: none;
}
.hero-content {
  margin-top: 100px;
}
.hero-title {
  font-weight: 600;
  font-size: 3.5vw !important;
  text-align: center;
  color: #ffffff;
}
.hero-text {
  text-align: center;
  color: #ffffff;
  padding: 50px 5vw;
}
.hero-button .btn {
  transition: all 0.5s;
  width: 200px;
  font-weight: 900;
  height: 60px;
}
.hero-button .btn:hover {
  transform: translate3d(0, -2px, 0);
}
.btn-all-course:hover {
  transform: translate3d(0, -2px, 0);
}
</style>
