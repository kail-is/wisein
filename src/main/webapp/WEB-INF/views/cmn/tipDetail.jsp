<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<head>
    <link rel="stylesheet" href="resources/css/tipDetail.css">
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
        <div class="title">
            <c:out value="${tipBoardDTO.subject}"/>
        </div>

        <div class="writer-wrap">
            <p class="writer"><c:out value="${tipBoardDTO.writer}" /></p>
            <ul class="person-function">
                <li><a href="#">메일 전송</a></li>
                <li><a href="#">질문 모아 보기</a></li>
                <li><a href="#">답변 모아 보기</a></li>
            </ul>
            <span class="material-icons purple">videocam</span>
        </div>

        <div class="subject">
            ${content}
        </div>
    </section>

        <div class="icon" align="right">
            <span class="material-icons" onClick="updTip()">border_color</span>
            <span class="material-icons" onClick="delTip()">delete</span>
        </div>


    <section class="recommend-wrap">
        <div class="recommend-titleLine">
            댓글 (${commentNum})
        </div>
        <c:forEach var="commentList" items="${commentList}">
            <ul class="recommend">
                <li>
                    <div class="wrap">
                        <div class="recommend-info-wrap">
                            <div class="img"></div>
                            <div class="info">
                                <div class="title"><c:out value="${commentList.writer}"/></div>
                                <div class="sub">
                                    <div class="date">
                                        <fmt:formatDate value="${commentList.regDate}" pattern="yyyy-MM-dd"/>
                                    </div>
                                    <div class="icon">
                                        <span class="material-icons">border_color </span>
                                        <span class="material-icons" onClick="delComm(${commentList.num})" >delete </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="content">
                            <c:out value="${commentList.content}"/>
                        </div>
                    </div>
                </li>
            </ul>
        </c:forEach>
    </section>

    <div data-v-3b426d7d="" class="CommentWriter">
        <div data-v-3b426d7d="" class="comment_inbox">
            <strong data-v-3b426d7d="" class="blind">댓글을 입력하세요</strong>
            <em><div data-v-3b426d7d="" id="comment_writer" name="comment_writer" class="comment_inbox_name">hannah94</div></em>
            <textarea data-v-3b426d7d="" id="comment_content" name="comment_content" placeholder="댓글을 남겨보세요" rows="1" class="comment_inbox_text" style="overflow: hidden; overflow-wrap: break-word; height: 17px;"></textarea>
        </div>

        <div data-v-3b426d7d="" class="comment_attach">
            <div data-v-3b426d7d="" class="register_box">
                <a data-v-3b426d7d="" href="#" role="button" class="button btn_register" onClick="regComm()">등록</a>
            </div>
        </div>
    </div>
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

   <script>
         function delTip(){
             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
                 window.location.href="/delTip?num=${tipBoardDTO.num}"
             }
         }

         function updTip(){
              window.location.href="/updTip?num=${tipBoardDTO.num}"
         }

         function regComm(){
            var writer = 'hannah94'
            var commContent = document.getElementById('comment_content').value
            var brdRef = "tip||"+${tipBoardDTO.num};

            if(commContent.length==0){
                alert("댓글을 입력하세요👀");
                document.getElementById('comment_content').focus();
                return;
            }

            $.ajax({
               data:{
                   "writer": writer
                   ,"content": commContent
                   ,"brdRef" : brdRef
               },
               type:"POST",
               url:"/regTipComm",
               success:function(data) {
                   window.location.href = "/tipDetail?num=${tipBoardDTO.num}"
               },
               error:function(request, status, error) {
                   alert("댓글 등록 실패😢");
               }
            })
         }

          function delComm(num){
             var num = num;
             var brdRef = "tip||" + ${tipBoardDTO.num};

             $.ajax({
                data:{
                    "num": num
                    ,"brdRef": brdRef
                 },
                type:"POST",
                url:"/delTipComm",
                success:function(data) {
                    window.location.href = "/tipDetail?num=${tipBoardDTO.num}"
                },
                error:function(request, status, error) {
                    alert("댓글 삭제 실패😢");
                }
             })
          }

   </script>

</body>
</html>