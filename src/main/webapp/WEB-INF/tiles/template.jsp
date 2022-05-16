<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="resources/css/common.css">
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
			<div >
				<tiles:insertAttribute name="footer"/>
			</div>
			<!--/footer-->
			<div id="dim"></div>
		</div>
	</body>
	<script src="${pageContext.request.contextPath}/resources/js/common/dim.js"></script>

	<script>
		$dim();
	</script>
</html>