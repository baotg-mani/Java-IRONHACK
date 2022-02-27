"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gCountryTable = null;
var gRegionTable = null;

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
    onEditCountryClick(this);
  });

  // gán sự kiện click vào btn Save Country
  $("#save-country").on("click", onBtnSaveCountry);

  // gán sự kiện click vào icon Delete country
  $(document).on("click", ".delete", function () {
    onBtnDeleteCountryClick(this);
  });

  // gán sự kiện click Confirm Delete Row country (by id)
  $("#confirm-del-one").on("click", function () {
    onConfirmDelOneCountryClick();
  });

  // gán sự kiện click vào btn Delete ALL country
  $("#del_all").on("click", function () {
    onBtnDelAllCountryClick();
  });

  // gán sự kiện click Confirm Delete All country
  $("#confirm-del").on("click", function () {
    onConfirmDelAllCountryClick();
  })

  // xử lý sự kiện khi ẩn modal delete all
  $("#modal-del-all-country").on("hidden.bs.modal", function () {
    resetData();
  });

  $("#country-list").on("click", onBtnCountryListClick);
  $("#region-list").on("click", onBtnRegionListClick);

  // gán sự kiện click btn Add region
  $(document).on("click", ".add_region", function () {
    onBtnAddRegionClick(this);
  });

  // gán sự kiện click confirm Save Region
  $("#save-region").on("click", function () {
    onBtnSaveRegionClick();
  })

  // gán sự kiện click cho btn Edit Region
  $(document).on("click", ".edit_region", function () {
    onEditRegionClick(this);
  });

  // gán sự kiện click cho btn Del Region
  $(document).on("click", ".delete_region", function () {
    onDeleteRegionClick(this);
  });

  // gá sự kiện click confirm Delete Region
  $("#confirm-del-region").click(onConfirmDelRegionClick);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  onBtnCountryListClick();
}

// Hàm xử lý khi click vào btn Edit trong bảng
function onEditCountryClick(paramEdit) {
  // thay đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: tạo obj lưu data
  var vRowObj = {};
  //B1: thu thập data
  vRowObj = gCountryTable.row($(paramEdit).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get country theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/country/details" + "/" + gId,
    success: function (resCountry) {
      console.log(resCountry);

      $("#input-country-code").val(resCountry.countryCode);
      $("#input-country-name").val(resCountry.countryName);
    }
  });
}

// Hàm xử lý sự kiện click vào nút Sava country
function onBtnSaveCountry() {
  //B0: tạo biến lưu dữ liệu
  var vUpdateObj = {
    id: gId,
    countryCode: "",
    countryName: ""
  }
  //B1: thu thập dữ liệu
  getCountryOnForm(vUpdateObj);
  //B2: validate dữ liệu
  var vIsCountryValid = validateCountry(vUpdateObj);
  if (vIsCountryValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/country/update" + "/" + gId,
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resCountry) {
          //B4: xử lý hiển thị front end
          onBtnCountryListClick();
          resetData();
        }
      });
    } else { //Create country (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      vUpdateObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/country/create",
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resCountry) {
          //B4: xử lý hiển thị front end
          onBtnCountryListClick();
          resetData();
        }
      });
    }
  }
}

// Hàm xử lý sự kiện Delete Country theo id
function onBtnDeleteCountryClick(paramDel) {
  // đổi trạng thái
  gFormMode = gDELETE_MODE;
  // hiển thị modal
  $("#modal-del-one-country").modal("show");
  // thu thập dữ liệu ID
  var vRow = $(paramDel).parents("tr");
  var vRowObj = gCountryTable.row(vRow).data();
  gId = vRowObj.id;
  console.log(gId);
}

// Hàm xử lý sự kiện click Confirm Delete 1 Drink
function onConfirmDelOneCountryClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/user/delete" + "/" + gId,
    success: function (response) {
      onBtnCountryListClick();
      resetData();
      $("#modal-del-one-country").modal("hide");
    }
  });
}

// Hàm xử lý sự kiện xóa tất cả dữ liệu countries
function onBtnDelAllCountryClick() {
  // hiển thị modal del ALL
  $("#modal-del-all-country").modal("show");
  // thay đổi trạng thái
  gFormMode = gDELETE_MODE;
}

// Hàm xử lý sự kiện click Confirm xóa ALL
function onConfirmDelAllCountryClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/country/delete",
    success: function (response) {
      onBtnCountryListClick();
      resetData();
      $("#modal-del-all-country").modal("hide");
    }
  });
}

// Hàm xử lý sự kiện click nút User List exchange
function onBtnCountryListClick() {
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
        <th>Country code</th>
        <th>Country name</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gCountryTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { data: "id" },
      { data: "countryCode" },
      { data: "countryName" }
    ],
    columnDefs: [
      {
        targets: 3,
        defaultContent:
          `
            <button class='btn btn-info btn-sm edit'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete'>
              <i class='far fa-trash-alt'></i>
            </button>
            <button class='btn btn-primary btn-sm add_region'>
            <i class='fas fa-plus'></i> Region
            </button>
          `
      }
    ]
  });
  // call API get data users
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/country/all",
    success: function (resCountryList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gCountryTable.clear();

      //Cập nhật data cho bảng 
      gCountryTable.rows.add(resCountryList);

      //Cập nhật lại giao diện hiển thị bảng
      gCountryTable.draw();
    }
  });
}

// Hàm xử lý sự kiện khi click nút Region List exchange
function onBtnRegionListClick() {
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
        <th>Region code</th>
        <th>Region name</th>
        <th>Country id</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gRegionTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { "data": "id" },
      { "data": "regionCode" },
      { "data": "regionName" },
      { "data": "country" }
    ],
    columnDefs: [
      {
        targets: 3,
        render: function (countryObj) {
          return renderCountryId(countryObj);
        }
      },
      {
        targets: 4,
        defaultContent:
          `
            <button class='btn btn-info btn-sm edit_region'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete_region'>
              <i class='far fa-trash-alt'></i>
            </button>
          `
      }
    ]
  });
  // call API get data orders
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/region/all",
    success: function (resRegionList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gRegionTable.clear();

      //Cập nhật data cho bảng 
      gRegionTable.rows.add(resRegionList);

      //Cập nhật lại giao diện hiển thị bảng
      gRegionTable.draw();
    }
  });
}

// Hàm xử lý sự kiện click btn Add region
function onBtnAddRegionClick(paramAddRegion) {
  gFormMode = gCREATE_MODE;
  // thu thập dữ liệu ID của country mà muốn add region
  var vRowObj = gCountryTable.row($(paramAddRegion).parents("tr")).data();
  gId = vRowObj.id; // id country
  gUser = vRowObj;
  // hiển thị modal add region
  $("#modal-add-edit-region").modal("show");
}

// Hàm xử lý sự kiện click Save Region
function onBtnSaveRegionClick() {
  //B0: tạo biến lưu trữ dữ liệu new region
  var vNewRegion = {
    id: null,
    regionCode: "",
    regionName: ""
  }
  //B1: thu thập dữ liệu (ID)
  getRegionOnModal(vNewRegion);
  console.log(vNewRegion);
  var vIsRegionValid = validateRegion(vNewRegion);
  if (vIsRegionValid) {
    //B3: call api xử lý POST dữ liệu hoặc PUT dữ liệu
    if (gFormMode == gCREATE_MODE) { // for POST protocol
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/region/create" + "/" + gId,
        data: JSON.stringify(vNewRegion),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resRegion) {
          console.log(resRegion);
          //B4: xử lý hiển thị front-end
          toastr.success("successfully CREATED region");
          $("#modal-add-edit-region").modal("hide");
          resetData();
        },
        error: function (ajaxContext) {
          alert(ajaxContext.responseText);
        }
      });
    } else { // for PUT protocol
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/region/update" + "/" + gId, // gId là order id
        data: JSON.stringify(vNewRegion),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resUpdatedRegion) {
          console.log(resUpdatedRegion);
          //B4: xử lý hiển thị front-end
          toastr.success("successfully UPDATED region");
          $("#modal-add-edit-region").modal("hide");
          // load lại table order
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/region/all",
            success: function (resAllRegion) {
              gRegionTable.clear();
              gRegionTable.rows.add(resAllRegion);
              gRegionTable.draw();
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

// Hàm xử lý sự kiện click btn Edit Region
function onEditRegionClick(paramEditRegion) {
  // đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: khai báo biến lưu dữ liệu region
  var vRegion = {};
  //B1: thu thập dữ liệu (region id)
  var vRowObj = gRegionTable.row($(paramEditRegion).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call Api lấy dữ liệu theo region id
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/region/details" + "/" + gId,
    success: function (resRegion) {
      //B4: xử lý dữ liệu
      vRegion = resRegion;
      //B5: xử lý hiển thị
      $("#modal-add-edit-region").modal("show"); // show modal
      // hiển thị dữ liệu order lên form
      $("#input-region-code").val(vRegion.regionCode);
      $("#input-region-name").val(vRegion.regionName);
    }
  });
}

// Hàm xử lý sự kiện click btn Delete Region
function onDeleteRegionClick(paramDelRegion) {
  gFormMode = gDELETE_MODE;
  // thu thập id region
  var vRowObj = gRegionTable.row($(paramDelRegion).parents("tr")).data();
  gId = vRowObj.id;
  // hiển thị modal
  $("#modal-del-one-region").modal("show");
}

// Hàm xử lý sự kiện click Confirm delete Region
function onConfirmDelRegionClick() {
  // call api xóa dữ liệu region
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/region/delete" + "/" + gId,
    success: function (response) {
      toastr.success("successfully deleted region");
      // load lại table Region
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/region/all",
        success: function (resAllRegion) {
          gRegionTable.clear();
          gRegionTable.rows.add(resAllRegion);
          gRegionTable.draw();
          // reset và ẩn modal
          resetData();
          $("#modal-del-one-region").modal("hide");
        }
      });
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm thu thập dữ liệu trên form
function getCountryOnForm(paramObj) {
  paramObj.countryCode = $("#input-country-code").val();
  paramObj.countryName = $("#input-country-name").val();
}

// Hàm kiểm tra dữ liệu
function validateCountry(paramObj) {
  if (paramObj.countryCode == "") {
    alert("countryCode input not found");
    return false;
  }
  if (paramObj.countryName == "") {
    alert("countryName input not found");
    return false;
  }
  return true;
}

// Hàm reset dữ liệu
function resetData() {
  gFormMode = gNORMAL_MODE;

  $("#input-country-code").val("");
  $("#input-country-name").val("");

  $("#input-region-code").val("");
  $("#input-region-name").val("");

  gId = null;
}

// Hàm render prop countryObj trả về countryId
function renderCountryId(paramCountryObj) {
  console.log(paramCountryObj);
  return paramCountryObj.id;
}

// Hàm thu thập dữ liệu Region trên modal
function getRegionOnModal(paramRegion) {
  paramRegion.regionCode = $("#input-region-code").val();
  paramRegion.regionName = $("#input-region-name").val();
}

// Hàm validate region
function validateRegion(paramRegion) {
  if (paramRegion.regionCode == "") {
    toastr.error("regionCode not found");
    return false;
  }
  if (paramRegion.regionName == "") {
    toastr.error("regionName not found");
    return false;
  }
  return true;
}
