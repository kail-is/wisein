// matzipBoard - func

    let matzipUpdBox = document.querySelector('.matzip-upd')
    let isMatzipUpd = !(matzipUpdBox === undefined || matzipUpdBox === null) ? true : false

    const categoryEl   = document.getElementById('category');
    const keywordEl = document.getElementById('keyword');
    const matzipDataEl = document.getElementById('matzip_upload_data');

    const keyword = document.querySelector('.content-inner-box .keyword');
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

            $.ajax({
                data:{"writer": writer, "subject":subject, "content":content, "star":star, "category": category, "matzipData": matzip_data, "matzipId": matzip_id, "addressName": address_name},
                type:"GET",
                url:"/regMatzip",
                success:function(data) {
                    alert("성공");
                    const num = getPostNum(writer, subject)
                    if(brdNum != num){
                        updateImgHash(brdNum, num)
                    }
                    window.location.href = "/matzipList"
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
        }
    }

    function updateImgHash(brdNum, num){

        const brdNm = getBoardNm()

         $.ajax({
                data:{"brdNm": brdNm, "randomStr": brdNum, "brdNum":num},
                type:"GET",
                url:"/updateHash",
                success:function(data) {
                    alert("이미지 업데이트 성공");
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })
    }


    function getPostNum(writer, subject) {
        let postNum = "-1"

         $.ajax({
                data:{"writer": writer, "subject":subject},
                type:"GET",
                url:"/getPostNum",
                async: false,
                success:function(data) {
                    postNum = data;
                },
                error:function(request, status, error) {
                    alert("실패");
                }
            })

        return postNum;
    }

    function matzipUpd(){

        const num = document.getElementById('num').value;
        const subject = document.getElementById('subject').value;
        const content = editor.getHTML();
        const star = document.getElementById('star').value;

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

    function matzipValidate(subject, content, matzip_data) {

        if (isEmpty(subject)){
            alert("제목을 입력하세요.");
            return false;
        }

        if (isEmpty(matzip_data)){
            alert("맛집을 검색하세요.");
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
                const list = data.documents.map((item,index)=>{
                    return `<li><input type="radio" id="search-${index}" class="content-radio content-radio-checked" name="content-radio" value="${index}:${item.place_name}(${item.address_name})"/><label for="search-${index}" class="content-label">${item.place_name}(${item.address_name})</label></li>`;
                }).join('');
                matzipListEl.innerHTML = list;
            }
    }

    function findKeyword() {
        let keyword = `${categoryEl.value} ${keywordEl.value}`
        const kakaoRestApiKey = KAKAO_KEY

        $.ajax({
            type : 'get',
            url : 'https://dapi.kakao.com/v2/local/search/keyword.JSON?query='+keyword,
            beforeSend : function(xhr){
                xhr.setRequestHeader("Authorization", `KakaoAK ${kakaoRestApiKey}`);
            },
            error: function(xhr, status, error){
                alert(error+'error');
            },
            success : function(data){
                popupManger.matzipPopupOpen(data,(result)=>{
                    matzip_name.value = result.selectedData.split("(")[0]
                    keywordEl.value = result.selectedData.split("(")[1].replace(")","");
                    matzipDataEl.value = JSON.stringify(result.resultData, null, 2);

                    keywordEl.style.width = "50%"
                    matzip_name.classList.remove('none');
                    fmtSrhBtn.classList.remove('none');
                    addSrhBtn.classList.add('none');
                });

            },
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
