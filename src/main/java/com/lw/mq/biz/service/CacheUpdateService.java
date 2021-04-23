package com.lw.mq.biz.service;

public interface CacheUpdateService {

    void updateCache();

    void forceUpdateCache();

    String getCacheJson();

}
