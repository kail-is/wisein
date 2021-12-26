<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/dataBoard.css">
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
            이런내용 궁금해요
        </div>

        <div class="writer-wrap">
            <p class="writer">서은빈 (OK저축은행)</p>
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
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptatum adipisci ratione nulla mollitia accusamus repellat fugiat eius excepturi odit omnis dolore similique, aspernatur reiciendis quia et nostrum numquam expedita temporibus!
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, ipsum. Quisquam, maiores praesentium tempora officia distinctio, delectus necessitatibus voluptas esse vero quasi voluptates ipsum id laudantium saepe odio hic eius?
            <img src="../image/test2.png" alt="" style="width: 100%;">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Omnis cumque aut in eum officiis exercitationem commodi modi quos esse, laborum dicta nobis, itaque minima amet, aperiam tenetur? Suscipit, rem dolorum.
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Similique illo numquam veritatis voluptas fugiat perspiciatis corporis assumenda commodi facilis maxime maiores nam saepe at, distinctio vitae praesentium laborum deleniti eius.
        </div>

    </section>


    <section class="recommend-wrap">
        <div class="recommend-titleLine">
            댓글 (1)
        </div>
        <ul class="recommend">
            <li>
                <div class="wrap">
                    <div class="recommend-info-wrap">
                        <div class="img"></div>
                        <div class="info">
                            <div class="title">서은빈(OK저축은행)</div>
                            <div class="sub">
                                <div class="date">
                                    2021-10-22
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
                    <div class="content">유익한 글 정말 잘 읽었습니다!</div>
                </div>
            </li>
        </ul>

        <div class="recommend-write-wrap recommend">
            <div class="wrap">
                <div class="recommend-info-wrap">
                    <div class="img"></div>
                    <div class="info" id="writer-info">
                        <div class="title">서은빈(OK저축은행)</div>
                    </div>
                </div>
                <div class="content-writer-wrap">
                    <textarea>유익한 글 정말 잘 읽었습니다!</textarea>
                </div>
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