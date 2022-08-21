// foodDetail

    let writer = document.getElementsByClassName("writer")

    Array.from(writer).forEach(function(element) {
        element.addEventListener('click', function(e) {
            if(e.target.nextElementSibling.style.display === 'block'){
                e.target.nextElementSibling.style.display = 'none';
            }else{
                e.target.nextElementSibling.style.display = 'block';
            }
        });
    });

    // 데이터 적재
    const matzip_obj = JSON.parse(document.getElementById('matzip_data').innerText)
    const matzip_id = matzip_obj.id;
    document.getElementById('food-info-addr').innerText = matzip_obj.address_name
    document.getElementById('food-info-title').innerText = matzip_obj.place_name;
    document.getElementById('info-wrap-title').innerText = matzip_obj.place_name;
    document.getElementById('food-info-content').innerText = matzip_obj.place_url;

    // 댓글 삭제
    let upd = document.getElementsByClassName("recm-upd")
    let del = document.getElementsByClassName("recm-del")

    function delRecm(recmId) {

       let delConfirm = confirm('삭제하시겠습니까?');

       if (delConfirm) {
            $.ajax({
                data:{"num": recmId},
                type:"GET",
                url:"/delRecm",
                success:function(data) {
                    alert('삭제 완료');
                    window.location.href = "recmCnt?id=${matzip.id}"
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
       }else {
          alert('삭제 취소');
       }
    }

    // 지도
    var xDis = matzip_obj.x
    var yDis = matzip_obj.y
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div

        mapOption = {
            center: new kakao.maps.LatLng(yDis, xDis), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커가 표시될 위치입니다
    var markerPosition  = new kakao.maps.LatLng(yDis, xDis);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    var iwContent = '<div style="padding:5px;">' + matzip_obj.place_name +  '<br> <a href="' + matzip_obj.place_url + '" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(yDis, xDis); //인포윈도우 표시 위치입니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        position : iwPosition,
        content : iwContent
    });

    // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
    infowindow.open(map, marker);

    let starCnt = document.querySelectorAll('.inner-star').length
    let starGrp = document.querySelectorAll('.inner-star')

    for (let z of starGrp ) {
            let ratingPercentage = z.getInnerHTML() / 5 * 100
            let ratingRounded = ratingPercentage + '%'
            z.style.width = ratingRounded
    }

    // 폐업 신고

    function reportClosed(){

       let reptConfirm = confirm('업체 폐업 신고를 하시겠습니까?');

       if (reptConfirm) {
            $.ajax({
                data:{"matzip_id": matzip_id},
                type:"GET",
                url:"/reportClosed",
                success:function(data) {
                    alert('정상 신고 처리 되었습니다. 관리자 확인 뒤 폐업처리됩니다.');
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
       }else {
          alert('삭제 취소');
       }


    }

