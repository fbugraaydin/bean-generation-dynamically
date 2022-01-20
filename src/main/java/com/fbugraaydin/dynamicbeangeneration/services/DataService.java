package com.fbugraaydin.dynamicbeangeneration.services;

import com.fbugraaydin.dynamicbeangeneration.repositories.GenericRepoBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    final DefaultListableBeanFactory context;

    public DataService(DefaultListableBeanFactory context) {
        this.context = context;
    }

    public String getData(String tenant) {
        final String beanName = tenant + "Bean";
        GenericRepoBean bean = (GenericRepoBean) context.getBean(beanName);
        return bean.doSomething();
    }

}
