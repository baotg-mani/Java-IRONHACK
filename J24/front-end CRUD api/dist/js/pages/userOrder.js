"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gUserTable = null;
var gOrderTable = null;

const gNORMAL_MODE = "normal";
const gCREATE_MODE = "create";
const gUPDATE_MODE = "update";
const gDELETE_MODE = "delete";
var gFormMode = gNORMAL_MODE;
var gId = null;
var gUser = null;
var gOrder = null;

/*** REGION 2 - Vùng gán / thực thi hàm xử lý sự kiện cho các elements */
$(document).ready(function () {
  onPageLoading();

  $(document).on("click", ".edit", function () {
    onEditUserClick(this);
  });

  // gán sự kiện click vào btn Save data
  $("#save_data").on("click", onBtnSaveUser);

  // gán sự kiện click vào icon Delete
  $(document).on("click", ".delete", function () {
    onBtnDeleteUserClick(this);
  });

  // gán sự kiện click Confirm Delete Row
  $("#confirm-del-one").on("click", function () {
    onConfirmDelOneUserClick();
  });

  // gán sự kiện click vào btn Delete ALL
  $("#del_all").on("click", function () {
    onBtnDelAllUserClick();
  });

  // gán sự kiện click Confirm Delete All
  $("#confirm-del").on("click", function () {
    onConfirmDelAllUserClick();
  })

  // xử lý sự kiện khi ẩn modal delete all
  $("#modal-del-all-user").on("hidden.bs.modal", function () {
    resetData();
  });

  $("#user-list").on("click", onBtnUserListClick);
  $("#order-list").on("click", onBtnOrderListClick);

  // gán sự kiện click btn Add order
  $(document).on("click", ".add_order", function () {
    onBtnAddOrderClick(this);
  });

  // gán sự kiện click confirm Save Order
  $("#save-order").on("click", function () {
    onBtnSaveOrderClick();
  })

  // gán sự kiện click cho btn Edit Order
  $(document).on("click", ".edit_order", function () {
    onEditOrderClick(this);
  });

  // gán sự kiện click cho btn Del Order
  $(document).on("click", ".delete_order", function () {
    onDeleteOrderClick(this);
  });

  // gá sự kiện click confirm Delete Order
  $("#confirm-del-order").click(onConfirmDelOrderClick);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  onBtnUserListClick();
}

// Hàm xử lý khi click vào btn Edit trong bảng
function onEditUserClick(paramEdit) {
  // thay đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: tạo obj lưu data
  var vRowObj = {};
  //B1: thu thập data
  vRowObj = gUserTable.row($(paramEdit).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get user theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/user/details" + "/" + gId,
    success: function (resUser) {
      console.log(resUser);

      $("#input-fullname").val(resUser.fullname);
      $("#input-email").val(resUser.email);
      $("#input-phone").val(resUser.phone);
      $("#input-address").val(resUser.address);
    }
  });
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveUser() {
  //B0: tạo biến lưu dữ liệu
  var vUpdateObj = {
    id: gId,
    fullname: "",
    email: "",
    phone: "",
    address: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vUpdateObj);
  //B2: validate dữ liệu
  var vIsUserValid = validateUser(vUpdateObj);
  if (vIsUserValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/user/update" + "/" + gId,
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resUser) {
          //B4: xử lý hiển thị front end
          onBtnUserListClick();
          resetData();
        }
      });
    } else { //Create user (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      console.log("ok")
      vUpdateObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/create",
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resUser) {
          //B4: xử lý hiển thị front end
          onBtnUserListClick();
          resetData();
        }
      });
    }
  }
}

// Hàm xử lý sự kiện Delete
function onBtnDeleteUserClick(paramDel) {
  // đổi trạng thái
  gFormMode = gDELETE_MODE;
  // hiển thị modal
  $("#modal-del-one-user").modal("show");
  // thu thập dữ liệu ID
  var vRow = $(paramDel).parents("tr");
  var vRowObj = gUserTable.row(vRow).data();
  gId = vRowObj.id;
  console.log(gId);
}

// Hàm xử lý sự kiện click Confirm Delete 1 Drink
function onConfirmDelOneUserClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/user/delete" + "/" + gId,
    success: function (response) {
      onBtnUserListClick();
      resetData();
      $("#modal-del-one-user").modal("hide");
    }
  });
}

// Hàm xử lý sự kiện xóa tất cả dữ liệu vouchers
function onBtnDelAllUserClick() {
  // hiển thị modal del ALL
  $("#modal-del-all-user").modal("show");
  // thay đổi trạng thái
  gFormMode = gDELETE_MODE;
}

// Hàm xử lý sự kiện click Confirm xóa ALL
function onConfirmDelAllUserClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/user/delete/all",
    success: function (response) {
      onBtnUserListClick();
      resetData();
      $("#modal-del-all-user").modal("hide");
    }
  });
}

// Hàm xử lý sự kiện click nút User List exchange
function onBtnUserListClick() {
  // load lại table
  // check: nếu đã có DataTable rồi thì hủy và làm trống thead ở trong DOM
  if ($.fn.dataTable.isDataTable('#my-table')) {
    $("#my-table").DataTable().destroy();
    $("#my-table").html("");
    $("#my-table").append("<thead></thead><tbody></tbody>");
  }
  // sau đó tạo lại các cột trong thead
  $("#my-table thead").append(`
      <tr>
        <th>STT</th>
        <th>Fullname</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gUserTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { data: "id" },
      { data: "fullname" },
      { data: "email" },
      { data: "phone" },
      { data: "address" }
    ],
    columnDefs: [
      {
        targets: 5,
        defaultContent:
          `
            <button class='btn btn-info btn-sm edit'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete'>
              <i class='far fa-trash-alt'></i>
            </button>
            <button class='btn btn-primary btn-sm add_order'>
            <i class='fas fa-plus'></i> Order
            </button>
          `
      }
    ]
  });
  // call API get data users
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/user/all",
    success: function (resOrderList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gUserTable.clear();

      //Cập nhật data cho bảng 
      gUserTable.rows.add(resOrderList);

      //Cập nhật lại giao diện hiển thị bảng
      gUserTable.draw();
    }
  });
}

// Hàm xử lý sự kiện khi click nút Order List exchange
function onBtnOrderListClick() {
  // load lại table
  // check: nếu đã có DataTable rồi thì hủy và làm trống thead ở trong DOM
  if ($.fn.dataTable.isDataTable('#my-table')) {
    $("#my-table").DataTable().destroy();
    $("#my-table").html("");
    $("#my-table").append("<thead></thead><tbody></tbody>");
  }
  // sau đó tạo lại các cột trong thead
  $("#my-table thead").append(`
      <tr>
        <th>STT</th>
        <th>Order code</th>
        <th>Pizza size</th>
        <th>Pizza type</th>
        <th>Voucher code</th>F
        <th>Price</th>
        <th>Paid</th>
        <th>User id</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gOrderTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { "data": "id" },
      { "data": "orderCode" },
      { "data": "pizzaSize" },
      { "data": "pizzaType" },
      { "data": "voucherCode" },
      { "data": "price" },
      { "data": "paid" },
      { "data": "user" }
    ],
    columnDefs: [
      {
        targets: 7,
        render: function (userObj) {
          return renderUserId(userObj);
        }
      },
      {
        targets: 8,
        defaultContent:
          `
            <button class='btn btn-info btn-sm edit_order'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete_order'>
              <i class='far fa-trash-alt'></i>
            </button>
          `
      }
    ]
  });
  // call API get data orders
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/order/all",
    success: function (resOrderList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gOrderTable.clear();

      //Cập nhật data cho bảng 
      gOrderTable.rows.add(resOrderList);

      //Cập nhật lại giao diện hiển thị bảng
      gOrderTable.draw();
    }
  });
}

// Hàm xử lý sự kiện click btn Add order
function onBtnAddOrderClick(paramAddOrder) {
  gFormMode = gCREATE_MODE;
  // thu thập dữ liệu ID của User mà muốn add order
  var vRowObj = gUserTable.row($(paramAddOrder).parents("tr")).data();
  gId = vRowObj.id; // id user
  gUser = vRowObj;
  // hiển thị modal add order
  $("#modal-add-edit-order").modal("show");
}

// Hàm xử lý sự kiện click Save Order
function onBtnSaveOrderClick() {
  //B0: tạo biến lưu trữ dữ liệu new order
  var vNewOrder = {
    id: null,
    orderCode: "",
    pizzaSize: "",
    pizzaType: "",
    voucherCode: "",
    price: null,
    paid: null
    // user: gUser
  }
  //B1: thu thập dữ liệu (ID)
  getOrderDataOnModal(vNewOrder);
  console.log(vNewOrder);
  var vIsOrderValid = validateOrder(vNewOrder);
  if (vIsOrderValid) {
    //B3: call api xử lý POST dữ liệu hoặc PUT dữ liệu
    if (gFormMode == gCREATE_MODE) { // for POST protocol
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/create" + "/" + gId,
        data: JSON.stringify(vNewOrder),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resOrder) {
          console.log(resOrder);
          //B4: xử lý hiển thị front-end
          toastr.success("successfully CREATED order");
          $("#modal-add-edit-order").modal("hide");
          resetData();
        },
        error: function (ajaxContext) {
          alert(ajaxContext.responseText);
        }
      });
    } else { // for PUT protocol
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/order/update" + "/" + gId, // gId là order id
        data: JSON.stringify(vNewOrder),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resUpdatedOrder) {
          console.log(resUpdatedOrder);
          //B4: xử lý hiển thị front-end
          toastr.success("successfully UPDATED order");
          $("#modal-add-edit-order").modal("hide");
          // load lại table order
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/order/all",
            success: function (resAllOrder) {
              gOrderTable.clear();
              gOrderTable.rows.add(resAllOrder);
              gOrderTable.draw();
            }
          });
          resetData();
        },
        error: function (ajaxContext) {
          alert(ajaxContext.responseText);
        }
      });
    }
  }
}

// Hàm xử lý sự kiện click btn Edit Order
function onEditOrderClick(paramEditOrder) {
  // đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: khai báo biến lưu dữ liệu order
  var vOrder = {};
  //B1: thu thập dữ liệu (order id)
  var vRowObj = gOrderTable.row($(paramEditOrder).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call Api lấy dữ liệu theo order id
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/order/details" + "/" + gId,
    success: function (resOrder) {
      //B4: xử lý dữ liệu
      vOrder = resOrder;
      //B5: xử lý hiển thị
      $("#modal-add-edit-order").modal("show"); // show modal
      // hiển thị dữ liệu order lên form
      $("#input-order-code").val(vOrder.orderCode);
      $("#input-pizza-size").val(vOrder.pizzaSize);
      $("#input-pizza-type").val(vOrder.pizzaType);
      $("#input-voucher-code").val(vOrder.voucherCode);
      $("#input-price").val(vOrder.price);
      $("#input-paid").val(vOrder.paid);
    }
  });
}

// Hàm xử lý sự kiện click btn Delete Order
function onDeleteOrderClick(paramDelOrder) {
  gFormMode = gDELETE_MODE;
  // thu thập id order
  var vRowObj = gOrderTable.row($(paramDelOrder).parents("tr")).data();
  gId = vRowObj.id;
  // hiển thị modal
  $("#modal-del-one-order").modal("show");
}

// Hàm xử lý sự kiện click Confirm delete Order
function onConfirmDelOrderClick() {
  // call api xóa dữ liệu order
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/order/delete" + "/" + gId,
    success: function (response) {
      toastr.success("successfully deleted order");
      // load lại table Order
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/order/all",
        success: function (resAllOrder) {
          gOrderTable.clear();
          gOrderTable.rows.add(resAllOrder);
          gOrderTable.draw();
          // reset và ẩn modal
          resetData();
          $("#modal-del-one-order").modal("hide");
        }
      });
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm thu thập dữ liệu trên form
function getDataOnForm(paramObj) {
  paramObj.fullname = $("#input-fullname").val();
  paramObj.email = $("#input-email").val();
  paramObj.phone = $("#input-phone").val();
  paramObj.address = $("#input-address").val();
}

// Hàm kiểm tra dữ liệu
function validateUser(paramObj) {
  if (paramObj.fullname == "") {
    alert("fullname not found");
    return false;
  }
  if (paramObj.email == "") {
    alert("email not found");
    return false;
  }
  if (!validateEmail(paramObj.email)) {
    alert("Invalid email");
    return false;
  }
  if (paramObj.phone == "") {
    alert("phone not found");
    return false;
  }
  if (paramObj.adress == "") {
    alert("address not found");
    return false
  }
  return true;
}

// Ham validate email
function validateEmail(paramEmail) {
  var vEmailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
  return vEmailReg.test(paramEmail);
}

// Hàm reset dữ liệu
function resetData() {
  gFormMode = gNORMAL_MODE;

  $("#input-fullname").val("");
  $("#input-email").val("");
  $("#input-phone").val("");
  $("#input-address").val("");

  $("#input-order-code").val("");
  $("#input-pizza-size").val("");
  $("#input-pizza-type").val("");
  $("#input-voucher-code").val("");
  $("#input-price").val("");
  $("#input-paid").val("");

  gId = null;
}

// Hàm render prop userObj trả về userId
function renderUserId(paramUserObj) {
  console.log(paramUserObj);
  return paramUserObj.id;
}

// Hàm thu thập dữ liệu Order trên modal
function getOrderDataOnModal(paramOrder) {
  paramOrder.orderCode = $("#input-order-code").val();
  paramOrder.pizzaSize = $("#input-pizza-size").val();
  paramOrder.pizzaType = $("#input-pizza-type").val();
  paramOrder.voucherCode = $("#input-voucher-code").val();
  paramOrder.price = parseInt($("#input-price").val());
  paramOrder.paid = parseInt($("#input-paid").val());
}

// Hàm validate order
function validateOrder(paramOrder) {
  if (paramOrder.orderCode == "") {
    toastr.error("orderCode not found");
    return false;
  }
  if (paramOrder.pizzaSize == "") {
    toastr.error("pizzaSize not found");
    return false;
  }
  if (paramOrder.pizzaType == "") {
    toastr.error("pizzaType not found");
    return false;
  }
  if (isNaN(paramOrder.price)) {
    toastr.error("price must be a number");
    return false;
  }
  if (isNaN(paramOrder.paid)) {
    toastr.error("paid must be a number");
    return false;
  }
  return true;
}
