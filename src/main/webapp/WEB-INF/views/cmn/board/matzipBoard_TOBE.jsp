<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="content-wrap">
        <div id="contents" class="matzip-write">

            <%-- 맛집 수정 데이터 --%>
            <c:if test="${!empty recm}">
                <p id="matzip-data" class="none"> ${matzip.matzipData} </p>
                <textarea id="content" class="none">${recm.content}</textarea>
                <input type="hidden" size="100" id="num" value="${recm.num}" disabled>
                <input type="hidden" size="210" id="mod-writer" value="${recm.writer}" disabled>
                <input type="hidden" size="210" id="star-val" value="${recm.star}">

                <input type="hidden" size="210" id="matzip_id" value="${matzip.id}" disabled>
             </c:if>

            <input type="text" size="210" id="writer" value="${member.id}" placeholder="아이디입니다" disabled>

            <div class="content-inner-box">
                <select name="star" id="star">
                     <option value="5">★★★★★</option>
                     <option value="4">★★★★☆</option>
                     <option value="3">★★★☆☆</option>
                     <option value="2">★★☆☆☆</option>
                     <option value="1">★☆☆☆☆</option>
                </select>

                <%-- 맛집 수정 --%>
                <c:if test="${!empty recm}">
                     <select name="category" id="category" disabled>
                        <option value="" selected> ${matzip.category} </option>
                     </select>
                    <input type="text" size="210" id="matzip-name" placeholder="맛집 이름" disabled>
                    <input type="text" size="210" id="matzip-address" class="address matzip-upd" placeholder="주소" disabled>
                </c:if>

                <%-- 맛집 등록 --%>
                <c:if test="${empty recm}">
                     <select name="category" id="category">
                         <option value="인덕원" selected>인덕원</option>
                         <option value="회현">회현</option>
                         <option value="을지로">을지로</option>
                     </select>
                    <input type="text" size="210" id="matzip-name" class= "none" placeholder="맛집 이름" required>
                    <input type="text" size="210" class="keyword" id='keywordBox' placeholder="키워드" autocomplete=off required>
                     <div class="button-wrap">
                        <input type="button" id="add-search-btn" value="주소 검색" onclick="findKeyword()">
                        <input type="button" id="format-search-btn" class= "none" value="다시 검색" onclick="formatKeyword()">
                    </div>
                </c:if>
            </div> <%-- content-inner-box --%>

            <%-- 제목 / 데이터 / 에디터 / 뷰어 / 버튼--%>
            <input type="text" size="210" id="subject" placeholder="제목을 입력하세요"  value="${recm.subject}" autocomplete=off required>
            <input type="hidden" size="210" id="matzip_upload_data" placeholder="맛집데이터" required>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
                <c:if test="${empty recm}">
                   <input type="button" value="등록" onclick="matzipSubmit()">
               </c:if>
               <c:if test="${!empty recm}">
                   <input type="button" value="수정" onclick="matzipUpd()">
               </c:if>
               <input type="button" value="취소" onclick="cancel()">
            </div>
        </div>


        <%-- 맛집 검색 팝업 --%>
        <div id="matzipPopupBox" class="popup-wrap page-center none">
            <div class="signUp-popup-wrap matzip-wrap" style="">
            <p class="close" onclick="popupManger.popupClose()"></p>
            <h2 class="center mb1r">맛집검색</h2>
                <form action="" id="popup-content_form" onSubmit="return false;">
                    <ul id="matzipList" class="matzip-list-box"></ul>
                    <div class="signUp-confirm-Button-wrap">
                        <button type="submit" onclick="popupManger.popupConfirm(form)" id="confirm_btn" name="confirm_btn">확인</button>
                    </div>
                </form>
            </div>
	    </div>