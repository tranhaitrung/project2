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