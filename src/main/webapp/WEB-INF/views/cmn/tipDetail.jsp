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
        <!--좋아요북마크-->
        <div class="icon" style="float:right;">
            <span class="material-icons" id="like_btn" onclick="like(${tipBoardDTO.num})" style="color:gray;" >thumb_up</span>
            <span class="material-icons" id="bookmark_btn" onclick="bookmark(${tipBoardDTO.num})" style="color:gray;" >bookmarks</span>
        </div>

        <!--제목-->
        <div class="title">
            <c:out value="${tipBoardDTO.subject}"/>
        </div>


        <!--작성자-->
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

        <!-- 게시글 수정삭제 -->
        <div class="icon" align="right">
            <span class="material-icons" onClick="updTip()">border_color</span>
            <span class="material-icons" onClick="delTip()">delete</span>
        </div>

    <!-- 댓글 -->
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
                                    <!-- 댓글 수정삭제 -->
                                    <c:if test="${commentList.writer == 'hannah94'}">
                                        <div class="icon">
                                            <span class="material-icons" onClick="openModi(${commentList.num}, '${commentList.content}')">border_color</span>
                                            <span class="material-icons" onClick="delComm(${commentList.num})">delete</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!--댓글보기-->
                        <div class="content" id="comm${commentList.num}" name="comm${commentList.num}">
                            <c:out value="${commentList.content}"/>
                        </div>
                        <!--댓글수정-->
                        <div class="content-mod" id="modComm${commentList.num}" name="modComm${commentList.num}" style='display: none;'>
                            <textarea class="comment_inbox_text" id="modComm_content${commentList.num}" name="modComm_content${commentList.num}" placeholder="댓글을 남겨보세요" rows="1" style="overflow: hidden; overflow-wrap: break-word; height: 17px;"></textarea>
                        </div>
                         <div class="comment_attach" id="mod_comment_attach" style='display: none;'>
                            <div class="register_box">
                                <a href="#" role="button" class="button btn_register" onClick="modComm(${commentList.num})">수정</a>
                                <a href="#" role="button" class="button btn_register" onClick="modCancel(${commentList.num})">취소</a>
                            </div>
                         </div>
                    </div>
                </li>
            </ul>
        </c:forEach>
    </section>
    <!--댓글입력-->
    <div class="CommentWriter">
        <div class="comment_inbox">
            <strong class="blind">댓글을 입력하세요</strong>
            <em><div class="comment_inbox_name" id="comment_writer" name="comment_writer">hannah94</div></em>
            <textarea class="comment_inbox_text" id="comment_content" name="comment_content" placeholder="댓글을 남겨보세요" rows="1" style="overflow: hidden; overflow-wrap: break-word; height: 17px;"></textarea>
        </div>

        <div class="comment_attach">
            <div class="register_box">
                <a href="#" role="button" class="button btn_register" onClick="regComm()">등록</a>
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
                alert("댓글을 입력하세요✍");
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
             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
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
          }

          var isMod = false;

          function openModi(num, content){
            if(isMod==false){
                document.getElementById('comm'+num).style.display = 'none';
                document.getElementById('modComm'+num).style.display = 'block';
                document.getElementById('mod_comment_attach').style.display = 'block';
                document.getElementById('modComm_content'+num).value = content;
                isMod = true;
            }else{
                alert("이미 수정중인 댓글이 있어용🤔")
            }
          }

          function modCancel(num){
            isMod = false;
            document.getElementById('modComm'+num).style.display = 'none';
            document.getElementById('mod_comment_attach').style.display = 'none';
            document.getElementById('comm'+num).style.display = 'block';
          }

          function modComm(num){
              var num = num;
              var content = document.getElementById('modComm_content'+num).value;

              if(content.length==0){
                  alert("수정할 댓글 내용을 입력하세요✍");
                  document.getElementById('modComm_content'+num).focus();
                  return;
              }

              $.ajax({
                 data:{
                     "num" : num
                     ,"content": content
                 },
                 type:"POST",
                 url:"/udpTipComm",
                 success:function(data) {
                     window.location.href = "/tipDetail?num=${tipBoardDTO.num}"
                 },
                 error:function(request, status, error) {
                     alert("댓글 수정 실패😢");
                 }
              })
          }

          function like(num){
             var brdRef = "tip||" + ${tipBoardDTO.num};
             var writer = 'hannah94'
              if(writer != ""){
                   $.ajax({
                       data:{
                           "brdRef" : brdRef,
                           "writer" : writer
                       },
                       type:"GET",
                       url:"/likeTip",
                       success:function(data) {
                           window.location.href = "/tipDetail?num=${tipBoardDTO.num}"
                       },
                       error:function(request, status, error) {
                           alert("좋아요 실패😢");
                       }
                   })
              } else if(writer == ""){
                   alert("로그인 후 이용가능합니다.");
                   window.location.href="/login";
              }
          }

          function bookmark(num){
          }

   </script>

</body>
</html>