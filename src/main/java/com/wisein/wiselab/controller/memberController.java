package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.AuthKeyConfig;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class memberController {

	@Autowired
	MemberService service;

	@Autowired
	BCryptPasswordEncoder passEncoder;

	@GetMapping(value="/register")
	public String getRegister () throws Exception {
		return "reg";
	}

	@PostMapping(value = "/register")
	public String postRegister (MemberDTO dto, AuthKeyConfig tmpKey) throws Exception {

		String tempKey = tmpKey.tempKeyCreate();
		dto.setAuthState(tempKey);

		String inputPw = dto.getPw();
		String passEncd = passEncoder.encode(inputPw);
		dto.setPw(passEncd);

		service.register(dto);

		return "emailSend";
	}

	@GetMapping(value="/login")
	public String getLogin () throws Exception {
		return "login";
	}

	@PostMapping(value = "/login")
	public String postLogin(MemberDTO dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {

		HttpSession session= req.getSession();
		MemberDTO login = service.login(dto);
		String lgFailMessage;

		if (login != null) {
			boolean passMat = passEncoder.matches(dto.getPw(), login.getPw());
			if(passMat) {
				List<FileDTO> memberImgList = service.memImgList(login.getId());
				login.setFileList(memberImgList);
				session.setAttribute("member", login);
			} else {
				session.setAttribute("member", null);
				lgFailMessage ="로그인에 실패했습니다.";
				rttr.addFlashAttribute("msg", lgFailMessage);

				return "redirect:/";
			}
		} else {
			lgFailMessage ="로그인에 실패했습니다.";
			rttr.addFlashAttribute("msg", lgFailMessage);

			return "redirect:/";
		}

		return "redirect:/";
	}

	@GetMapping(value = "/user/logout")
	public String logout(HttpSession session) throws Exception {
		service.logout(session);
		return "redirect:/";
	}


	@GetMapping(value = "/user/update")
	public String getModifyUser() throws Exception {

		return "upd";
	}


	@PostMapping(value = "/user/update")
	public String postModifyUser(MemberDTO dto, HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

		if (dto.getPw().length() > 1) {
			String inputPw = dto.getPw();
			String passEncd = passEncoder.encode(inputPw);
			dto.setPw(passEncd);
		}else {
			dto.setPw(null);
		}

		service.modify(dto, multipartHttpServletRequest);

		// 회원 정보 수정 뒤 세션 등록 dto 수정
		MemberDTO login = service.login(dto);
		List<FileDTO> memberImgList = service.memImgList(login.getId());
		login.setFileList(memberImgList);
		session.setAttribute("member", login);

		return "redirect:/";
	}


	@ResponseBody
	@GetMapping(value = "/delImgFile")
	public Map<String, String> deleteUserImg(@RequestParam("delImgFileNm") String fileNm) throws Exception {

		service.delUserImg(fileNm);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("code","0000");
		map.put("msg", "삭제 완료.");
		return map;
	}

	@GetMapping(value = "/user/withdraw")
	public String getWithdrawalUser() throws Exception {
		return "withdrawal";
	}

	@PostMapping(value = "/user/withdraw")
	public String withdrawal(MemberDTO dto, HttpSession session) throws Exception {
		service.withdraw(dto, session);
		session.invalidate();
		return "redirect:/";
	}

}