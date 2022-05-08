<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:formatDate  var="hireDate" value="${member.hireDate}" type="DATE" pattern="yyyy-MM-dd"/>

<script type="text/javascript">

window.onload = function() {

    let pwChkBool = false;

    let pwBox = document.querySelector('#pw');
    let pwChkBox = document.querySelector('#pwChk');

    let pwChkBtn = document.querySelector("#pwChkBtn");
    let updBtn = document.querySelector("#upd_btn");

    // PW 유효성 컨트롤: 패스워드 일치 여부 체크
    pwChkBtn.addEventListener('click', () => {
        if (pw.value === pwChk.value) {
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
    updBtn.addEventListener('click', () => {
      if (checkAll()) {
        alert("유효성 테스트 통과");
      } else {
        alert("유효성 테스트 미통과");
        event.preventDefault();
      }
    });

    // 전체 유효성 체크
    function checkAll() {
        if(pwChkBool) {
            return true;
        }else {
            return false;
        }
    }
}
</script>

<section id="content">
<form role="form" method="post" encType = "multipart/form-data" autocomplete="off">
  <div class="input_area">

  <input type="text" id="id" name="id" value="${member.id}" required="required" />

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
   <input type="text" id="name" name="name" value="${member.name}" required="required" readonly />
  </div>

  <div class="input_area">
   <label for="name">프로필 사진</label>
   <input type="file" id="files" name="fileName" />
    <div class="file_list">
        <c:forEach var="list" items="${member.fileList}">
        <img src ="../${list.filePath}" width="100" height="100">
        ${list.orgFileName}
        </c:forEach>
    </div>
  </div>

  <div class="input_area">
    <label for="site">파견 사이트</label>
    <input type="text" id="site" name="site" value="${member.site}" placeholder="파견 사이트" required="required" />
  </div>

    <div class="input_area">
      <label for="hireDate">입사 일자 </label>
      <input type="date" id="hireDate" name="hireDate" value="${hireDate}" placeholder="입사 일자" required="required"/>
    </div>

    <div class="input_area">
      <label for="site">구글 미트 사용 </label>
      <label><input type="radio" name="meetYn" value="Y"
        <c:if test="${member.meetYn eq 'Y'}"> checked </c:if> > Y</label>
      <label><input type="radio" name="meetYn" value="N"
        <c:if test="${member.meetYn eq 'N'}"> checked </c:if> > N</label>

    </div>

    <div class="input_area">
      <label for="site">미트 링크 </label>
      <input type="url" id="meetLink" name="meetLink" value="${member.meetLink}" placeholder="구글 미트 링크" required="required" />
    </div>


  <button type="submit" id="upd_btn" name="upd_btn">정보 수정</button>
 </form>
</section>