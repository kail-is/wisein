package com.wisein.wiselab.controller;

import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class testRestController {
	@RequestMapping(value="/test")
	public String testHelloWorld () {
//		log.info("ggood");
		return "hello World!!!!";
	}
}