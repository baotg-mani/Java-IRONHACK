"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#customer-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "address" },
    { "data": "city" },
    { "data": "country" },
    { "data": "creditLimit" },
    { "data": "firstName" },
    { "data": "lastName" },
    { "data": "phoneNumber" },
    { "data": "postalCode" },
    { "data": "salesRepEmployeeNumber" },
    { "data": "state" }
  ],
  columnDefs: [
    {
      targets: 11,
      defaultContent:
        `
        <button style="color:deepskyblue" class='btn btn-outline-light btn-sm edit'>
          <i class='fas fa-edit'></i>
        </button>	
        <button style="color:palevioletred" class='btn btn-outline-light btn-sm delete'>
          <i class='far fa-trash-alt'></i>
        </button>
        <button class='btn btn-outline-success btn-sm payment' data-toggle="tooltip" title="Show payments">
          <i class="fas fa-money-check-alt nav-icon"></i>
        </button>
        <button class='btn btn-outline-warning btn-sm order' data-toggle="tooltip" title="Show oders">
          <i class="fas fa-dolly"></i>
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
  $("#modal-add-edit-customer").on("hidden.bs.modal", resetData);

  // gán sự kiện click cho btn Show Payments
  $(document).on("click", ".payment", function () {
    onBtnShowPaymentsClick(this);
  });

  // gán sự kiện click cho btn Show Orders
  $(document).on("click", ".order", function () {
    onBtnShowOrdersClick(this);
  });
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
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
  gCustomerId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get drink theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/customer" + "/" + gCustomerId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-address").val(res.address);
      $("#input-city").val(res.city);
      $("#input-country").val(res.country);
      $("#input-creditLimit").val(res.creditLimit);
      $("#input-firstName").val(res.firstName);
      $("#input-lastName").val(res.lastName);
      $("#input-phoneNumber").val(res.phoneNumber);
      $("#input-postalCode").val(res.postalCode);
      $("#input-salesRepEmployeeNumber").val(res.salesRepEmployeeNumber);
      $("#input-state").val(res.state);

      $("#modal-add-edit-customer").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  $("#modal-add-edit-customer").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vCustomerObj = {
    address: "",
    city: "",
    country: "",
    creditLimit: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    postalCode: "",
    salesRepEmployeeNumber: "",
    state: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vCustomerObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vCustomerObj);
  if (vIsDataValid) {
    console.log(vCustomerObj)
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/customer" + "/" + gCustomerId,
        data: JSON.stringify(vCustomerObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-customer").modal("hide");
          toastr.success("UPDATE thành công! &#x1F618;");
          resetData();
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText + " &#128545;");
        }
      });
    } else { //Create customer (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      $.ajax({

        type: "POST",
        url: "http://localhost:8080/customer",
        data: JSON.stringify(vCustomerObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-customer").modal("hide");
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
  gCustomerId = vRowObj.id;
}

// Hàm xử lý sự kiện click Confirm Delete 1 Customer
function onConfirmDelOneClick() {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/customer" + "/" + gCustomerId,
    success: function (res) {
      loadAllDataToTable();
      resetData();
      $("#modal-delete").modal("hide");
      toastr.success("DELETE thành công   &#128519;");
    }
  });
}

// Hàm xử lý sư kiện click btn Show Payments 
function onBtnShowPaymentsClick(paramBtn) {
  "use strict";
  // thu thập customerId
  var vRow = $(paramBtn).parents("tr");
  var vRowObj = gTable.row(vRow).data();
  gCustomerId = vRowObj.id;

  // gọi và truyền customerId sang trang Payment
  var vUrlPaymentToOpen = "crudPayment.html" + "?customerId=" + gCustomerId;
  console.log(gCustomerId)
  window.location.href = vUrlPaymentToOpen;
}

// Hàm xử lý sự kiện click btn Show Orders
function onBtnShowOrdersClick(paramBtn) {
  "use strict";
  // thu thập customerId
  var vRow = $(paramBtn).parents("tr");
  var vRowObj = gTable.row(vRow).data();
  gCustomerId = vRowObj.id;

  // gọi và truyền customerId sang trang Order
  var vUrlOrderToOpen = "crudOrder.html" + "?customerId=" + gCustomerId;
  window.location.href = vUrlOrderToOpen;
}


/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm xử lý load data lên Table
function loadAllDataToTable() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/customer/all",
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
  paramObj.address = $("#input-address").val();
  paramObj.city = $("#input-city").val();
  paramObj.country = $("#input-country").val();
  paramObj.creditLimit = parseInt($("#input-creditLimit").val());
  paramObj.firstName = $("#input-firstName").val();
  paramObj.lastName = $("#input-lastName").val();
  paramObj.phoneNumber = $("#input-phoneNumber").val();
  paramObj.postalCode = $("#input-postalCode").val();
  paramObj.salesRepEmployeeNumber = parseInt($("#input-salesRepEmployeeNumber").val());
  paramObj.state = $("#input-state").val();
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.address == "") {
    toastr.error("Thiếu address &#128545;");
    return false;
  }
  if (paramObj.city == "") {
    toastr.error("Thiếu city &#128545;");
    return false;
  }
  if (paramObj.country == "") {
    toastr.error("Thiếu country &#128545;");
    return false;
  }
  if (isNaN(paramObj.creditLimit)) {
    toastr.error("creditLimit phải là một số! &#128545;");
    return false;
  }
  if (paramObj.firstName == "") {
    toastr.error("Thiếu firstName &#128545;");
    return false;
  }
  if (paramObj.lastName == "") {
    toastr.error("Thiếu lastName &#128545;");
    return false;
  }
  if (paramObj.phoneNumber == "") {
    toastr.error("Thiếu phoneNumber &#128545;");
    return false;
  }
  if (paramObj.postalCode == "") {
    toastr.error("Thiếu postalCode &#128545;");
    return false;
  }
  if (isNaN(paramObj.salesRepEmployeeNumber)) {
    toastr.error("salesRepEmployeeNumber phải là một số! &#128545;");
    return false;
  }
  if (paramObj.state == "") {
    toastr.error("Thiếu state &#128545;");
    return false;
  }
  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-address").val("");
  $("#input-city").val("");
  $("#input-country").val("");
  $("#input-creditLimit").val("");
  $("#input-firstName").val("");
  $("#input-lastName").val("");
  $("#input-phoneNumber").val("");
  $("#input-postalCode").val("");
  $("#input-salesRepEmployeeNumber").val("");
  $("#input-state").val("");

  gCustomerId = null;
  gSTT = 1;
}

