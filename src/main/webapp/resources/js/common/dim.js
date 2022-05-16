 function $dim(){

    let body      = document.getElementsByTagName('body')[0];
    let element   =  document.getElementById('dim') ?? body;
    let isDimming = true;

    for(let i = 0; i<arguments.length;i++){
        switch (typeof(arguments[i])) {
            case 'object':
                if(arguments[i].nodeType === 1){
                    element = arguments[i];
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
        element.className   += ' dim';
        body.style.overflowY = 'hidden';
    }else{
        element.classList.remove('dim');
        body.style.overflowY = 'auto';
    }
}