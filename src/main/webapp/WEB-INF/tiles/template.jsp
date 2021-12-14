<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
	    <tiles:insertAttribute name="header"/>
	</head>
	<body>

		<!--nav-->
		<div >
		    <tiles:insertAttribute name="nav"/>
		</div>
		<!--/nav-->
		
		<div class="container">
		    <tiles:insertAttribute name="contents"/>
		</div>
		
		
		<!--footer-->
		<div >
		    <tiles:insertAttribute name="footer"/>
		</div>
		<!--/footer-->
		
	</body>
</html>