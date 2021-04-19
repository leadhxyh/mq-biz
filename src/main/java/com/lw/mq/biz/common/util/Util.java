package com.lw.mq.biz.common.util;

import java.util.concurrent.TimeUnit;

public class Util {

    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {

        }
    }

}
