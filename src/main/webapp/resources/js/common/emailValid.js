$(document).ready(function(){

		$("#email_btn").click(function(){
			var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			var email = $("#email").val();

			if (email=="") {
				$("#error_Text").text("이메일을 입력하세요.");
				$("#error_Text").css({"color":"red","font-size":"11px"});
				return false;
			} else {
				if (regEmail.test(email)==false) {
					$("#error_Text").text("이메일 형식에 맞게 입력하세요.");
					$("#error_Text").css({"color":"red","font-size":"11px"});
					return false;
				} else {
					$("#error_Text").text("사용 가능한 이메일 입니다");
					$("#error_Text").css({"color":"green","font-size":"11px"});

					alert("인증번호를 발송하였습니다.\n입력한 이메일에서 인증 확인을 눌러주세요.");

					$.ajax({
						data:{"email":email},
						type:"GET",
						url:"/authMailSend",
						success:function(data) {
							console.log("success");
						},
						error:function(request, status, error) {
							alert("실패");
						}
					})
				}
			}
		})

        $("#auth_Check").click(function() {
            console.log("test");
            $.ajax({
                url:"/authCheck",
                dataType:"json",
                type:"GET",
                success:function(data) {
                    if (data.msg=="Y") {
                        location.href="/login";
                    } else {
                        alert("인증을 완료해주세요.");
                    }
                },
                error:function(request, status, error) {
                    console.log("fail");
                }
            });
        });

	});