        //전역변수
        let tipNum = document.location.search.replace(/[^0-9]/g,"");
        let tipWriter = document.getElementById('tipWriter') || "";
        let memId = document.getElementById('comment_writer')|| "";
        let boardType = "tip";
        let isMod = false;

        if (tipWriter != "") tipWriter = tipWriter.innerHTML;
        if (memId != "") memId = memId.innerHTML;

        function gatherMemTip(){
            window.location.href="/gatherMemTip?writer="+tipWriter;
        }

         function delTip(){
             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
                 window.location.href="/delTip?num="+tipNum;
             }
         }

         function updTip(){
              window.location.href="/updTip?num="+tipNum;
         }

         function changeCommHtml(newCommList){
            let newCommArr = newCommList.commentList;
            let html = "<div class='recommend-titleLine'> 댓글 ("+newCommArr.length+")</div>"
            for(let i=0; i < newCommArr.length; i++){
                newCommArr[i].content = newCommArr[i].content.replaceAll('&lt;br&gt;', '<br>')
                console.log("ddddddddd"+newCommArr[i].content)
                html += "<ul class='recommend'><li>"
                html += "<div class='wrap'><div class='recommend-info-wrap'>"

                if(newCommArr[i].filePath == null){
                    html += "<img src ='../resources/image/Conic.png'>"
                }else{
                    html += "<img src ='../"+newCommArr[i].filePath+"'>"
                }

                html += "<div class='info'><div class='title'>"+newCommArr[i].writer+"</div>"
                html += "<div class='sub'><div class='date'>"+(newCommArr[i].regDate).slice(0,10)+"</div>"
                if(newCommArr[i].writer == memId){
                    html += "<div class='icon'>"
                    html += "<span class='material-icons' onClick='openModi("+newCommArr[i].num+",&apos;"+newCommArr[i].content+"&apos;)'>border_color</span>"
                    html += "<span class='material-icons' onClick='delComm("+newCommArr[i].num+")'>delete</span></div>"
                }
                html += "</div></div></div>"
                html += "<div class='content' id='comm"+newCommArr[i].num+"' name='comm"+newCommArr[i].num+"'>"+newCommArr[i].content+"</div>"
                html += " <div class='content-mod' id='modComm"+newCommArr[i].num+"' name='modComm"+newCommArr[i].num+"' style='display: none;'>"
                html += "<textarea class='comment_inbox_text' id='modComm_content"+newCommArr[i].num+"' name='modComm_content"+newCommArr[i].num+"placeholder='댓글을 남겨보세요 onkeydown='resize(this)' onkeyup='resize(this)'></textarea>"
                html += "</div> <div class='comment_attach' id='mod_comment_attach"+newCommArr[i].num+"' style='display: none;'>"
                html += "<div class='register_box'>"
                html += "<a href='#' role='button' class='button btn_register' onClick='modComm("+newCommArr[i].num+")'>수정</a>"
                html += "<a href='#' role='button' class='button btn_register' onClick='modCancel("+newCommArr[i].num+")'>취소</a>"
                html += "</div></div></div></li></ul>"
            }
                html += "</section>";
                let parent = document.getElementById('changeCommHtml');
                parent.innerHTML = "";
                parent.innerHTML = html;
         }

         function initCommText(){
            document.getElementById("comment_content").value = '';
            document.getElementById('comment_content').style.height = '0px'
         }

         function regComm(){
            let content = document.getElementById('comment_content').value
            content = content.replace(/(?:\r\n|\r|\n)/g,'<br>');
            let data = {boardType: boardType, boardIdx: tipNum, content: content}

            if(content.length==0){
                alert("댓글을 입력하세요👀");
                document.getElementById('comment_content').focus();
                return;
            }

            fetch('/regTipComm',{
                method: 'POST',
                cache : 'no-cache',
                headers: {"Content-Type": "application/json"},
                body:JSON.stringify(data)
            })
            .then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(json =>  {
                changeCommHtml(json);
                initCommText();
            })
         }

          function delComm(commNum){
             let data = {num: commNum, boardType: boardType, boardIdx: tipNum};

             if(confirm('진짜 삭제하실꺼에여?🥺') == true){
                 fetch('/delTipComm',{
                     method: 'POST',
                     cache : 'no-cache',
                     headers: {"Content-Type": "application/json"},
                     body:JSON.stringify(data)
                 })
                 .then(response => response.json())
                 .catch(error => console.error('Error:', error))
                 .then(json =>  {
                    changeCommHtml(json);
                 })
             }
          }


          function openModi(commNum, content){
            content = content.replaceAll('<br>', '\r\n');
            if(isMod==false){
                document.getElementById('comm'+commNum).style.display = 'none';
                document.getElementById('modComm'+commNum).style.display = 'block';
                document.getElementById('mod_comment_attach'+commNum).style.display = 'block';
                document.getElementById('modComm_content'+commNum).value = content;
                resize(document.getElementById('modComm_content'+commNum))
                isMod = true;
            }else{
                alert("이미 수정중인 댓글이 있어용🤔")
            }
          }

          function modCancel(commNum){
            isMod = false;
            document.getElementById('modComm'+commNum).style.display = 'none';
            document.getElementById('mod_comment_attach'+commNum).style.display = 'none';
            document.getElementById('comm'+commNum).style.display = 'block';
          }

          function modComm(commNum){
              let content = document.getElementById('modComm_content'+commNum).value;
              content = content.replace(/(?:\r\n|\r|\n)/g,'<br>');
              let data = {num: commNum, boardType: boardType, boardIdx: tipNum, content: content};

              if(content.length==0){
                  alert("수정할 댓글 내용을 입력하세요✍");
                  document.getElementById('modComm_content'+commNum).focus();
                  return;
              }

              fetch('/udpTipComm',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                 changeCommHtml(json);
                 modCancel(commNum);
              })
          }

          function changeLikeHtml(json){
              let html;
              let likeDelYn = json.likeDelYn;
              let num = tipNum;

              if(likeDelYn=='N'){
                html = "<span class='material-icons purple' id='like_btn' onclick='udpLike("+num+")'>thumb_up</span>"
              }else{
                html = "<span class='material-icons gray' id='like_btn' onclick='udpLike("+num+")'>thumb_up</span>"
              }

              let parent = document.getElementById('changeLikeHtml');
              parent.innerHTML='';
              parent.innerHTML= html;
          }

          function regLike(tipNum){
              if(tipWriter==memId){
                  alert(memId+"님 자신의 글입니다😅")
                  return;
              }

              let data = {boardType: boardType, boardIdx: tipNum};

              fetch('/regLikeTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                 changeLikeHtml(json);
              })
          }

          function udpLike(tipNum){
              if(tipWriter==memId){
                  alert(memId+"님 자신의 글입니다😅")
                  return;
              }

              let data = {boardType: boardType, boardIdx: tipNum};

              fetch('/udpLikeTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                changeLikeHtml(json);
             })
          }

          function changeScrapHtml(json){
              let html;
              let scrapDelYn = json.scrapDelYn;
              let num = tipNum;

              if(scrapDelYn=='N'){
                html = "<span class='material-icons purple' id='like_btn' onclick='udpScrap("+num+")'>bookmarks</span>"
              }else{
                html = "<span class='material-icons gray' id='like_btn' onclick='udpScrap("+num+")'>bookmarks</span>"
              }

              let parent = document.getElementById('changeScrapHtml');
              parent.innerHTML='';
              parent.innerHTML= html;
          }

          function regScrap(tipNum){
              if(tipWriter==memId){
                  alert(memId+"님 자신의 글입니다😅")
                  return;
              }

              let data = {boardType: boardType, boardIdx: tipNum};

              fetch('/regScrapTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                  changeScrapHtml(json);
              })
          }

          function udpScrap(tipNum){
              if(tipWriter==memId){
                  alert(memId+"님 자신의 글입니다😅")
                  return;
              }

              let data = {boardType: boardType, boardIdx: tipNum};

              fetch('/udpScrapTip',{
                  method: 'POST',
                  cache : 'no-cache',
                  headers: {"Content-Type": "application/json"},
                  body:JSON.stringify(data)
              })
              .then(response => response.json())
              .catch(error => console.error('Error:', error))
              .then(json =>  {
                  changeScrapHtml(json);
              })
          }

         function resize(obj){
             obj.style.height = '1px';
             obj.style.height = (10 + obj.scrollHeight) + 'px';
         }



