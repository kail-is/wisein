
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
