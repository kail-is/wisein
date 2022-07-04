 function $dim(){

    let body         = document.getElementsByTagName('body')[0];
    let element      = document.getElementById('dim');
    let elDimWrapper = document.getElementById('dim-wrapper');
    let loadingImg   = document.getElementById('loading-img');
    let isDimming    = true;
    let option       = {};

    //option.isLoading = true; //로딩처리는 딤말고 다른데서 처리필요

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