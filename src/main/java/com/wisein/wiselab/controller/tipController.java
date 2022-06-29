package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.common.paging.PaginationInfo;
import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import com.wisein.wiselab.service.CommentService;
import com.wisein.wiselab.service.TipBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class tipController {

    @Autowired
    TipBoardService tipBoardService;

    @Autowired
    CommentService commentService;

    private final AbstractPagingCustom PagingTagCustom;

    //다건 조회
    @GetMapping(value="/tipList")
    public String tipList (@ModelAttribute("TipBoardDTO") TipBoardDTO dto, Model model) throws Exception {
        List<TipBoardDTO> tipList = new ArrayList<>();
        tipList = tipBoardService.selectTipList(dto);

        dto.setTotalRecordCount(tipBoardService.selectBoardTotalCount(dto));
        String pagination = PagingTagCustom.render(dto);

        System.out.println(pagination);

        model.addAttribute("tipList", tipList);
        model.addAttribute("pagination", pagination);


        return "cmn/tipList";
    }

    //단건 조회
    @GetMapping(value="/tipDetail")
    public String tipDetail (HttpServletRequest request, TipBoardDTO dto, Model model,  @RequestParam("num") int num) throws Exception {
        TipBoardDTO TipBoardDTO = null;
        List<CommentDTO> commentList = new ArrayList<>();
        String brdRef = "tip||"+num;
        int commentNum = commentService.selectCommentTotalCount(brdRef);

        if(dto.getNum() !=0){
            TipBoardDTO = tipBoardService.selectTipOne(dto);
            commentList = commentService.selectComment(brdRef);
        }else{
            TipBoardDTO.setNum(num);
            TipBoardDTO = tipBoardService.selectTipOne(dto);
            commentList = commentService.selectComment(brdRef);
        }


        model.addAttribute("tipBoardDTO", TipBoardDTO);
        model.addAttribute("content", TipBoardDTO.getContent());
        model.addAttribute("commentNum", commentNum);
        model.addAttribute("commentList", commentList);

        return "cmn/tipDetail";
    }

    //등록
    @GetMapping(value="/regTip")
    public String regTip () throws Exception {
        return "cmn/regTip";
    }

    @PostMapping(value="/regTip")
    public String regTip (HttpServletRequest request, TipBoardDTO dto) throws Exception {
        dto.setWriter("test2");
        tipBoardService.insertTipBoard(dto);

        return "redirect:/tipList";
    }

    //삭제
    @GetMapping(value="/delTip")
    public String delTip (@RequestParam("num") int num) throws Exception {
        tipBoardService.deleteTipBoard(num);
        return "redirect:/tipList";
    }


    //수정
    @GetMapping(value="/updTip")
    public String updTip (TipBoardDTO dto, Model model, HttpSession session) throws Exception {
        dto = tipBoardService.selectTipOne(dto);
        model.addAttribute("TipBoardDTO", dto);
        session.setAttribute("TipBoardDTO", dto);
        return "cmn/updTip";
    }

    @PostMapping(value="/updTip")
    public String updTip(HttpServletRequest request, TipBoardDTO dto, HttpSession session) throws Exception {
        tipBoardService.updateTipBoard(dto);
        session.removeAttribute("TipBoardDTO");
        return "redirect:/tipDetail?num="+dto.getNum();
    }

    //이미지 url 반환
    @ResponseBody
    @RequestMapping(value="/imgUrlReg")
    public String imgUrlReg(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session) throws Exception {
        return tipBoardService.imgUrlReg(multipartHttpServletRequest, session);
    }

    //댓글 등록
    @PostMapping(value = "/regTipComm")
    public void tipRegComment (CommentDTO dto) throws Exception {
        commentService.insertComment(dto);
    }

    //댓글 삭제
    @PostMapping(value = "/delTipComm")
    public void tipDelComment (int num) throws Exception {
        commentService.deleteComment(num);
    }
}
