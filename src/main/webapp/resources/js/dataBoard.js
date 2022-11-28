import {listToHTag, hTagToList}  from "./common/leftSideBar.js";


let tagList = hTagToList(document.querySelector(".subject"));

var leftSideBar = document.querySelector("#leftSideBar");

var pathName = window.location.pathname
//console.log(pathName);
if(pathName !== "/questionsList" && pathName !== "/commentList" && pathName !== "/gatherMemTip"){
    var title = document.querySelector(".title").getInnerHTML().trim()

//console.log(title);
    leftSideBar.innerHTML +='<li style = "cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a>' + title +' </a></li>';
    listToHTag(leftSideBar,tagList);
}

var classVar = pathName.substring(1, pathName.length);

if(pathName === "/questionsList" || pathName === "/commentList" || pathName === "/gatherMemTip") {
    leftSideBar.innerHTML +='<li class="questionsList" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=/questionsList>Qa질문 모아보기</a></li>';
    leftSideBar.innerHTML +='<li class="commentList" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=/commentList?sideCheck=Y>Qa답글 모아보기</a></li>';
    leftSideBar.innerHTML +='<li class="gatherMemTip" style="cursor: pointer; padding-left: 10px; padding-bottom: 5px"><a href=/gatherMemTip?sideCheck=Y>Tip게시글 모아보기</a></li>';

    const temp = document.querySelector(".questionsList");
    temp.style="color: #949494; margin: 1em 0; cursor: pointer; padding-left: 10px; padding-bottom: 5px; font-weight: 300;";
//    console.log(temp);

    const text = document.querySelector("."+classVar);
    text.style="color: #7102a5; font-weight: 600;";
//    console.log(text);
}











