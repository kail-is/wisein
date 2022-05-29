<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <head>
        <link rel="stylesheet" href="resources/css/totalSearch.css">
    </head>
<body>

    <header>
        <ul>
            <li class="logo"><a href="#">wiseIN</a></li>
            <li class="search-wrap">
                <div class="select-wrap">
                    <select name="" id="search-list">
                        <option value="front">Front</option>
                    </select>
                </div>
                <input type="text" class="search-bar" placeholder="Type something…" required="required">
            </li>
            <li class="bar">
                <label for="bar-chk">
                    <span class="material-icons">
                        menu
                    </span>
                </label>
                
            </li>
        </ul>
    </header>

    <div class="sidebar-wrap">
        <input type="checkbox" name="" id="bar-chk" style="display: none;">
        <div class="sidebar">
            <div class="top">
                <div class="info">
                    <img src="../image/test1.png" alt=""> 
                    <label for="my-function-chk">서은빈(직위)<br>OK 저축은행</label>
                    <input type="checkbox" name="" id="my-function-chk" style="display: none;">
                    <ul class="person-function">
                        <li><a href="#">정보 수정</a></li>
                        <li><a href="#">질문 모아 보기</a></li>
                        <li><a href="#">답변 모아 보기</a></li>
                    </ul>
                </div>
                <ul class="menu">
                    <li><a href="#">질문하고 싶어요</a></li>
                    <li><a href="#">Tech Tip</a></li>
                    <li><a href="#">사이트 별 맛집 목록</a></li>
                    <li><a href="#">현재 파견 리스트</a></li>
                </ul>
                <div class="search-bar">
                    <span class="material-icons">
                        search
                    </span>
                    <input type="text">
                </div>
            </div>
            <div class="bottom">
                <ul class="icon">
                    <li>
                        <span class="material-icons">
                            perm_identity
                        </span>
                        
                    </li>
                    <li>
                        <span class="material-icons">
                            link
                        </span>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="content-wrap">
        <section class="content-frame">
            <div class="title bline">통합검색</div>
            <ul>
                <li class="mgt16">
                    <div class="title">먹방</div>
                    <div class="totalSearch-table">
                        <ul class="tr">
                            <li class="th">번호</li>
                            <li class="th">제목</li>
                            <li class="th">작성자</li>
                            <li class="th">날짜</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td search-subject">안녕하세요asdasdadasd</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                    </div>
                </li>
                <li class="mgt16">
                    <div class="title">달리기</div>
                    <div class="totalSearch-table">
                        <ul class="tr">
                            <li class="th">번호</li>
                            <li class="th">제목</li>
                            <li class="th">작성자</li>
                            <li class="th">날짜</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                        <ul class="tr">
                            <li class="td">1</li>
                            <li class="td">안녕하세요</li>
                            <li class="td">칼퇴근</li>
                            <li class="td">2021-10-20</li>
                        </ul>
                    </div>
                </li>
            </ul>
            
        </section>
    </div>

    <div class="info-wrap">

        <ul id="leftSideBar" class="info">
        </ul>

    </div>

    
    <script src="../js/dataBoard.js" type="module"></script>
    
    <script>

    function $dim(){

        let element   = document.getElementsByTagName('body')[0];
        let isDimming = true;

        for(let i = 0; i<arguments.length;i++){
            switch (typeof(arguments[i])) {
                case 'object':
                    if(arguments[i].nodeType !== 1){
                        element = arguments[i];
                    }
                    break;
                case 'boolean':  
                    isDimming = arguments[i];
                    break;
                default:
                    break;
            }
        }
        
        if(isDimming){
            element.className += 'dim';
        }else{
            element.classList.remove('dim');
        }
    }

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

    
    //$dim(true);
    //$dim(true);
    // setTimeout(() => {
    //     $dim(false);
    // }, 3000);
       
    </script>

    
</body>