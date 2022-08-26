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

    $.ajax({
        data: formData,
        type: 'POST',
        enctype: 'multipart/form-data',
        url: '/upload',
        contentType: false,
        processData: false,
        success: function(imgUrl) {
           const alt = blob.name
           url = window.location.host
           callback( "http://" + url + '/' +imgUrl, alt);
           imgUpload = true
        },
        error: function(e) {
            alert('업로드 실패')
        }
    });

    return false
 };

const editor = new Editor({
         el: document.querySelector('#editor'),
         height: '749px',
         initialEditType: 'wysiwyg',
         previewStyle: 'vertical',
         placeholder: '📌욕설이나 비방, 모욕, 선정성이 존재하는 사진이나 게시글은 업로드하지 말아주세요📌',
         plugins: [colorSyntax],
         hooks: {
            addImageBlobHook: onUploadImage
         }
    });