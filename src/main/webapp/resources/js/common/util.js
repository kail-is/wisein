
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

 /*
     * 작성자 : 주한나
     * 주어진 날짜(yyyyMMdd) 윤년인지를 검사
     * 날짜 : 2022-08-06
     * */
var isLeapYear = function(day){
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
var lastDate = function(day){
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
var firstDate = function(day){
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
var dateTime = function(day){
        var yyyyMMdd = String(day);
        var sYear = yyyyMMdd.substring(0,4);
        var sMonth = yyyyMMdd.substring(4,6);
        var sDate = yyyyMMdd.substring(6,8);

        return new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
    };
