package com.example.apacheignitedemo.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

public class PersonEntity implements Serializable {

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String name;

    public PersonEntity(Long id, String name) {
        this.id = id;
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
}
