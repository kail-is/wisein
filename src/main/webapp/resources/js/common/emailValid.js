$(document).ready(function(){

		$("#signup_btn").click(function(){
		    var email_Id = document.getElementById('id').value;
//			var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
//			var email = $("#email").val();
//
//			if (email=="") {
//				$("#error_Text").text("이메일을 입력하세요.");
//				$("#error_Text").css({"color":"red","font-size":"11px"});
//				return false;
//			} else {
//				if (regEmail.test(email)==false) {
//					$("#error_Text").text("이메일 형식에 맞게 입력하세요.");
//					$("#error_Text").css({"color":"red","font-size":"11px"});
//					return false;
//				} else {
//					$("#error_Text").text("사용 가능한 이메일 입니다");
//					$("#error_Text").css({"color":"green","font-size":"11px"});
//
//					alert("인증번호를 발송하였습니다.\n입력한 이메일에서 인증 확인을 눌러주세요.");

					$.ajax({
						data:{"email_Id":email_Id},
						type:"GET",
						url:"/authMailSend",
						success:function(data) {
							document.getElementById('reg_form').submit();
						},
						error:function(request, status, error) {
							alert("실패");
						}
					})

	    })
	});