<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="resources/css/tipBoard.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
    <form role="form" method="post" autocomplete="off" id="tipBoard_form">
    <div class="content-wrap">

        <div>제목</div>
        <p><input type="text" size="210" id='title' name='title' placeholder="제목을 입력하세요"value="${tipListDTO.subject}" required></p>

        <div>내용</div>
        <div id="contents">
            <div id="editor"></div>
            <div id="viewer"></div>
            ${tipListDTO.content}
            <input type="hidden" id='content' name='content'>
        </div>

        <div class="button-wrap">
             <c:if test="${empty tipListDTO.subject}">
                <input type="button" value="등록" onclick="reg()">
             </c:if>
            <c:if test="${!empty tipListDTO.subject}">
                <input type="button" value="수정" onclick="update()">
            </c:if>
            <input type="button" value="취소" onclick="cancel()">
       </div>
    </div>
    </form>
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
        function reg(){
            document.querySelector("#content").value = editor.getHTML();
            document.getElementById('tipBoard_form').submit();
        }

        function update(){
         document.querySelector("#content").value = editor.getHTML();
         document.getElementById('tipBoard_form').submit();
        }


        function cancel(){
            if(confirm('진짜 취소하실꺼에여?🥺') == true){
                console.log('뒤로가기되찌롱');
                window.history.back()
            }
        }
    </script>
</body>
</html>