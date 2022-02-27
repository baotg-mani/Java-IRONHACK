$(document).ready(function () {
    const token = getCookie("token");

    //Gọi API để lấy thông tin người dùng

    //Khai báo xác thực ở headers
    var headers = {
        Authorization: "Bearer " + token
    };

    var urlInfo = "http://42.115.221.44:8080/devcamp-auth/users/me";

    $.ajax({
        url: urlInfo,
        method: "GET",
        headers: headers,
        success: function (responseObject) {
            displayUser(responseObject);
        },
        error: function (xhr) {
            console.log(xhr);
            // Khi token hết hạn, AJAX sẽ trả về lỗi khi đó sẽ redirect về trang login để người dùng đăng nhập lại
            redirectToLogin();
        }
    });

    //Set sự kiện cho nút logout
    $("#logout").on("click", function () {
        redirectToLogin();
    });

    function redirectToLogin() {
        // Trước khi logout cần xóa token đã lưu trong cookie
        setCookie("token", "", 1);
        window.location.href = "login.html";
    }

    //Hiển thị thông tin người dùng
    function displayUser(data) {
        $("#name").html(data.firstname);
        $("#id").html(data.id);
        $("#email").html(data.email);
        $("#firstname").html(data.firstname);
        $("#lastname").html(data.lastname);
    };

    //Hàm get Cookie đã giới thiệu ở bài trước
    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    //Hàm setCookie đã giới thiệu ở bài trước
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
})