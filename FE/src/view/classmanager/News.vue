<template>
  <div style="height: 100vh">
    <div class="bg-class">
      <h2>IT17326</h2>
    </div>
    <div class="card mt-4 input-news">
      <div class="py-2">
        <img src="../../assets/image/avatar.jpg" class="mx-3" />
        <input placeholder="Thông báo nội dung nào đó cho lớp bạn" />
        <i class="fa-regular fa-paper-plane"></i>
      </div>
    </div>
    <div class="card mt-3 p-3">
      <div>
        <i class="fa-regular fa-message"></i>&nbsp; <b>Triệu Văn Tưởng</b>
      </div>
      <hr class="my-2" />
      <p style="white-space: pre-line">
        1. Bài Giảng Mới: Trong tuần này, chúng ta sẽ bắt đầu một chủ đề mới thú
        vị về "Công Nghệ và Xã Hội". Bài giảng đầu tiên đã được tải lên vào phần
        "Mục Lưu Trữ" trên trang lớp học. Xin đừng quên đọc kỹ nội dung và chuẩn
        bị câu hỏi để thảo luận trong buổi học sắp tới.<br />
        2. Bài Tập Thực Hành: Tôi đã đăng tải một loạt bài tập thực hành về chủ
        đề mới trong phần "Bài Tập". Đây là cơ hội tốt để áp dụng kiến thức đã
        học và nâng cao kỹ năng của mình. Hãy đảm bảo rằng bạn hoàn thành và gửi
        bài tập đúng hạn. <br />
        3. Thảo Luận Trực Tuyến: Hãy nhớ rằng buổi thảo luận trực tuyến về chủ
        đề "Tương Tác Xã Hội và Công Nghệ" sẽ diễn ra vào thứ Ba, lúc 15:00. Hãy
        tham gia để chia sẻ ý kiến, trao đổi quan điểm và học hỏi từ các bạn
        khác.
      </p>
    </div>
  </div>
</template>

<style scoped>
.bg-class {
  height: 300px;
  background-image: url("https://www.gstatic.com/classroom/themes/img_code.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  position: relative;
}

.bg-class > h2 {
  color: white;
  position: absolute;
  bottom: 10px;
  left: 25px;
}
.input-news img {
  height: 50px;
  width: 50px;
  border-radius: 50%;
  float: left;
}
.input-news {
  box-shadow: 0 1px 2px 0 rgba(60, 64, 67, 0.3),
    0 2px 6px 2px rgba(60, 64, 67, 0.15);
}
.input-news input {
  height: 50px;
  width: 89%;
  border: none !important;
  outline: none !important;
}
</style>
