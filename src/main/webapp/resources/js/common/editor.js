const { Editor } = toastui;
const { colorSyntax } = Editor.plugin;

const editor = new Editor({
         el: document.querySelector('#editor'),
         height: '749px',
         initialEditType: 'markdown',
         previewStyle: 'vertical',
         placeholder: '📌욕설이나 비방, 모욕, 선정성이 존재하는 사진이나 게시글은 업로드하지 말아주세요📌',
         plugins: [colorSyntax]
    });