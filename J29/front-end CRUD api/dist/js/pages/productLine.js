"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#product-line-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "productLine" },
    { "data": "description" }
  ],
  columnDefs: [
    {
      targets: 3,
      defaultContent:
        `
        <button style="color:deepskyblue" class='btn btn-outline-light btn-sm edit'>
          <i class='fas fa-edit'></i>
        </button>	
        <button style="color:palevioletred" class='btn btn-outline-light btn-sm delete'>
          <i class='far fa-trash-alt'></i>
        </button>
        `
    },
    {
      targets: 0,
      render: function () {
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

var gProductLineId = null;

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

  // gán sự kiện khi ẩn modal add-edit đi (sự kiện reset data)
  $("#modal-add-edit-product-line").on("hidden.bs.modal", resetData);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  "use strict";
  loadAllDataToTable();
}

// Hàm xử lý khi click btn Edit
function onBtnEditClick(paramEdit) {
  "use strict";
  // thay đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: tạo obj lưu data
  var vRowObj = {};
  //B1: thu thập data
  vRowObj = gTable.row($(paramEdit).parents("tr")).data();
  gProductLineId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get product line theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/product-line" + "/" + gProductLineId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-productLine").val(res.productLine);
      $("#input-description").val(res.description);

      $("#modal-add-edit-product-line").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  $("#modal-add-edit-product-line").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vProductLineObj = {
    productLine: "",
    description: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vProductLineObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vProductLineObj);
  if (vIsDataValid) {
    console.log(vProductLineObj);
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/product-line" + "/" + gProductLineId,
        data: JSON.stringify(vProductLineObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-product-line").modal("hide");
          toastr.success("UPDATE thành công! &#x1F618;");
          resetData();
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText + " &#128545;");
        }
      });
    } else { //Create order (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/product-line",
        data: JSON.stringify(vProductLineObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-product-line").modal("hide");
          toastr.success("CREATE thành công   &#x1F618;");
          resetData();
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText + " &#128545;");
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
  gProductLineId = vRowObj.id;
}

// Hàm xử lý sự kiện click Confirm Delete 1 Order
function onConfirmDelOneClick() {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/product-line" + "/" + gProductLineId,
    success: function (res) {
      loadAllDataToTable();
      resetData();
      $("#modal-delete").modal("hide");
      toastr.success("DELETE thành công   &#128519;");
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm xử lý load data lên DataTable
function loadAllDataToTable() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/product-line/all",
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
  paramObj.productLine = $("#input-productLine").val();
  paramObj.description = $("#input-description").val();
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.productLine == "") {
    toastr.error("Thiếu productLine &#128545;");
    return false;
  }
  if (paramObj.description == "") {
    toastr.error("Thiếu description &#128545;");
    return false;
  }
  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-productLine").val("");
  $("#input-description").val("");

  gProductLineId = null;
  gSTT = 1;
}

