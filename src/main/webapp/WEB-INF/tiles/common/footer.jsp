<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!--반응형 서치-->
    <div class="search-wrap mobile-search">
        <div class="select-wrap">
             <select name="searchType" class="searchType" id="search-list2">
                <option value="all"   ${searchType eq 'all' ? 'selected':''}>전체검색</option>
                <option value="front" ${searchType eq 'front'?'selected':''}>FRONT</option>
                <option value="back"  ${searchType eq 'back'?'selected':''}>BACK</option>
                <option value="db"    ${searchType eq 'db'?'selected':''}>DB</option>
             </select>
        </div>
        <input type="text" id="keywordInput2" class="search-bar" placeholder="검색할 제목을 입력하세요" value="${keyword}" onkeyup="enterKey('MO')"/>
        <span class="material-icons">
            <a onclick="enterKey('MO')">search</a>
        </span>
    </div>

    <div class="top-arrow">
        <a href="#">
            <span class="material-icons purple">
                keyboard_arrow_up
            </span>
        </a>
    </div>
