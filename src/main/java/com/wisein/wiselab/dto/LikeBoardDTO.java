package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class LikeBoardDTO {
    private int idx;
    private int boardIdx;
    private String boardType;
    private String userId;
    private String delYn;

}

