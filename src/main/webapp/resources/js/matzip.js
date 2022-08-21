// matzipBoard - func

    let isMatzipUpd = isEmpty(document.querySelector('.matzip-upd')) ? true : false;

    if(isMatzipUpd){
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
        const matzip_obj = JSON.parse(matzip_data);
        const matzip_id = matzip_obj.id
        const address_name = matzip_obj.address_name

        $.ajax({
            data:{"writer": writer, "subject":subject, "content":content, "star":star, "category": category, "matzipData": matzip_data, "matzipId": matzip_id, "addressName": address_name},
            type:"GET",
            url:"/regMatzip",
            success:function(data) {
                alert("성공");
                window.location.href = "/matzipList"
            },
            error:function(request, status, error) {
                alert("실패");
            }
        })
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

// matzipBoard - editor
