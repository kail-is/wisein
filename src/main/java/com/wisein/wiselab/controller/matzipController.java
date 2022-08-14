package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.JsonInstance;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;
import com.wisein.wiselab.service.MatzipService;
import com.wisein.wiselab.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class matzipController {

	@Autowired
	MatzipService service;


	@Autowired
	MemberService memberService;

	JSONObject jObject;
	JSONParser jParser;
	JSONArray jArray;

	public matzipController() {
		this.jObject = JsonInstance.getjObjectInstance();
		this.jParser = JsonInstance.getJsonParserInstatnce();
		this.jArray = JsonInstance.getJsonArrayInstance();
	}


	//맛집 리스트 메인
	@GetMapping(value = "/matzipList")
	public String main(Model model, CompanyDTO companyDTO) throws Exception {

		//카테고리 select
		List<CompanyDTO> selectCompany = service.company();
		//회사ㄷ
		List<CompanyDTO> companyList = service.companyList();
		List<CompanyDTO> company = new ArrayList<>();

		for (int i=0; i<companyList.size(); i++) {
			companyDTO = new CompanyDTO();

			try {
				jObject = (JSONObject) jParser.parse(companyList.get(i).getCompanydata());
				jArray = (JSONArray) jObject.get("documents");
				jObject = (JSONObject) jArray.get(0);

				List<CompanyDTO> siteCountList = service.matzipCount(companyList.get(i).getLocation());

				companyDTO.setId(companyList.get(i).getId());
				companyDTO.setLocation(companyList.get(i).getLocation());
				companyDTO.setMatzipCount(siteCountList.get(0).getMatzipCount());
				companyDTO.setCompanyName((String) jObject.get("place_name"));
				companyDTO.setCompanyLoc((String) jObject.get("address_name"));

				company.add(companyDTO);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("companyList", company);
		model.addAttribute("selectCompany", selectCompany);

		return "cmn/foodList";
	}

	@GetMapping(value = "/matzip")
	public String getMatzip(@RequestParam int id, Model model) throws Exception {


		MatzipDTO matzipDTO = service.selectMatzip(id);

		List<RecmDTO> recmDTOList = service.selectMzRecm(id);
		model.addAttribute("matzip", matzipDTO);
		model.addAttribute("recmList", recmDTOList);

		// test
		List<FileDTO> memberImgList = memberService.memImgList("jeansuh42");
		model.addAttribute("fileList", memberImgList);

		return "cmn/foodDetail";
	}

	@GetMapping(value="/matzipBoard")
	public String matzipBoard () throws Exception {
		return "board/matzipBoard";
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
		matzipDTO.setAddressName(request.getParameter("addressName"));

		service.insertMzBoard(matzipDTO, recmDTO);

		return "redirect:/matzipList";
	}

	@GetMapping(value="/updRecm")
	public String updRecm(@RequestParam int id, Model model) throws Exception {

		RecmDTO recmDTO = service.selectRecm(id);
		model.addAttribute("recm", recmDTO);
		MatzipDTO matzipDTO = service.selectMatzip(recmDTO.getRefMatzip());
		model.addAttribute("matzip", matzipDTO);

		return "board/matzipUpd";
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

	@GetMapping(value="/addClosed")
	public String updClosedStat (@RequestParam int matzipId, RedirectAttributes ra) throws Exception {
		service.updClosedStat(matzipId);
		ra.addAttribute("id", matzipId);
		return "redirect:/matzip";
	}

	@ExceptionHandler(NullPointerException.class)
	public String nullCheck(){
		return "redirect:/matzipList";
	}

}