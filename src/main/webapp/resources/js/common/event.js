function clickDisplayChangeListener(params) {
    Array.from(params).forEach(function(element) {
        element.addEventListener('click', function(e) {
            try {
                if(e.target.nextElementSibling.style.display === 'block'){
                    e.target.nextElementSibling.style.display = 'none';
                }else{
                    e.target.nextElementSibling.style.display = 'block';
                }
            } catch (error) {
                console.log(error);
            } 
        });
    });
}

export {clickDisplayChangeListener}