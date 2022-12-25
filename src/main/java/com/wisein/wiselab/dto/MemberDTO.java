package com.wisein.wiselab.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String site;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private Date regDate;
    private String meetLink;
    private String meetYn;
    private List<FileDTO> fileList;
    private String fileInfos;
    private String memStatus;
    private String authState;
    private String memberToken;

}
