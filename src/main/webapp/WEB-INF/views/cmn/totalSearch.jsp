<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String url =  request.getServerName().toString(); %>
    <head>
        <link rel="stylesheet" href="${url}/resources/css/totalSearch.css">
    </head>
    <div class="content-wrap">
        <section class="content-frame">
            <div class="title bline">통합검색</div>
            <ul id="integration-data">
            </ul>
        </section>
        <p id="slide-bottom-arrow" style="text-align: center;font-size: 1rem;margin-bottom: 3rem">아래로↓</p>
    </div>
    
    <script>
        let callAjaxIntegrationData = (addr = location.pathname) => {
            let currentPageNo = 0;
            let boardTypeList = [];
            let isCall        = true;
            let integrationData = document.getElementById("integration-data");
            let slideBottomArrow = document.getElementById("slide-bottom-arrow");
            const url = new URL(location.href);
            const urlParams = url.searchParams;
            return function(){
                currentPageNo += isCall ? 1 : 0;
                if(isCall){
                    isCall = false;
                    //XMLHttpRequest 객체 생성
                    let xhr = new XMLHttpRequest();
                    if(urlParams.has('currentPageNo')){
                        urlParams.set('currentPageNo',currentPageNo);
                    }else{
                        urlParams.append('currentPageNo',currentPageNo);
                    }
                    let callAddr = addr + '-data' +'?'+ urlParams;
                    //요청을 보낼 방식, 주소, 비동기여부 설정
                    xhr.open('GET', callAddr, true);
                    //요청 전송
                    $dim(true,{isLoading : true});
                    //통신후 작업
                    setTimeout(()=>{//화면 딜레이로 의미없는 스크롤시 줄이는 --필요없으면 삭제 해도됨.
                        xhr.send();
                        xhr.onload = () => {
                            //통신 성공
                            if (xhr.status === 200) {
                                let result = JSON.parse(xhr.response);
                                let innerHtml = currentPageNo === 1 ? '' : integrationData.innerHTML;
                                for (let key of Object.keys(result)) {
                                    if (result[key].length < ${viewRecordsPerPage}){
                                        slideBottomArrow.style.display = 'none';
                                        isCall = false;
                                    }else{
                                        slideBottomArrow.style.display = 'block';
                                        isCall = true;
                                    }
                                    innerHtml += `<li>`;
                                    let boardType = result[key][0].boardType;
                                    if (boardTypeList.indexOf(boardType) === -1) {
                                        innerHtml += `<div class="title mgt16">` + boardType + `</div>`;
                                    }
                                    innerHtml += `<div class="totalSearch-table">`;
                                    if (boardTypeList.indexOf(boardType) === -1) {
                                        innerHtml += `<ul class="tr">`;
                                        innerHtml += `<li class="th">번호</li>`;
                                        innerHtml += `<li class="th">제목</li>`;
                                        innerHtml += `<li class="th">작성자</li>`;
                                        innerHtml += `<li class="th">날짜</li>`;
                                        innerHtml += `</ul>`;
                                        boardTypeList.push(boardType);
                                    }
                                    for (let item of result[key]) {
                                        innerHtml += `<ul class="tr">`;
                                        innerHtml += `<li class="td">` + item.num+ `</li>`;
                                        innerHtml += `<li class="td search-subject">` + item.subject + `</li>`;
                                        innerHtml += `<li class="td">` + item.writer + `</li>`;
                                        innerHtml += `<li class="td">` + item.regDate + `</li>`;
                                        innerHtml += `</ul>`;
                                    }
                                    innerHtml += `</div>`;
                                    innerHtml += `</li>`;
                                }
                                integrationData.innerHTML = innerHtml;
                            } else {
                                //통신 실패
                                console.log("통신 실패");
                                $dim(false);
                            }
                            $dim(false);
                        }
                    },500)
                }
            };
        }
        let callFun = callAjaxIntegrationData();
        callFun();
        window.addEventListener('wheel',()=>{
            let scrollY =  window.innerHeight + window.scrollY;//브라우저 안쪽 길이 + 스크롤의 현재 위치
            if(scrollY > document.body.offsetHeight){//body태그 문서 길이
                callFun();
            }else if(window.innerHeight >= document.body.offsetHeight){//초기 데이터양이 적어 스크롤이 생기지 않으면
                callFun();
            }
        });
        let writer = document.getElementsByClassName("writer");
        Array.from(writer).forEach(function(element) {
            element.addEventListener('click', function(e) {
                if(e.target.nextElementSibling.style.display === 'block'){
                    e.target.nextElementSibling.style.display = 'none';
                }else{
                    e.target.nextElementSibling.style.display = 'block';
                }
            });
        });
        window.onbeforeunload = function() {
            callFun = null;
        };
    </script>