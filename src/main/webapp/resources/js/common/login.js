let delImgArr = []

window.onload = function() {

    let idChkBool = false;
    let pwChkBool = false;
    let upd_pwChkBool = false;

    let idBox = document.querySelector('#id');
    let pwBox = document.querySelector('#pw');
    let pwChkBox = document.querySelector('#pwChk');

    let updPwBox = document.querySelector('#upd_pw');
    let updPwChkBox = document.querySelector('#upd_pwChk');

    let idChkBtn = document.querySelector("#idChkBtn");
    let pwChkBtn = document.querySelector("#pwChkBtn");
    let updPwChkBtn = document.querySelector("#upd_pwChkBtn");
    let signupBtn = document.querySelector("#signup_btn");
    let loginBtn = document.querySelector("#login_btn");

    let findPwBtn = document.querySelector("#findpw_btn");


    let updForm = document.getElementById('upd_form')
    let userDataUpdBtn = document.querySelector("#upd_btn");
    let pwModBtn = document.querySelector("#upd_pwModBtn");


    // ID 유효성 컨트롤: 회원 가입 여부 체크
    idChkBtn.addEventListener('click', () => {
        var userId = document.querySelector("#id").value;

        if (isEmpty(userId)) {
            commonPopup.alertPopup("아이디를 입력해주세요", true);
        } else {
            fetch("/idDupChk?" + "userId=" + userId)
                 .then(response => response.text())
                 .catch(error => console.error('Error:', error))
                 .then (idChkNum => {
                    if (idChkNum > 0) {
                      document.querySelector("#idChkAlert > .red").classList.remove('none');
                    } else {
                        document.querySelector("#idChkAlert > .porintColor").classList.remove('none');
                        document.querySelector("#idChkAlert > .red").classList.add('none');
                        idBox.readOnly = true; // 재수정 불가를 위한 readOnly 활성화
                        idChkBool = true;
                    }
                 })
        }
    });

    // 아이디 재설정
    idBox.addEventListener('click', async () => {
        if (idChkBool == true) {
            if (await commonPopup.confirmPopup("아이디를 재설정하시겠습니까? 아이디 중복 확인을 다시 받으셔야 합니다.", commonPopup.callback)) {
                idChkBool = false;
                document.querySelector("#idChkAlert > .porintColor").classList.add('none');
                idBox.value = "";
                idBox.readOnly = false;
                idBox.focus;
            }
       }
     });

    // PW 유효성 컨트롤: 패스워드 일치 여부 체크
    pwChkBtn.addEventListener('click', () => {

        const pwMatched = pw.value === pwChk.value
        const pwExped = pwExp(pw.value)

        if ( isEmpty(pw.value) || isEmpty(pwChk.value) ) {
            document.querySelector("#pwChkAlert > .red > .alert-text").innerText = "패스워드를 입력하세요."
            document.querySelector("#pwChkAlert > .red").classList.remove('none');
            pwChkBool = false;
        } else

        if(!pwMatched) {
            document.querySelector("#pwChkAlert > .red > .alert-text").innerText = "비밀번호가 일치하지 않습니다."
            document.querySelector("#pwChkAlert > .red").classList.remove('none');
            pwChkBool = false;
        } else

        if (!pwExped) {
            document.querySelector("#pwChkAlert > .red > .alert-text").innerText = "영문, 숫자, 특수문자를 혼합하여 입력해주세요."
            document.querySelector("#pwChkAlert > .red").classList.remove('none');
            pwChkBool = false;
        } else

        if(pwMatched && pwExped) {
            pwChkBool = true;
            document.querySelector("#pwChkAlert > .porintColor").classList.remove('none');
            document.querySelector("#pwChkAlert > .red").classList.add('none');
            pwBox.readOnly = true;
            pwChkBox.readOnly = true;
        }
    });

    // 회원정보 수정 비밀번호 재설정
    pwBox.addEventListener('click', async () => {
        if (pwChkBool == true) {
            if (await commonPopup.confirmPopup("비밀번호를 재설정하시겠습니까? 패스워드 확인을 다시 받으셔야 합니다.", commonPopup.callback)) {
                pwChkBool = false;
                pwBox.value = "";
                pwBox.readOnly = false;
                pwChkBox.value = "";
                pwChkBox.readOnly = false;
                pwBox.focus;
                document.querySelector("#pwChkAlert > .porintColor").classList.add('none');
            }
       }
     });

    // 비밀번호 수정 유효성 체크
   upd_pwChkBtn.addEventListener('click', () => {

        const pwMatched = upd_pw.value === upd_pwChk.value
        const pwExped = pwExp(upd_pw.value)

        if ( isEmpty(upd_pw.value) || isEmpty(upd_pwChk.value) ) {
            document.querySelector("#upd_pwChkAlert > .red > .alert-text").innerText = "패스워드를 입력하세요."
            document.querySelector("#upd_pwChkAlert > .red").classList.remove('none');
            upd_pwChkBool = false;
        }else

        if(!pwMatched) {
            document.querySelector("#upd_pwChkAlert > .red > .alert-text").innerText = "비밀번호가 일치하지 않습니다."
            document.querySelector("#upd_pwChkAlert > .red").classList.remove('none');
            upd_pwChkBool = false;
        }else

        if (!pwExped) {
            document.querySelector("#upd_pwChkAlert > .red > .alert-text").innerText = "영문, 숫자, 특수문자를 혼합하여 입력해주세요."
            document.querySelector("#upd_pwChkAlert > .red").classList.remove('none');
            upd_pwChkBool = false;
        }

        if(pwMatched && pwExped) {
            upd_pwChkBool = true;
            document.querySelector("#upd_pwChkAlert > .porintColor").classList.remove('none');
            document.querySelector("#upd_pwChkAlert > .red").classList.add('none');
            updPwBox.readOnly = true;
            updPwChkBox.readOnly = true;
        }

    });

    // 회원정보 수정 비밀번호 재설정
    updPwBox.addEventListener('click', async () => {
        if (upd_pwChkBool == true) {
            if (await commonPopup.confirmPopup("비밀번호를 재설정하시겠습니까? 패스워드 확인을 다시 받으셔야 합니다.", commonPopup.callback)) {
                upd_pwChkBool = false;
                updPwBox.value = "";
                updPwBox.readOnly = false;
                updPwChkBox.value = "";
                updPwChkBox.readOnly = false;
                updPwBox.focus;
                document.querySelector("#upd_pwChkAlert > .porintColor").classList.add('none');
            }
       }
     });

    // 회원 가입 버튼 유효성 컨트롤: stateHandler
    signupBtn.addEventListener('click', () => {
      if (chkAll()) {
        commonPopup.alertPopup("유효성 테스트 통과", false);
        emailValid();
      } else {
        commonPopup.alertPopup("유효성 테스트 미통과", true);
        event.preventDefault();
      }
    });


   userDataUpdBtn.addEventListener('click', () => {
        if (updChkAll()) {
            document.querySelector('#userUpdBox').classList.add('none');
            $dim(false);
            if(!delImgArr.isEmpty){
                for (const i in delImgArr) {
                 fetch("/delImgFile?" + "delImgFileNm=" + delImgArr[i])
                     .then(response => response.json())
                     .catch(error => console.error('이미지 삭제 실패 Error:', error))
                     // 왜 콘솔 로그가 안 찍힐까요?
                }
            }
            updForm.submit();
        } else {
           commonPopup.alertPopup("비밀번호를 입력하세요.", true);
           event.preventDefault();
         }
   })

    function chkAll() {
        if(idChkBool && pwChkBool) {
           return true;
        }else{
           return false;
        }
    }

    function updChkAll() {
        let passMod = (document.querySelector('#upd_pw').value.length > 0 || document.querySelector('#upd_pwChk').value.length > 0 ) ? true : false
        // 비밀번호 입력 확인

        // 비밀번호 수정 여부 체크
        if((passMod && upd_pwChkBool) || (!passMod && !upd_pwChkBool)){
               return true;
        }else{
               return false;
        }
    }

    function pwExp(pw) {
         let num = pw.search(/[0-9]/g);
         let eng = pw.search(/[a-z]/ig);
         let spe = chkSpecialChar(pw) ? 0 : -1;

        if(num < 0 || eng < 0 || spe < 0 ){
            return false;
        }else {
            return true;
        }

    }

   loginBtn.addEventListener('click', () => {
        var id = document.getElementById("login_id").value;
        var password = document.getElementById("login_pw").value;

        if(id.length == 0) {
            commonPopup.alertPopup("아이디를 입력하세요.", false);
          return false
        }

        if(password.length == 0) {
          commonPopup.alertPopup("비밀번호를 입력하세요.",false );
          return false
        }

        fetch("/authCheck?" + "login_Id=" + id)
             .then(response => response.json())
             .catch(error => console.error('Error:', error))
             .then( idChk => {
                 if (idChk.idExist == 0) {
                   commonPopup.alertPopup("존재하지 않는 아이디입니다.", false);
                 } else {
                    if (idChk.authKey!="Y") {
                     commonPopup.alertPopup("메일 인증이 완료되지 않았습니다.", false);
                    } else {
                     document.getElementById("login_form").submit();
                    }
                 }
              });

    })

    findPwBtn.addEventListener('click', () => {

        let id = document.querySelector("#findpw_Id").value

        fetch("/idDupChk?" + "userId=" + id)
             .then(response => response.text())
             .catch(error => console.error('Error:', error))
             .then( idChkNum => {
                 if (idChkNum == 0) {
                   document.querySelector("#findPwAlert > .red").classList.remove('none');
                 } else{
                    chgePwEmail(id);
                 }
              });

    })

}


function login() {
    document.querySelector('#loginBox').classList.remove('none');
    document.querySelector('#joinBox').classList.add('none');
    document.querySelector('#userUpdBox').classList.add('none');
    $dim();
}

function signUp() {
    document.querySelector('#signUpBox').classList.remove('none');
    document.querySelector('#joinBox').classList.add('none');
    document.querySelector('#loginBox').classList.add('none');
    document.querySelector('#userUpdBox').classList.add('none');
    $dim();
}

function findPw() {
    document.querySelector('#findPwBox').classList.remove('none');
    document.querySelector('#loginBox').classList.add('none');
    $dim();
}

function userUpd() {
    document.querySelector('#userUpdBox').classList.remove('none');
    document.querySelector('#bar-chk').checked = false;
    $dim();
}

async function userWithdraw() {
    let withdrawal = await commonPopup.confirmPopup("탈퇴하시겠습니까?", commonPopup.callback);

    if (withdrawal) {
        location.href = "/user/withdraw"
    }
}

function memPopUpClose(modalCheck) {
    document.querySelector('#signUpBox').classList.add('none');
    document.querySelector('#loginBox').classList.add('none');
    document.querySelector('#userUpdBox').classList.add('none');
    document.querySelector('#findPwBox').classList.add('none');
    commonPopup.modalPopupCheck(modalCheck);
    $dim(false); // dim 역할은 비로그인 시 누르지 못하게 하는 용도입니다.
    location.reload(); // 멤버 세션 체크를 위해 리로드를 시킵니다.
}

// 회원가입 이메일 인증
function emailValid() {
    var email_Id = document.getElementById('id').value;

    fetch("/authMailSend?" + "email_Id=" + email_Id)
         .then(response => response.text())
         .catch(error => console.error('Error:', error))
         .then( response => {
            document.getElementById('reg_form').submit()
         });

}


// 비밀번호 이메일 인증
function chgePwEmail(userId) {

    fetch("/pwMailSend?" + "userId=" + userId)
         .then(response => response.text())
         .catch(error => console.error('Error:', error))
         .then(response => {
                commonPopup.alertPopup("임시 비밀번호 변경 링크가 발송됩니다. 이메일을 확인하세요!", false)
                setTimeout( function(){
                    memPopUpClose(false)
                }, 300);
            });

}


// 이미지 삭제
function imgDel(delImgFileNm) {
    let selector = document.getElementById(delImgFileNm)
    selector.parentElement.remove()
    delImgArr.push(delImgFileNm)
}
