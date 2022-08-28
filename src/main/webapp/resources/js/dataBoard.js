import {listToHTag, hTagToList}  from "./common/leftSideBar.js";

let tagList = hTagToList(document.querySelector(".subject"));
var leftSideBar = document.querySelector("#leftSideBar");

var title = document.querySelector(".title").getInnerHTML().trim()
leftSideBar.innerHTML +='<li style = "cursor: pointer; padding-left: 10px; padding-bottom: 5px">' + title +' </a></li>';

listToHTag(leftSideBar,tagList);