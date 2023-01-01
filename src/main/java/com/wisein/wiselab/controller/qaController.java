package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.dto.ScrapBoardDTO;
import com.wisein.wiselab.service.CommentService;
import com.wisein.wiselab.service.LikeService;
import com.wisein.wiselab.service.QaListService;

import com.wisein.wiselab.service.ScrapService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.lang.model.SourceVersion;
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

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    ScrapService scrapService;

    private final AbstractPagingCustom PagingTagCustom;

    @GetMapping(value="/qalist")
    public String qaList (@ModelAttribute("qaListDTO") QaListDTO qaListDTO
                        //, HttpSession session
                        //, @RequestParam(value="sideCheck", required = false, defaultValue = "false") String sideCheck
                        , Model model) throws Exception {
        List<QaListDTO> qaList = new ArrayList<>();
        qaList = qaListservice.selectQaList(qaListDTO);

        qaListDTO.setTotalRecordCount(qaListservice.selectBoardTotalCount(qaListDTO));
        String pagination = PagingTagCustom.render(qaListDTO);
        qaList = qaListservice.selectQaList(qaListDTO);

        if(qaList.isEmpty()){
            qaList = null;
        }

        model.addAttribute("qaList", qaList);
        model.addAttribute("pagination", pagination);
        return "cmn/qaList";
    }

    @GetMapping(value="/qaBoard")
    public String qaBoard (QaListDTO qaListDTO, HttpSession session) throws Exception {
//        System.out.println("/qaRegComment qaListDTO : " + qaListDTO.toString());
        session.setAttribute("qaRegListDTO", qaListDTO);
        return "board/qaBoard";
    }

    @PostMapping(value="/qaBoard")
    public String qaBoard (HttpServletRequest request
            , QaListDTO qaListDTO
            , RedirectAttributes re) throws Exception {
        //System.out.println("========================= qaBoard Post 시작 ====================================");
        HttpSession session = request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        // 댓글 답변일경우
        QaListDTO dto = (QaListDTO) session.getAttribute("qaRegListDTO");
        if(dto != null){
            qaListDTO.setParentNum(dto.getParentNum());
        }
        qaListDTO.setWriter(member.getId());
//        System.out.println("/qaBoard post : " + qaListDTO.toString());

        // 원본 작성일경우
        if(qaListDTO.getParentNum() == 0){
            qaListservice.insertQaBoard(qaListDTO);
        }else if(qaListDTO.getParentNum() != 0){    // 댓글 작성일경우
            qaListservice.insertCommentQaBoard(qaListDTO);
            session.removeAttribute("qaRegListDTO");
        }
        // 게시글안에서 수정일 경우 작성 후 해당 게시글로 이동
        String udpGubun = request.getParameter("updGubun");
//        System.out.println(udpGubun);

        if(udpGubun.equals("Y")){
            qaListDTO.setNum(qaListDTO.getParentNum());
            qaListDTO = qaListservice.selectQaOne(qaListDTO);

            int num = qaListDTO.getNum();

//            System.out.println("/qaBoard post : " + qaListDTO.toString());
            re.addFlashAttribute("qaListDTO", qaListDTO);
            re.addAttribute("num", num);
            return "redirect:/qaDetail";
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

        //meetLink
        String meetLink = qaListservice.selectMeetLink(num);
        //System.out.println("meetLink = " + meetLink);

        QaListDTO qaListDTO = null;
        List<QaListDTO> commentQaList = new ArrayList<>();
        List<String> commentContent = new ArrayList<>();
        List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();
        List<ScrapBoardDTO> scrapQaBoardList = new ArrayList<>();
        List<String> commentMeetLinkList = new ArrayList<>();

        // redirect시 필요조건
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap!=null) {
//            System.out.println(flashMap.toString());
            dto =(QaListDTO)flashMap.get("qaListDTO");
//            System.out.println("flashMap : " + dto.toString());
        }

        // 답변모아보기에서 게시글 선택 시 해당 원글 조회를 위한 세팅
        if(dto.getParentNum()!= 0){
            dto.setNum(dto.getParentNum());
        }
        // 해당 게시글에대한 조회 및 댓글 목록 조회
        if (dto.getNum() != 0){
//            System.out.println("/qaDetail dto.getNum() : " + dto.getNum());
            qaListDTO = qaListservice.selectQaOne(dto);
            // 댓글 목록 조회
            commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(qaListDTO.getNum());

            int commentQalistSize = commentQaList.size();

            if(commentQalistSize != 0){
                for(int i=0; i<commentQalistSize; i++){
                    int commentNum = commentQaList.get(i).getNum();
                    String commentMeetLink = qaListservice.selectMeetLink(commentNum);
                    commentMeetLinkList.add(commentMeetLink);
                    //System.out.println("commentMeetLinkList.get(i) = " + commentMeetLinkList.get(i));
                }
                model.addAttribute("commentMeetLinkList", commentMeetLinkList);
            }


        } else {
//            System.out.println("/qaDetail num : " + num);
            qaListDTO.setNum(num);
            qaListDTO = qaListservice.selectQaOne(dto);
        }
        // login했을경우 해당 게시글에 대한 좋아요 및 스크랩 정보 조회
        if(member != null){
            likeQaBoardList = qaListservice.selectLikeQaBoardList(member);
            scrapQaBoardList = qaListservice.selectScrapQaBoardList(member);
        }

//        System.out.println("qaListDTO : " + qaListDTO.toString());
//        System.out.println("commentQaList : " + commentQaList.toString());
//        System.out.println(qaListDTO.toString());
//        System.out.println(qaListDTO.getContent());
//        System.out.println(commentQaList.toString());

        model.addAttribute("qaListDTO", qaListDTO);
        model.addAttribute("content", qaListDTO.getContent());
        model.addAttribute("commentQaList", commentQaList);
        // 좋아요 히스토리가 있을경우 저장
        if(likeQaBoardList != null){
            model.addAttribute("likeQaBoardList", likeQaBoardList);
//            System.out.println("likeQaBoardList : " + likeQaBoardList.toString());
        }
        // 스크랩 히스토리가 있을경우 저장
        if(scrapQaBoardList != null){
            model.addAttribute("scrapQaBoardList", scrapQaBoardList);
//            System.out.println("scrapQaBoardList : " + scrapQaBoardList.toString());
        }
        // 해당 게시글에 댓글이 있을경우 content 저장
        for (int i=0; i<commentQaList.size(); i++){
            commentContent.add(commentQaList.get(i).getContent());
        }
        model.addAttribute("commentContent", commentContent);

        String check =(String)session.getAttribute("side_gubun");
//System.out.println("check :" + check);
        if(check == null){
            String side_gubun = "Y";
            model.addAttribute("side_gubun", side_gubun);
        }
        model.addAttribute("meetLink", meetLink);

        return "cmn/qaDetail";
    }

    @ResponseBody
    @PostMapping(value="/likeDetail")
    public Object likeDetail (HttpServletRequest request
            , QaListDTO dto
            , Model model
            , @RequestParam("num") int num) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        QaListDTO qaListDTO = null;
        List<QaListDTO> commentQaList = new ArrayList<>();
        List<String> commentContent = new ArrayList<>();
        List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();
        List<ScrapBoardDTO> scrapQaBoardList = new ArrayList<>();

        // 답변모아보기에서 게시글 선택 시 해당 원글 조회를 위한 세팅
        if(dto.getParentNum()!= 0){
            dto.setNum(dto.getParentNum());
        }
        // 해당 게시글에대한 조회 및 댓글 목록 조회
        if (dto.getNum() != 0){
            qaListDTO = qaListservice.selectQaOne(dto);
            // 댓글 목록 조회
            commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(qaListDTO.getNum());
        } else {
            qaListDTO.setNum(num);
            qaListDTO = qaListservice.selectQaOne(dto);
        }
        // login했을경우 해당 게시글에 대한 좋아요 및 스크랩 정보 조회
        if(member != null){
            likeQaBoardList = qaListservice.selectLikeQaBoardList(member);
            scrapQaBoardList = qaListservice.selectScrapQaBoardList(member);
        }

        model.addAttribute("qaListDTO", qaListDTO);
        model.addAttribute("content", qaListDTO.getContent());
        model.addAttribute("commentQaList", commentQaList);
        // 좋아요 히스토리가 있을경우 저장
        if(likeQaBoardList != null){
            model.addAttribute("likeQaBoardList", likeQaBoardList);
//            System.out.println("likeQaBoardList : " + likeQaBoardList.toString());
        }
        // 스크랩 히스토리가 있을경우 저장
        if(scrapQaBoardList != null){
            model.addAttribute("scrapQaBoardList", scrapQaBoardList);
//            System.out.println("scrapQaBoardList : " + scrapQaBoardList.toString());
        }
        // 해당 게시글에 댓글이 있을경우 content 저장
        for (int i=0; i<commentQaList.size(); i++){
            commentContent.add(commentQaList.get(i).getContent());
        }
        model.addAttribute("commentContent", commentContent);
        String side_gubun = "Y";
        model.addAttribute("side_gubun", side_gubun);

        Map<String, Object> body = new HashMap<>();

        body.put("commentQaList", commentQaList);
        body.put("likeQaBoardList", likeQaBoardList);
        body.put("scrapQaBoardList", scrapQaBoardList);

//        System.out.println("commentQaList : " + body.get("commentQaList"));
//        System.out.println("likeQaBoardList : " + body.get("likeQaBoardList"));
//        System.out.println("scrapQaBoardList : " + body.get("scrapQaBoardList"));

//        JSONObject jsonObject = new JSONObject(body);
//
//        System.out.println("jsonObject : " + jsonObject);

        return body;
    }

    @ResponseBody
    @PostMapping(value = "/regLikeQa")
    public JSONObject regLikeQa (HttpSession session
            , @RequestBody String data
            , LikeBoardDTO dto) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        dto.setUserId(member.getId());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);

        JSONObject jsonObj = (JSONObject)obj;

        String numStr = String.valueOf(jsonObj.get("num"));
        int num = Integer.parseInt(numStr);

        dto.setBoardIdx(num);
        dto.setBoardType("qa");

//        System.out.println(dto.toString());

        likeService.insertLike(dto);
        likeService.addQaLikeCount(num);

        int parentNum = likeService.getQaParentNum(num);
        qaListservice.addQaLikeCount(parentNum); // 원본게시글 전체 likeCount 값 증가

        //String likeDelYn = likeService.TipLikeYN(dto);

        List<QaListDTO> commentQaList = new ArrayList<>();
        List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();

        // 댓글 목록 조회
        commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(parentNum);
        likeQaBoardList = qaListservice.selectLikeQaBoardList(member);

        JSONObject response = new JSONObject();

        JSONObject jsonObjComment = new JSONObject();
        JSONArray jArrayComment = new JSONArray();//배열이 필요할때
        for (int i = 0; i < commentQaList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("num", commentQaList.get(i).getNum());
            jArrayComment.add(sObject);
        }
        jsonObjComment.put("commentQaList", jArrayComment);
//        System.out.println(jsonObjComment.toString());

        JSONObject jsonObjLike = new JSONObject();
        JSONArray jArrayLike = new JSONArray();//배열이 필요할때
        for (int i = 0; i < likeQaBoardList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("boardIdx", likeQaBoardList.get(i).getBoardIdx());
            sObject.put("delYn", likeQaBoardList.get(i).getDelYn());
            jArrayLike.add(sObject);
        }
        jsonObjLike.put("likeQaBoardList", jArrayLike);
//        System.out.println(jsonObjLike.toString());

        response.put("commentQaList",jsonObjComment);
        response.put("likeQaBoardList",jsonObjLike);
        response.put("member",member);
//        System.out.println("response : " + response.toString());

        return response;
    }

    //like 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpLikeQa")
    public JSONObject udpLikeQa (HttpSession session
            , LikeBoardDTO dto
            , @RequestBody String data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        dto.setUserId(member.getId());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);

        JSONObject jsonObj = (JSONObject)obj;

        String numStr = String.valueOf(jsonObj.get("num"));
        int num = Integer.parseInt(numStr);

        dto.setBoardIdx(num);
        dto.setBoardType("qa");

        // 원본게시글 like/scrap 카운트하려면 parentNum 값 필요
        int parentNum = likeService.getQaParentNum(num);

        String likeDelYn = likeService.TipLikeYN(dto);

        if(likeDelYn.equals("N")){ //해제
            likeService.undoLike(dto);
            likeService.delQaLikeCount(num);
            qaListservice.delQaLikeCount(parentNum); // 원본게시글 전체 likeCount 값 감소
        }else{//재등록
            likeService.doLike(dto);
            likeService.addQaLikeCount(num);
            qaListservice.addQaLikeCount(parentNum); // 원본게시글 전체 likeCount 값 증가
        }

        List<QaListDTO> commentQaList = new ArrayList<>();
        List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();

        // 댓글 목록 조회
        commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(parentNum);
        likeQaBoardList = qaListservice.selectLikeQaBoardList(member);

        JSONObject response = new JSONObject();

        JSONObject jsonObjComment = new JSONObject();
        JSONArray jArrayComment = new JSONArray();//배열이 필요할때
        for (int i = 0; i < commentQaList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("num", commentQaList.get(i).getNum());
            jArrayComment.add(sObject);
        }
        jsonObjComment.put("commentQaList", jArrayComment);
//        System.out.println(jsonObjComment.toString());

        JSONObject jsonObjLike = new JSONObject();
        JSONArray jArrayLike = new JSONArray();//배열이 필요할때
        for (int i = 0; i < likeQaBoardList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("boardIdx", likeQaBoardList.get(i).getBoardIdx());
            sObject.put("delYn", likeQaBoardList.get(i).getDelYn());
            jArrayLike.add(sObject);
        }
        jsonObjLike.put("likeQaBoardList", jArrayLike);
//        System.out.println(jsonObjLike.toString());

        response.put("commentQaList",jsonObjComment);
        response.put("likeQaBoardList",jsonObjLike);
        response.put("member",member);
//        System.out.println("response : " + response.toString());

        return response;
    }

    //scrap 등록
    @ResponseBody
    @PostMapping(value = "/regScrapQa")
    public JSONObject regScrapQa (HttpSession session
            , @RequestBody String data
            , ScrapBoardDTO dto) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        dto.setUserId(member.getId());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);

        JSONObject jsonObj = (JSONObject)obj;

        String numStr = String.valueOf(jsonObj.get("num"));
        int num = Integer.parseInt(numStr);

        dto.setBoardIdx(num);
        dto.setBoardType("qa");

        scrapService.insertScrap(dto);
        scrapService.addQaScrapCount(num);

        int parentNum = scrapService.getScrapParentNum(num);
        qaListservice.addQaScrapCount(parentNum); // 원본게시글 전체 ScrapCount 값 증가

        List<QaListDTO> commentQaList = new ArrayList<>();
        List<ScrapBoardDTO> scrapQaBoardList = new ArrayList<>();

        // 댓글 목록 조회
        commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(parentNum);
        scrapQaBoardList = qaListservice.selectScrapQaBoardList(member);

        JSONObject response = new JSONObject();

        JSONObject jsonObjComment = new JSONObject();
        JSONArray jArrayComment = new JSONArray();//배열이 필요할때
        for (int i = 0; i < commentQaList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("num", commentQaList.get(i).getNum());
            jArrayComment.add(sObject);
        }
        jsonObjComment.put("commentQaList", jArrayComment);
//        System.out.println(jsonObjComment.toString());

        JSONObject jsonObjScrap = new JSONObject();
        JSONArray jArrayScrap = new JSONArray();//배열이 필요할때
        for (int i = 0; i < scrapQaBoardList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("boardIdx", scrapQaBoardList.get(i).getBoardIdx());
            sObject.put("delYn", scrapQaBoardList.get(i).getDelYn());
            jArrayScrap.add(sObject);
        }
        jsonObjScrap.put("scrapQaBoardList", jArrayScrap);
//        System.out.println(jsonObjLike.toString());

        response.put("commentQaList",jsonObjComment);
        response.put("scrapQaBoardList",jsonObjScrap);
        response.put("member",member);
//        System.out.println("response : " + response.toString());

        return response;
    }

    //scrap 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpScrapQa")
    public JSONObject udpScrapQa (HttpSession session
            , @RequestBody String data
            , ScrapBoardDTO dto) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        dto.setUserId(member.getId());

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);

        JSONObject jsonObj = (JSONObject)obj;

        String numStr = String.valueOf(jsonObj.get("num"));
        int num = Integer.parseInt(numStr);

        dto.setBoardIdx(num);
        dto.setBoardType("qa");

        int parentNum = scrapService.getScrapParentNum(num);

        String scrapDelYn = scrapService.TipScrapYN(dto);

        if(scrapDelYn.equals("N")){ //해제
            scrapService.undoScrap(dto);
            scrapService.delQaScrapCount(num);
            qaListservice.delQaScrapCount(parentNum); // 원본게시글 전체 ScrapCount 값 감소
        }else{//재등록
            scrapService.doScrap(dto);
            scrapService.addQaScrapCount(num);
            qaListservice.addQaScrapCount(parentNum); // 원본게시글 전체 ScrapCount 값 증가
        }

        List<QaListDTO> commentQaList = new ArrayList<>();
        List<ScrapBoardDTO> scrapQaBoardList = new ArrayList<>();

        // 댓글 목록 조회
        commentQaList = (List<QaListDTO>) qaListservice.selectCommentQaList(parentNum);
        scrapQaBoardList = qaListservice.selectScrapQaBoardList(member);

        JSONObject response = new JSONObject();

        JSONObject jsonObjComment = new JSONObject();
        JSONArray jArrayComment = new JSONArray();//배열이 필요할때
        for (int i = 0; i < commentQaList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("num", commentQaList.get(i).getNum());
            jArrayComment.add(sObject);
        }
        jsonObjComment.put("commentQaList", jArrayComment);
//        System.out.println(jsonObjComment.toString());

        JSONObject jsonObjScrap = new JSONObject();
        JSONArray jArrayScrap = new JSONArray();//배열이 필요할때
        for (int i = 0; i < scrapQaBoardList.size(); i++)//배열
        {
            JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
            sObject.put("boardIdx", scrapQaBoardList.get(i).getBoardIdx());
            sObject.put("delYn", scrapQaBoardList.get(i).getDelYn());
            jArrayScrap.add(sObject);
        }
        jsonObjScrap.put("scrapQaBoardList", jArrayScrap);
//        System.out.println(jsonObjScrap.toString());

        response.put("commentQaList",jsonObjComment);
        response.put("scrapQaBoardList",jsonObjScrap);
        response.put("member",member);
//        System.out.println("response : " + response.toString());

        return response;
    }

    @GetMapping(value = "/qaDelete")
    public String qaDelete (
            HttpServletRequest request
            , @RequestParam(value="num", required = false, defaultValue = "0") int num
            , @RequestParam(value="commentQaNum", required = false, defaultValue = "0") int commentQaNum) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        //System.out.println("num : " + num);
        //System.out.println("commentQaNum : " + commentQaNum);

        // 원본 게시글 삭제일경우
        if(num != 0){
            // 1.게시글 num을 받아서 댓글/좋아요/스크랩 목록 조회 한 뒤
            // 2.게시글 삭제 및 댓글 삭제 진행
            // 3.댓글 목록 for문을 통해 좋아요 여부 삭제 진행
            // 4.댓글 목록 for문을 통해 스크랩 여부 삭제 진행

            //1.
            List<QaListDTO> commentQaList = new ArrayList<>();
            List<LikeBoardDTO> likeQaBoardList = new ArrayList<>();
            List<ScrapBoardDTO> scrapQaBoardList = new ArrayList<>();

            commentQaList = qaListservice.selectCommentQaList(num);
            likeQaBoardList = qaListservice.selectLikeQaBoardList(member);
            scrapQaBoardList = qaListservice.selectScrapQaBoardList(member);

            //2.
            qaListservice.deleteQaBoard(num);
            if(commentQaList != null){
                qaListservice.deleteCommentQaBoard(num);
                int commentQaListSize = commentQaList.size();
                //3.
                if(likeQaBoardList != null){
                    int LikeQaBoardListSize = likeQaBoardList.size();
                    for(int i=0; i<commentQaListSize; i++){
                        int commentNum = commentQaList.get(i).getNum();
                        for(int j=0; j<LikeQaBoardListSize; j++){
                            if(commentNum == likeQaBoardList.get(j).getBoardIdx()){
                                likeService.undoLike(likeQaBoardList.get(j));
                            }
                        }
                    }
                }
                //4.
                if(scrapQaBoardList != null){
                    int scrapQaBoardListSize = scrapQaBoardList.size();
                    for(int i=0; i<commentQaListSize; i++){
                        int commentNum = commentQaList.get(i).getNum();
                        for(int j=0; j<scrapQaBoardListSize; j++){
                            if(commentNum == scrapQaBoardList.get(j).getBoardIdx()){
                                scrapService.undoScrap(scrapQaBoardList.get(j));
                            }
                        }
                    }
                }
            }
        }else if(commentQaNum != 0){    // 댓글 삭제일경우
            num = commentQaNum;
            qaListservice.deleteQaBoard(num);
        }
        return "redirect:/qalist";
    }

    @GetMapping(value = "/qaUpdate")
    public String qaUpdate (QaListDTO qaListDTO
            , Model model) throws Exception {

        qaListDTO = qaListservice.selectQaOne(qaListDTO);

        model.addAttribute("qaListDTO", qaListDTO);

        return "board/qaBoard";
    }

    @PostMapping(value = "/qaUpdatePro")
    public String qaUpdatePro (QaListDTO qaListDTO
//                            , Model model
            , RedirectAttributes re) throws Exception {

        qaListservice.updateQaBoard(qaListDTO);
//        System.out.println("qaListDTO : " + qaListDTO.toString());

        int num = qaListDTO.getNum();
        // 댓글일경우 조회 후 저장
        if(qaListDTO.getParentNum() != 0){
            num = qaListservice.getParentNum(qaListDTO);
        }
//        System.out.println("num : " + num);

//        model.addAttribute("qaListDTO", qaListDTO);
        re.addFlashAttribute("qaListDTO", qaListDTO);
        re.addAttribute("num", num);

        return "redirect:/qaDetail";
    }

    //, @RequestParam @Nullable String num
    //이미지 url 반환
    @ResponseBody
    @RequestMapping(value="/qaImgUrlReg")
    public String qaImgUrlReg(HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest, QaListDTO qaListDTO, Model model) throws Exception {
//        System.out.println("qaImgUrlReg : " + qaListDTO.toString());
        return qaListservice.imgUrlReg(multipartHttpServletRequest, session, model);
    }
    @ResponseBody
    @PostMapping(value = "/selectQaNum")
    public void selectQaNum (QaListDTO qaListDTO, HttpSession session) throws Exception {
//        System.out.println("/selectQaNum qaListDTO : " + qaListDTO.toString());

        qaListDTO = qaListservice.selectQaNum(qaListDTO);

//        System.out.println("qaListDTO : " + qaListDTO.toString());

        session.setAttribute("qaListDTO", qaListDTO);
    }

    @ResponseBody
    @PostMapping(value = "/qaRegComment")
    public void qaRegComment (QaListDTO qaListDTO, HttpSession session) throws Exception {
//        System.out.println("11/qaRegComment qaListDTO : " + qaListDTO.toString());
        session.setAttribute("qaRegListDTO", qaListDTO);
    }

    //@ResponseBody
    @PostMapping(value = "/qaAdopt")
    public String qaAdopt (int boardNum
            , int commentNum
            , RedirectAttributes re) throws Exception {
        QaListDTO dto = new QaListDTO();
        dto.setNum(commentNum);
        dto.setParentNum(boardNum);
        qaListservice.adoptQaBoard(dto);
        qaListservice.adoptCommentQaBoard(dto);
//        System.out.println(dto.toString());
        int num = boardNum;
        re.addAttribute("num", num);

        return "redirect:/qaDetail";
    }

    @GetMapping(value="/questionsList")
    public String questionsList (HttpServletRequest request
            , @ModelAttribute("qaListDTO") QaListDTO qaListDTO
            , @RequestParam(value="sideCheck", required = false, defaultValue = "N") String sideCheck
           , Model model) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        // 사이드모아보기 아니면서 처음 모아보기일경우
        if(qaListDTO.getWriter() != null){
            String temp = (String)session.getAttribute("questionsListWriter");
            if(temp != null){
                session.removeAttribute("questionsListWriter");
            }
            session.setAttribute("questionsListWriter", qaListDTO.getWriter());
            //System.out.println("사이드모아보기 아니면서 처음 모아보기일경우");
        }
        // 사이드모아보기 처음일경우
        if(qaListDTO.getWriter() == null && sideCheck.equals("Y")){
            String temp = (String)session.getAttribute("questionsListWriter");
            if(temp != null){
                session.removeAttribute("questionsListWriter");
            }
            session.setAttribute("questionsListWriter", member.getId());
            //System.out.println("사이드모아보기 처음일경우");
        }

        qaListDTO.setWriter((String)session.getAttribute("questionsListWriter"));
        session.setAttribute("questionsListWriter", qaListDTO.getWriter());
//        System.out.println(qaListDTO.getWriter());

        // 본인글 및 본인 질문모아보기에서는 사이드바 보여주게 설정
        if(member.getId().equals(qaListDTO.getWriter())){
            String side_gubun = "Y";
            model.addAttribute("side_gubun", side_gubun);
        }

        List<QaListDTO> qaList = new ArrayList<>();

        qaList = qaListservice.selectQuestionsList(qaListDTO);
        qaListDTO.setTotalRecordCount(qaListservice.selectMemberQaTotalCount(qaListDTO));
        String pagination = PagingTagCustom.render(qaListDTO);

        model.addAttribute("qaList", qaList);
        model.addAttribute("pagination", pagination);

        return "cmn/qaList";
    }

    @GetMapping(value="/commentList")
    public String commentList (HttpServletRequest request
            , @ModelAttribute("qaListDTO") QaListDTO qaListDTO
            , @RequestParam(value="sideCheck", required = false, defaultValue = "N") String sideCheck
            , Model model) throws Exception {
        HttpSession session= request.getSession();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        // 사이드모아보기 아니면서 처음 모아보기일경우
        if(qaListDTO.getWriter() != null){
            String temp = (String)session.getAttribute("commentListWriter");
            if(temp != null){
                session.removeAttribute("commentListWriter");
            }
            session.setAttribute("commentListWriter", qaListDTO.getWriter());
        }

        qaListDTO.setWriter((String)session.getAttribute("commentListWriter"));

//System.out.println(sideCheck);
        // 사이드바에서 QA답글모아보기 예외처리
        if(sideCheck.equals("Y")){
            String temp = (String)session.getAttribute("questionsListWriter");
            if(temp != null && temp.equals(member.getId())){
                qaListDTO.setWriter(temp);
                session.setAttribute("commentListWriter", member.getId());
            }
        }

//System.out.println(session.getAttribute("questionsListWriter"));
//System.out.println(session.getAttribute("commentListWriter"));
//System.out.println(qaListDTO.getWriter());

        // 본인글 및 본인 답글모아보기에서는 사이드바 보여주게 설정
        if(member.getId().equals(qaListDTO.getWriter())){
            String side_gubun = "Y";
            model.addAttribute("side_gubun", side_gubun);
        }

        List<QaListDTO> qaList = new ArrayList<>();

        qaList = qaListservice.selectCommentList(qaListDTO);
        qaListDTO.setTotalRecordCount(qaListservice.selectMemberQaCommentTotalCount(qaListDTO));
        String pagination = PagingTagCustom.render(qaListDTO);

        model.addAttribute("qaList", qaList);
        model.addAttribute("pagination", pagination);

        return "cmn/qaList";
    }

    //comment_meetLink_btn 조회
    @ResponseBody
    @PostMapping(value = "/selectMeetLink")
    public JSONObject selectMeetLink (HttpSession session
            , @RequestBody String data) throws Exception {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);

        JSONObject jsonObj = (JSONObject)obj;

        String numStr = String.valueOf(jsonObj.get("num"));
        int num = Integer.parseInt(numStr);

        String meetLink = qaListservice.selectMeetLink(num);

        JSONObject response = new JSONObject();

        response.put("meetLink",meetLink);

        return response;
    }

}




















