package com.wisein.wiselab.controller;

import com.wisein.wiselab.common.paging.AbstractPagingCustom;
import com.wisein.wiselab.dto.*;
import com.wisein.wiselab.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    CommonService commonService;

    @Autowired
    MemberService memberService;


    private final AbstractPagingCustom PagingTagCustom;

    //다건 조회
    @GetMapping(value="/tipList")
    public String tipList (HttpSession session,  @ModelAttribute("TipBoardDTO") TipBoardDTO dto, Model model) throws Exception {
        //수정때문에 세션저장해둔것 지움
        session.removeAttribute("TipBoardDTO");

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

        //댓글 갯수
        CommentDTO CommentDTO = new CommentDTO();
        CommentDTO.setBoardIdx(num);
        CommentDTO.setBoardType("tip");
        int commentNum = commentService.selectCommentTotalCount(CommentDTO);

        //좋아요 체크 확인
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        LikeBoardDTO LikeDTO = new LikeBoardDTO();
        LikeDTO.setUserId((member.getId()));
        LikeDTO.setBoardIdx(num);
        LikeDTO.setBoardType("tip");

        String likeDelYn = likeService.TipLikeYN(LikeDTO);
        if(likeDelYn==null){ likeDelYn = "none"; }

        //스크랩 체크 확인
        ScrapBoardDTO ScrapDTO = new ScrapBoardDTO();
        ScrapDTO.setUserId((member.getId()));
        ScrapDTO.setBoardIdx(num);
        ScrapDTO.setBoardType("tip");
        String scrapDelYn = scrapService.TipScrapYN(ScrapDTO);
        if(scrapDelYn==null){ scrapDelYn = "none"; }


        //팁 단건 내용+코멘트 내용 리스트
        TipBoardDTO TipBoardDTO = tipBoardService.selectTipOne(dto);
        List<CommentDTO> commentList = commentService.selectComment(CommentDTO);

        //팁 작성자 이미지
        HashMap<String,String> commWritersIMG = new HashMap<String,String>();
        List<String> commWriters = commentService.selectCommWriters(CommentDTO);

        if(commWriters.size()!=0){
            for(int i=0; i < commWriters.size(); i++){
                if(memberService.memImgList(commWriters.get(i)).size() != 0){
                    commWritersIMG.put(commWriters.get(i), (memberService.memImgList(commWriters.get(i))).get(0).getFilePath());
                } else {
                    commWritersIMG.put(commWriters.get(i), null);
                }
            }
        }

        //사이드바 설정
        String side_gubun = "Y";

        model.addAttribute("tipBoardDTO", TipBoardDTO);
        model.addAttribute("content", TipBoardDTO.getContent());
        model.addAttribute("commentNum", commentNum);
        model.addAttribute("commentList", commentList);
        model.addAttribute("likeDelYn", likeDelYn);
        model.addAttribute("scrapDelYn", scrapDelYn);
        model.addAttribute("memberId", member.getId());
        model.addAttribute("commWritersIMG", commWritersIMG);
        model.addAttribute("side_gubun", side_gubun);
        return "cmn/tipDetail";
    }

    //등록
    @GetMapping(value="/tipBoard")
    public String regTip () throws Exception {
        return "board/tipBoard";
    }

    @ResponseBody
    @PostMapping(value="/tipBoard")
    public ResponseEntity regTip ( HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        TipBoardDTO dto = new TipBoardDTO();
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        dto.setWriter(member.getId());
        dto.setCategory(data.get("category"));
        dto.setSubject(data.get("subject"));
        dto.setContent(data.get("content"));

        tipBoardService.insertTipBoard(dto);

        //이미지 난수 게시글로 변경 ex) tip||sj2s10 => tip||22
        String brdNum = data.get("brdNum");
        int postNum = tipBoardService.selectTipPostNum(dto);

        FileDTO file = new FileDTO();

        file.setRefNum("tip||" + postNum);
        file.setTemRefHash("tip||" + brdNum);
        commonService.updateHash(file);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    //삭제
    @GetMapping(value="/delTip")
    public String delTip (@RequestParam("num") int num) throws Exception {
        CommentDTO CommentDTO = new CommentDTO();
        CommentDTO.setBoardIdx(num);
        CommentDTO.setBoardType("tip");

        tipBoardService.deleteTipBoard(num);
        commentService.deleteAllComment(CommentDTO);

        return "redirect:/tipList";
    }


    //수정
    @GetMapping(value="/updTip")
    public String updTip (TipBoardDTO dto, Model model, HttpSession session) throws Exception {
        dto = tipBoardService.selectTipOne(dto);
        model.addAttribute("TipBoardDTO", dto);
        session.setAttribute("TipBoardDTO", dto);
        return "board/tipBoard";
    }

    @ResponseBody
    @PostMapping(value="/updTip")
    public ResponseEntity updTip(HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        TipBoardDTO dto = new TipBoardDTO();

        dto.setNum(Integer.parseInt(data.get("num")));
        dto.setCategory(data.get("category"));
        dto.setSubject(data.get("subject"));
        dto.setContent(data.get("content"));

        tipBoardService.updateTipBoard(dto);
        session.removeAttribute("TipBoardDTO");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    //댓글 등록
    @ResponseBody
    @PostMapping(value = "/regTipComm")
    public JSONObject tipRegComment (HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        CommentDTO dto = new CommentDTO();

        dto.setWriter(member.getId());
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));
        dto.setContent(data.get("content"));

        //댓글등록
        commentService.insertComment(dto);

        //댓글 리스트
        List<CommentDTO> commentList = commentService.selectComment(dto);

        //댓글 작성자 img
        HashMap<String,String> commWritersIMG = new HashMap<String,String>();
        List<String> commWriters = commentService.selectCommWriters(dto);

        if(commWriters.size()!=0){
            for(int i=0; i < commWriters.size(); i++){
                if(memberService.memImgList(commWriters.get(i)).size() != 0){
                    commWritersIMG.put(commWriters.get(i), (memberService.memImgList(commWriters.get(i))).get(0).getFilePath());
                } else {
                    commWritersIMG.put(commWriters.get(i), null);
                }
            }
        }

        JSONObject response = new JSONObject();
        response.put("commentList",commentList);
        response.put("commWritersIMG",commWritersIMG);

        return response;
    }

    //댓글 삭제
    @ResponseBody
    @PostMapping(value = "/delTipComm")
    public JSONObject tipDelComment (@RequestBody Map<String, String> data) throws Exception {
        CommentDTO dto = new CommentDTO();

        dto.setNum(Integer.parseInt(data.get("num")));
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));

        commentService.deleteComment(dto);

        //댓글 리스트
        List<CommentDTO> commentList = commentService.selectComment(dto);

        //댓글 작성자 img
        HashMap<String,String> commWritersIMG = new HashMap<String,String>();
        List<String> commWriters = commentService.selectCommWriters(dto);

        if(commWriters.size()!=0){
            for(int i=0; i < commWriters.size(); i++){
                if(memberService.memImgList(commWriters.get(i)).size() != 0){
                    commWritersIMG.put(commWriters.get(i), (memberService.memImgList(commWriters.get(i))).get(0).getFilePath());
                } else {
                    commWritersIMG.put(commWriters.get(i), null);
                }
            }
        }

        JSONObject response = new JSONObject();
        response.put("commentList", commentList);
        response.put("commWritersIMG",commWritersIMG);

        return response;
    }

    //댓글 수정
    @ResponseBody
    @PostMapping(value = "/udpTipComm")
    public JSONObject tipUpdComment (@RequestBody Map<String, String> data) throws Exception {
        CommentDTO dto = new CommentDTO();

        dto.setNum(Integer.parseInt(data.get("num")));
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));
        dto.setContent(data.get("content"));

        commentService.updateComment(dto);

        //댓글 리스트
        List<CommentDTO> commentList = commentService.selectComment(dto);

        //댓글 작성자 img
        HashMap<String,String> commWritersIMG = new HashMap<String,String>();
        List<String> commWriters = commentService.selectCommWriters(dto);

        if(commWriters.size()!=0){
            for(int i=0; i < commWriters.size(); i++){
                if(memberService.memImgList(commWriters.get(i)).size() != 0){
                    commWritersIMG.put(commWriters.get(i), (memberService.memImgList(commWriters.get(i))).get(0).getFilePath());
                } else {
                    commWritersIMG.put(commWriters.get(i), null);
                }
            }
        }

        JSONObject response = new JSONObject();
        response.put("commentList", commentList);
        response.put("commWritersIMG",commWritersIMG);

        return response;
    }

    //like 등록
    @ResponseBody
    @PostMapping(value = "/regLikeTip")
    public JSONObject tipRegLike (HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        LikeBoardDTO dto = new LikeBoardDTO();

        dto.setUserId(member.getId());
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));

        likeService.insertLike(dto);
        likeService.addTipLikeCount(Integer.parseInt(data.get("boardIdx")));

        String likeDelYn = likeService.TipLikeYN(dto);

        JSONObject response = new JSONObject();
        response.put("likeDelYn", likeDelYn);

        return response;
    }

    //like 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpLikeTip")
    public JSONObject tipUdpLike (HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        LikeBoardDTO dto = new LikeBoardDTO();

        dto.setUserId(member.getId());
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));

        String likeDelYn = likeService.TipLikeYN(dto);

        if(likeDelYn.equals("N")){ //해제
            likeService.undoLike(dto);
            likeService.delTipLikeCount(Integer.parseInt(data.get("boardIdx")));
        }else{//재등록
            likeService.doLike(dto);
            likeService.addTipLikeCount(Integer.parseInt(data.get("boardIdx")));
        }

        likeDelYn = likeService.TipLikeYN(dto);

        JSONObject response = new JSONObject();
        response.put("likeDelYn", likeDelYn);

        return response;
    }

    //scrap 등록
    @ResponseBody
    @PostMapping(value = "/regScrapTip")
    public JSONObject tipRegScrap (HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        ScrapBoardDTO dto = new ScrapBoardDTO();

        dto.setUserId(member.getId());
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));

        scrapService.insertScrap(dto);
        scrapService.addTipScrapCount(Integer.parseInt(data.get("boardIdx")));

        String scrapDelYn = scrapService.TipScrapYN(dto);
        JSONObject response = new JSONObject();
        response.put("scrapDelYn", scrapDelYn);

        return response;
    }

    //scrap 상태 변경
    @ResponseBody
    @PostMapping(value = "/udpScrapTip")
    public JSONObject tipUdpScrap (HttpSession session, @RequestBody Map<String, String> data) throws Exception {
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        ScrapBoardDTO dto = new ScrapBoardDTO();

        dto.setUserId(member.getId());
        dto.setBoardIdx(Integer.parseInt(data.get("boardIdx")));
        dto.setBoardType(data.get("boardType"));

        String scrapDelYn = scrapService.TipScrapYN(dto);

        if(scrapDelYn.equals("N")){ //해제
            scrapService.undoScrap(dto);
            scrapService.delTipScrapCount(Integer.parseInt(data.get("boardIdx")));
        }else{//재등록
            scrapService.doScrap(dto);
            scrapService.addTipScrapCount(Integer.parseInt(data.get("boardIdx")));
        }

        scrapDelYn = scrapService.TipScrapYN(dto);
        JSONObject response = new JSONObject();
        response.put("scrapDelYn", scrapDelYn);

        return response;
    }

}
