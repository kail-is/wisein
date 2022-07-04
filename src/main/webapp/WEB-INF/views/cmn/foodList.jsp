<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="resources/css/foodList.css">
</head>
<div class="content-wrap">
    <section class="content-frame">
        <div class="top-group">
            <div class="category-wrap">
                <p class="category-select">지역(가나다)</p>
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
                            <select id="category">
                                <option value="">-----</option>
                                 <c:forEach items="${selectCompany}" var="category">
                                    <option value="${category.location}"><c:out value="${category.location}"/></option>
                                </c:forEach>
                            </select>
                        </p>
                        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d9d935757467c55bbacfbd5c39b0824&libraries=services"></script>


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
                        <span class="material-icons">
                                expand_more
                            </span>
                    </div>
                    <div id="hello" class="board-cell board-map gray">
                        맛집
                        <span class="material-icons">
                                expand_more
                            </span>
                    </div>
                </div>

            <script src="resources/js/common/foodlist.js"></script>

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
</div>

    <script>
        let writer = document.getElementsByClassName("writer")
        Array.from(writer).forEach(function(element) {
            element.addEventListener('click', function(e) {
                if(e.target.nextElementSibling.style.display === 'block'){
                    e.target.nextElementSibling.style.display = 'none';
                }else{
                    e.target.nextElementSibling.style.display = 'block';
                }
            });
        });
    </script>


</body>
</html>