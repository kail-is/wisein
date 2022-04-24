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
</header>

<div class="sidebar-wrap">
    <input type="checkbox" name="" id="bar-chk" style="display: none;">
    <div class="sidebar">
        <div class="top">
            <div class="info">
                <img src="resources/image/test1.png" alt="">
                <label for="my-function-chk">서은빈(직위)<br>OK 저축은행</label>
                <input type="checkbox" name="" id="my-function-chk" style="display: none;">
                <ul class="person-function">
                    <li><a href="#">정보 수정</a></li>
                    <li><a href="#">질문 모아 보기</a></li>
                    <li><a href="#">답변 모아 보기</a></li>
                </ul>
            </div>
            <ul class="menu">
                <li><a href="#">질문하고 싶어요</a></li>
                <li><a href="#">Tech Tip</a></li>
                <li><a href="#">사이트 별 맛집 목록</a></li>
                <li><a href="#">현재 파견 리스트</a></li>
            </ul>
            <div class="search-bar">
                    <span class="material-icons">
                        search
                    </span>
                <input type="text">
            </div>
        </div>
        <div class="bottom">
            <ul class="icon">
                <li>
                        <span class="material-icons">
                            perm_identity
                        </span>

                </li>
                <li>
                        <span class="material-icons">
                            link
                        </span>
                </li>
            </ul>
        </div>
    </div>
</div>

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
            <button type="button"></button>
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
            <div class="board-line">
                <div class="board-cell board-no">
                    1
                </div>
                <div class="board-cell board-category purple">
                    BACK
                </div>
                <div class="board-cell board-title">
                    제목(가나다)
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
            <div class="board-line">
                <div class="board-cell board-no">
                    2
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
            <div class="board-line">
                <div class="board-cell board-no">
                    3
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
            <div class="board-line">
                <div class="board-cell board-no">
                    4
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
            <div class="board-line">
                <div class="board-cell board-no">
                    5
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
            <div class="board-line">
                <div class="board-cell board-no">
                    6
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
            <div class="board-line">
                <div class="board-cell board-no">
                    7
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
            <div class="board-line">
                <div class="board-cell board-no">
                    8
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
            <div class="board-line">
                <div class="board-cell board-no">
                    9
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
            <div class="board-line">
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