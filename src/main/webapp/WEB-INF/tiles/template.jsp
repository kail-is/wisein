<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="resources/css/common.css">
        <link rel="stylesheet" href="resources/iconfont/material-icons.css">
        <link rel="stylesheet" href="resources/css/main.css">
        <script src="resources/js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div class="all-wrapper">
			<head>
				<tiles:insertAttribute name="header"/>
			</head>
			<!--nav-->
			<div >
				<tiles:insertAttribute name="nav"/>
			</div>
			<!--/nav-->
			<c:if test="${side_gubun eq 'Y'}">
				<div>
					<tiles:insertAttribute name="leftSide"/>
				</div>
			</c:if>
			<div class="container">
				<tiles:insertAttribute name="contents"/>
			</div>
			<!--footer-->
			<div>
				<tiles:insertAttribute name="footer"/>
			</div>
			<!--/footer-->
			<div id="dim"></div>
		</div>
	</body>
	<script src="${pageContext.request.contextPath}/resources/js/common/dim.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/authCheck.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/util.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/login.js"></script>

	<script>
	<c:if test="${empty member}">
	    $dim();
	    document.querySelector('#joinBox').classList.remove('none');
	</c:if>
	</script>

    <script>
       function enterkey() {
    	   var selectedSearchType = document.getElementById('search-list');
    	   var inputKeyword = document.getElementById('keywordInput');

           if(window.event.keyCode == 13) {
       		   self.location = "qalist"
       						 + '?searchType=' + selectedSearchType.value
       	   					 + "&keyword=" + inputKeyword.value
           }
       }
    </script>
</html>