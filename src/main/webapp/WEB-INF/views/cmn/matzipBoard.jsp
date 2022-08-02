<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/tipBoard.css">
    <link rel="stylesheet" href="resources/css/foodDetail.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<style>
	.content-radio {
		display: none;
	}
	
	.content-radio-checked:checked+label {
		background-color: #ebebeb;
	}
	
	.content-label {
		display: block;
		padding: 0.75rem;
		cursor: pointer;
	}
</style>

<body>
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
                <input type="text" size="210" class="address" id='keyword' placeholder="키워드" required>

                 <div class="button-wrap">
                    <input type="button" value="주소 검색" onclick="findKeyword()">
                </div>

            </div>


            <input type="text" size="210" id="subject" placeholder="제목을 입력하세요" required>
            <input type="text" size="210" id="matzip_data" placeholder="맛집데이터" required>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
               <input type="button" value="등록" onclick="submit()">
               <input type="button" value="취소" onclick="cancel()">
            </div>
        </div>
    </div>
    
    <%-- 맛집 검색 --%>
	<div id="matzipPopupBox" class="popup-wrap page-center none">
	    <div class="signUp-popup-wrap" style="min-width: 450px">
	    <p class="close" onclick="popupClose()"></p>
	    <h2 style="text-align: center; margin-bottom: 1rem">맛집검색</h2>
		    <form action="" id="popup-content_form" onSubmit="return false;">
		    	<ul id="matzipList" style="border: 1px solid black; height: 500px; overflow-y: scroll;"></ul>
		        <div class="signUp-confirm-Button-wrap">
		            <button type="submit" onclick="popupManger.popupConfirm(form)" id="signup_btn" name="signup_btn">확인</button>
		        </div>
	        </form>
		</div>
	</div>
    
    <script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    <script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>
    <script>
        const { Editor } = toastui;
        const { colorSyntax } = Editor.plugin;

        const editor = new Editor({
                 el: document.querySelector('#editor'),
                 height: '749px',
                 initialEditType: 'markdown',
                 previewStyle: 'vertical',
                 placeholder: '📌욕설이나 비방, 모욕, 선정성이 존재하는 사진이나 게시글은 업로드하지 말아주세요📌',
                 plugins: [colorSyntax]
            });
    </script>
    <script>
        function submit(){
            const writer = document.getElementById('writer').value;
            const subject = document.getElementById('subject').value;
            const content = editor.getHTML();
            const star = document.getElementById('star').value;

            const matzip_data = document.getElementById('matzip_data').value;
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
    </script>
    <script>
	    const popupManger = {
				originalData : '',
				callback     : () => {},
				popupConfirm : function(e){
					var formData1 = new FormData(e); 
			    	this.popupClose();
			    	this.resultData = formData1.get('content-radio');
			    	const result = {
			    		resultData   : formData1.get('content-radio'),
			    		originalData : this.originalData
			    	}
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
						return `<li><input type="radio" id="\${index}" class="content-radio content-radio-checked" name="content-radio" value="\${item.place_name}(\${item.address_name})"/><label for="\${index}" class="content-label">\${item.place_name}(\${item.address_name})</label></li>`;
					}).join('');
					matzipListEl.innerHTML = list;
				}	
		}
    
    	function findKeyword() {
    		const categoryEl   = document.getElementById('category');
    		const keywordEl    = document.getElementById('keyword');
    		const matzipDataEl = document.getElementById('matzip_data');
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
    		    		const matzipDataEl = document.getElementById('matzip_data');
    		            const address = document.querySelector('.content-inner-box .address');
    		            const matzip_name = document.querySelector('#matzip-name');
    		            
    		            matzip_name.value = result.resultData.split("(")[0]
    		    		keywordEl.value = result.resultData.split("(")[1].replace(")","");
    		    		matzipDataEl.value = JSON.stringify(result.originalData, null, 2);
    	                address.style.width = "50%"
    	                matzip_name.classList.remove('none');
    		    	});
    				
    			},
    		});
    	}
    </script>
</body>
</html>