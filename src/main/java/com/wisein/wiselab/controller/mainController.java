package com.wisein.wiselab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class mainController {

	@GetMapping(value = "/")
	public String main() throws Exception {
		return "redirect:qalist";
	}

	@GetMapping(value = "/totalSearch")
	public String totalSearch() throws Exception {
		return "cmn/totalSearch";
	}

}