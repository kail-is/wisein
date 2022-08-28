package com.wisein.wiselab.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecmDTO {
    private int num;
    private int refMatzip;
    private String writer;
    private float star;
    private String subject;
    private String content;
    private Date regDate;
    private Date updDate;
    private String delYn;
    private List<FileDTO> recmImgList;
}

