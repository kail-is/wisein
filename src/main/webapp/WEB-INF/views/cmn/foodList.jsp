<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/foodList.css">
</head>
<div class="content-wrap">
    <section class="content-frame">
        <div class="top-group">
            <div class="category-wrap">
                <p class="category-select">
                    <select onchange="categorySelect(this)">
                        <option value="none">-----</option>
                        <c:forEach items="${comCategory}" var="category">
                            <option value="cate"><c:out value="${category.location}"/></option>
                        </c:forEach>
                    </select>
                </p>
                <ul class="person-function">
                    <li><a href="#">지역(가나다)</a></li>
                    <li><a href="#">사이트(가나다)</a></li>
                    <li><a href="#">맛집 개수</a></li>
                </ul>
                <span class="material-icons">
                        expand_more
                    </span>
            </div>
            <input type="button" value="추천">
        </div>

        <div class="map-wrap">
            <div id="map"></div>
        </div>
        <div class="board-wrap">
            <div class="board-list">
                <div class="board-line board-header">
                    <div class="board-cell board-category purple2">
                        <p>
                            <select onchange="categorySelect(this)">
                                <option value="none">-----</option>
                                <c:forEach items="${comCategory}" var="category">
                                    <option value="cate"><c:out value="${category.location}"/></option>
                                </c:forEach>
                            </select>
                        </p>

                        <ul class="person-function">
                            <li><a href="#">지역(가나다)</a></li>
                            <li><a href="#">사이트(가나다)</a></li>
                            <li><a href="#">맛집 개수</a></li>
                        </ul>
                        <span class="material-icons">
                                expand_more
                            </span>
                    </div>
                    <div class="board-cell board-title">
                        <p>site</p>
                    </div>
                    <div id="changeText" class="board-cell board-map gray">
                        맛집
                    </div>
                </div>






            <div class="button-wrap">
                <input type="button" value="추천">
            </div>
        </div>
        <div class="pageNo-wrap">
            <ul class="pageno-group gray">
                <li>1</li>
                <li>2</li>
                <li>3</li>
                <li>4</li>
                <li class="current purple">5</li>
                <li>
                        <span class="material-icons">
                            chevron_right
                        </span>
                </li>
            </ul>
        </div>
    </section>

    <script src="resources/js/common/foodlist.js"></script>
</div>