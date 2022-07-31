<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="content-wrap ">
            <div id="contents" class="matzip-write">

            <input type="text" size="210" id="writer" value="${member.id}" placeholder="아이디입니다" required>

            <div class="content-inner-box">
                <select name="star" id="star">
                     <option value="1">☆☆☆☆☆</option>
                     <option value="2">★☆☆☆☆</option>
                     <option value="3" selected>★★★☆☆</option>
                     <option value="4">★★★★☆</option>
                     <option value="5">★★★★★</option>
                </select>

                 <select name="category" id="category">
                     <option value="인덕원">인덕원</option>
                     <option value="회현">회현</option>
                     <option value="을지로">을지로</option>
                 </select>

                <input type="text" size="210" id="matzip-name" class= "none" placeholder="맛집 이름" required>

                <input type="text" size="210" class="address" placeholder="주소" required>


                 <div class="button-wrap">
                    <input type="button" value="주소 검색" onclick="find()">
                </div>

            </div>


            <input type="text" size="210" id="subject" placeholder="제목을 입력하세요" required>

            <input type="text" size="210" id="matzip_upload_data" onchange="matzipDataSet(this)" placeholder="맛집데이터" required>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
               <input type="button" value="등록" id="matzip-submit-btn" onclick="matzipSubmit()">
               <input type="button" value="취소" onclick="cancel()">
            </div>
        </div>
    </div>
    <script src='${url}/resources/js/matzip.js'> </script>