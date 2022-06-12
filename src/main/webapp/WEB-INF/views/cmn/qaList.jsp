<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/qaList.css?ver=1">
</head>
<body>

<header>
    <ul>
        <li class="logo"><a href="#">wiseIN</a></li>
        <li class="search-wrap">
            <div class="select-wrap">
                <select name="" id="search-list">
                    <option value="front">Front</option>
                </select>
            </div>
            <input type="text" class="search-bar" placeholder="Type something…" required="required">
        </li>
        <li class="bar">
            <label for="bar-chk">
                    <span class="material-icons">
                        menu
                    </span>
            </label>

        </li>
    </ul>
    <style>
    	.pagination {
		padding: 10px;
		text-align: center;
		}
		.pagination a {
			color : black;
			text-decoration : none;
			font-size: 16px;
			padding: 5px;
			/* border: 1px solid #ddd; */
			border-radius: 5px;
		}
		.pagination a.active {
			font-weight: 500;
			color : red;
		}
		.pagination a:hover:not(.active) {
			/* background-color: #e5e7f5; */
		}
    </style>
</header>

<div class="content-wrap">
    <section class="content-frame">
        <div class="content-top-group">
            <div class="category-group">
                <p class="title-select">제목(가나다)</p>
                <ul class="person-function">
                    <li><a href="#">제목</a></li>
                    <li><a href="#">카테고리</a></li>
                    <li><a href="#">글쓴이</a></li>
                </ul>
                <span class="material-icons">
                        expand_more
                    </span>
            </div>
            <button type="button" onClick="location.href='qaBoard'" ></button>
        </div>
        <div class="board-list">
            <div class="board-line board-header">
                <div class="board-cell board-no">
                </div>
                <div class="board-cell board-category purple2">
                    <p class="category-select">카테고리</p>
                    <ul class="person-function">
                        <li><a href="#">FRONT</a></li>
                        <li><a href="#">BACK</a></li>
                        <li><a href="#">DB</a></li>
                    </ul>
                    <span class="material-icons">
                            expand_more
                        </span>
                </div>
                <div class="board-cell board-title">
                    제목(가나다)
                    <span class="material-icons">
                            expand_more
                        </span>
                </div>
                <div class="board-cell board-answer gray">
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
                <div class="board-line">
                    <div class="board-cell board-no">
                        <c:out value="${qa.num}" />
                    </div>
                    <div class="board-cell board-category purple">
                        <c:out value="${qa.category}" />
                    </div>
                    <div class="board-cell board-title">
                        <a href="/qaDetail${pageDTO.makeSearch()}&num=${qa.num}"><c:out value="${qa.subject}" /></a>
                    </div>
                    <div class="board-cell board-answer gray">
                            <span class="material-icons purple2">
                                help_outline
                            </span>
                        1
                    </div>
                    <div class="board-cell board-like gray">
                            <span class="material-icons">
                                thumb_up
                            </span>
                        1
                    </div>
                    <div class="board-cell board-writer gray">
                        <p class="writer"><c:out value="${qa.writer}" /><br><span class="xs-off">(OK저축은행)</span><span class="xs-on">21-10-24</span></p>
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

            <!-- <div class="board-line">
                <div class="board-cell board-no">
                    10
                </div>
                <div class="board-cell board-category purple2">
                    FRONT
                </div>
                <div class="board-cell board-title">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Beatae placeat iusto quidem assumenda eum quibusdam cupiditate, inventore tempore fugiat in incidunt labore illum consequuntur facere. Reiciendis provident impedit libero quos.
                </div>
                <div class="board-cell board-answer gray">
                        <span class="material-icons purple">
                            check_circle
                        </span>
                    1
                </div>
                <div class="board-cell board-like gray">
                        <span class="material-icons">
                            thumb_up
                        </span>
                    1
                </div>
                <div class="board-cell board-writer gray">
                    <p class="writer">서은빈<br><span class="xs-off">(OK저축은행)</span><span class="xs-on">21-10-24</span></p>
                    <ul class="person-function">
                        <li><a href="#">메일 전송</a></li>
                        <li><a href="#">질문 모아 보기</a></li>
                        <li><a href="#">답변 모아 보기</a></li>
                    </ul>
                </div>
                <div class="board-cell board-date gray">
                    21-10-24
                </div>
            </div>
            </div> -->
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
            <c:if test="${pageDTO.page != 1}">
                <a href='qalist${pageDTO.makeSearch(1)}'>&laquo;</a>
            </c:if>

            <c:if test="${pageDTO.prev}">
                <a href='qalist${pageDTO.makeSearch(pageDTO.startPage-1)}'>&lt;</a>
            </c:if>

            <c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" var="idx">
                <a href='qalist${pageDTO.makeSearch(idx)}' <c:out value="${pageDTO.page==idx?' class=active ':''}"/>> ${idx}</a>
            </c:forEach>

            <c:if test="${pageDTO.next}">
                <a href='qalist${pageDTO.makeSearch(pageDTO.endPage+1)}'>&gt;</a>
            </c:if>

            <c:if test="${pageDTO.page != pageDTO.totalEndPage && qaList.size()>0}">
                <a href='qalist${pageDTO.makeSearch(pageDTO.totalEndPage)}'>&raquo;</a>
            </c:if>
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

<script type="module" >
    import * as event from 'resources/js/common/event.js';
    let writer = document.getElementsByClassName("writer");
    let categorySelect = document.getElementsByClassName("category-select");
    let titleSelect = document.getElementsByClassName("title-select");

    event.clickDisplayChangeListener(writer);
    event.clickDisplayChangeListener(categorySelect);
    event.clickDisplayChangeListener(titleSelect);

</script>

</body>
</html>