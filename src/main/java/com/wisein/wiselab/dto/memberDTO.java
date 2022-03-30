package com.wisein.wiselab.dto;

import lombok.Data;

import java.util.Date;

@Data
public class memberDTO {
    private String id;
    private String pw;
    private String name;
    private String site;
    private Date hireDate;
    private Date regDate;
    private String meetLink;
    private String meetYn;

}
