package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class testController2 {

	@Autowired
	MemberService service;

	@GetMapping(value="/")
	public String main (String a) throws Exception {
		System.out.println(a);
		return "cmn/main";
	}

	@GetMapping(value="/fooddetail")
	public String foodDetail () throws Exception {
		return "cmn/foodDetail";
	}

	@GetMapping(value="/foodlist")
	public String foodList () throws Exception {
		return "cmn/foodList";
	}

	@GetMapping(value="/qalist")
	public String qaList () throws Exception {
		return "cmn/qaList";
	}

	@GetMapping(value="/databoard")
	public String dataBoard () throws Exception {
		return "cmn/dataBoard";
	}

	@GetMapping(value="/reg")
	public String getRegister () throws Exception {
		return "reg";
	}

	@PostMapping(value = "/reg")
	public String postRegister (MemberDTO dto) throws Exception {
		service.register(dto);
		return "redirect:/";
	}

}