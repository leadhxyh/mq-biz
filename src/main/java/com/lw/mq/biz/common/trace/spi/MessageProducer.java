package com.lw.mq.biz.common.trace.spi;

public interface MessageProducer {

    void logError(Throwable cause);

    void logError(String message, Throwable cause);

    void logEvent(String type, String name);

    void logEvent(String type, String name, String status, String nameValuePairs);

    Transaction newTransaction(String type, String name);

}
