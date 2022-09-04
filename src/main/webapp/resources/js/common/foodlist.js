let x, y, companyLength, localCheck, marker;
let createDiv = document.createElement('div');
let createDivBefore = document.querySelector('.button-wrap');

//const commonPage = {
//    totalCount : null,
//    recordPerPage : 10,
//    navPage : 10,
//    firstPage : 1,
//    lastPage : null,
//}


window.addEventListener('DOMContentLoaded', function(){
    fetch("/companyList")
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(companyList => {
            companyLength = companyList.company.length;
            for (let i=0; i<companyList.company.length; i++) {
                createDiv.innerHTML += "<div id='list' onclick='matzipLoc(&quot;" + companyList.company[i].location+ "&quot;,&quot;" + companyList.company[i].companyLoc+ "&quot;,&quot;" + companyList.company[i].id+ "&quot;)' class='board-line'><div style='width:300px;' class='board-cell board-category purple2'>"
                            +companyList.company[i].location+"</div>"
                            +"<div class='board-cell board-title'>"
                            +companyList.company[i].companyName+"</div>"
                            +"<div class='board-cell board-map purple'><span class='material-icons'>map</span>"
                            +companyList.company[i].matzipCount+"</div></div>";
            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);
        });
});

function categorySelect(target) {
    if (target.value != "none") {
        let option = target.options[target.selectedIndex].text;

        fetch("/categorySelect?"+"option="+option)
            .then(response => response.json())
            .catch(error => console.log('Error'))
            .then(categoryList => {
                let changeText = document.getElementById('changeText')
                changeText.innerText = "맛집";

                let companyArray = Array.from(document.querySelectorAll('#list'));
                    for (let i=0; i<companyArray.length; i++) {
                        companyArray[i].remove();
                    }
                for (let i=0; i<categoryList.company.length; i++) {
                    createDiv.innerHTML += "<div id='list' onclick='matzipLoc(&quot;" + categoryList.company[i].location+ "&quot;,&quot;" + categoryList.company[i].companyLoc+ "&quot;,&quot;" + categoryList.company[i].id+ "&quot;)' class='board-line'><div style='width:300px;' class='board-cell board-category purple2'>"
                                +categoryList.company[i].location+"</div>"
                                +"<div class='board-cell board-title'>"
                                +categoryList.company[i].companyName+"</div>"
                                +"<div class='board-cell board-map purple'><span class='material-icons'>map</span>"
                                +categoryList.company[i].matzipCount+"</div></div>";
                }
                createDivBefore.insertAdjacentElement('beforebegin', createDiv);
            });
    }
}

function matzipLoc(location,address,id) {

    let companyArray = Array.from(document.querySelectorAll('#list'));
    for (let i=0; i<companyArray.length; i++) {
        companyArray[i].remove();
    }

    fetch("/foodList?"+"location="+location)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(matzipList => {
            for (let i=0;i<matzipList.matzip.length; i++) {
                createDiv.innerHTML += "<div id='list' onclick='selectFoodLocal(&quot;" + matzipList.matzip[i].companyLoc+ "&quot;,&quot;" + matzipList.matzip[i].id+ "&quot;)' class='board-line'><div style='width:300px;' class='board-cell board-category purple2'>"
                    +matzipList.matzip[i].location+"</div>"
                    +"<div class='board-cell board-title'>"
                    +matzipList.matzip[i].companyName+"</div>"
                    +"<div class='board-cell board-map purple'><span class='material-icons'>map</span>"
                    +matzipList.matzip[i].matzipCount+"</div></div>";
            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);

            selectFoodLocal(address, id);

        })

}

function selectFoodLocal(local, id) {

    localExistCheck(id);

    let changeText = document.getElementById('changeText')
    changeText.innerText = "추천수";

    fetch("/lateChange?"+"local="+local)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(late => {
            let lateRst = JSON.parse(late.local.toString().replace(/&quot;/g, '"'));
            x = lateRst.documents[0].x;
            y = lateRst.documents[0].y;

            panTo(lateRst.documents[0].address_name);
        })
}

var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.37947804818484, 127.11415037150388),
        level: 3,
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

function panTo(loc) {
    var moveLatLon = new kakao.maps.LatLng(y, x);
    map.panTo(moveLatLon);
    localCheckPoint(loc);
}

//상세 맛집 페이지 이동
function localCheckPoint(loc) {
    var markerPosition = new kakao.maps.LatLng(y, x);

    marker = new kakao.maps.Marker({
        position: markerPosition,
        clickable: true
    });

    marker.setMap(map);


    kakao.maps.event.addListener(marker, 'click', function() {

        if (localCheck==1) {
            fetch("/matzipDetailId?"+"loc="+loc)
                .then(response => response.json())
                .catch(error => console.log('Error'))
                .then(matzipId => {
                    let url = "http://localhost:8080/matzip?id="+matzipId;
                    location.href = url;
                });
        }
    });
}

//좌표 이동 막기
function localExistCheck(id) {
    fetch("/matzipCheck?"+"id="+id)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(existCheck => {
            localCheck = existCheck;
        });
}

















