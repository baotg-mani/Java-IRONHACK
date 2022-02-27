"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */

/*** REGION 2 - Vùng gán / thực thi hàm xử lý sự kiện cho các elements */
$(document).ready(function () {
  // xử lý sự kiện khi Btn Sign up click
  $("#btn-sign-up").on("click", onBtnSignUpClick);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Hàm xử lý sự kiện click button Sign up
function onBtnSignUpClick() {
  "use strict";
  // Khai báo đối tượng lưu dữ liệu
  var vSignUpData = {
    firstname: "",
    lastname: "",
    email: "",
    password: "",
  }
  // B1: thu thập dữ liệu
  getDataOnForm(vSignUpData);
  // B2: validate dữ liệu
  var vIsDataValid = validateData(vSignUpData);
  if (vIsDataValid) {
    // B3: xử lý sự kiện front-end
    $.ajax({
      url: "http://42.115.221.44:8080/devcamp-auth/users/signup",
      type: 'POST',
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(vSignUpData),
      success: function (pRes) {
        alert("sign-up successfully");
        console.log(pRes)
      },
      error: function (pAjaxContext) {
        alert(pAjaxContext.responseJSON.message);
        console.log(pAjaxContext.responseJSON.message);
      }
    });
  }
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm get data trên form
function getDataOnForm(paramSignUpObj) {
  "use strict";
  paramSignUpObj.firstname = $("#input-firstname").val().trim();
  paramSignUpObj.lastname = $("#input-lastname").val().trim();
  paramSignUpObj.email = $("#input-email").val().trim();
  paramSignUpObj.password = $("#input-password").val().trim();
}

// Hàm kiểm tra (validate) dữ liệu nhập vào
function validateData(paramSignUpObj) {
  "use strict";
  if (paramSignUpObj.firstname === "") {
    alert("Invalid first name!");
    console.log("Invalid first name!");
    return false;
  }
  if (paramSignUpObj.lastname === "") {
    alert("Invalid last name!");
    console.log("Invalid last name!");
    return false;
  }
  if (!validateEmail(paramSignUpObj.email) || paramSignUpObj.email === "") {
    alert("Invalid email");
    console.log("Invalid email");
    return false;
  }
  if (paramSignUpObj.password === "") {
    alert("Invalid password!");
    console.log("Invalid password!");
    return false;
  }
  let vConfirmPass = $("#re-input-password").val().trim();
  if (vConfirmPass === "" || vConfirmPass !== paramSignUpObj.password) {
    alert("Password not confirmed!");
    console.log("Password not confirmed!");
    return false;
  }
  let vEccept = $("#checkbox-accept").is(":checked");
  if (vEccept === false) {
    alert("Need to accept Temrs and Policy to sign up completely.")
    console.log("Need to accept Temrs and Policy to sign up completely.");
    return false;
  }
  return true;
}

// Hàm validate Email
function validateEmail(paramEmail) {
  "use strict";
  var vEmailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
  return vEmailReg.test(paramEmail);
}