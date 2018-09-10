package com.feri.es.service.impl;

import com.feri.es.dao.UserESDao;
import com.feri.es.domain.User;
import com.feri.es.service.UserESService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/9/10 15:34
 */
@Service
public class UserESServiceImpl implements UserESService {
    @Autowired
    private UserESDao dao;
    @Override
    public boolean save(User user){
        dao.save(user);
        return true;
    }

    @Override
    public boolean delById(int id) {
        dao.deleteById(id);
        return true;
    }

    @Override
    public List<User> queryAll() {
        Iterator<User> users= dao.findAll().iterator();
        List<User> userList=new ArrayList<>();
        while (users.hasNext()){
            userList.add(users.next());
        }
        return userList;
    }

    @Override
    public List<User> queryAll(String name) {
        //模糊查询
        QueryBuilder builder1 = QueryBuilders.wildcardQuery("username", "*" + name + "*");
//        QueryBuilder builder2 = QueryBuilders.wildcardQuery("id", "*" + name + "*");
//        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
//        boolQueryBuilder.must(builder1);
//        boolQueryBuilder.must(builder2);
//        Iterator<User> users = dao.search(boolQueryBuilder).iterator();
        Iterator<User> users = dao.search(builder1).iterator();
        List<User> userList = new ArrayList<>();
        while (users.hasNext()) {
            userList.add(users.next());
        }
        return userList;
    }
}
