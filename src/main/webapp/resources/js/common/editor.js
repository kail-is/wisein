const { Editor } = toastui;
const { colorSyntax } = Editor.plugin;

let updYn = document.location.pathname.startsWith('/upd')
const brdNum = updYn ? document.location.search.replace(/[^0-9]/g,"") : randomString()
const brdNm = getBoardNm()

UPLOADCARE_PUBLIC_KEY = "bd591f9450438bb316d9"

const onUploadImage = (blob, callback) => {
     let uploadImg = uploadcare.fileFrom("object", blob);
     uploadImg.done(fileInfo => {
       let formData = new FormData();
       formData.append("brdCdNm", brdNum)
       formData.append("brdNm", brdNm)
       formData.append("fileInfo", JSON.stringify(fileInfo))

       let entries = formData.entries();
       for (const pair of entries) {
           console.log(pair[0]+ ', ' + pair[1]);
       }

       debugger;

        fetch('/uploadImg', {
            method: 'POST',
            cache: 'no-cache',
            body: formData,
        })
        .then(response => response.text())
        .catch(error => console.error('Error:', error))
        .then( imgUrl => {
            console.log('uploadImg')
        });

        console.log("File uploaded: ", fileInfo.cdnUrl);
        const alt = document.getElementById("toastuiAltTextInput").value;
        callback(fileInfo.cdnUrl, alt)
    });

    return false
 };

const editor = new Editor({
         el: document.querySelector('#editor'),
         height: '749px',
         initialEditType: 'markdown',
         previewStyle: 'vertical',
         placeholder: '📌욕설이나 비방, 모욕, 선정성이 존재하는 사진이나 게시글은 업로드하지 말아주세요📌',
         plugins: [colorSyntax],
         hooks: {
            addImageBlobHook: onUploadImage
         }
    });