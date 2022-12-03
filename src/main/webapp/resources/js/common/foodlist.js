let x, y, companyLength, localCheck, marker;
let createDiv = document.createElement('div');
let createDivBefore = document.querySelector('.button-wrap');
let pageDiv = document.querySelector('#page');
let name, infoTitle, map;
let clickOverlay = null;
let content;

let totalCount;
let dataPerPage = 5;
let currentPage;
let startRow;
let endRow;
let pageBlock = 5;
let startPage;
let endPage;
let totalPage;



var mapContainer = document.getElementById('map')
    mapOption = {
        center: new kakao.maps.LatLng(37.37947804818484, 127.11415037150388),
        level: 5,
    };


map = new kakao.maps.Map(mapContainer, mapOption);

//맛집 로드시 회사리스트 get
window.addEventListener('DOMContentLoaded', function(){
    fetch("/companyList")
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(companyList => {
            for (let i=0; i<companyList.company.length; i++) {
                createDiv.innerHTML
                    += "<div id='list' class='matzip-list-line'>"
                    + "<div style='width:300px;' class='matzip-list-cell matzip-category purple2'>"
                    + companyList.company[i].location+"</div>"
                    + "<div onclick='getMatzipList(&quot;" + companyList.company[i].location+"&quot;)' class='matzip-list-cell food-list-title'>"
                    + companyList.company[i].companyName+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><span class='material-icons'>map</span>"
                    + companyList.company[i].matzipCount+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><button onclick='checkLocal(&quot;" + companyList.company[i].companyLoc+ "&quot;,&quot;" + companyList.company[i].companyName+ "&quot;,&quot;" + companyList.company[i].id+ "&quot;)' class='material-icons-outlined purple'>where_to_vote</button></div>"
                    + "<div class='matzip-list-cell board-map purple'><button class='material-icons-outlined purple' onclick='roadView(&quot;" + companyList.company[i].companyLoc + "&quot;)'>visibility</button><div></div>";
            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);
        });
});

//카테고리 선택
function foodCategorySelect(target) {
    removeAllChild(pageDiv);

    if (target.value != "none") {
        let option = target.options[target.selectedIndex].text;

        fetch("/foodCategorySelect?"+"option="+option)
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
                    createDiv.innerHTML
                        += "<div id='list' class='matzip-list-line'><div style='width:300px;' class='matzip-list-cell matzip-category purple2'>"
                        + categoryList.company[i].location+"</div>"
                        + "<div onclick='getMatzipList(&quot;" + categoryList.company[i].location+"&quot;)' class='matzip-list-cell food-list-title'>"
                        + categoryList.company[i].companyName+"</div>"
                        + "<div class='matzip-list-cell board-map purple'><span class='material-icons'>map</span>"
                        + categoryList.company[i].matzipCount+"</div>"
                        + "<div class='matzip-list-cell board-map purple'><button onclick='checkLocal(&quot;" + categoryList.company[i].companyLoc+ "&quot;,&quot;" + categoryList.company[i].companyName+ "&quot;,&quot;" + categoryList.company[i].id+ "&quot;)' class='material-icons-outlined purple'>where_to_vote</button></div>"
                        + "<div class='matzip-list-cell board-map purple' style='width:50px;'>" + "<button class='material-icons-outlined purple' onclick='roadView(&quot;" + categoryList.company[i].companyLoc + "&quot;)'>visibility</button>" + "<div></div>";
                }
                createDivBefore.insertAdjacentElement('beforebegin', createDiv);

            });
    }
}

//회사 클릭시 맛집리스트 get
function getMatzipList(location, currentPage) {
    removeAllChild(pageDiv);

    if (currentPage==null) {
        currentPage=1;
    }

    let companyArray = Array.from(document.querySelectorAll('#list'));
    for (let i=0; i<companyArray.length; i++) {
        companyArray[i].remove();
    }

    setPageRow(currentPage, dataPerPage);
    changeText.innerText = "추천수";

    fetch("/foodList", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            location: location,
            startRow : startRow,
            dataPerPage : dataPerPage,
        })
    })
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(matzipList => {
            if (matzipList.matzip.length==0) {
                createDiv.innerHTML
                    += "<div id='list' class='matzip-list-line' style='height:50px;justify-content:center;align-items:center;';>"
                    + "등록된 맛집이 없습니다."
                    + "</div>";
            }
            for (let i=0;i<matzipList.matzip.length; i++) {
                createDiv.innerHTML
                    += "<div id='list' class='matzip-list-line'><div style='width:300px;' class='matzip-list-cell matzip-category purple2'>"
                    + matzipList.matzip[i].location+"</div>"
                    + "<div onclick='localExistCheck(&quot;" + matzipList.matzip[i].companyLoc+ "&quot;,&quot;" + matzipList.matzip[i].id+ "&quot;)' class='matzip-list-cell food-list-title'>"
                    + matzipList.matzip[i].companyName+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><span class='material-icons'>map</span>"
                    + matzipList.matzip[i].matzipCount+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><button onclick='checkLocal(&quot;" + matzipList.matzip[i].companyLoc+ "&quot;,&quot;" + matzipList.matzip[i].companyName+ "&quot;,&quot;" + matzipList.matzip[i].id+ "&quot;)' class='material-icons-outlined purple'>where_to_vote</button></div>"
                    + "<div class='matzip-list-cell board-map purple' style='width:50px;'>" + "<button class='material-icons-outlined purple' onclick='roadView(&quot;" + matzipList.matzip[i].companyLoc + "&quot;)'>visibility</button>" + "<div></div>";

            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);

            fetch("/dataCount?"+"location="+location)
                .then(response => response.json())
                .catch(error => console.log('Error'))
                .then(totalCount => {
                    setTotalPage(totalCount, dataPerPage);
                    setPageBlock(currentPage, pageBlock)

                    if (endPage<totalPage) {
                        if (startPage!=1) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "《";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, 1);
                            }
                        }

                        if (startPage>1) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "〈";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, startPage-parseInt(1));
                            }
                        }

                        for (let i=startPage; i<=endPage; i++) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML += i;
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, this.innerHTML);
                            }
                        }

                        if (endPage<totalPage) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "〉";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, endPage+parseInt(1));
                            }
                        }
                        if (endPage<totalPage) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "》";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, totalPage);
                            }
                        }
                    } else if (endPage>=totalPage) {
                        if (startPage!=1) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "《";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, 1);
                            }
                        }

                        if (startPage>1) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML = "〈";
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, startPage-parseInt(1));
                            }
                        }

                        for (let i=startPage; i<=totalPage; i++) {
                            creBtn = document.createElement('button');
                            creBtn.innerHTML += i;
                            creBtn.classList.add('btn-wrap');
                            pageDiv.append(creBtn);
                            creBtn.onclick = function() {
                                getMatzipList(location, this.innerHTML);
                            }
                        }
                    }

                })

        })
}

function removeAllChild(pageDiv) {
    while (pageDiv.hasChildNodes()) {
        pageDiv.removeChild(pageDiv.firstChild);
    }
}

function setPageRow(currentPage, dataPerPage) {
    startRow = (Math.floor(currentPage - 1)*dataPerPage) + 1;
    endRow = currentPage * dataPerPage;
}

function setTotalPage(totalCount, dataPerPage) {
    totalPage = Math.floor(totalCount/dataPerPage + (totalCount%dataPerPage==0 ? 0 : 1));
}

function setPageBlock(currentPage, pageBlock) {
    startPage = Math.floor((currentPage-1)/pageBlock) * pageBlock + 1
    endPage = startPage + pageBlock - 1;
}

//마커 찍기 공통
function checkLocal(location, companyName, id) {

    fetch("/lateChange?"+"local="+location)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(late => {
            let lateRst = JSON.parse(late.local.toString().replace(/&quot;/g, '"'));
            x = lateRst.documents[0].x;
            y = lateRst.documents[0].y;

            var moveLatLon = new kakao.maps.LatLng(y, x);
            map.panTo(moveLatLon);

            var markerPosition = new kakao.maps.LatLng(y, x);

            marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true
            });

            marker.setMap(map);

            kakao.maps.event.addListener(marker, 'click', function() {
                if (clickOverlay) {
                    clickOverlay.setMap(null);
                }

                marker = new kakao.maps.Marker({
                    position: markerPosition,
                    clickable: true
                });

                customOverlay = new kakao.maps.CustomOverlay({
                    map: map,
                    position: marker.getPosition()
                });

                content = '<div class="wrap food-wrap">' +
                '    <div class="info food-wrap">' +
                '        <div class="title food-wrap">' +
                companyName+
                '            <div class="close-btn food-wrap" onclick="closeOverlay()" title="닫기"></div>' +
                '        </div>' +
                '        <div class="body food-wrap">' +
                '            <div class="desc">' +
                '                <div class="ellipsis">'+location+'</div>' +
                '                <div class="detail-btn"><a href="javascript:void(0);"  onclick="localExistCheck(\''+location+'\',\''+id+'\');">상세보기</a></div>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';
                customOverlay.setContent(content);

                customOverlay.setMap(map);
                clickOverlay = customOverlay;
            });
        })
}

//로드뷰 좌표 변환
function roadView(local) {
    fetch("/lateChange?"+"local="+local)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(late => {
            let lateRst = JSON.parse(late.local.toString().replace(/&quot;/g, '"'));
            x = lateRst.documents[0].x;
            y = lateRst.documents[0].y;

            setRoadView(x, y);
        })
}

//커스텀 오버레이 맛집 상세페이지 이동
function detailPage(loc, localCheck) {
    if (localCheck == 1) {
        fetch("/matzipDetailId?"+"loc="+loc)
            .then(response => response.json())
            .catch(error => console.log('Error'))
            .then(matzipId => {
                if (typeof matzipId != 'number') {
                    return;
                } else {
                    let url = "http://localhost:8080/matzip?id="+matzipId;
                    location.href = url;
                }
            });
    }
}

//커스텀 오버레이 닫기
function closeOverlay() {
    customOverlay.setMap(null);
}

//로드뷰 표시
function setRoadView(x, y) {
    let roadviewContainer = document.getElementById('roadview');
    let roadview = new kakao.maps.Roadview(roadviewContainer);
    let roadviewClient = new kakao.maps.RoadviewClient();

    let position = new kakao.maps.LatLng(y, x);

    roadviewClient.getNearestPanoId(position, 50, function(panoId) {
        if (panoId==null) {
            commonPopup.alertPopup("해당 지역의 로드뷰를 볼 수 없습니다!", false);
        } else {
            let closeBtn = document.querySelector('#road-close-btn');

            document.querySelector('#roadViewPopup').classList.remove('none');
            commonPopup.modalPopupCheck(true);

            closeBtn.addEventListener('click', function() {
                commonPopup.close();
                commonPopup.modalPopupCheck(false);
            });

            roadview.setPanoId(panoId, position);
        }
    });
}

//matzip board db에 맛집이 있는 지 확인
function localExistCheck(location, id) {
    fetch("/matzipCheck?"+"id="+id)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(existCheck => {
            localCheck = existCheck;
            detailPage(location, localCheck);
        });
}
















