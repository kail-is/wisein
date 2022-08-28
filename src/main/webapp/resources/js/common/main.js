 function $dim(){

    let body         = document.getElementsByTagName('body')[0];
    let element      = document.getElementById('dim');
    let elDimWrapper = document.getElementById('dim-wrapper');
    let loadingImg   = document.getElementById('loading-img');
    let isDimming    = true;
    let option       = {};


    for(let i = 0; i<arguments.length;i++){
        switch (typeof(arguments[i])) {
            case 'object':
                if(arguments[i].nodeType === 1){
                    element = arguments[i];
                }else{
                    option = arguments[i];
                }
                break;
            case 'boolean':
                isDimming = arguments[i];
                break;
            default:
                break;
        }
    }
    if(isDimming){
        loadingImg.style.display   = option.isLoading ? 'block' : 'none';
        element.className         += ' dim';
        elDimWrapper.style.display = 'block';
        body.style.overflowY       = 'hidden';
    }else{
        element.classList.remove('dim');
        loadingImg.style.display   = 'none';
        elDimWrapper.style.display = 'none';
        body.style.overflowY       = 'auto';
    }
}

function enterkey() {
   var selectedSearchType = document.getElementById('search-list');
   var inputKeyword = document.getElementById('keywordInput');

   if(window.event.keyCode == 13) {
       const searchData = "/integration/board"
                     + '?searchType=' + selectedSearchType.value
                     + "&keyword=" + inputKeyword.value

        location.href = self.location.origin + searchData
   }
}