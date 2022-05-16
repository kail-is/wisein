<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <link rel="stylesheet" href="resources/css/main.css">
</head>

    <div class="content-wrap">
        <section class="questions content-frame">

            <div class="title">
                이런내용 궁금해요
            </div>

            <div class="writer-wrap">
                <p class="writer side_mapping_1">서은빈 (OK저축은행)</p>
                <ul class="person-function side_mapping_1">
                    <li class=" side_mapping_2"><a href="#">메일 전송</a></li>
                    <li class=" side_mapping_2"><a href="#">질문 모아 보기</a></li>
                    <li class=" side_mapping_2"><a href="#">답변 모아 보기</a></li>
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

        <ul class="answer-wrap">

            <li class="answer content-frame">

                <div class="title">
                    답변을 드립니다.
                </div>

                <div class="function">
                    <a href="#">
                        <span class="material-icons">
                            thumb_up
                        </span>
                    </a>
                    <a href="#">
                        <span class="material-icons purple">
                            turned_in_not
                        </span>
                    </a>
                </div>
                <div class="writer-wrap">
                    <p class="writer">김경민 (OK저축은행)</p>
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
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Omnis cumque aut in eum officiis exercitationem commodi modi quos esse, laborum dicta nobis, itaque minima amet, aperiam tenetur? Suscipit, rem dolorum.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Similique illo numquam veritatis voluptas fugiat perspiciatis corporis assumenda commodi facilis maxime maiores nam saepe at, distinctio vitae praesentium laborum deleniti eius.
                </div>

            </li>

        </ul>
    </div>
<div class="popup-wrap page-center">
    <div class="signUp-popup-wrap">
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">ID</label>
            <input type="text" class="signUp-input pr30p">
            <div class="email-info">@ wiselab.co.kr</div>
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">이름</label>
            <input type="text" class="signUp-input">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">파견 사이트</label>
            <input type="text" class="signUp-input">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PASSWORD</label>
            <input type="text" class="signUp-input">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PWCHECK</label>
            <input type="text" class="signUp-input">
        </div>
        <div class="signUp-pwassword-info-wrap">
            <p class="porintColor">패스워드가 일치합니다</p>
            <p class="red">패스워드가 일치하지 않습니다</p>
        </div>
        <div class="signUp-confirm-Button-wrap">
            <button type="button">메일 인증하고 가입하기</button>
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


</body>
</html>