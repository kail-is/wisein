<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
    <link rel="stylesheet" href="resources/css/qaDetail.css">
    <script src="/resources/js/qaDetail.js"></script>
</head>

<c:set var="num" value="${qaListDTO.num}" />
<c:set var="category" value="${qaListDTO.category}" />
<c:set var="writer" value="${member.id}" />

<body>
<div class="content-wrap">
    <section class="questions content-frame">
        <div class="info">
            <div class="title">
                <c:out value="${qaListDTO.subject}" />
            </div>
        </div>
        <div class="title">
            <div class="icon" style="float:right;">
                <c:if test="${qaListDTO.adpYn eq 'N'}">
                    <a href="#" role="button" class="button btn_register" onclick="comment_btn('${fn:replace(writer, "'", "\\'") }','${fn:replace(category, "'", "\\'") }',${num});"  id="comment_btn" name="comment_btn" style="float: right;">답변!</a>
                </c:if>
            </div>
        </div>

        <div class="writer-wrap">
            <p class="writer"><c:out value="${qaListDTO.writer}" /></p>
            <ul class="person-function">
                <li><a href="https://m196.mailplug.com/member/login?host_domain=wiselab.co.kr" target='_blank'>메일 전송</a></li>
                <li><a href="#" onclick="questionsList_btn('${fn:replace(qaListDTO.writer, "'", "\\'") }');" id="questionsList_btn">질문 모아 보기</a></li>
                <li><a href="#" onclick="commentList_btn('${fn:replace(qaListDTO.writer, "'", "\\'") }');" id="commentList_btn">답변 모아 보기</a></li>
            </ul>

            <c:if test="${meetLink == null}">
                <a onclick="alert('미트링크가 없어요😅')"><span class="material-icons purple">videocam</span></a>
            </c:if>
            <c:if test="${meetLink != null}">
                <a href="${meetLink}" target='_blank'><span class="material-icons purple">videocam</span></a>
            </c:if>

        <c:if test="${member.id == qaListDTO.writer}">
            <span class="material-icons" onclick="update_btn(${qaListDTO.num})" id="update_btn">border_color</span>
            <span class="material-icons" onclick="delete_btn(${qaListDTO.num})" id="delete_btn">delete</span>
        </c:if>
        </div>
        <div class="subject">
            ${content}
        </div>
    </section>

<hr>
</br>

<c:set var="i" value="${0}"  />
<c:forEach var="commentQa" items="${commentQaList}" varStatus="status">
    <c:set var="commentQaContent" value="${commentContent[i]}"  />
    <c:set var="commentQaNum" value="${commentQa.num}"  />
    <c:set var="commentQaWriter" value="${commentQa.writer}"  />

    <section class="questions content-frame">
        <div class="title">
            <c:out value="${commentQa.subject}" />
            <div class="icon" style="float:right;">

                <c:set var="check" value="0"/>
                <c:forEach var="likeQa" items="${likeQaBoardList}" varStatus="status">
                    <c:set var="done_loop" value="false"/>
                    <c:if test="${likeQa.boardIdx == commentQa.num && likeQa.delYn eq 'N'}">
                        <c:set var="check" value="1"/>
                        <c:set var="done_loop" value="true"/>
                    </c:if>
                    <c:if test="${likeQa.boardIdx == commentQa.num && likeQa.delYn eq 'Y'}">
                        <c:set var="check" value="2"/>
                        <c:set var="done_loop" value="true"/>
                    </c:if>
                </c:forEach>

                <c:set var="checkScrap" value="0"/>
                <c:forEach var="scrapQa" items="${scrapQaBoardList}" varStatus="status">
                    <c:set var="done_loop" value="false"/>
                    <c:if test="${scrapQa.boardIdx == commentQa.num && scrapQa.delYn eq 'N'}">
                        <c:set var="checkScrap" value="1"/>
                        <c:set var="done_loop" value="true"/>
                    </c:if>
                    <c:if test="${scrapQa.boardIdx == commentQa.num && scrapQa.delYn eq 'Y'}">
                        <c:set var="checkScrap" value="2"/>
                        <c:set var="done_loop" value="true"/>
                    </c:if>
                </c:forEach>
<span id="changeLikeHtml${i}">
                <c:if test="${check == 0}">
                    <div class="board-cell board-like gray">
                    <span class="material-icons" id="comment_like_btn" onclick="regLike('${writer}',${commentQaNum})" >thumb_up</span>
                    </div>
                </c:if>

                <c:if test="${check == 1}">
                    <div class="board-cell board-like purple2">
                    <span class="material-icons" id="comment_like_btn" onclick="udpLike('${writer}',${commentQaNum})"  >thumb_up</span>
                    </div>
                </c:if>

                <c:if test="${check == 2}">
                    <div class="board-cell board-like gray">
                    <span class="material-icons" id="comment_like_btn" onclick="udpLike('${writer}',${commentQaNum})" style="color:gray;">thumb_up</span>
                    </div>
                </c:if>
</span>
<span id="changeScrapHtml${i}">
                <c:if test="${checkScrap == 0}">
                    <div class="board-cell board-like gray">
                    <span class="material-icons" id="comment_scrap_btn" onclick="regScrap('${writer}',${commentQaNum})" >bookmarks</span>
                    </div>
                </c:if>

                <c:if test="${checkScrap == 1}">
                    <div class="board-cell board-like purple2">
                    <span class="material-icons" id="comment_scrap_btn" onclick="udpScrap('${writer}',${commentQaNum})"  >bookmarks</span>
                    </div>
                </c:if>

                <c:if test="${checkScrap == 2}">
                    <div class="board-cell board-like gray">
                    <span class="material-icons" id="comment_scrap_btn" onclick="udpScrap('${writer}',${commentQaNum})" >bookmarks</span>
                    </div>
                </c:if>
</span>
                <c:if test="${member.id == qaListDTO.writer && qaListDTO.adpYn eq 'N'}">
                    <a href="#" role="button" class="" id="adp_btn" onclick="adp_btn('${fn:replace(writer, "'", "\\'") }',${qaListDTO.num},${commentQaNum});" style="float: right;">채택하기!</a>
                </c:if>
                <c:if test="${qaListDTO.adpNum == commentQaNum && qaListDTO.adpYn eq 'Y'}">
                   <a href="#" style="float: right;">질문자채택!</a>
                </c:if>
            </div>
        </div>
        <div class="writer-wrap">
            <p class="writer"><c:out value="${commentQa.writer}" /></p>
            <ul class="person-function">
                <li><a href="https://m196.mailplug.com/member/login?host_domain=wiselab.co.kr" target='_blank'>메일 전송</a></li>
                <li><a href="#" id="comment_questionsList_btn" onclick="comment_questionsList_btn('${commentQaWriter}')">질문 모아 보기</a></li>
                <li><a href="#" id="comment_commentsList_btn" onclick="comment_commentList_btn('${commentQaWriter}')">답변 모아 보기</a></li>
            </ul>

            <span class="material-icons purple" id="comment_meetLink_btn" onclick="comment_meetLink_btn(${commentQaNum})" >videocam</span>

            <c:if test="${member.id == commentQa.writer}">
                <span class="material-icons" id="comment_update_btn" onclick="comment_update_btn(${commentQaNum})" >border_color</span>
                <span class="material-icons" id="comment_delete_btn" onclick="comment_delete_btn(${commentQaNum})" >delete</span>
            </c:if>
        </div>
        <div class="subject">
            ${commentQaContent}
        </div>
    </section>
<c:set var="i" value="${i+1}"  />
</c:forEach>
</div>

<form id="adpForm" name="adpForm" action="/qaAdopt" method="post">
    <input type="hidden" id="boardNum" name="boardNum" value="" />
    <input type="hidden" id="commentNum" name="commentNum" value="" />
</form>


</body>

