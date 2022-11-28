<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--
<c:if test="${empty questionsListWriter && empty commentListWriter}">
<div class="content-wrap boardList">
</c:if>
<c:if test="${not empty questionsListWriter || not empty commentListWriter}">
<div class="content-wrap boardList" style="max-width: 1300px;">
</c:if>
-->
<div class="content-wrap boardList">
    <section class="content-frame boardList">
        <div class="content-top-group">
        <c:if test="${side_gubun ne 'Y'}">
            <button type="button" onClick="location.href='/qaBoard'" ></button>
        </c:if>
        </div>
        <div class="board-list">
            <div class="board-line board-header">
                <div class="board-cell board-no">
                </div>
                <div class="board-cell board-category purple2">
                    <p class="category-select">카테고리</p>
                    <!--
                    <ul class="person-function">
                        <li><a href="#">FRONT</a></li>
                        <li><a href="#">BACK</a></li>
                        <li><a href="#">DB</a></li>
                    </ul>
                    <span class="material-icons">
                            expand_more
                        </span>
                        -->
                </div>
                <div class="board-cell board-title ">
                    제목(가나다)
                    <!--
                    <span class="material-icons">
                        expand_more
                    </span>
                    -->
                </div>
                <div class="board-cell board-answer  gray">
                    답변
                </div>
                <div class="board-cell board-like gray">
                    좋아요
                </div>
                <div class="board-cell board-writer gray">
                    작성자
                </div>
                <div class="board-cell board-date gray">
                    날짜
                </div>
            </div>

            <c:forEach var="qa" items="${qaList}">
                <div class="board-line ">
                    <div class="board-cell board-no">
                        <c:out value="${qa.num}" />
                    </div>
                    <div class="board-cell board-category purple">
                        <c:out value="${qa.category}" />
                    </div>
                    <div class="board-cell board-title">
                        <a href="/qaDetail?num=${qa.num}&parentNum=${qa.parentNum}"><c:out value="${qa.subject}" /></a>
                    </div>
                    <div class="board-cell board-answer gray">
                        <c:if test="${qa.adpYn eq 'Y'}">
                            <span class="material-icons" style="color:purple;">
                                check_circle
                            </span>
                        </c:if>
                        <c:if test="${qa.adpYn eq 'N'}">
                            <span class="material-icons purple2">
                                help_outline
                            </span>
                        </c:if>
                    </div>

                    <c:if test="${qa.likeCount == 0}">
                        <div class="board-cell board-like gray">
                            <span class="material-icons">thumb_up</span>
                        </div>
                    </c:if>
                    <c:if test="${qa.likeCount != 0}">
                        <div class="board-cell board-like purple2">
                            <span class="material-icons">thumb_up</span>${qa.likeCount}
                        </div>
                    </c:if>

                    <div class="board-cell board-writer gray">
                        <p class="writer"><c:out value="${qa.writer}" /><br>
                        </p>
                        <ul class="person-function">
                            <li><a href="#">메일 전송</a></li>
                            <li><a href="#">질문 모아 보기</a></li>
                            <li><a href="#">답변 모아 보기</a></li>
                        </ul>
                    </div>
                    <div class="board-cell board-date gray">
                        <c:out value="${qa.regDate}" />
                    </div>
                </div>
            </c:forEach>

        </div>
    </section>
    <div class="search-wrap">
        <div class="select-wrap">
            <select name="" id="search-list">
                <option value="front">Front</option>
            </select>
        </div>
        <input type="text" class="search-bar" placeholder="Type something…" required="required">
        <span class="material-icons">
                <a href="#">search</a>
            </span>
    </div>
    <ul class="pageno-group">
	    <div class="pagination">
	    	${pagination}
    	</div>
    </ul>
</div>

<div class="top-arrow">
    <a href="#">
            <span class="material-icons purple">
                keyboard_arrow_up
            </span>
    </a>
</div>