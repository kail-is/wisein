package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.service.QaListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class qaController {

    @Autowired
    QaListService qaListservice;

    @GetMapping(value="/qalist")
    public String qaList (Model model) throws Exception {

        List<QaListDTO> qaList = new ArrayList<>();

        qaList = (List<QaListDTO>) qaListservice.selectQaList();

//        if(qaList.size() > 0) {
//            for (int i = 0; i < qaList.size(); i++) {
//                System.out.println(i + " : " + qaList.get(i));
//            }
//        }

        model.addAttribute("qaList", qaList);

        return "cmn/qaList";
    }

    @GetMapping(value="/qaBoard")
    public String qaBoard () throws Exception {
        return "cmn/qaBoard";
    }

    @PostMapping(value="/regQaBoard")
    public String regQaBoard (HttpServletRequest request, QaListDTO qaListDTO) throws Exception {

        qaListDTO.setCategory("DB");
        qaListDTO.setWriter("test2");


//        System.out.println(qaListDTO.toString());

        qaListservice.insertQaBoard(qaListDTO);

        return "redirect:/qalist";
    }

    @GetMapping(value="/qaDetail")
    public String qaDetail (HttpServletRequest request
                            , QaListDTO dto
                            , Model model
                            , @RequestParam("num") int num) throws Exception {

        QaListDTO qaListDTO = null;

        if (dto.getNum() != 0){
//            System.out.println("/qaDetail qaListDTO.getNum() : " + qaListDTO.getNum());
            qaListDTO = qaListservice.selectQaOne(dto);
        } else {
//            System.out.println("/qaDetail num : " + num);
            qaListDTO.setNum(num);
            qaListDTO = qaListservice.selectQaOne(dto);
        }

//        System.out.println(qaListDTO.toString());
//        System.out.println(qaListDTO.getContent());

        model.addAttribute("qaListDTO", qaListDTO);
        model.addAttribute("content", qaListDTO.getContent());

        return "cmn/qaDetail";
    }

    @GetMapping(value = "/qaDelete")
    public String qaDelete (int num) throws Exception {

//        System.out.println("num : " + num);

        qaListservice.deleteQaBoard(num);

        return "redirect:/qalist";
    }

    @GetMapping(value = "/qaUpdate")
    public String qaUpdate (QaListDTO qaListDTO
                            , Model model) throws Exception {

//        System.out.println("/qaUpdate qaListDTO.getNum() : " + qaListDTO.getNum());

        qaListDTO = qaListservice.selectQaOne(qaListDTO);

//        System.out.println("/qaUpdate qaListDTO.toString() : " + qaListDTO.toString());
//        System.out.println("qaListDTO.getContent() : " + qaListDTO.getContent());

        model.addAttribute("qaListDTO", qaListDTO);
//        model.addAttribute("content", qaListDTO.getContent());


//        qaListservice.deleteQaBoard(num);

        return "cmn/qaBoard";
    }

    @ResponseBody
    @PostMapping(value = "/qaUpdatePro")
    public void qaUpdatePro (QaListDTO qaListDTO) throws Exception {

//        System.out.println("/qaUpdatePro qaListDTO : " + qaListDTO.toString());

        qaListservice.updateQaBoard(qaListDTO);
    }
}




















