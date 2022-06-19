package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.service.QaListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
//        if(qaList.size() > 0) {
//            for (int i = 0; i < qaList.size(); i++) {
//                System.out.println(i + " : " + qaList.get(i));
//            }
//        }

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
        return "cmn/qaBoard";
    }

    @GetMapping(value="/regQaBoard")
    public String regQaBoard (HttpServletRequest request, QaListDTO qaListDTO) throws Exception {

        qaListDTO.setWriter("test2");
        qaListDTO.setSubject(request.getParameter("title"));
        qaListDTO.setContent(request.getParameter("content"));

        System.out.println(qaListDTO.toString());

        qaListservice.insertQaBoard(qaListDTO);

        return "redirect:/qalist";
    }

    @GetMapping(value="/qaDetail")
    public String qaDetail (HttpServletRequest request, QaListDTO dto, Model model, @RequestParam("num") int num) throws Exception {

        QaListDTO qaListDTO = null;
        List<QaListDTO> commentQaList = new ArrayList<>();

        System.out.println("================================================================");
        if (dto.getNum() != 0){
            System.out.println("11111111111111111111111111111111");
//            System.out.println("/qaDetail qaListDTO.getNum() : " + qaListDTO.getNum());
            qaListDTO = qaListservice.selectQaOne(dto);
            // 댓글 목록 조회
            commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(qaListDTO.getNum());
        } else {
            System.out.println("2222222222222222222222222222222222222222222");
//            System.out.println("/qaDetail num : " + num);
            qaListDTO.setNum(num);
            qaListDTO = qaListservice.selectQaOne(dto);
        }

//        System.out.println(qaListDTO.toString());
//        System.out.println(qaListDTO.getContent());



        model.addAttribute("qaListDTO", qaListDTO);
        model.addAttribute("content", qaListDTO.getContent());
        model.addAttribute("commentQaList", commentQaList);
        for(int i=0; i<commentQaList.size(); i++){
            System.out.println(commentQaList.get(i).getContent());
            model.addAttribute( "content"+commentQaList.get(i).getNum(), commentQaList.get(i).getContent());
        }


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
    //, @RequestParam @Nullable String num
    //이미지 url 반환
    @ResponseBody
    @RequestMapping(value="/qaImgUrlReg")
    public String qaImgUrlReg(HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest, QaListDTO qaListDTO, Model model) throws Exception {
        System.out.println("qaImgUrlReg : " + qaListDTO.toString());
        return qaListservice.imgUrlReg(multipartHttpServletRequest, session, model);
    }
    @ResponseBody
    @PostMapping(value = "/selectQaNum")
    public void selectQaNum (QaListDTO qaListDTO, HttpSession session) throws Exception {
        System.out.println("/selectQaNum qaListDTO : " + qaListDTO.toString());

        qaListDTO = qaListservice.selectQaNum(qaListDTO);

        System.out.println("qaListDTO : " + qaListDTO.toString());

        session.setAttribute("qaListDTO", qaListDTO);
    }

    @ResponseBody
    @PostMapping(value = "/qaRegComment")
    public void qaRegComment (QaListDTO qaListDTO) throws Exception {

        System.out.println("/qaRegComment qaListDTO : " + qaListDTO.toString());

        qaListservice.insertCommentQaBoard(qaListDTO);
    }



}




















