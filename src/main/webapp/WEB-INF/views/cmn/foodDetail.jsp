<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/foodDetail.css">
</head>
<div class="content-wrap">
    <div class="info-wrap">

        <ul class="info">
            <li>홍대 쌀국수 회현점</li>
            <li>평가</li>
        </ul>
    </div>
    <section class="content-frame">
        <div class="map-wrap">
            <div id="map"></div>
        </div>
        <div class="food-info-wrap">
            <div class="food-info-title">
                홍대 쌀국수 회현점
            </div>
            <div class="food-info-addr gray">
                서울특별시 중구 덕계로 72 지하상가 B104호
            </div>
            <div class="food-info-content">
                모든 국민은 소급입법에 의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다. 모든 국민은 학문과 예술의 자유를 가진다. 법관은 헌법과 법률에 의하여 그 양심에 따라 독립하여 심판한다.대통령의 임기가 만료되는 때에는 임기만료 70일 내지 40일전에 후임자를 선거한다. 국회는 상호원조 또는 안전보장에 관한 조약, 중요한 국제조직에 관한 조약, 우호통상항해조약, 주권의 제약에 관한 조약, 강화조약, 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결·비준에 대한 동의권을 가진다.
            </div>

        </div>
    </section>
    <div class="score-wrap">
        <p class="purple">평가</p>
        <span class="material-icons purple">
                star
            </span>
        <span class="material-icons purple">
                star
            </span>
        <span class="material-icons purple">
                star
            </span>
        <span class="material-icons purple">
                star
            </span>
        <span class="material-icons gray">
                star
            </span>
        <p class="purple">4.0(2)</p>
    </div>

    <section class="content-frame">
        <div class="food-board-wrap">
            <div class="food-board-title">
                분짜가 진짜 최고!
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
            </div>
            <div class="food-board-writer gray">
                서은빈(OK저축은행)
            </div>
            <div class="food-board-img">
                <img src="../image/pizza.jpg" alt="">
            </div>
            <div class="food-board-content">
                모든 국민은 소급입법에 의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다. 모든 국민은 학문과 예술의 자유를 가진다. 법관은 헌법과 법률에 의하여 그 양심에 따라 독립하여 심판한다.대통령의 임기가 만료되는 때에는 임기만료 70일 내지 40일전에 후임자를 선거한다. 국회는 상호원조 또는 안전보장에 관한 조약, 중요한 국제조직에 관한 조약, 우호통상항해조약, 주권의 제약에 관한 조약, 강화조약, 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결·비준에 대한 동의권을 가진다.
            </div>
        </div>
    </section>
    <section class="content-frame">
        <div class="food-board-wrap">
            <div class="food-board-title">
                분짜가 진짜 최고!
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
            </div>
            <div class="food-board-writer gray">
                <p>서은빈(OK저축은행)</p>
                <span class="material-icons purple">
                        border_color
                    </span>
                <span class="material-icons purple">
                        delete
                    </span>
            </div>
            <div class="food-board-img">
                <img src="../image/pizza.jpg" alt="">
            </div>
            <div class="food-board-content">
                모든 국민은 소급입법에 의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다. 모든 국민은 학문과 예술의 자유를 가진다. 법관은 헌법과 법률에 의하여 그 양심에 따라 독립하여 심판한다.대통령의 임기가 만료되는 때에는 임기만료 70일 내지 40일전에 후임자를 선거한다. 국회는 상호원조 또는 안전보장에 관한 조약, 중요한 국제조직에 관한 조약, 우호통상항해조약, 주권의 제약에 관한 조약, 강화조약, 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결·비준에 대한 동의권을 가진다.
            </div>
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