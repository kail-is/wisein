package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class CommonController {

    @Autowired
    CommonService comService;

    @ResponseBody
    @PostMapping(value = "/upload")
    public String postImg (HttpSession session, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        MemberDTO ne = (MemberDTO)(session.getAttribute("member"));
        System.out.println("multipartHttpServletRequest" + multipartHttpServletRequest);

        String url = comService.uploadImgList("recm", ne.getId(), multipartHttpServletRequest);

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

}

