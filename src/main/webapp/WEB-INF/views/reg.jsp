<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<section id="content">
 <form role="form" method="post" autocomplete="off">
  <div class="input_area">
   <label for="id">아이디 </label>
   <input type="text" id="id" name="id" required="required" />
  </div>

  <div class="input_area">
   <label for="pw">패스워드</label>
   <input type="password" id="pw" name="pw" required="required" />
  </div>

  <div class="input_area">
   <label for="name">이름</label>
   <input type="text" id="name" name="name" required="required" />
  </div>

    <div class="input_area">
     <label for="site">사이트</label>
     <input type="text" id="site" name="site" placeholder="사이트" required="required" />
    </div>

  <button type="submit" id="signup_btn" name="signup_btn">회원가입</button>
 </form>
</section>