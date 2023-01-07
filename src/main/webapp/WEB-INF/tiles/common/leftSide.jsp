<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String url =  request.getServerName().toString(); %>
<div class="info-wrap">
    <ul id="leftSideBar" class="info">
    </ul>
</div>

<script src="${url}/resources/js/dataBoard.js" type="module" async></script>

<input type="hidden" id="questionsListWriter" name="boardNum" value="${questionsListWriter}" />
<input type="hidden" id="commentListWriter" name="boardNum" value="${commentListWriter}" />
<input type="hidden" id="tipWriter" name="tipWriter" value="${tipWriter}" />

