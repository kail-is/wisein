var x;
var y;
var pointIndex=0;
var companyLength;
var marker;

window.addEventListener('DOMContentLoaded', function(){

    $.ajax({
        url:"/companyList",
        type:"GET",
        datatype:"json",
        success:function(data) {

            companyLength = data.company.length;

            for (var i=0; i<data.company.length; i++) {
                $(".button-wrap").before("<div id='matmat' onclick='test(&quot;" + data.company[i].companyLoc+ "&quot;,&quot;" + data.company[i].location+ "&quot;)' class='board-line'><div style='width:300px;' id='local' class='board-cell board-category purple2'>"
                    +data.company[i].location+"</div>"
                    +"<div id='site' class='board-cell board-title'>"
                    +data.company[i].companyName+"</div>"
                    +"<div id='count' class='board-cell board-map purple'><span class='material-icons'>map</span>"
                    +data.company[i].matzipCount+"</div></div>");

            }
        },
        error:function(request, status, error) {
            alert("Fail");
        }
    });
});

$("#category").blur(function() {

    var place = $("#category option:selected").val()

    if (place!="") {
        $.ajax({
            url:"/selectCompany",
            data : {"place":place},
            type:"GET",
            datatype:"json",
            success:function(data) {

                for (var i=0;i<companyLength; i++) {
                    $("#matmat").remove();
                }

                $("#hello").text("맛집");

                for (var j=0; j<data.company.length; j++) {
                    $(".button-wrap").before("<div id='matmat' onclick='test(&quot;" + data.company[j].companyLoc+ "&quot;,&quot;" + data.company[j].location+ "&quot;)' class='board-line'><div style='width:300px;' id='local' class='board-cell board-category purple2'>"
                        +data.company[j].location+"</div>"
                        +"<div id='site' class='board-cell board-title'>"
                        +data.company[j].companyName+"</div>"
                        +"<div id='count' class='board-cell board-map purple'><span class='material-icons'>map</span>"
                        +data.company[j].matzipCount+"</div></div>");
                }

            },
            error:function(request, status, error) {
                alert("Fail");
            }
        });
    } else {
        return;
    }

});

//수정1
function test(local,place) {

    $.ajax({
        url:"/placeMatzip",
        data : {"place":place},
        type:"GET",
        datatype:"json",
        success:function(data) {

            for (var i=0;i<companyLength; i++) {
                $("#matmat").remove();
            }
            $("#hello").text("추천수");
            //console.log("id : "+data.company[0].id);
            for (var j=0; j<data.company.length; j++) {
                $(".button-wrap").before("<div id='matmat' onclick='selectFoodLocal(&quot;" + data.company[j].companyLoc+ "&quot;)' class='board-line'><div style='width:300px;' id='local' class='board-cell board-category purple2'>"
                    +data.company[j].location+"</div>"
                    +"<div id='site' class='board-cell board-title'>"
                    +data.company[j].companyName+"</div>"
                    +"<div id='count' class='board-cell board-map purple'><span class='material-icons'>map</span>"
                    +data.company[j].matzipCount+"</div></div>");
            }

        },
        error:function(request, status, error) {
            alert("Fail");
        }
    });
        selectFoodLocal(local);

}

function selectFoodLocal(local)  {

    $.ajax({
        url:"/lateChange",
        data : {"local":local},
        type:"GET",
        datatype:"json",
        success:function(data) {
            var locatePoint = data.local;
            local2 = JSON.parse(locatePoint);
            x = local2['documents'][0].address.x;
            y = local2['documents'][0].address.y;

            local="";
            panTo(local2['documents'][0].address.address_name);
        },
        error:function(request, status, error) {
            alert("Fail");
        }
    });
}

var infowindow = new kakao.maps.InfoWindow({zIndex:1});
var ps = new kakao.maps.services.Places();

var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

function panTo(loc) {

    var moveLatLon = new kakao.maps.LatLng(y, x);
    map.panTo(moveLatLon);
    localCheckPoint(loc);
}

function localCheckPoint(loc) {
    var markerPosition = new kakao.maps.LatLng(y, x);

    marker = new kakao.maps.Marker({
        position: markerPosition,
        clickable: true
    });

    marker.setMap(map);

    kakao.maps.event.addListener(marker, 'click', function() {

        $.ajax({
            url:"/matzipDetailId",
            data : {"loc":loc},
            type:"GET",
            datatype:"json",
            success:function(data) {

                var url = "http://localhost:8080/matzip?id="+data;
                location.href = url;
            },
            error:function(request, status, error) {
                alert("Fail");
            }
        });
    });
}



















