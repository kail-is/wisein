package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.AuthKeyConfig;
import com.wisein.wiselab.config.MailHandler;
import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dao.memberDAOImpl;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class testController2 {

	@Autowired
	MemberService service;
	@Autowired
	MailHandler mailHandler;
	@Autowired
	BCryptPasswordEncoder passEncoder;

	@GetMapping(value = "/main")
	public String main() throws Exception {

		return "cmn/main";
	}

	@GetMapping(value = "/fooddetail")
	public String foodDetail() throws Exception {
		return "cmn/foodDetail";
	}

	@GetMapping(value = "/foodlist")
	public String foodList() throws Exception {
		return "cmn/foodList";
	}

	@GetMapping(value = "/qalist")
	public String qaList() throws Exception {
		return "cmn/qaList";
	}

	@GetMapping(value = "/databoard")
	public String dataBoard() throws Exception {
		return "cmn/dataBoard";
	}

	@GetMapping(value = "/reg")
	public String getRegister() throws Exception {
		return "reg";
	}

	@PostMapping(value = "/reg")
	public String postRegister(MemberDTO dto,
							   AuthKeyConfig tmpKey) throws Exception {

		//회원가입 하면 이메일 인증에 필요한 임시키 생성
		//dto의 auth_state에 저장
		String tempKey = tmpKey.tempKeyCreate();
		dto.setAuthstate(tempKey);

		// Password Encoding by BCryptPasswordEncoder
		String inputPw = dto.getPw();
		String passEncd = passEncoder.encode(inputPw);
		dto.setPw(passEncd);

		service.register(dto);
		return "redirect:/reg";
	}


	@GetMapping(value = "/login")
	public String getLogin() throws Exception {
		return "login";
	}

	@PostMapping(value = "/login")
	public String postLogin(MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {

		HttpSession session = req.getSession();
		MemberDTO login = service.login(dto);
		String lgFailMessage; // 로그인 실패 시의 RedirectAttributes

		String authKey = service.findTempKey(login.getId());
		System.out.println("인증키 : " + authKey);

		if (!authKey.equals("Y")) {
			session.setAttribute("authUser", login.getId());
			return "auth";
		}

		if (login != null) {
			// 해당 MemberDTO를 받아왔을 때에만 출력
			boolean passMat = passEncoder.matches(dto.getPw(), login.getPw());

			if (passMat) {
				// matches를 통한 패스워드 매칭 시 로그인 성공
				session.setAttribute("member", login);
			} else {
				// login이 null || 패스워드 매칭 실패
				session.setAttribute("member", null);
				lgFailMessage = "로그인에 실패했습니다.";
				rttr.addFlashAttribute("msg", lgFailMessage);
				return "redirect:/login";
			}

		} else {
			//  해당 MemberDTO를 받아오지 못했을 경우 - mem_status = 'N' 등
			lgFailMessage = "로그인에 실패했습니다.";
			rttr.addFlashAttribute("msg", lgFailMessage);
			return "redirect:/login";
		}

		return "redirect:/login";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		service.logout(session);
		return "redirect:/";
	}
	
	//메일 보냄
	@ResponseBody
	@GetMapping(value = "/authMailSend")
	public void authMailSend(HttpSession session,
							 @RequestParam("email") String email) {

		String userName = (String) session.getAttribute("authUser");

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
				"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
				userName +
				"		님 안녕하세요.<br />" +
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
		mailHandler.send(email, "WISE IN 이메일 인증", emailcontent.toString());
	}
	
	//메일 인증 클릭시 인증 상태 업데이트
	@GetMapping(value = "/authSuccess")
	public void authSuccess(HttpSession session, HttpServletResponse response,
							Model model) throws Exception {
		String id = (String) session.getAttribute("authUser");
		service.authStateUpdate(id);

		model.addAttribute("checkId", id);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('이메일 인증이 완료되었습니다.'); window.close(); </script>;");

	}

	//인증 체크
	@ResponseBody
	@GetMapping(value = "/authCheck")
	public Map<String, String> authCheck(HttpSession session, HttpServletResponse response) throws Exception {
		String id = (String) session.getAttribute("authUser");
		Map<String, String> map = new HashMap<String, String>();

		String state = service.findTempKey(id);
		System.out.print(state);

		map.put("msg", state);

		if (state.equals("Y")) {
			session.removeAttribute("authUser");
		}

		return map;
	}

}