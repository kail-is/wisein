<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!--무게시글-->
<c:if test="${tipList == null}">
    <div class="content-wrap boardList">
        <section class="content-frame boardList">
             <div class="header-section">
                 <div class="title">TIP</div>
                 <div class="content-top-group">
                    <button type="button" onClick="location.href='tipBoard'"></button>
                </div>
             </div>
            <img src ="../resources/image/nonPosting.png" class="noPost">
        </section>
    </div>
</c:if>

<!--유게시글-->
<c:if test="${tipList != null}">
    <div class="content-wrap boardList">
        <section class="content-frame boardList">
            <div class="header-section">
                <div class="title">TIP</div>
                <div class="content-top-group">
                   <c:if test="${side_gubun ne 'Y'}">
                        <button type="button" onClick="location.href='tipBoard'" ></button>
                   </c:if>
                </div>
            </div>
            <div class="board-list">
                <div class="board-line board-header">
                    <div class="board-cell board-no">
                    </div>
                    <div class="board-cell board-category purple2">
                        <p class="category-select">카테고리</p>
                        <!--
                        <ul class="person-function">
                            <li><a href="#">FRONT</a></li>
                            <li><a href="#">BACK</a></li>
                            <li><a href="#">DB</a></li>
                        </ul>
                        <span class="material-icons">
                                expand_more
                        </span>
                        -->
                    </div>
                    <div class="board-cell board-title">
                        제목(가나다)
                        <!--
                        <span class="material-icons">
                                expand_more
                        </span>
                        -->
                    </div>
                    <div class="board-cell board-like gray">
                        댓글수
                    </div>
                    <div class="board-cell board-like gray">
                        좋아요
                    </div>
                    <div class="board-cell board-like gray">
                        스크랩
                    </div>
                    <div class="board-cell board-writer gray">
                        작성자
                    </div>
                    <div class="board-cell board-date gray">
                        날짜
                    </div>
                </div>

                <c:forEach var="tip" items="${tipList}">
                    <div class="board-line">
                        <div class="board-cell board-no">
                            <c:out value="${tip.num}" />
                        </div>
                        <div class="board-cell board-category purple">
                            <c:out value="${tip.category}" />
                        </div>
                        <div class="board-cell board-title">
                            <a href="/tipDetail?num=${tip.num}"><c:out value="${tip.subject}" /></a>
                        </div>
                        <!--댓글수-->
                        <c:if test="${tip.commCnt == 0}">
                            <div class="board-cell board-like gray">
                                <span class="material-icons">comment</span>${tip.commCnt}
                            </div>
                        </c:if>
                        <c:if test="${tip.commCnt != 0}">
                            <div class="board-cell board-like purple2">
                                <span class="material-icons">comment</span>${tip.commCnt}
                            </div>
                        </c:if>
                        <!--좋아요-->
                        <c:if test="${tip.likeCount == 0}">
                            <div class="board-cell board-like gray">
                                <span class="material-icons">thumb_up</span>${tip.likeCount}
                            </div>
                        </c:if>
                        <c:if test="${tip.likeCount != 0}">
                            <div class="board-cell board-like purple2">
                                <span class="material-icons">thumb_up</span>${tip.likeCount}
                            </div>
                        </c:if>
                        <!--북마크-->
                        <c:if test="${tip.scrapCount == 0}">
                            <div class="board-cell board-like gray">
                                <span class="material-icons" style="max-width:24px;">bookmarks</span>${tip.scrapCount}
                            </div>
                        </c:if>
                        <c:if test="${tip.scrapCount != 0}">
                            <div class="board-cell board-like purple2">
                                <span class="material-icons" style="max-width:24px;">bookmarks</span>${tip.scrapCount}
                            </div>
                        </c:if>
                        <div class="board-cell board-writer gray">
                            <p class="writer"><c:out value="${tip.writer}" /><br></p>
                            <ul class="person-function list">
                                <li><a href="https://m196.mailplug.com/member/login?host_domain=wiselab.co.kr" target='_blank'>메일 전송</a></li>
                                <li><a onclick="gatherMemTip('${tip.writer}')">작성팁 모아보기</a></li>
                            </ul>
                        </div>
                        <div class="board-cell board-date gray">
                            <fmt:formatDate value="${tip.regDate}" pattern="yyyy-MM-dd"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!--페이징-->
        <ul class="pageno-group">
            <div class="pagination">
                ${pagination}
            </div>
        </ul>
    </div>
</c:if>


