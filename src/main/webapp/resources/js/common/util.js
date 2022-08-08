
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
  };


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
     * 주어진 날짜(yyyyMMdd) 윤년인지를 검사
     * 날짜 : 2022-08-06
     * */
function isLeapYear(day){
        var year = day.substring(0,4);
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
     * 주어진 날짜(yyyyMMdd)의 그 달의 마지막 날짜(yyyyMMdd) 반환
     * 날짜 : 2022-08-06
     * */
function lastDate(day){
        var yyyyMMdd = String(day);
        var days = "31";
        var year = yyyyMMdd.substring(0,4);
        var month = yyyyMMdd.substring(4,6);

        if (Number(month) == 2){
            if (isLeapYear(year+month+"01"))
                days = "29";
            else
                days = "28";
        }else if (Number(month) == 4 || Number(month) == 6 || Number(month) == 9 || Number(month) == 11){
            days = "30";
        }
        return year+month+days;
    };

 /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd)의 달의 시작 날짜(yyyyMMdd)반환
     * 날짜 : 2022-08-06
     * */
function firstDate(day){
        var yyyyMMdd = String(day);
        var year = yyyyMMdd.substring(0,4);
        var month = yyyyMMdd.substring(4,6);
        return year+month+"01";
    };

 /*
     * 작성자 : 주한나
     * Datetime 형식으로 변환
     * 날짜 : 2022-08-06
     * */
function dateTime(day){
        var yyyyMMdd = String(day);
        var sYear = yyyyMMdd.substring(0,4);
        var sMonth = yyyyMMdd.substring(4,6);
        var sDate = yyyyMMdd.substring(6,8);

        return new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
    };
