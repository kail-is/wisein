package com.wisein.wiselab.config;

import java.util.Random;

//임시 인증키 생성

public class AuthKeyConfig {

    public String tempKeyCreate() {
        Random random = new Random();

        String tempKey="";

        for (int i=0; i<3; i++) {
            int index = random.nextInt(25)+65;
            tempKey+=(char) index;
        }
        int num_index = random.nextInt(899999)+100000;
        tempKey+=num_index;

        return tempKey;
    }
}
