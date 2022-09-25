<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<head>
    <link rel="stylesheet" href="resources/css/tipDetail.css">
</head>
<div class="content-wrap">
    <section class="questions content-frame">
        <div class="icon" style="float:right;">
            <span id="changeLikeHtml">
                <!--좋아요-->
                <c:if test="${likeDelYn == 'none'}">
                    <span class="material-icons" id="like_btn" onclick="regLike(${tipBoardDTO.num})" style="color:gray;" >thumb_up</span>
                </c:if>
                <c:if test="${likeDelYn == 'Y'}">
                    <span class="material-icons" id="like_btn" onclick="udpLike(${tipBoardDTO.num})" style="color:gray;" >thumb_up</span>
                </c:if>
                <c:if test="${likeDelYn == 'N'}">
                    <span class="material-icons" id="like_btn" onclick="udpLike(${tipBoardDTO.num})" style="color:purple;" >thumb_up</span>
                </c:if>
            </span>

            <!--북마크-->
            <span id="changeScrapHtml">
                <c:if test="${scrapDelYn == 'none'}">
                    <span class="material-icons" id="bookmark_btn" onclick="regScrap(${tipBoardDTO.num})" style="color:gray;" >bookmarks</span>
                </c:if>
                <c:if test="${scrapDelYn == 'Y'}">
                    <span class="material-icons" id="bookmark_btn" onclick="udpScrap(${tipBoardDTO.num})" style="color:gray;" >bookmarks</span>
                </c:if>
                <c:if test="${scrapDelYn == 'N'}">
                    <span class="material-icons" id="bookmark_btn" onclick="udpScrap(${tipBoardDTO.num})" style="color:purple;" >bookmarks</span>
                </c:if>
            </span>
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

        <!-- 게시글 수정삭제 아이콘-->
        <c:set var="memberId" value="${memberId}" />
        <c:if test="${tipBoardDTO.writer == memberId}">
            <div class="icon" align="right">
                <span class="material-icons" onClick="updTip()">border_color</span>
                <span class="material-icons" onClick="delTip()">delete</span>
            </div>
        </c:if>

    <!-- 댓글 -->
    <section class="recommend-wrap" id="changeCommHtml">
        <div class="recommend-titleLine">
            댓글 (${commentNum})
        </div>
        <c:forEach var="commentList" items="${commentList}">
            <ul class="recommend">
                <li>
                    <div class="wrap">
                        <div class="recommend-info-wrap">
                            <!-- 댓글 작성자 img -->
                            <c:forEach var="item" items="${commWritersIMG}">
                                <c:choose>
                                    <c:when test = "${commentList.writer == item.key && item.value != null}">
                                        <img src ="../${item.value}">
                                    </c:when>
                                    <c:when test = "${commentList.writer == item.key && item.value == null}">
                                        <img src ="../resources/image/Conic.png">
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <div class="info">
                                <!-- 댓글 작성자 -->
                                <div class="title"><c:out value="${commentList.writer}"/></div>
                                <div class="sub">
                                    <!-- 댓글 날짜 -->
                                    <div class="date">
                                        <fmt:formatDate value="${commentList.regDate}" pattern="yyyy-MM-dd"/>
                                    </div>
                                    <!-- 댓글 수정삭제 아이콘 -->
                                    <c:if test="${commentList.writer == memberId}">
                                        <div class="icon">
                                            <span class="material-icons" onClick="openModi(${commentList.num}, '${commentList.content}')">border_color</span>
                                            <span class="material-icons" onClick="delComm(${commentList.num})">delete</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!--댓글 내용-->
                        <div class="content" id="comm${commentList.num}" name="comm${commentList.num}">
                            <c:out value="${commentList.content}"/>
                        </div>
                        <!--댓글 수정-->
                        <div class="content-mod" id="modComm${commentList.num}" name="modComm${commentList.num}" style='display: none;'>
                            <textarea class="comment_inbox_text" id="modComm_content${commentList.num}" name="modComm_content${commentList.num}" placeholder="댓글을 남겨보세요" rows="1"></textarea>
                        </div>
                         <div class="comment_attach" id="mod_comment_attach${commentList.num}" style='display: none;'>
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
    <!--댓글 입력-->
    <div class="CommentWriter">
        <div class="comment_inbox">
            <strong class="blind">댓글을 입력하세요</strong>
            <em><div class="comment_inbox_name" id="comment_writer" name="comment_writer">${memberId}</div></em>
            <textarea class="comment_inbox_text" id="comment_content" name="comment_content" placeholder="댓글을 남겨보세요" rows="1"></textarea>
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
        let boardType = "tip";
        let boardIdx = ${tipBoardDTO.num};

         function delTip(){
             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
                 window.location.href="/delTip?num=${tipBoardDTO.num}"
             }
         }

         function updTip(){
              window.location.href="/updTip?num=${tipBoardDTO.num}"
         }

         function changeCommHtml(newCommList){
            let id = '${memberId}';
            let newCommArr = newCommList.commentList
            let commWritersIMG = newCommList.commWritersIMG
            let commWriters = Object.keys(commWritersIMG)
            let html = "<div class='recommend-titleLine'> 댓글 ("+newCommArr.length+")</div>"
            for(let i=0; i < newCommArr.length; i++){
                html += "<ul class='recommend'><li>"
                html += "<div class='wrap'><div class='recommend-info-wrap'>"

                for(let j=0; j <commWriters.length; j++){
                    if(newCommArr[i].writer == commWriters[j]){
                        if(commWritersIMG[commWriters[j]] == null){
                            html += "<img src ='../resources/image/Conic.png'>"
                        }else{
                            html += "<img src ='../"+commWritersIMG[commWriters[j]]+"'>"
                        }
                    }
                }

                html += "<div class='info'><div class='title'>"+newCommArr[i].writer+"</div>"
                html += "<div class='sub'><div class='date'>"+(newCommArr[i].regDate).slice(0,10)+"</div>"
                if(newCommArr[i].writer == id){
                    html += "<div class='icon'>"
                    html += "<span class='material-icons' onClick='openModi("+newCommArr[i].num+",&apos;"+newCommArr[i].content+"&apos;)'>border_color</span>"
                    html += "<span class='material-icons' onClick='delComm("+newCommArr[i].num+")'>delete</span></div>"
                }
                html += "</div></div></div>"
                html += "<div class='content' id='comm"+newCommArr[i].num+"' name='comm"+newCommArr[i].num+"'>"+newCommArr[i].content+"</div>"
                html += " <div class='content-mod' id='modComm"+newCommArr[i].num+"' name='modComm"+newCommArr[i].num+"' style='display: none;'>"
                html += "<textarea class='comment_inbox_text' id='modComm_content"+newCommArr[i].num+"' name='modComm_content"+newCommArr[i].num+"' placeholder='댓글을 남겨보세요' rows='1' style='overflow: hidden; overflow-wrap: break-word; height: 17px;'></textarea>"
                html += "</div> <div class='comment_attach' id='mod_comment_attach"+newCommArr[i].num+"' style='display: none;'>"
                html += "<div class='register_box'>"
                html += "<a href='#' role='button' class='button btn_register' onClick='modComm("+newCommArr[i].num+")'>수정</a>"
                html += "<a href='#' role='button' class='button btn_register' onClick='modCancel("+newCommArr[i].num+")'>취소</a>"
                html += "</div></div></div></li></ul>"
            }
                html += "</section>";
                let parent = document.getElementById('changeCommHtml');
                parent.innerHTML = "";
                parent.innerHTML = html;
         }

         function initCommText(){
            document.getElementById("comment_content").value = '';
         }

         function regComm(){
            let content = document.getElementById('comment_content').value
            let data = {boardType: boardType, boardIdx: boardIdx, content: content}

            if(content.length==0){
                alert("댓글을 입력하세요👀");
                document.getElementById('comment_content').focus();
                return;
            }
            let check;
            fetch('/regTipComm',{
                method: 'POST',
                cache : 'no-cache',
                headers: {"Content-Type": "application/json"},
                body:JSON.stringify(data)
            })
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(json =>  {
                changeCommHtml(json);
                initCommText();
            })
         }

          function delComm(num){
             let data = {num: num, boardType: boardType, boardIdx: boardIdx};

             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
                 fetch('/delTipComm',{
                     method: 'POST',
                     cache : 'no-cache',
                     headers: {"Content-Type": "application/json"},
                     body:JSON.stringify(data)
                 })
                 .then(response => response.json())
                 .catch(error => console.error('Error:', error))
                 .then(json =>  {
                    changeCommHtml(json);
                 })
             }
          }

          var isMod = false;

          function openModi(num, content){
            if(isMod==false){
                document.getElementById('comm'+num).style.display = 'none';
                document.getElementById('modComm'+num).style.display = 'block';
                document.getElementById('mod_comment_attach'+num).style.display = 'block';
                document.getElementById('modComm_content'+num).value = content;
                isMod = true;
            }else{
                alert("이미 수정중인 댓글이 있어용🤔")
            }
          }

          function modCancel(num){
            isMod = false;
            document.getElementById('modComm'+num).style.display = 'none';
            document.getElementById('mod_comment_attach'+num).style.display = 'none';
            document.getElementById('comm'+num).style.display = 'block';
          }

          function modComm(num){
              let content = document.getElementById('modComm_content'+num).value;
              let data = {num: num, boardType: boardType, boardIdx: boardIdx, content: content};

              if(content.length==0){
                  alert("수정할 댓글 내용을 입력하세요✍");
                  document.getElementById('modComm_content'+num).focus();
                  return;
              }

              fetch('/udpTipComm',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                 changeCommHtml(json);
                 modCancel(data.num);
              })
          }

          function changeLikeHtml(json){
              let html;
              let likeDelYn = json.likeDelYn;
              let num = ${tipBoardDTO.num};

              if(likeDelYn=='N'){
                html = "<span class='material-icons' id='like_btn' onclick='udpLike("+num+")' style='color:purple;'>thumb_up</span>"
              }else{
                html = "<span class='material-icons' id='like_btn' onclick='udpLike("+num+")' style='color:gray;'>thumb_up</span>"
              }

              let parent = document.getElementById('changeLikeHtml');
              parent.innerHTML='';
              parent.innerHTML= html;
          }

          function regLike(num){
              let data = {num: num, boardType: boardType, boardIdx: boardIdx};

              fetch('/regLikeTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                 changeLikeHtml(json);
              })
          }

          function udpLike(num){
              let data = {num: num, boardType: boardType, boardIdx: boardIdx};

              fetch('/udpLikeTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                changeLikeHtml(json);
             })
          }

          function changeScrapHtml(json){
              let html;
              let scrapDelYn = json.scrapDelYn;
              let num = ${tipBoardDTO.num};

              if(scrapDelYn=='N'){
                html = "<span class='material-icons' id='like_btn' onclick='udpScrap("+num+")' style='color:purple;'>bookmarks</span>"
              }else{
                html = "<span class='material-icons' id='like_btn' onclick='udpScrap("+num+")' style='color:gray;'>bookmarks</span>"
              }

              let parent = document.getElementById('changeScrapHtml');
              parent.innerHTML='';
              parent.innerHTML= html;
          }

          function regScrap(num){
              let data = {num: num, boardType: boardType, boardIdx: boardIdx};

              fetch('/regScrapTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                  changeScrapHtml(json);
              })
          }

          function udpScrap(num){
              let data = {num: num, boardType: boardType, boardIdx: boardIdx};

              fetch('/udpScrapTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                  changeScrapHtml(json);
              })
          }

   </script>

</body>
</html>