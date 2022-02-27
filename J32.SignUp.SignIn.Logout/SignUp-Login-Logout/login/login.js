"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
const gTOKEN = getCookie("token"); //get token on Cookie
const gURL_LOGIN = "http://42.115.221.44:8080/devcamp-auth/users/signin";


/*** REGION 2 - Vùng gán / thực thi sự kiện cho các elements */
$(document).ready(function () {
  //Kiểm tra token nếu có token tức người dùng đã đăng nhập thì chuyển sang page info
  if (gTOKEN) {
    window.location.href = "../user-info/userInfo.html";
  }

  //--- gán sự kiện khi submit form login (form code bên html dùng thẻ <form> nên phải dùng code này)
  $("#form-submit").on("submit", function (e) {
    e.preventDefault();
    onBtnLoginClick();
  });

  //--- Sử dụng cách gán sự kiện này khi khối form code trong html dùng thẻ <div>
  // $("#btn-login").on("click", onBtnLoginClick);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// hàm xử lý sự kiện login
function onBtnLoginClick() {
  //B0: khai báo đối tượng chứa dữ liệu
  var vLoginObj = {
    email: "",
    password: ""
  };
  // B1: thu thập dữ liệu
  getLoginData(vLoginObj);

  // B2: validate dữ liệu
  var vIsValidated = validateLoginData(vLoginObj);
  if (vIsValidated) {

    // B3: call API login data to det Token + hiển thị console
    loginFunction(vLoginObj);
  }
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm xử lý sự kiện login get token
function loginFunction(paramLoginObj) {
  //--- API theo đặc tả sẽ call theo data-form chứ không phải kiểu json nên cần khai báo formdata
  var vFormData = new FormData();
  vFormData.append('email', paramLoginObj.email);
  vFormData.append('password', paramLoginObj.password);

  $.ajax({
    type: "POST",
    url: gURL_LOGIN,
    data: vFormData,
    contentType: false,
    cache: false,
    processData: false,
    success: function (resObj) {
      responseHandler(resObj);
    },
    error: function (xhr) {
      // Lấy error message
      var vErrMessage = xhr.responseJSON.message;
      console.log(vErrMessage);
    }
  });

  //--- Đây là cách call theo data-json
  // $.ajax({
  //   url: "http://42.115.221.44:8080/devcamp-auth/users/signin?email=" + paramLoginObj.email + '&password=' + paramLoginObj.password,
  //   type: 'POST',
  //   dataType: "json",
  //   contentType: "application/json; charset=utf-8",
  //   success: function (pRes) {
  //     console.log(pRes);
  //     alert("Login successfully!");
  //     gToken = pRes.token;

  //     // chuyển snag trang User info
  //     window.location.href = "../user-info/userInfo.html"
  //   },
  //   error: function (pAjaxContext) {
  //     alert(pAjaxContext.responseText);
  //     console.log(pAjaxContext.responseText);
  //   }
  // });
}

// function to get login data on form
function getLoginData(paramLoginObj) {
  paramLoginObj.email = $("#inp-email").val().trim();
  paramLoginObj.password = $("#inp-password").val().trim();
  // paramLoginObj.rememberUser = $('#ckb-remember-account').is(":checked");
}

// hàm validate dữ liệu login
function validateLoginData(paramLoginObj) {
  if (!validateEmail(paramLoginObj.email) || paramLoginObj.email === "") {
    alert("Email không hợp lệ");
    return false;
  }
  if (paramLoginObj.password === "") {
    alert("Bạn cần nhập password");
    return false;
  }
  return true;
}

// Hàm validate email bằng regex
function validateEmail(email) {
  const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return regex.test(String(email).toLowerCase());
}

//Xử lý object trả về khi login thành công
function responseHandler(data) {
  //Lưu token vào cookie trong 1 ngày
  setCookie("token", data.token, 1);
  //chuyển sang trang user infomation
  window.location.href = "../user-info/userInfo.html";
}

//Hàm setCookie đã giới thiệu ở bài trước
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

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


