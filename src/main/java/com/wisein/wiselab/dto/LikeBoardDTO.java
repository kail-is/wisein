package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class LikeBoardDTO {
    private int idx;
    private int boardIdx;
    private String boardType;
    private String userId;
    private int likeCheck;
    private int parentNum;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLikeCheck() {
        return likeCheck;
    }

    public void setLikeCheck(int likeCheck) {
        this.likeCheck = likeCheck;
    }

    public int getParentNum() {
        return parentNum;
    }

    public void setParentNum(int parentNum) {
        this.parentNum = parentNum;
    }
}

