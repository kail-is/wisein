package com.wisein.wiselab.dto;

import lombok.Data;

import java.util.Date;
import com.wisein.wiselab.common.paging.PaginationInfo;
import lombok.EqualsAndHashCode;

@Data
public class QaListDTO extends PaginationInfo {
    private int num;
    private String category;
    private String writer;
    private String subject;
    private String content;
    private String regDate;
    private String updDate;
    private String adpYn;
    private String delYn;
    private int parentNum;
    private int likeCount;
    private int adpNum;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdDate() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getAdpYn() {
        return adpYn;
    }

    public void setAdpYn(String adpYn) {
        this.adpYn = adpYn;
    }

    public String getDelYn() {
        return delYn;
    }

    public void setDelYn(String delYn) {
        this.delYn = delYn;
    }

    public int getParentNum() {
        return parentNum;
    }

    public void setParentNum(int parentNum) {
        this.parentNum = parentNum;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getAdpNum() {
        return adpNum;
    }

    public void setAdpNum(int adpNum) {
        this.adpNum = adpNum;
    }
}

