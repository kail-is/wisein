$(document).ready(function(){

		//인증 번호 저장
		var authNum = "";

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

					alert("인증번호를 발송하였습니다.\n입력한 이메일에서 인증번호를 확인해주세요.");

					$.ajax({
						data:{"email":email},
						type:"POST",
						dataType:"json",
						url:"/wiselab/authKeySend",
						success:function(data) {

							authNum = data.authNum;
						},
						error:function(request, status, error) {
							alert("실패");
						}
					})
				}
			}
		})

		//인증 버튼 클릭
		$("#certi_Btn").click(function() {

			var input_Num = $("#authKey").val();

			if (input_Num=="") {

				$("#auth_Error").text("인증 번호를 입력하세요");
				$("#auth_Error").css({"color":"red","font-size":"11px"});
				console.log(input_Num);
				return false;
			}

			if (authNum==input_Num) {

				$("#auth_Error").text("인증 번호가 일치 합니다");
				$("#auth_Error").css({"color":"green","font-size":"11px"});

				$.ajax({
					url:"/wiselab/stateUpd",
					data : {"id":"ehdrms523"},
					dataType:"json",
					success:function(data) {
						alert("인증에 성공하였습니다.")
					},
					error:function(request, status, error) {
						alert("error");
					}
				});

			} else if (authNum!=input_Num) {

				$("#auth_Error").text("인증 번호가 일치하지 않습니다");
				$("#auth_Error").css({"color":"red","font-size":"11px"});
				return false;
			}
		})


		$("#auth_Check").click(function() {
			$.ajax({
				url:"/wiselab/auth_Check",
				data:{"id":"ehdrms523"},
				dataType:"json",
				success:function(data) {
					if (data.state=="N") {
						alert("이메일 인증을 먼저 하세요");
						history.back(-1);

					} else if (data.state=="Y") {
						alert("인증 성공");
						location.href="/wiselab/success"
					}
				},
				error:function(request, status, error) {
					alert("error");
				}
			})
		});
	});