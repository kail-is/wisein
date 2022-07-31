<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link rel="stylesheet" href="resources/css/foodDetail.css">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css'/>
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
            <div id="map" style="width:100%;height:350px;"></div>
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
            <c:if test="${matzip.closedState == 'N'}">
                <div id="ban-button" class="ban-button" onclick="reportClosed()">
                    <i class="fas fa-exclamation-circle"></i> 폐업 신고
                </div>
            </c:if>
            <c:if test="${matzip.closedState == 'Y'}">
                <div class="closed-matzip-report">해당 매장은 더는 운영하지 않습니다.</div>
            </c:if>
        </div>
    </section>
    <div class="score-wrap">
        <div class="rate-wrap">
            <p>평가</p>
            <div class="rating-star">
              <div class="rating-score">
                <div class="outer-star">
                    <div class="inner-star">${matzip.rate}</div>
                </div>
              </div>
            </div>
            <p> ${matzip.rate} (${matzip.count}) </p>
        </div>
    </div>

    <c:forEach var="recm" items="${recmList}">
    <section class="content-frame">
        <div class="food-board-wrap">
            <div class="food-board-title">
                <p><c:out value="${recm.subject}" /></p>
                <div class="rating-star">
                  <div class="rating-score">
                    <div class="outer-star">
                        <div class="inner-star">${recm.star}</div>
                    </div>
                  </div>
                </div>
                <p class="recm-upd recm-icon" id="upd-${recm.num}">
                <a href="/updRecm?id=${recm.num}"> <i class="fas fa-pencil-alt"></i> </a> </p>
                <p class="recm-del recm-icon" id="del-${recm.num}" onclick="delRecm(${recm.num})">
                    <i class="fas fa-trash-alt"></i>
                </p>
            </div>
            <div class="food-board-writer gray">
                <p class=""> <c:out value="${recm.writer}" /></p>
                <p class=""> <fmt:formatDate value="${recm.regDate}" pattern="yyyy-MM-dd" /></p>
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


    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=31a7b466aaed9176525d555ca8a9644e"></script>
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

        // 데이터 적재
        const matzip_obj = JSON.parse(document.getElementById('matzip_data').innerText)
        const matzip_id = matzip_obj.documents[0].id;
        document.getElementById('food-info-addr').innerText = matzip_obj.documents[0].address_name;
        document.getElementById('food-info-title').innerText = matzip_obj.documents[0].place_name;
        document.getElementById('info-wrap-title').innerText = matzip_obj.documents[0].place_name;
        document.getElementById('food-info-content').innerText = matzip_obj.documents[0].place_url;

        // 댓글 삭제
        let upd = document.getElementsByClassName("recm-upd")
        let del = document.getElementsByClassName("recm-del")

        function delRecm(recmId) {

           let delConfirm = confirm('삭제하시겠습니까?');

           if (delConfirm) {
                $.ajax({
                    data:{"num": recmId},
                    type:"GET",
                    url:"/delRecm",
                    success:function(data) {
                        alert('삭제 완료');
                        window.location.href = "recmCnt?id=${matzip.id}"
                    },
                    error:function(request, status, error) {
                        alert("실패");
                    }
                })
           }else {
              alert('삭제 취소');
           }
        }

        // 지도
        var xDis = matzip_obj.documents[0].x
        var yDis = matzip_obj.documents[0].y
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div

            mapOption = {
                center: new kakao.maps.LatLng(yDis, xDis), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 마커가 표시될 위치입니다
        var markerPosition  = new kakao.maps.LatLng(yDis, xDis);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        var iwContent = '<div style="padding:5px;">' + matzip_obj.documents[0].place_name +  '<br> <a href="' + matzip_obj.documents[0].place_url + '" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = new kakao.maps.LatLng(yDis, xDis); //인포윈도우 표시 위치입니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            position : iwPosition,
            content : iwContent
        });

        // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
        infowindow.open(map, marker);

        let starCnt = document.querySelectorAll('.inner-star').length
        let starGrp = document.querySelectorAll('.inner-star')

        for (let z of starGrp ) {
                ratingPercentage = z.getInnerHTML() / 5 * 100
                ratingRounded = ratingPercentage + '%'
                z.style.width = ratingRounded
        }

        // 폐업 신고

        function reportClosed(){

           let reptConfirm = confirm('업체 폐업 신고를 하시겠습니까?');

           if (reptConfirm) {
                $.ajax({
                    data:{"matzip_id": matzip_id},
                    type:"GET",
                    url:"/reportClosed",
                    success:function(data) {
                        alert('정상 신고 처리 되었습니다. 관리자 확인 뒤 폐업처리됩니다.');
                    },
                    error:function(request, status, error) {
                        alert("실패");
                    }
                })
           }else {
              alert('삭제 취소');
           }


        }

    </script>


</body>
</html>