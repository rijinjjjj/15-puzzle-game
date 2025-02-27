package com.rijin.ui;

import java.util.Random;

public class Captcha {
    private Captcha() {
    }

    public static String getCode() {
        //生成长度为5的验证码
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int num = r.nextInt(62);
            char c = 0;
            if (num < 10) {
                c = (char)('0' + num);
            }else if (num < 36) {
                c = (char)('A' + num - 10);
            }else {
                c = (char)('a' + num - 36);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}