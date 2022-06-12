<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/foodDetail.css">
</head>

<div id='matzip_data' style='display:none'>
${matzip.matzipData}
</div>

<div class="content-wrap">
    <div class="info-wrap" style="margin-top:0">
        <ul class="info">
            <li id="info-wrap-title">회현 쌀국수</li>
            <li>평가</li>
        </ul>
    </div>
    <section class="content-frame">
        <div class="map-wrap">
            <div id="map" >
                <img style="width: inherit;" src="https://devtalk.kakao.com/uploads/default/original/2X/5/5396f4466d7ba5ebeab81fe7d4e1d6b98ae19b34.png">
            </div>
        </div>
        <div class="food-info-wrap">
            <div id="food-info-title" class="food-info-title">
                ${matzip.id}
            </div>
            <div id="food-info-addr" class="food-info-addr gray">
                ${matzip.matzipData}
            </div>
            <div id="food-info-content" class="food-info-content">
                모든 국민은 소급입법에 의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다. 모든 국민은 학문과 예술의 자유를 가진다. 법관은 헌법과 법률에 의하여 그 양심에 따라 독립하여 심판한다.대통령의 임기가 만료되는 때에는 임기만료 70일 내지 40일전에 후임자를 선거한다. 국회는 상호원조 또는 안전보장에 관한 조약, 중요한 국제조직에 관한 조약, 우호통상항해조약, 주권의 제약에 관한 조약, 강화조약, 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결·비준에 대한 동의권을 가진다.
            </div>

        </div>
    </section>
    <div class="score-wrap">
        <p class="purple">평가</p>
        <span class="material-icons purple">
                star_border
            </span>
        <span class="material-icons purple">
                star_border
            </span>
        <span class="material-icons purple">
                star_border
            </span>
        <span class="material-icons purple">
                star_border
            </span>
        <span class="material-icons gray">
                star_border
            </span>
        <p class="purple">${matzip.rate} (${matzip.count})</p>
    </div>

    <c:forEach var="recm" items="${recmList}">
    <section class="content-frame">
        <div class="food-board-wrap">
            <div class="food-board-title">
                <c:out value="${recm.subject}" />
                <span class="material-icons purple">
                       star_border
                    </span>
                <span class="material-icons purple">
                        star_border
                    </span>
                <span class="material-icons purple">
                        star_border
                    </span>
                <span class="material-icons purple">
                        star_border
                    </span>
                <span class="material-icons gray">
                        star_border
                    </span>
                <c:out value="${recm.star}" />
            </div>
            <div class="food-board-writer gray">
                <c:out value="${recm.writer}" />
            </div>
            <div class="food-board-img">
                <img src="../image/pizza.jpg" alt="">
            </div>
            <div class="food-board-content">
                <c:out value="${recm.content}" escapeXml="false" />
            </div>
        </div>
    </section>
    </c:forEach>
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

         const matzip_obj = JSON.parse(document.getElementById('matzip_data').innerText)
         const matzip_id = matzip_obj.documents[0].id;
         document.getElementById('food-info-addr').innerText = matzip_obj.documents[0].address_name;
         document.getElementById('food-info-title').innerText = matzip_obj.documents[0].place_name;
         document.getElementById('info-wrap-title').innerText = matzip_obj.documents[0].place_name;
         document.getElementById('food-info-content').innerText = matzip_obj.documents[0].place_url;

        debugger;
    </script>

</body>
</html>