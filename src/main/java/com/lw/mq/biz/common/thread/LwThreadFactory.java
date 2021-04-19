package com.lw.mq.biz.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class LwThreadFactory implements ThreadFactory {

    private final String prefixName;

    private int priority;

    private final boolean daemon;

    private final AtomicLong threadNumber = new AtomicLong(1);

    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("MQ");

    private LwThreadFactory(String prefixName, boolean daemon) {
        this.prefixName = prefixName;
        this.daemon = daemon;
    }

    private LwThreadFactory(String prefixName, int priority, boolean daemon) {
        this.prefixName = prefixName;
        this.priority = priority;
        this.daemon = daemon;
    }

    public static ThreadGroup getThreadGroup() {
        return THREAD_GROUP;
    }

    public static ThreadFactory create(String prefixName, boolean daemon) {
        return new LwThreadFactory(prefixName, daemon);
    }

    public static ThreadFactory create(String prefixName, int priority, boolean daemon) {
        return new LwThreadFactory(prefixName, priority, daemon);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(THREAD_GROUP, r,
                THREAD_GROUP.getName() + "-" + prefixName + "-" + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        if (priority > 0) {
            thread.setPriority(priority);
        }
        return thread;
    }

}
