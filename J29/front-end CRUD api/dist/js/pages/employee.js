"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#employee-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "firstName" },
    { "data": "lastName" },
    { "data": "email" },
    { "data": "jobTitle" },
    { "data": "officeCode" },
    { "data": "extension" },
    { "data": "reportTo" }
  ],
  columnDefs: [
    {
      targets: 8,
      defaultContent:
        `
        <button style="color:deepskyblue" class='btn btn-outline-light btn-sm edit'>
          <i class='fas fa-edit'></i>
        </button>	
        <button style="color:palevioletred" class='btn btn-outline-light btn-sm delete'>
          <i class='far fa-trash-alt'></i>
        </button>`
    },
    {
      targets: 0,
      render: function() {
        return gSTT++;
      }
    }
  ]
});

const gNORMAL_MODE = "normal";
const gCREATE_MODE = "create";
const gUPDATE_MODE = "update";
const gDELETE_MODE = "delete";
var gFormMode = gNORMAL_MODE;

var gId = null;

/*** REGION 2 - Vùng gán / thực thi hàm xử lý sự kiện cho các elements */
$(document).ready(function () {
  onPageLoading();

  // gán sự kiện click vào btn Add new
  $("#add-new").on("click", onBtnAddClick);

  // gán sự kiện click vào btn edit
  $(document).on("click", ".edit", function () {
    onBtnEditClick(this);
  });

  // gán sự kiện click vào btn Save data
  $("#save-data").on("click", onBtnSaveData);

  // gán sự kiện click vào icon Delete
  $(document).on("click", ".delete", function () {
    onBtnDeleteClick(this);
  });

  // gán sự kiện click Confirm Delete Row
  $("#confirm-delete").on("click", function () {
    onConfirmDelOneClick();
  });
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  loadAllDataToTable();
}

// Hàm xử lý khi click vào Row trong bảng
function onBtnEditClick(paramEdit) {
  "use strict";
  // thay đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: tạo obj lưu data
  var vRowObj = {};
  //B1: thu thập data
  vRowObj = gTable.row($(paramEdit).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get drink theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/employee" + "/" + gId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-lastName").val(res.lastName);
      $("#input-firstName").val(res.firstName);
      $("#input-email").val(res.email);
      $("#input-officeCode").val(res.officeCode);
      $("#input-reportTo").val(res.reportTo);
      $("#input-jobTitle").val(res.jobTitle);
      $("#input-extension").val(res.extension);

      $("#modal-add-edit-employee").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  resetData();
  $("#modal-add-edit-employee").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vEmployeeObj = {
    id: gId,
    lastName: "",
    firstName: "",
    email: "",
    officeCode: -1,
    reportTo: -1,
    jobTitle: "",
    extension: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vEmployeeObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vEmployeeObj);
  if (vIsDataValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/employee" + "/" + gId,
        data: JSON.stringify(vEmployeeObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-employee").modal("hide");
          toastr.success("UPDATE thành công   &#x1F618;");
          resetData();
        }
      });
    } else { //Create drink (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      console.log("ok")
      vEmployeeObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/employee",
        data: JSON.stringify(vEmployeeObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-employee").modal("hide");
          toastr.success("CREATE thành công   &#x1F618;");
          resetData();
        }
      });
    }
  }
}

// Hàm xử lý sự kiện Delete
function onBtnDeleteClick(paramDel) {
  "use strict";
  // đổi trạng thái
  gFormMode = gDELETE_MODE;
  // hiển thị modal
  $("#modal-delete").modal("show");
  // thu thập dữ liệu ID
  var vRow = $(paramDel).parents("tr");
  var vRowObj = gTable.row(vRow).data();
  gId = vRowObj.id;
  console.log(gId);
}

// Hàm xử lý sự kiện click Confirm Delete 1 Drink
function onConfirmDelOneClick(paramDel) {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/employee" + "/" + gId,
    success: function (res) {
      loadAllDataToTable();
      resetData();
      $("#modal-delete").modal("hide");
      toastr.success("DELETE thành công   &#128519;");
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Ham xu ly load data vouchers to Table
function loadAllDataToTable() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/employee/all",
    type: "GET",
    dataType: "json",
    success: function (res) {
      console.log(res);
      gTable.clear();
      gTable.rows.add(res);
      gTable.draw();
    },
    error: function (err) {
      alert(err.response);
    }
  });
}

// Hàm thu thập dữ liệu trên form
function getDataOnForm(paramObj) {
  "use strict";
  paramObj.lastName = $("#input-lastName").val();
  paramObj.firstName = $("#input-firstName").val();
  paramObj.email = $("#input-email").val();
  paramObj.officeCode = parseInt($("#input-officeCode").val());
  paramObj.reportTo = parseInt($("#input-reportTo").val());
  paramObj.jobTitle = $("#input-jobTitle").val();
  paramObj.extension = $("#input-extension").val();
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.lastName == "") {
    toastr.error("Thiếu last name   &#128545;");
    return false;
  }
  if (paramObj.firstName == "") {
    toastr.error("Thiếu first name   &#128545;");
    return false;
  }
  if (paramObj.email == "") {
    toastr.error("Thiếu email   &#128545;");
    return false;
  }
  if (!validateEmail(paramObj.email)) {
    toastr.error("Email không hợp lệ   &#128545;")
    return false;
  }
  if (isNaN(paramObj.officeCode)) {
    toastr.error("officeCode phải là số   &#128545;");
    return false
  }
  if (isNaN(paramObj.reportTo)) {
    toastr.error("reportTo phải là số   &#128545;");
    return false
  }
  if (paramObj.jobTitle == "") {
    toastr.error("Thiếu job title   &#128545;");
    return false;
  }
  if (paramObj.extension == "") {
    toastr.error("Thiếu extension   &#128545;");
    return false;
  }
  return true;
}

function validateEmail(paramEmail) {
  var vEmailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
  return vEmailReg.test(paramEmail);
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-lastName").val("");
  $("#input-firstName").val("");
  $("#input-email").val("");
  $("#input-officeCode").val("");
  $("#input-reportTo").val("");
  $("#input-jobTitle").val("");
  $("#input-extension").val("");
  gId = null;
  gSTT = 1;
}

