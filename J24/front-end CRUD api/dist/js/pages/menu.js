
"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gTable = $("#menu-table").DataTable({
  "columns": [
    { "data": "id" },
    { "data": "size" },
    { "data": "duongKinh" },
    { "data": "suon" },
    { "data": "salad" },
    { "data": "soLuongNuocNgot" },
    { "data": "donGia" }
  ],
  columnDefs: [
    {
      targets: 7,
      defaultContent:
        `
        <button class='btn btn-info btn-sm edit'>
          <i class='fas fa-edit'></i>
        </button>	
        <button class='btn btn-danger btn-sm delete'>
          <i class='far fa-trash-alt'></i>
        </button>`
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

  $(document).on("click", ".edit", function () {
    onBtnEditClick(this);
  });

  // gán sự kiện click vào btn Save data
  $("#save_data").on("click", onBtnSaveData);

  // gán sự kiện click vào icon Delete
  $(document).on("click", ".delete", function () {
    onBtnDeleteClick(this);
  });

  // gán sự kiện click Confirm Delete Row
  $("#confirm-del-one").on("click", function () {
    onConfirmDelOneClick();
  });

  // gán sự kiện click vào btn Delete ALL
  $("#del_all").on("click", function () {
    onBtnDelAllClick();
  });

  // gán sự kiện click Confirm Delete All
  $("#confirm-del").on("click", function () {
    onConfirmDelAllClick();
  })

  // xử lý sự kiện khi ẩn modal delete all
  $("#modal-del-all").on("hidden.bs.modal", function () {
    resetData();
  });
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  loadAllDataToTable();
}

// Hàm xử lý khi click vào Row trong bảng
function onBtnEditClick(paramEdit) {
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
    url: "http://localhost:8080/menus" + "/" + gId,
    success: function (resMenu) {
      console.log(resMenu);

      $("#input-size-combo").val(resMenu.size);
      $("#input-duong-kinh").val(resMenu.duongKinh);
      $("#input-suon").val(resMenu.suon);
      $("#input-salad").val((resMenu.salad));
      $("#input-so-nuoc").val((resMenu.soLuongNuocNgot));
      $("#input-price").val(resMenu.donGia);
    }
  });
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  //B0: tạo biến lưu dữ liệu
  var vUpdateObj = {
    id: gId,
    size: "",
    duongKinh: null,
    suon: null,
    salad: null,
    soLuongNuocNgot: null,
    donGia: null
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vUpdateObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vUpdateObj);
  if (vIsDataValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/menus" + "/" + gId,
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resMenu) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          resetData();
        }
      });
    } else { //Create drink (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      console.log("ok")
      vUpdateObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/menus",
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resMenu) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          resetData();
        }
      });
    }
  }
}

// Hàm xử lý sự kiện Delete
function onBtnDeleteClick(paramDel) {
  // đổi trạng thái
  gFormMode = gDELETE_MODE;
  // hiển thị modal
  $("#modal-del-one").modal("show");
  // thu thập dữ liệu ID
  var vRow = $(paramDel).parents("tr");
  var vRowObj = gTable.row(vRow).data();
  gId = vRowObj.id;
  console.log(gId);
}

// Hàm xử lý sự kiện click Confirm Delete 1 Drink
function onConfirmDelOneClick(paramDel) {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/menus" + "/" + gId,
    success: function (response) {
      loadAllDataToTable();
      resetData();
      $("#modal-del-one").modal("hide");
    }
  });
}

// Hàm xử lý sự kiện xóa tất cả dữ liệu vouchers
function onBtnDelAllClick() {
  // hiển thị modal del ALL
  $("#modal-del-all").modal("show");
  // thay đổi trạng thái
  gFormMode = gDELETE_MODE;
}

// Hàm xử lý sự kiện click Confirm xóa ALL
function onConfirmDelAllClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/menus",
    success: function (response) {
      loadAllDataToTable();
      resetData();
      $("#modal-del-all").modal("hide");
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Ham xu ly load data menus to Table
function loadAllDataToTable() {
  $.ajax({
    url: "http://localhost:8080/menus",
    type: "GET",
    dataType: "json",
    success: function (res) {
      console.log(res);
      gTable.clear();
      gTable.rows.add(res);
      gTable.draw();
    },
    error: function (err) {
      console.log(err.response);
    }
  });
}

// Hàm thu thập dữ liệu trên form
function getDataOnForm(paramObj) {
  paramObj.size = $("#input-size-combo").val();
  paramObj.duongKinh = parseInt($("#input-duong-kinh").val());
  paramObj.suon = parseInt($("#input-suon").val());
  paramObj.salad = parseInt($("#input-salad").val());
  paramObj.soLuongNuocNgot = parseInt($("#input-so-nuoc").val());
  paramObj.donGia = parseInt($("#input-price").val());
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  if (paramObj.size == "") {
    alert("Thiếu size combo");
    return false;
  }
  if (isNaN(paramObj.duongKinh)) {
    alert("Đường kính là một số");
    return false
  }
  if (isNaN(paramObj.suon)) {
    alert("Số lượng sườn phải là một số");
    return false
  }
  if (isNaN(paramObj.salad)) {
    alert("Số lượng salad phải là một số");
    return false
  }
  if (isNaN(paramObj.soLuongNuocNgot)) {
    alert("Số lượng nước ngọt phải là một số");
    return false
  }
  if (isNaN(paramObj.donGia)) {
    alert("Đơn giá phải là một số");
    return false
  }
  return true;
}

// Hàm reset dữ liệu
function resetData() {
  gFormMode = gNORMAL_MODE;

  $("#input-size-combo").val("");
  $("#input-duong-kinh").val("");
  $("#input-suon").val("");
  $("#input-salad").val("");
  $("#input-so-nuoc").val("");
  $("#input-price").val("");

  gId = null;
}

