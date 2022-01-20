package com.fbugraaydin.dynamicbeangeneration.repositories;

public class GenericRepoBean {
    public String tenant;

    public String doSomething() {
        return "Getting data for " + tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
