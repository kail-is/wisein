<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

        <div class="title">
            <c:out value="${qaListDTO.subject}" />
        </div>

        <div class="writer-wrap">
            <p class="writer"><c:out value="${qaListDTO.writer}" /></p>
            <ul class="person-function">
                <li><a href="#">메일 전송</a></li>
                <li><a href="#">질문 모아 보기</a></li>
                <li><a href="#">답변 모아 보기</a></li>
            </ul>
            <span class="material-icons purple">
                    videocam
                </span>
        </div>

        <div class="subject">
            <!--<c:out value="${qaListDTO.content}" />-->
            ${content}
        </div>

    </section>

        <div class="icon" align="right">
            <span class="material-icons" id="update_btn">
                border_color
            </span>
            <span class="material-icons" id="delete_btn">
                delete
            </span>
        </div>


    <section class="recommend-wrap">
        <div class="recommend-titleLine">
            댓글 (1)
        </div>
        <!-- 댓글 목록 조회 -->
        <c:forEach var="commentQa" items="${commentQaList}">
            <ul class="recommend">
                <li>
                    <div class="wrap">
                        <div class="recommend-info-wrap">
                            <div class="img"></div>
                            <div class="info">
                                <div class="title"><c:out value="${commentQa.writer}" /></div>
                                <div class="sub">
                                    <div class="date">
                                        <c:out value="${commentQa.regDate}" />
                                    </div>
                                    <div class="icon">
                                            <span class="material-icons">
                                                border_color
                                            </span>
                                        <span class="material-icons">
                                                delete
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="content"><c:out value="${commentQa.content}" /></div>
                    </div>
                </li>
            </ul>
        </c:forEach>
    </section>

    <div data-v-3b426d7d="" class="CommentWriter">
        <div data-v-3b426d7d="" class="comment_inbox">
            <strong data-v-3b426d7d="" class="blind">댓글을 입력하세요</strong>
            <em id="comment_writer" name="comment_writer" data-v-3b426d7d="" class="comment_inbox_name" >testvsl</em>
            <textarea id="comment_content" name="comment_content" data-v-3b426d7d="" placeholder="댓글을 남겨보세요" rows="1" class="comment_inbox_text" style="overflow: hidden; overflow-wrap: break-word; height: 17px;"></textarea>
        </div>
        <div data-v-3b426d7d="" class="comment_attach">

            <div data-v-3b426d7d="" class="register_box">
                <a data-v-3b426d7d="" href="#" role="button" class="button btn_register" id="comment_btn" name="comment_btn">등록</a>
            </div>
        </div>
    </div>




</div>

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
            var parentNum = '<c:out value="${qaListDTO.num}" />';
            var category = '<c:out value="${qaListDTO.category}" />';
            <!-- var writer = document.getElementById('comment_writer').value; -->
            var writer = "testvsl";
            var subject = "";
            var content = document.getElementById('comment_content').value;

            $.ajax({
                data:{
                    "parentNum":parentNum
                    ,"category":category
                    ,"writer":writer
                    ,"subject":subject
                    ,"content":content
                },
                type:"POST",
                url:"/qaRegComment",
                success:function(data) {
                    window.location.href = "/qaDetail?num=${qaListDTO.num}"
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })

        });
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
       function submit(){


       }
    </script>



</body>
</html>