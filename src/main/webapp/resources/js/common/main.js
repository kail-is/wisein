 function $dim(){

    let body         = document.getElementsByTagName('body')[0];
    let element      = document.getElementById('dim');
    let elDimWrapper = document.getElementById('dim-wrapper');
    let loadingImg   = document.getElementById('loading-img');
    let option       = {};


    switch (typeof arguments[0]) {
        case "boolean":
            option.isDimming = arguments[0];
            break;
        case "object":
            option.isDimming = arguments[0].isDimming;
            option.isLoading = arguments[0].isLoading;
            break;
        default:
            option.isDimming = true;
            break;
    }

    if(option.isDimming){
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

function enterKey(param) {
   var selectedSearchType, inputKeyword

    if(param == 'PC'){
        selectedSearchType = document.getElementById('search-list');
        inputKeyword = document.getElementById('keywordInput');
    }else if(param == 'MO'){
        selectedSearchType = document.getElementById('search-list2');
        inputKeyword = document.getElementById('keywordInput2');
    }

   if(window.event.keyCode == 13 || window.event.type == 'click') {
       const searchData = "/integration/board"
                     + '?searchType=' + selectedSearchType.value
                     + "&keyword=" + inputKeyword.value

        location.href = self.location.origin + searchData
   }
}
