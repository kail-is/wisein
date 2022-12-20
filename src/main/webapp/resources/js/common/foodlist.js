let marker;
let createDiv = document.createElement('div');
let pageDiv = document.querySelector('#page');
let coordinate;
let type;
let clickOverlay = null;

let mapContainer = document.getElementById('map')
    mapOption = {
        center: new kakao.maps.LatLng(37.37947804818484, 127.11415037150388),
        level: 5,
    };

let map = new kakao.maps.Map(mapContainer, mapOption);

//맛집 로드시 회사리스트 get
//window.addEventListener('DOMContentLoaded', function(){
function init() {
    let companyArray = Array.from(document.querySelectorAll('#list'));
    for (let i=0; i<companyArray.length; i++) {
        companyArray[i].remove();
    }
    type = "company";
        fetch("/companyList")
            .then(response => response.json())
            .catch(error => console.log("Error"))
            .then(companyList => {
                setDataList(companyList, type);
            })
}
//});

//카테고리 선택
function foodCategorySelect(target) {
    console.log(target.value);
    type = "company";
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
                setDataList(categoryList, type)
            });
    }
}

//맛집 리스트
function getMatzipList(location, currentPage) {
    let pageBlock = 5;
    let dataPerPage = 5;

    type = "matzip";
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
            setDataList(matzipList, type);

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

//데이터 set
function setDataList(dataList, type) {
    let createDivBefore = document.querySelector('.button-wrap');
    let args = arguments;
    let listText;

    if (dataList.list.length == 0) {
        if (args[1] == "company") {
            listText = "등록된 파견사이트가 없습니다.";
        } else {
            listText = "등록된 맛집이 없습니다.";
        }
        createDiv.innerHTML
            += "<div id='list' class='matzip-list-line' style='height:50px;justify-content:center;align-items:center;';>"
            + listText
            + "</div>";
    } else {
        if (args[1] == "company") {
            for (let i=0; i<dataList.list.length; i++) {
                createDiv.innerHTML
                    += "<div id='list' class='matzip-list-line'>"
                    + "<div style='width:300px;' class='matzip-list-cell matzip-category purple2'>"
                    + dataList.list[i].location+"</div>"
                    + "<div onclick='getMatzipList(&quot;" + dataList.list[i].location+"&quot;)' class='matzip-list-cell food-list-title'>"
                    + dataList.list[i].companyName+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><span class='material-icons'>map</span>"
                    + dataList.list[i].matzipCount+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><button onclick='checkLocal(&quot;" + dataList.list[i].companyLoc+ "&quot;,&quot;" + dataList.list[i].companyName+ "&quot;,&quot;" + dataList.list[i].id+ "&quot;,&quot;company&quot;)' class='material-icons-outlined purple'>where_to_vote</button></div>"
                    + "<div class='matzip-list-cell board-map purple'><button class='material-icons-outlined purple' onclick='roadView(&quot;" + dataList.list[i].companyLoc + "&quot;)'>visibility</button><div></div>";
            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);
        } else if (args[1] == "matzip") {
            for (let i=0;i<dataList.list.length; i++) {
                createDiv.innerHTML
                    += "<div id='list' class='matzip-list-line'>"
                    + "<div style='width:300px;' class='matzip-list-cell matzip-category purple2'>"
                    + dataList.list[i].location+"</div>"
                    + "<div onclick='detailPage(&quot;" + dataList.list[i].id+ "&quot;)' class='matzip-list-cell food-list-title'>"
                    + dataList.list[i].companyName+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><span class='material-icons'>map</span>"
                    + dataList.list[i].matzipCount+"</div>"
                    + "<div class='matzip-list-cell board-map purple'><button onclick='checkLocal(&quot;" + dataList.list[i].companyLoc+ "&quot;,&quot;" + dataList.list[i].companyName+ "&quot;,&quot;" + dataList.list[i].id+ "&quot;,&quot;matzip&quot;)' class='material-icons-outlined purple'>where_to_vote</button></div>"
                    + "<div class='matzip-list-cell board-map purple'><button class='material-icons-outlined purple' onclick='roadView(&quot;" + dataList.list[i].companyLoc + "&quot;)'>visibility</button>" + "<div></div>";

            }
            createDivBefore.insertAdjacentElement('beforebegin', createDiv);
        }
    }
}

async function getLocalLatitue(location) {
    let coordinateObj = {};
    await fetch("/lateChange?"+"local="+location)
        .then(response => response.json())
        .catch(error => console.log('Error'))
        .then(late => {
            let lateRst = JSON.parse(late.local.toString().replace(/&quot;/g, '"'));
            coordinateObj.latitude = lateRst.documents[0].x;
            coordinateObj.longitude = lateRst.documents[0].y;
        });
    return coordinateObj;
}

//마커 찍기
async function checkLocal(location, companyName, id, type) {
    let content, overlayText;

    coordinate = await getLocalLatitue(location);

    var moveLatLon = new kakao.maps.LatLng(coordinate.longitude, coordinate.latitude);
    map.panTo(moveLatLon);

    marker = new kakao.maps.Marker({
        position: moveLatLon,
        clickable: true
    });

    marker.setMap(map);

    kakao.maps.event.addListener(marker, 'click', function() {
        if (clickOverlay) {
            clickOverlay.setMap(null);
        }

        marker = new kakao.maps.Marker({
            position: moveLatLon,
            clickable: true
        });

        customOverlay = new kakao.maps.CustomOverlay({
            map: map,
            position: marker.getPosition()
        });

        if (type=="matzip") {
            overlayText = '<div class="detail-btn"><a href="javascript:void(0);"  onclick="detailPage(\''+id+'\');">상세보기</a></div>'
        } else {
            overlayText = '';
        }
        content = '<div class="wrap food-wrap">' +
        '    <div class="info food-wrap">' +
        '        <div class="title food-wrap">' +
        companyName+
        '            <div class="close-btn food-wrap" onclick="closeOverlay()" title="닫기"></div>' +
        '        </div>' +
        '        <div class="body food-wrap">' +
        '            <div class="desc">' +
        '                <div class="ellipsis">'+location+'</div>' +
        ''                 +overlayText+
        '            </div>' +
        '        </div>' +
        '    </div>' +
        '</div>';

        customOverlay.setContent(content);

        customOverlay.setMap(map);
        clickOverlay = customOverlay;
    });
}

//상세 페이지 이동
function detailPage(id) {
    id = Number(id);
    if (typeof id == "number") {
        let url = "http://" + document.location.host + "/matzip?id="+id;
        location.href = url
    } else {
        return;
    }
}

//커스텀 오버레이 닫기
function closeOverlay() {
    customOverlay.setMap(null);
}

//로드뷰 표시
async function roadView(local) {
    coordinate = await getLocalLatitue(local);

    let roadviewContainer = document.getElementById('roadview');
    let roadview = new kakao.maps.Roadview(roadviewContainer);
    let roadviewClient = new kakao.maps.RoadviewClient();

    let position = new kakao.maps.LatLng(coordinate.longitude, coordinate.latitude);

    roadviewClient.getNearestPanoId(position, 50, function(panoId) {
        if (panoId==null) {
            commonPopup.alertPopup("해당 지역의 로드뷰를 볼 수 없습니다!");
        } else {
            let closeBtn = document.querySelector('#road-close-btn');

            document.querySelector('#roadViewPopup').classList.remove('none');
            $dim();

            closeBtn.addEventListener('click', function() {
                commonPopup.close();
                $dim(false);
            });

            roadview.setPanoId(panoId, position);
        }
    });
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

init();