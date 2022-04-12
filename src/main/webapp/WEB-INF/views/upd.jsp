<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
   <input type="text" id="name" name="name" value="${member.name}" required="required" />
  </div>

  <div class="input_area">
    <label for="site">사이트</label>
    <input type="text" id="site" name="site" value="${member.site}" placeholder="사이트" required="required" />
  </div>

  <button type="submit" id="upd_btn" name="upd_btn">정보 수정</button>
 </form>
</section>