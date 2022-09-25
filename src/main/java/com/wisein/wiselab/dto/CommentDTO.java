package com.wisein.wiselab.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CommentDTO {
    private int num;
    private int boardIdx;
    private String boardType;
    private String content;
    private String writer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;
    private Date updDate;
    private String delYn;
}
