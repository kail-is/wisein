package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;
import com.wisein.wiselab.service.MatzipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class matzipController {

	@Autowired
	MatzipService service;

	@GetMapping(value = "/matzipList")
	public String main(Model model, CompanyDTO companyDTO) throws Exception {

		List<CompanyDTO> selectCompany = service.company();

		model.addAttribute("selectCompany", selectCompany);

		return "cmn/foodList";
	}

	@GetMapping(value = "/matzip")
	public String getMatzip(@RequestParam int id, Model model) throws Exception {

		System.out.println("di : " +id);

		MatzipDTO matzipDTO = service.selectMatzip(id);

		List<RecmDTO> recmDTOList = service.selectMzRecm(id);
		model.addAttribute("matzip", matzipDTO);
		model.addAttribute("recmList", recmDTOList);

		return "cmn/foodDetail";
	}

	@GetMapping(value="/matzipBoard")
	public String matzipBoard () throws Exception {
		return "cmn/matzipBoard";
	}

	@GetMapping(value="/regMatzip")
	public String regMatzip (HttpServletRequest request, RecmDTO recmDTO, MatzipDTO matzipDTO) throws Exception {

		recmDTO.setWriter(request.getParameter("writer"));
		recmDTO.setRefMatzip(Integer.parseInt(request.getParameter("matzipId")));
		recmDTO.setSubject(request.getParameter("subject"));
		recmDTO.setContent(request.getParameter("content"));
		recmDTO.setStar(Float.parseFloat(request.getParameter("star")));

		matzipDTO.setCategory(request.getParameter("category"));
		matzipDTO.setId(Integer.parseInt(request.getParameter("matzipId")));
		matzipDTO.setMatzipData(request.getParameter("matzipData"));

		service.insertMzBoard(matzipDTO, recmDTO);

		return "redirect:/matzipList";
	}

	@GetMapping(value="/updRecm")
	public String updRecm(@RequestParam int id, Model model) throws Exception {

		RecmDTO recmDTO = service.selectRecm(id);
		model.addAttribute("recm", recmDTO);

		return "cmn/matzipUpd";
	}

	@GetMapping(value="/putRecm")
	public String putRecm(HttpServletRequest request, RecmDTO recmDTO) throws Exception {

		recmDTO.setNum(Integer.parseInt(request.getParameter("num")));
		recmDTO.setSubject(request.getParameter("subject"));
		recmDTO.setContent(request.getParameter("content"));
		recmDTO.setStar(Integer.parseInt(request.getParameter("star")));

		service.updRecm(recmDTO);

		return "cmn/foodDetail";
	}

	@GetMapping(value="/delRecm")
	public String delRecm (@RequestParam int num) throws Exception {
		service.delRecm(num);
		return "cmn/foodDetail";
	}

	@GetMapping(value="/recmCnt")
	public String recmCnt (@RequestParam int id, RedirectAttributes ra) throws Exception {
		int cnt = service.recmCnt(id);
		if (cnt == 0) {
			return "redirect:/matzipList";
		} else {
			ra.addAttribute("id", id);
			return "redirect:/matzip";
		}

	}

	@ExceptionHandler(NullPointerException.class)
	public String nullCheck(){
		return "redirect:/matzipList";
	}

}