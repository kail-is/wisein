<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String url =  request.getServerName().toString(); %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="resources/css/common.css">
        <link rel="stylesheet" href="resources/iconfont/material-icons.css">
        <link rel="stylesheet" href="resources/css/main.css">
        <script src="resources/js/jquery-3.5.1.min.js"></script>
		<script src="${url}/resources/js/common/dim.js"></script>
	</head>
	<body>
	<div id="dim-wrapper" style="display: none">
		<div id="dim"></div>
		<img id="loading-img" src="${url}/resources/image/miocat.png" class="w100" alt="" style="display:none; position: fixed; width: 120px; height: 120px;
		top: calc(50% - 60px); left: calc(50% - 60px); animation: rotate 2s infinite linear; z-index: 100;
		">
		<style>
            @keyframes rotate {
                from {
                    -webkit-transform: rotate(0deg);
                    -o-transform: rotate(0deg);
                    transform: rotate(0deg);
                }
                to {
                    -webkit-transform: rotate(360deg);
                    -o-transform: rotate(360deg);
                    transform: rotate(360deg);
                }
            }
		</style>
	</div>
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

		</div>
	</body>

    <script src="${url}/resources/js/common/authCheck.js"></script>
    <script src="${url}/resources/js/common/util.js"></script>
    <script src="${url}/resources/js/common/login.js"></script>

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