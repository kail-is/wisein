package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.service.QaListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class qaListController {

    @Autowired
    QaListService qaListservice;

    @GetMapping(value="/qalist")
    public String qaList (Model model) throws Exception {

        List<QaListDTO> qaList = new ArrayList<>();

        qaList = (List<QaListDTO>) qaListservice.selectQaList();
        if(qaList.size() > 0) {
            for (int i = 0; i < qaList.size(); i++) {
                System.out.println(i + " : " + qaList.get(i));
            }
        }

        model.addAttribute("qaList", qaList);
        return "cmn/qaList";
    }


}
