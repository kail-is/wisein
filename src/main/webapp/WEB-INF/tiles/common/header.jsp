<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <header>
        <ul>
            <li class="logo"><a href="/">wiseIN</a></li>
            <li class="search-wrap">
                <div class="select-wrap">
                    <select name="searchType" class="searchType" id="search-list">
	                    <option value="all"   ${qaListDTO.getSearchType() eq 'all' ? 'selected':''}>카테고리 검색 </option>
						<option value="front" ${qaListDTO.getSearchType() eq 'front'?'selected':''}>FRONT</option>
			    		<option value="back"  ${qaListDTO.getSearchType() eq 'back'?'selected':''}>BACK</option>
			    		<option value="db"    ${qaListDTO.getSearchType() eq 'db'?'selected':''}>DB</option>
	                </select>
                </div>
                <input type="text" id="keywordInput" class="search-bar" placeholder="Type something…" onkeyup="enterkey()" value="${qaListDTO.getKeyword()}" />
            </li>
            <li class="bar">
                <label for="bar-chk">
                    <span class="material-icons">
                        menu
                    </span>
                </label>

            </li>
        </ul>
    </header>

    <script>
       function enterkey() {
    	   var selectedSearchType = document.getElementById('search-list');
    	   var inputKeyword = document.getElementById('keywordInput');

           if(window.event.keyCode == 13) {
       		   self.location = "qalist"
       						 + '?searchType=' + selectedSearchType.value
       	   					 + "&keyword=" + inputKeyword.value
           }
       }
    </script>
