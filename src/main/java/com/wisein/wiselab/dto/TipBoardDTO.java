package com.wisein.wiselab.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TipBoardDTO {
    private int num;
    private String category;
    private String writer;
    private String subject;
    private String content;
    private Date regDate;
    private Date updDate;
    private String delYn;
    private int count;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
