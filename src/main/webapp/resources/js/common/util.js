 /*
 * 작성자 : 서은빈
 * 공백 확인
 * param : Object
 * return : Bool
 * 날짜 : 2022-06-19
* */
function isEmpty(obj){
    if( obj == "" || obj == null || obj == undefined || ( obj != null && typeof obj == "object" && !Object.keys(obj).length ) ){
      return true
    }else{
      return false
    }
  }

 /*
     * 작성자 : 이형근
     * 문자열이 숫자형식에 맞는지 여부 체크
     * param : value
     * return : Bool
     * 날짜 : 2022-08-11
     * */
function gfn_isNum(val){
  return !isNaN(val)
}

 /*
     * 작성자 : 이형근
     * 문자열이 알파벳(a~z, A~Z)만으로 구성되어 있는지 체크
     * param : value
     * return : Bool
     * 날짜 : 2022-08-11
     * */
function gfn_isAlpha(val){
    if(!val){
        return false;
    }
    const regex = /^[a-z|A-Z]+$/;
    return regex.test(val);
}

 /*
     * 작성자 : 이형근
     * 문자열이 알파벳(a~z, A~Z), 숫자만으로 구성되어 있는지 체크
     * param : value
     * return : Bool
     * 날짜 : 2022-08-11
     * */
function gfn_isAlNum(val){
    if(!val){
        return false;
    }
    const regex = /^[a-z|A-Z|0-9]+$/;
    return regex.test(val);
}

 /*
     * 작성자 : 이형근
     * 문자열이 한글로만 구성되어 있는지 체크
     * param : value
     * return : Bool
     * 날짜 : 2022-08-11
     * */
function gfn_isKor(val){
    if(!val){
        return false;
    }
    const regex = /^[ㄱ-ㅎ|가-힣]+$/;
    return regex.test(val);
}

 /*
     * 작성자 : 이형근
     * 문자열이 숫자형식에 맞으면 숫자값을 반환
     * param : object
     * return : int
     * 날짜 : 2022-08-11
     * */
function gfn_getNum(obj){
  if(!isNaN(obj)){
    return Number(obj)
  }
}

 /*
     * 작성자 : 이형근
     * NULL 일 경우 빈 값을 리턴
     * param : object
     * return : ""
     * 날짜 : 2022-08-11
     * */
function gfn_nullToEmpty(obj) {
    var newStr = obj;

    if( obj == "" || obj == null || obj == undefined || ( obj != null && typeof obj == "object" && !Object.keys(obj).length ) ){
      return "";
    }
    return newStr;
  };

 /*
     * 작성자 : 이형근
     * 입력값 형태에 따라서 길이 또는 범위를 구하는 함수
     * param : object
     * return : int
     * 날짜 : 2022-08-11
     * */
function gfn_length(obj){
    if( obj == "" || obj == null || obj == undefined || ( obj != null && typeof obj == "object" && !Object.keys(obj).length ) ){
      return 0;
    }
    return obj.length;
}

 /*
     * 작성자 : 이형근
     * 전체 문자열 중 특정 문자열이 포함된 위치를 반납
     * param : strFull, searchStr
     * return : int
     * 날짜 : 2022-08-11
     * */
function gfn_indexOf(strFull, searchStr){
    idx = strFull.indexOf(searchStr);
    return idx;
}

 /*
     * 작성자 : 이형근
     * 입력된 문자열의 일부분을 다른 문자열로 치환하는 함수
     * param : strFull, searchStr
     * return : int
     * 날짜 : 2022-08-11
     * */
function gfn_replace(strFull, strOld, strNew){
    strFull = strFull.replace(strOld, strNew);
    return strFull;
}

 /*
     * 작성자 : 이형근
     * 입력값 형태에 따라서 길이 또는 범위를 구하는 함수
     * param : strFull, searchStr
     * return : int
     * 날짜 : 2022-08-11
     * */
function gfn_length(obj){
    if(typeof obj == "object"){
        return Object.keys(obj).length;
    } else{
        return obj.length;
    }
}

/*
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

alertPopup : function (alertMsg, dimCheck) {
    let dimClosed;
    if (typeof dimCheck != "undefined") {
        dimClosed = true;
    }
    let closeBtn = document.querySelector('#alert-close-btn');

    if (alertMsg!=null && alertMsg.trim()!="") {
        this.open("alert-type", alertMsg);
    }
    $dim();

    closeBtn.addEventListener('click', function () {
        console.log(dimClosed);
        commonPopup.close(dimClosed);
    });

},

confirmPopup : function (confirmMsg, confirmCallback) {
    if (confirmMsg==null && confirmMsg.trim()=="") {
        console.log("Text is null");

    } else if (confirmCallback==null || typeof confirmCallback != 'function') {
        console.log("CallBack is not Function");
    } else {
         return confirmCallback(confirmMsg);
    }

},

callback : function (confirmMsg) {
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

close : function (dimClosed) {
    console.log(dimClosed);
    document.querySelector('#commonPopup').classList.add('none');
    document.querySelector('#roadViewPopup').classList.add('none');
    if (typeof dimClosed == "undefined") {
        $dim(false);
    }
},

open : function (type, msg) {
    document.querySelector('.popup-title').style.display = "";

    if (type=="alert-type") {
        document.querySelector('#commonPopup').classList.remove('none');
        document.querySelector('.alert-button-wrap').style.display = "";
        document.querySelector('.confirm-button-wrap').style.display = "none";
        document.querySelector('.popup-message-wrap').innerHTML = msg;

    } else if (type=="confirm-type") {
        document.querySelector('#commonPopup').classList.remove('none');
        document.querySelector('.confirm-button-wrap').style.display = "";
        document.querySelector('.alert-button-wrap').style.display = "none";
        document.querySelector('.popup-message-wrap').innerHTML = msg;
    }
    $dim();
},

}

 /*
 * 작성자 : 서은빈
 * String 타입을 날짜 형식으로 변환
 * param : String
 * return : Date
 * 날짜 : 2022-08-07
 * */

function strToDate(input){
    // input 예제 1. 2022-08-06
    // input 예제 2. 2022/08/06
    // input 예제 3. 20220806
    // 이외의 input의 경우 return "Invalid Date" (문자열 반환이기 때문에 ==으로 비교 필요)

    // 2022/08/06일 경우의 정규식
    let regexSlash = RegExp(/^\d{4}\/(0[1-9]|1[012])\/(0[1-9]|[12][0-9]|3[01])$/);

    // 2022-08-06일 경우의 정규식
    let regexHipen = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);

    input = input.trim();

    if ( regexHipen.test(input) ) {

        console.log(new Date(input));
        return new Date(input);

    } else if ( regexSlash.test(input) ){

        let slashArr = input.split('/')

        let year = slashArr[0]
        let month = slashArr[1]
        let day = slashArr[2]

        console.log(new Date(year + "-" + month  + "-" + day ))
        return new Date(year + "-" + month  + "-" + day );

   } else if ( input.length == 8 ){

        let year = input.substr(0, 4);
        let month = input.substr(4, 2);
        let day = input.substr(6, 2);

        console.log(new Date(year + "-" + month  + "-" + day ))
        return new Date(year + "-" + month  + "-" + day );

    } else {
        return "Invalid Date"
    }

}

/*
 * 작성자 : 서은빈
 * 문자 전체 길이: 문자,숫자,특수문자 - Count 1, 한글/한자 - Count 2
 * param : String
 * return : Number (String의 문자열 전체 길이)
 * 날짜 : 2022-08-06
 * */

function lengthByteCount(text) {

    let length = 0;

    text = text.trim();

    for( let i of text ) {
        if (/[ㄱ-ㅎㅏ-ㅣ가-힣一-龥]/.test(i)) {
            length += 2
        } else {
            length++
        }
    }
    return length
}



/*
 * 작성자 : 서은빈
 * Byte: 영문,숫자 - Count 1, 한글 - Count 2
 * param : String
 * return : Number (String의 Byte)
 * 날짜 : 2022-08-06
* */

function getByteLength(text) {
    let byte = 0;

    text = text.trim();

    for( let i of text ) {
        if (/[ㄱ-ㅎㅏ-ㅣ가-힣一-龥]/.test(i)) {
            byte += 2
        } else {
            byte++
        }
    }
    return byte
}


/*
 * 작성자 : 서은빈
 * Json to String
 * param : JSON (Object)
 * return : String
 * 날짜 : 2022-08-06
* */

function JSONToString(jsonObj) {
    return JSON.stringify(jsonObj);
    // 있어야 할까..
}


/*
 * 작성자 : 서은빈
 * 모바일 기종명 확인 (PC 실행시 PC)
 * param :
 * return : String
 * 날짜 : 2022-08-06
 * */

function getMobileModelType() {
	 let userAgent = navigator.userAgent;

	 if(userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPod") != -1 || userAgent.indexOf("iPad") != -1){
	    return "iOS"
	 }else if(userAgent.indexOf("Android") != -1){
	    return "Android"
	 }else {
    	 return "PC"
	 }

}


/*
 * 작성자 : 서은빈
 * 안드로이드 여부 확인
 * param :
 * return : Bool
 * 날짜 : 2022-08-06
 * */

function isAndroid() {
    let userAgent = navigator.userAgent;
    if(userAgent.indexOf("Android") != -1){
        return true
    }else {
        return false
    }
}


/*
 * 작성자 : 서은빈
 * 아이폰 / 아이팟 여부 확인
 * param :
 * return : Bool
 * 날짜 : 2022-08-06
 * */

function isiPhone() {
    let userAgent = navigator.userAgent;
    if(userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPod") != -1 ){
        return true
    }else {
        return false
    }
}



 /*
 * 작성자 : 서은빈
 * 모바일 여부 확인
 * param :
 * return : Bool
 * 날짜 : 2022-08-06
 * */

function isMobile() {
	if ( isAndroid() || isiPhone() ) {
		return true;
	}else {
		return false;
	}
}


/*
 * 작성자 : 서은빈
 * 휴대폰 번호 유효성 확인
 * param : String
 * return : Bool
 * 날짜 : 2022-08-07
 * */

function isCellPhoneNum(phoneNumber) {

    phoneNumber = phoneNumber.trim();

    let regexPhone = /^010-?([0-9]{4})-?([0-9]{4})$/;

    if( regexPhone.test(phoneNumber) ) {
        return true
    }else {
        return false
    }
}


/*
 * 작성자 : 서은빈
 * 일반 번호 유효성 확인
 * param : String
 * return : Bool
 * 날짜 : 2022-08-07
 * */

function isFixedLinePhoneNum(phoneNumber) {

    phoneNumber = phoneNumber.trim();

    let regexFixedLinePhone = /^\d{2,3}-?\d{3,4}-?\d{4}$/;

    if( regexFixedLinePhone.test(phoneNumber) ) {
        return true
    }else {
        return false
    }
}


/*
 * 작성자 : 서은빈
 * 이메일 유효성 확인
 * param : String
 * return : Bool
 * 날짜 : 2022-08-07
 * */

function isEmail(mailAdd) {

    mailAdd = mailAdd.trim();

    let regexMail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    if( regexMail.test(mailAdd) ) {
        return true
    }else {
        return false
    }
}



/*
 * 작성자 : 서은빈
 * 시간 시분 입력 체크
 * param : String (hh-mm or hhmm)
 * return : Bool
 * 날짜 : 2022-08-07
 * */

function isHHMM(input) {

    input = input.trim()

    let hour = ""
    let minute = ""

    if(input.length == 4 && !isNaN(parseInt(input))) {
        hour = input.substr(0,2)
        minute = input.substr(2,2)
    }else if (input.length == 5 && input.indexOf('-') != -1){
        let regexHm = /^\d{2}-\d{2}$/;
        if(!regexHm.test(input)){
            return false
        }

        let timeArr = input.split('-')
        hour = timeArr[0]
        minute = timeArr[1]

    }else {
        return false
    }

    if (hour >= 24 || hour < 0) {
        return false
    }

    if (minute >= 60 || minute < 0) {
        return false
    }

    console.log("hour: " + hour)
    console.log("minute: " + minute)

    return true
}



/*
 * 작성자 : 서은빈
 * 시간 시분초 입력 체크
 * param : String (hh-mm-ss or hhmmss)
 * return : Bool
 * 날짜 : 2022-08-07
 * */

function isHHMMSS(input) {

    input.trim()

    let hour = ""
    let minute = ""
    let second = ""

    if(input.length == 6 && !isNaN(parseInt(input))) {
        hour = input.substr(0,2)
        minute = input.substr(2,2)
        second = input.substr(4,2)
    }else if (input.length == 8 && input.indexOf('-') != -1){
        let regexHms = /^\d{2}-\d{2}-\d{2}$/;
        if(!regexHms.test(input)){
            return false
        }

        let timeArr = input.split('-')
        hour = timeArr[0]
        minute = timeArr[1]
        second = timeArr[2]

    }else {
        return false
    }

    if (hour >= 24 || hour < 0) {
        return false
    }

    if (minute >= 60 || minute < 0) {
        return false
    }

    if (second >= 60 || second < 0) {
        return false
    }

    console.log("hour: " + hour)
    console.log("minute: " + minute)
    console.log("second: " + second)

    return true
}

    /*
     * 작성자 : 주한나
     * Date 타입을 String 타입으로 변환
     * param : Date
     * return : String
     * 날짜 : 2022-08-08
     * */
function dateToStr(date){
        let year = date.getFullYear();
        let month = date.getMonth()+1;
        let day = date.getDate();

        if(month < 10) month = "0"+month;
        if(day < 10) day = "0"+day;

        return year+""+month+""+day;
    };


    /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd)의 요일 반환(kor/eng)
     * param : String
     * return : String
     * 날짜 : 2022-08-18
     * */
function getDay(date,type){
        let weekDay = (type == 'kor')? ['일', '월', '화', '수', '목', '금', '토'] : ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
        let inputDay = this.strToDate(date)
        let week = weekDay[inputDay.getDay()];

        return week;
    };


    /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd)가 윤년인지 검사
     * param : String
     * return : boolean
     * 날짜 : 2022-08-06
     * */
function isLeapYear(date){
        if(date.length != 8){return "Invalid Date"}

        let year = date.substring(0,4);
        if (year%4 == 0){
            if (year%100 == 0){
                return (year%400 == 0);
            }else{
                return true;
            }
        }else{
            return false;
        }
    };


    /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd)의 해당 달의 시작 날짜(yyyyMMdd)반환
     * param : String
     * return : String
     * 날짜 : 2022-08-06
     * */
function firstDate(date){
        if(date.length != 8){return "Invalid Date"}
        return date.substring(0,4)+date.substring(4,6)+"01";
    };


    /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd) 해당 달의 마지막 날짜(yyyyMMdd) 반환
     * param : String
     * return : String
     * 날짜 : 2022-08-06
     * */
function lastDate(date){
        if(date.length != 8){return "Invalid Date"}

        let yyyy = date.substring(0,4)
        let mm = date.substring(4,6)

        let inputDay = new Date(yyyy, mm, 0);

        return this.dateToStr(inputDay);
    };


    /*
     * 작성자 : 주한나
     * 오늘로부터 X일 뒤의 날짜 계산
     * param : Number
     * return : String
     * 날짜 : 2022-08-09
     * */
function addDate(days){
        let today = new Date();
        let resultDay = new Date(today.getFullYear(),today.getMonth(),today.getDate()+Number(days));

        return this.dateToStr(resultDay);
    };


    /*
     * 작성자 : 주한나
     * 오늘로부터 X일 전의 날짜 계산
     * param : Number
     * return : String
     * 날짜 : 2022-08-09
     * */
function minusDate(days){
        let today = new Date();
        let resultDay = new Date(today.getFullYear(),today.getMonth(),today.getDate()-Number(days));

        return this.dateToStr(resultDay);
    };


    /*
     * 작성자 : 주한나
     * 입력날로부터 X일 뒤의 날짜 계산
     * param : String, Number
     * return : String
     * 날짜 : 2022-08-09
     * */
function addDayFromInputDay(date, days){
        let inputDay = this.strToDate(date);
        let resultDay = new Date(date.substring(0,4), date.substring(4,6)-1, inputDay.getDate()+Number(days));

        return this.dateToStr(resultDay);
    };


    /*
     * 작성자 : 주한나
     * 입력날로부터 X일 전의 날짜 계산
     * param : String
     * return : String
     * 날짜 : 2022-08-09
     * */
function minusDayFromInputDay(date, days){
        let inputDay = this.strToDate(date);
        let resultDay = new Date(date.substring(0,4), date.substring(4,6)-1, inputDay.getDate()-Number(days));

        return this.dateToStr(resultDay);
    };


    /*
     * 작성자 : 주한나
     * 입력날로부터 X달 후의 날짜 계산
     * param : String
     * return : String
     * 날짜 : 2022-08-09
     * */
function addMonthFromSomeDay(date, months){
        let inputDay = this.strToDate(date);
        let resultDay = new Date(date.substring(0,4), inputDay.getMonth()+months, date.substring(6,8));

        let year = resultDay.getFullYear();
        let month = resultDay.getMonth()+1;

        //입력 날이랑 더한 날이 다르면 마지막날로 강제 셋팅
        //ex)22년 8월 31일 + 6달 => 23년 2월 28일
        if(inputDay.getDate() != resultDay.getDate()){
            month = month-1 //더했을때 날짜끼리 다르면 월이 넘어간거라 -1해줌
            resultDay = new Date(year, month, 0);
        }

        let day = resultDay.getDate();

        if(month < 10) month = "0"+month;
        if(day < 10) day = "0"+day;

        return year+""+month+""+day;
    };


    /*
     * 작성자 : 주한나
     * 입력받은 두 날짜 간 일수 계산
     * param : String
     * return : Number
     * 날짜 : 2022-08-17
     * */
function diffDay(date1, date2){
        let day1 = this.strToDate(date1).getTime();
        let day2 = this.strToDate(date2).getTime();

        let resultDay = Math.abs((day2-day1)/(1000*60*60*24));//양수처리

        return resultDay;
    };


    /*
     * 작성자 : 주한나
     * 입력받은 두 날짜 간 월수 계산
     * param : String
     * return : Number
     * 날짜 : 2022-08-17
     * */
function diffMonth(date1, date2){
        //입력 달의 1일로 계산
        let day1 = this.strToDate(this.firstDate(date1))
        let day2 = this.strToDate(this.firstDate(date2))
        let resultMonth = Math.round((day2-day1)/(1000*60*60*24*30));// 반올림처리

        resultMonth = Math.abs(resultMonth);//양수만 나오게 처리

        return resultMonth;
    };


    /*
     * 작성자 : 주한나
     * data 타입 반환
     * return : String (ex) 'number', 'string', 'object', 'boolean', 'undefined'
     * 날짜 : 2022-08-16
     * */
function getType(data){
       return (typeof data).toLowerCase();
     };


    /*
     * 작성자 : 주한나
     * data 가 object 인지 확인
     * return : boolean
     * 날짜 : 2022-08-16
     * */
function isObject(data){
       return this.getType(data) === "object" && data !== null;
     };


    /*
     * 작성자 : 주한나
     * 입력값을 String 으로 변환
     * return : String
     * 날짜 : 2022-08-12
     * */
function toString(data){
       return this.isObject(data)? JSON.stringify(data) : data+"";
     };

 /*
 * 작성자 : 서은빈
 * 숫자 범위 사이의 난수 반환
 * param : Number 1 ~ Number 2 (정수)
 * return : Number (정수)
 * 날짜 : 2022-08-14
 * */

function getRandomNum(min, max) {
    let randomNum = Math.random() * (max - min + 1) + min
	return Math.floor(randomNum)
}


 /*
 * 작성자 : 서은빈
 * 문자열의 좌측에서 지정 길이 반환
 * param : String, Number (정수)
 * return : String
 * 날짜 : 2022-08-14
 * */

function substrLeft(str, length) {
	return str.substr(0, length);
}


 /*
 * 작성자 : 서은빈
 * 문자열 포함 여부 확인
 * param : String
 * return : Bool
 * 날짜 : 2022-08-14
 * */

function chkSpecialChar(str) {
    let specialRule = /[`~!@#$%^&*|\\\'\";:₩/?]/ig;
    if(specialRule.test(str)) {
    	return true
    }else {
        return false
    }
}

 /*
 * 작성자 : 서은빈
 * 난수 값 반환
 * param :
 * return : String
 * 날짜 : 2022-08-21
 * */

 function randomString() {
    return Math.random().toString(36).substr(2,11);
 }


 /*
 * 작성자 : 서은빈
 * 글 작성 게시판 종류 반환
 * param :
 * return : String
 * 날짜 : 2022-08-26
 * */

 function getBoardNm() {

    const BoardNmProps = {
        QA: "qa",
        TIP: "tip",
        RECM: "recm",
    };
    Object.freeze(BoardNmProps);

    const pathNm = document.location.pathname.split('/')[1].toUpperCase().replace("BOARD", "")

    switch (pathNm) {
        case "QA":
          console.log("QA")
          return BoardNmProps.QA
          break;
        case "TIP":
          console.log("TIP")
          return BoardNmProps.TIP
          break;
        case "MATZIP":
        case "UPDRECM":
          console.log("RECM")
          return BoardNmProps.RECM
          break;
        case "UPDTIP":
          console.log("UPDTIP")
          return BoardNmProps.TIP
          break;
        default:
          console.log('pathNm is not defined');
      }
 }