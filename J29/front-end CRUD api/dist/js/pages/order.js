"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#order-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "comments" },
    { "data": "orderDate" },
    { "data": "requiredDate" },
    { "data": "shippedDate" },
    { "data": "status" },
    { "data": "customer" }
  ],
  columnDefs: [
    {
      targets: 6,
      render: function (customerObj) {
        return (customerObj.firstName + " " + customerObj.lastName);
      }
    },
    {
      targets: 7,
      defaultContent:
        `
        <button style="color:deepskyblue" class='btn btn-outline-light btn-sm edit'>
          <i class='fas fa-edit'></i>
        </button>	
        <button style="color:palevioletred" class='btn btn-outline-light btn-sm delete'>
          <i class='far fa-trash-alt'></i>
        </button>
        <button class='btn btn-outline-warning btn-sm order_detail' data-toggle="tooltip" title="Order details">
          <i class="fas fa-list"></i>
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

var gCustomerId = null;
var gOrderId = null;

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
  $("#modal-add-edit-order").on("hidden.bs.modal", resetData);

  // gán sự kiện click vào btn Show orderDetails
  $(document).on("click", ".order_detail", function () {
    onBtnShowOrderDetailsClick(this);
  })
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  "use strict";
  // check xem có customerId ở query string từ page khác truyền vào không
  var vUrlString = window.location.href;
  var vUrl = new URL(vUrlString);
  gCustomerId = vUrl.searchParams.get("customerId");
  console.log(gCustomerId)
  if (gCustomerId != null) {
    showOrdersByCustomerId();
    resetData();

  } else { // nếu không có data trên query string thì load như bình thường
    loadAllDataToTable();
  }
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
  gOrderId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get drink theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/order" + "/" + gOrderId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-comments").val(res.comments);
      $("#input-orderDate").val((res.orderDate).split("-").reverse().join("-"));
      $("#input-requiredDate").val((res.requiredDate).split("-").reverse().join("-"));
      $("#input-shippedDate").val((res.shippedDate).split("-").reverse().join("-"));
      $("#input-status").val(res.status);
      $("#input-customer_id").val((res.customer).id);
      $("#input-customer_id").attr("disabled", "disabled");

      $("#modal-add-edit-order").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  $("#modal-add-edit-order").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vOrderObj = {
    comments: "",
    orderDate: "",
    requiredDate: "",
    shippedDate: "",
    status: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vOrderObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vOrderObj);
  if (vIsDataValid) {
    console.log(vOrderObj)
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/order" + "/" + gOrderId,
        data: JSON.stringify(vOrderObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-order").modal("hide");
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
        url: "http://localhost:8080/order" + "/" + gCustomerId,
        data: JSON.stringify(vOrderObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-order").modal("hide");
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
  gOrderId = vRowObj.id;
}

// Hàm xử lý sự kiện click Confirm Delete 1 Order
function onConfirmDelOneClick() {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/order" + "/" + gOrderId,
    success: function (res) {
      loadAllDataToTable();
      resetData();
      $("#modal-delete").modal("hide");
      toastr.success("DELETE thành công   &#128519;");
    }
  });
}

// Hàm xử lý sự kiện click btn Show orderDetails
function onBtnShowOrderDetailsClick(paramBtn) {
  "use strict";
  // thu thập orderId
  var vRow = $(paramBtn).parents("tr");
  var vRowObj = gTable.row(vRow).data();
  gOrderId = vRowObj.id;

  // gọi và truyền customerId sang trang Payment
  var vUrlOrderDetailToOpen = "crudOrderDetail.html" + "?orderId=" + gOrderId;
  console.log(gOrderId)
  window.location.href = vUrlOrderDetailToOpen;
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm xử lý load data lên DataTable
function loadAllDataToTable() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/order/all",
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

// Hàm xử lý load data theo customerId lên DataTabe
function showOrdersByCustomerId() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/order/customerId" + "/" + gCustomerId,
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
  paramObj.comments = $("#input-comments").val();
  paramObj.orderDate = ($("#input-orderDate").val()).split("-").reverse().join("-");
  paramObj.requiredDate = ($("#input-requiredDate").val()).split("-").reverse().join("-");
  paramObj.shippedDate = ($("#input-shippedDate").val()).split("-").reverse().join("-");
  paramObj.status = $("#input-status").val();
  if (gFormMode == gCREATE_MODE) {
    gCustomerId = parseInt($("#input-customer_id").val());
  }
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.comments == "") {
    toastr.error("Thiếu comments &#128545;");
    return false;
  }
  if (paramObj.orderDate == "") {
    toastr.error("Thiếu orderDate &#128545;");
    return false;
  }
  if (paramObj.requiredDate == "") {
    toastr.error("Thiếu requiredDate &#128545;");
    return false;
  }
  if (paramObj.shippedDate == "") {
    toastr.error("Thiếu shippedDate &#128545;");
    return false;
  }
  if (paramObj.status == "") {
    toastr.error("Thiếu status &#128545;");
    return false;
  }
  if (gFormMode == gCREATE_MODE) {
    if (isNaN(gCustomerId)) {
      toastr.error("customer_id phải là một số &#128545;");
      return false;
    }
  }
  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-comments").val("");
  $("#input-orderDate").val("");
  $("#input-requiredDate").val("");
  $("#input-shippedDate").val("");
  $("#input-status").val("");
  $("#input-customer_id").val("");
  $("#input-customer_id").removeAttr("disabled");

  gCustomerId = null;
  gOrderId = null;
  gSTT = 1;
}

