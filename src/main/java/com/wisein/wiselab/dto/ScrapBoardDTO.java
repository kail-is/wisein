package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class ScrapBoardDTO {
    private int idx;
    private int boardIdx;
    private String boardType;
    private String userId;
    private int likeCheck;
    private int parentNum;
    private String brdRef;
    private String delYn;

}

