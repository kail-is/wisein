package com.wisein.wiselab.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wisein.wiselab.dto.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class AuthInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest req,
                                 HttpServletResponse res, Object obj) throws Exception {

            String path = req.getServletPath();

            if (path.equals("/authMailSend") || path.equals("/idDupChk") || path.equals("/authSuccess")) {
                return true;
            }

            HttpSession session = req.getSession();
            MemberDTO member = (MemberDTO)session.getAttribute("member");

            if(member == null) {
                res.setContentType("text/html;charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('로그인한 사용자만 이용 가능합니다.');");
                out.println("location.href='/'");
                out.println("</script>");
                out.flush();
                return false;
            }

            return true;
        }
}

