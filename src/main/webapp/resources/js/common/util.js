
 /*
     * 작성자 : 서은빈
     * 공백 확인
     * param : Object
     * return : Bool
     * 날짜 : 2022-06-19
     * */
var isEmpty = function(obj){
    if( obj == "" || obj == null || obj == undefined || ( obj != null && typeof obj == "object" && !Object.keys(obj).length ) ){
      return true
    }else{
      return false
    }
  };

ㄵ/*
    * 작성자 : 임동근
    * 서버의 현재 시간 가져오는 함수
*/
function getCurrentServerTime() {
    let xmlHttp;
    let year,month,date,hours,minutes,currentDate;

    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
        xmlHttp.open('HEAD', window.location.href.toString(), false);
        xmlHttp.setRequestHeader("Content-Type", "text/html");
        xmlHttp.send('');

        let currentTime = xmlHttp.getResponseHeader("Date");

        if (xmlHttp.status == 200) {
            let dates = new Date(currentTime);
            year = dates.getFullYear();
            month = dates.getMonth() + 1;
            date = dates.getDate();
            hours = dates.getHours();
            minutes = dates.getMinutes();

            if (parseInt(month)<10) {
                month = 0 + "" + month;
            }
            if (parseInt(date)<10) {
                date = 0 + "" + date;
            }
            if(parseInt(hours) < 10){
                hours = 0 + "" + hours;
            }
            if(parseInt(minutes) < 10){
                minutes = 0 + "" + minutes;
            }
            currentDate = year+"/"+month+"/"+date+"/"+hours+":"+minutes;
        } else {
            console.log("fail");
        }
    }
    return currentDate;
}

/*
    * 작성자 : 임동근
    * 문자열 오른쪽부분을 지정한 길이만큼 리턴
*/
function stringRightCheck(str, checkPoint) {

    if (str!=null) {
        if (str.length>checkPoint) {
            return str.substring(checkPoint);
        } else {
            console.log("Error");
        }
    } else {
        console.log("str is Null");
    }
}

/*
    * 작성자 : 임동근
    * 문자열 자릿수 체크하여 제한 (한글 2Byte, 영어 1Byte)
*/
function stringLengthCheck(input, maxByte) {
    let inputLen = input.length;
    let totalByte = 0;

    for (let i=0; i<inputLen; i++) {
        let inputChar = input.charAt(i);
        if (escape(inputChar).length > 4) {
            totalByte+=2;
        } else {
            totalByte++;
        }
    }

    if (totalByte > maxByte) {
        alert("입력한 길이수 초과");
        return;
    }
    return input;
}

/*
    * 작성자 : 임동근
    * alert 팝업, confirm 팝업, 모달 적용, 팝업 닫기
*/
let commonPopup = {

alertPopup : function (alertMsg) {
    let closeBtn = document.querySelector('#alert-close-btn');

    if (alertMsg!=null && alertMsg.trim()!="") {
        this.open("alert-type", alertMsg);
    }

    closeBtn.addEventListener('click', function () {
        commonPopup.close();
    });

},

confirmPopup : async function (confirmMsg, confirmCallback) {
    if (confirmMsg==null && confirmMsg.trim()=="") {
        console.log("Text is null");

    } else if (confirmCallback==null || typeof confirmCallback != 'function') {
        console.log("CallBack is not Function");
    } else {
        return await confirmCallback(confirmMsg);

    }
},

callback : async function (confirmMsg) {
    return new Promise(resolve => {
        let confirmBtn = document.querySelector('#confirm-btn');
        let cancelBtn = document.querySelector('#confirm-cancel-btn');

        commonPopup.open("confirm-type", confirmMsg);

        const onclick = pass => {
            resolve(pass);
            commonPopup.close();
        };

        confirmBtn.addEventListener('click', onclick.bind(null, true));
        cancelBtn.addEventListener('click', onclick.bind(null, false));
    })

},

close : function () {
    document.querySelector('#alertBox').classList.add('none');
    document.querySelector('#confirmBox').classList.add('none');
    commonPopup.modalPopupCheck(false);
},

open : function (type, msg) {
    if (type=="alert-type") {
        document.querySelector('#alertBox').classList.remove('none');
        document.querySelector('.popup-message-wrap').innerHTML = msg;
    } else if (type=="confirm-type") {
        document.querySelector('#confirmBox').classList.remove('none');
        document.querySelector(".confirm-message-wrap").innerHTML = msg;
    }
    this.modalPopupCheck(false);
},

modalPopupCheck : function (modalCheck) {
    let body = document.getElementsByTagName('body')[0];
    let element = document.getElementById('dim');
    let elDimWrapper = document.getElementById('dim-wrapper');

    if (modalCheck) {
        element.className         += ' dim';
        elDimWrapper.style.display = 'block';
        body.style.overflowY       = 'hidden';

    } else {
        element.classList.remove('dim');
        elDimWrapper.style.display = 'none';
        body.style.overflowY       = 'auto';
    }
},
}

function memPopUpClose(modalCheck) {
    document.querySelector('#signUpBox').classList.add('none');
    document.querySelector('#loginBox').classList.add('none');
    document.querySelector('#userUpdBox').classList.add('none');
    document.querySelector('#findPwBox').classList.add('none');
    commonPopup.modalPopupCheck(modalCheck);
}

