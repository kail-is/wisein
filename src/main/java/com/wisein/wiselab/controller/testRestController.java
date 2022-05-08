package com.wisein.wiselab.controller;

import com.wisein.wiselab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class testRestController {

	@Autowired
	MemberService service;

	@GetMapping(value = "/idDupChk")
	public int getIdDupChk(String userId) throws Exception {
		return service.idDupChk(userId);
	}

	@RequestMapping(value="/test")
	public String testHelloWorld () {
		return "hello World!!!!";
	}
}