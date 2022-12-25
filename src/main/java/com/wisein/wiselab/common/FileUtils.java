package com.wisein.wiselab.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Data;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wisein.wiselab.dto.FileDTO;

@Component
public class FileUtils {

    public List<FileDTO> parseFileInfo(String brdRef, String regId, String fileType, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }
        String brdType = brdRef.split("\\|\\|")[0];
        String brdNum = brdRef.split("\\|\\|")[1];

        String savingPath, printingPath;

        List<FileDTO> fileList = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();

        if (fileType.equals("image")) {
            savingPath = "src/main/webapp/resources/file/images/" + current.format(format);
            printingPath = "resources/file/images/" + current.format(format);
        } else {
            savingPath = "src/main/webapp/resources/file/" + current.format(format);
            printingPath = "resources/file/" + current.format(format);
        }

        File file = new File(savingPath);
        if (file.exists() == false) {
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String newFileName, fileExtension, contType;

        while (iterator.hasNext()) {
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for (MultipartFile multipartFile : list) {
                if (multipartFile.isEmpty() == false) {

                    contType = multipartFile.getContentType();
                    String[] contArr = contType.split("/");
                    fileExtension = contArr[1];
                    newFileName = Long.toString(System.nanoTime()) + "." + fileExtension;

                    if (ObjectUtils.isEmpty(contType)) {
                        break;
                    }

                    FileDTO oneFile = new FileDTO();

                    oneFile.setBrdRef(brdRef);
                    oneFile.setFileName(newFileName);
                    oneFile.setOrgFileName(multipartFile.getOriginalFilename());
                    oneFile.setFilePath(printingPath + "/" + newFileName);
                    oneFile.setFileExtension(fileExtension);
                    oneFile.setRegId(regId);
                    fileList.add(oneFile);

                    file = new File(savingPath + "/" + newFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }

    public List<FileDTO> parseFileInfo(String brdRef, String regId, String fileType, JSONObject fileInfos) throws Exception {
        if (ObjectUtils.isEmpty(fileInfos)) {
            return null;
        }
        String brdType = brdRef.split("\\|\\|")[0];
        String brdNum = brdRef.split("\\|\\|")[1];

        List<FileDTO> fileList = new ArrayList<>();
        Iterator iterator = fileInfos.keySet().iterator();

//        while (iterator.hasNext()) {

//            String key = (String)iterator.next();

//            if(fileInfos.get(key) instanceof JSONObject)
//            {
                JSONObject originalImageInfo = (JSONObject) fileInfos.get("originalImageInfo");

                FileDTO oneFile = new FileDTO();
                oneFile.setBrdRef(brdRef);
                oneFile.setFileName((String) fileInfos.get("uuid"));
                oneFile.setOrgFileName((String) fileInfos.get("name"));
                oneFile.setFilePath((String) fileInfos.get("originalUrl"));
                oneFile.setFileExtension((String) originalImageInfo.get("format"));
                oneFile.setRegId(regId);
                fileList.add(oneFile);
//           }
//        }
        return fileList;
    }
}
