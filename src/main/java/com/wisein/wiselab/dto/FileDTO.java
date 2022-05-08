package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class FileDTO {
    private int idx;
    private String brdRef;
    private String fileName;
    private String orgFileName;
    private String filePath;
    private String fileExtension;
    private String dltYn;
    private String regId;
}