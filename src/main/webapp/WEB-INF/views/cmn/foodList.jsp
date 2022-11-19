<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrap food-wrap">
    <section class="content-frame food-wrap">
        <div class="top-group">
            <div class="category-wrap">
                <p class="category-select">
                    <select id="selectOption" onchange="categorySelect(this)">
                        <option value="none">-----</option>
                        <c:forEach items="${comCategory}" var="category">
                            <option value="cate"><c:out value="${category.location}"/></option>
                        </c:forEach>
                    </select>
                </p>

                <span class="material-icons">
                        expand_more
                    </span>
            </div>
            <input class="recm-btn" type="button" onclick="location.href='matzipBoard'" value="추천">
        </div>

        <div class="map-wrap food-wrap">
            <div id="map"></div>
        </div>
        <div class="board-wrap">
            <div class="board-list food-wrap">
                <div class="board-line board-header">
                    <div class="board-cell board-category purple2">
                        <p>
                            <select class="select-list" onchange="categorySelect(this)">
                                <option value="none">-----</option>
                                <c:forEach items="${comCategory}" var="category">
                                    <option value="cate"><c:out value="${category.location}"/></option>
                                </c:forEach>
                            </select>
                        </p>

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
                    <div class="board-cell board-text">
                        로드뷰
                    </div>
                </div>

            <div class="button-wrap">
                <input type="button" onclick="location.href='matzipBoard'"value="추천">
            </div>
        </div>

        <div id="page" class="page-wrap">
        </div>

    </section>

    <script src="resources/js/common/foodlist.js"></script>
</div>