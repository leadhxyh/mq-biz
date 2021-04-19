package com.lw.mq.biz.service.impl;

import com.lw.mq.biz.common.thread.LwThreadFactory;
import com.lw.mq.biz.common.util.Util;
import com.lw.mq.biz.inf.TimerService;
import com.lw.mq.biz.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TopicServiceImpl implements TopicService, TimerService {

    private AtomicBoolean startFlag = new AtomicBoolean(false);

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100), LwThreadFactory.create("TopicService", true),
                new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    public void start() {
        if (startFlag.compareAndSet(false, true)) {
            executor.execute(() -> {
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
}
