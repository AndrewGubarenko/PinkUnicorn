package com.pink.unicorn.domain.PlainObjects;

import java.util.HashSet;
import java.util.Set;

public class PlainTag {

    private Long id;
    private String name;
    private Set<Long> plainProductIdList = new HashSet<>();

    public PlainTag(){}
    public PlainTag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getPlainProductIdList() {
        return plainProductIdList;
    }

    public void setPlainProductIdList(Set<Long> plainProductIdList) {
        this.plainProductIdList = plainProductIdList;
    }
}
