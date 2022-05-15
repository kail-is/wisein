package com.wisein.wiselab.controller;

import com.wisein.wiselab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

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
	public ResponseEntity<String> testHelloWorld (HttpServletRequest request) {

		return new ResponseEntity<String>("hello World!!!!", new HttpHeaders(), HttpStatus.valueOf(200));
	}
}