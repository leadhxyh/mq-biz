package com.lw.mq.biz.service.impl;

import com.lw.mq.biz.common.thread.LwThreadFactory;
import com.lw.mq.biz.common.util.Util;
import com.lw.mq.biz.db.meta.TopicRepository;
import com.lw.mq.biz.entity.LastUpdateEntity;
import com.lw.mq.biz.inf.TimerService;
import com.lw.mq.biz.service.CacheUpdateService;
import com.lw.mq.biz.service.TopicService;
import com.lw.mq.biz.service.common.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TopicServiceImpl extends AbstractBaseService implements TopicService, TimerService, CacheUpdateService {

    @Autowired
    private TopicRepository topicRepository;

    private AtomicBoolean startFlag = new AtomicBoolean(false);

    private AtomicBoolean updateFlag = new AtomicBoolean(false);

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100), LwThreadFactory.create("TopicService", true),
                new ThreadPoolExecutor.DiscardOldestPolicy());

    private long lastTime = System.currentTimeMillis();

    private LastUpdateEntity lastUpdateEntity = null;

    @PostConstruct
    private void init() {
        super.setBaseRepository(topicRepository);
    }

    @Override
    public void start() {
        if (startFlag.compareAndSet(false, true)) {
            updateCache();
            executor.execute(() -> {
                updateCache();
                Util.sleep(3000);
            });
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void info() {

    }

    @Override
    public void updateCache() {
        if (updateFlag.compareAndSet(false, true)) {
            if (checkChanged()) {
                forceUpdateCache();
            }
            updateFlag.set(false);
        }
    }

    @Override
    public void forceUpdateCache() {

    }

    @Override
    public String getCacheJson() {
        return null;
    }


    private boolean checkChanged() {
        boolean flag = doCheckChanged();
        if (!flag) {

        } else {
            lastTime = System.currentTimeMillis();
        }
        return flag;
    }

    private boolean doCheckChanged() {
        boolean flag = false;

        try {
            LastUpdateEntity temp = topicRepository.getLastUpdate();
            if ((lastUpdateEntity == null && temp != null) || (lastUpdateEntity != null && temp == null)) {
                lastUpdateEntity = temp;
                flag = true;
            } else if (lastUpdateEntity != null && temp != null
                && (temp.getMaxId() != lastUpdateEntity.getMaxId()
                        || temp.getLastDate().getTime() != lastUpdateEntity.getLastDate().getTime()
                        || temp.getCount() != lastUpdateEntity.getCount())) {
                lastUpdateEntity = temp;
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return flag;
    }
}
