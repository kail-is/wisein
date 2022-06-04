import {listToTag,tagToList}  from "./common/leftSideBar.js";

let arr123 = tagToList(document.getElementsByClassName('side_mapping_1'));
var leftSideBar = document.getElementById("leftSideBar");
listToTag(leftSideBar,arr123);