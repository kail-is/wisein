<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="content">
 <form role="form" method="post" autocomplete="off">
  <div class="input_area">
   <label for="id">아이디 </label>
   <input type="text" id="id" name="id" required="required" />
  </div>

  <div class="input_area">
   <label for="pw">패스워드</label>
   <input type="password" id="pw" name="pw" required="required" />
  </div>

  <button type="submit" id="login_btn" name="login_btn">로그인</button>
 </form>
</section>

<div> 로그인 여부: ${not empty member} </div>
<div> 로그인 객체: ${member} </div>

<c:if test="${not empty msg}">
    <p style="color:red;">${msg}</p>
</c:if>

<ul>
 <c:if test="${empty member}">
 <li> <a href="/login">로그인</a> </li>
 <li> <a href="/reg">회원가입</a> </li>
 </c:if>
  <c:if test="${not empty member}">
 <li><a href="/logout">로그아웃</a> </li>
 </c:if>
</ul>