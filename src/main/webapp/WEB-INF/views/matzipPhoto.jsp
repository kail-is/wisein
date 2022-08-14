<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>

.hey {
   width: 500px;
   height: 300px;
}
.swiper-container {
	padding:30px 0;
	border:5px solid silver;
	border-radius:7px;
	box-shadow:0 0 20px #ccc inset;
}
.swiper-slide {
	text-align:center;
	display:flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items:center; /* 위아래 기준 중앙정렬 */
	justify-content:center; /* 좌우 기준 중앙정렬 */
}
:root {
    --swiper-theme-color: lightgray;
    --swiper-navigation-size: 30px;
}

</style>

<div class="hey" >
<div class="swiper mySwiper" style=" ">
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


<script>
    var swiper = new Swiper(".mySwiper", {
    autoHeight : true,
    loop : true,
    navigation: {
     nextEl: ".swiper-button-next",
     prevEl: ".swiper-button-prev",
    },
   });

  </script>