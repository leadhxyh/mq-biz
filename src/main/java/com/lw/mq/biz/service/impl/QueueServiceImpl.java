package com.lw.mq.biz.service.impl;

import com.lw.mq.biz.common.thread.LwThreadFactory;
import com.lw.mq.biz.common.util.Util;
import com.lw.mq.biz.inf.TimerService;
import com.lw.mq.biz.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class QueueServiceImpl implements QueueService, TimerService {

    private Logger log = LoggerFactory.getLogger(QueueServiceImpl.class);

    private AtomicBoolean startFlag = new AtomicBoolean(false);

    private volatile boolean isRunning = true;

    // 上次记录时间值
    private volatile long lastUpdateTime = System.currentTimeMillis();

    private ThreadPoolExecutor executor;

    @Override
    public void start() {
        if (startFlag.compareAndSet(false, true)) {
            executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(50), LwThreadFactory.create("QueueService", true), new ThreadPoolExecutor.DiscardOldestPolicy());
            executor.execute(() -> {
                while (isRunning) {
                    try {
                        lastUpdateTime = System.currentTimeMillis();
                    } catch (Exception e) {

                    }
                    Util.sleep(5000);
                }
            });
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void info() {

    }

    private boolean checkChanged() {
        return false;
    }
}
