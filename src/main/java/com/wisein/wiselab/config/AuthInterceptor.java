package com.wisein.wiselab.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wisein.wiselab.dto.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest req,
                                 HttpServletResponse res, Object obj) throws Exception {

            HttpSession session = req.getSession();
            MemberDTO member = (MemberDTO)session.getAttribute("member");

            if(member == null) {
                res.sendRedirect("/");
                return false;
            }

            return true;
        }
}

