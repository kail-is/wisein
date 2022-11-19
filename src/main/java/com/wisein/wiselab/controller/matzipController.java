package com.wisein.wiselab.controller;

import com.wisein.wiselab.dao.MatzipDAO;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;
import com.wisein.wiselab.service.MatzipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class matzipController {

	@Autowired
	MatzipService service;
	@Autowired
	MatzipDAO dao;

	//맛집 리스트 메인
	@GetMapping(value = "/matzipList")
	public String main(Model model, CompanyDTO companyDTO) throws Exception {

		List<CompanyDTO> comCategoryList = dao.companyCategory();
		model.addAttribute("comCategory", comCategoryList);

		return "cmn/foodList";
	}

	@GetMapping(value = "/matzip")
	public String getMatzip(@RequestParam int id, Model model) throws Exception {

		MatzipDTO matzipDTO = service.selectMatzip(id);
		List<RecmDTO> recmDTOList = service.selectMzRecm(id);
		model.addAttribute("matzip", matzipDTO);
		model.addAttribute("recmList", recmDTOList);

		return "cmn/foodDetail";
	}

	@GetMapping(value="/matzipBoard")
	public String matzipBoard (Model model) throws Exception {

		List<CompanyDTO> companyList = dao.companyList();

		System.out.println("#######"+ companyList);;

		model.addAttribute("company", companyList);

		return "board/matzipBoard_TOBE";
	}

	@PostMapping(value="/regMatzip")
	@ResponseBody
	public ResponseEntity<String> regMatzip(@RequestBody Map<String, String> params) throws Exception {

		RecmDTO recmDTO = new RecmDTO();
		MatzipDTO matzipDTO = new MatzipDTO();

		recmDTO.setWriter(params.get("writer"));
		recmDTO.setRefMatzip(Integer.parseInt(params.get("matzipId")));
		recmDTO.setSubject(params.get("subject"));
		recmDTO.setContent(params.get("content"));
		recmDTO.setStar(Float.parseFloat(params.get("star")));

		matzipDTO.setCategory(params.get("category"));
		matzipDTO.setId(Integer.parseInt(params.get("matzipId")));
		matzipDTO.setMatzipData(params.get("matzipData"));
		matzipDTO.setAddressName(params.get("addressName"));

		service.insertMzBoard(matzipDTO, recmDTO);

		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping(value="/updRecm")
	public String updRecm(@RequestParam int id, Model model) throws Exception {

		RecmDTO recmDTO = service.selectRecm(id);
		model.addAttribute("recm", recmDTO);
		MatzipDTO matzipDTO = service.selectMatzip(recmDTO.getRefMatzip());
		model.addAttribute("matzip", matzipDTO);

		return "board/matzipBoard_TOBE";
	}

	@PostMapping(value="/putRecm")
	@ResponseBody
	public ResponseEntity<String> putRecm(@RequestBody Map<String, String> params) throws Exception {

		RecmDTO recmDTO = new RecmDTO();

		recmDTO.setNum(Integer.parseInt(params.get("num")));
		recmDTO.setSubject(params.get("subject"));
		recmDTO.setContent(params.get("content"));
		recmDTO.setStar(Integer.parseInt(params.get("star")));

		service.updRecm(recmDTO);

		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value="/delRecm")
	public int delRecm (@RequestParam int id) throws Exception {
		service.delRecm(id);
		int recmMatzipId = dao.recmMatzipId(id);

//		return "cmn/foodDetail";
		return recmMatzipId;
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

	@GetMapping(value="/addClosed")
	public String updClosedStat (@RequestParam int matzipId, RedirectAttributes ra) throws Exception {
		service.updClosedStat(matzipId);

		ra.addAttribute("id", matzipId);
		return "redirect:/matzip";
	}

	@ResponseBody
	@GetMapping(value = "/getPostNum")
	public int getPostNum(String writer, String subject) throws Exception {
		RecmDTO dto = new RecmDTO();
		dto.setWriter(writer);
		dto.setSubject(subject);
		int postNum = service.selectRecmPostNum(dto);
	 	return postNum;
	}

	@ExceptionHandler(NullPointerException.class)
	public String nullCheck(){
		return "redirect:/matzipList";
	}

}