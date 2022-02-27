
"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gTable = $("#drink-table").DataTable({
  "columns": [
    { "data": "id" },
    { "data": "maNuocUong" },
    { "data": "tenNuocUong" },
    { "data": "donGia" },
    { "data": "ngayTao" },
    { "data": "ngayCapNhat" }
  ],
  columnDefs: [
    {
      targets: 6,
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
    url: "http://localhost:8080/drinks" + "/" + gId,
    success: function (resDrink) {
      console.log(resDrink);

      $("#input-drink-code").val(resDrink.maNuocUong);
      $("#input-drink-name").val(resDrink.tenNuocUong);
      $("#input-price").val(resDrink.donGia);
      $("#input-createDate").val((resDrink.ngayTao).split("-").reverse().join("-"));
      $("#input-updateDate").val((resDrink.ngayCapNhat).split("-").reverse().join("-"));
    }
  });
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  //B0: tạo biến lưu dữ liệu
  var vUpdateObj = {
    id: gId,
    donGia: null,
    maNuocUong: "",
    ngayCapNhat: "",
    ngayTao: "",
    tenNuocUong: ""
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
        url: "http://localhost:8080/drinks" + "/" + gId,
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resDrink) {
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
        url: "http://localhost:8080/drinks",
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resDrink) {
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
    url: "http://localhost:8080/drinks" + "/" + gId,
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
    url: "http://localhost:8080/drinks",
    success: function (response) {
      loadAllDataToTable();
      resetData();
      $("#modal-del-all").modal("hide");
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Ham xu ly load data vouchers to Table
function loadAllDataToTable() {
  $.ajax({
    url: "http://localhost:8080/drinks",
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
  paramObj.donGia = parseInt($("#input-price").val());
  paramObj.maNuocUong = $("#input-drink-code").val();
  paramObj.tenNuocUong = $("#input-drink-name").val();
  paramObj.ngayTao = $("#input-createDate").val();
  paramObj.ngayCapNhat = $("#input-updateDate").val();
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  if (paramObj.maNuocUong == "") {
    alert("Thiếu mã drink");
    return false;
  }
  if (paramObj.tenNuocUong == "") {
    alert("Thiếu tên drink");
    return false;
  }
  if (paramObj.ngayTao == "") {
    alert("Thiếu ngày tạo");
    return false;
  }
  if (isNaN(paramObj.donGia)) {
    alert("Phải là số");
    return false
  }
  return true;
}

// Hàm reset dữ liệu
function resetData() {
  gFormMode = gNORMAL_MODE;

  $("#input-drink-code").val("");
  $("#input-drink-name").val("");
  $("#input-price").val("");
  $("#input-createDate").val("");
  $("#input-updateDate").val("");

  gId = null;
}

