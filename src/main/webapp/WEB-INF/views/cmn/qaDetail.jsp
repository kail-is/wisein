<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
    <link rel="stylesheet" href="resources/css/qaDetail.css">
</head>
<div class="content-wrap">
    <div class="info-wrap">
        <ul class="info">
            <li>이런 내용이 궁금해요</li>
            <li>1. 답변을 드립니다</li>
            <li>2. 이것도 참고해 보세요</li>
        </ul>
    </div>
    <section class="questions content-frame">
        <div class="info">
            <div class="title">
                <c:out value="${qaListDTO.subject}" />
                <c:if test="${qaListDTO.adpYn eq 'N'}">
                    <a href="#" role="button" class="button btn_register" id="comment_btn" name="comment_btn" style="float: right;">답변!</a>
                </c:if>
            </div>
        </div>
        <div class="writer-wrap">
            <p class="writer"><c:out value="${qaListDTO.writer}" /></p>
            <ul class="person-function">
                <li><a href="#">메일 전송</a></li>
                <li><a href="#">질문 모아 보기</a></li>
                <li><a href="#">답변 모아 보기</a></li>
            </ul>
            <span class="material-icons purple">videocam</span>
        <c:if test="${member.id == qaListDTO.writer}">
            <span class="material-icons" id="update_btn">border_color</span>
            <span class="material-icons" id="delete_btn">delete</span>
        </c:if>
        </div>
        <div class="subject">
            ${content}
        </div>
    </section>

<c:set var="i" value="${0}"  />
<c:forEach var="commentQa" items="${commentQaList}" varStatus="status">
    <c:set var="commentQaContent" value="${commentContent[i]}"  />
    <c:set var="commentQaNum" value="${commentQa.num}"  />

<c:set var="i" value="${i+1}"  />
    <section class="questions content-frame">
        <div class="title">
            <c:out value="${commentQa.subject}" />
            <div class="icon" style="float:right;">

                <c:set var="check" value="0"/>
                <c:forEach var="likeQa" items="${likeQaBoardList}" varStatus="status">
                    <c:set var="done_loop" value="false"/>
                    <c:if test="${likeQa.boardIdx == commentQa.num && likeQa.likeCheck == 1}">
                        <span class="material-icons" id="comment_like_btn" onclick="comment_like_btn(${commentQaNum})" >thumb_up</span>
                        <c:set var="check" value="1"/>
                        <c:set var="done_loop" value="true"/>
                    </c:if>
                </c:forEach>

                <c:if test="${check == 0}">
                    <span class="material-icons" id="comment_like_btn" onclick="comment_like_btn(${commentQaNum})" style="color:gray;" >thumb_up</span>
                </c:if>

                &nbsp;&nbsp;
                <span class="material-icons" id="comment_like_btn" onclick="comment_like_btn(${commentQaNum})" style="color:gray;" >bookmarks</span>

                <c:if test="${member.id == qaListDTO.writer && qaListDTO.adpYn eq 'N'}">
                    <a href="#" role="button" class="" id="adp_btn" onclick="adp_btn(${commentQaNum})" style="float: right;">채택하기!</a>
                </c:if>
                <c:if test="${qaListDTO.adpNum == commentQaNum && qaListDTO.adpYn eq 'Y'}">
                   <a href="#" style="float: right;">질문자채택!</a>
                </c:if>
            </div>
        </div>
        <div class="writer-wrap">
            <p class="writer"><c:out value="${commentQa.writer}" /></p>
            <ul class="person-function">
                <li><a href="#">메일 전송</a></li>
                <li><a href="#">질문 모아 보기</a></li>
                <li><a href="#">답변 모아 보기</a></li>
            </ul>

            <span class="material-icons purple">videocam</span>

            <c:if test="${member.id == commentQa.writer}">
                <span class="material-icons" id="comment_update_btn" onclick="comment_update_btn(${commentQaNum})" >border_color</span>
                <span class="material-icons" id="comment_delete_btn" onclick="comment_delete_btn(${commentQaNum})" >delete</span>
            </c:if>
        </div>
        <div class="subject">
            ${commentQaContent}
        </div>
    </section>
</c:forEach>
</div>

    <script>
        function comment_like_btn(num) {
            var writer = '<c:out value="${member.id}" />';
            var boardNum = '<c:out value="${qaListDTO.num}" />';
               if(writer != ""){
                   $.ajax({
                       data:{
                           "num":num,
                           "boardNum":boardNum
                       },
                       type:"GET",
                       url:"/qaLike",
                       success:function(data) {
                           console.log(data);
                           var likeCheck = data.likeCheck;
                           window.location.href="/qaDetail?num="+boardNum;
                       },
                       error:function(request, status, error) {
                           alert("실패");
                       }
                   })
               } else if(writer == ""){
                   alert("로그인 후 이용가능합니다.");
                   window.location.href="/login";
               }
        }
    </script>

    <script>
        function comment_update_btn(num) {
            window.location.href="/qaUpdate?num="+num;
        }
        function comment_delete_btn(num) {
            if(confirm('정말 삭제하시겠습니까?') == true){
                window.location.href="/qaDelete?num="+num;
            }
        }
    </script>


    <script>
        $("#update_btn").click(function(){
            window.location.href="/qaUpdate?num=${qaListDTO.num}"
        });
    </script>
    <script>
        $("#delete_btn").click(function(){
            if(confirm('정말 삭제하시겠습니까?') == true){
                window.location.href="/qaDelete?num=${qaListDTO.num}"
            }
        });
    </script>

    <script>
        $("#comment_btn").click(function(){
           var writer = '<c:out value="${member.id}" />';
           if(writer != ""){
               var parentNum = '<c:out value="${qaListDTO.num}" />';
               var category = '<c:out value="${qaListDTO.category}" />';
               var writer = '<c:out value="${member.id}" />';

               $.ajax({
                   data:{
                       "parentNum":parentNum
                       ,"category":category
                       ,"writer":writer
                   },
                   type:"POST",
                   url:"/qaRegComment",
                   success:function(data) {
                       window.location.href="/qaBoard?parentNum=" + parentNum + "&writer=" + writer +"&category="+category;
                   },
                   error:function(request, status, error) {
                       alert("실패");
                   }
               })
           } else if(writer == ""){
               alert("로그인 후 이용가능합니다.");
           }

        });
    </script>

    <script>
        function adp_btn(num) {
            var writer = '<c:out value="${member.id}" />';
            var boardNum = '<c:out value="${qaListDTO.num}" />';
            var commentNum = num;
               if(writer != ""){
                   if(confirm('정말 채택하시겠습니까?') == true){
                       $.ajax({
                           data:{
                               "boardNum":boardNum,
                               "commentNum":commentNum
                           },
                           type:"POST",
                           url:"/qaAdopt",
                           success:function(data) {
                               window.location.href="/qaDetail?num="+boardNum;
                           },
                           error:function(request, status, error) {
                               alert("실패");
                           }
                       })
                   }
               } else if(writer == ""){
                   alert("로그인 후 이용가능합니다.");
               }
        }
    </script>


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
    <script>
        function fn_qaBoard(){
           var parentNum = '<c:out value="${qaListDTO.parentNum}" />';
           var category = '<c:out value="${qaListDTO.category}" />';
           var writer = '<c:out value="${member.id}" />';

           $.ajax({
               data:{
                   "parentNum":parentNum
                   ,"category":category
                   ,"writer":writer
               },
               type:"POST",
               url:"/qaRegComment",
               success:function(data) {
                   window.location.href="/qaBoard"
               },
               error:function(request, status, error) {
                   alert("fn_qaBoard() 실패");
               }
           })
        }
    </script>



</body>
</html>