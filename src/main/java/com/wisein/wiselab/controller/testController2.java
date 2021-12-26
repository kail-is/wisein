package com.wisein.wiselab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class testController2 {
	@GetMapping(value="/main")
	public String main () throws Exception {
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
}