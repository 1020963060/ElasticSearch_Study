package com.feri.es.domain;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *@Author feri
 *@Date Created in 2018/9/10 15:10
 */
@Document(indexName = "wyhusers",type = "user")
public class User {
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
