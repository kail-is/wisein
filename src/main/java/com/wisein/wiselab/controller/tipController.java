package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.TipBoardDTO;
import com.wisein.wiselab.service.tipBoardService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class tipController {

    @Autowired
    tipBoardService tipBoardservice;

    @GetMapping(value="/tipBoard")
    public String tipBoard (Model model) throws Exception {

        List<TipBoardDTO> tipBoard = new ArrayList<>();

        tipBoard = (List<TipBoardDTO>) tipBoardservice.selectTipBoard();
        if(tipBoard.size() > 0) {
            for (int i = 0; i < tipBoard.size(); i++) {
                System.out.println(i + " : " + tipBoard.get(i));
            }
        }

        model.addAttribute("tipBoard", tipBoard);
        return "cmn/tipList";
    }

    @GetMapping(value="/tipList")
    public String tipBoard () throws Exception {
        return "cmn/tiplist";
    }

    @GetMapping(value="/regTipBoard")
    public String regTipBoard (HttpServletRequest request, TipBoardDTO tipBoardDTO) throws Exception {

        tipBoardDTO.setCategory("DB");
        tipBoardDTO.setWriter("test2");
        tipBoardDTO.setSubject(request.getParameter("title"));
        tipBoardDTO.setContent(request.getParameter("content"));

        System.out.println(tipBoardDTO.toString());

        tipBoardservice.insertTipBoard(tipBoardDTO);

        return "redirect:/tiplist";
    }

}
