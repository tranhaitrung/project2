


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
    });
}