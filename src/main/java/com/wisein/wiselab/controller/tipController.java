package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.ScrapBoardDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import com.wisein.wiselab.service.CommentService;
import com.wisein.wiselab.service.LikeService;
import com.wisein.wiselab.service.ScrapService;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class tipController {

    @Autowired
    TipBoardService tipBoardService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    ScrapService scrapService;


    private final AbstractPagingCustom PagingTagCustom;

    //다건 조회
    @GetMapping(value="/tipList")
    public String tipList (@ModelAttribute("TipBoardDTO") TipBoardDTO dto, Model model) throws Exception {
        List<TipBoardDTO> tipList = new ArrayList<>();
        tipList = tipBoardService.selectTipList(dto);

        dto.setTotalRecordCount(tipBoardService.selectBoardTotalCount(dto));
        String pagination = PagingTagCustom.render(dto);

        model.addAttribute("tipList", tipList);
        model.addAttribute("pagination", pagination);

        return "cmn/tipList";
    }

    //단건 조회
    @GetMapping(value="/tipDetail")
    public String tipDetail (HttpSession session, TipBoardDTO dto, Model model,  @RequestParam("num") int num) throws Exception {
        TipBoardDTO TipBoardDTO = null;
        List<CommentDTO> commentList = new ArrayList<>();
        String brdRef = "tip||"+num;
        //댓글 갯수
        int commentNum = commentService.selectCommentTotalCount(brdRef);

        //좋아요 체크 확인
        // String id = (String) session.getAttribute("authUser");
        // likeBoardDTO.setUserId(id);
        LikeBoardDTO LikeDTO = new LikeBoardDTO();
        LikeDTO.setBrdRef(brdRef);
        LikeDTO.setUserId("hannah94");

        String likeDelYn = likeService.TipLikeYN(LikeDTO);
        if(likeDelYn==null){ likeDelYn = "none"; }

        //스크랩 체크 확인
        //String id = (String) session.getAttribute("authUser");
        //scrapBoardDTO.setUserId(id);
        ScrapBoardDTO ScrapDTO = new  ScrapBoardDTO();
        ScrapDTO.setBrdRef(brdRef);
        ScrapDTO.setUserId("hannah94");

        String scrapDelYn = scrapService.TipScrapYN(ScrapDTO);
        if(scrapDelYn==null){ scrapDelYn = "none"; }

        //팁 단건 내용+코멘트 내용 리스트
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
        model.addAttribute("likeDelYn", likeDelYn);
        model.addAttribute("scrapDelYn", scrapDelYn);

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
        String brdRef = "tip||"+num;

        tipBoardService.deleteTipBoard(num);
        commentService.deleteAllComment(brdRef);

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
    @ResponseBody
    @PostMapping(value = "/regTipComm")
    public void tipRegComment (CommentDTO dto) throws Exception {
        commentService.insertComment(dto);
    }

    //댓글 삭제
    @ResponseBody
    @PostMapping(value = "/delTipComm")
    public void tipDelComment (CommentDTO dto) throws Exception {
        commentService.deleteComment(dto);
    }

    //댓글 수정
    @ResponseBody
    @PostMapping(value = "/udpTipComm")
    public void tipUpdComment (CommentDTO dto) throws Exception {
        commentService.updateComment(dto);
    }

    //like 등록
    @ResponseBody
    @PostMapping(value = "/regLikeTip")
    public void tipRegLike (int num, LikeBoardDTO dto) throws Exception {
        String brdRef = "tip||"+num;
        dto.setBrdRef(brdRef);
        likeService.insertLike(dto);
        likeService.addTipLikeCount(num);
    }

    //like 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpLikeTip")
    public void tipUdpLike (int num, LikeBoardDTO dto) throws Exception {
        String brdRef = "tip||"+num;
        dto.setBrdRef(brdRef);
        String likeDelYn = likeService.TipLikeYN(dto);
        if(likeDelYn.equals("N")){ //해제
            likeService.undoLike(dto);
            likeService.delTipLikeCount(num);
        }else{//재등록
            likeService.doLike(dto);
            likeService.addTipLikeCount(num);
        }
    }

    //scrap 등록
    @ResponseBody
    @PostMapping(value = "/regScrapTip")
    public void tipRegScrap (int num, ScrapBoardDTO dto) throws Exception {
        String brdRef = "tip||"+num;
        dto.setBrdRef(brdRef);
        scrapService.insertScrap(dto);
        scrapService.addTipScrapCount(num);
    }

    //scrap 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpScrapTip")
    public void tipUdpScrap (int num, ScrapBoardDTO dto) throws Exception {
        String brdRef = "tip||"+num;
        dto.setBrdRef(brdRef);
        String scrapDelYn = scrapService.TipScrapYN(dto);
        if(scrapDelYn.equals("N")){ //해제
            scrapService.undoScrap(dto);
            scrapService.delTipScrapCount(num);
        }else{//재등록
            scrapService.doScrap(dto);
            scrapService.addTipScrapCount(num);
        }
    }


}
