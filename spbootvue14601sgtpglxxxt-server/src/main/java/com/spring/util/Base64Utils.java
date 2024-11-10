package com.spring.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    private static final String CHARSET = "utf-8";

    public static String encode(String str) {
        try {
            return encode(str.getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(byte[] bytes) {
        try {
            return new String(Base64.getEncoder().encode(bytes), CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(byte[] bytes) {
        byte[] bytes1 = Base64.getDecoder().decode(bytes);

        try {
            return new String(bytes1, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String data) {
        try {
            return decode(data.getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
