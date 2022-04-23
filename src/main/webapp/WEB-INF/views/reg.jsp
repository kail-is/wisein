<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<script type="text/javascript">

window.onload = function() {
    var httpRequest;
    let idChkBool = false;
    let pwChkBool = false;
    let pwChkBtn = document.querySelector("#pwChkBtn");
    let signupBtn = document.querySelector("#signup_btn");
    let valiChkBtn = document.querySelector("#validation_test");

    // ID 유효성 컨트롤: 회원 가입 여부 체크
	document.querySelector("#idChkBtn").addEventListener('click', () => {
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
                        document.querySelector('#id').readOnly = true; // 재수정 불가를 위한 readOnly 활성화
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


    // PW 유효성 컨트롤: 패스워드 일치 여부 체크
    document.querySelector("#pwChkBtn").addEventListener('click', () => {
        if (pw.value === pwChk.value) {
            pwChkBool = true;
            alert("패스워드가 일치합니다.");
            document.querySelector('#pw').readOnly = true;
            document.querySelector('#pwChk').readOnly = true;
        } else {
          pwChkBool = false;
          alert("패스워드가 불일치합니다. 재입력하세요.");
        }
    });


    // 회원 가입 버튼 유효성 컨트롤: stateHandler
    signupBtn.disabled = true;

    valiChkBtn.addEventListener('click', () => {
      if ( (idChkBool && pwChkBool) == 1) {
        signupBtn.disabled = false;
        alert("유효성 테스트 통과");
      } else {
        signupBtn.disabled = true;
        alert("유효성 테스트 미통과");
      }
    });


}
</script>


<section id="content">
 <form role="form" method="post" autocomplete="off">
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

  <button type="submit" id="signup_btn" name="signup_btn">회원가입</button>
  <button type="button" id="validation_test" name="validation_test">유효성 테스트</button>
 </form>
</section>
