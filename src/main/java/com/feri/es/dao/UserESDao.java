package com.feri.es.dao;

import com.feri.es.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 *@Author feri
 *@Date Created in 2018/9/10 15:28
 */
public interface UserESDao extends ElasticsearchRepository<User,Integer> {

}
