<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:formatDate  var="hireDate" value="${member.hireDate}" type="DATE" pattern="yyyy-MM-dd"/>

<section id="content">
 <form role="form" method="post" autocomplete="off">
  <div class="input_area">

  <input type="text" id="id" name="id" value="${member.id}" required="required" />

  <div class="input_area">
   <label for="pw">패스워드</label>
   <input type="password" id="pw" name="pw" required="required" />
  </div>

  <div class="input_area">
   <label for="name">이름</label>
   <input type="text" id="name" name="name" value="${member.name}" required="required" readonly />
  </div>

  <div class="input_area">
    <label for="site">사이트</label>
    <input type="text" id="site" name="site" value="${member.site}" placeholder="사이트" required="required" />
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

    ${member}

  <button type="submit" id="upd_btn" name="upd_btn">정보 수정</button>
 </form>
</section>