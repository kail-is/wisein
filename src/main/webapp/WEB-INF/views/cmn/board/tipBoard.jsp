<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <div class="content-wrap">
        <div>제목</div>
        <p><input type="text" size="210" id='title' placeholder="제목을 입력하세요" required></p>

        <div>내용</div>
        <div id="contents">
            <div id="editor"></div>
            <div id="viewer"></div>
        </div>

        <div class="button-wrap">
            <input type="button" value="등록" onclick="submit()">
            <input type="button" value="취소" onclick="cancel()">
       </div>
    </div>
    <script>
        function submit(){
            const title = document.getElementById('title').value;
            console.log(title);
            console.log(editor.getHTML());
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