<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="content-wrap">
        <!--카테고리-->
        <div class="select-wrap" style="position: absolute;">
            <c:if test="${!empty TipBoardDTO.category}"></c:if>
                <select name="category" id="category">
                    <option value="FRONT" selected>Front</option>
                    <option value="BACK">Back</option>
                    <option value="DB">DB</option>
                </select>
        </div>

        <!--제목-->
        <p><input type="text" size="210" id='subject' name='subject' placeholder="제목을 입력하세요" value="${TipBoardDTO.subject}" required style="width: 95%; margin-left: 73px;"></p>

        <div>내용</div>
        <div id="contents">
            <div id="editor">
                ${TipBoardDTO.content}
            </div>
            <div id="viewer"></div>
        </div>

        <!--등록/수정/취소버튼-->
        <div class="button-wrap">
            <c:if test="${empty TipBoardDTO.subject}">
                <input type="button" value="등록" onclick="reg()">
            </c:if>
            <c:if test="${!empty TipBoardDTO.subject}">
                <input type="button" value="수정" onclick="udp()">
            </c:if>
            <input type="button" value="취소" onclick="cancel()">
       </div>
    </div>
    <script>

        $(document).ready(function(){
            if(document.location.search.replace(/[^0-9]/g,"") !=''){$("#category").val('${TipBoardDTO.category}');}
        });


        function validCheck() {
            let result = true;
             if(document.querySelector("#subject").value == ''){
                alert('제목을 입력하세요✍')
                document.querySelector("#subject").focus();
                result = false;
             }else if(editor.getMarkdown() == ''){
                alert('내용을 입력하세요✍')
                editor.focus();
                result = false;
             }
             return result;
        };

        function reg(){
            let category = document.querySelector("#category").value;
            let subject = document.querySelector("#subject").value;
            let content = editor.getHTML();
            let data = {category: category, subject: subject, content: content, brdNum: brdNum};

            if(validCheck()){
                fetch('/tipBoard',{
                    method: 'POST',
                    cache : 'no-cache',
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify(data)
                })
                .then(response => response.text())
                .catch(error => console.error('Error:', error))
                .then(response => window.location.href = "/tipList")
            }
        };

        function udp(){
            let num = document.location.search.replace(/[^0-9]/g,"");
            let category = document.querySelector("#category").value;
            let subject = document.querySelector("#subject").value;
            let content = editor.getHTML();
            let data = {num: num, category: category, subject: subject, content: content};

            if(validCheck()){
                  fetch('/updTip',{
                        method: 'POST',
                        cache : 'no-cache',
                        headers: {"Content-Type": "application/json"},
                        body:JSON.stringify(data)
                  })
                  .then(response => response.text())
                  .catch(error => console.error('Error:', error))
                  .then(response => window.location.href = "/tipDetail?num=${tipBoardDTO.num}")
            }
        };

        function cancel(){
            if(confirm('진짜 취소하실꺼에여?🥺') == true){
                window.history.back()
            }
        };

    </script>