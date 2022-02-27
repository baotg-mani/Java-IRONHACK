"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#payment-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "checkNumber" },
    { "data": "paymentDate" },
    { "data": "amount" },
    { "data": "customer" }
  ],
  columnDefs: [
    {
      targets: 4,
      render: function (customerObj) {
        return (customerObj.firstName + " " + customerObj.lastName);
      }
    },
    {
      targets: 5,
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

var gPaymentId = null;
var gCustomerId = null;

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
  $("#modal-add-edit-payment").on("hidden.bs.modal", resetData);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Hàm xử lý sự kiện load Page
function onPageLoading() {
  // check xem có customerId ở query string từ page khác truyền vào không
  var vUrlString = window.location.href;
  var vUrl = new URL(vUrlString);
  gCustomerId = vUrl.searchParams.get("customerId");
  console.log(gCustomerId)
  if (gCustomerId != null) {
    showPaymentsByCustomerId();
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
  gPaymentId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get drink theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/payment" + "/" + gPaymentId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-checkNumber").val(res.checkNumber);
      $("#input-paymentDate").val((res.paymentDate).split("-").reverse().join("-"));
      $("#input-amount").val(res.amount);
      $("#input-customer-id").val((res.customer).id);
      $("#input-customer-id").attr("disabled", "disabled");

      $("#modal-add-edit-payment").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  $("#modal-add-edit-payment").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vPaymentObj = {
    checkNumber: "",
    paymentDate: "",
    amount: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vPaymentObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vPaymentObj);
  if (vIsDataValid) {
    console.log(vPaymentObj)
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/payment" + "/" + gPaymentId,
        data: JSON.stringify(vPaymentObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-payment").modal("hide");
          toastr.success("UPDATE thành công! &#x1F618;");
          resetData();
        }
      });
    } else { //Create payment (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      $.ajax({

        type: "POST",
        url: "http://localhost:8080/payment" + "/" + gCustomerId,
        data: JSON.stringify(vPaymentObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-payment").modal("hide");
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
  gPaymentId = vRowObj.id;
}

// Hàm xử lý sự kiện click Confirm Delete 1 Payment
function onConfirmDelOneClick() {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/payment" + "/" + gPaymentId,
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
    url: "http://localhost:8080/payment/all",
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

// Hàm xử lý load data theo customerId lên DataTable
function showPaymentsByCustomerId() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/payment/customerId" + "/" + gCustomerId,
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
  paramObj.checkNumber = $("#input-checkNumber").val();
  paramObj.paymentDate = ($("#input-paymentDate").val()).split("-").reverse().join("-");
  paramObj.amount = parseInt($("#input-amount").val());
  if (gFormMode == gCREATE_MODE) {
    gCustomerId = parseInt($("#input-customer-id").val());
  }
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.checkNumber == "") {
    toastr.error("Thiếu checkNumber &#128545;");
    return false;
  }
  if (paramObj.paymentDate == "") {
    toastr.error("Thiếu paymentDate &#128545;");
    return false;
  }
  if (isNaN(paramObj.amount)) {
    toastr.error("amount phải là một số! &#128545;");
    return false;
  }
  if (gFormMode == gCREATE_MODE) {
    if (isNaN(gCustomerId)) {
      toastr.error("Customer ID phải là một số! &#128545;");
      return false;
    }
  }
  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-checkNumber").val("");
  $("#input-paymentDate").val("");
  $("#input-amount").val("");
  $("#input-customer-id").val("");
  $("#input-customer-id").removeAttr("disabled");

  gPaymentId = null;
  gCustomerId = null;
  gSTT = 1;
}

