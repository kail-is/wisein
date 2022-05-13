package com.wisein.wiselab.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QaListDTO {
    private int num;
    private String category;
    private String writer;
    private String subject;
    private String content;
    private Date reg_date;
    private Date upd_date;
    private String adp_yn;
    private String del_yn;
    private int parent_num;

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

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(Date upd_date) {
        this.upd_date = upd_date;
    }

    public String getAdp_yn() {
        return adp_yn;
    }

    public void setAdp_yn(String adp_yn) {
        this.adp_yn = adp_yn;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public int getParent_num() {
        return parent_num;
    }

    public void setParent_num(int parent_num) {
        this.parent_num = parent_num;
    }
}

