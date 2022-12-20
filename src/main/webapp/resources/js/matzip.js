// matzipBoard - func

    let isMatzipUpd = !isEmpty(window.location.pathname.match("upd"))

    const categoryEl   = document.getElementById('category');
    const keywordEl = document.getElementById('keywordBox');
    const matzipDataEl = document.getElementById('matzip_upload_data');

    //const keywordBox = document.querySelector('.content-inner-box .keyword');
    const matzip_name = document.querySelector('#matzip-name');

    const fmtSrhBtn = document.querySelector('#format-search-btn');
    const addSrhBtn = document.querySelector('#add-search-btn');

    if(isMatzipUpd){
    // 맛집 수정 데이터
        const matzip_obj = JSON.parse(document.querySelector('#matzip-data').innerText)
        document.querySelector('#matzip-address').value = matzip_obj.address_name
        document.querySelector('#matzip-name').value = matzip_obj.place_name;

        const defaultStar = document.querySelector("#star-val").value
        document.querySelector("#star")[defaultStar - 1].selected = true;

        editor.setHTML(document.querySelector('#content').value);
    }

    function matzipSubmit(){

        const writer = document.getElementById('writer').value;
        const subject = document.getElementById('subject').value;
        const content = editor.getHTML();
        const star = document.getElementById('star').value;

        const category = document.getElementById('category').value;
        const matzip_data = document.getElementById('matzip_upload_data').value;
        const matzip_obj = matzip_data ? JSON.parse(matzip_data) : "";
        const matzip_id = matzip_obj.id
        const address_name = matzip_obj.address_name

        if ( matzipValidate(subject, content, matzip_data) ){

            const obj = {"writer": writer, "subject":subject, "content":content, "star":star, "category": category, "matzipData": matzip_data, "matzipId": matzip_id, "addressName": address_name}

            fetch("/regMatzip", {
                    method : 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(obj),
                }).then(response => response.text())
                .catch(error => console.error('Error:', error))
                .then(response => {
                   commonPopup.alertPopup("성공", false);
                   const postNum = getPostNum(writer, subject)
                   if(brdNum != postNum){ updateImgHash(brdNum, postNum) }
                   window.location.href = "/matzip?id=" + matzip_id
                })
        }
    }

    function updateImgHash(brdNum, postNum){

        const brdName = getBoardNm()

         fetch("/updateHash?" + "brdNm=" + brdName + "&randomStr=" + brdNum + "brdNum=" + postNum)
               .then(response => response.text())
               .catch(error => console.error('Error:', error))
               .then(response => {
                    console.log(response)
                    commonPopup.alertPopup("이미지 업데이트 성공" ,false);
               })
    }


     async function getPostNum(writer, subject) {
        let postNum = "-1"
        const response = await fetch("/getPostNum?" + "writer=" + writer + "&subject=" + subject)
        const data = await response.text()
        return data
        // TODO 동기식 실행 완료 - fulfilled Promise 객체 수정 요함
    }

    function matzipUpd(){

        const num = document.getElementById('num').value ;
        const subject = document.getElementById('subject').value;
        const content = editor.getHTML();
        const star = document.getElementById('star').value;
        const matzipId = document.getElementById('matzip_id').value;

        debugger;

        const obj = {"num": num, "subject":subject, "content":content, "star":star}

        fetch("/putRecm", {
                method : 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(obj),
            }).then(response => response.text())
            .catch(error => console.error('Error:', error))
            .then(response => {
               commonPopup.alertPopup("성공", false);
               window.location.href = "/matzip?id=" + matzipId
            });
    }

    function matzipValidate(subject, content, matzip_data) {

        if (isEmpty(subject)){
            commonPopup.alertPopup("제목을 입력하세요.", false);
            return false;
        }

        if (isEmpty(matzip_data)){
            commonPopup.alertPopup("맛집을 검색하세요.", false);
            return false;
        }

        if(content == "<p><br></p>") {
           if (!confirm("내용 없이 등록하시겠습니까?")) {
             return false;
           }
        }

        return true;

    }

// matzipBoard - 외부 맛집 검색

    const popupManger = {
            originalData : '',
            callback     : () => {},
            popupConfirm : function(e){
                var formData1 = new FormData(e);
                this.popupClose();
                this.selectedData = formData1.get('content-radio');
                var index = formData1.get('content-radio').split(":")[0]
                const result = {
                    selectedData   : formData1.get('content-radio').split(":")[1],
                    resultData     : this.originalData.documents[index],
                    originalData   : this.originalData,
                }
                this.callback(result);
            },
            popupClose: function(id='matzipPopupBox'){
                const popupEl = document.getElementById(id);
                popupEl.classList.add('none');

            },
            popupOpen : function(id='matzipPopupBox'){
                const popupEl = document.getElementById(id);
                popupEl.classList.remove('none');

            },
            matzipPopupOpen : function(data,callback){
                this.originalData = data;
                this.popupOpen();
                this.callback = callback;
                const matzipListEl = document.getElementById('matzipList');
                let list = ``
                if(data.documents.length > 0) {
                    list = data.documents.map((item,index)=>{
                        return `<li><input type="radio" id="search-${index}" class="content-radio content-radio-checked" name="content-radio" value="${index}:${item.place_name}(${item.address_name})"/><label for="search-${index}" class="content-label">${item.place_name}(${item.address_name})</label></li>`;
                    }).join('');
                }else {
                    list = `<li class="flex-center" style="height: 100%;"><p>데이터가 없습니다! 다시 검색하세요.</p></li>`
                }
                matzipListEl.innerHTML = list;
            }
    }

    function findKeyword() {
        let keyword = `${categoryEl.value} ${keywordEl.value}`
        const kakaoRestApiKey = KAKAO_KEY

        fetch( "https://dapi.kakao.com/v2/local/search/keyword.JSON?query=" + keyword, {
            headers: {'Authorization': `KakaoAK ${kakaoRestApiKey}`}
            }).then(response => response.json())
            .catch(error => console.error('Error:', error))
            .then(data => {
                debugger;
               popupManger.matzipPopupOpen(data,(result)=>{
                   matzip_name.value = result.selectedData.split("(")[0]
                   keywordEl.value = result.selectedData.split("(")[1].replace(")","");
                   matzipDataEl.value = JSON.stringify(result.resultData, null, 2);

                   keywordEl.style.width = "50%"
                   matzip_name.classList.remove('none');
                   fmtSrhBtn.classList.remove('none');
                   addSrhBtn.classList.add('none');
               });
            });
    }

    function formatKeyword() {

        matzip_name.value = ""
        keywordEl.value = ""
        matzipDataEl.value = ""

        keywordEl.style.width = "70%"
        matzip_name.classList.add('none');
        fmtSrhBtn.classList.add('none');
        addSrhBtn.classList.remove('none');

    }
