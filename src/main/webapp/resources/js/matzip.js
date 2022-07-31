// matzipBoard - func

    function matzipDataSet(obj) {
    // TODO - api 통신 이후 변경
       const address = document.querySelector('.content-inner-box .address');
       const matzip_name = document.querySelector('#matzip-name');
       if (obj.value.length > 0) {
            address.style.width = "50%"
            matzip_name.classList.remove('none');
       }else {
            address.style.width = "70%"
            matzip_name.classList.add('none');
       }
    }

    function matzipSubmit(){
        const writer = document.getElementById('writer').value;
        const subject = document.getElementById('subject').value;
        const content = editor.getHTML();
        const star = document.getElementById('star').value;

        const matzip_data = document.getElementById('matzip_upload_data').value;
        const matzip_obj = JSON.parse(matzip_data);
        const matzip_id = matzip_obj.documents[0].id;

        $.ajax({
            data:{ "writer": writer, "subject":subject,"content":content, "star":star, "matzipData": matzip_data, "matzipId": matzip_id},
            type:"GET",
            url:"/regMatzip",
            success:function(data) {
                alert("성공");
                window.location.href = "/matzipList"
            },
            error:function(request, status, error) {
                alert("실패");
            }
        })
    }


    function matzipUpd(updStar){

        const num = document.getElementById('num').value;
        const subject = document.getElementById('subject').value;
        const content = editor.getHTML();
        const star = document.getElementById('star').value == "" ? updStar : document.getElementById('star').value;

        $.ajax({
            data:{"num": num, "subject":subject, "content":content, "star":star},
            type:"GET",
            url:"/putRecm",
            success:function(data) {
                alert("성공");
                window.location.href = "/matzipList"
            },
            error:function(request, status, error) {
                alert("실패");
            }
        })
    }

// matzipBoard - editor

     document.getElementById('matzip_upload_data').value =
     `{
        "documents": [
          {
            "address_name": "서울 중구 회현동1가 206",
            "category_group_code": "FD6",
            "category_group_name": "음식점",
            "category_name": "음식점 > 아시아음식 > 동남아음식 > 베트남음식",
            "distance": "",
            "id": "1710196369",
            "phone": "02-318-7768",
            "place_name": "홍대쌀국수 회현점",
            "place_url": "http://place.map.kakao.com/1710196369",
            "road_address_name": "서울 중구 퇴계로 72",
            "x": "126.980640745963",
            "y": "37.5591237285706"
          }
        ],
        "meta": {
          "is_end": true,
          "pageable_count": 1,
          "same_name": {
            "keyword": "홍대쌀국수 회현점",
            "region": [],
            "selected_region": ""
          },
          "total_count": 1
        }
      }`;


