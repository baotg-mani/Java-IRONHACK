"use strict";
/*** REGION 1 - Global variables - Vùng khai báo biến, hằng số, tham số TOÀN CỤC */
var gChapterTable = null;
var gLessonTable = null;

const gNORMAL_MODE = "normal";
const gCREATE_MODE = "create";
const gUPDATE_MODE = "update";
const gDELETE_MODE = "delete";
var gFormMode = gNORMAL_MODE;
var gSttChapter = 1;
var gSttLesson = 1;
var gId = null;
var gUser = null;
var gOrder = null;

/*** REGION 2 - Vùng gán / thực thi hàm xử lý sự kiện cho các elements */
$(document).ready(function () {
  onPageLoading();

  $(document).on("click", ".edit", function () {
    onEditChapterClick(this);
  });

  // gán sự kiện click vào btn Save Chapter
  $("#save-chapter").on("click", onBtnSaveChapter);

  // gán sự kiện click vào icon Delete Chapter
  $(document).on("click", ".delete", function () {
    onBtnDeleteChapterClick(this);
  });

  // gán sự kiện click Confirm Delete Row chapter (by id)
  $("#confirm-del-chapter").on("click", function () {
    onConfirmDelChapterClick();
  });

  $("#chapter-list").on("click", function () {
    onBtnChapterListClick();
    resetData();
  });
  $("#lesson-list").on("click", function () {
    onBtnLessonListClick();
    resetData();
  });

  // gán sự kiện click btn Add Lesson
  $(document).on("click", ".add_lesson", function () {
    onBtnAddLessonClick(this);
  });

  // gán sự kiện click confirm Save Lesson
  $("#save-lesson").on("click", function () {
    onBtnSaveLessonClick();
  })

  // gán sự kiện click cho btn Edit Lesson
  $(document).on("click", ".edit_lesson", function () {
    onEditLessonClick(this);
  });

  // gán sự kiện click cho btn Del Lesson
  $(document).on("click", ".delete_lesson", function () {
    onDeleteLessonClick(this);
  });

  // gá sự kiện click confirm Delete Lesson
  $("#confirm-del-lesson").click(onConfirmDelLessonClick);
});

/*** REGION 3 - Event handlers - Vùng khai báo các hàm xử lý sự kiện */
// Ham xu ly su kien load Page
function onPageLoading() {
  onBtnChapterListClick();
}

// Hàm xử lý khi click vào btn Edit chapter trong bảng
function onEditChapterClick(paramEdit) {
  // thay đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: tạo obj lưu data
  var vRowObj = {};
  //B1: thu thập data
  vRowObj = gChapterTable.row($(paramEdit).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call api get chpater theo ID
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/chapter/detail" + "/" + gId,
    success: function (resChapter) {
      //B4: xử lý hiển thị
      $("#input-chapter-code").val(resChapter.chapterCode);
      $("#input-chapter-name").val(resChapter.chapterName);
      $("#input-intro-chapter").val(resChapter.introduction);
      $("#input-translator").val(resChapter.translatorName);
      $("#input-page-chapter").val(resChapter.page);
    }
  });
}

// Hàm xử lý sự kiện click vào nút Sava Chapter
function onBtnSaveChapter() {
  //B0: tạo biến lưu dữ liệu
  var vUpdateObj = {
    id: gId,
    chapterCode: "",
    chapterName: "",
    introduction: "",
    translatorName: "",
    page: null
  }
  //B1: thu thập dữ liệu
  getChapterOnForm(vUpdateObj);
  //B2: validate dữ liệu
  var vIsChapterValid = validateChapter(vUpdateObj);
  if (vIsChapterValid) {
    if (gFormMode == gUPDATE_MODE) {
      //B3: call restAPI update dữ liệu
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/chapter/update" + "/" + gId,
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resChapter) {
          //B4: xử lý hiển thị front end
          onBtnChapterListClick();
          resetData();
          toastr.success("successfully updated chapter");
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText);
        }
      });
    } else { //Create chapter (POST dữ liệu)
      //B3: call restAPI create dữ liệu
      vUpdateObj.id = gId;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/chapter/create",
        data: JSON.stringify(vUpdateObj),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resChapter) {
          //B4: xử lý hiển thị front end
          onBtnChapterListClick();
          resetData();
          toastr.success("successfully created chapter");
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText);
        }
      });
    }
  }
}

// Hàm xử lý sự kiện Delete Chapter theo id
function onBtnDeleteChapterClick(paramDel) {
  // đổi trạng thái
  gFormMode = gDELETE_MODE;
  // hiển thị modal
  $("#modal-del-one-chapter").modal("show");
  // thu thập dữ liệu ID
  var vRow = $(paramDel).parents("tr");
  var vRowObj = gChapterTable.row(vRow).data();
  gId = vRowObj.id;
  console.log(gId);
}

// Hàm xử lý sự kiện click Confirm Delete 1 Chapter
function onConfirmDelChapterClick() {
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/chapter/delete" + "/" + gId,
    success: function (response) {
      onBtnChapterListClick();
      resetData();
      $("#modal-del-one-chapter").modal("hide");
      toastr.success("successfully DELETED chapter");
    }
  });
}

// Hàm xử lý sự kiện click nút Chapter List exchange
function onBtnChapterListClick() {
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
        <th>Chapter code</th>
        <th>Chapter name</th>
        <th>Introduction</th>
        <th>Translator name</th>
        <th>Page</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gChapterTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { data: "id" },
      { data: "chapterCode" },
      { data: "chapterName" },
      { data: "introduction" },
      { data: "translatorName" },
      { data: "page" }
    ],
    columnDefs: [
      {
        targets: 0,
        render: function () {
          return gSttChapter++;
        }
      },
      {
        targets: 6,
        defaultContent:
          `<div class="row">
            <button class='btn btn-info btn-sm edit'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete'>
              <i class='far fa-trash-alt'></i>
            </button>
            <button class='btn btn-primary btn-sm add_lesson'>
            <i class='fas fa-plus'></i> Lesson
            </button>
          </div>`
      }
    ]
  });
  // call API get data users
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/chapter/all",
    success: function (resChapterList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gChapterTable.clear();

      //Cập nhật data cho bảng 
      gChapterTable.rows.add(resChapterList);

      //Cập nhật lại giao diện hiển thị bảng
      gChapterTable.draw();
    }
  });
}

// Hàm xử lý sự kiện khi click nút Lesson List exchange
function onBtnLessonListClick() {
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
        <th>Lesson code</th>
        <th>Lesson name</th>
        <th>Introduction</th>
        <th>Page</th>
        <th>Chapter id</th>
        <th>Actions</th>
      </tr>
    `);
  // và tạo DataTable mapping vs data từ API
  gLessonTable = $("#my-table").DataTable({
    searching: false,
    ordering: false,
    columns: [
      { "data": "id" },
      { "data": "lessonCode" },
      { "data": "lessonName" },
      { "data": "introduction" },
      { "data": "page" },
      { "data": "chapter" }
    ],
    columnDefs: [
      {
        targets: 0,
        render: function () {
          return gSttLesson++;
        }
      },
      {
        targets: 5,
        render: function (chapterObj) {
          return renderChapterId(chapterObj);
        }
      },
      {
        targets: 6,
        defaultContent:
          `
            <button class='btn btn-info btn-sm edit_lesson'>
              <i class='fas fa-edit'></i>
            </button>	
            <button class='btn btn-danger btn-sm delete_lesson'>
              <i class='far fa-trash-alt'></i>
            </button>
          `
      }
    ]
  });
  // call API get data Lessons
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/lesson/all",
    success: function (resLessonList) {
      //Xóa toàn bộ dữ liệu đang có của bảng
      gLessonTable.clear();

      //Cập nhật data cho bảng 
      gLessonTable.rows.add(resLessonList);

      //Cập nhật lại giao diện hiển thị bảng
      gLessonTable.draw();
    }
  });
}

// Hàm xử lý sự kiện click btn Add Lesson
function onBtnAddLessonClick(paramAddLesson) {
  gFormMode = gCREATE_MODE;
  // thu thập dữ liệu ID của chapter mà muốn add lesson
  var vRowObj = gChapterTable.row($(paramAddLesson).parents("tr")).data();
  gId = vRowObj.id; // id Chapter
  gUser = vRowObj;
  // hiển thị modal add lesson
  $("#modal-add-edit-lesson").modal("show");
}

// Hàm xử lý sự kiện click Save Lesson
function onBtnSaveLessonClick() {
  //B0: tạo biến lưu trữ dữ liệu new lesson
  var vNewLesson = {
    id: null,
    lessonCode: "",
    lessonName: ""
  }
  //B1: thu thập dữ liệu (ID)
  getLessonOnModal(vNewLesson);
  var vIsLessonValid = validateLesson(vNewLesson);
  if (vIsLessonValid) {
    //B3: call api xử lý POST dữ liệu hoặc PUT dữ liệu
    if (gFormMode == gCREATE_MODE) { // for POST protocol
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/lesson/create" + "/" + gId,
        data: JSON.stringify(vNewLesson),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resLesson) {
          console.log(resLesson);
          //B4: xử lý hiển thị front-end
          onBtnLessonListClick();
          toastr.success("successfully CREATED lesson");
          $("#modal-add-edit-lesson").modal("hide");
          resetData();
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText);
        }
      });
    } else { // for PUT protocol
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/lesson/update" + "/" + gId, // gId là lesson id
        data: JSON.stringify(vNewLesson),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (resUpdatedLesson) {
          console.log(resUpdatedLesson);
          //B4: xử lý hiển thị front-end
          toastr.success("successfully UPDATED lesson");
          $("#modal-add-edit-lesson").modal("hide");
          // load lại table lesson
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/lesson/all",
            success: function (resAllLesson) {
              gLessonTable.clear();
              gLessonTable.rows.add(resAllLesson);
              gLessonTable.draw();
            }
          });
          resetData();
        },
        error: function (ajaxContext) {
          toastr.error(ajaxContext.responseText);
        }
      });
    }
  }
}

// Hàm xử lý sự kiện click btn Edit Lesson
function onEditLessonClick(paramEditLesson) {
  // đổi trạng thái
  gFormMode = gUPDATE_MODE;
  //B0: khai báo biến lưu dữ liệu lesson
  var vLesson = {};
  //B1: thu thập dữ liệu (lesson id)
  var vRowObj = gLessonTable.row($(paramEditLesson).parents("tr")).data();
  gId = vRowObj.id;
  //B2: validate(bỏ qua)
  //B3: call Api lấy dữ liệu theo lesson id
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/lesson/detail" + "/" + gId,
    success: function (resLesson) {
      //B4: xử lý dữ liệu
      vLesson = resLesson;
      //B5: xử lý hiển thị
      $("#modal-add-edit-lesson").modal("show"); // show modal
      // hiển thị dữ liệu order lên form
      $("#input-lesson-code").val(vLesson.lessonCode);
      $("#input-lesson-name").val(vLesson.lessonName);
      $("#input-intro-lesson").val(vLesson.introduction);
      $("#input-page-lesson").val(vLesson.page);
    }
  });
}

// Hàm xử lý sự kiện click btn Delete Lesson
function onDeleteLessonClick(paramDelLesson) {
  gFormMode = gDELETE_MODE;
  // thu thập id lesson
  var vRowObj = gLessonTable.row($(paramDelLesson).parents("tr")).data();
  gId = vRowObj.id;
  // hiển thị modal
  $("#modal-del-one-lesson").modal("show");
}

// Hàm xử lý sự kiện click Confirm delete Lesson
function onConfirmDelLessonClick() {
  // call api xóa dữ liệu lesson
  $.ajax({
    type: "DELETE",
    url: "http://localhost:8080/lesson/delete" + "/" + gId,
    success: function (response) {
      toastr.success("successfully deleted lesson");
      // load lại table Lesson
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/lesson/all",
        success: function (resAllLesson) {
          // reset và ẩn modal
          resetData();
          $("#modal-del-one-lesson").modal("hide");
          gLessonTable.clear();
          gLessonTable.rows.add(resAllLesson);
          gLessonTable.draw();
        }
      });
    }
  });
}

/*** REGION 4 - Common funtions - Vùng khai báo hàm dùng chung trong toàn bộ chương trình*/
// Hàm thu thập dữ liệu Chapter trên form
function getChapterOnForm(paramObj) {
  paramObj.chapterCode = $("#input-chapter-code").val().trim();
  paramObj.chapterName = $("#input-chapter-name").val().trim();
  paramObj.introduction = $("#input-intro-chapter").val().trim();
  paramObj.translatorName = $("#input-translator").val().trim();
  paramObj.page = parseInt($("#input-page-chapter").val().trim());
}

// Hàm kiểm tra dữ liệu
function validateChapter(paramObj) {
  if (paramObj.chapterCode == "") {
    toastr.error("chapterCode input not found");
    return false;
  }
  if (paramObj.chapterName == "") {
    toastr.error("chapterName input not found");
    return false;
  }
  if (paramObj.introduction == "") {
    toastr.error("introduction of chapter input not found");
    return false;
  }
  if (paramObj.translatorName == "") {
    toastr.error("translatorName input not found");
    return false;
  }
  if (isNaN(paramObj.page)) {
    toastr.error("page of chapter must be a number");
    return false;
  }
  return true;
}

// Hàm reset dữ liệu
function resetData() {
  gFormMode = gNORMAL_MODE;

  $("#input-chapter-code").val("");
  $("#input-chapter-name").val("");
  $("#input-intro-chapter").val("");
  $("#input-translator").val("");
  $("#input-page-chapter").val("");

  $("#input-lesson-code").val("");
  $("#input-lesson-name").val("");
  $("#input-intro-lesson").val("");
  $("#input-page-lesson").val("");

  gId = null;
  gSttChapter = 1;
  gSttLesson = 1
}

// Hàm render prop chapterObj trả về chapterId
function renderChapterId(paramChapterObj) {
  console.log(paramChapterObj);
  return paramChapterObj.id;
}

// Hàm thu thập dữ liệu Lesson trên modal
function getLessonOnModal(paramLesson) {
  paramLesson.lessonCode = $("#input-lesson-code").val().trim();
  paramLesson.lessonName = $("#input-lesson-name").val().trim();
  paramLesson.introduction = $("#input-intro-lesson").val().trim();
  paramLesson.page = parseInt($("#input-page-lesson").val().trim());
}

// Hàm validate lesson
function validateLesson(paramLesson) {
  if (paramLesson.lessonCode == "") {
    toastr.error("lessonCode not found");
    return false;
  }
  if (paramLesson.lessonName == "") {
    toastr.error("lessonName not found");
    return false;
  }
  if (paramLesson.introduction == "") {
    toastr.error("introduction of lesson not found");
    return false;
  }
  if (isNaN(paramLesson.page)) {
    toastr.error("page of lesson must be a number");
    return false;
  }
  return true;
}
