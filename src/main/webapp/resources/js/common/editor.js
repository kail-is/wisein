const { Editor } = toastui;
const { colorSyntax } = Editor.plugin;

let updYn = document.location.pathname.startsWith('/upd')
const brdNum = updYn ? document.location.search.replace(/[^0-9]/g,"") : randomString()
const brdNm = getBoardNm()

const onUploadImage = (blob, callback) => {
   console.log(blob)

   let formData = new FormData();
   formData.append("brdNumCd", brdNum)
   formData.append("brdNm", brdNm)
   formData.append("file", blob)

    fetch('/upload', {
        method: 'POST',
        cache: 'no-cache',
        body: formData
    })
    .then(response => response.text())
    .catch(error => console.error('Error:', error))
    .then( imgUrl => {
        const alt = document.getElementById("toastuiAltTextInput").value
        url = window.location.host
        callback( "http://" + url + '/' + imgUrl, alt)
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