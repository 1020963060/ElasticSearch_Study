package com.feri.es.controller;

import com.feri.es.domain.User;
import com.feri.es.service.UserESService;
import com.feri.es.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@Author feri
 *@Date Created in 2018/9/10 15:52
 */

@RestController
public class UserESController {
    @Autowired
    private UserESService service;

    @RequestMapping("useresadd.do")
    public R save(User user){
        if(service.save(user)){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }
    @RequestMapping("useresdel.do")
    public R del(int id){
        if(service.delById(id)){
            return R.setOK();
        }else {
            return R.setERROR();
        }
    }
    @RequestMapping("usereslist.do")
    public List<User> list(){
       return service.queryAll();
    }
    @RequestMapping("usereslike.do")
    public List<User> list(String name){
        return service.queryAll(name);
    }


}
