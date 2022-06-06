<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<head>
    <link rel="stylesheet" href="resources/css/tipList.css?ver=1">
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
            <button type="button" onClick="location.href='regTip'" ></button>
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

            <c:forEach var="tip" items="${tipList}">
                <div class="board-line">
                    <div class="board-cell board-no">
                        <c:out value="${tip.num}" />
                    </div>
                    <div class="board-cell board-category purple">
                        <c:out value="${tip.category}" />
                    </div>
                    <div class="board-cell board-title">
                        <a href="/tipDetail?num=${tip.num}"><c:out value="${tip.subject}" /></a>
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
                        <p class="writer"><c:out value="${tip.writer}" /><br></p>
                        <ul class="person-function">
                            <li><a href="#">메일 전송</a></li>
                            <li><a href="#">질문 모아 보기</a></li>
                            <li><a href="#">답변 모아 보기</a></li>
                        </ul>
                    </div>
                    <div class="board-cell board-date gray">
                        <fmt:formatDate value="${tip.regDate}" pattern="yyyy-MM-dd"/>
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
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li class="current">5</li>
        <li>6</li>
        <li>7</li>
        <li>8</li>
        <li>9</li>
        <li>10</li>
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