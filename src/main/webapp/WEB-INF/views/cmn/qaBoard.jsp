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

        <div>제목</div>
        <p>
            <input type="text" size="210" id='title' placeholder="제목을 입력하세요" value="${qaListDTO.subject}" required>
        </p>

        <div>내용</div>
        <div id="contents">
            <div id="editor">
                ${qaListDTO.content}
                <!--${content}-->
            </div>
            <div id="viewer"></div>
        </div>

        <!-- 신규/수정 여부 -->
        <div class="button-wrap">
            <c:if test="${empty qaListDTO.subject}">
                <input type="button" value="등록" onclick="submit()">
            </c:if>
            <c:if test="${!empty qaListDTO.subject}">
                <input type="button" value="수정" onclick="update()">
            </c:if>
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

            editor.removeHook('addImageBlobHook');

    </script>

    <script>
        function submit(){
            const subject = document.getElementById('title').value;
            const content = editor.getHTML();

            $.ajax({
                data:{"subject":subject,"content":content},
                type:"POST",
                url:"/regQaBoard",
                success:function(data) {
                    alert("성공");
                    window.location.href = "/qalist"
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
        }
    </script>
    <script>
        function update(){
            const num = ${qaListDTO.num};
            const subject = document.getElementById('title').value;
            const content = editor.getHTML();

            $.ajax({
                data:{"num":num,"subject":subject,"content":content},
                type:"POST",
                url:"/qaUpdatePro",
                success:function(data) {
                    alert("성공");
                    window.location.href = "/qaDetail?num=${qaListDTO.num}"
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
        }
    </script>
    <script>
        function cancel(){
            if(confirm('진짜 취소하실꺼에여?🥺') == true){
                console.log('뒤로가기되찌롱');
                window.history.back()
            }
        }
    </script>
</body>
</html>