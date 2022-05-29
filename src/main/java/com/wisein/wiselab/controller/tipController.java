package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.TipBoardDTO;
import com.wisein.wiselab.service.TipBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class tipController {

    @Autowired
    TipBoardService tipBoardService;

    //다건 조회
    @GetMapping(value="/tipList")
    public String tipList (Model model) throws Exception {

        List<TipBoardDTO> tipList = new ArrayList<>();

        tipList = (List<TipBoardDTO>) tipBoardService.selectTipList();
        if(tipList.size() > 0) {
            for (int i = 0; i < tipList.size(); i++) {
                System.out.println(i + " : " + tipList.get(i));
            }
        }

        model.addAttribute("tipList", tipList);
        return "cmn/tipList";
    }

    //단건 조회
    @GetMapping(value="/tipDetail")
    public String qaDetail (HttpServletRequest request, TipBoardDTO TipBoardDTO, Model model) throws Exception {

        System.out.println("tipNum : " + TipBoardDTO.getNum());

        TipBoardDTO dto = tipBoardService.selectTipOne(TipBoardDTO);

        System.out.println(dto.toString());
        System.out.println(dto.getContent());

        model.addAttribute("TipBoardDTO", dto);
        model.addAttribute("content", dto.getContent());

        return "cmn/tipDetail";
    }

    //등록
    @GetMapping (value="/regTip")
    public String regTip () throws Exception {
        return "cmn/tipBoard";
    }

    @PostMapping(value="/regTip")
    public String regTip (HttpServletRequest request, TipBoardDTO TipBoardDTO) throws Exception {
        //여기부터
        TipBoardDTO.setCategory("DB");
        TipBoardDTO.setWriter("test2");
        //여기까지 카테고리 / writer 받아와서 넣도록 나중에 수정
        TipBoardDTO.setSubject(request.getParameter("title"));
        TipBoardDTO.setContent(request.getParameter("content"));
        System.out.println("======================");
        System.out.println(TipBoardDTO.toString());
        System.out.println("======================");

        tipBoardService.insertTipBoard(TipBoardDTO);

        return "redirect:/tipList";
    }
}
