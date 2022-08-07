<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="content-wrap ">
            <div id="contents" class="matzip-write">

            <input type="text" size="210" id="writer" value="${member.id}" placeholder="아이디입니다" required>

            <div class="content-inner-box">
                <select name="star" id="star">
                     <option value="1">☆☆☆☆☆</option>
                     <option value="2">★☆☆☆☆</option>
                     <option value="3" selected>★★★☆☆</option>
                     <option value="4">★★★★☆</option>
                     <option value="5">★★★★★</option>
                </select>

                 <select name="category" id="category">
                     <option value="인덕원">인덕원</option>
                     <option value="회현">회현</option>
                     <option value="을지로">을지로</option>
                 </select>

                <input type="text" size="210" id="matzip-name" class= "none" placeholder="맛집 이름" required>
                <input type="text" size="210" class="keyword" id='keyword' placeholder="키워드" required>

                 <div class="button-wrap">
                    <input type="button" value="주소 검색" onclick="findKeyword()">
                </div>

            </div>


            <input type="text" size="210" id="subject" placeholder="제목을 입력하세요" required>
            <input type="text" size="210" id="matzip_upload_data" placeholder="맛집데이터" required>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
               <input type="button" value="등록" onclick="matzipSubmit()">
               <input type="button" value="취소" onclick="cancel()">
            </div>
        </div>
    </div>

    <%-- 맛집 검색 --%>
	<div id="matzipPopupBox" class="popup-wrap page-center none">
	    <div class="signUp-popup-wrap" style="min-width: 450px">
	    <p class="close" onclick="popupManger.popupClose()"></p>
	    <h2 style="text-align: center; margin-bottom: 1rem">맛집검색</h2>
		    <form action="" id="popup-content_form" onSubmit="return false;">
		    	<ul id="matzipList" style="border: 1px solid black; height: 500px; overflow-y: scroll;"></ul>
		        <div class="signUp-confirm-Button-wrap">
		            <button type="submit" onclick="popupManger.popupConfirm(form)" id="confirm_btn" name="confirm_btn">확인</button>
		        </div>
	        </form>
		</div>
	</div>

    <script>
	    const popupManger = {
				originalData : '',
				callback     : () => {},
				popupConfirm : function(e){
				    debugger;
					var formData1 = new FormData(e);
			    	this.popupClose();
			    	this.selectedData = formData1.get('content-radio');
			    	var index = formData1.get('content-radio').split(":")[0]
			    	const result = {
			    		selectedData   : formData1.get('content-radio').split(":")[1],
			    		resultData     : this.originalData.documents[index],
			    		originalData   : this.originalData,
			    	}
			    	debugger
			    	this.callback(result);
				},
				popupClose: function(id='matzipPopupBox'){
					const popupEl = document.getElementById(id);
			    	popupEl.classList.add('none');

				},
				popupOpen : function(id='matzipPopupBox'){
					const popupEl = document.getElementById(id);
			    	popupEl.classList.remove('none');

				},
				matzipPopupOpen : function(data,callback){
					this.originalData = data;
					this.popupOpen();
					this.callback = callback;
					const matzipListEl = document.getElementById('matzipList');
					const list = data.documents.map((item,index)=>{
						return `<li><input type="radio" id="search-\${index}" class="content-radio content-radio-checked" name="content-radio" value="\${index}:\${item.place_name}(\${item.address_name})"/><label for="search-\${index}" class="content-label">\${item.place_name}(\${item.address_name})</label></li>`;
					}).join('');
					matzipListEl.innerHTML = list;
				}
		}

    	function findKeyword() {
    		const categoryEl   = document.getElementById('category');
    		const keywordEl    = document.getElementById('keyword');
    		const matzipDataEl = document.getElementById('matzip_upload_data');
    		let keyword = `\${categoryEl.value} \${keywordEl.value}`
    		const kakaoRestApiKey = '';

    		$.ajax({
    			type : 'get',
    			url : 'https://dapi.kakao.com/v2/local/search/keyword.JSON?query='+keyword,
    			beforeSend : function(xhr){
    				xhr.setRequestHeader("Authorization", `KakaoAK \${kakaoRestApiKey}`);
    			},
    			error: function(xhr, status, error){
    				alert(error+'error');
    			},
    			success : function(data){
    		    	popupManger.matzipPopupOpen(data,(result)=>{
    		    		const keywordEl = document.getElementById('keyword');
    		    		const matzipDataEl = document.getElementById('matzip_upload_data');
    		            const keyword = document.querySelector('.content-inner-box .keyword');
    		            const matzip_name = document.querySelector('#matzip-name');

    		            matzip_name.value = result.selectedData.split("(")[0]
    		    		keywordEl.value = result.selectedData.split("(")[1].replace(")","");
    		    		matzipDataEl.value = JSON.stringify(result.resultData, null, 2);
    	                keyword.style.width = "50%"
    	                matzip_name.classList.remove('none');
    		    	});

    			},
    		});
    	}
    </script>
    <script src="${url}/resources/js/matzip.js"></script>