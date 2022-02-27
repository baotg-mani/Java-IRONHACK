$(document).ready(function () {
    //----Phần này làm sau khi đã làm trang info.js
    //Kiểm tra token nếu có token tức người dùng đã đăng nhập
    const token = getCookie("token");

    if (token) {
        window.location.href = "info.html";
    }
    //----Phần này làm sau khi đã làm trang info.js

    //Sự kiện bấm nút login
    $("#submit").on("click", function () {
        var email = $("#inputEmail").val().trim();
        var password = $("#inputPassword").val().trim();

        if (validateForm(email, password)) {
            signinForm(email, password);
        }
    });

    function signinForm(email, password) {
        var loginUrl = "http://42.115.221.44:8080/devcamp-auth/users/signin";

        //API theo đặc tả sẽ call theo data-form chứ không phải kiểu json nên cần khai báo formdata
        var formData = new FormData();
        formData.append('email', email);
        formData.append('password', password);

        $.ajax({
            type: "POST",
            url: loginUrl,
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function (responseObject) {
                responseHandler(responseObject);
            },
            error: function (xhr) {
                // Lấy error message
                var errorMessage = xhr.responseJSON.message;
                showError(errorMessage);
            }
        });
    }

    //Xử lý object trả về khi login thành công
    function responseHandler(data) {
        //Lưu token vào cookie trong 1 ngày
        setCookie("token", data.token, 1);

        window.location.href = "info.html";
    }

    //Hàm setCookie đã giới thiệu ở bài trước
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    //Hiển thị lỗi lên form 
    function showError(message) {
        var errorElement = $("#error");

        errorElement.html(message);
        errorElement.addClass("d-block");
        errorElement.addClass("d-none");
    }

    //Validate dữ liệu từ form
    function validateForm(email, password) {
        if (!validateEmail(email)) {
            alert("Email không phù hợp");
            return false;
        };

        if (password === "") {
            alert("Password không phù hợp");
            return false;
        }

        return true;
    }

    // Hàm validate email bằng regex
    function validateEmail(email) {
        const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return regex.test(String(email).toLowerCase());
    }

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
});