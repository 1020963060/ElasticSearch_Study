package com.feri.es.utils;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2018/9/10 17:35
 */
public class ESUtils {
    private String csname;
    private String cshost;
    private int csport;
    private String indexname;
    private String type;
    private TransportClient client;
   public ESUtils() throws UnknownHostException {
       Settings settings=Settings.builder().put("cluster.name",csname).build();
       //2、创建客户端对象
       client=new PreBuiltTransportClient(settings).addTransportAddress(
               new InetSocketTransportAddress(InetAddress.getByName(cshost),csport));

   }
   //新增
    public String add(Map<String,Object> objs){
        IndexResponse indexResponse=client.prepareIndex(indexname,type,objs.get("id").toString()).setSource(objs,XContentType.JSON).get();
        return indexResponse.status().name();
    }

    //修改
    public String update(Map<String,Object> objs){
        UpdateResponse updateResponse=client.prepareUpdate(indexname,type,objs.get("id").toString()).setDoc(objs,XContentType.JSON).get();
        return updateResponse.status().name();
    }

    //删除
    public String del(String id){
        DeleteResponse deleteResponse=client.prepareDelete(indexname,type,id).get();
        return deleteResponse.status().name();
    }
    //查询
    public String query(String id){
        GetResponse getResponse=client.prepareGet(indexname,type,id).get();
        return getResponse.getSourceAsString();
    }
    //模糊查询
    public List<Map<String,Object>> queryLike(String fname,String name){
       SearchRequestBuilder builder=client.prepareSearch();
        List<Map<String,Object>> list=new ArrayList<>();
       builder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);//扫描查询
        builder.setQuery(QueryBuilders.matchPhrasePrefixQuery(fname,name));
        SearchResponse searchResponse=builder.get();
        SearchHits hits=searchResponse.getHits();
        for(int i=0;i<hits.totalHits;i++){
            SearchHit hitFields=hits.getAt(i);
            list.add(hitFields.getSource());
        }
        return list;
    }
    //关闭
    public void cclose(){
       client.close();
    }

}
