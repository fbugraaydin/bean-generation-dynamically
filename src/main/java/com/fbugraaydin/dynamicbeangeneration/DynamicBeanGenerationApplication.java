package com.fbugraaydin.dynamicbeangeneration;

import com.fbugraaydin.dynamicbeangeneration.repositories.GenericRepoBean;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class DynamicBeanGenerationApplication {

    @Value("${tenants}")
    List<String> tenants;

    final DefaultListableBeanFactory context;

    public DynamicBeanGenerationApplication(DefaultListableBeanFactory context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(DynamicBeanGenerationApplication.class, args);
    }

    @Bean
    DefaultListableBeanFactory beanFactory() {
        return new DefaultListableBeanFactory();
    }

    @EventListener(ApplicationStartedEvent.class)
    public void initialize() {
        tenants.forEach(tenant -> {
            final String beanName = tenant + "Bean";
            GenericBeanDefinition gbd = new GenericBeanDefinition();
            gbd.setBeanClass(GenericRepoBean.class);
            MutablePropertyValues mpv = new MutablePropertyValues();
            mpv.add("tenant", tenant);
            gbd.setPropertyValues(mpv);
            context.registerBeanDefinition(beanName, gbd);
        });
    }

}
