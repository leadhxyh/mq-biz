package com.lw.mq.biz.common.trace.spi;

public interface Transaction {

    String SUCCESS = "0";

    void setStatus(String status);

    void setStatus(Throwable e);

    void addData(String key, Object value);

    void complete();

}
