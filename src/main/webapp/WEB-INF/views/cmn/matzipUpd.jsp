<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/tipBoard.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
    <div class="content-wrap">

        <p><input type="text" size="210" id='num' value="${recm.num}"  required></p>
        <p><input type="text" size="210" id='writer' value="${recm.writer}" required></p>
        <p><input type="text" size="210" id='subject' value="${recm.subject}" required></p>

        <p><select name="star" id='star'>
             <option value="" selected disabled hidden> ${recm.star} </option>
             <option value="1">☆☆☆☆☆</option>
             <option value="2">★☆☆☆☆</option>
             <option value="3">★★★☆☆</option>
             <option value="4">★★★★☆</option>
             <option value="5">★★★★★</option>
        </select></p>

         <p><select name="category" id='category'>
             <option value="" selected disabled hidden> ${recm.refMatzip} </option>
             <option value="인덕원">인덕원</option>
             <option value="회현">회현</option>
             <option value="을지로">을지로</option>
         </select></p>

        <p><input type="text" size="210" id='matzip_data' value="${recm.refMatzip}" required></p>

        <div>내용</div>
        <div id="contents">
            <div id="editor">${recm.content}</div>
            <div id="viewer"></div>
        </div>

        <div class="button-wrap">
            <input type="button" value="등록" onclick="submit()">
            <input type="button" value="취소" onclick="cancel()">
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
                const num = document.getElementById('num').value;
                const subject = document.getElementById('subject').value;
                const content = editor.getHTML();
                const star = document.getElementById('star').value == "" ? ${recm.star} : document.getElementById('star').value;

                debugger;

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
        </script>
</body>
</html>