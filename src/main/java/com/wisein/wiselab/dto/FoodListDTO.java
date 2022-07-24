package com.wisein.wiselab.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class FoodListDTO {
    private int id;
    private String category;
    private String matzipdata;
}
