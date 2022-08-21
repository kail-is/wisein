package com.wisein.wiselab.service;

import com.wisein.wiselab.common.FileUtils;
import com.wisein.wiselab.dao.CommonDAO;
import com.wisein.wiselab.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j

public class CommonServiceImpl implements CommonService {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private CommonDAO dao;

    @Override
    public List<FileDTO> selectImgList(String brd, String id) throws Exception {
        String brdRef =  brd + "||" + id;
        List<FileDTO> list = dao.selectFileList(brdRef);
        return list;
    }

    @Override
    public String uploadImgList(String brd, String regId, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        String hashcode = multipartHttpServletRequest.getParameter("hashcode");

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        String name;
        String url = "";

        while(iterator.hasNext()) {
            name = iterator.next();
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
            for(MultipartFile multipartFile : list) {
                String contType =  multipartFile.getContentType();
                String[] contArr = contType.split("/");
                String extension = contArr[1];
            }
        }

        String brdRef = brd + "||" + hashcode;
        List<FileDTO> list = fileUtils.parseFileInfo(brdRef, regId, "image", multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list) == false) {
            dao.uploadImgList(list);
            url = list.get(0).getFilePath();
        }

        return url;
    }

    @Override
    public void delUserImg(String fileNm) throws Exception {
        dao.delUserImg(fileNm);
    }


}
