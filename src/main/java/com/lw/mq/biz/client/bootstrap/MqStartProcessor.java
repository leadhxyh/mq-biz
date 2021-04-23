package com.lw.mq.biz.client.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class MqStartProcessor implements BeanFactoryPostProcessor, PriorityOrdered, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(MqStartProcessor.class);

    protected static AtomicBoolean initFlag = new AtomicBoolean(false);

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (this.environment != null) {
            if (initFlag.compareAndSet(false, true)) {
                logger.info("消息客户端开始初始化");

                logger.info("消息客户端初始化完成");
            }
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
