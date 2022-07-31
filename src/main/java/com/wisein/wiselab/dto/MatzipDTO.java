package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class MatzipDTO {
    private int id;
    private float rate;
    private String category;
    private String matzipData;
    private int count;
    private String closedState;
}
