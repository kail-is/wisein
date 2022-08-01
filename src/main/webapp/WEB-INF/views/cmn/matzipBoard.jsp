<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/tipBoard.css">
    <link rel="stylesheet" href="resources/css/foodDetail.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
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

            <input type="text" size="210" id="matzip_data" onchange="matzipDataSet(this)" placeholder="맛집데이터" required>

            <div id="editor"></div>
            <div id="viewer"></div>

            <div class="button-wrap">
               <input type="button" value="등록" onclick="submit()">
               <input type="button" value="취소" onclick="cancel()">
            </div>
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

        function submit(){
            const writer = document.getElementById('writer').value;
            const subject = document.getElementById('subject').value;
            const content = editor.getHTML();
            const star = document.getElementById('star').value;

            const matzip_data = document.getElementById('matzip_data').value;
            const matzip_obj = JSON.parse(matzip_data);
            debugger;
            const matzip_id = matzip_obj.documents[0].id;

            debugger;

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
    	
    	function findKeyword() {
    		
    		const categoryEl   = document.getElementById('category');
    		const keywordEl    = document.getElementById('keyword');
    		const matzipDataEl = document.getElementById('matzip_data');
    		let keyword = `\${categoryEl.value} \${keywordEl.value}`
    		
    		$.ajax({
    			type : 'get',
    			url : 'https://dapi.kakao.com/v2/local/search/keyword.JSON?query='+keyword,
    			beforeSend : function(xhr){
    				xhr.setRequestHeader("Authorization", "KakaoAK {REST_API_KEY}");
    			},
    			error: function(xhr, status, error){ 
    				alert(error+'error'); 
    			},
    			success : function(data){
    				matzipDataEl.value = JSON.stringify(data);
    			},
    		});
    	}
    </script>
</body>
</html>