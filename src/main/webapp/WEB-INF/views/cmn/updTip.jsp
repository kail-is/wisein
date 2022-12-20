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
        <input type="hidden" id='num' name='num' value="${TipBoardDTO.num}">

        <div class="select-wrap" style="position: absolute;">
            <select name="category" id="category" >
                <option value="FRONT">Front</option>
                <option value="BACK">Back</option>
                <option value="DB">DB</option>
            </select>
        </div>

        <p><input type="text" size="210" id='subject' name='subject' placeholder="제목을 입력하세요" value="${TipBoardDTO.subject}"  required style="width: 95%; margin-left: 73px;"></p>

        <div>내용</div>
        <div id="contents">
            <div id="editor">
                 ${TipBoardDTO.content}
            </div>
            <div id="viewer"></div>
            <input type="hidden" id='content' name='content'>
        </div>

        <div class="button-wrap">
            <input type="button" value="수정" onclick="update()">
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
                 plugins: [colorSyntax],
                  hooks : {
                            addImageBlobHook: (blob, callback) => {
                                 const alt = blob.name
                                 const imgURL  = uploadImage(blob);
                                 callback(imgURL , alt);
                                }
                           }
             });

             function uploadImage(blob){
                   let dataImgUrl;
                   let formData = new FormData();
                   formData.append('image', blob);
                   $.ajax({
                         url : '/imgUrlReg',
                         enctype: 'multipart/form-data',
                         type: 'POST',
                         data: formData,
                         processData: false,
                         contentType: false,
                         async: false,
                     })
                     .done(function(data) {
                         dataImgUrl = data;
                     })
                     .fail(function(err) {
                         alert(err);
                     });
                         return dataImgUrl;
                  };
     </script>
    <script>
        function update(){
             var subject = document.querySelector("#subject").value;
             var content = editor.getMarkdown();

             if(subject == ''){
                 commonPopup.alertPopup('제목을 입력하세요✍', false);
                 document.querySelector("#subject").focus();
                 return;
             }if(content == ''){
                 commonPopup.alertPopup('내용을 입력하세요✍', false);
                 editor.focus();
                 return;
             }else{
                 document.querySelector("#content").value = editor.getHTML();
                 document.getElementById('tipBoard_form').submit();
             }
        }

        async function cancel(){
            if(await commonPopup.confirmPopup('진짜 취소하실꺼에여?🥺', commonPopup.callback)){
                window.history.back()
            }
        }

        $(document).ready(function(){
            $("#category").val('${TipBoardDTO.category}');
        });
    </script>
</body>
</html>