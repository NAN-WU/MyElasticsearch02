
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;

/**
 * @author NAN
 * @date 2018/11/2 9:28
 */

public class MyTEST {
    @Test
    //创建索引
    public void test1() throws Exception {
        //创建客户端访问对象
        /**
         * Settings表示集群的设置
         * EMPTY：表示没有集群的配置
         * Settings.EMPTY
         */
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//创建文档对象
        // 方案一：组织Document数据
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("id",3);
//        map.put("title","3-ElasticSearch是一个基于Lucene的搜索服务器");
//        map.put("content","3-它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。");
        // 方案二：组织Document数据（使用ES的api构建json）
        // {id:1,title:"xxx",content:"xxxxxx"}
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("id", "1")
                .field("title", "900ElasticSearch是一个基于Lucene的搜索服务器。")
                .field("content", "100它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，" +
                        "是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。")
                .endObject();
        //创建索引、创建文档类型、设置唯一主键。同时创建文档
        client.prepareIndex("blog1","article","1").setSource(builder).get();//执行动作
        //关闭资源
        client.close();
    }
}


