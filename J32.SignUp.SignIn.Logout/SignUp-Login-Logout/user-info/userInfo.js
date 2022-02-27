/*** REGION 1 - Global variables */
const gTOKEN = getCookie("token");
const gURL_INFO = "http://42.115.221.44:8080/devcamp-auth/users/me";

/*** REGION 2 - Vùng gán sự kiện cho elements */
$(document).ready(function () {
  //run sự kiện khi load trang
  onPageLoading();

  //Set sự kiện cho nút logout
  $("#btn-logout").on("click", function () {
    redirectToLogin();
  });
});

/*** REGION 3 - Event handlers */
function onPageLoading() {
  //Khai báo xác thực ở headers
  var vHeaders = {
    Authorization: "Bearer " + gTOKEN
  };

  //Gọi API để lấy thông tin người dùng
  $.ajax({
    url: gURL_INFO,
    method: "GET",
    headers: vHeaders,
    success: function (resObj) {
      console.log(resObj);
      displayUser(resObj);
    },
    error: function (xhr) {
      console.log(xhr);
      // Khi token hết hạn, AJAX sẽ trả về lỗi khi đó sẽ redirect về trang login để người dùng đăng nhập lại
      redirectToLogin();
    }
  });
}

/*** REGION 4 - Common funtions */
function redirectToLogin() {
  // Trước khi logout cần xóa token đã lưu trong cookie
  setCookie("token", "", 1);
  window.location.href = "../login/login.html";
}

//Hiển thị thông tin người dùng
function displayUser(data) {
  $("#inp-email").val(data.email);
  $("#inp-firstname").val(data.firstname);
  $("#inp-lastname").val(data.lastname);
};

//Hàm get Cookie đã giới thiệu ở bài trước
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

//Hàm setCookie đã giới thiệu ở bài trước
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
