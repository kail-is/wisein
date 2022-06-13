<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<script src="resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

window.onload = function() {
    var httpRequest;

    let idChkBool = false;
    let pwChkBool = false;

    let idBox = document.querySelector('#id');
    let pwBox = document.querySelector('#pw');
    let pwChkBox = document.querySelector('#pwChk');

    let idChkBtn = document.querySelector("#idChkBtn");
    let pwChkBtn = document.querySelector("#pwChkBtn");
    let signupBtn = document.querySelector("#signup_btn");


    // ID 유효성 컨트롤: 회원 가입 여부 체크
	idChkBtn.addEventListener('click', () => {
		var userId = document.querySelector("#id").value;
		httpRequest = new XMLHttpRequest();
	    httpRequest.onreadystatechange = () => {
		    if (httpRequest.readyState === XMLHttpRequest.DONE) {
			      if (httpRequest.status === 200) {
			    	var result = httpRequest.response;
                     if (result > 0) {
    			        alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                     } else {
                        alert("사용 가능한 아이디입니다.");
                        idBox.readOnly = true; // 재수정 불가를 위한 readOnly 활성화
                        idChkBool = true;
                     }
			      } else {
			        alert('Request Error');
			      }
		    }
	    };
        httpRequest.open('GET', '/idDupChk?' + "userId=" + userId);
        httpRequest.send();
	});

    // 아이디 재설정
    idBox.addEventListener('click', () => {
        if (idChkBool == true) {
            if (confirm("아이디를 재설정하시겠습니까? 아이디 중복 확인을 다시 받으셔야 합니다.")) {
                idChkBool = false;
                idBox.value = "";
                idBox.readOnly = false;
                idBox.focus;
            }
       }
     });

    // PW 유효성 컨트롤: 패스워드 일치 여부 체크
    pwChkBtn.addEventListener('click', () => {
        if ( !pw.value && !pwChk.value ) {
            pwChkBool = false;
            alert("패스워드를 입력하세요.");
        } else if( pw.value === pwChk.value ) {
            pwChkBool = true;
            alert("패스워드가 일치합니다.");
            pwBox.readOnly = true;
            pwChkBox.readOnly = true;
        } else {
          pwChkBool = false;
          alert("패스워드가 불일치합니다. 재입력하세요.");
        }
    });

    // 비밀번호 재설정
    pwBox.addEventListener('click', () => {
        if (pwChkBool == true) {
            if (confirm("비밀번호를 재설정하시겠습니까? 패스워드 확인을 다시 받으셔야 합니다.")) {
                pwChkBool = false;
                pwBox.value = "";
                pwBox.readOnly = false;
                pwChkBox.value = "";
                pwChkBox.readOnly = false;
                pwBox.focus;
                alert("pwChkBool" + pwChkBool);
            }
       }
     });

    pwChkBox.addEventListener('click', () => {
        if (pwChkBool == true) {
            if (confirm("비밀번호를 재설정하시겠습니까? 패스워드 확인을 다시 받으셔야 합니다.")) {
                pwChkBool = false;
                pwBox.value = "";
                pwBox.readOnly = false;
                pwChkBox.value = "";
                pwChkBox.readOnly = false;
                pwBox.focus;
            }
       }
     });

    // 회원 가입 버튼 유효성 컨트롤: stateHandler
    signupBtn.addEventListener('click', () => {
      if (checkAll()) {
        alert("유효성 테스트 통과");
        emailValid();
      } else {
        alert("유효성 테스트 미통과");
        event.preventDefault();
      }
    });

    // 전체 유효성 체크
    function checkAll() {
        if(idChkBool && pwChkBool) {
           return true;
        }else {
           return false;
        }
    }

    // 이메일 인증
    function emailValid() {
        var email_Id = document.getElementById('id').value;

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
    }

}
</script>


<section id="content">
 <form role="form" method="post" autocomplete="off" id="reg_form">
  <div class="input_area">
   <label for="id">아이디 </label>
   <input type="text" id="id" name="id" required="required" />
   <input type="button" id="idChkBtn" value="중복 확인"/>
  </div>

  <div class="input_area">
   <label for="pw">패스워드</label>
   <input type="password" id="pw" name="pw" required="required" />
  </div>

   <div class="input_area">
     <label for="pw">패스워드 체크</label>
     <input type="password" id="pwChk" name="pwChk" required="required" />
     <input type="button" id="pwChkBtn" value="패스워드 확인"/>
   </div>

  <div class="input_area">
   <label for="name">이름</label>
   <input type="text" id="name" name="name" required="required" />
  </div>

    <div class="input_area">
     <label for="site">파견 사이트</label>
     <input type="text" id="site" name="site" placeholder="파견 사이트" required="required" />
    </div>

  <button type="button" id="signup_btn" name="signup_btn">회원가입</button>
 </form>
</section>
