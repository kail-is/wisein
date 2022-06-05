<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.wisein.wiselab.common.paging.PaginationInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagination</title>
</head>
<body>
<div class="pagination">
	<c:if test="${qaListDTO.getPaginationInfo().isHasPreviousPage()}">
   		<a href='qalist${qaListDTO.makeQueryString(1)}'>&laquo;</a>
   	</c:if>
   	
   	<c:if test="${qaListDTO.getPaginationInfo().isHasPreviousPage()}">
   		<a href='qalist${qaListDTO.makeQueryString(qaListDTO.getPaginationInfo().getFirstPage()-1)}'>&lt;</a>
   	</c:if>

	<c:forEach begin="${qaListDTO.getPaginationInfo().getFirstPage()}" end="${qaListDTO.getPaginationInfo().getLastPage()}" var="idx">
   		<a href='qalist${qaListDTO.makeQueryString(idx)}' <c:out value="${qaListDTO.getCurrentPageNo() == idx ? 'class = active' : ''}"/>>${idx}</a>
   	</c:forEach>
   	
   	<c:if test="${qaListDTO.getPaginationInfo().isHasNextPage()}">
   		<a href='qalist${qaListDTO.makeQueryString(qaListDTO.getPaginationInfo().getLastPage()+1)}'>&gt;</a>
   	</c:if>
   	
   	<c:if test="${qaListDTO.getPaginationInfo().isHasNextPage()}">
   		<a href='qalist${qaListDTO.makeQueryString(qaListDTO.getPaginationInfo().getTotalPageCount())}'>&raquo;</a>
   	</c:if>
</div>
</body>
</html>