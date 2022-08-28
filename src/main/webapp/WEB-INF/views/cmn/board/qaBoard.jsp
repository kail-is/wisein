<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <form autocomplete="off" id="qaBoardForm">
       <div class="content-wrap">
         <div class="select-wrap" style="position: absolute;">
            <c:if test="${empty qaListDTO.category}">
                <select id="category" name="category" >
                   <option value="FRONT">Front</option>
                   <option value="BACK">Back</option>
                   <option value="DB">DB</option>
                </select>
            </c:if>
            <c:if test="${!empty qaListDTO.category}">
                <c:out value="${qaListDTO.category}" />
            </c:if>
        </div>
        <p>
            <input type="text" size="210" id="subject" name="subject" placeholder="제목을 입력하세요" value="${qaListDTO.subject}" required style="width: 95%; margin-left: 73px;">
        </p>

        <div>내용</div>
        <div id="contents">
            <div id="editor">${qaListDTO.content}</div>
            <div id="viewer"></div>

            <input type="hidden" id="num" name="num" value="${qaListDTO.num}">
            <input type="hidden" id="parentNum" name="parentNum" value="${qaListDTO.parentNum}">
            <input type="hidden" id="content" name="content">
        </div>

        <div class="button-wrap">
            <c:if test="${empty qaListDTO.subject}">
                <input type="button" value="등록" onclick="reg()">
            </c:if>
            <c:if test="${!empty qaListDTO.subject}">
                <input type="button" value="수정" onclick="update()">
            </c:if>
            <input type="button" value="취소" onclick="cancel()">
       </div>

    </div>
    </form>
    <script>
        function reg(){
            var writer = '<c:out value="${member.id}" />';
            if(writer != ""){
                document.querySelector("#content").value = editor.getHTML();
                var num = document.querySelector("#num").value;
                var parentNum = document.querySelector("#parentNum").value;

                if(num == ""){document.querySelector("#num").value = 0;}
                if(parentNum == ""){document.querySelector("#parentNum").value = 0;}

                var form = document.getElementById("qaBoardForm");
                form.action = "/qaBoard";
                form.method = "POST";
                form.submit();
            } else if(writer == ""){
               alert("로그인 후 이용가능합니다.");
            }
        }
    </script>
    <script>
        function update(){
            Debugger
            const num = '<c:out value="${qaListDTO.num}" />';
            const subject = document.getElementById('subject').value;
            const content = editor.getHTML();
            Debugger
            $.ajax({
                data:{"num":num,"subject":subject,"content":content},
                type:"POST",
                url:"/qaUpdatePro",
                success:function(data) {
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