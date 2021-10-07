package com.bigdatacompany.elastic;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        /*

                  --Index Apı--
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("name","Apple Macbook Air");
        json.put("details","Intel Core i5, 16gb ram, 128GB SSD");
        json.put("price","5420");
        json.put("provider","Apple Tr");

        IndexResponse indexResponse = client.prepareIndex("product", "_doc", "3")
                .setSource(json, XContentType.JSON)
                .get();
        System.out.println(indexResponse);
         */

        /*
                --GetApı--
        GetResponse getResponse = client.prepareGet("product", "_doc", "3").get();
        Map<String, Object> source = getResponse.getSource();
        String name = (String) source.get("name");
        String price = (String) source.get("price");
        String details = (String) source.get("details");
        String provider = (String) source.get("provider");

        System.out.println("name = "+name+ " price = "+price+" details = "+details+" provider = "+provider);
         */

        /*
                --Search Api--
        SearchResponse searchResponse = client.prepareSearch("product")
                .setTypes("_doc")
                .setQuery(QueryBuilders.matchQuery("price", "2500"))
                .get();

        SearchHit[] hits = searchResponse.getHits().getHits();

        for (SearchHit hit : hits){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
         */

        /*
                --Delete Api--
        DeleteResponse deleteResponse = client.prepareDelete("product","_doc","1").get();
        System.out.println(deleteResponse.getId());
         */

        /*
               --Delete By Query Api--
        BulkByScrollResponse response =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(QueryBuilders.matchQuery("name", "Apple"))
                        .source("product")
                        .get();

        System.out.println(response.getDeleted());
         */
    }
}
