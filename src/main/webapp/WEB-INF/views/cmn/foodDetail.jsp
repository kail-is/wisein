<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
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
    <section class="content-frame food-detail">
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
                    <i class="fas fa-exclamation-circle font-awe"></i> 폐업 신고
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
                <a href="/updRecm?id=${recm.num}"> <i class="fas fa-pencil-alt font-awe"></i> </a> </p>
                <p class="recm-del recm-icon" id="del-${recm.num}" onclick="delRecm(${recm.num})">
                    <i class="fas fa-trash-alt font-awe"></i>
                </p>
            </div>
            <div class="food-board-writer gray">
                <p class=""> <c:out value="${recm.writer}" /></p>
                <p class=""> <fmt:formatDate value="${recm.regDate}" pattern="yyyy-MM-dd" /></p>
            </div>
            <div class="food-board-img">
                <div class="swiper matzipSwiper" style=" ">
                    <div class="swiper-wrapper">
                    <c:forEach var="file" items="${fileList}">
                      <div class="swiper-slide">
                        <img src="${file.filePath}" />
                      </div>
                    </c:forEach>
                    </div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
            <div class="food-board-content">
                <c:out value="${recm.content}" escapeXml="false" />
            </div>
        </div>
    </section>
    </c:forEach>
</div>
    <script src="${url}/resources/js/foodDetail.js"></script>

    <script>
        var swiper = new Swiper(".matzipSwiper", {
        navigation: {
         nextEl: ".swiper-button-next",
         prevEl: ".swiper-button-prev",
        },
       });

      </script>