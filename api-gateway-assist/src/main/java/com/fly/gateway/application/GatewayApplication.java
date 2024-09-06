package com.fly.gateway.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class GatewayApplication implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}
