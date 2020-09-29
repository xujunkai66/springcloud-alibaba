package com.xjk.oauthjwt.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xujunkai
 */
public class Tese {
    public static void main(String[] args) {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalSecret);
    }
}
