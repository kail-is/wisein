<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<<<<<<< HEAD
=======
<% String url =  request.getServerName().toString(); %>
>>>>>>> master
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>메인</title>
        <link rel="stylesheet" href="${url}/resources/iconfont/material-icons.css">
        <link rel="stylesheet" href="${url}/resources/css/main.css">
        <script src="resources/js/jquery-3.5.1.min.js"></script>
    </head>
<body>
    <header>
        <ul>
            <li class="logo"><a href="/">wiseIN</a></li>
            <li class="search-wrap">
<<<<<<< HEAD
                <div class="select-wrap">  
                    <select name="searchType" class="searchType" id="search-list">
	                    <option value="all"   ${qaListDTO.getSearchType() eq 'all' ? 'selected':''}>카테고리 검색 </option>
						<option value="front" ${qaListDTO.getSearchType() eq 'front'?'selected':''}>FRONT</option>
			    		<option value="back"  ${qaListDTO.getSearchType() eq 'back'?'selected':''}>BACK</option>
			    		<option value="db"    ${qaListDTO.getSearchType() eq 'db'?'selected':''}>DB</option>
	                </select>
                </div>
                <input type="text" id="keywordInput" class="search-bar" placeholder="Type something…" onkeyup="enterkey()" value="${qaListDTO.getKeyword()}" />
=======
                <div class="select-wrap">
                    <select name="searchType" class="searchType" id="search-list">
	                    <option value="all"   ${searchType eq 'all' ? 'selected':''}>카테고리 검색 </option>
						<option value="front" ${searchType eq 'front'?'selected':''}>FRONT</option>
			    		<option value="back"  ${searchType eq 'back'?'selected':''}>BACK</option>
			    		<option value="db"    ${searchType eq 'db'?'selected':''}>DB</option>
	                </select>
                </div>
                <input type="text" id="keywordInput" class="search-bar" placeholder="Type something…" onkeyup="enterkey()" value="${keyword}" />
>>>>>>> master
            </li>
            <li class="bar">
                <label for="bar-chk">
                    <span class="material-icons">
                        menu
                    </span>
                </label>

            </li>
        </ul>
    </header>
<<<<<<< HEAD
    
=======

>>>>>>> master
    <script>
       function enterkey() {
    	   var selectedSearchType = document.getElementById('search-list');
    	   var inputKeyword = document.getElementById('keywordInput');
    	   
           if(window.event.keyCode == 13) {
<<<<<<< HEAD
       		   self.location = "qalist" 
=======
       		   self.location = "/integration/board"
>>>>>>> master
       						 + '?searchType=' + selectedSearchType.value
       	   					 + "&keyword=" + inputKeyword.value
           }
       }
    </script>
