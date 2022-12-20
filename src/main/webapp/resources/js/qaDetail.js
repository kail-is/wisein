
        // 댓글-댓글모아보기
        function comment_commentList_btn(commentQaWriter) {
            window.location.href="/commentList?writer="+commentQaWriter;
        }
        // 원본-댓글모아보기
        function commentList_btn(writer) {
            window.location.href="/commentList?writer="+writer;
        }
        // 댓글-질문모아보기
        function comment_questionsList_btn(commentQaWriter) {
            window.location.href="/questionsList?writer="+commentQaWriter;
        }

        // 원본-질문모아보기
        function questionsList_btn(writer) {
            window.location.href="/questionsList?writer="+writer;
        }


        // 댓글 업데이트
        function comment_update_btn(num) {
            window.location.href="/qaUpdate?num="+num;
        }
        // 댓글 삭제
        async function comment_delete_btn(commentQaNum) {
            if(await commonPopup.confirmPopup('정말 삭제하시겠습니?', commonPopup.callback)){
                window.location.href="/qaDelete?commentQaNum="+commentQaNum;
            }
        }

        // 원본 업데이트
        function update_btn(num){
            window.location.href="/qaUpdate?num="+num
        }
        // 원본 삭제
        async function delete_btn(num){
            if(await commonPopup.confirmPopup('정말 삭제하시겠습니까?', commonPopup.callback)){
                window.location.href="/qaDelete?num="+num
            }
        }

        // 답변달기
        function comment_btn(writer, category, num){
           if(writer != ""){
               var parentNum = num;
               window.location.href="/qaBoard?parentNum=" + parentNum + "&writer=" + writer +"&category="+category;
           } else if(writer == ""){
               commonPopup.alertPopup("로그인 후 이용가능합니다.");
           }
        }

        // 채택하기
        function adp_btn(writer, boardNum, commentNum) {
               if(writer != ""){
                   document.querySelector("#boardNum").value = boardNum;
                   document.querySelector("#commentNum").value = commentNum;
                   document.getElementById('adpForm').submit();
               } else if(writer == ""){
                   commonPopup.alertPopup("로그인 후 이용가능합니다.");
               }
        }





        function changeLikeHtml(json){
            let writer = json.member.id;

            //console.log(json);
            //console.log(writer);
            for(let i=0; i<json.commentQaList.commentQaList.length; i++){
                //console.log(json.commentQaList.commentQaList[i].num);
                let commentQaNum = json.commentQaList.commentQaList[i].num;

                let check = 0;
                let id = "changeLikeHtml"+i;
                for(let j=0; j<json.likeQaBoardList.likeQaBoardList.length; j++){
                    let likeQaBoardIdx = json.likeQaBoardList.likeQaBoardList[j].boardIdx;
                    let likeQaBoardDelYn = json.likeQaBoardList.likeQaBoardList[j].delYn;
                    if(commentQaNum == likeQaBoardIdx && likeQaBoardDelYn == 'N'){
                        check = 1;
                        id = "changeLikeHtml"+i;
                        break;
                    }
                    if(commentQaNum == likeQaBoardIdx && likeQaBoardDelYn == 'Y'){
                        check = 2;
                        id = "changeLikeHtml"+i;
                        break;
                    }
                }

                let html;

                if(check == 0){
                    html = "<div class='board-cell board-like gray qaDetail'>"
                    html += "<span class='material-icons' id='comment_like_btn' onclick='regLike(" + "\"" + writer + "\"," + commentQaNum + ")' >thumb_up</span>"
                    html += "</div>"
                }
                else if(check == 1){
                    html = "<div class='board-cell board-like purple2 qaDetail'>"
                    html += "<span class='material-icons' id='comment_like_btn' onclick='udpLike(" + "\"" + writer + "\"," + commentQaNum + ")'  >thumb_up</span>"
                    html += "</div>"
                }
                else if(check == 2){
                    html = "<div class='board-cell board-like gray qaDetail'>"
                    html += "<span class='material-icons' id='comment_like_btn' onclick='udpLike(" + "\"" + writer + "\"," + commentQaNum + ")' >thumb_up</span>"
                    html += "</div>"
                }

                //console.log(check);
                //console.log(id);
                let parent = document.getElementById(id);
                parent.innerHTML='';
                parent.innerHTML= html;
            }
        }


        function changeScrapHtml(json){
            let writer = json.member.id;

            //console.log(json);
            //console.log(writer);
            for(let i=0; i<json.commentQaList.commentQaList.length; i++){
                //console.log(json.commentQaList.commentQaList[i].num);
                let commentQaNum = json.commentQaList.commentQaList[i].num;

                let checkScrap = 0;
                let id = "changeScrapHtml"+i;
                for(let j=0; j<json.scrapQaBoardList.scrapQaBoardList.length; j++){
                    let scrapQaBoardIdx = json.scrapQaBoardList.scrapQaBoardList[j].boardIdx;
                    let scrapQaBoardDelYn = json.scrapQaBoardList.scrapQaBoardList[j].delYn;
                    if(commentQaNum == scrapQaBoardIdx && scrapQaBoardDelYn == 'N'){
                        checkScrap = 1;
                        id = "changeScrapHtml"+i;
                        break;
                    }
                    if(commentQaNum == scrapQaBoardIdx && scrapQaBoardDelYn == 'Y'){
                        checkScrap = 2;
                        id = "changeScrapHtml"+i;
                        break;
                    }
                }

                let html;

                if(checkScrap == 0){
                    html = "<div class='board-cell board-like gray qaDetail'>"
                    html += "<span class='material-icons' id='comment_scrap_btn' onclick='regScrap(" + "\"" + writer + "\"," + commentQaNum + ")' >bookmarks</span>"
                    html += "</div>"
                }

                else if(checkScrap == 1){
                    html = "<div class='board-cell board-like purple2 qaDetail'>"
                    html += "<span class='material-icons' id='comment_scrap_btn' onclick='udpScrap(" + "\"" + writer + "\"," + commentQaNum + ")'  >bookmarks</span>"
                    html += "</div>"
                }

                else if(checkScrap == 2){
                    html = "<div class='board-cell board-like gray qaDetail'>"
                    html += "<span class='material-icons' id='comment_scrap_btn' onclick='udpScrap(" + "\"" + writer + "\"," + commentQaNum + ")' >bookmarks</span>"
                    html += "</div>"
                }

                //console.log(checkScrap);
                //console.log(id);
                let parent = document.getElementById(id);
                parent.innerHTML='';
                parent.innerHTML= html;
            }
        }

        function regLike(writer,num){
            //var writer = '<c:out value="${member.id}" />';
            //alert("regLike "+writer);
            if(writer != ""){
                let data = {
                    method: 'POST',
                    body: JSON.stringify({ num }),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };

                fetch("/regLikeQa", data)
                .then(response => response.json())
                .catch(error => console.log(error))
                .then(json =>  {
                    changeLikeHtml(json);
                })
            } else if(writer == ""){
               commonPopup.alertPopup("로그인 후 이용가능합니다.");
               window.location.href="/login";
            }
        }

        function udpLike(writer,num){
            //var writer = '<c:out value="${member.id}" />';
            //alert("udpLike "+writer);
            //alert(num);
            if(writer != ""){
                let data = {
                    method: 'POST',
                    body: JSON.stringify({ num }),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };

                fetch("/udpLikeQa", data)
                .then(response => response.json())
                .catch(error => console.log(error))
                .then(json =>  {
                    changeLikeHtml(json);
                })
            } else if(writer == ""){
               commonPopup.alertPopup("로그인 후 이용가능합니다.");
               window.location.href="/login";
            }
        }

        function regScrap(writer,num){
            //var writer = '<c:out value="${member.id}" />';
            //alert(writer);
            //alert(num);
            if(writer != ""){
                let data = {
                    method: 'POST',
                    body: JSON.stringify({ num }),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };

                fetch("/regScrapQa", data)
                .then(response => response.json())
                .catch(error => console.log(error))
                .then(json =>  {
                    changeScrapHtml(json);
                })
            } else if(writer == ""){
               commonPopup.alertPopup("로그인 후 이용가능합니다.");
               window.location.href="/login";
            }
        }

        function udpScrap(writer,num){
            //var writer = '<c:out value="${member.id}" />';
            //alert(writer);
            //alert(num);
            if(writer != ""){
                let data = {
                    method: 'POST',
                    body: JSON.stringify({ num }),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                };

                fetch("/udpScrapQa", data)
                .then(response => response.json())
                .catch(error => console.log(error))
                .then(json =>  {
                    changeScrapHtml(json);
                })
            } else if(writer == ""){
               commonPopup.alertPopup("로그인 후 이용가능합니다.");
               window.location.href="/login";
            }
        }

        function comment_meetLink_btn(num){
            let data = {
                method: 'POST',
                body: JSON.stringify({ num }),
                headers: {
                    'Content-Type': 'application/json'
                }
            };

            fetch("/selectMeetLink", data)
            .then(response => response.json())
            .catch(error => console.log(error))
            .then(json =>  {
                console.log(json);
                let meetLink = json.meetLink;
                console.log(meetLink);
                if(meetLink == null){
                    commonPopup.alertPopup('미트링크가 없어요😅');
                } else{
                    //window.location.href = meetLink;
                    //window.location.replace(meetLink);
                    window.open(meetLink,'_blank');
                }
            })
        }



        function comment_meetLink_btn(num){
            let data = {
                method: 'POST',
                body: JSON.stringify({ num }),
                headers: {
                    'Content-Type': 'application/json'
                }
            };

            fetch("/selectMeetLink", data)
            .then(response => response.json())
            .catch(error => console.log(error))
            .then(json =>  {
                console.log(json);
                let meetLink = json.meetLink;
                console.log(meetLink);
                if(meetLink == null){
                    commonPopup.alertPopup('미트링크가 없어요😅');
                } else{
                    //window.location.href = meetLink;
                    //window.location.replace(meetLink);
                    window.open(meetLink,'_blank');
                }
            })
        }


