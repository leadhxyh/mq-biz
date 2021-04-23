package com.lw.mq.biz.common.util;

import java.util.concurrent.TimeUnit;

public class Util {

    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {

        }
    }

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str) || str.trim().length() == 0);
    }

}
