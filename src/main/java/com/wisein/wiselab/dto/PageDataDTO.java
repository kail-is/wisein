package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class PageDataDTO {
    private String location;
    private int dataPerPage;
    private int startRow;
}
