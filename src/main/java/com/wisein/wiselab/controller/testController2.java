package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.MailHandler;
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

	@GetMapping(value = "/totalSearch")
	public String totalSearch() throws Exception {
		return "cmn/totalSearch";
	}

	@GetMapping(value = "/fooddetail")
	public String foodDetail() throws Exception {
		return "cmn/foodDetail";
	}

	@GetMapping(value = "/foodlist")
	public String foodList() throws Exception {
		return "cmn/foodList";
	}

	@GetMapping(value = "/databoard")
	public String dataBoard() throws Exception {
		return "cmn/dataBoard";
	}

	@GetMapping(value="/reg")
	public String getRegister () throws Exception {
		return "reg";
	}

	@GetMapping(value="/tipboard")
	public String tipBoard () throws Exception {
		return "cmn/tipBoard";
	}

	@PostMapping(value = "/reg")
	public String postRegister (MemberDTO dto) throws Exception {
		service.register(dto);
		return "redirect:/";
	}

}