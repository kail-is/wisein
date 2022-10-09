package com.wisein.wiselab.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MailDTO {
    String title;
    String toAddress;
    String messageContent;
}
