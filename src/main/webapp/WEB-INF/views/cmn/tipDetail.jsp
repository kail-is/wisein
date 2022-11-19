<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<head>
    <link rel="stylesheet" href="resources/css/tipDetail.css">
</head>
<div class="content-wrap">
    <section class="questions content-frame">
        <div class="icon" style="float:right;">
            <span id="changeLikeHtml">
                <!--좋아요-->
                <c:if test="${likeDelYn == 'none'}">
                    <span class="material-icons" id="like_btn" onclick="regLike(${tipBoardDTO.num})" style="color:gray;" >thumb_up</span>
                </c:if>
                <c:if test="${likeDelYn == 'Y'}">
                    <span class="material-icons" id="like_btn" onclick="udpLike(${tipBoardDTO.num})" style="color:gray;" >thumb_up</span>
                </c:if>
                <c:if test="${likeDelYn == 'N'}">
                    <span class="material-icons" id="like_btn" onclick="udpLike(${tipBoardDTO.num})" style="color:purple;" >thumb_up</span>
                </c:if>
            </span>

            <!--북마크-->
            <span id="changeScrapHtml">
                <c:if test="${scrapDelYn == 'none'}">
                    <span class="material-icons" id="bookmark_btn" onclick="regScrap(${tipBoardDTO.num})" style="color:gray;" >bookmarks</span>
                </c:if>
                <c:if test="${scrapDelYn == 'Y'}">
                    <span class="material-icons" id="bookmark_btn" onclick="udpScrap(${tipBoardDTO.num})" style="color:gray;" >bookmarks</span>
                </c:if>
                <c:if test="${scrapDelYn == 'N'}">
                    <span class="material-icons" id="bookmark_btn" onclick="udpScrap(${tipBoardDTO.num})" style="color:purple;" >bookmarks</span>
                </c:if>
            </span>
        </div>

        <!--제목-->
        <div class="title">
            <c:out value="${tipBoardDTO.subject}"/>
        </div>

        <!--작성자-->
        <div class="writer-wrap">
            <p class="writer" id="tipWriter"><c:out value="${tipBoardDTO.writer}" /></p>
            <ul class="person-function">
                <li><a href="https://mail.wiselab.co.kr/member/login?host_domain=wiselab.co.kr&t=1664738123" target='_blank'>메일 전송</a></li>
                <li><a href="#">질문 모아 보기</a></li>
                <li><a href="#">답변 모아 보기</a></li>
            </ul>

                <c:if test="${meetLink == null}">
                    <a onclick="alert('미트링크가 없어요😅')"><span class="material-icons purple">videocam</span></a>
                </c:if>
                <c:if test="${meetLink != null}">
                    <a href="${meetLink}" target='_blank'><span class="material-icons purple">videocam</span></a>
                </c:if>
        </div>

        <div class="subject">
            ${content}
        </div>
    </section>

        <!-- 게시글 수정삭제 아이콘-->
        <c:set var="memberId" value="${memberId}" />
        <c:if test="${tipBoardDTO.writer == memberId}">
            <div class="icon" align="right">
                <span class="material-icons" onClick="updTip()">border_color</span>
                <span class="material-icons" onClick="delTip()">delete</span>
            </div>
        </c:if>

    <!-- 댓글 -->
    <section class="recommend-wrap" id="changeCommHtml">
        <div class="recommend-titleLine">
            댓글 (${commentNum})
        </div>
        <c:forEach var="commentList" items="${commentList}">
            <ul class="recommend">
                <li>
                    <div class="wrap">
                        <div class="recommend-info-wrap">
                            <!-- 댓글 작성자 img -->
                                <c:if test = "${commentList.filePath != null}">
                                    <img src ="../${commentList.filePath}" onError="this.src='../resources/image/Conic.png'">
                                </c:if>
                                <c:if test = "${commentList.filePath == null}">
                                    <img src ="../resources/image/Conic.png">
                                </c:if>
                            <div class="info">
                                <!-- 댓글 작성자 -->
                                <div class="title"><c:out value="${commentList.writer}"/></div>
                                <div class="sub">
                                    <!-- 댓글 날짜 -->
                                    <div class="date">
                                        <fmt:formatDate value="${commentList.regDate}" pattern="yyyy-MM-dd"/>
                                    </div>
                                    <!-- 댓글 수정삭제 아이콘 -->
                                    <c:if test="${commentList.writer == memberId}">
                                        <div class="icon">
                                            <span class="material-icons" onClick="openModi(${commentList.num}, '${commentList.content}')">border_color</span>
                                            <span class="material-icons" onClick="delComm(${commentList.num})">delete</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <!--댓글 내용-->
                        <div class="content" id="comm${commentList.num}" name="comm${commentList.num}">
                            <c:out value="${commentList.content}"/>
                        </div>
                        <!--댓글 수정-->
                        <div class="content-mod" id="modComm${commentList.num}" name="modComm${commentList.num}" style='display: none;'>
                            <textarea class="comment_inbox_text" id="modComm_content${commentList.num}" name="modComm_content${commentList.num}" placeholder="댓글을 남겨보세요" rows="1"></textarea>
                        </div>
                         <div class="comment_attach" id="mod_comment_attach${commentList.num}" style='display: none;'>
                            <div class="register_box">
                                <a href="#" role="button" class="button btn_register" onClick="modComm(${commentList.num})">수정</a>
                                <a href="#" role="button" class="button btn_register" onClick="modCancel(${commentList.num})">취소</a>
                            </div>
                         </div>
                    </div>
                </li>
            </ul>
        </c:forEach>
    </section>
    <!--댓글 입력-->
    <div class="CommentWriter">
        <div class="comment_inbox">
            <strong class="blind">댓글을 입력하세요</strong>
            <em><div class="comment_inbox_name" id="comment_writer" name="comment_writer">${memberId}</div></em>
            <textarea class="comment_inbox_text" id="comment_content" name="comment_content" placeholder="댓글을 남겨보세요" rows="1"></textarea>
        </div>

        <div class="comment_attach">
            <div class="register_box">
                <a href="#" role="button" class="button btn_register" onClick="regComm()">등록</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>