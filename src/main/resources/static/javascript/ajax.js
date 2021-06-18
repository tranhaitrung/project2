$(document).ready(function () {

    // alert("hello");

    //loadUser();
    //searchArticleAdmin();

    // initEvens();


})


function loadUser(){
    var text = document.getElementById("text").value;
    var sel = document.getElementById("select1").value;
    var data = {
        text_search: text,
        select: sel
    }
    console.log(text);
    console.log(data);
    $.ajax({
        url: 'http://localhost:8080/quan-ly/getListUser',
        method: 'GET',
        data: data,
        success: function (response){
            var res = response;
            console.log(res);
            $('#list-user').html(res);
        }
    }).fail(function (response){
        $('#user-list').html("Không có kết quả trả về");
    });
}

function searchArticleAdmin(){
    var text_search = document.getElementById('text-admin').value;
    var price = document.getElementById('select-price-admin').value;
    var area = document.getElementById('select-area-admin').value;
    var select = document.getElementById('select_article_search').value;

    var data = {
        id: text_search,
        title: text_search,
        city: text_search,
        price: price,
        area: area,
        select: select
    }
    console.log(data);
    $.ajax({
        url: 'http://localhost:8080/quan-ly/getListArticle',
        method: 'GET',
        data: data,
        success: function (response){
            var res = response;
            console.log(res);
            $('#list-articles').html(res);
        }
    }).fail(function (response){
        $('#articles').html("Không có kết quả phù hợp!");
    });
}

function deleteArticleAdmin(){
    console.log('delete');
    var arr = [];
    var str = "";
    var checkbox = document.getElementsByName('xoa');
    for (var i=0; i<checkbox.length;i++){
        if (checkbox[i].checked){
            arr.push(checkbox[i].value);
            str += checkbox[i].value + "-";
        }
    }

    var data = {
        data :str
    }
    console.log(data);
    $.ajax({
        url: 'http://localhost:8080/quan-ly/xoa-bai',
        method: 'POST',
        data: data,
        dataType: 'json',
        success: function (response){
            var res = response;
            console.log(res);
            $('#list-articles').html(res);
            alert("Xóa thành công!")
        },
        error: function (response){
        }
    })
}

function deleteAccountAdmin(){
    var str = "";
    var checkbox = document.getElementsByName('deleteAccount');
    for (var i=0; i<checkbox.length;i++){
        if (checkbox[i].checked){
            str += checkbox[i].value + "-";
        }
    }

    var data = {
        data :str
    }
    console.log(data);
    $.ajax({
        url: 'http://localhost:8080/quan-ly/deleteAccount',
        method: 'POST',
        data: data,
        dataType: 'json',
        success: function (response){
            var res = response;
            console.log(res);
            $('#list-user').html(res);
            alert("Xóa thành công!");
        },
        error: function (response){
        }
    })

}

function notification(){
    var uer = document.getElementById("tentk").innerText;

    var check = true;

    if (document.getElementById("fullname").value === ''){
        alert("Vui lòng nhập tên!");
        check = false;
        return;
    }

    if (document.getElementById("telephone").value === ''){
        alert("Vui lòng nhập số điện thoại!");
        check = false;
        return;
    }

    if (document.getElementById("email").value === ''){
        alert("Vui lòng nhập email!");
        check = false;
        return;
    }

    if (document.getElementById("link").value === ''){
        alert("Vui lòng điền link bài viết bạn quan tâm!");
        check = false;
        return;
    }

    if (!check){
        return;
    }

    var data = {
        email: $('#email').val(),
        link: $('#link').val(),
        name: $('#fullname').val(),
        phone: $('#telephone').val(),
        message: $('#message').val(),
        username: uer,
    }
    console.log(uer);
    console.log(data);
    var url = 'http://localhost:8080/them-thong-bao';
    console.log(url);
    $.ajax({
        url: url,
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response){
            alert("Yêu cầu liên hệ lại thành công!")
            $('#exampleModalNotification').modal('hide');

        },
        error: function (response){
            alert("Yêu cầu thất bại bại!")
        }
    });

}

function seen(id){
    console.log(id);
    var url = 'http://localhost:8080/xem-thong-bao/'+id;
    $.ajax({
        url: url,
        method: 'GET',
        success: function (response){
            $('#ModalNotification').modal('show');
            $('#fullname_receive').val(response.name);
            $('#telephone_receive').val(response.phone);
            $('#email_receive').val(response.email);
            $('#link_receive').val(response.link);
            $('#message_receive').val(response.message);
        },
        error: function (){
            alert("Có lỗi xảy ra, vui lòng đăng nhập lại!")
        }
    })
}