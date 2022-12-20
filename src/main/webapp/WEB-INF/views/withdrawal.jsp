<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="content" style="display:none">
 <form role="form" method="post" autocomplete="off">
  <div class="input_area">
   <label for="id">아이디 </label>
   <input type="text" id="id" name="id" value="${member.id}"required="required" />
  </div>

  <button type="submit" id="withdraw_btn" name="withdraw_btn">탈퇴</button>
 </form>
</section>

<script>
window.onload = function(){
   document.querySelector("form").submit();
   commonPopup.alertPopup("탈퇴 완료")
}
</script>