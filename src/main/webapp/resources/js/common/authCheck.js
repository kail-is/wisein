$(document).ready(function(){

    $("#login_btn").click(function(){
        var id = document.getElementById("id").value;

        $.ajax({
            url:"/authCheck",
            data : {"login_Id":id},
            type:"GET",
            success:function(data) {
                if (data.idExist=="0") {
                    alert("아이디 존재안함");
                } else {
                    if (data.authKey!="Y") {
                        alert("인증이 완료되지 않았습니다.");
                    } else {
                        document.getElementById("login_form").submit();
                    }
                }
            },
            error:function(request, status, error) {
            	alert("실패");
            }
        });
    })
});