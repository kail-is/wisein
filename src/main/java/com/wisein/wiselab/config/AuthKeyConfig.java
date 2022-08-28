package com.wisein.wiselab.config;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class AuthKeyConfig {

    //임시 인증키 생성
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

    // 임시 비밀번호 생성
    public String tempKeyPassword(int size) {

        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer tempPassword = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            idx = sr.nextInt(len);
            tempPassword.append(charSet[idx]);
        }

        return tempPassword.toString();
    }


}
