package com.wisein.wiselab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class testController2 {
	@RequestMapping(value="/test2")
	public String testHelloWorld () throws Exception {
		return "cmn/index";
	}
}