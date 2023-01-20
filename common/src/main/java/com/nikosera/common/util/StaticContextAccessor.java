package com.nikosera.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Component
public class StaticContextAccessor {

    private static StaticContextAccessor instance;

    private final ApplicationContext applicationContext;

    public StaticContextAccessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void registerInstance() {
        instance = this;
    }

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }

}
