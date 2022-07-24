package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.common.paging.PagingTagCustom;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.service.QaListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
           //     System.out.println(i + " : " + qaList.get(i));
            }
        }

        // 답변개수 구하는 로직
//        Map<String, Integer> adpCount = new HashMap<>();
//        List<Map> adpCountList = new ArrayList<>();
//        int adpCnt = 0;
//        for (int i=0; i<qaList.size(); i++){
//            int cnt = qaListservice.selectAdpCount(qaList.get(i));
//            String num = String.valueOf(qaList.get(i).getNum());
//            adpCount.put(num, cnt);
//            adpCountList.add(adpCount);
//        }

        model.addAttribute("qaList", qaList);
        model.addAttribute("pagination", pagination);

        return "cmn/qaList";
    }

    @GetMapping(value="/qaBoard")
    public String qaBoard () throws Exception {
        return "cmn/qaBoard";
    }

    @PostMapping(value="/qaBoard")
    public String regQaBoard (HttpServletRequest request, QaListDTO qaListDTO) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        qaListDTO.setWriter(member.getId());

        System.out.println("/qaBoard post : " + qaListDTO.toString());

        if(qaListDTO.getParentNum() == 0){
            qaListservice.insertQaBoard(qaListDTO);
        }else if(qaListDTO.getParentNum() != 0){
            qaListservice.insertCommentQaBoard(qaListDTO);
        }

        return "redirect:/qalist";
    }

    @GetMapping(value="/qaDetail")
    public String qaDetail (HttpServletRequest request
            , QaListDTO dto
            , Model model
            , @RequestParam("num") int num) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        System.out.println("member : " + session.getAttribute("member"));

        QaListDTO qaListDTO = null;
        List<QaListDTO> commentQaList = new ArrayList<>();
        List<String> commentContent = new ArrayList<>();
        List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();

        if (dto.getNum() != 0){
            qaListDTO = qaListservice.selectQaOne(dto);
            // 댓글 목록 조회
            commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(qaListDTO.getNum());
        } else {
            qaListDTO.setNum(num);
            qaListDTO = qaListservice.selectQaOne(dto);
        }
        likeQaBoardList = qaListservice.selectLikeQaBoardList(member);

        System.out.println("qaListDTO : " + qaListDTO.toString());
        System.out.println("commentQaList : " + commentQaList.toString());

        model.addAttribute("qaListDTO", qaListDTO);
        model.addAttribute("content", qaListDTO.getContent());
        model.addAttribute("commentQaList", commentQaList);

        if(likeQaBoardList != null){
            model.addAttribute("likeQaBoardList", likeQaBoardList);
        }

        for (int i=0; i<commentQaList.size(); i++){
            commentContent.add(commentQaList.get(i).getContent());
        }
        model.addAttribute("commentContent", commentContent);

        return "cmn/qaDetail";
    }

    @ResponseBody
    @GetMapping(value = "/qaLike")
    public Object qaLike (HttpServletRequest request, int num, int boardNum) throws Exception {
        HttpSession session= request.getSession();

        MemberDTO member = (MemberDTO) session.getAttribute("member");
        System.out.println(member.toString());

        // 처음 좋아요시 insert 용도
        LikeBoardDTO dto = new LikeBoardDTO();
        dto.setBoardIdx(num);
        dto.setParentNum(boardNum);
        dto.setUserId(member.getId());

        // 해당 글에 좋아요 한적있을시 update 용도
        LikeBoardDTO likeDTO = qaListservice.checkLikeQaBoard(dto);

        int likeBoardNum = 0;

        if(likeDTO != null){ // 좋아요 한적있을시 update
            qaListservice.updateLikeQaBoard(likeDTO);
            System.out.println("updateDto : " + likeDTO.toString());
            likeBoardNum = likeDTO.getBoardIdx();
            likeDTO = qaListservice.selectOneLikeQaBoard(dto);
            System.out.println("likeDTO : " + likeDTO.toString());
            // 좋아요 여부에따른 게시글 좋아요 개수 증가
            if(likeDTO.getLikeCheck() == 1){
                qaListservice.likeAddCount(likeDTO);
            }else if(likeDTO.getLikeCheck() == 0){
                qaListservice.likeMinusCount(likeDTO);
            }
            return likeDTO;
        } else if (likeDTO == null){ // 좋아요한적 없을시 insert
            qaListservice.insertLikeQaBoard(dto);
            System.out.println("insertDto : " + dto.toString());
            likeBoardNum = dto.getBoardIdx();
            likeDTO = qaListservice.selectOneLikeQaBoard(dto);
            // 게시글 좋아요개수 +1
            qaListservice.likeAddCount(likeDTO);
        }
        return likeDTO;
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
    public void qaRegComment (QaListDTO qaListDTO, HttpSession session) throws Exception {
        System.out.println("11/qaRegComment qaListDTO : " + qaListDTO.toString());
        session.setAttribute("qaRegListDTO", qaListDTO);
    }

    @ResponseBody
    @PostMapping(value = "/qaAdopt")
    public void qaAdopt (int boardNum, int commentNum) throws Exception {
        QaListDTO dto = new QaListDTO();
        dto.setNum(commentNum);
        dto.setParentNum(boardNum);
        qaListservice.adoptQaBoard(dto);
    }
}




















