<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <p id="matzip-data"> ${matzip.matzipData} </p>
    <div class="content-wrap">
        <div id="contents" class="matzip-write matzip-upd">

        <input type="hidden" size="100" id="num" value="${recm.num}" disabled>
        <input type="hidden" size="210" id="writer" value="${recm.writer}" required disabled>
        <input type="hidden" size="210" id="star-val" value="${recm.star}">

            <div class="content-inner-box">
                <select name="star" id="star">
                     <option value="1">★☆☆☆☆</option>
                     <option value="2">★★☆☆☆</option>
                     <option value="3">★★★☆☆</option>
                     <option value="4">★★★★☆</option>
                     <option value="5">★★★★★</option>
                </select>

                 <select name="category" id="category" disabled>
                     <option value="" selected> ${matzip.category} </option>
                     <option value="인덕원">인덕원</option>
                     <option value="회현">회현</option>
                     <option value="을지로">을지로</option>
                 </select>

                <input type="text" size="210" id="matzip-name" placeholder="맛집 이름" disabled>
                <input type="text" size="210" id="matzip-address" class="address matzip-upd" placeholder="주소" disabled>

            </div>

            <input type="text" size="210" id="subject" value="${recm.subject}" required>
            <textarea id="content" class="none">${recm.content}</textarea>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
               <input type="button" value="수정" onclick="matzipUpd()">
               <input type="button" value="취소" onclick="cancel()">
            </div>
        </div>