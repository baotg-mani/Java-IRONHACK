"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gSTT = 1;
var gTable = $("#product-table").DataTable({
  "ordering": false,
  "columns": [
    { "data": "id" },
    { "data": "productCode" },
    { "data": "productName" },
    { "data": "productDescription" },
    { "data": "productLineObj" },
    { "data": "productScale" },
    { "data": "productVendor" },
    { "data": "quantityInStock" },
    { "data": "buyPrice" }
  ],
  columnDefs: [
    {
      targets: 4,
      render: function (productLineObj) {
        return productLineObj.id;
      }
    },
    {
      targets: 9,
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
var gProductId = null;

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
  $("#modal-add-edit-product").on("hidden.bs.modal", resetData);

});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  "use strict";
  // check xem có customerId ở query string từ page khác truyền vào không
  var vUrlString = window.location.href;
  var vUrl = new URL(vUrlString);
  gProductLineId = vUrl.searchParams.get("productLineId");
  console.log(gProductLineId)
  if (gProductLineId != null) {
    showProductsByProductLineId();
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
  gProductId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get drink theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/product" + "/" + gProductId,
    success: function (res) {
      console.log(res);
      //B4: xử lý hiển thị thông tin lên modal
      $("#input-productCode").val(res.productCode);
      $("#input-productName").val(res.productName);
      $("#input-productDescription").val(res.productDescription);
      $("#input-product_line_id").val((res.productLineObj).id);
      $("#input-productScale").val(res.productScale);
      $("#input-productVendor").val(res.productVendor);
      $("#input-quantityInStock").val(res.quantityInStock);
      $("#input-buyPrice").val(res.buyPrice);
      $("#input-product_line_id").attr("disabled", "disabled");

      $("#modal-add-edit-product").modal("show");
    }
  });
}

// Hàm xử lý sự kiện click vào nút Add new
function onBtnAddClick() {
  "use strict";
  gFormMode = gCREATE_MODE;
  $("#modal-add-edit-product").modal("show");
}

// Hàm xử lý sự kiện click vào nút Sava data
function onBtnSaveData() {
  "use strict";
  //B0: tạo biến lưu dữ liệu
  var vProductObj = {
    productCode: "",
    productName: "",
    productDescription: "",
    productScale: "",
    productVendor: "",
    quantityInStock: "",
    buyPrice: ""
  }
  //B1: thu thập dữ liệu
  getDataOnForm(vProductObj);
  //B2: validate dữ liệu
  var vIsDataValid = validateData(vProductObj);
  if (vIsDataValid) {
    console.log(vProductObj);
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/product" + "/" + gProductId,
        data: JSON.stringify(vProductObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-product").modal("hide");
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
        url: "http://localhost:8080/product" + "/" + gProductLineId,
        data: JSON.stringify(vProductObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (res) {
          //B4: xử lý hiển thị front end
          loadAllDataToTable();
          $("#modal-add-edit-product").modal("hide");
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
  gProductId = vRowObj.id;
}

// Hàm xử lý sự kiện click Confirm Delete 1 Order
function onConfirmDelOneClick() {
  "use strict";
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/product" + "/" + gProductId,
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
    url: "http://localhost:8080/product/all",
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

// Hàm xử lý load data theo productLineId lên DataTabe
function showProductsByProductLineId() {
  "use strict";
  $.ajax({
    url: "http://localhost:8080/product/productLineId" + "/" + gProductLineId,
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
  paramObj.productCode = $("#input-productCode").val();
  paramObj.productName = $("#input-productName").val();
  paramObj.productDescription = $("#input-productDescription").val();
  paramObj.productScale = $("#input-productScale").val();
  paramObj.productVendor = $("#input-productVendor").val();
  paramObj.quantityInStock = parseInt($("#input-quantityInStock").val());
  paramObj.buyPrice = parseInt($("#input-buyPrice").val());
  if (gFormMode == gCREATE_MODE) {
    gProductLineId = parseInt($("#input-product_line_id").val());
  }
}

// Hàm kiểm tra dữ liệu
function validateData(paramObj) {
  "use strict";
  if (paramObj.productCode == "") {
    toastr.error("Thiếu productCode &#128545;");
    return false;
  }
  if (paramObj.productName == "") {
    toastr.error("Thiếu productName &#128545;");
    return false;
  }
  if (paramObj.productDescription == "") {
    toastr.error("Thiếu productDescription &#128545;");
    return false;
  }
  if (gFormMode == gCREATE_MODE) {
    if (isNaN(gProductLineId)) {
      toastr.error("product_line_id phải là một số &#128545;");
      return false;
    }
  }
  if (paramObj.productScale == "") {
    toastr.error("Thiếu productScale &#128545;");
    return false;
  }
  if (paramObj.productVendor == "") {
    toastr.error("Thiếu productVendor &#128545;");
    return false;
  }
  if (isNaN(paramObj.quantityInStock)) {
    toastr.error("quantityInStock phải là một số! &#128545;");
    return false;
  }
  if (isNaN(paramObj.buyPrice)) {
    toastr.error("productScale phải là một số! &#128545;");
    return false;
  }

  return true;
}

function resetData() {
  "use strict";
  gFormMode = gNORMAL_MODE;
  $("#input-productCode").val("");
  $("#input-productName").val("");
  $("#input-productDescription").val("");
  $("#input-shippedDate").val("");
  $("#input-productScale").val("");
  $("#input-productVendor").val("");
  $("#input-quantityInStock").val("");
  $("#input-buyPrice").val("");
  $("#input-product_line_id").val("");

  $("#input-product_line_id").removeAttr("disabled");

  gProductLineId = null;
  gProductId = null;
  gSTT = 1;
}

