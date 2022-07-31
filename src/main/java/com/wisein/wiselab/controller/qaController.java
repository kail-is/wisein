package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.common.paging.PagingTagCustom;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.service.QaListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class qaController {

    @Autowired
    QaListService qaListservice;
    
    private final AbstractPagingCustom PagingTagCustom;

    @GetMapping(value="/qalist")
    public String qaList (@ModelAttribute("qaListDTO") QaListDTO qaListDTO, Model model) throws Exception {
        List<QaListDTO> qaList = new ArrayList<>();
        qaList = qaListservice.selectQaList(qaListDTO);
        
        qaListDTO.setTotalRecordCount(qaListservice.selectBoardTotalCount(qaListDTO));
        String pagination = PagingTagCustom.render(qaListDTO);

        if(qaList.size() > 0) {
            for (int i = 0; i < qaList.size(); i++) {
                System.out.println(i + " : " + qaList.get(i));
            }
        }
        
        model.addAttribute("qaList", qaList);
        model.addAttribute("pagination", pagination);

        return "cmn/qaList";
    }

    @GetMapping(value="/qaBoard")
    public String qaBoard () throws Exception {
        return "board/qaBoard";
    }

    @GetMapping(value="/regQaBoard")
    public String regQaBoard (HttpServletRequest request, QaListDTO qaListDTO) throws Exception {

        qaListDTO.setCategory("DB");
        qaListDTO.setWriter("test2");
        qaListDTO.setSubject(request.getParameter("title"));
        qaListDTO.setContent(request.getParameter("content"));

        System.out.println(qaListDTO.toString());

        qaListservice.insertQaBoard(qaListDTO);

        return "redirect:/qalist";
    }

    @GetMapping(value="/qaDetail")
    public String qaDetail (HttpServletRequest request, QaListDTO qaListDTO, Model model) throws Exception {

        System.out.println("qaNum : " + qaListDTO.getNum());

        QaListDTO dto = qaListservice.selectQaOne(qaListDTO);

        System.out.println(dto.toString());
        System.out.println(dto.getContent());

        model.addAttribute("qaListDTO", dto);
        model.addAttribute("content", dto.getContent());

        return "cmn/qaDetail";
    }
}
