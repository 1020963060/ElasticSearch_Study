package com.feri.es.service;

import com.feri.es.domain.User;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/9/10 15:32
 */
public interface UserESService {
    boolean save(User user);
    boolean delById(int id);
    List<User> queryAll();
    List<User> queryAll(String name);


}
