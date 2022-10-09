package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.AuthKeyConfig;
import com.wisein.wiselab.config.MailHandler;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class EmailController {

    @Autowired
    MemberService service;
    @Autowired
    MailHandler mailHandler;
    @Autowired
    BCryptPasswordEncoder passEncoder;

    //가입 인증 메일 발송
    @ResponseBody
    @GetMapping(value = "/authMailSend")
    public void authMailSend(HttpSession session,
                             @RequestParam("email_Id") String email, MailDTO mailDTO) {

        session.setAttribute("authUser", email);
        String address = "@wiselab.co.kr";
        email = email+address;
        System.out.println("이메일 : "+email);

        StringBuffer emailcontent = new StringBuffer();
        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(" <div" +
                "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid black; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">WISN IN</span><br />" +
                "		<span style=\"color: #02b875\">메일인증</span> 안내입니다." +
                "	</h1>\n" +
                "		아래 <b style=\"color: #02b875\">메일 인증</b> 버튼을 클릭하여 인증을 완료해주세요.<br />" +
                "		감사합니다." +
                "	</p>" +
                "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\" href=\"http://localhost:8080/authSuccess\">" +
                "		<p" +
                "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">" +
                "			메일 인증</p>" +
                "	</a>" +
                "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>" +
                " </div>"
        );
        emailcontent.append("</body>");
        emailcontent.append("</html>");

        mailDTO.setTitle("WISE IN 이메일 인증");
        mailDTO.setToAddress(email);
        mailDTO.setMessageContent(emailcontent.toString());

        mailHandler.send(mailDTO);
    }

    //가입 메일 인증 완료
    @GetMapping(value = "/authSuccess")
    public void authSuccess(HttpSession session, HttpServletResponse response,
                            Model model) throws Exception {
        String id = (String) session.getAttribute("authUser");
        System.out.println("이메일 : " +id);
        service.authStateUpdate(id);

        model.addAttribute("checkId", id);

        String path = "/";

        session.removeAttribute("authUser");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('이메일 인증이 완료되었습니다.'); location.href='"+path+"' </script>;");
    }

    //인증 체크
    @ResponseBody
    @GetMapping(value = "/authCheck")
    public Map<String, String> authCheck(@RequestParam("login_Id") String id) throws Exception {

        Map<String, String> auth_Map = new HashMap<String, String>();

        String authKey = service.findTempKey(id);

        int idExist = service.authIdExist(id);
        String id_Exist = Integer.toString(idExist);

        auth_Map.put("idExist", id_Exist);
        auth_Map.put("authKey", authKey);

        return auth_Map;
    }

    // 비밀번호 변경 메일 전송
    @ResponseBody
    @GetMapping(value = "/pwMailSend")
    public void findPwMailSend(HttpSession session,
                               @RequestParam("user_id") String email, MailDTO mailDTO) throws Exception {

        session.setAttribute("authUser", email);

        AuthKeyConfig tmpKey = new AuthKeyConfig();
        MemberDTO memberDto = new MemberDTO();

        String memberToken = tmpKey.tempKeyCreate();
        String changePw = tmpKey.tempKeyPassword(10);

        // String passEncd = passEncoder.encode(changePw);
        // 이렇게 저장해서 쓰면 암호화 해싱 값이 달라서인지 안 됨

        memberDto.setId(email);
        memberDto.setMemberToken(memberToken);
        memberDto.setPw(changePw);
        // memberDto.setPw(passEncd);

        String address = "@wiselab.co.kr";
        email = email+address;

        StringBuffer emailcontent = new StringBuffer();
        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(" <div" +
                "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid black; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">WISN IN</span><br />" +
                "		<span style=\"color: #02b875\">임시 비밀번호</span> 안내입니다." +
                "	</h1>\n" +
                "		아래 <b style=\"color: #02b875\">임시 비밀번호 변경</b> 버튼을 누르시면 발급된 임시 비밀번호로 패스워드가 수정됩니다.<br />" +
                "		로그인 뒤 비밀번호 수정을 권장합니다.<br />" +
                "		감사합니다." +
                "	</p>" +
                "	<p" +
                "	 style=\"text-align: center; margin: 2em;\">" +
                "			임시 비밀번호</p>" +
                "	<p" +
                "	 style=\"text-align: center; margin: 2em; font-weight: bold;\">" +
                    changePw +
                "	</p>" +
                "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\" href=\"http://localhost:8080/chgPw?memberToken=" +
                memberToken +
                "\">" +
                "		<p" +
                "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">" +
                "			임시 비밀번호 변경</p>" +
                "	</a>" +
                "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>" +
                " </div>"
        );
        emailcontent.append("</body>");
        emailcontent.append("</html>");

        mailDTO.setTitle("WISE IN 패스워드 변경");
        mailDTO.setToAddress(email);
        mailDTO.setMessageContent(emailcontent.toString());

        mailHandler.send(mailDTO);

        service.addChgePw(memberDto);

    }


    //비밀번호 변경 메일 인증 완료
    @ResponseBody
    @GetMapping(value = "/chgPw")
    public void findPwMailSendSuccess(@RequestParam("memberToken") String token,
                                                     HttpSession session,
                                                     HttpServletResponse response) throws Exception {

        String id = (String) session.getAttribute("authUser");
        System.out.println("이메일 : " +id);

        // 비밀번호 변경 테이블에서 토큰을 통한 업데이트
        service.modMemByToken(token);

        String path = "/";

        session.removeAttribute("authUser");

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script>alert('임시 패스워드로 변경되었습니다.'); location.href='"+path+"' </script>;");
    }


    // 폐업 신고 메일 전송
    @ResponseBody
    @GetMapping(value = "/reportClosed")
    public void reportClosed(@RequestParam("matzip_id") String matzipId, MailDTO mailDTO) throws Exception {

        String address = "wisein.adm@gmail.com";

        StringBuffer emailcontent = new StringBuffer();
        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(" <div" +
                "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid black; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">WISN IN</span><br />" +
                "		<span style=\"color: #02b875\">폐업 신고</span> 안내" +
                "	</h1>\n" +
                "	</p>" +
                "	<p" +
                "	 style=\"text-align: center; margin: 2em;\">" +
                "			해당 맛집 확인</p>" +
                "	<p" +
                "	 style=\"text-align: center; margin: 2em; font-weight: bold;\">" +
                "	<a style=\"color: black; text-decoration: none; text-align: center;\" href=\"http://localhost:8080/matzip?id=" +
                matzipId +
                "\">" +
                " 맛집 이동" +
                "	</p>" +
                "	</a>" +
                "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\" href=\"http://localhost:8080/addClosed?matzipId=" +
                matzipId +
                "\">" +
                "		<p" +
                "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #02b875; line-height: 45px; vertical-align: middle; font-size: 16px;\">" +
                "			폐업 처리</p>" +
                "	</a>" +
                "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>" +
                " </div>"
        );
        emailcontent.append("</body>");
        emailcontent.append("</html>");

        mailDTO.setTitle("WISE IN 맛집 폐업 신고");
        mailDTO.setToAddress(address);
        mailDTO.setMessageContent(emailcontent.toString());

        mailHandler.send(mailDTO);


    }


}
