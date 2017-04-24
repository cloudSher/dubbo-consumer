package com.sher.boot.client;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wei.zhao on 2017/4/7.
 */
public class EsClient {

    public static void init() throws UnknownHostException {

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));

        SearchResponse sr = client.prepareSearch()
                .setQuery(QueryBuilders.matchQuery("message", "myProduct"))
                .addAggregation(AggregationBuilders.terms("top_10_states")
                        .field("state").size(10))
                .execute().actionGet();
    }
}
