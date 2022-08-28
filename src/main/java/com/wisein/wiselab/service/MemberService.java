package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MemberDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {

    public void register(MemberDTO dto) throws Exception;

    public int idDupChk(String userId) throws Exception;

    public MemberDTO login(MemberDTO dto) throws Exception;

    public String findTempKey(String id) throws Exception;

    public void logout(HttpSession session) throws Exception;

    public void authStateUpdate(String id) throws Exception;

    public int authIdExist(String id) throws Exception;

    public void modify(MemberDTO dto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    public void addChgePw(MemberDTO dto) throws Exception;

    public void modMemByToken(String token) throws Exception;

    public void delUserImg(String fileNm) throws Exception;

    public List<FileDTO> memImgList(String id) throws Exception;

    public void withdraw(MemberDTO dto, HttpSession session) throws Exception;

}

