package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.FileDTO;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface CommonService {

    public List<FileDTO> selectImgList(String brd, String id) throws Exception;

    public String uploadImgList(String brd, String regId, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    public String uploadImgList(String brd, String regId, String brdNumCd, JSONObject fileInfos) throws Exception;

    public void delUserImg(String fileNm) throws Exception;

    public void deleteAllImg(String brdRef) throws Exception;

    public void updateHash(FileDTO dto) throws Exception;

}
