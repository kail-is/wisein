package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.CommonService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class CommonController {

    @Autowired
    CommonService comService;

    @GetMapping(value = "/error/404Page")
    public String error404(HttpServletResponse res, Model model) throws Exception {
        model.addAttribute("code", "ERROR_404");
        return "errors/404";
    }

    @GetMapping(value = "/error/errorPage")
    public String errorPage() throws Exception {
        return "errors/error";
    }

    @ResponseBody
    @PostMapping(value = "/upload")
    public String postImg (HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        MemberDTO member = (MemberDTO)(session.getAttribute("member"));
        String brdNm = multipartHttpServletRequest.getParameter("brdNm");

        String url = comService.uploadImgList(brdNm, member.getId(), multipartHttpServletRequest);

        return url;
    }

    @ResponseBody
    @PostMapping(value = "/uploadImg")
    public String postJsonImg (HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        MemberDTO member = (MemberDTO)(session.getAttribute("member"));
        String brdNm = multipartHttpServletRequest.getParameter("brdNm");
        String brdCdNm = multipartHttpServletRequest.getParameter("brdCdNm");
        String fileInfo = multipartHttpServletRequest.getParameter("fileInfo");

        System.out.println(fileInfo);

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(fileInfo);
        JSONObject fileInfos = (JSONObject) obj;

        String url = comService.uploadImgList(brdNm, member.getId(), brdCdNm, fileInfos);

        return url;
    }


    @ResponseBody
    @GetMapping(value = "/delImg")
    public Map<String, String> deleteUserImg(@RequestParam("delImgFileNm") String fileNm) throws Exception {

        comService.delUserImg(fileNm);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("code","0000");
        map.put("msg", "삭제 완료.");
        return map;
    }

    @ResponseBody
    @GetMapping(value = "/updateHash")
    public Map<String, String> updateHash(HttpSession session, String brdNm, String randomStr, String brdNum) throws Exception {

        // 로그인 사용자 요건 확인 고민 중
        FileDTO file = new FileDTO();

        randomStr = brdNm + "||" + randomStr;
        brdNum = brdNm + "||" + brdNum;

        file.setTemRefHash(randomStr);
        file.setRefNum(brdNum);

        comService.updateHash(file);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("code","0000");
        map.put("msg", "업데이트 완료.");
        return map;
    }

}

