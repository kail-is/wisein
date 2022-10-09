package com.wisein.wiselab.config.interceptor;

import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MatzipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class CheckWriterInterceptor implements HandlerInterceptor {

    @Autowired
    private MatzipService matzipService;

    private MemberDTO memberDTO;

    public CheckWriterInterceptor(MemberDTO memberDTO, MatzipService matzipService) {
        this.matzipService = matzipService;
        this.memberDTO = memberDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res, Object handler)
            throws Exception {

        int brdNum = Integer.parseInt(req.getParameter("id"));

        String writer = matzipService.selectRecm(brdNum).getWriter();
        HttpSession session = req.getSession();

        MemberDTO member = session.getAttribute("member") != null ? (MemberDTO)session.getAttribute("member") : null;

        if(!member.getId().equals(writer)) {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('자신의 글만 수정/삭제할 수 있습니다.');");
            out.println("history.back();");
            out.println("</script>");
            out.flush();
            return false;
        }

        return true;
    }


}
