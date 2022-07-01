<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="sidebar-wrap">
        <input type="checkbox" name="" id="bar-chk" style="display: none;">
        <div class="sidebar">
            <div class="top">
                <div class="info">
                    <img src ="../${member.fileList[0].filePath}" width="45" height="45">
                    <label for="my-function-chk">${member.name} (직위)<br>${member.site}</label>
                    <input type="checkbox" name="" id="my-function-chk" style="display: none;">
                    <ul class="person-function">
                     <c:if test="${empty member}">
                     <li onclick="login()">로그인 </li>
                     <li onclick="signUp()">회원가입 </li>
                     </c:if>
                      <c:if test="${not empty member}">
                     <li><a href="#">작성 글 모아 보기</a> </li>
                     <li><a href="user/logout">로그아웃</a> </li>
                     <li><a href="userUpd()">정보 수정</a> </li>
                     <li><a href="user/withdraw">탈퇴</a> </li>
                     </c:if>
                    </ul>
                </div>
                <ul class="menu">
                    <li><a href="/qaBoard">질문하고 싶어요</a></li>
                    <li><a href="/tipBoard">Tech Tip</a></li>
                    <li><a href="/">사이트 별 맛집 목록</a></li>
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


<%-- 회원 가입 --%>
<div id="signUpBox" class="popup-wrap page-center none">
    <div class="signUp-popup-wrap" style="min-width: 450px">
    <p class="close"> </p>
    <form role="form" method="post" autocomplete="off" id="reg_form" action="/register">
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">ID</label>
            <input type="text" class="signUp-input pr30p" id="id" name="id" required="required" placeholder="아이디">
            <div class="email-info">@ wiselab.co.kr</div>
            <p id="idChkBtn" class="signUp-input-label mg0">check</p>
        </div>
        <div id ="idChkAlert" class="signUp-pwassword-info-wrap">
            <p class="porintColor none">사용 가능한 아이디입니다.</p>
            <p class="red none">아이디가 존재합니다. 다른 아이디를 입력하세요.</p>
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">이름</label>
            <input type="text" class="signUp-input" id="name" name="name" required="required" placeholder="이름">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">파견 사이트</label>
            <input type="text" class="signUp-input" id="site" name="site" placeholder="파견 사이트" required="required">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PASSWORD</label>
            <input type="password" class="signUp-input" id="pw" name="pw" required="required" placeholder="비밀번호">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PWCHECK</label>
            <input type="password" class="signUp-input" id="pwChk" name="pwChk" required="required" placeholder="비밀번호 확인">
            <p for="" id="pwChkBtn" class="signUp-input-label mg0">check</p>
        </div>
        <div id="pwChkAlert" class="signUp-pwassword-info-wrap">
            <p class="porintColor none">패스워드가 일치합니다</p>
            <p class="red none">패스워드가 일치하지 않습니다</p>
        </div>
        <div class="signUp-confirm-Button-wrap">
            <button type="button" id="signup_btn" name="signup_btn">메일 인증하고 가입하기</button>
        </div>
    </div>
    </form>
</div>

<div id="loginBox" class="popup-wrap page-center none">
<form role="form" method="post" autocomplete="off" id="login_form" action="/login">
    <div class="signUp-popup-wrap form-between">
        <div class="w70p">
            <div class="signUp-input-wrap">
                <label for="" class="signUp-input-label">ID</label>
                <input type="text" class="signUp-input" id="login_id" name="id" required="required" />
            </div>
            <div class="signUp-input-wrap">
                <label for="" class="signUp-input-label">PASSWORD</label>
                <input type="password" class="signUp-input" id="login_pw" name="pw" required="required" />
            </div>
        </div>
        <div class="w30p">
            <div class="login-button-wrap">
                <button type="button" id="login_btn" name="login_btn">로그인</button>
            </div>
        </div>
    </div>
    <div class="popup-link-wrap">
        <p onclick="signUp()">회원가입</a></p>
        <p><a href="#">아이디 비밀번호 찾기</a></p>
    </div>
</form>
</div>



<div id="joinBox" class="popup-wrap page-center">
    <div class="signUp-popup-wrap form-between">
        <div class="w50p">
            <div>
                <button type="button" name="signUp_btn" onclick="signUp()" class="join-box-btn">SIGN UP</button>
            </div>
        </div>
        <div class="w50p">
            <div>
                <button type="button"  name="login_btn" onclick="login()" class="join-box-btn">LOGIN</button>
            </div>
        </div>
    </div>
    <div class="join-box-txt">
       <p>해당 사이트는 와이즈랩 메일 인증 뒤 가입이 가능합니다.</p>
    </div>
</div>


<div id="loginBox" class="popup-wrap page-center none">
<form role="form" method="post" autocomplete="off" id="login_form" action="/login">
    <div class="signUp-popup-wrap form-between">
        <div class="w70p">
            <div class="signUp-input-wrap">
                <label for="" class="signUp-input-label">ID</label>
                <input type="text" class="signUp-input" id="login_id" name="id" required="required" />
            </div>
            <div class="signUp-input-wrap">
                <label for="" class="signUp-input-label">PASSWORD</label>
                <input type="password" class="signUp-input" id="login_pw" name="pw" required="required" />
            </div>
        </div>
        <div class="w30p">
            <div class="login-button-wrap">
                <button type="button" id="login_btn" name="login_btn">로그인</button>
            </div>
        </div>
    </div>
    <div class="popup-link-wrap">
        <p onclick="register()">회원가입</a></p>
        <p><a href="#">아이디 비밀번호 찾기</a></p>
    </div>
</form>
</div>



<%-- 회원 정보 수정 --%>
<div id="userUpdBox" class="popup-wrap page-center none">
    <div class="signUp-popup-wrap" style="min-width: 450px">
    <form role="form" method="post" autocomplete="off" id="upd_form" action="/user/update">
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">ID</label>
            <input type="text" class="signUp-input pr30p" id="upd_id" name="id" required="required" placeholder="아이디" value="{}" readonly/>
            <div class="email-info">@ wiselab.co.kr</div>
            <p for="" id="idChkBtn" class="signUp-input-label">check</p>
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">이름</label>
            <input type="text" class="signUp-input" id="upd_name" name="name" required="required" placeholder="이름">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">파견 사이트</label>
            <input type="text" class="signUp-input" id="upd_site" name="site" placeholder="파견 사이트" required="required">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PASSWORD</label>
            <input type="password" class="signUp-input" id="upd_pw" name="pw" required="required" placeholder="비밀번호">
        </div>
        <div class="signUp-input-wrap">
            <label for="" class="signUp-input-label">PWCHECK</label>
            <input type="password" class="signUp-input" id="upd_pwChk" name="pwChk" required="required" placeholder="비밀번호 확인">
            <p id="pwChkBtn" class="signUp-input-label">check</p>
        </div>
        <div id="pwChkAlert" class="signUp-pwassword-info-wrap">
            <p class="porintColor none">패스워드가 일치합니다</p>
            <p class="red none">패스워드가 일치하지 않습니다</p>
        </div>
        <div class="signUp-confirm-Button-wrap">
            <button type="button" id="upd_btn" name="upd_btn">정보 수정</button>
        </div>
    </div>
    </form>
</div>
