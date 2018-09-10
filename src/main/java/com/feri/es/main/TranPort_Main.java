package com.feri.es.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feri.es.domain.User;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 *@Author feri
 *@Date Created in 2018/9/10 16:59
 */
public class TranPort_Main {
    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        //1、创建配置对象---指定集群名称
        Settings settings=Settings.builder().put("cluster.name","es01").build();
        //2、创建客户端对象
        TransportClient client=new PreBuiltTransportClient(settings).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("10.8.163.50"),9300));
       // client.listedNodes()  //列出集群的所有节点
        //crud
        //新增
//        User user=new User();
//        user.setId(10001);
//        user.setUsername("学生");
//        JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(user));
        JSONObject jo=new JSONObject();
        jo.put("id","202");
        jo.put("username","师者");
//        IndexResponse response=client.prepareIndex("users","user",jo.getString("id")).setSource(jo,XContentType.JSON).get();
//        System.out.println("新增："+response.status().toString());

        GetResponse getResponse=client.prepareGet("users","user","200002").get();
        System.out.println("查询："+getResponse.getSourceAsString());
        UpdateResponse updateResponse=client.prepareUpdate("users","user","200002").setDoc(jo,XContentType.JSON).get();
        System.out.println("修改："+updateResponse.status().toString());
       // DeleteResponse deleteResponse=client.prepareDelete().setType("user").setIndex("users").execute().get();
//        DeleteResponse deleteResponse=client.prepareDelete("users","user","AWXCw-p13J3YPsq4SyV-").get();
//        System.out.println("删除："+deleteResponse.status().toString());

        //DeleteResponse deleteResponse=client.prepareDelete("users","user","AWXCxFaG3J3YPsq4SyV_").get();
    }
}
