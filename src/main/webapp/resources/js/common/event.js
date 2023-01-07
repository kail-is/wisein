////디테일페이지 글쓴이 클릭이벤트(메일전송,질문모아보기,답변모아보기)
//function clickDisplayChangeListener(params) {
//    Array.from(params).forEach(function(element) {
//        element.addEventListener('click', function(e) {
//                if(e.target.nextElementSibling.style.display === 'block'){
//                    e.target.nextElementSibling.style.display = 'none';
//                }else{
//                    e.target.nextElementSibling.style.display = 'none';
//                }
//        });
//    });
//}
//debugger;
//let clkWriter = document.getElementsByClassName("writer");
//let categorySelect = document.getElementsByClassName("category-select");
//let titleSelect = document.getElementsByClassName("title-select");
//
//clickDisplayChangeListener(clkWriter);
//clickDisplayChangeListener(categorySelect);
//clickDisplayChangeListener(titleSelect);


let totalSearchWriter = document.getElementsByClassName("writer");
let beforeClickElement;

Array.from(totalSearchWriter).forEach(function(element) {
    element.addEventListener('click', function(e) {
        let clickElement = e.target;

        if(e.target.nextElementSibling.style.display === 'block'){
            e.target.nextElementSibling.style.display = 'none';
        }else{
            e.target.nextElementSibling.style.display = 'block';
        }

        if (typeof beforeClickElement != "undefined") {
            if (clickElement != beforeClickElement) {
                beforeClickElement.nextElementSibling.style.display = 'none';
            }
        }

        beforeClickElement = clickElement;
    });
});
