import {listToHTag, hTagToList}  from "./common/leftSideBar.js";


let tagList = hTagToList(document.querySelector(".subject"));

var leftSideBar = document.querySelector("#leftSideBar");

var pathName = window.location.pathname
if(pathName !== "/questionsList" && pathName !== "/commentList"){
    var title = document.querySelector(".title").getInnerHTML().trim()
    leftSideBar.innerHTML +='<li style = "cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a>' + title +' </a></li>';
    listToHTag(leftSideBar,tagList);
}

var classVar = pathName.substring(1, pathName.length);

if(pathName === "/questionsList" || pathName === "/commentList") {
    leftSideBar.innerHTML +='<li class="questionsList" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=/questionsList>Qa질문모아보기</a></li>';
    leftSideBar.innerHTML +='<li class="" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=#>Tip질문모아보기</a></li>';
    leftSideBar.innerHTML +='<li class="commentList" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=/commentList?sideCheck=Y>Qa답글모아보기</a></li>';
    leftSideBar.innerHTML +='<li class="" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=#>Tip답글모아보기</a></li>';

    const temp = document.querySelector(".questionsList");
    temp.style="color: #949494; margin: 1em 0; cursor: pointer; padding-left: 10px; padding-bottom: 5px; font-weight: 300;";
//    console.log(temp);

    const text = document.querySelector("."+classVar);
    text.style="color: #7102a5; font-weight: 600;";
//    console.log(text);
}











