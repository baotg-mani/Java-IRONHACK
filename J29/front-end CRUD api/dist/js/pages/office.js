"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#office-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "city" },
    { "data": "phone" },
    { "data": "addressLine" },
    { "data": "state" },
    { "data": "country" },
    { "data": "territory" }
  ],
  columnDefs: [
    {
      targets: 7,
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
    url: "http://localhost:8080/office" + "/" + gId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-city").val(res.city);
      $("#input-phone").val(res.phone);
      $("#input-addressLine").val(res.addressLine);
      $("#input-state").val(res.state);
      $("#input-country").val(res.country);
      $("#input-territory").val(res.territory);

      $("#modal-add-edit-office").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  resetData();
  $("#modal-add-edit-office").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vOfficeObj = {
    id: gId,
    city: "",
    phone: "",
    addressLine: "",
    state: "",
    country: "",
    territory: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vOfficeObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vOfficeObj);
  if (vIsDataValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/office" + "/" + gId,
        data: JSON.stringify(vOfficeObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-office").modal("hide");
          toastr.success("UPDATE thành công   &#x1F618;");
          resetData();
        }
      });
    } else { //Create drink (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      console.log("ok")
      vOfficeObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/office",
        data: JSON.stringify(vOfficeObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-office").modal("hide");
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

// Hàm xử lý sự kiện click Confirm Delete 1 Office
function onConfirmDelOneClick(paramDel) {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/office" + "/" + gId,
    success: function (res) {
      loadAllDataToTable();
      resetData();
      $("#modal-delete").modal("hide");
      toastr.success("DELETE thành công   &#128519;");
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Ham xu ly load data offices to Table
function loadAllDataToTable() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/office/all",
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
  paramObj.city = $("#input-city").val();
  paramObj.phone = $("#input-phone").val();
  paramObj.addressLine = $("#input-addressLine").val();
  paramObj.state = $("#input-state").val();
  paramObj.country = $("#input-country").val();
  paramObj.territory = $("#input-territory").val();
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.city == "") {
    toastr.error("Thiếu city   &#128545;");
    return false;
  }
  if (paramObj.phone == "") {
    toastr.error("Thiếu phone   &#128545;");
    return false;
  }
  if (paramObj.addressLine == "") {
    toastr.error("Thiếu addressLine   &#128545;");
    return false;
  }
  if (paramObj.state == "") {
    toastr.error("Thiếu state   &#128545;");
    return false
  }
  if (paramObj.country == "") {
    toastr.error("Thiếu country   &#128545;");
    return false
  }
  if (paramObj.territory == "") {
    toastr.error("Thiếu territory   &#128545;");
    return false;
  }
  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-city").val("");
  $("#input-phone").val("");
  $("#input-addressLine").val("");
  $("#input-state").val("");
  $("#input-country").val("");
  $("#input-territory").val("");
  gId = null;
  gSTT = 1;
}

